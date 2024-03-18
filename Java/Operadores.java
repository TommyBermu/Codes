import java.util.Scanner;
import java.lang.Math;

class Aritmetica {
    public static void main(String[] args) {
        Scanner entrada = new Scanner(System.in);
        float numero1, numero2, suma, resta, multiplicacion, division, resto;
        
        System.out.println("Digita 2 numeros: ");
        numero1 = entrada.nextFloat();
        numero2 = entrada.nextFloat();

        suma = numero1 + numero2;
        resta = numero1 - numero2;
        multiplicacion = numero1 * numero2;
        division = numero1 / numero2;
        resto = numero1 % numero2;

        System.out.println("La resta es: " + suma);
        System.out.println("La resta es: " + resta);
        System.out.println("La multiplicacion es: " + multiplicacion);
        System.out.println("La division es: " + division);
        System.out.println("El resto es: " + resto);

        entrada.close();
    }
}

class Matematicas {
    public static void main(String[] args) {
        double raiz = Math.sqrt(9);
        System.out.println(raiz);

        double resultado = Math.pow(5, 2);
        System.out.println(resultado); 

        float flotante = 4.49f; //hacia arriba si > 0.5 y si no, hacia abajo
        flotante = Math.round(flotante); //
        System.out.println(flotante);

        double aleatorio = Math.random(); // entre 0 y 1
        System.out.println(aleatorio);
    }
}