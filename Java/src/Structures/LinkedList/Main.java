package Structures.LinkedList;

public class Main {
    public static void main(String[] args) {
        final int start = 10;
        final int end = 100000000; // 10^8
        
        for (int size = start; size <= end; size *= 10)

            for (int i = 0; i < 6; i++){ // se hace 6 veces por cada tamaño de datos para luego sacar promedio
                SinglyLinkedListNT<Integer> lista = new SinglyLinkedListNT<>();

                for (int a = 0; a < size; a++) // se crea la lista de {size} datos
                    lista.pushFront(a);

                exec(size, "pushBack", lista::pushBack, 55);

                Runtime runtime = Runtime.getRuntime();
                long usedMemory = runtime.totalMemory() - runtime.freeMemory();
                System.out.println("Memoria Usada: " + (usedMemory / 1024 / 1024) + " MB");

                lista = null;
                System.gc(); // sugerencia al GC
            }
    }

    /**** exec con sobrecarga de metodos ****/

    public static void exec(int size, String method, Operation metodo) {
        long start = System.nanoTime();

        metodo.apply(); // ejecuto con el dato

        long finish = System.nanoTime();
        double timeElapsed = (finish - start) / 1000000.0; // paso de nanosegundos a milisegundos
        System.out.printf("\nSe ejecutó %s de %d elementos en: %.4f milisegundos\n", method, size, timeElapsed);
    }

    public static void exec(int size, String method, OperationV<Integer> metodo, Integer value) {
        long start = System.nanoTime();

        metodo.apply(value); // ejecuto con el dato

        long finish = System.nanoTime();
        double timeElapsed = (finish - start) / 1000000.0; // paso de nanosegundos a milisegundos
        System.out.printf("\nSe ejecutó %s de %d elementos en: %.4f milisegundos\n", method, size, timeElapsed);
    }

    public static void exec(int size, String method, OperationD<Integer> metodo, Integer value, Integer key) {
        long start = System.nanoTime();

        metodo.apply(value, key); // ejecuto con los datos

        long finish = System.nanoTime();
        double timeElapsed = (finish - start) / 1000000.0; // paso de nanosegundos a milisegundos
        System.out.printf("\nSe ejecutó %s de %d elementos en: %.4f milisegundos\n", method, size, timeElapsed);
    }
}