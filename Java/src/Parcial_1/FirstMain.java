package Parcial_1;

import java.util.Locale;
import java.util.Scanner;

public class FirstMain {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        sc.useLocale(Locale.ENGLISH);

        int cantPuntos = sc.nextInt();
        Punto[] puntos = new Punto[cantPuntos];
        int dimensionPuntos = sc.nextInt();

        for (int i = 0; i < cantPuntos; i++) {
            double[] posicion = new double[dimensionPuntos];
            for (int j = 0; j < dimensionPuntos; j++) {
                posicion[j] = sc.nextDouble();
            }
            puntos[i] = new Punto(posicion);
        }

        int cantClusters = sc.nextInt();
        Cluster[] clusters = new Cluster[cantClusters];
        for (int i = 0; i < cantClusters; i++) {
            double[] posicion = new double[dimensionPuntos];
            for (int j = 0; j < dimensionPuntos; j++) {
                posicion[j] = sc.nextDouble();
            }
            clusters[i] = new Cluster(new Punto(posicion));
        }

        int cantIterciones = sc.nextInt();
        int p = sc.nextInt();
        sc.close();

        KMeans kmeans = new KMeans(clusters, puntos, cantIterciones, p);
        kmeans.imprimirPuntos();
    }
}