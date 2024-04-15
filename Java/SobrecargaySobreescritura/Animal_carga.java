package SobrecargaySobreescritura;

public class Animal_carga {
    // atributos

    // constructores

    // getters y setters

    // otros métodos
    public void hacer_sonido() {       
    }

    public void hacer_sonido(String nombre_animal) {
        System.out.println("El animal " + nombre_animal + " hace un sonido");
    }

    public void hacer_sonido(String nombre_animal, String tipo_sonido) {
        System.out.println("El animal " + nombre_animal + " hace un sonido de tipo " + tipo_sonido);
    }

    // la sobrecarga de métodos se da cuando se usa el mismo nombre de método pero con parámetros distintos, lo que hace que se use uno u otro dependiendo de los parámetros de entrada
}
