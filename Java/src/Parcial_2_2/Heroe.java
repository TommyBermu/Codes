package Parcial_2_2;

public class Heroe extends Personaje implements Habilidades, Tienda{
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