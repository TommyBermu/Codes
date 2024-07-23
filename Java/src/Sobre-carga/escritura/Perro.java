package SobrecargaySobreescritura;

public class Perro extends Animal_escritura{

    // atributos

    // constructor

    // getters y setters
    
    // métodos

    @Override // la sobreescritura de métodos es simplemente agregar el @Override para que la instancia use el método a su manera
    public void hacer_sonido() {
        System.out.println("Soy un perro y ladro: Guau Guau");
    }
}
