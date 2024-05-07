package Relaciones;

import java.util.ArrayList;

public class Relaciones {
    public static void main(String[] args) {
        Auto carro = new Auto();
        carro.setId(1542l);
        carro.setMarca("Renault");
        carro.setModelo("Duster");

        ArrayList<Propietario> listaPropietarios = new ArrayList<Propietario>();

        Propietario prop1 = new Propietario(11l, "Tomas", "Bermdudez");
        Propietario prop2 = new Propietario(12l, "Julian", "Velandia");

        listaPropietarios.add(prop1);
        listaPropietarios.add(prop2);

        carro.setListaPropietarios(listaPropietarios);

        System.out.println("El auto " + carro.getMarca() + " " + carro.getModelo() + " Tiene como propietarios a: " + carro.getListaPropietarios().toString());
    }
}
