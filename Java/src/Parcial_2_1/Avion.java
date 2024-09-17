package Parcial_2_1;

public class Avion {
    private String empresa;
    private Silla[] capacidad_economica;
    private int e_pointer = 0; 
    private Silla[] capacidad_ejecutiva;
    private int c_pointer = 0;

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

    public boolean agregarSilla_economica(int cantidad_sillas) {
        try{
            for (int i = 0; i < cantidad_sillas; i++) {
                capacidad_economica[e_pointer] = new Silla("Economica");
                e_pointer++;
            }
            return true;
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Error al crear la reserva: No hay suficientes asientos disponibles.");
            return false;
        }
    }

    public boolean agregarSilla_ejecutiva(int cantidad_sillas) {
        try {
            for (int i = 0; i < cantidad_sillas; i++) {
                capacidad_ejecutiva[c_pointer] = new Silla("Ejecutiva");
                c_pointer++;
            }
            return true;
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Error al crear la reserva: No hay suficientes asientos disponibles.");
            return false;
        }
    }
}