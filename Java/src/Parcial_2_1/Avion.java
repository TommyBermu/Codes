package Parcial_2_1;

public class Avion {
    private String empresa;
    private Silla[] capacidad_economica;
    private Silla[] capacidad_ejecutiva;

    public Avion() {
    }

    public Avion(String empresa, int capacidad) {
        this.empresa = empresa;
        this.capacidad_economica = new Silla[(int)(capacidad * 0.6)];
        this.capacidad_ejecutiva = new Silla[(int)(capacidad * 0.4)];
        System.out.println("Avion registrado con exito.");
    }

    public String getEmpresa() {
        return empresa;
    }

    public void setEmpresa(String empresa) {
        this.empresa = empresa;
    }

    public int getCapacidad_economica() {
        return capacidad_economica.length;
    }
    
    public int getCapacidad_ejecutiva() {
        return capacidad_ejecutiva.length;
    }

    public void agregarSilla_economica(int cantidad_sillas) {
        for (int i = 0; i < cantidad_sillas; i++)
            capacidad_economica[i] = new Silla("Economica");
    }

    public void agregarSilla_ejecutiva(int cantidad_sillas) {
        for (int i = 0; i < cantidad_sillas; i++)
            capacidad_ejecutiva[i] = new Silla("Ejecutiva");
    }
}