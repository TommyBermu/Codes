#include "shared.h"

int is_host_disconnected(struct host_data *host) {
    return (host->cpu_usage < 0 && host->cpu_user < 0 && host->cpu_system < 0 &&
            host->cpu_idle < 0 && host->mem_used_mb < 0 &&
            host->mem_free_mb < 0 && host->swap_total_mb < 0 &&
            host->swap_free_mb < 0);
}

int main() {
    int shmId = shmget(SHM_KEY, sizeof(struct shared_data), 0666);
    if (shmId == -1) {
        perror("shmget error - ¿El collector está corriendo?");
        exit(1);
    }

    struct shared_data *data = (struct shared_data *)shmat(shmId, NULL, 0);
    if (data == (void *)-1) {
        perror("shmat error");
        exit(1);
    }

    int semId = semget(SEM_KEY, 1, 0666);
    if (semId == -1) {
        perror("semget error - ¿El collector está corriendo?");
        exit(1);
    }

    while (1) {
        // Limpiar pantalla
        printf("\033[2J\033[H");

        // SECCIÓN CRÍTICA
        sem_wait(semId);

        // Mostrar encabezado
        printf("IP\t\tCPU%%\tCPU_user%%\tCPU_sys%%\tCPU_idle%%\tMem_used_"
               "MB\tMem_free_MB\tSwap_total_MB\tSwap_free_MB\n");

        // Mostrar datos de cada host
        for (int i = 0; i < data->num_hosts; i++) {
            if (is_host_disconnected(&data->hosts[i])) {
                continue;
            }
            printf("%s\t", data->hosts[i].ip);

            // CPU
            if (data->hosts[i].cpu_usage < 0) {
                printf("--\t--\t\t--\t\t--\t\t");
            } else {
                printf("%.1f\t%.1f\t\t%.1f\t\t%.1f\t\t",
                       data->hosts[i].cpu_usage, data->hosts[i].cpu_user,
                       data->hosts[i].cpu_system, data->hosts[i].cpu_idle);
            }

            // Memoria
            if (data->hosts[i].mem_used_mb < 0) {
                printf("--\t\t--\t\t--\t\t--\n");
            } else {
                printf("%.0f\t\t%.0f\t\t%.0f\t\t%.0f\n",
                       data->hosts[i].mem_used_mb, data->hosts[i].mem_free_mb,
                       data->hosts[i].swap_total_mb,
                       data->hosts[i].swap_free_mb);
            }
        }

        sem_signal(semId);

        sleep(2);
    }

    // 4. LIMPIEZA
    shmdt(data);

    return 0;
}
