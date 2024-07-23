package Ejercicio_Plantas;

public class Arbusto extends Planta {

    private double ancho_arbusto;
    private boolean es_domestico;
    private String variedad_arbusto;
    private String color_hojas;
    private boolean podable;
    
    public Arbusto() {
    }

    public Arbusto(String nombre, double alto_del_tallo, boolean tiene_hojas, String clima_ideal, double ancho_arbusto,
            boolean es_domestico, String variedad_arbusto, String color_hojas, boolean podable) {
        super(nombre, alto_del_tallo, tiene_hojas, clima_ideal);
        this.ancho_arbusto = ancho_arbusto;
        this.es_domestico = es_domestico;
        this.variedad_arbusto = variedad_arbusto;
        this.color_hojas = color_hojas;
        this.podable = podable;
    }

    public double getAncho_arbusto() {
        return ancho_arbusto;
    }

    public void setAncho_arbusto(double ancho_arbusto) {
        this.ancho_arbusto = ancho_arbusto;
    }

    public boolean isEs_domestico() {
        return es_domestico;
    }

    public void setEs_domestico(boolean es_domestico) {
        this.es_domestico = es_domestico;
    }

    public String getVariedad_arbusto() {
        return variedad_arbusto;
    }

    public void setVariedad_arbusto(String variedad_arbusto) {
        this.variedad_arbusto = variedad_arbusto;
    }

    public String getColor_hojas() {
        return color_hojas;
    }

    public void setColor_hojas(String color_hojas) {
        this.color_hojas = color_hojas;
    }

    public boolean isPodable() {
        return podable;
    }

    public void setPodable(boolean podable) {
        this.podable = podable;
    }

    @Override
    public void saludar() {
        System.out.println("Hola, soy un arbusto");        
    }
}
