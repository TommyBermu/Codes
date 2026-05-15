
#include <arpa/inet.h>
#include <netinet/in.h>
#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <sys/socket.h>
#include <unistd.h>

#define PORT 3535

int sendAll(int fd, void *buf, size_t len) {
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

int main() {
    int fd, r;
    struct sockaddr_in client;
    socklen_t size;
    char buffer[10];

    fd = socket(AF_INET, SOCK_STREAM, 0);
    if (fd == -1) {
        perror("socket error");
        exit(1);
    }

    client.sin_family = AF_INET;
    client.sin_port = htons(PORT);
    client.sin_addr.s_addr = inet_addr("127.0.0.1");

    memset(&(client.sin_zero), 0, 8);

    r = connect(fd, (struct sockaddr *)&client, sizeof(struct sockaddr_in));

    if (r == -1) {
        perror("conection error");
        exit(1);
    }

    printf("conectado\n");

    while (1) {
        int option;
        printf("(1) hablar con el servidor\n(2) cerrar conexión\n");

        if (scanf("%d", &option) != 1) {
            printf("Opcion invalida\n");
            while (getchar() != '\n')
                ;
            continue;
        }
        while (getchar() != '\n')
            ;

        switch (option) {
        case 1:
            printf("que le quieres decir al servidor?\n");

            fgets(buffer, sizeof(buffer), stdin);
            buffer[strcspn(buffer, "\n")] = '\0';

            r = sendAll(fd, buffer, sizeof(buffer));

            if (r == -1) {
                perror("send error");
            }
            break;
        case 2:
            printf("cerrando cliente...\n");
            exit(0);
            break;

        default:
            printf("seleccione una opción valida\n");
            break;
        }
    }
    close(fd);
    exit(0);
}
