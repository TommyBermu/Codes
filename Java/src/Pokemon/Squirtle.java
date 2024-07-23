package Pokemon;

public class Squirtle extends Pokemon implements Agua {

    public Squirtle() {
    }

    public Squirtle(int num_pokedex, int temporada, String nombre_pokemon, String sexo, String tipo, double peso) {
        super(num_pokedex, temporada, nombre_pokemon, sexo, tipo, peso);
    }

    // de la clase abstracta

    @Override
    protected void atacar_placaje() {
        System.out.println("Hola soy Squirtle y estoy atacando con Placaje");
    }

    @Override
    protected void atacar_araniazo() {
        System.out.println("Hola soy Squirtle y estoy atacando con Arañazo");
    }

    @Override
    protected void atacar_mordisco() {
        System.out.println("Hola soy Squirtle y estoy atacando con Mordisco");
    }

    @Override
    public void atacar_hidrobomba() {
        System.out.println("Hola soy Squirtle y estoy atacando con Hidrobomba");
    }

    @Override
    public void atacar_pistola_agua() {
        System.out.println("Hola soy Squirtle y estoy atacando con Pistola Agua");
    }

    @Override
    public void atacar_burbuja() {
        System.out.println("Hola soy Squirtle y estoy atacando con Burbuja");
    }

    @Override
    public void atacar_hidropulso() {
        System.out.println("Hola soy Squirtle y estoy atacando con Hidropulso");
    }
}
