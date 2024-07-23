package Pokemon;

public class Charmander extends Pokemon implements Fuego {

    public Charmander() {
    }

    public Charmander(int num_pokedex, int temporada, String nombre_pokemon, String sexo, String tipo, double peso) {
        super(num_pokedex, temporada, nombre_pokemon, sexo, tipo, peso);
    }

    // de la clase abstracta

    @Override
    protected void atacar_placaje() {
        System.out.println("Hola soy Charmander y estoy atacando con Placaje");
    }

    @Override
    protected void atacar_araniazo() {
        System.out.println("Hola soy Charmander y estoy atacando con Arañazo");
    }

    @Override
    protected void atacar_mordisco() {
        System.out.println("Hola soy Charmander y estoy atacando con Mordisco");
    }

    // de la interfaz

    @Override
    public void atacar_punio_fuego() {
        System.out.println("Hola soy Charmander y estoy atacando con Punio Fuego");
    }

    @Override
    public void atacar_ascuas() {
        System.out.println("Hola soy Charmander y estoy atacando con Ascuas");
    }

    @Override
    public void atacar_lanzallamas() {
        System.out.println("Hola soy Charmander y estoy atacando con Lanzallamas");
    }
}
