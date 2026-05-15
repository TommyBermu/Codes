#include "shared.h"

struct mem_stats {
    unsigned long mem_total;
    unsigned long mem_available;
    unsigned long mem_free;
    unsigned long swap_total;
    unsigned long swap_free;
};

void get_info(struct mem_stats *info) {
    FILE *f = fopen("/proc/meminfo", "r");
    if (!f) {
        perror("fopen error");
        return;
    }

    char line[256];

    // Inicializar en 0
    info->mem_total = 0;
    info->mem_available = 0;
    info->mem_free = 0;
    info->swap_total = 0;
    info->swap_free = 0;

    // Leer línea por línea
    while (fgets(line, sizeof(line), f)) {
        if (sscanf(line, "MemTotal: %lu kB", &info->mem_total) == 1)
            continue;
        if (sscanf(line, "MemAvailable: %lu kB", &info->mem_available) == 1)
            continue;
        if (sscanf(line, "MemFree: %lu kB", &info->mem_free) == 1)
            continue;
        if (sscanf(line, "SwapTotal: %lu kB", &info->swap_total) == 1)
            continue;
        if (sscanf(line, "SwapFree: %lu kB", &info->swap_free) == 1)
            continue;
    }

    fclose(f);
}

void calculate_usage(struct mem_stats *info, double *mem_used_mb,
                     double *mem_free_mb, double *swap_total_mb,
                     double *swap_free_mb) {
    // Calcular memoria usada en MB
    // mem_used = MemTotal - MemAvailable
    unsigned long mem_used_kb = info->mem_total - info->mem_available;

    // Convertir de KB a MB (dividir por 1024)
    *mem_used_mb = mem_used_kb / 1024.0;
    *mem_free_mb = info->mem_free / 1024.0;
    *swap_total_mb = info->swap_total / 1024.0;
    *swap_free_mb = info->swap_free / 1024.0;
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

    // Conectar al recolector
    r = connect(fd, (struct sockaddr *)&client, sizeof(struct sockaddr_in));
    if (r == -1) {
        perror("connection error");
        exit(1);
    }

    printf("Conectado al recolector %s:%d\n", ip_recolector, puerto);
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

    struct mem_stats info;
    int r;

    // para la conexión
    int fd = conectar(puerto, ip_recolector);

    // Loop principal
    while (1) {
        // Obtener información de memoria
        get_info(&info);

        // Calcular valores en MB
        double mem_used_mb, mem_free_mb, swap_total_mb, swap_free_mb;
        calculate_usage(&info, &mem_used_mb, &mem_free_mb, &swap_total_mb,
                        &swap_free_mb);

        // MEM;<ip_logica_agente>;<mem_used_MB>;<MemFree_MB>;<SwapTotal_MB>;<SwapFree_MB>\n
        char buffer[100];
        snprintf(buffer, sizeof(buffer), "MEM;%s;%.0f;%.0f;%.0f;%.0f\n",
                 ip_logica, mem_used_mb, mem_free_mb, swap_total_mb,
                 swap_free_mb);

        // Enviar datos
        r = sendAll(fd, buffer, sizeof(buffer));
        if (r == -1) {
            perror("send error");
            break;
        }

        sleep(2);
    }

    close(fd);
    exit(0);
}
