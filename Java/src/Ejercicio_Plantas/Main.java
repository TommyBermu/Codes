package Ejercicio_Plantas;

public class Main {
    public static void main(String[] args) {
        Arbol arbol = new Arbol("Roble", 10.5, true, "Templado", "Roble común", "Frondoso", 0.3, "Verde oscuro", "Caducas");
        Flor flor = new Flor("Orquídea", 0.5, false, "Tropical", "Blanco", 6, "Amarillo", "Phalaenopsis", "Verano");
        Arbusto arbusto = new Arbusto("Forsythia", 1.8, true, "Templado",2.5, true, "Forsythia intermedia","Verde", true);

        arbol.saludar();
        flor.saludar();
        arbusto.saludar();


    }
}
