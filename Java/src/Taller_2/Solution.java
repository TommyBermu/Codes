package Taller_2;
import java.util.*;

public class Solution {
    public static void main(String[] args) {
        /* Fracciones 4

        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int d = sc.nextInt();
        Fraccion fraccion = new Fraccion(n ,d);
        System.out.println(fraccion.Solution());
        sc.close();

        */

        /* Arreglo de fracciones */

        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        String propios[] = new String[N];
        String mixtos[] = new String[N];
        for(int i = 0; i < N; i++){
            int n = sc.nextInt();
            int d = sc.nextInt();
            Fraccion frac = new Fraccion(n, d);
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
    }
}