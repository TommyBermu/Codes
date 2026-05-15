#include <stdio.h>
#include <stdlib.h>
#include <sys/shm.h>
#include <sys/types.h>
#include <sys/wait.h>
#include <unistd.h>

int main() {
    key_t key = 12345;
    int shmId;
    double *ap;
    pid_t pid;
    int status;

    shmId = shmget(key, sizeof(double), 0666 | IPC_CREAT);

    if (shmId < 0) {
        perror("error al crear shm...");
        exit(-1);
    }
    ap = shmat(shmId, 0, 0);

    pid = fork();
    if (pid == 0) {
        *ap = 3.14159;
    } else {
        wait(&status);
        printf("pi: %f\n", *ap);
    }

    return 0;
}
