package Pokemon;

public abstract class Pokemon {

    protected int num_pokedex, temporada;
    protected String nombre_pokemon, sexo, tipo;
    protected double peso;

    protected Pokemon() {
    }

    protected Pokemon(int num_pokedex, int temporada, String nombre_pokemon, String sexo, String tipo, double peso) {
        this.num_pokedex = num_pokedex;
        this.temporada = temporada;
        this.nombre_pokemon = nombre_pokemon;
        this.sexo = sexo;
        this.tipo = tipo;
        this.peso = peso;
    }

    protected abstract void atacar_placaje();

    protected abstract void atacar_araniazo();

    protected abstract void atacar_mordisco();

}
