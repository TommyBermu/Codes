package Taller_2;

import java.util.*;
import java.lang.Math;

public class Solution {
    public static void main(String[] args) {
        // Fracciones4();
        // ArregloDeFracciones();
        // DivisionDeFracciones();
        // FraccionMasCercano();
        // Fracciones5();
    }

    public static void Fracciones4() {

        // Fracciones 4

        Scanner sc = new Scanner(System.in);
        Fraccion fraccion = new Fraccion(sc.nextInt(), sc.nextInt());
        System.out.println(fraccion.Propio() + " " + fraccion.Mixto());
        sc.close();
    }

    public static void ArregloDeFracciones() {

        // Arreglo de Fracciones

        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        String propios[] = new String[N];
        String mixtos[] = new String[N];
        for (int i = 0; i < N; i++) {
            Fraccion frac = new Fraccion(sc.nextInt(), sc.nextInt());
            propios[i] = frac.Propio();
            mixtos[i] = frac.Mixto();
        }
        String option = sc.next();
        if (option.equals("p")) {
            for (int i = 0; i < N; i++) {
                System.out.print(propios[i] + " ");
            }
        } else if (option.equals("m")) {
            for (int i = 0; i < N; i++) {
                System.out.print(mixtos[i] + " ");
            }
        } else if (option.equals("a")) {
            for (int i = 0; i < N; i++) {
                System.out.print(propios[i] + " " + mixtos[i] + (mixtos[i].equals("1") || mixtos[i].equals("-1") ? "" : " "));
            }
        }
        sc.close();
    }

    public static void DivisionDeFracciones() {

        // División de Fracciones

        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        String resultado = "";
        List<Fraccion> lista = new ArrayList<Fraccion>();
        for (int i = 0; i < N; i++) {
            lista.add(new Fraccion(sc.nextInt(), sc.nextInt()));
        }

        for (int i = 0; i < N - 1; i++) {
            lista.set(i, Fraccion.Division(lista.get(i), lista.get(i + 1)));
            resultado += lista.get(i).Mixto() + " ";
        }
        System.out.println(resultado);
        sc.close();
    }

    public static void FraccionMasCercano() {

        // Fracción más Cercano

        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        List<Fraccion> lista = new ArrayList<Fraccion>();

        for (int i = 0; i < N; i++) {
            lista.add(new Fraccion(sc.nextInt(), sc.nextInt()));
        }

        Fraccion target = new Fraccion(sc.nextInt(), sc.nextInt());
        int posicion = 0;
        double menor = Math.abs(lista.get(0).getValue() - target.getValue());

        for (int i = 1; i < N; i++) {
            if (Math.abs(lista.get(i).getValue() - target.getValue()) < menor) {
                menor = Math.abs(lista.get(i).getValue() - target.getValue());
                posicion = i;
            }
        }
        System.out.println(target.den_crudo);
        System.out.println(Fraccion.Resta(lista.get(posicion), target).Propio());
        System.out.println(lista.get(posicion).Mixto());
        sc.close();

    }

    public static void Fracciones5() {

        // Fracciones 5

        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        ArrayList<Fraccion> lista = new ArrayList<Fraccion>();
        for (int i = 0; i < N; i++) {
            lista.add(new Fraccion(sc.nextInt(), sc.nextInt()));
        }
        sc.close();
        for (Fraccion fraccion : lista) {
            fraccion.simplificar();
        }
        Collections.sort(lista);
        for (Fraccion fraccion : lista) {
            System.out.println(fraccion);
        }
        System.out.println(Fraccion.sumatoria(lista));
    }
}
