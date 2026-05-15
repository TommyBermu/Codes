#include <stdbool.h>
#include <stdio.h>
#include <sys/shm.h>
#include <sys/wait.h>
#include <unistd.h>

bool esPrimo(int n) {
    if (n < 2)
        return false;
    if (n == 2)
        return true;
    if (n % 2 == 0)
        return false;

    for (int i = 3; i * i <= n; i += 2) {
        if (n % i == 0)
            return false;
    }

    return true;
}

int main() {
    key_t key = 12345;
    int shmId, cantidad, inicio, fin, status;
    int *ap;
    int count = 0;

    shmId = shmget(key, sizeof(int) * 2, 0666 | IPC_CREAT);
    if (shmId < 0) {
        perror("error al crear la memoria");
        return 1;
    }
    ap = shmat(shmId, 0, 0);

    scanf("%d %d", &inicio, &fin);

    int mitad_rango = inicio + (fin - inicio) / 2;

    pid_t pid = fork();

    if (pid == 0) { // mitad inferior
        for (int i = inicio; i <= mitad_rango; i++) {
            if (esPrimo(i))
                count++;
        }
        *ap = count;

    } else {
        for (int i = mitad_rango + 1; i <= fin; i++) {
            if (esPrimo(i))
                count++;
        }
        wait(&status);

        count += *ap;

        printf("Cantidad de primos: %i\n", count);
    }
}
