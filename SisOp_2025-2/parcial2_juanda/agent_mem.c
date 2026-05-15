#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <unistd.h>
#include <sys/socket.h>
#include <netinet/in.h>
#include <arpa/inet.h>


struct mem{
    long mem_total;
    long mem_available;
    long mem_free;
    long swap_total;
    long swap_free;
};


int get_memory_info(struct mem *memory){
    FILE *file = fopen("/proc/meminfo", "r");
    if (file == NULL) {
        perror("Failed to open /proc/meminfo");
        return -1;
    }

    char line[256];
    while (fgets(line, sizeof(line), file)) {
        if(strncmp(line, "MemTotal:", 9) == 0){

            sscanf(line, " MemTotal: %ld kB", &memory->mem_total);

        } else if(strncmp(line, "MemAvailable:", 13) == 0){

            sscanf(line, " MemAvailable: %ld kB", &memory->mem_available);

        } else if(strncmp(line, "MemFree:", 8) == 0){

            sscanf(line, " MemFree: %ld kB", &memory->mem_free);

        } else if(strncmp(line, "SwapTotal:", 10) == 0){

            sscanf(line, " SwapTotal: %ld kB", &memory->swap_total);

        } else if(strncmp(line, "SwapFree:", 9) == 0){

            sscanf(line, " SwapFree: %ld kB", &memory->swap_free);
        }
    }

    fclose(file);
    return 0;

}

int main(int argc, char *argv[]){

    if(argc <4 ){
        printf("Uso: ./agent_mem <ip_recolector> <puerto> <ip_logica_agente>\n");
        printf("Ejemplo: ./agent_mem 192.168.1.100 8080 192.168.1.101\n");
        return 1;
    }

    struct mem memory_info;

    char *server_ip=argv[1];
    int server_port=atoi(argv[2]);
    char *ip_logica=argv[3];
    
    struct sockaddr_in server_addr;
    char mensaje[256];

    while (1) {

        if(get_memory_info(&memory_info) == 0){
        
        // Calculos
        double mem_used=(memory_info.mem_total - memory_info.mem_available) / 1024.0;
        double mem_free=memory_info.mem_free / 1024.0;
        double swap_total=memory_info.swap_total / 1024.0;
        double swap_free=memory_info.swap_free / 1024.0;

        int sock=socket(AF_INET, SOCK_STREAM, 0);
        if(sock<0){
            perror("Error al crear el socket");
            sleep(2);
            continue;
        }

        server_addr.sin_family=AF_INET;
        server_addr.sin_port=htons(server_port);
        if(inet_pton(AF_INET, server_ip, &server_addr.sin_addr)<=0){
            perror("Dirección IP inválida");
            close(sock);
            return 1;
        }

        if(connect(sock, (struct sockaddr *)&server_addr, sizeof(server_addr))<0){
            perror("Error en connect, intentando de nuevo en 2 segundos...");
            close(sock);
            sleep(2);
            continue;
        }


        snprintf(mensaje, sizeof(mensaje),
                 "MEM;%s;%.2f;%.2f;%.2f;%.2f",
                 ip_logica, mem_used, mem_free, swap_total, swap_free);

        if(send(sock, mensaje, strlen(mensaje), 0) < 0){
            perror("Error en send");
        }

        else{
            printf("Datos de memoria enviados al recolector.\n");
        }

        close(sock);
        
        }

        sleep(2);


    }

    return 0;


}