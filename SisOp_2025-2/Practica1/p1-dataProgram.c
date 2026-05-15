#include "songStruct.h"
#include <fcntl.h>
#include <semaphore.h>
#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <sys/mman.h>
#include <sys/stat.h>
#include <unistd.h>

#define SHM_NAME "/song_search_shm"
#define SEM_REQUEST "/sem_request"
#define SEM_RESPONSE "/sem_response"

// Estructura para comunicación entre procesos
struct SearchRequest {
    char artist[ARTIST_MAX];
    char songName[NAME];
    int shouldExit;
};

struct SearchResponse {
    struct Song song;
    int found;
};

struct SharedMemory {
    struct SearchRequest request;
    struct SearchResponse response;
};

void clearInputBuffer() {
    int c;
    while ((c = getchar()) != '\n' && c != EOF)
        ;
}

void printMenu() {
    printf("\n");
    printf("Bienvenido\n");
    printf("1. Ingresar artista\n");
    printf("2. Ingresar canción\n");
    printf("3. Buscar\n");
    printf("4. Salir\n");
    printf("Opcion: ");
}

void printSongInfo(struct Song *song) {
    printf("\n");
    printf("Artista: %s\n", song->artist);
    printf("Cancion: %s\n", song->name);
    printf("Album: %s\n", song->album);
    printf("Genero: %s\n", song->genre);
    printf("Fecha: %s\n", song->date);
    printf("Duracion: %s\n", song->length);
    printf("Emocion: %s\n", song->emotion);
    printf("\nLetra:\n%s\n", song->text);
}

int main() {
    printf("\nConectando con el motor de busqueda\n");

    // Abrir memoria compartida
    int shm_fd = shm_open(SHM_NAME, O_RDWR, 0666);
    if (shm_fd == -1) {
        perror("Error: No se puede conectar con el motor de busqueda");
        return 1;
    }

    struct SharedMemory *shm = (struct SharedMemory *)mmap(
        NULL, sizeof(struct SharedMemory), PROT_READ | PROT_WRITE, MAP_SHARED,
        shm_fd, 0);

    if (shm == MAP_FAILED) {
        perror("Error al mapear memoria compartida");
        return 1;
    }

    // Abrir semáforos
    sem_t *sem_req = sem_open(SEM_REQUEST, 0);
    sem_t *sem_res = sem_open(SEM_RESPONSE, 0);

    if (sem_req == SEM_FAILED || sem_res == SEM_FAILED) {
        perror("Error al abrir semáforos");
        return 1;
    }

    printf("conectado\n");

    // Asignar memoria dinámica para buffers de entrada
    char *artist = (char *)malloc(ARTIST_MAX * sizeof(char));
    char *songName = (char *)malloc(NAME * sizeof(char));

    if (!artist || !songName) {
        perror("Error al asignar memoria");
        return 1;
    }

    int option;

    while (1) {
        printMenu();

        if (scanf("%d", &option) != 1) {
            clearInputBuffer();
            printf("\nOpcion invalida.\n");
            continue;
        }
        clearInputBuffer();

        switch (option) {
        case 1:
            printf("\nNombre del artista: ");
            fgets(artist, ARTIST_MAX, stdin);
            artist[strcspn(artist, "\n")] = '\0';
            break;
        case 2:
            printf("Nombre de la cancion: ");
            fgets(songName, NAME, stdin);
            songName[strcspn(songName, "\n")] = '\0';
            break;
        case 3:
            printf("\nBuscando...\n");

            // Preparar solicitud
            strncpy(shm->request.artist, artist, ARTIST_MAX);
            strncpy(shm->request.songName, songName, NAME);
            shm->request.shouldExit = 0;

            // Enviar solicitud
            sem_post(sem_req);

            // Esperar respuesta
            sem_wait(sem_res);

            // Procesar respuesta
            if (shm->response.found) {
                printSongInfo(&shm->response.song);
            } else {
                printf("\nNA\n");
            }
            break;

        case 4:
            printf("\nCerrando...\n");

            // Enviar señal de salida al servidor
            shm->request.shouldExit = 1;
            sem_post(sem_req);

            // Liberar memoria dinámica
            free(artist);
            free(songName);

            // Limpiar recursos
            munmap(shm, sizeof(struct SharedMemory));
            close(shm_fd);
            sem_close(sem_req);
            sem_close(sem_res);

            return 0;

        default:
            printf("\nOpcion invalida.\n");
        }
    }

    return 0;
}
