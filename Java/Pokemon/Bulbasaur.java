package Pokemon;

public class Bulbasaur extends Pokemon implements Planta {

    public Bulbasaur() {
    }

    public Bulbasaur(int num_pokedex, int temporada, String nombre_pokemon, String sexo, String tipo, double peso) {
        super(num_pokedex, temporada, nombre_pokemon, sexo, tipo, peso);
    }

    // de la clase abstracta

    @Override
    protected void atacar_placaje() {
        System.out.println("Hola soy Bulbasaur y estoy atacando con Placaje");
    }

    @Override
    protected void atacar_araniazo() {
        System.out.println("Hola soy Bulbasaur y estoy atacando con Arañazo");
    }

    @Override
    protected void atacar_mordisco() {
        System.out.println("Hola soy Bulbasaur y estoy atacando con Mordisco");
    }

    @Override
    public void atacar_paralizar() {
        System.out.println("Hola soy Bulbasaur y estoy atacando con Paralizar");
    }

    @Override
    public void atacar_drenaje() {
        System.out.println("Hola soy Bulbasaur y estoy atacando con Drenaje");
    }

    @Override
    public void atacar_hoja_afilada() {
        System.out.println("Hola soy Bulbasaur y estoy atacando con Hoja Afilada");
    }

    @Override
    public void atacar_latigo_cepa() {
        System.out.println("Hola soy Bulbasaur y estoy atacando con Latigo Cepa");
    }
}
