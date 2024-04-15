package Abstract;

public abstract class Figura {
    protected double x; // posicion en x
    protected double y; // posicion en y

    protected Figura(){}

    protected Figura(double x, double y){
        this.x = x;
        this.y = y;
    }

    public abstract double calcular_area();
}
