#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>

int main() {
    int pipefd[2];
    char buffer[5];
    pid_t pid;
    int r;

    r = pipe(pipefd);
    if (r < 0) {
        perror("error pipe");
    }
    pid = fork();

    if (pid < 0) {
        return 1;
    } else if (pid == 0) {
        close(pipefd[0]);
        r = write(pipefd[1], "hola\n", 5);
        close(pipefd[1]);
        exit(0);
    } else {
        close(pipefd[1]);
        r = read(pipefd[0], buffer, 5);
        buffer[r] = 0;
        printf("%s", buffer);
        close(pipefd[0]);
    }
    exit(0);
}
