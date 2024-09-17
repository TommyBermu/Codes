package Parcial_2_2;

public abstract class Personaje{
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