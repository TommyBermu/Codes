#include <stdio.h>
#include <stdlib.h>

int main() {
    FILE *archivo;
    char linea[32];

    archivo = fopen("datos.txt", "r");

    if (archivo == NULL) {
        printf("no se pudo abrir el archivo");
        return 1;
    }

    while (fgets(linea, sizeof(linea), archivo)){
        printf("%s", linea);
    }

    fclose(archivo);
    return 0;
}
