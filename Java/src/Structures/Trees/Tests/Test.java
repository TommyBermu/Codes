package Structures.Trees.Tests;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import Structures.Trees.BinarySearchTree;

public class Test {
    public static void main(String[] args) {
        try {
            final int start = 100; // 10^2
            final int end = 100000000; // 10^8
            final int tests = 6; // cantidad de pruebas a promediar

            /**************************/
            String method = "pushFront";
            /**************************/

            PrintWriter salida = new PrintWriter(new FileWriter("./Java/src/Structures/LinkedList/" + method + ".txt"));
            double[] cpu = new double[tests];
            double[] ram = new double[tests];
            
            for (int size = start; size <= end; size *= 10){

                for (int i = 0; i < tests; i++){ // se hace {tests} veces por cada tamaño de datos para luego sacar promedio

                    /*************************************************************/
                    BinarySearchTree<Integer> arbol = new BinarySearchTree<>();
                    /*************************************************************/

                     // TODO se crea el arbol de {size} datos

                    /**************************************************************************/
                    // TODO double timeElapsed = exec(arbol::addBefore, 777, lista.fetch(size/2)); 
                    /*************************************************************************

                    Runtime runtime = Runtime.getRuntime();
                    double usedMemory = runtime.totalMemory() - runtime.freeMemory();

                    System.out.printf("\nSe ejecutó %s de %d elementos en: %.4f milisegundos", method, size, timeElapsed);
                    System.out.printf("\nMemoria Usada:  %.1f  MB\n", (usedMemory / 1024 / 1024));
                    
                    cpu[i] = timeElapsed;
                    ram[i] = usedMemory;

                    arbol = null;
                    System.gc(); // sugerencia al GC

                    */
                }

                // se calculan los promeidos
                double p_cpu = 0;
                double p_ram = 0;
                for (int i = 0; i < tests; i++){
                    p_cpu += cpu[i];
                    p_ram += ram[i];
                }
                p_cpu /= tests;
                p_ram /= tests;
                // se sacan los resultados promedio a un archivo .txt
                salida.printf("\nSe ejecutó %s de %d elementos en: %.4f milisegundos", method, size, p_cpu);
                salida.printf("\nMemoria Usada:  %.1f  MB\n", (p_ram / 1024 / 1024));
            }
                
            salida.close();

            } catch (IOException e){
                e.printStackTrace();
            }
    }

    /****************************************/
    /**** exec con sobrecarga de metodos ****/
    /****************************************/

    // metodo que no recibe argumentos
    public static double exec(Operation metodo) {
        long start = System.nanoTime();

        metodo.apply();

        long finish = System.nanoTime();
        return (finish - start) / 1000000.0; // paso de nanosegundos a milisegundos
    }

    // metodo que recibe un argumento
    public static double exec(OperationV<Integer> metodo, Integer value) {
        long start = System.nanoTime();

        metodo.apply(value); // ejecuto con el dato

        long finish = System.nanoTime();
        return (finish - start) / 1000000.0; // paso de nanosegundos a milisegundos
    }
}