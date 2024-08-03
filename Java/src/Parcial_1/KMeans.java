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

            //si es la última iteración, que no se reinicie la lista de puntos del cluster
            for (Cluster iCluster: this.clusters){
                iCluster.actualizarCentroide();
                if (i != iteraciones -1){
                    iCluster.reiniciarPuntos();
                }
            }
        }
    }

    public void imprimirPuntos(){
        String imprimible = "";
        for (short i = 0; i < clusters.size(); i++){
            imprimible += "Cluster " + (i + 1) + " - " + clusters.get(i).toString() + "\nPuntos en el cluster:\n" ;
            for (short j = 0; j < clusters.get(i).getPuntos().size(); j++) {
                imprimible += clusters.get(i).getPuntos().get(j).toString();
                imprimible += (i == clusters.size()-1 && j == clusters.get(i).getPuntos().size()-1 ? "" : "\n") + (j == clusters.get(i).getPuntos().size()-1 && i != clusters.size()-1 ? "\n" : "");
            }
        }
        System.out.println(imprimible);
    }

    public void imprimirPalabras(){
        String imprimible = "";
        for (short i = 0; i < clusters.size(); i++){
            imprimible += "Cluster " + (i + 1) + "\nPalabras en el cluster:\n";
        }
        System.out.println(imprimible);
    }
}