package Parcial_2_1;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Main {
    static HashMap<Integer, Vuelo> vuelos = new HashMap<>();
    static HashMap<String, Avion> aviones = new HashMap<>();
    static ArrayList<Reserva> reservas = new ArrayList<>();
    static HashMap<String, Pasajero> pasajeros = new HashMap<>();
    public static void main(String[] args) throws FileNotFoundException {
        Scanner sc = new Scanner(new File("Java/src/Parcial_2_1/input1.txt"));
        boolean exit = false;
        
        while (!exit) {
            //System.out.println("\n1. Registrar un avion\n2. Registrar un pasajero\n3. Crear una reserva\n4. Crear un vuelo nacional\n5. Crear un vuelo internacional\n6. Mostrar la informacion del vuelo con el numero de Vuelo\n0. Salir\n");
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

                    Avion avion = vuelos.get(numero_vuelo).getAvion();

                    sc.nextLine();
                    try {
                        if (tipo_vuelo == 1) {
                            if (clase.equals("Economica")) 
                                avion.agregarSilla_economica(cant_sillas); 
                            else
                                avion.agregarSilla_ejecutiva(cant_sillas);
                            reservas.add(new Reserva((VueloNacional)vuelos.get(numero_vuelo), cant_sillas, clase, pasajeros.get(nombre_pasajero))); 
                        }
                        else if (tipo_vuelo == 2){
                            if (clase.equals("Economica")) 
                                avion.agregarSilla_economica(cant_sillas); 
                            else
                                avion.agregarSilla_ejecutiva(cant_sillas);
                            reservas.add(new Reserva((VueloInternacional)vuelos.get(numero_vuelo), cant_sillas, clase, pasajeros.get(nombre_pasajero)));
                        }
                    } catch (ArrayIndexOutOfBoundsException e) {
                        System.out.println("Error al crear la reserva: No hay suficientes asientos disponibles.");
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
}