#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <unistd.h>
#include <netinet/in.h>
#include <sys/socket.h>
#include <arpa/inet.h>

#define PORT 3535
#define BACKLOG 4

int main () {
    int fd, fd2, r;
    struct sockaddr_in server, client;
    socklen_t size;

    char buffer[20];

    fd = socket(AF_INET, SOCK_STREAM, 0);
   
    if (fd == -1) {
        perror("error al crear el socket");
        exit(1);
    }  

    server.sin_family = AF_INET;
    server.sin_port = htons(PORT);
    server.sin_addr.s_addr = INADDR_ANY;

    memset(&(server.sin_zero), 0, 8);

    r = bind(fd, (struct sockaddr*)&server, sizeof(struct sockaddr));
    if (r == -1) {
        perror("bind error");
        close(fd);
        exit(1);
    }
    
    r = listen(fd, BACKLOG);
    if (r == -1) {
        perror("listen");
        close(fd);
        exit(1);
    }
    printf("Servidor TCP escuchando en puerto %d...\n", PORT);

    size = sizeof(struct sockaddr_in);

    fd2 = accept(fd, (struct sockaddr*)&client, &size);
     if (fd2 == -1) {
        perror("accept");
        close(fd);
        exit(1);
    }
    printf("Cliente conectado desde %s:%d\n",
           inet_ntoa(client.sin_addr), ntohs(client.sin_port));

    r = send(fd2, "hola cliente", 12, 0);
    if (r == -1) {
        perror("send");
    }

    r = recv(fd2, buffer, 20, 0);
    if (r > 0) {
        buffer[r] = '\0'; 
        printf("Cliente dice: %s\n", buffer);
    } else if (r == 0) {
        printf("Cliente desconectado.\n");
    } else {
        perror("recv");
    }
    close(fd2);
    close(fd);

    return 0;
}
