#include <arpa/inet.h>
#include <netinet/in.h>
#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <sys/socket.h>
#include <unistd.h>

#define PORT 3535

int main() {
    int fd, fd2, r;
    struct sockaddr_in client;
    socklen_t size;

    char buffer[20];

    fd = socket(AF_INET, SOCK_STREAM, 0);

    if (fd == -1) {
        perror("error al crear el socket");
        exit(1);
    }

    client.sin_family = AF_INET;
    client.sin_port = htons(PORT);
    // client.sin_addr.s_addr = inet_addr("10.203.141.129"); // funciona normal
    // xd
    client.sin_addr.s_addr = inet_addr("127.0.0.1"); // ip del servidor :D

    memset(&(client.sin_zero), 0, 8);

    r = connect(fd, (struct sockaddr *)&client, sizeof(struct sockaddr_in));
    if (r == -1) {
        perror("error al hacer la conexion");
        exit(1);
    }

    r = recv(fd, buffer, 20, 0);
    if (r > 0) {
        buffer[r] = '\0';
        printf("Servidor dice: %s\n", buffer);
    } else if (r == 0) {
        printf("Cliente desconectado.\n");
    } else {
        perror("recv");
    }

    r = send(fd, "hola servidor", 13, 0);

    close(fd);

    return 0;
}
