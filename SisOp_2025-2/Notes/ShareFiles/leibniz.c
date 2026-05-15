#include <stdio.h>
#include <stdlib.h>
#include <sys/wait.h>
#include <unistd.h>

int main() {
    long cantidad = 50000000000;
    double acc = 0;
    FILE *file;
    double opened;
    pid_t pid = fork();

    if (pid < 0) {
        return 1;
    } else if (pid == 0) {
        for (long i = 1; i < cantidad; i++) {
            acc -= 4.0 / (4.0 * i - 1.0);
        }
        printf("negativos: %.10f\n", acc);
        file = fopen("neg", "wb");
        fwrite(&acc, sizeof(acc), 1, file);
        fclose(file);
    } else {
        for (int i = 0; i < cantidad; i++) {
            acc += 4.0 / (4.0 * i + 1.0);
        }
        printf("positivos: %.10f\n", acc);
        wait(NULL);
        file = fopen("neg", "rb");
        fread(&opened, sizeof(double), 1, file);
        acc += opened;
        printf("pi: %.10f\n", acc);
    }
    exit(0);
}
