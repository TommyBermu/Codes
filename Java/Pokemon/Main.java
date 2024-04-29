package Pokemon;

public class Main {
    public static void main(String[] args) {
        Charmander charmander = new Charmander();
        Bulbasaur bulbasaur = new Bulbasaur();
        Squirtle squirtle = new Squirtle();
        Pikachu pikachu = new Pikachu();

        charmander.atacar_araniazo();
        charmander.atacar_lanzallamas();

        bulbasaur.atacar_placaje();
        bulbasaur.atacar_hoja_afilada();

        squirtle.atacar_mordisco();
        squirtle.atacar_hidrobomba();

        pikachu.atacar_araniazo();
        pikachu.atacar_rayo_carga();
    }
}
