package Taller_3_1;

public class Empresa implements Cliente{
    private double ingresos;
    private double deudas;
    private int num_empleados;

    public Empresa(double ingresos, double deudas, int num_empleados) {
        this.ingresos = ingresos;
        this.deudas = deudas;
        this.num_empleados = num_empleados;
    }

    @Override
    public double calcularRiesgo() {
        return (deudas / ingresos) * (1.0/num_empleados);
    }

    @Override
    public double capacidadDePago() {
        return ingresos - deudas - num_empleados * 500;
    }

    @Override
    public String aptoPrestamo() {
        if (calcularRiesgo() < 0.4 && capacidadDePago() > 5000){
            return "Apto";
        }
        return "No Apto";
    }

    @Override
    public String toString() {
        return "Empresa Pequena: Riesgo: " + calcularRiesgo() + ", Capacidad de Pago: " + capacidadDePago() + ", Resultado: " + aptoPrestamo();
    }
}
