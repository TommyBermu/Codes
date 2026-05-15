#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <time.h>
#include <unistd.h>

#define MB (1024 * 1024)

// Estructura para guardar bloques de memoria
typedef struct {
    void *ptr;
    size_t size;
} MemBlock;

void print_memory_usage(size_t total_mb) {
    printf("Memoria ocupada: %zu MB (%.2f GB)\n", total_mb, total_mb / 1024.0);
}

void fill_memory(void *ptr, size_t size) {
    char *data = (char *)ptr;
    // Llenar con datos aleatorios para forzar el uso real de memoria
    for (size_t i = 0; i < size; i += 4096) {
        data[i] = rand() % 256;
    }
}

int main(int argc, char *argv[]) {
    size_t target_mb = 4096; // 1GB por defecto
    int duration = 60;       // 30 segundos por defecto
    int continuous = 0;      // Modo continuo de escritura

    if (argc > 1) {
        target_mb = atoi(argv[1]);
    }
    if (argc > 2) {
        duration = atoi(argv[2]);
    }
    if (argc > 3) {
        continuous = atoi(argv[3]);
    }

    printf("=== Memory Stress Test ===\n");
    printf("Memoria objetivo: %zu MB (%.2f GB)\n", target_mb,
           target_mb / 1024.0);
    printf("Duración: %d segundos\n", duration);
    printf("Modo continuo: %s\n\n", continuous ? "Sí" : "No");

    size_t block_size = 10 * MB; // Bloques de 10MB
    size_t num_blocks = (target_mb * MB) / block_size;

    MemBlock *blocks = malloc(num_blocks * sizeof(MemBlock));
    if (!blocks) {
        fprintf(stderr, "Error: No se pudo asignar memoria para control\n");
        return 1;
    }

    printf("Asignando memoria en bloques de %zu MB...\n\n", block_size / MB);

    size_t total_allocated = 0;
    size_t successful_blocks = 0;

    // Fase 1: Asignar memoria
    for (size_t i = 0; i < num_blocks; i++) {
        blocks[i].ptr = malloc(block_size);

        if (blocks[i].ptr == NULL) {
            printf("Advertencia: No se pudo asignar más memoria en el bloque "
                   "%zu\n",
                   i);
            break;
        }

        blocks[i].size = block_size;
        fill_memory(blocks[i].ptr, block_size);

        total_allocated += block_size;
        successful_blocks++;

        if ((i + 1) % 10 == 0) {
            print_memory_usage(total_allocated / MB);
        }
    }

    printf("\n✓ Asignación completada\n");
    print_memory_usage(total_allocated / MB);

    // Fase 2: Mantener y opcionalmente escribir en la memoria
    printf("\nManteniendo memoria ocupada por %d segundos...\n", duration);

    time_t start_time = time(NULL);
    int iteration = 0;

    while (difftime(time(NULL), start_time) < duration) {
        if (continuous) {
            // Escribir continuamente en la memoria
            for (size_t i = 0; i < successful_blocks; i++) {
                fill_memory(blocks[i].ptr, blocks[i].size);
            }
            iteration++;
            printf("Iteración %d - Memoria reescrita\n", iteration);
        }

        sleep(1);

        if (!continuous && (int)difftime(time(NULL), start_time) % 5 == 0) {
            printf("Tiempo restante: %d segundos\n",
                   duration - (int)difftime(time(NULL), start_time));
        }
    }

    // Fase 3: Liberar memoria
    printf("\nLiberando memoria...\n");
    for (size_t i = 0; i < successful_blocks; i++) {
        free(blocks[i].ptr);
    }
    free(blocks);

    printf("✓ Test completado - Memoria liberada\n");

    return 0;
}
