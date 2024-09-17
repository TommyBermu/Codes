package Parcial_2_1;

public class Reserva {
    private Vuelo vuelo;
    private int cant_sillas;
    private String clase;
    private Pasajero pasajero;

    public Reserva() {
    }
    
    public Reserva(VueloNacional vuelo, int cant_sillas, String clase, Pasajero pasajero) {
        this.vuelo = vuelo;
        this.cant_sillas = cant_sillas;
        this.clase = clase;
        this.pasajero = pasajero;
        boolean cls = clase.equals("Economica");
        System.out.println("Reserva creada con exito. Precio total: $" + ((VueloNacional.IMPUESTO + vuelo.getPrecio() * (cls ? 1 : 2.5)) * cant_sillas));
    }

    public Reserva(VueloInternacional vuelo, int cant_sillas, String clase, Pasajero pasajero) {
        this.vuelo = vuelo;
        this.cant_sillas = cant_sillas;
        this.clase = clase;
        this.pasajero = pasajero;
        boolean cls = clase.equals("Economica");
        System.out.println("Reserva creada con exito. Precio total: $" + ((VueloInternacional.IMPUESTO + vuelo.getPrecio() * (cls ? 1 : 2.5)) * cant_sillas));
    }

    public Vuelo getVuelo() {
        return vuelo;
    }

    public void setVuelo(Vuelo vuelo) {
        this.vuelo = vuelo;
    }

    public int getCant_sillas() {
        return cant_sillas;
    }

    public void setCant_sillas(int cant_sillas) {
        this.cant_sillas = cant_sillas;
    }

    public String getClase() {
        return clase;
    }

    public void setClase(String clase) {
        this.clase = clase;
    }

    public Pasajero getPasajero() {
        return pasajero;
    }

    public void setPasajero(Pasajero pasajero) {
        this.pasajero = pasajero;
    }
}
