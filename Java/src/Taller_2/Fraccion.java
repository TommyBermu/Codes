package Taller_2;

import java.util.ArrayList;

public class Fraccion implements Comparable<Fraccion> {
    long num_crudo, den_crudo, num, den, coc, res;
    private String signo;

    public Fraccion(long num, long den) {
        Asignar(num, den);
    }

    private void Asignar(long num, long den) {
        if (den == 0)
            throw new IllegalArgumentException("El denominador no puede ser 0");
        this.den_crudo = den;
        this.num_crudo = num;
        this.num = num < 0 ? -num : num;
        this.den = den < 0 ? -den : den;
        this.signo = num >= 0 ^ den >= 0 ? "-" : "";
        this.coc = this.num / this.den;
        this.res = this.num % this.den;
    }

    String Simplificado() {
        return "" + signo + (num != den && den != 1 ? num + "/" + den : (den != 0 ? num / den : num));
    }

    String Propio() {
        return "" + signo + num + "/" + den;
    }

    String Mixto() {
        return "" + signo + (coc != 0 ? coc : "") + (coc != 0 && res != 0 ? " " : "") + (res != 0 ? +res + "/" + den : "");
    }

    void Suma(Fraccion target) {
        simplificar();
        target.simplificar();
        Asignar(num_crudo * target.den_crudo + den_crudo * target.num_crudo, den_crudo * target.den_crudo);
    }

    static Fraccion Suma(Fraccion first, Fraccion second) {
        return new Fraccion(first.num_crudo * second.den_crudo + first.den_crudo * second.num_crudo, first.den_crudo * second.den_crudo);
    }

    void Resta(Fraccion target) {
        simplificar();
        target.simplificar();
        Asignar(num_crudo * target.den_crudo - den_crudo * target.num_crudo, den_crudo * target.den_crudo);
    }

    static Fraccion Resta(Fraccion first, Fraccion second) {
        return new Fraccion(first.num_crudo * second.den_crudo - first.den_crudo * second.num_crudo, first.den_crudo * second.den_crudo);
    }

    void Multiplicacion(Fraccion target) {
        simplificar();
        target.simplificar();
        Asignar(num_crudo * target.num_crudo, den_crudo * target.den_crudo);
    }

    static Fraccion Multiplicacion(Fraccion first, Fraccion second) {
        return new Fraccion(first.num_crudo * second.num_crudo, first.den_crudo * second.den_crudo);
    }

    void Division(Fraccion target) {
        simplificar();
        target.simplificar();
        Asignar(num_crudo * target.den_crudo, den_crudo * target.num_crudo);
    }

    static Fraccion Division(Fraccion first, Fraccion second) {
        return new Fraccion(first.num_crudo * second.den_crudo, first.den_crudo * second.num_crudo);
    }

    public double getValue() {
        return (double) num_crudo / (double) den_crudo;
    }

    boolean esMayor(Fraccion target) {
        return this.getValue() > target.getValue();
    }

    boolean esMenor(Fraccion target) {
        return this.getValue() < target.getValue();
    }

    boolean esIgual(Fraccion target) {
        return this.getValue() == target.getValue();
    }
    
    public void simplificar() {
        long div = GCD(den_crudo, res);
        Asignar(num_crudo / div, den_crudo / div);
    }

    private long GCD(long a, long b) {
        if (a == 0)
            return b;
        if (b == 0)
            return a;
        return GCD(b, a % b);
    }

    @Override
    public int compareTo(Fraccion fr) {
        if (esMayor(fr))
            return 1;
        if (esMenor(fr))
            return -1;
        return 0;
    }

    public String toString() {
        return Simplificado();
    }

    public static Fraccion sumatoria(ArrayList<Fraccion> lista) {
        Fraccion sum = new Fraccion(0, 1);
        for (Fraccion fraccion : lista) {
            sum.Suma(fraccion);
            sum.simplificar();
        }
        return sum;
    }
}
