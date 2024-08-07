package Parcial_1;

import java.util.Scanner;

public class SecondMain {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        final int cantPalabras = sc.nextInt();
        sc.nextLine();
        Punto[] palabrasComoPunto = new Punto[cantPalabras];

        for (int i = 0; i < cantPalabras; i++) {
            String palabra = sc.nextLine();
            Punto palabraCalculada = new Punto(palabra);
            palabrasComoPunto[i] = palabraCalculada;
        }

        final int cantClusters = sc.nextInt();
        Cluster[] clusters = new Cluster[cantClusters];

        for (int i = 0; i < cantClusters; i++) {
            double[] posicion = new double[26];
            for (int j = 0; j < 26; j++) {
                posicion[j] = sc.nextDouble();
            }
            clusters[i] = new Cluster(new Punto(posicion));
        }

        final int p = sc.nextInt();
        final int cantIterciones = sc.nextInt();
        sc.close();

        KMeans kmeans = new KMeans(clusters, palabrasComoPunto, cantIterciones, p);
        kmeans.imprimirPalabras();
    }
}