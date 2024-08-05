package Parcial_1;

import java.lang.Math;
import java.util.Arrays;

public class Punto {
    private double[] posicion;

    public Punto(double[] posicion){
        this.posicion = posicion;
    }

    public int getDimension(){
        return this.posicion.length;
    }

    public void setPosicion(double[] posicion){
        this.posicion = posicion;
    }

    public double getCoord(int numCoord){

        return this.posicion[numCoord];
    }

    public double calcularMinkowski(Punto comparable, double p){
        double sumatoria = 0.0;
        for (int i = 0; i < this.posicion.length; i++){
            sumatoria += Math.pow(Math.abs(comparable.getCoord(i) - this.getCoord(i)), p);
        }
        return Math.pow(sumatoria, 1.0/p);
    }

    public String toString(){
        return Arrays.toString(posicion);
    }
}