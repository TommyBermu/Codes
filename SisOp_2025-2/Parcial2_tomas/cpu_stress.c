#include <math.h>
#include <pthread.h>
#include <stdio.h>
#include <unistd.h>

#define NUM_THREADS 8

// Función que ejecuta operaciones intensivas
void *cpu_intensive_task(void *arg) {
    int thread_id = *(int *)arg;
    double result = 0.0;

    printf("Thread %d iniciado\n", thread_id);

    // Bucle infinito con operaciones matemáticas intensivas
    while (1) {
        for (long i = 0; i < 1000000; i++) {
            result += sqrt(i) * tan(i) * sin(i) * cos(i);
            result = fmod(result, 1000000);
        }
    }

    return NULL;
}

int main(int argc, char *argv[]) {
    pthread_t threads[NUM_THREADS];
    int thread_ids[NUM_THREADS];

    printf("=== Programa de Carga CPU ===\n");
    printf("Iniciando %d hilos de procesamiento...\n", NUM_THREADS);
    printf("Presiona Ctrl+C para detener\n\n");

    // Crear múltiples hilos para maximizar el uso de CPU
    for (int i = 0; i < NUM_THREADS; i++) {
        thread_ids[i] = i;
        if (pthread_create(&threads[i], NULL, cpu_intensive_task,
                           &thread_ids[i]) != 0) {
            fprintf(stderr, "Error al crear thread %d\n", i);
            return 1;
        }
    }

    // Esperar a que terminen los hilos (nunca terminarán)
    for (int i = 0; i < NUM_THREADS; i++) {
        pthread_join(threads[i], NULL);
    }

    return 0;
}
