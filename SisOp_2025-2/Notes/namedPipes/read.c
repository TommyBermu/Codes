#include <stdio.h>
#include <fcntl.h>
#include <unistd.h>

int main() {
    char buffer[100];
    int fd = open("/tmp/mi_fifo", O_RDONLY);
    read(fd, buffer, sizeof(buffer));
    printf("Mensaje recibido: %s\n", buffer);
    close(fd);
    return 0;
}