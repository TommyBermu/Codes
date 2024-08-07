package Parcial_1;

import java.lang.Math;
import java.util.Arrays;

public class Punto {
    private double[] posicion;
    private String palabra;

    public Punto(double[] posicion) {
        this.posicion = posicion;
    }

    public Punto(String palabra) {
        this.palabra = palabra;
        double[] coordenadas = { 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0 };
        for (Character c : palabra.toCharArray()) {
            if ((int) c <= 122 && (int) c >= 97)
                coordenadas[(int) c - 97]++;
        }
        this.posicion = coordenadas;
    }

    public int getDimension() {
        return posicion.length;
    }

    public String getPalabra() {
        return palabra;
    }

    public void setPosicion(double[] posicion) {
        this.posicion = posicion;
    }

    public double getCoord(int numCoord) {
        return posicion[numCoord];
    }

    public double calcularMinkowski(Punto comparable, int p) {
        double sumatoria = 0.0;
        for (int i = 0; i < posicion.length; i++) {
            sumatoria += Math.pow(Math.abs(comparable.getCoord(i) - this.getCoord(i)), p);
        }
        return Math.pow(sumatoria, 1.0 / p);
    }

    public String toString() {
        return Arrays.toString(posicion);
    }
}