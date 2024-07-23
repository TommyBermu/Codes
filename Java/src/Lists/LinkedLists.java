package Lists;
import java.util.List;
import java.util.LinkedList;

public class LinkedLists {
    public static void main(String[] args) {
        List <Persona> lista = new LinkedList<Persona>();
        lista.add(new Persona(1, "Luisina", 30));
        lista.add(new Persona(2, "Tomas", 18));
        lista.add(new Persona(3, "Gabriel", 24));
        lista.add(new Persona(4, "TodoCode", 2));

        lista.add(0, new Persona(5, "Sapo", 54));
        
        // el contenido de las LinkedList no se puede "ver" mediante sus índices, solo recorriendo la lista entera

        for (Persona pers: lista) {
            System.out.println("Prueba: " + pers.getNombre());
        }
    }    
}
