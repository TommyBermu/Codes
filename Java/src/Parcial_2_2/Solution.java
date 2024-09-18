package Parcial_2_2;

import java.util.*;

public class Solution {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        boolean ganador = false;

        System.out.println("Comienza la batalla");

        String nombre_heroe = sc.nextLine();
        int vida_heroe = sc.nextInt(); sc.nextLine();
        int dano_heroe = sc.nextInt(); sc.nextLine();
        int creditos_heroe = sc.nextInt(); sc.nextLine();
        Heroe heroe = new Heroe(nombre_heroe, vida_heroe, dano_heroe, creditos_heroe);

        String nombre_villano = sc.nextLine();
        int vida_villano = sc.nextInt(); sc.nextLine();
        int dano_villano = sc.nextInt(); sc.nextLine();
        Villano villano = new Villano(nombre_villano, vida_villano, dano_villano);

        while (!ganador){
            int opcion = sc.nextInt();
            if (sc.hasNextLine())
                sc.nextLine();

            switch (opcion){
                case 1:
                    heroe.atacar(villano);
                    break;
                case 2:
                    String habilidad = sc.nextLine();
                    if (habilidad.equals("aumentar_vida")){
                        heroe.aumentarVida();
                    } else if (habilidad.equals("duplicar_ataque")){
                        heroe.duplicarDano();
                    }
                    break;
                case 3:
                    String compra = sc.nextLine();
                    if (compra.equals("aumentar_vida")){
                        heroe.comprarVida();
                    } else if (compra.equals("duplicar_ataque")){
                        heroe.comprarDano();
                    }
                    break;
            }

            if (villano.getVida() <= 0){
                System.out.println("Has derrotado al villano.");
                ganador = true;
            } else {
                villano.atacar(heroe);
                if (heroe.getVida() <= 0){
                    System.out.println("El heroe ha sido derrotado.");
                    ganador = true;
                }
            }
        }
        sc.close();
    }
}

abstract class Personaje{
    protected String nombre;
    protected int vida, dano;

    public Personaje(String nombre, int vida, int dano) {
        this.nombre = nombre;
        this.vida = vida;
        this.dano = dano;
        System.out.println(nombre + ": Vida = " + vida);
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getVida() {
        return vida;
    }

    public void setVida(int vida) {
        this.vida = vida;
    }

    public int getDano() {
        return dano;
    }

    public void setDano(int dano) {
        this.dano = dano;
    }

    public void atacar(Personaje p) {
        p.setVida(p.getVida() - this.dano);
        System.out.println(p.getNombre() + " recibe " + this.dano + " puntos de dano. Vida restante: " + p.getVida());
    }
}

class Heroe extends Personaje implements Habilidades, Tienda{
    private int creditos;
    private int heals = 2;
    private int doubles = 2;

    public Heroe(String nombre, int vida, int dano, int creditos) {
        super(nombre, vida, dano);
        this.creditos = creditos;
    }

    public int getCreditos() {
        return creditos;
    }

    public void setCreditos(int creditos) {
        this.creditos = creditos;
    }

    public int getHeals() {
        return heals;
    }

    public int getDoubles() {
        return doubles;
    }

    @Override
    public void comprarDano(){
        if (creditos >= 20){
            doubles++;
            creditos -= 20;
            System.out.println("Has comprado la habilidad duplicar_ataque");
        } else {
            System.out.println("No tienes los suficientes creditos para comprar esta habilidad. Perdiste el turno.");
        }
    }

    @Override
    public void comprarVida(){
        if (creditos >= 40){
            heals++;
            creditos -= 40;
            System.out.println("Has comprado la habilidad aumentar_vida");
        } else {
            System.out.println("No tienes los suficientes creditos para comprar esta habilidad. Perdiste el turno.");
        }
    }

    @Override
    public void duplicarDano() {
        if (doubles > 0) {
            dano = dano * 2;
            doubles--;
            System.out.println(nombre + " duplica su ataque. Ataque actual: " + dano);
        } else {
            System.out.println("No tienes mas habilidades disponibles, debes comprar. Perdiste el turno.");
        }
    }

    @Override
    public void aumentarVida() {
        if (heals > 0) {
            vida += 50;
            heals--;
            System.out.println(nombre + " aumenta su vida. Vida actual: " + vida);
        } else {
            System.out.println("No tienes mas habilidades disponibles, debes comprar. Perdiste el turno.");
        }
    }

}

class Villano extends Personaje {
    public Villano(String nombre, int vida, int dano) {
        super(nombre, vida, dano);
    }
}

interface Habilidades {
    public void aumentarVida();

    public void duplicarDano();
}

interface Tienda {
    public void comprarDano();
    
    public void comprarVida();
}