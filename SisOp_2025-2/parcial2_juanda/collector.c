#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <unistd.h>
#include <sys/socket.h>
#include <arpa/inet.h>
#include <sys/ipc.h>
#include <sys/shm.h>
#include <signal.h>
#include <semaphore.h>
#include <fcntl.h>
#include <time.h>


struct host_info{
    char ip[32];
    float cpu_usage;
    float user_pct;
    float sys_pct;
    float idle_pct;
    float mem_used;
    float mem_free;
    float swap_total;
    float swap_free;
    time_t last_update;
    int updated;
};


#define MAX_HOSTS 4
#define SHM_KEY 12345
#define SEM_NAME "/collector_semaphore"


int buscar_o_crear_host(struct host_info *hosts, char *ip_reicibida){
    int primer_libre = -1;
    for(int i=0; i<MAX_HOSTS; i++){
        if(hosts[i].updated == 1 && strcmp(hosts[i].ip, ip_reicibida) == 0){
            return i;
        }

        if(hosts[i].updated == 0 && primer_libre == -1){
            primer_libre = i;
        }

    }

    if(primer_libre != -1){
        strcpy(hosts[primer_libre].ip, ip_reicibida);
        hosts[primer_libre].updated = 1;
        return primer_libre;
        }
    
    return -1;

}

void handle_sigint(int sig) {
    printf("\nApagando servidor... Borrando semáforo del sistema.\n");
    sem_unlink(SEM_NAME); // ¡Esto es vital para no dejar basura!
    printf("Limpieza terminada. Adiós.\n");
    exit(0);
}


int main(int argc, char *argv[]){

    sem_t *sem = sem_open(SEM_NAME, O_CREAT, 0644, 1);
    if(sem == SEM_FAILED){
        perror("sem_open");
        exit(1);
    }

    if(argc <2 ){
        printf("Uso: ./collector <puerto>\n");
        printf("Ejemplo: ./collector 8080\n");
        return 1;
    }

    //Atoi para convertir string a entero
    int puerto = atoi(argv[1]);

    //Memoria compartida para despues pasar información a visalizador
    int shm_id=shmget(SHM_KEY, sizeof(struct host_info)*MAX_HOSTS, IPC_CREAT | 0666);
    if(shm_id <0){
        perror("shmget");
        exit(1);
    }

    struct host_info *hosts=(struct host_info *) shmat(shm_id, NULL, 0);

    //Socket parte servidor
    int server_sock = socket(AF_INET, SOCK_STREAM, 0);
    struct sockaddr_in server_addr, client_addr;
    int opt = 1;
    setsockopt(server_sock, SOL_SOCKET, SO_REUSEADDR, &opt, sizeof(opt));

    server_addr.sin_family = AF_INET;
    server_addr.sin_addr.s_addr = INADDR_ANY;
    server_addr.sin_port = htons(puerto);

    int r=bind(server_sock, (struct sockaddr *)&server_addr, sizeof(server_addr));
    if (r<0){
        perror("Error en bind");
        close(server_sock);
        exit(1);
    }

    int l=listen(server_sock, 6);
    if (l<0){
        perror("Error en listen");
        close(server_sock);
        exit(1);
    }

    //Eliminación de los procesos hijos completamente
    signal(SIGCHLD, SIG_IGN);

    //Obtener el tamaño del cliente
    socklen_t cliente_len=sizeof(client_addr);
    
    //Bucle para poder admitir varias conexiones
    while(1){


        int client_sock=accept(server_sock, (struct sockaddr *)&client_addr, &cliente_len);
        if(client_sock<0){
            perror("Error en accept");
            close(server_sock);
            exit(1);
        }

        //Crear proceso hijo para atender al cliente
        pid_t pid=fork();
        if(pid<0){
            perror("Error en fork");
            close(client_sock);
            continue;
        }

        if(pid==0){ //Proceso hijo
            close(server_sock);
            char buffer[256];
            memset(buffer, 0, sizeof(buffer));

            //Definimos el recv indicando el socket,bufer, tamaño y flags
            int b=recv(client_sock, buffer, sizeof(buffer)-1, 0);
            if(b>0){

                char *tipo=strtok(buffer, ";");
                char *ip=strtok(NULL, ";");

                if(tipo!= NULL && ip != NULL){

                    sem_wait(sem); //Bloquear sección crítica

                    int indice=buscar_o_crear_host(hosts, ip);
                    if(indice != -1){

                        hosts[indice].last_update = time(NULL);

                        if(strcmp(tipo, "CPU")==0){
                            char *cpu_usage_str=strtok(NULL, ";");
                            char *user_pct_str=strtok(NULL, ";");
                            char *sys_pct_str=strtok(NULL, ";");
                            char *idle_pct_str=strtok(NULL, ";");

                            if(cpu_usage_str != NULL && user_pct_str != NULL && sys_pct_str != NULL && idle_pct_str != NULL){

                                hosts[indice].cpu_usage = atof(cpu_usage_str);
                                hosts[indice].user_pct = atof(user_pct_str);
                                hosts[indice].sys_pct = atof(sys_pct_str);
                                hosts[indice].idle_pct = atof(idle_pct_str);

                            }

                        } else if (strcmp(tipo, "MEM")==0){
                            char *mem_used_str=strtok(NULL, ";");
                            char *mem_free_str=strtok(NULL, ";");
                            char *swap_total_str=strtok(NULL, ";");
                            char *swap_free_str=strtok(NULL, ";");

                            if(mem_used_str != NULL && mem_free_str != NULL && swap_total_str != NULL && swap_free_str != NULL){

                                hosts[indice].mem_used = atof(mem_used_str);
                                hosts[indice].mem_free = atof(mem_free_str);
                                hosts[indice].swap_total = atof(swap_total_str);
                                hosts[indice].swap_free = atof(swap_free_str);

                            }

                        }

                    }

                    sem_post(sem); //Desbloquear sección crítica

                }   

            }

        close(client_sock);
        sem_close(sem);
        shmdt(hosts);
        exit(0);
    }

    //Else proceso padre
    else{
        close(client_sock);
        }


    }

    return 0;
}


