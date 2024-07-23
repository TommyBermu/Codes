package Taller_1;
import java.util.Scanner;
class Persona {
    String Nombre, Genero;
    double Peso, Altura;
    int Edad;
    public Persona(){
        Scanner sc = new Scanner(System.in);
        Nombre = sc.nextLine();
        Edad = sc.nextInt();
        sc.nextLine();
        Genero = sc.nextLine();
        Peso = sc.nextDouble();
        sc.nextLine();
        Altura = sc.nextDouble();
        sc.close();
    }

    double calcularIMC(){
        return Peso / (Altura*Altura);
    }

    boolean esMayorDeEdad(){
        return Edad >=18 ? true: false;
    }

    public String toString(){
        return "Nombre:"+Nombre + " edad:"+Edad + " genero:"+Genero + " peso:"+Peso + " altura:"+Altura;
    }
}

class Solution1 {
    public static void main(String[] args) {
        Persona mano = new Persona();
        System.out.println(mano.calcularIMC());
        System.out.println("Mayor de edad: " + mano.esMayorDeEdad());
        System.out.println(mano.toString());
    }
}