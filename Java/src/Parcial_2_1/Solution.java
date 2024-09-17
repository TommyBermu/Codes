package Parcial_2_1;

import java.util.*;

public class Solution {
    static HashMap<Integer, Vuelo> vuelos = new HashMap<>();
    static HashMap<String, Avion> aviones = new HashMap<>();
    static ArrayList<Reserva> reservas = new ArrayList<>();
    static HashMap<String, Pasajero> pasajeros = new HashMap<>();
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        boolean exit = false;
        
        while (!exit) {
            short option = sc.nextShort();
            if (sc.hasNext())
                sc.nextLine();
            switch (option) {
                case 0:
                    exit = true;
                    break;
                case 1:
                    // Registrar un avion

                    String empresa = sc.nextLine();
                    aviones.put(empresa, new Avion(empresa, sc.nextInt()));
                    sc.nextLine();
                    break;
                case 2:
                    // Registrar un pasajero
                    String nombre = sc.nextLine();
                    pasajeros.put(nombre, new Pasajero(nombre, sc.nextLine(), sc.nextInt()));
                    sc.nextLine();
                    break;
                case 3:
                    // Crear una reserva

                    int tipo_vuelo = sc.nextInt();
                    sc.nextLine();

                    int numero_vuelo = sc.nextInt();
                    sc.nextLine();
                    String clase = sc.nextLine();
                    String nombre_pasajero = sc.nextLine();
                    int cant_sillas = sc.nextInt();
                    sc.nextLine();

                    Avion avion = aviones.get(vuelos.get(numero_vuelo).getAvion().getEmpresa());

                    if (clase.equals("Economica")) {
                        if(avion.agregarSilla_economica(cant_sillas))
                            procesarReserva(cant_sillas, numero_vuelo, clase, pasajeros.get(nombre_pasajero), tipo_vuelo);
                    }
                    else if (clase.equals("Ejecutiva")){
                        if(avion.agregarSilla_ejecutiva(cant_sillas))
                            procesarReserva(cant_sillas, numero_vuelo, clase, pasajeros.get(nombre_pasajero), tipo_vuelo);
                    }
                    
                    break;
                case 4:
                    // Crear un vuelo nacional

                    String empresa_n = sc.nextLine();
                    int numero_vuelo_n = sc.nextInt();
                    sc.nextLine();
                    vuelos.put(numero_vuelo_n, new VueloNacional(numero_vuelo_n, sc.nextLine(), sc.nextLine(), sc.nextLine(), aviones.get(empresa_n), sc.nextDouble()));
                    sc.nextLine();
                    break;
                case 5:
                    // Crear un vuelo internacional

                    String empresa_i = sc.nextLine();              
                    int numero_vuelo_i = sc.nextInt();
                    sc.nextLine();
                    vuelos.put(numero_vuelo_i, new VueloInternacional(numero_vuelo_i, sc.nextLine(), sc.nextLine(), sc.nextLine(), aviones.get(empresa_i), sc.nextLine(), sc.nextDouble()));
                    sc.nextLine();
                    break;
                case 6:
                    // Mostrar la informacion del vuelo con el numero de Vuelo
                    System.out.println(vuelos.get(sc.nextInt()));
                    sc.nextLine();
                    break;
            }
        }
        sc.close();
    }

    public static void procesarReserva(int cant_sillas, int numero_vuelo, String clase, Pasajero pasajero, int tipo_vuelo){
        if (tipo_vuelo == 1){
            reservas.add(new Reserva((VueloNacional)vuelos.get(numero_vuelo), cant_sillas, clase, pasajero));
        } else if (tipo_vuelo == 2){
            reservas.add(new Reserva((VueloInternacional)vuelos.get(numero_vuelo), cant_sillas, clase, pasajero));
        }
    }
}

class Avion {
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

    public int getCapacidad_ejecutiva() {
        return capacidad_ejecutiva.length;
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

class Silla {
    private String clase;

    public Silla() {
    }

    public Silla(String clase) {
        this.clase = clase;
    }

    public String getClase() {
        return clase;
    }

    public void setClase(String clase) {
        this.clase = clase;
    }
}


class Pasajero {
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

class Reserva {
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

abstract class Vuelo {
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

class VueloNacional extends Vuelo{
    static final double IMPUESTO = 1000;

    public VueloNacional() {
    }

    public VueloNacional(int numero, String origen, String destino, String fecha, Avion avion, double precio) {
        super(numero, origen, destino, fecha, avion, precio);
    }
}

class VueloInternacional extends Vuelo {
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