#include <arpa/inet.h>
#include <netinet/in.h>
#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <sys/ipc.h>
#include <sys/sem.h>
#include <sys/shm.h>
#include <sys/socket.h>
#include <sys/types.h>
#include <sys/wait.h>
#include <unistd.h>

#define MAX_HOSTS 4
#define SHM_KEY 1234 // Llave para la memoria compartida
#define SEM_KEY 5678 // Llave para el semáforo

struct host_data {
    char ip[32];
    float cpu_usage;
    float cpu_user;
    float cpu_system;
    float cpu_idle;
    float mem_used_mb;
    float mem_free_mb;
    float swap_total_mb;
    float swap_free_mb;
};

// Estructura que irá en memoria compartida
struct shared_data {
    struct host_data hosts[MAX_HOSTS];
    int num_hosts; // Cantidad de hosts activos
};

static inline int sendAll(int fd, char *buf, size_t len) {
    size_t total = 0;
    size_t bytes_left = len;
    int n;

    while (total < len) {
        n = send(fd, buf + total, bytes_left, 0);
        if (n == -1) {
            return -1;
        }
        total += n;
        bytes_left -= n;
    }
    return total;
}

static inline int recvAll(int fd, char *buf, size_t len) {
    size_t total = 0;
    size_t bytes_left = len;
    int n;

    while (total < len) {
        n = recv(fd, buf + total, bytes_left, 0);
        if (n == -1) {
            return -1;
        }
        if (n == 0) {
            return 0;
        }
        total += n;
        bytes_left -= n;
    }
    return total;
}

static inline struct shared_data *create_mem() {
    int shmId;
    struct shared_data *ap;

    shmId = shmget(SHM_KEY, sizeof(struct shared_data), IPC_CREAT | 0666);

    if (shmId < 0) {
        perror("error al crear shm");
        exit(1);
    }

    ap = (struct shared_data *)shmat(shmId, 0, 0);
    if (ap == (void *)-1) {
        perror("error al adjuntar shm");
        exit(1);
    }

    ap->num_hosts = 0;
    for (int i = 0; i < MAX_HOSTS; i++) {
        strcpy(ap->hosts[i].ip, "");
        ap->hosts[i].cpu_usage = -999.0;
        ap->hosts[i].cpu_user = -999.0;
        ap->hosts[i].cpu_system = -999.0;
        ap->hosts[i].cpu_idle = -999.0;
        ap->hosts[i].mem_used_mb = -999.0;
        ap->hosts[i].mem_free_mb = -999.0;
        ap->hosts[i].swap_total_mb = -999.0;
        ap->hosts[i].swap_free_mb = -999.0;
    }

    return ap;
}

static inline void delete_mem_sem() {
    int shmId = shmget(SHM_KEY, sizeof(struct shared_data), IPC_CREAT | 0666);
    shmctl(shmId, IPC_RMID, NULL);

    // elimina los semaforos
    int semId = semget(SEM_KEY, 1, 0666);
    if (semId != -1) {
        semctl(semId, 0, IPC_RMID);
    }
}

static inline int create_sem() {
    int semId = semget(SEM_KEY, 1, IPC_CREAT | 0666);
    if (semId == -1) {
        perror("semget error");
        exit(1);
    }
    if (semctl(semId, 0, SETVAL, 1) == -1) {
        perror("semctl SETVAL error");
        exit(1);
    }
    return semId;
}

// Definir las operaciones
static inline void sem_wait(int semid) {
    struct sembuf wait_op = {0, -1, 0};

    if (semop(semid, &wait_op, 1) == -1) {
        perror("semop wait error");
    }
}

static inline void sem_signal(int semid) {
    struct sembuf signal_op = {0, 1, 0};

    if (semop(semid, &signal_op, 1) == -1) {
        perror("semop signal error");
    }
}
