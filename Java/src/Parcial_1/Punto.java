package Parcial_1;

import java.util.ArrayList;
import java.lang.Math;

public class Punto {
    private ArrayList <Double> posicion;

    public Punto(ArrayList <Double> posicion){
        //constructor
        this.posicion = posicion;
    }

    public int getDimension(){
        return posicion.size();
    }

    public void setPosicion(ArrayList <Double> posicion){
        //setter for the coords of the Point
        this.posicion = posicion;
    }

    public Double getCoord(int numCoord){
        //return the specific coord from the ArrayList
        return this.posicion.get(numCoord);
    }

    public Double calcularMinkowski(Punto comparable, int p){
        //calculate the Minkowski distance = (Σ i=1->n (|xi-yi|^p))^(1/p)
        Double sumatoria = 0.0;
        for (int i = 0; i < this.posicion.size(); i++){
            sumatoria += Math.pow(Math.abs(comparable.getCoord(i) - this.getCoord(i)), p);
        }
        return Math.pow(sumatoria, 1.0/p);
    }

    public String toString(){
        return posicion.toString();
    }
}
