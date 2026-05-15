#include <stdio.h>
#include <stdlib.h>

int main(){ 
    char *nombre, *apellido, *edad, *estatura;
    int edad_int;
    double estatura_doub;
    FILE *archivo;

    nombre = malloc(32);
    apellido = malloc(32);
    edad = malloc(32);
    estatura = malloc(32);

    scanf("%s %s", nombre, apellido);
    scanf("%d %lf", &edad_int, &estatura_doub);
    
    archivo = fopen("datos.txt", "w");

    if (archivo == NULL) {
        printf("Error al abrir el archivo\n");
        return 1;
    }

    fprintf(archivo, "%s\n", nombre);
    fprintf(archivo, "%s\n", apellido);

    sprintf(edad, "%d\n", edad_int);
    fprintf(archivo, "%s\n", edad);

    sprintf(estatura, "%.2f\n", estatura_doub);
    fprintf(archivo, "%s\n", estatura);

    fclose(archivo);

    free(nombre);
    free(apellido);

    return 0;
}
