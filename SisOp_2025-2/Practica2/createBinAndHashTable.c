#include "nodeAndHastTable.h"
#include "songStruct.h"
#include <stdbool.h>
#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <unistd.h>

void cleanQuotes(char *str) {
    size_t len = strlen(str);

    if (len >= 2 && str[0] == '"' && str[len - 1] == '"') {
        memmove(str, str + 1, len - 2);
        str[len - 2] = '\0';
    }
}

void lineToSong(char *line, struct Song *song) {

    char *token = strtok(line, "|");
    int field = 0;

    while (token) {

        cleanQuotes(token);

        switch (field) {
        case 0:
            strncpy(song->artist, token, ARTIST_MAX);
            break;
        case 1:
            strncpy(song->name, token, NAME);
            break;
        case 2:
            strncpy(song->text, token, TEXT);
            break;
        case 3:
            strncpy(song->length, token, ARTIST_MAX);
            break;
        case 4:
            strncpy(song->emotion, token, ARTIST_MAX);
            break;
        case 5:
            strncpy(song->genre, token, ARTIST_MAX);
            break;
        case 6:
            strncpy(song->album, token, NAME);
            break;
        case 7:
            strncpy(song->date, token, NAME);
            break;
        }

        field++;
        token = strtok(NULL, "|");
    }
}

struct hashTable writeHashTable(const char *songFile) {

    FILE *fSongs = fopen(songFile, "r+b");
    if (!fSongs) {
        perror("Error al abrir el archivo de canciones");

        struct hashTable table;
        initializeHashTable(&table);
        return table;
    }

    struct hashTable table;
    initializeHashTable(&table);

    struct Song song;

    while (fread(&song, sizeof(struct Song), 1, fSongs) == 1) {

        long originalOffset = ftell(fSongs) - sizeof(struct Song);
        printf("Procesando: %s | %s (offset: %ld)\n", song.artist, song.name,
               originalOffset);

        struct node existingNode = search(&table, song.artist);

        if (existingNode.offset == -1) {
            // No hay colisión, insertar directamente
            printf("  No hay colision\n");
            insert(&table, song.artist, originalOffset);
        } else {
            if (existingNode.nextOffset == -1) {
                // Solo hay una canción con ese hash
                printf("  Existe una colision\n");
                insert(&table, song.artist, originalOffset);
            } else {
                // Hay múltiples canciones con ese hash
                printf("  Existen multiples colisiones\n");

                // Leer la canción actual que es la cabeza
                struct Song currentSong;
                fseek(fSongs, existingNode.offset, SEEK_SET);
                fread(&currentSong, sizeof(struct Song), 1, fSongs);

                // Actualizar el nextOffset de la canción actual
                currentSong.nextOffset = existingNode.nextOffset;

                // Escribir la canción actualizada
                fseek(fSongs, existingNode.offset, SEEK_SET);
                fwrite(&currentSong, sizeof(struct Song), 1, fSongs);
                fflush(fSongs);

                // Insertar el nuevo nodo como cabeza
                insert(&table, song.artist, originalOffset);

                // Volver a posicionar el puntero después de la canción actual
                fseek(fSongs, originalOffset + sizeof(struct Song), SEEK_SET);
            }
        }
    }

    fclose(fSongs);
    return table;
}

int main() {
    FILE *fcsv = fopen("spotify_para_c.csv", "r");
    if (!fcsv) {
        perror("Error al abrir el dataset");
        return 1;
    }

    // Binario en donde vamos a escribir las estructuras
    FILE *fbin = fopen("songs.bin", "wb");
    if (!fbin) {
        perror("Error al crear el archivo binario");
        return 1;
    }

    char buffer[15000]; // Para poder leer una linea (No estoy seguro del tamaño
                        // xd)

    struct Song song; // La estructura que creamos en el otro archivo

    fgets(buffer, sizeof(buffer), fcsv); // Leer la primera linea (headers)

    while (fgets(buffer, sizeof(buffer), fcsv)) {

        buffer[strcspn(buffer, "\n")] = '\0';
        lineToSong(buffer, &song);
        song.nextOffset = -1; // Inicializar nextOffset
        fwrite(&song, sizeof(struct Song), 1, fbin);
    }

    fclose(fcsv);
    fclose(fbin);

    struct hashTable table = writeHashTable("songs.bin");

    FILE *hashTableSongs = fopen("hashTableSongs.bin", "wb");
    if (!hashTableSongs) {
        perror("Error al crear el archivo binario de la tabla hash");
        return 1;
    }

    fwrite(&table, sizeof(struct hashTable), 1, hashTableSongs);
    fclose(hashTableSongs);

    return 0;
}
