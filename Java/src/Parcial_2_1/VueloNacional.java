package Parcial_2_1;

public class VueloNacional extends Vuelo{
    static final double IMPUESTO = 1000;

    public VueloNacional() {
    }

    public VueloNacional(int numero, String origen, String destino, String fecha, Avion avion, double precio) {
        super(numero, origen, destino, fecha, avion, precio);
    }
}
