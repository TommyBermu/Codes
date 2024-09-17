package Parcial_2_1;

public class Pasajero {
    private String nombres, apellidos;
    private int documento;

    public Pasajero() {
    }

    public Pasajero(String nombres, String apellidos, int documento) {
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.documento = documento;
        System.out.println("Pasajero registrado con exito.");
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public int getDocumento() {
        return documento;
    }

    public void setDocumento(int documento) {
        this.documento = documento;
    }

    @Override
    public String toString() {
        return "Nombres: " + nombres + ", Apellidos: " + apellidos + ", Documento: " + documento;
    }
}