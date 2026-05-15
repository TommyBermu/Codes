#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <unistd.h>
#include <sys/ipc.h>
#include <sys/shm.h>
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
#define TIME_OUT 10 // segundos

int main(){
    
    int shm_id = shmget(SHM_KEY, sizeof(struct host_info) * MAX_HOSTS, IPC_CREAT | 0666);
    if(shm_id < 0){
        printf("Error al obtener la memoria compartida\n");
        exit(1);
    }

    struct host_info *hosts = (struct host_info *) shmat(shm_id, NULL, 0);

    sem_t *sem = sem_open(SEM_NAME, 0);
    if(sem == SEM_FAILED){
        perror("sem_open");
        exit(1);
    }

    printf("Visualizador iniciado. Mostrando información de hosts:\n");

    while(1){
        //Limipiar pantalla
        printf("\033[H\033[J");

        sem_wait(sem); //Bloquear sección crítica
        printf("=== MONITOR DE SISTEMA DISTRIBUIDO ===\n");
        printf("%-15s | %-6s | %-6s | %-6s || %-9s | %-9s | %-9s\n", 
               "IP", "CPU%", "User%", "Sys%", "MemUsed", "MemFree", "SwapUsed");
        printf("--------------------------------------------------------------------------------------\n");

        time_t current_time = time(NULL);

        for(int i=0; i<MAX_HOSTS; i++){
            if(hosts[i].updated == 1){
                double segunds_diff = difftime(current_time, hosts[i].last_update);

                if(segunds_diff <= TIME_OUT){

                float swap_used = hosts[i].swap_total - hosts[i].swap_free;
                printf("%-15s | %-5.1f | %-5.1f | %-5.1f || %6.0f MB | %6.0f MB | %6.0f MB\n", 
                       hosts[i].ip, 
                       hosts[i].cpu_usage, 
                       hosts[i].user_pct, 
                       hosts[i].sys_pct, 
                       hosts[i].mem_used, 
                       hosts[i].mem_free, 
                       swap_used);
                }
            }

            else {
                printf("%-15s %-8s %-10s %-10s %-10s %-12s %-12s\n",
                           hosts[i].ip, "--", "--", "--", "--", "--", "--");
                }
    
            }

        sem_post(sem); //Desbloquear sección crítica
        sleep(2);

        }

        return 0;
}