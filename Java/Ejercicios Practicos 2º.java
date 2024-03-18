import java.util.Scanner;

class Ejercicio_2_1 {
    public static void main(String[] args) {
        Scanner entrada = new Scanner(System.in);
        int dato;
        System.out.print("Ingrese un número entero: ");
        dato = entrada.nextInt();
        if (dato%10 == 0) {
            System.out.println(dato + " Es un múltiplo de 10");
        } else {
            System.out.println(dato + " No es un múltiplo de 10");
        }
        entrada.close();
    }
}

class Ejercicio_2_2 {
    public static void main(String[] args) {
        Scanner entrada = new Scanner(System.in);
        double a, b;
        System.out.print("Ingrese un dato a: ");
        a = entrada.nextDouble();
        System.out.print("Ingrese un dato b: ");
        b = entrada.nextDouble();
        if (a > b) {
            System.out.println(a + " Es mayor que " + b);
        }
        if (a < b) {
            System.out.println(b + " Es mayor que " + a);
        }
        if (a == b) {
            System.out.println("Ambos números son iguales");
        }
        entrada.close();
    }
}

class Ejercicio_2_3 {
    public static void main(String[] args) {
        Scanner entrada = new Scanner(System.in);
        char caracter;
        System.out.print("Ingrese un caracter: ");
        caracter = entrada.next().charAt(0);
        if (Character.isUpperCase(caracter)) {
            System.out.println("El caracter " + caracter + " está en mayuscula");
        } else {
            System.out.println("El caracter " + caracter + " está en minuscula");
        }
        entrada.close();
    }
}

class Ejercicio_2_4 {
    public static void main(String[] args) {
        Scanner entrada = new Scanner(System.in);
        double valor;
        System.out.print("Ingrese sus comprar totales: ");
        valor = entrada.nextDouble();
        if (valor > 300) {
            System.out.println("Su total a pagar será de " + (valor - (valor*0.2)) + " pesos");
        } else {
            System.out.println("No aplica el descuento");
        }
        entrada.close();
    }
}

class Ejercicio_2_5 {
    public static void main(String[] args) {
        Scanner entrada = new Scanner(System.in);
        double horas;
        System.out.print("Ingrese sus horas trabajadas esta semana: ");
        horas = entrada.nextDouble();
        if (horas <= 40) {
            System.out.println("Su paga será de " + horas*16 + " pesos");
        } else {
            System.out.println("Su paga será de " + (640 + (horas-40)*20) + " pesos");
        }
        entrada.close();
    }
}

class Ejercicio_2_6 {
    public static void main(String[] args) {
        Scanner entrada = new Scanner(System.in);
        int a, b;
        System.out.println("Ingrese dos números: ");
        a = entrada.nextInt();
        b = entrada.nextInt();
        if (a%2 == 0 && b%2 == 0) {
            System.out.println("Ambos números son pares");
        } else if (a%2 == 1 && b%2 == 1) {
                System.out.println("Ambos números son impares");
        } else {
            System.out.println("Un número es par y otro es impar");
        }
        entrada.close();
    }
}

class Ejercicio_2_7 {
    public static void main(String[] args) {
        Scanner entrada = new Scanner(System.in);
        double a, b, c;
        System.out.println("Ingrese tres números: ");
        a = entrada.nextDouble();
        b = entrada.nextDouble();
        c = entrada.nextDouble();
        if (a > b && b > c) {
            System.out.println("Ordem: " + a + ", " + b + ", " + c);
        } else if (a > c && c > b) {
            System.out.println("Ordem: " + a + ", " + c + ", " + b);
        } else if (b > a && a > c) {
            System.out.println("Ordem: " + b + ", " + a + ", " + c);
        } else if (b > c && c > a) {
            System.out.println("Ordem: " + b + ", " + c + ", " + a);
        } else if (c > a && a > b) {
            System.out.println("Ordem: " + c + ", " + a + ", " + b);
        } else if (c > b && b > a) {
            System.out.println("Ordem: " + c + ", " + b + ", " + a);
        } 
        entrada.close();
    }
}

class Ejercicio_2_8 {
    public static void main(String[] args) {
        Scanner entrada = new Scanner(System.in);
        int numero;
        System.out.println("Ingrese un entero: ");
        numero = entrada.nextInt();
        if (numero < 10){
            System.out.println("El número tiene una cifra");
        } else if (numero < 100){
            System.out.println("El número tiene dos cifras");
        } else if (numero < 1000){
            System.out.println("El número tiene tres cifras");
        } else if (numero < 10000){
            System.out.println("El número tiene cuatro cifras");
        } else if (numero < 100000){
            System.out.println("El número tiene cinco cifras");
        }
        entrada.close();
    }
}

class Ejercicio_2_9 {
    public static void main(String[] args) {
        Scanner entrada = new Scanner(System.in);
        int dia, mes, año;
        
        System.out.println("Ingrese el día: "); dia = entrada.nextInt();
        System.out.println("Ingrese el mes: "); mes = entrada.nextInt();
        System.out.println("Ingrese el año: "); año = entrada.nextInt();

        if (dia >= 1 && dia <= 30){
            if (mes >= 1 && mes <= 12){
                if (año != 0){
                    System.out.println("La fecha es correcta");
                } else {
                    System.out.println("La fecha es incorrecta, año incorrecto");
                }
            } else {
                System.out.println("La fecha es incorrecta, mes incorrecto");
            }
        } else {
            System.out.println("La fecha es incorrecta, dia incorrecto");
        }
        entrada.close();
    }
}

class Ejercicio_2_10 {
    public static void main(String[] args) {
        Scanner entrada = new Scanner(System.in);
        int dia, mes, año;
        
        System.out.println("Ingrese el día: "); dia = entrada.nextInt();
        System.out.println("Ingrese el mes: "); mes = entrada.nextInt();
        System.out.println("Ingrese el año: "); año = entrada.nextInt();

        if ((dia >= 1 && dia <= 31 && (mes == 1 || mes == 3 || mes == 5 || mes == 7 || mes == 8 || mes == 10 || mes == 12)) || (dia >= 1 && dia <= 30 && (mes == 4 || mes == 6 || mes == 9 || mes == 11)) || (dia >= 1 && dia <= 28 && mes == 2)){
            if (mes >= 1 && mes <= 12){
                if (año != 0){
                    System.out.println("La fecha es correcta");
                } else {
                    System.out.println("La fecha es incorrecta, año incorrecto");
                }
            } else {
                System.out.println("La fecha es incorrecta, mes incorrecto");
            }
        } else {
            System.out.println("La fecha es incorrecta, dia incorrecto");
        }
        entrada.close();
        entrada.close();
    }
}

class Ejercicio_2_11 {
    public static void main(String[] args) {
        Scanner entrada = new Scanner(System.in);
        entrada.close();
    }
}

class Ejercicio_2_12 {
    public static void main(String[] args) {
        Scanner entrada = new Scanner(System.in);
        entrada.close();
    }
}

class Ejercicio_2_13 {
    public static void main(String[] args) {
        Scanner entrada = new Scanner(System.in);
        entrada.close();
    }
}

class Ejercicio_2_14 {
    public static void main(String[] args) {
        Scanner entrada = new Scanner(System.in);
        entrada.close();
    }
}