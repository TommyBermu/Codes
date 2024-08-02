package Parcial_1;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        //se crea una lista en la que se almacenen todos los puntos
        ArrayList <Punto> puntos = new ArrayList<Punto>();
        int cantPuntos = sc.nextInt();
        int dimensionPuntos = sc.nextInt();
        for (int i = 0; i < cantPuntos; i++){
            ArrayList <Double> posicion = new ArrayList<Double>();
            for (int j = 0; j < dimensionPuntos; j++){
                posicion.add(sc.nextDouble());
            }
            puntos.add(new Punto(posicion));
        }

        //se crea una lista en la que se almacenen los clusters
        ArrayList <Cluster> clusters = new ArrayList<Cluster>();
        int cantClusters = sc.nextInt();
        for (int i = 0; i < cantClusters; i++){
            ArrayList <Double> posicion = new ArrayList<Double>();
            for (int j = 0; j < dimensionPuntos; j++){
                posicion.add(sc.nextDouble());
            }
            clusters.add(new Cluster(new Punto(posicion)));
        }

        //cantidad de iteraciones para K-Means
        int cantIterciones = sc.nextInt();

        //parámetro P para Minkowski
        int p = sc.nextInt();
        
        sc.close();

        KMeans kmeans = new KMeans(clusters, puntos, cantIterciones, p);
        kmeans.imprimir();
    }
}
