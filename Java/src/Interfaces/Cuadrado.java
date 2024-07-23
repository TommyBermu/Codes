package Interfaces;

public class Cuadrado implements Figura, Dibujable{

    private double lado;

    public Cuadrado(){}

    public Cuadrado(double lado, double x, double y){
        this.lado = lado;
    }

    @Override
    public double calcular_area() {
        return lado * lado;
    }

    @Override
    public void dibujar() {
        System.out.println("Estoy dibujando un cuadrado");
    }
}