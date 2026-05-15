#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <sys/shm.h>
#include <sys/types.h>
#include <sys/wait.h>
#include <unistd.h>

struct manito {
    float flotante;
    long largo;
    char palabra[16];
};

int main() {
    key_t key = 12345;
    int shmId;
    struct manito *ap;
    pid_t pid;

    shmId = shmget(key, sizeof(struct manito), IPC_CREAT | 0666);

    if (shmId < 0) {
        perror("error al crear shm");
        exit(1);
    }

    ap = (struct manito *)shmat(shmId, 0, 0);
    if (ap == (void *)-1) {
        perror("error al adjuntar shm");
        exit(1);
    }

    pid = fork();
    if (pid == 0) {
        ap->flotante = 3.4;
        ap->largo = 12343412341;
        strcpy(ap->palabra, "test\n");
        shmdt(ap);
        exit(0);
    } else {
        wait(NULL);
        printf("estructura: %f, %ld, %s\n", ap->flotante, ap->largo,
               ap->palabra);
        shmdt(ap);
        shmctl(shmId, IPC_RMID, NULL);
    }

    return 0;
}
