package Lists;
import java.util.HashMap;
import java.util.Map;

public class HashMaps {
    public static void main(String[] args) {
        Map<Integer, String> empleados = new HashMap<>();

        empleados.put(1523, "Sergio");
        empleados.put(1524, "Sneheider");
        empleados.put(1525, "Daniel");
        empleados.put(1526, "Julian");

        System.out.println(empleados.containsValue("Julian"));
        System.out.println(empleados.containsKey(1523));

        System.out.println(empleados.keySet());
        System.out.println(empleados.values());

        System.out.println(empleados.get(1526));
        empleados.remove(1523);
    }
}
