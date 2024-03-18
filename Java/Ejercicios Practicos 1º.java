import java.util.Scanner;

class Ejercicio_1_1 {
    // calcula e imprima el promedio de 3 notas
    public static void main(String[] args) {
        double n1 , n2 , n3 , total;
        Scanner entrada = new Scanner(System.in);
        System.out.println("Ingrese sus 3 calificaciones: ");
        n1 = entrada.nextDouble();
        n2 = entrada.nextDouble();
        n3 = entrada.nextDouble();
        total = n1 + n2 + n3;
        System.out.println("El total de sus calificaciones es " + total/3);
        entrada.close();
    }
}

class Ejercicio_1_2 {
    public static void main(String[] args) {
        double salario, horas;
        Scanner entrada = new Scanner(System.in);
        System.out.println("Ingrese su salario por hora: ");
        salario = entrada.nextDouble();
        System.out.println("Ingrese sus horas trabajadas esta semana: ");
        horas = entrada.nextDouble();
        System.out.println("Su paga total es de: " + salario * horas);
        entrada.close();
    }
}

class Ejercicio_1_3 {
    /* Guillermo = N
     * Luis = N/2
     * Juan = (N+N/2)/2
     */
    public static void main(String[] args) {
        double dolares;
        Scanner entrada = new Scanner(System.in);
        System.out.println("Ingrese la cantidad de dolares que tiene guillermo: ");
        dolares = entrada.nextDouble();
        System.out.println("Entre Guillermo, Luis y Juan hay " + (dolares + dolares/2 + (dolares+(dolares/2))/2) + " dolares");
        entrada.close();
    }
}

class Ejercicio_1_4 {
    public static void main(String[] args) {
        /* Una compañia paga 1000 mensuales, más 150 por carro vendido, más el 5% del valor del carro vendido, calcular el salario de un vendedor dado */
        Scanner entrada = new Scanner(System.in);
        double cant_carros, aditivo;
        System.out.println("Indique cuantos carros se vendieron en el mes: ");
        cant_carros = entrada.nextDouble();
        aditivo = 0;
        double bucle = cant_carros;
        while (bucle > 0) {
            bucle -= 1;
            System.out.println("Indique el precio del carro: ");
            aditivo += entrada.nextDouble() * 0.05;
        }
        System.out.println("El pago mensual del vendedor es de: " + (1000 + 150 * cant_carros + aditivo));
        entrada.close();
    }
}

class Ejercicio_1_5 {
    public static void main(String[] args) {
        double n1, n2, n3, parti , nfinal;
        Scanner entrada = new Scanner(System.in);
        System.out.println("Ingrese la nota de sus participaciónes: ");
        parti = entrada.nextDouble();
        System.out.println("Ingrese las notas de sus parciales: ");
        n1 = entrada.nextDouble();
        n2 = entrada.nextDouble();
        n3 = entrada.nextDouble();
        nfinal = parti*0.1 + n1*0.25 + n2*0.25 + n3*0.4;
        System.out.println("La nota final del estudiante es de: " + nfinal);
        entrada.close(); 
    }
}

class Ejercicio_1_6 {
    public static void main(String[] args) {
        double a, b, suma;
        Scanner entrada = new Scanner(System.in);
        System.out.println("Ingrese el valor de a: ");
        a = entrada.nextDouble();
        System.out.println("Ingrese el valor de b: ");
        b = entrada.nextDouble();
        suma = Math.pow((a+b), 2);
        System.out.println("El cuadrado de la suma entra a y b es: " + suma);
        entrada.close();
    }
}

class Ejercicio_1_7 {
    public static void main(String[] args) {
        Scanner entrada = new Scanner(System.in);
        int horas, sem, dia;
        System.out.println("Ingrese el número de horas: ");
        horas = entrada.nextInt();
        sem = horas/168;
        dia = horas%168 / 24;
        horas %= 24;
        System.out.println("Las horas ingresadas son: " + sem + " Semanas, " + dia + " Días, " + horas + " Horas.");
        entrada.close();
    }
}

class Ejercicio_1_8 {
    public static void main(String[] args) {
        Scanner entrada = new Scanner(System.in);
        double a, b, c, s, root1 , root2;
        System.out.print("Ingrese el valor de a: ");
        a = entrada.nextDouble();
        System.out.print("Ingrese el valor de b: ");
        b = entrada.nextDouble();
        System.out.print("Ingrese el valor de c: ");
        c = entrada.nextDouble();
        s = Math.sqrt(Math.pow(b, 2) - 4*a*c);
        root1 = (-b + s)/(2*a);
        root2 = (-b - s)/(2*a);
        System.out.println("Las raices son: " + root1 + ", " + root2);
        entrada.close();
    }
}