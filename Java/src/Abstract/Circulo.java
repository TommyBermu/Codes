package Abstract;

public class Circulo extends Figura {

    private double radio;

    public Circulo() {
    }

    public Circulo(double radio, double x, double y) {
        super(x, y);
        this.radio = radio;
    }

    @Override
    public double calcular_area() {
        double pi = 3.14159;
        return pi * radio * radio;
    }
}