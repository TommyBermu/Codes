package Taller_3_1;

public class Natural implements Cliente{
    private double ingresos;
    private double deudas;

    public Natural(double ingresos, double deudas) {
        this.ingresos = ingresos;
        this.deudas = deudas;
    }

    @Override
    public double calcularRiesgo() {
        return deudas / ingresos;
    }

    @Override
    public double capacidadDePago() {
        return ingresos - deudas;
    }

    @Override
    public String aptoPrestamo() {
        if (calcularRiesgo() < 0.5 && capacidadDePago() > 1000){
            return "Apto";
        }
        return "No Apto";
    }

    @Override
    public String toString() {
        return "Persona Natural: Riesgo: " + calcularRiesgo() + ", Capacidad de Pago: " + capacidadDePago() + ", Resultado: " + aptoPrestamo();
    }
}
