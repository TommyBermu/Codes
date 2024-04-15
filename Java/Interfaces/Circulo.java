package Interfaces;

public class Circulo implements Figura, Dibujable, Rotable{

    private double radio;

    public Circulo(){}

    public Circulo(double radio, double x, double y){
        this.radio = radio;
    }

    @Override
    public double calcular_area() {
        double pi = 3.14159;
        return pi * radio * radio;
    }

    @Override
    public void dibujar() {
        System.out.println("Estoy dibujando un circulo");
    }

    @Override
    public void rotar() {
        System.out.println("Estoy rotando un circulo");
    }
}