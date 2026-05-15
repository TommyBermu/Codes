#include <stdio.h>
#include <sys/types.h>
#include <sys/stat.h>
#include <fcntl.h>
#include <unistd.h>


int main() {
    const char *fifo_name = "/tmp/mi_fifo";

    // Crear la FIFO con permisos rw-r--r--
    if (mkfifo(fifo_name, 0644) == -1) {
        perror("Error al crear la FIFO");
        return 1;
    }

    printf("FIFO creada en: %s\n", fifo_name);
    
    int fd = open("/tmp/mi_fifo", O_WRONLY);
    write(fd, "hola proceso lector\n", 21);
    close(fd);
    return 0;
}