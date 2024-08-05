package Parcial_1;

import java.util.Locale;
import java.util.Scanner;

public class FirstMain {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        sc.useLocale(Locale.ENGLISH);

        //se crea una lista en la que se almacenen todos los puntos
        int cantPuntos = sc.nextInt();
        Punto[] puntos = new Punto[cantPuntos];
        int dimensionPuntos = sc.nextInt();

        for (int i = 0; i < cantPuntos; i++){

            //se crea una lista para guardar las coordenadas de cada punto, y pasarlo al Array de puntos
            double[] posicion = new double[dimensionPuntos];
            for (int j = 0; j < dimensionPuntos; j++){
                posicion[j] = sc.nextDouble();
            }
            puntos[i] = new Punto(posicion);
        }

        //se crea una lista en la que se almacenen los clusters
        int cantClusters = sc.nextInt();
        Cluster[] clusters = new Cluster[cantClusters];
        for (int i = 0; i < cantClusters; i++){

            //se crea una lista para guardar las coordenadas de cada cluster, y pasarlo al Array de clusters
            double[] posicion = new double[dimensionPuntos];
            for (int j = 0; j < dimensionPuntos; j++){
                posicion[j] = sc.nextDouble();
            }
            clusters[i] = new Cluster(new Punto(posicion));
        }

        //cantidad de iteraciones para K-Means
        int cantIterciones = sc.nextInt();
        //parámetro P para Minkowski
        int p = sc.nextInt();
        sc.close();

        KMeans kmeans = new KMeans(clusters, puntos, cantIterciones, (double)p);
        kmeans.imprimirPuntos();
    }
}