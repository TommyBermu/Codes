package Parcial_2_1;

public abstract class Vuelo {
    private int numero;
    private String origen, destino, fecha;
    private Avion avion;
    private double precio;

    protected Vuelo() {}

    protected Vuelo(int numero, String origen, String destino, String fecha, Avion avion, double precio) {
        this.numero = numero;
        this.origen = origen;
        this.destino = destino;
        this.fecha = fecha;
        this.avion = avion;
        this.precio = precio;
        System.out.println("Vuelo creado con exito.");
    }

    protected int getNumero() {
        return numero;
    }

    protected void setNumero(int numero) {
        this.numero = numero;
    }

    protected String getOrigen() {
        return origen;
    }

    protected void setOrigen(String origen) {
        this.origen = origen;
    }

    protected String getDestino() {
        return destino;
    }

    protected void setDestino(String destino) {
        this.destino = destino;
    }

    protected String getFecha() {
        return fecha;
    }

    protected void setFecha(String fecha) {
        this.fecha = fecha;
    }

    protected Avion getAvion() {
        return avion;
    }

    protected void setAvion(Avion avion) {
        this.avion = avion;
    }

    protected double getPrecio() {
        return precio;
    }

    protected void setPrecio(double precio) {
        this.precio = precio;
    }

    @Override
    public String toString() {
        return "Origen: " + origen + ", Destino: " + destino + ", Fecha: " + fecha + ", Empresa: " + this.avion.getEmpresa() + ", Precio: " + precio;
    }
}