#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>

int main () {
    int pipefd[2];
    pid_t pid;
    int r;

    long cantidad = 50000000;
    double acc = 0;
    double buffer;

    r = pipe(pipefd);
    if (r < 0) {
        perror("error pipe");
    }
    pid = fork();
    
    if (pid < 0) {
        return 1;
    } else if (pid == 0) {
        close(pipefd[0]); 

        for (long i = 1; i < cantidad; i++) {
            acc -= 4.0/(4.0*i - 1.0);
        }
        printf("negativos: %.10f\n", acc); 
        r = write(pipefd[1], &acc, sizeof(double));
        close(pipefd[1]);
        exit(0);
    } else {
        close(pipefd[1]);
         
        for (long i = 0; i < cantidad; i++){
            acc += 4.0/(4.0*i + 1.0);  
        }

        printf("positivos: %.10f\n", acc); 
        r = read(pipefd[0], &buffer, sizeof(double));

        acc += buffer;

        printf("pi: %.10f\n", acc);
        close(pipefd[0]);
    }
    exit(0);
}
