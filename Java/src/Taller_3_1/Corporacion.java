package Taller_3_1;

import java.lang.Math;

public class Corporacion implements Cliente{
    private double ingresos;
    private double deudas;
    private int num_empleados;
    private double patrimonio;

    public Corporacion(double ingresos, double deudas, double patrimonio, int num_empleados) {
        this.ingresos = ingresos;
        this.deudas = deudas;
        this.num_empleados = num_empleados;
        this.patrimonio = patrimonio;
    }

    @Override
    public double calcularRiesgo() {
        return (deudas / (ingresos + patrimonio)) * (1.0 / Math.log(num_empleados + 1));
    }

    @Override
    public double capacidadDePago() {
        return (ingresos + patrimonio) - deudas - (num_empleados * 1000);
    }

    @Override
    public String aptoPrestamo() {
        if (calcularRiesgo() < 0.3 && capacidadDePago() > 20000){
            return "Apto";
        }
        return "No Apto";
    }

    @Override
    public String toString() {
        return "Gran Corporacion: Riesgo: " + calcularRiesgo() + ", Capacidad de Pago: " + capacidadDePago() + ", Resultado: " + aptoPrestamo();
    }
}
