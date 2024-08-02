package Parcial_1;

import java.util.ArrayList;

public class KMeans {
    private ArrayList<Cluster> clusters;
    private ArrayList<Punto> puntos;

    public KMeans(ArrayList<Cluster> clusters, ArrayList<Punto> puntos, int iteraciones, int p){
        this.clusters = clusters;
        this.puntos = puntos;

        //implementación del algoritmo de K-Means
        for (int i = 0; i < iteraciones; i++){

            //asigna cada punto al cluster más cercano
            for (Punto iPunto: this.puntos) {

                //inicio con el primer cluster para tener un punto de comparación
                int clusterMasCercano = 0;
                Double minDistancia = iPunto.calcularMinkowski(this.clusters.get(clusterMasCercano).getCentro(), p);

                //comparo las distancias del punto al centro de los clusters restantes
                for (int j = 1; j < this.clusters.size(); j++){
                    Double next = iPunto.calcularMinkowski(this.clusters.get(j).getCentro(), p);
                    if (next < minDistancia){
                        minDistancia = next;
                        clusterMasCercano = j;
                    }
                }

                //agrego el punto en el que se está al cluster más cercano
                this.clusters.get(clusterMasCercano).addPunto(iPunto);
            }

            //se actualizan los centroides de cada cluster
            for (Cluster iCluster: this.clusters){
                iCluster.actualizarCentroide();
            }
            //si es la última iteración, que no se reinicie la lista de puntos del cluster
            if (i != iteraciones -1){
                for (Cluster iCluster: this.clusters){
                    iCluster.reiniciarPuntos();
                }
            }
        }
    }

    public void imprimir(){
        System.out.println("c1 centro: " + clusters.get(0).getCentro() + ", puntos: " + clusters.get(0).toString());
        System.out.println("c2 centro: " + clusters.get(1).getCentro() + ", puntos: " + clusters.get(1).toString());
        System.out.println("c3 centro: " + clusters.get(2).getCentro() + ", puntos: " + clusters.get(2).toString());
    }

    public String toString(){
        return "sapo";
    }
}