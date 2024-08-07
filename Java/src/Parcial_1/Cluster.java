package Parcial_1;

import java.util.ArrayList;

public class Cluster {
    private Punto centro;
    private ArrayList<Punto> puntos = new ArrayList<>();

    public Cluster(Punto centro) {
        this.centro = centro;
    }

    public Punto getCentro() {
        return centro;
    }

    public void setCentro(Punto centro) {
        this.centro = centro;
    }

    public ArrayList<Punto> getPuntos() {
        return puntos;
    }

    public void setPuntos(ArrayList<Punto> puntos) {
        this.puntos = puntos;
    }

    public void addPunto(Punto punto) {
        puntos.add(punto);
    }

    public void actualizarCentroide() {
        if (!puntos.isEmpty()) {
            int dimension = centro.getDimension();
            double[] posicion = new double[dimension];
            for (int i = 0; i < dimension; i++) {
                double prom = 0.0;
                for (Punto iPunto : puntos) {
                    prom += iPunto.getCoord(i);
                }
                posicion[i] = prom / (double) puntos.size();
            }
            centro.setPosicion(posicion);
        }
    }

    public void reiniciarPuntos() {
        puntos.clear();
    }

    public String toString() {
        return "Centroide: " + centro.toString();
    }
}