package Ejercicio_Plantas;

public abstract class Planta {
    private String nombre;
    private double alto_del_tallo;
    private boolean tiene_hojas;
    private String clima_ideal;

    public Planta() {
    }

    public Planta(String nombre, double alto_del_tallo, boolean tiene_hojas, String clima_ideal) {
        this.nombre = nombre;
        this.alto_del_tallo = alto_del_tallo;
        this.tiene_hojas = tiene_hojas;
        this.clima_ideal = clima_ideal;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public double getAlto_del_tallo() {
        return alto_del_tallo;
    }

    public void setAlto_del_tallo(double alto_del_tallo) {
        this.alto_del_tallo = alto_del_tallo;
    }

    public boolean isTiene_hojas() {
        return tiene_hojas;
    }

    public void setTiene_hojas(boolean tiene_hojas) {
        this.tiene_hojas = tiene_hojas;
    }

    public String getClima_ideal() {
        return clima_ideal;
    }

    public void setClima_ideal(String clima_ideal) {
        this.clima_ideal = clima_ideal;
    }

    protected abstract void saludar();
}
