package Parcial_1;

import java.util.ArrayList;

public class Cluster {
    private Punto centro;
    private ArrayList <Punto> puntos = new ArrayList<Punto>();

    public Cluster(Punto centro){
        this.centro = centro;
    }

    public Punto getCentro(){
        return centro;
    }

    public void setCentro(Punto centro){
        this.centro = centro;
    }

    public ArrayList<Punto> getPuntos(){
        return puntos;
    }

    public void setPuntos(ArrayList <Punto> puntos){
        this.puntos = puntos;
    }

    public void addPunto(Punto punto){
        this.puntos.add(punto);
    }

    public void actualizarCentroide(){
        ArrayList<Double> posicion = new ArrayList<Double>();
        double prom = 0.0;
        for (int i = 0; i < centro.getDimension(); i++){
            for (Punto iPunto: puntos) {
                prom += iPunto.getCoord(i);
            }
            posicion.add(prom / puntos.size());
        }
        this.centro.setPosicion(posicion);
    }

    public void reiniciarPuntos(){
        this.puntos.clear();
    }

    public String toString(){
        return puntos.toString();
    }
}