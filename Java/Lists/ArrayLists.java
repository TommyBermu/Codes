package Lists;
import java.util.List;
import java.util.ArrayList;

public class ArrayLists {
    public static void main(String[] args) {
        List <Persona> lista = new ArrayList<Persona>();
        lista.add(new Persona(1, "Luisina", 30));
        lista.add(new Persona(2, "Tomas", 18));
        lista.add(new Persona(3, "Gabriel", 24));
        lista.add(new Persona(4, "TodoCode", 2));
        
        for (short i = 0; i < lista.size(); i++) {
            System.out.println(lista.get(i).getEdad());
        }

        for (Persona pers: lista) {
            System.out.println("Prueba: " + pers.getNombre());
        }
    }    
}
