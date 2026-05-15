#include "nodeAndHastTable.h"
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

// Función de búsqueda
struct Song searchSong(const char *artistName, const char *songName) {
    struct Song song;
    memset(&song, 0, sizeof(song));

    struct hashTable *table =
        (struct hashTable *)malloc(sizeof(struct hashTable));
    if (!table) {
        perror("Error al asignar memoria para tabla hash");
        strcpy(song.artist, "ERROR");
        return song;
    }

    FILE *fHash = fopen("hashTableSongs.bin", "rb");
    if (!fHash) {
        perror("Error al abrir la tabla hash");
        strcpy(song.artist, "NA");
        strcpy(song.name, "Error al abrir tabla hash");
        free(table);
        return song;
    }

    fread(table, sizeof(struct hashTable), 1, fHash);
    fclose(fHash);

    struct node head = search(table, artistName);
    if (head.offset == -1) {
        strcpy(song.artist, "NA");
        strcpy(song.name, "No hay coincidencias de canciones con este autor");
        free(table);
        return song;
    }

    FILE *fSongs = fopen("songs.bin", "rb");
    if (!fSongs) {
        perror("Error al abrir el binario de las canciones");
        strcpy(song.artist, "NA");
        free(table);
        return song;
    }

    long offset = head.offset;
    int flag = 0;

    while (offset != -1) {
        fseek(fSongs, offset, SEEK_SET);
        fread(&song, sizeof(song), 1, fSongs);

        if (strcmp(artistName, song.artist) == 0 &&
            strcmp(songName, song.name) == 0) {
            fclose(fSongs);
            free(table);
            return song;
        }

        if (flag == 0) {
            offset = head.nextOffset;
            flag = 1;
        } else {
            offset = song.nextOffset;
        }
    }

    strcpy(song.artist, "NA");
    strcpy(
        song.name,
        "No hay coincidencia de alguna canción con este autor y este nombre");
    fclose(fSongs);
    free(table);
    return song;
}

int main() {
    printf("Iniciando proceso de busqueda\n");

    // Crear memoria compartida
    int shm_fd = shm_open(SHM_NAME, O_CREAT | O_RDWR, 0666);
    if (shm_fd == -1) {
        perror("Error al crear memoria compartida");
        return 1;
    }

    ftruncate(shm_fd, sizeof(struct SharedMemory));

    struct SharedMemory *shm = (struct SharedMemory *)mmap(
        NULL, sizeof(struct SharedMemory), PROT_READ | PROT_WRITE, MAP_SHARED,
        shm_fd, 0);

    if (shm == MAP_FAILED) {
        perror("Error al mapear memoria compartida");
        return 1;
    }

    // Crear semáforos
    sem_t *sem_req = sem_open(SEM_REQUEST, O_CREAT, 0666, 0);
    sem_t *sem_res = sem_open(SEM_RESPONSE, O_CREAT, 0666, 0);

    if (sem_req == SEM_FAILED || sem_res == SEM_FAILED) {
        perror("Error al crear semáforos");
        return 1;
    }

    // Bucle principal del servidor
    while (1) {
        // Esperar solicitud
        sem_wait(sem_req);

        if (shm->request.shouldExit) {
            printf("saliendo\n");
            break;
        }

        printf("buscando: '%s' - '%s'\n", shm->request.artist,
               shm->request.songName);

        // Realizar búsqueda
        struct Song result =
            searchSong(shm->request.artist, shm->request.songName);

        shm->response.song = result;
        shm->response.found = (strcmp(result.artist, "NA") != 0);

        // Enviar respuesta
        sem_post(sem_res);
    }

    // Limpieza
    munmap(shm, sizeof(struct SharedMemory));
    close(shm_fd);
    shm_unlink(SHM_NAME);
    sem_close(sem_req);
    sem_close(sem_res);
    sem_unlink(SEM_REQUEST);
    sem_unlink(SEM_RESPONSE);

    printf("proceso de busqueda terminado\n");
    return 0;
}
