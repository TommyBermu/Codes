#include "shared.h"

struct cpu_stats {
    unsigned long user;
    unsigned long nice;
    unsigned long system;
    unsigned long idle;
    unsigned long iowait;
    unsigned long irq;
    unsigned long softirq;
    unsigned long steal;
};

void get_info(struct cpu_stats *info) {
    FILE *f = fopen("/proc/stat", "r");
    if (!f) {
        perror("fopen error");
    }

    char cpu_label[5];

    if (fscanf(f, "%4s %lu %lu %lu %lu %lu %lu %lu %lu", cpu_label, &info->user,
               &info->nice, &info->system, &info->idle, &info->iowait,
               &info->irq, &info->softirq, &info->steal) != 9) {
        fprintf(stderr, "No se pudo leer la línea de cpu\n");
    }
    fclose(f);
}

void calculate_usage(struct cpu_stats *prev, struct cpu_stats *curr,
                     double *cpu_usage, double *user_pct, double *system_pct,
                     double *idle_pct) {
    // se calculan los deltas
    unsigned long delta_user = curr->user - prev->user;
    unsigned long delta_nice = curr->nice - prev->nice;
    unsigned long delta_system = curr->system - prev->system;
    unsigned long delta_idle = curr->idle - prev->idle;
    unsigned long delta_iowait = curr->iowait - prev->iowait;
    unsigned long delta_irq = curr->irq - prev->irq;
    unsigned long delta_softirq = curr->softirq - prev->softirq;
    unsigned long delta_steal = curr->steal - prev->steal;

    unsigned long cpu_total = delta_user + delta_nice + delta_system +
                              delta_idle + delta_iowait + delta_irq +
                              delta_softirq + delta_steal;

    if (cpu_total == 0) { // para evitar la división por 0 xd
        *cpu_usage = 0.0;
        *user_pct = 0.0;
        *system_pct = 0.0;
        *idle_pct = 0.0;
        return;
    }

    // CPU_usage = 100 * (CPU_total - CPU_idle) / CPU_total
    *cpu_usage = 100.0 * (cpu_total - delta_idle) / cpu_total;
    *user_pct = 100.0 * delta_user / cpu_total;
    *system_pct = 100.0 * delta_system / cpu_total;
    *idle_pct = 100.0 * delta_idle / cpu_total;
}

int conectar(int puerto, char *ip_recolector) {
    int fd, r;
    struct sockaddr_in client;

    fd = socket(AF_INET, SOCK_STREAM, 0);
    if (fd == -1) {
        perror("socket error");
        exit(1);
    }

    client.sin_family = AF_INET;
    client.sin_port = htons(puerto);
    client.sin_addr.s_addr = inet_addr(ip_recolector);

    memset(&(client.sin_zero), 0, 8);

    r = connect(fd, (struct sockaddr *)&client, sizeof(struct sockaddr_in));

    if (r == -1) {
        perror("conection error");
        exit(1);
    }

    printf("conectado\n");
    return fd;
}

int main(int argc, char *argv[]) {
    // Validar argumentos
    if (argc != 4) {
        fprintf(stderr, "Uso: %s <ip_recolector> <puerto> <ip_logica_agente>\n",
                argv[0]);
        exit(1);
    }

    // Extraer argumentos
    char *ip_recolector = argv[1];
    int puerto = atoi(argv[2]);
    char *ip_logica = argv[3];

    struct cpu_stats curr_info;
    int r;

    // para la conexión
    int fd = conectar(puerto, ip_recolector);

    // se calcula una vez antes de empezar a enviar
    get_info(&curr_info);
    struct cpu_stats prev_info;

    while (1) {
        prev_info = curr_info;
        get_info(&curr_info);

        double usage, user_pct, system_pct, idle_pct;
        calculate_usage(&prev_info, &curr_info, &usage, &user_pct, &system_pct,
                        &idle_pct);

        char buffer[100];
        snprintf(buffer, sizeof(buffer), "CPU;%s;%.1f;%.1f;%.1f;%.1f\n",
                 ip_logica, usage, user_pct, system_pct, idle_pct);

        r = sendAll(fd, buffer, sizeof(buffer));

        if (r == -1) {
            perror("send error");
        }

        sleep(2);
    }
    close(fd);
    exit(0);
}
