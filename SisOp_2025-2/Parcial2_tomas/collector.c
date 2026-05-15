#include "shared.h"

#define BACKLOG 8

int parse_cpu(char *info, struct host_data *h_data) {
    char tipo[10];
    float cpu_usage, cpu_user, cpu_system, cpu_idle;

    int fields = sscanf(info, "%[^;];%[^;];%f;%f;%f;%f", tipo, h_data->ip,
                        &cpu_usage, &cpu_user, &cpu_system, &cpu_idle);

    if (fields != 6 || strcmp(tipo, "CPU") != 0) {
        // Poner valores "raros" para indicar datos inválidos
        h_data->cpu_usage = -999.0;
        h_data->cpu_user = -999.0;
        h_data->cpu_system = -999.0;
        h_data->cpu_idle = -999.0;
        return -1;
    }

    h_data->cpu_usage = cpu_usage;
    h_data->cpu_user = cpu_user;
    h_data->cpu_system = cpu_system;
    h_data->cpu_idle = cpu_idle;

    return 0;
}

int parse_mem(char *info, struct host_data *h_data) {
    char tipo[10];
    float mem_used, mem_free, swap_total, swap_free;

    int fields = sscanf(info, "%[^;];%[^;];%f;%f;%f;%f", tipo, h_data->ip,
                        &mem_used, &mem_free, &swap_total, &swap_free);

    if (fields != 6 || strcmp(tipo, "MEM") != 0) {
        // Poner valores "raros" para indicar datos inválidos
        h_data->mem_used_mb = -999.0;
        h_data->mem_free_mb = -999.0;
        h_data->swap_total_mb = -999.0;
        h_data->swap_free_mb = -999.0;
        return -1;
    }

    h_data->mem_used_mb = mem_used;
    h_data->mem_free_mb = mem_free;
    h_data->swap_total_mb = swap_total;
    h_data->swap_free_mb = swap_free;

    return 0;
}

int create_server(int puerto) {
    int fd, r;
    struct sockaddr_in server;

    fd = socket(AF_INET, SOCK_STREAM, 0);

    if (fd == -1) {
        perror("socket error");
        exit(1);
    }

    server.sin_family = AF_INET;
    server.sin_port = htons(puerto);
    server.sin_addr.s_addr = INADDR_ANY;

    memset(&(server.sin_zero), 0, 8);

    r = bind(fd, (struct sockaddr *)&server, sizeof(struct sockaddr));

    if (r == -1) {
        perror("bind error");
        close(fd);
        exit(1);
    }

    r = listen(fd, BACKLOG);

    if (r == -1) {
        perror("listen error");
        close(fd);
        exit(1);
    }

    printf("Servidor TCP escuchando en puerto %d...\n", puerto);
    return fd;
}

int find_host_by_ip(struct shared_data *data, const char *ip) {
    for (int i = 0; i < data->num_hosts; i++) {
        if (strcmp(data->hosts[i].ip, ip) == 0) {
            return i;
        }
    }
    return -1;
}

int add_host(struct shared_data *data, const char *ip) {
    int idx = data->num_hosts;
    strcpy(data->hosts[idx].ip, ip);

    data->hosts[idx].cpu_usage = -999.0;
    data->hosts[idx].cpu_user = -999.0;
    data->hosts[idx].cpu_system = -999.0;
    data->hosts[idx].cpu_idle = -999.0;
    data->hosts[idx].mem_used_mb = -999.0;
    data->hosts[idx].mem_free_mb = -999.0;
    data->hosts[idx].swap_total_mb = -999.0;
    data->hosts[idx].swap_free_mb = -999.0;

    data->num_hosts++;
    return idx;
}

void remove_host(struct shared_data *data, int idx) {
    // Mover todos los hosts siguientes una posición hacia atrás
    for (int i = idx; i < data->num_hosts - 1; i++) {
        data->hosts[i] = data->hosts[i + 1];
    }

    // Decrementar el contador
    data->num_hosts--;

    printf("Host eliminado. Total hosts: %d\n", data->num_hosts);
}

int main(int argc, char *argv[]) {
    if (argc != 2) {
        fprintf(stderr, "Uso: %s <puerto>\n", argv[0]);
        return 1;
    }

    int puerto = atoi(argv[1]);

    // crear servidor y esperar clientes
    int fd = create_server(puerto);
    int fd2, r;
    struct sockaddr_in client;
    pid_t pid;
    socklen_t size = sizeof(struct sockaddr_in);

    char buffer[100];

    // para la memoria y semáforo
    struct shared_data *ap = create_mem();
    int semId = create_sem();

    // acá ya es para manjear los clientes
    while (1) {
        fd2 = accept(fd, (struct sockaddr *)&client, &size);
        if (fd2 == -1) {
            perror("accept error");
            continue;
        }

        pid = fork();

        if (pid < 0) { // error
            perror("fork error");
            close(fd2);
        } else if (pid == 0) { // hijo
            printf("Cliente conectado\n");
            close(fd);

            int agent_type = 0; // 0=desconocido, 1=CPU, 2=MEM
            char agent_ip[32] = "";

            while (1) {
                r = recvAll(fd2, buffer, sizeof(buffer));
                if (r == -1) {
                    perror("recv error");
                } else if (r == 0) {
                    printf("Cliente %s desconectado\n", agent_ip);

                    // Marcar las métricas del agente como inválidas
                    if (agent_type != 0 && strlen(agent_ip) > 0) {
                        sem_wait(semId);
                        int idx = find_host_by_ip(ap, agent_ip);
                        if (idx != -1) {
                            if (agent_type == 1) { // CPU
                                ap->hosts[idx].cpu_usage = -999.0;
                                ap->hosts[idx].cpu_user = -999.0;
                                ap->hosts[idx].cpu_system = -999.0;
                                ap->hosts[idx].cpu_idle = -999.0;
                                printf("Métricas CPU marcadas como inválidas "
                                       "para %s\n",
                                       agent_ip);
                            } else if (agent_type == 2) { // MEM
                                ap->hosts[idx].mem_used_mb = -999.0;
                                ap->hosts[idx].mem_free_mb = -999.0;
                                ap->hosts[idx].swap_total_mb = -999.0;
                                ap->hosts[idx].swap_free_mb = -999.0;
                                printf("Métricas MEM marcadas como inválidas "
                                       "para %s\n",
                                       agent_ip);
                            }

                            // Verificar si ambos están desconectados
                            int cpu_down = (ap->hosts[idx].cpu_usage < 0);
                            int mem_down = (ap->hosts[idx].mem_used_mb < 0);

                            if (cpu_down && mem_down) {
                                remove_host(ap, idx); // Eliminar completamente
                            }
                        }
                        sem_signal(semId);
                    }

                    shmdt(ap);
                    close(fd2);
                    exit(0);
                } else {
                    buffer[r] = '\0';

                    // Parsear el mensaje para obtener la IP
                    struct host_data temp_data;
                    int tipo = -1; // 1=CPU, 2=MEM, -1=error

                    // Identificar tipo de mensaje
                    if (strncmp(buffer, "CPU", 3) == 0) {
                        if (parse_cpu(buffer, &temp_data) == 0) {
                            tipo = 1;
                        }
                    } else if (strncmp(buffer, "MEM", 3) == 0) {
                        if (parse_mem(buffer, &temp_data) == 0) {
                            tipo = 2;
                        }
                    }

                    if (tipo == -1) {
                        fprintf(stderr,
                                "Error: mensaje inválido o mal formateado\n");
                        continue;
                    }

                    // Guardar tipo de agente e IP en la primera recepción
                    // válida
                    if (agent_type == 0) {
                        agent_type = tipo;
                        strcpy(agent_ip, temp_data.ip);
                    }

                    sem_wait(semId);

                    int idx = find_host_by_ip(ap, temp_data.ip);
                    if (idx == -1) {
                        // IP no existe, intentar agregarla
                        if (ap->num_hosts >= MAX_HOSTS) {
                            fprintf(stderr,
                                    "Error: máximo de hosts alcanzado (%d). "
                                    "Rechazando IP %s\n",
                                    MAX_HOSTS, temp_data.ip);
                            sem_signal(semId);
                            continue;
                        }

                        // Hay espacio, agregar nuevo host
                        idx = add_host(ap, temp_data.ip);
                        printf("Nuevo host agregado: %s\n", temp_data.ip);
                    }

                    if (tipo == 1) {
                        ap->hosts[idx].cpu_usage = temp_data.cpu_usage;
                        ap->hosts[idx].cpu_user = temp_data.cpu_user;
                        ap->hosts[idx].cpu_system = temp_data.cpu_system;
                        ap->hosts[idx].cpu_idle = temp_data.cpu_idle;
                    } else if (tipo == 2) {
                        ap->hosts[idx].mem_used_mb = temp_data.mem_used_mb;
                        ap->hosts[idx].mem_free_mb = temp_data.mem_free_mb;
                        ap->hosts[idx].swap_total_mb = temp_data.swap_total_mb;
                        ap->hosts[idx].swap_free_mb = temp_data.swap_free_mb;
                    }

                    sem_signal(semId);
                }
            }
        } else { // padre
            close(fd2);
            continue;
        }
    }

    shmdt(ap);
    delete_mem_sem();
    close(fd);
    exit(0);
}
