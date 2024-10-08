import java.util.Scanner;

class HolaMundo {
    public static void main(String [] args){
        System.out.println("Hola Mundo!");
        System.out.println("Hola Tomas");
    }
}

class DatosPrimitivos {
    public static void main(String[] args) {
        byte peque = 12; // de -128 a 127
        short corto = 10257; // de -32768 a 32767
        int entero = 465161861; // de -2^31 a 2^31-1
        long largo = 1865141448658941l; // de -2^63 a 2^63-1 y se le pone una l al final para especificar que es de tipo long
        System.out.println("Número entero: " + peque);
        System.out.println("Número entero: " + corto);
        System.out.println("Número entero: " + entero);
        System.out.println("Número entero: " + largo);

        float decimal = 45.05f; // la f es para especificar que es de tipo float
        double doble = 4148741.1841; //
        System.out.println("Número entero: " + decimal); // 2^32
        System.out.println("Número entero: " + doble); // 2^64

        char caracter = 'H'; //para denominar un caracter se hace con comillas simples ''
        boolean boleano = true; // pude ser true o false (con minuscula)
        System.out.println("Caracter: " + caracter);
        System.out.println("Desición: " + boleano);
    }
}

class NoPrimitivos {
    public static void main(String[] args) {
        Integer numero = 10; //a diferencia de int, puede almacenar datos tipo null
        String cadena = "Me gusta el pan"; //para un string se hace con comillas dobles ""
        System.out.println("Número entero: " + numero);
        System.out.println("Frase: " + cadena);

        final int constante = 10; //final se usa para definir una variable que no va a cambiar xd
        System.out.println("Constante: " + constante);
        /*los tipos de datos no primitivos tienen metodos con los que puedo jugar :p
        numero.(metodo) // cadena.(metodo)
        */ 

        enum Color {ROJO, AMARILLO, AZUL, ANARANJADO, VERDE, MORADO, NEGRO, BLANCO} // es como si fuera una clase 
        Color colo = Color.AMARILLO; //se puede instanciar un enum
        System.out.println("El color es: " + colo);

        for (Color c: Color.values()) {
            System.out.println("El color es: " + c);
        }
    }
}

class Entrada {
    public static void main(String[] args) {
        Scanner lectura = new Scanner(System.in); //es una entrada de datos
        float numero;

        System.out.print("Digite un número: ");
        numero = lectura.nextFloat(); // debe ir (variable Scanner).next(tipo de dato)();
        System.out.println("El número es: " + numero); 

        lectura.nextLine(); /* despues de ingresar un float, int, double, etc; se hace un salto de linea automatico, y esto lo "recoje"
        por lo que si se deja sin este "receptor" el proximo nextLine() lee ese salto de linea y no deja ingresa cadenas */

        System.out.print("Ingrese una palabra: ");
        String cadena = lectura.nextLine();
        System.out.println("La palabra es: " + cadena);

        System.out.print("Ingrese un caracter: ");
        char letra = lectura.next().charAt(0); // charAt(0) es para especificar el caracter de la palabra que debemos guardar
        System.out.println("El caracter es: " + letra);
        lectura.close(); //cuando se cierra la entrada de datos, no se puede instanciar otro objeto lector de datos
    }
}