#include <math.h>
#include <pthread.h>
#include <stdio.h>

int load_l() {
    long MAX = 1e09;
    double x;

    for (int i = 0; i < MAX; i++) {
        x = x + sin(x);
    }
    return x;
}

void *function(void *datos) {
    printf("nose %li\n", *(long *)datos);
    load_l();
    return NULL;
}

int main() {
    pthread_t hilo;
    int dato;
    int *retval;
    dato = 1234;
    int r;

    r = pthread_create(&hilo, NULL, (void *)function, (void *)&dato);

    pthread_join(hilo, (void **)&retval);
}
