package Pokemon;

public class Pikachu extends Pokemon implements Electrico {

    public Pikachu() {
    }

    public Pikachu(int num_pokedex, int temporada, String nombre_pokemon, String sexo, String tipo, double peso) {
        super(num_pokedex, temporada, nombre_pokemon, sexo, tipo, peso);
    }

    // de la clase abstracta

    @Override
    protected void atacar_placaje() {
        System.out.println("Hola soy Pikachu y estoy atacando con Placaje");
    }

    @Override
    protected void atacar_araniazo() {
        System.out.println("Hola soy Pikachu y estoy atacando con Arañazo");
    }

    @Override
    protected void atacar_mordisco() {
        System.out.println("Hola soy Pikachu y estoy atacando con Mordisco");
    }

    @Override
    public void atacar_impactrueno() {
        System.out.println("Hola soy Pikachu y estoy atacando con Impactrueno");
    }

    @Override
    public void atacar_punio_trueno() {
        System.out.println("Hola soy Pikachu y estoy atacando con Punio Trueno");
    }

    @Override
    public void atacar_rayo() {
        System.out.println("Hola soy Pikachu y estoy atacando con Rayo");
    }

    @Override
    public void atacar_rayo_carga() {
        System.out.println("Hola soy Pikachu y estoy atacando con Rayo Carga");
    }
}
