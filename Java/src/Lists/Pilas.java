package Lists;

import java.util.Stack;

public class Pilas {
    public static void main(String[] args) {

        Stack<Integer> pila = new Stack<>();

        System.out.println("Pila vacía: "+ pila);
        System.out.println("¿Está vacía?: " + pila.isEmpty());

        for (int i = 0; i < 10; i++) {
            pila.push(i);
        }

        System.out.println("Pila: "+ pila);
        System.out.println("¿Está vacía?: " + pila.isEmpty());
        
        pila.pop(); // elimina el último elemento agregado

        System.out.println("¿Está el 3?: "+ pila.search(3));
        System.out.println("Último agregado: " + pila.peek());
    }
}