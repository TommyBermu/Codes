#include <arpa/inet.h>
#include <netinet/in.h>
#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <sys/socket.h>
#include <unistd.h>

#define PORT 3535
#define BACKLOG 6

int recvAll(int fd, void *buf, size_t len) {
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

int main() {
    int fd, fd2, r;
    struct sockaddr_in server, client;
    socklen_t size;
    pid_t pid;

    char buffer[10];

    fd = socket(AF_INET, SOCK_STREAM, 0);

    if (fd == -1) {
        perror("socket error");
        exit(1);
    }

    server.sin_family = AF_INET;
    server.sin_port = htons(PORT);
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

    printf("Servidor TCP escuchando en puerto %d...\n", PORT);
    size = sizeof(struct sockaddr_in);
    while (1) {
        fd2 = accept(fd, (struct sockaddr *)&client, &size);
        if (fd2 == -1) {
            perror("accept error");
            close(fd);
            exit(1);
        }

        pid = fork();

        if (pid < 0) { // error
            close(fd2);
        } else if (pid == 0) { // hijo
            printf("Cliente conectado desde %s:%d\n",
                   inet_ntoa(client.sin_addr), ntohs(client.sin_port));
            close(fd);
            while (1) {
                r = recvAll(fd2, buffer, sizeof(buffer));
                if (r == -1) {
                    perror("recv error");
                } else if (r == 0) {
                    printf("Cliente %s:%d desconectado\n",
                           inet_ntoa(client.sin_addr), ntohs(client.sin_port));
                    close(fd2);
                    exit(0);
                } else {
                    buffer[r] = '\0';
                    printf("cliente %s:%d dice: %s\n",
                           inet_ntoa(client.sin_addr), ntohs(client.sin_port),
                           buffer);
                }
            }
        } else { // padre
            close(fd2);
            continue;
        }
    }
    close(fd);
    exit(0);
}
