package Taller_2;
import java.util.*;
import java.lang.Math;

public class Solution {
    public static void main(String[] args) {
        // Fracciones 4
        
        /*

        Scanner sc = new Scanner(System.in);
        Fraccion fraccion = new Fraccion(sc.nextInt(), sc.nextInt());
        System.out.println(fraccion.Propio() +" "+ fraccion.Mixto());
        sc.close();

        */

        // Arreglo de Fracciones 

        /*

        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        String propios[] = new String[N];
        String mixtos[] = new String[N];
        for(int i = 0; i < N; i++){
            Fraccion frac = new Fraccion(sc.nextInt(), sc.nextInt());
            propios[i] = frac.Propio();
            mixtos[i] = frac.Mixto();
        }
        String option = sc.next();
        if (option.equals("p")){
            for(int i = 0; i < N; i++){
                System.out.print(propios[i] + " ");
            }
        } else if (option.equals("m")) {
            for(int i = 0; i < N; i++){
                System.out.print(mixtos[i] + " ");
            }
        } else if (option.equals("a")) {
            for(int i = 0; i < N; i++){
                System.out.print(propios[i] + " " + mixtos[i] + (mixtos[i].equals("1") || mixtos[i].equals("-1") ? "" : " "));
            }
        }
        sc.close();
        
        */
        
        // División de Fracciones 

        /*

        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        String resultado = "";
        List <Fraccion> lista = new ArrayList<Fraccion>();
        for(int i = 0; i < N; i++){
            lista.add(new Fraccion(sc.nextInt(), sc.nextInt()));
        }

        for (int i = 0; i < N-1; i++) {
            System.out.println(resultado);
            resultado += lista.get(i).Division(lista.get(i+1)).Mixto() + " ";
        }
        System.out.println(resultado);
        sc.close();

        */

        // Fracción más Cercano

        /*

        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        List <Fraccion> lista = new ArrayList<Fraccion>();
        for(int i = 0; i < N; i++){
            lista.add(new Fraccion(sc.nextInt(), sc.nextInt()));
        }
        Fraccion target = new Fraccion(sc.nextInt(), sc.nextInt());
        int posicion = 0;
        double menor = Math.abs(lista.get(0).Diferencia(target));
        for (int i = 1; i < N; i++) {
            if (Math.abs(lista.get(i).Diferencia(target)) < menor) {
                menor = Math.abs(lista.get(i).Diferencia(target));
                posicion = i;
            }
        }
        System.out.println(lista.get(posicion).Resta(target).Mixto());
        System.out.println(lista.get(posicion).Mixto());
        sc.close();

        */
    }
}