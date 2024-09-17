package Parcial_2_1;

import java.util.*;

public class VueloInternacional extends Vuelo {
    static final double IMPUESTO = 2500;
    private ArrayList<String> escalas;

    public VueloInternacional() {
    }

    public VueloInternacional(int numero, String origen, String destino, String fecha, Avion avion, String escalas, double precio) {
        super(numero, origen, destino, fecha, avion, precio);
        this.escalas = new ArrayList<String>(Arrays.asList(escalas.split(",")));
    }

    public String getEscalas() {
        return escalas.toString();
    }

    public void setEscalas(String escalas) {
        this.escalas = new ArrayList<String>(Arrays.asList(escalas.split(",")));
    }
}