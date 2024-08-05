package Parcial_1;

import java.util.HashMap;

public class KMeans {
    private Cluster[] clusters;
    private Punto[] puntos;

    public KMeans(Cluster[] clusters, Punto[] puntos, int iteraciones, double p){
        this.clusters = clusters;
        this.puntos = puntos;

        for (int i = 0; i < iteraciones; i++){

            for (Punto iPunto: this.puntos) {

                int clusterMasCercano = 0;
                double minDistancia = iPunto.calcularMinkowski(this.clusters[clusterMasCercano].getCentro(), p);

                for (int j = 1; j < this.clusters.length; j++){
                    double next = iPunto.calcularMinkowski(this.clusters[j].getCentro(), p);
                    if (next < minDistancia){
                        minDistancia = next;
                        clusterMasCercano = j;
                    }
                }
                this.clusters[clusterMasCercano].addPunto(iPunto);
            }

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
        for (int i = 0; i < clusters.length; i++){
            imprimible += "Cluster " + (i + 1) + " - " + clusters[i].toString() + "\nPuntos en el cluster:\n" ;
            for (int j = 0; j < clusters[i].getPuntos().size(); j++){
                imprimible += clusters[i].getPuntos().get(j).toString();
                imprimible += (i == clusters.length-1 && j == clusters[i].getPuntos().size()-1 ? "" : "\n");
            }
            imprimible += (i < clusters.length -1 ? "\n" : "");
        }
        System.out.println(imprimible);
    }

    public void imprimirPalabras(HashMap <String, String> asociacion){
        String imprimible = "";
        for (int i = 0; i < clusters.length; i++){
            imprimible += "Cluster " + (i + 1) + "\nPalabras en el cluster:\n";
            for (int j = 0; j < clusters[i].getPuntos().size(); j++){
                imprimible += asociacion.get(clusters[i].getPuntos().get(j).toString());
                imprimible += (i == clusters.length-1 && j == clusters[i].getPuntos().size()-1 ? "" : "\n");
            }
            imprimible += (i < clusters.length -1 ? "\n" : "");
        }
        System.out.println(imprimible);
    }
}