package Ejercicio_Plantas;

public class Flor extends Planta {

    private String color_de_petalos;
    private int cantidad_de_petalos;
    private String color_del_pistilo;
    private String variedad_de_flor;
    private String estacion_florece;

    public Flor() {
    }

    public Flor(String nombre, double alto_del_tallo, boolean tiene_hojas, String clima_ideal, String color_de_petalos,
            int cantidad_de_petalos, String color_del_pistilo, String variedad_de_flor, String estacion_florece) {
        super(nombre, alto_del_tallo, tiene_hojas, clima_ideal);
        this.color_de_petalos = color_de_petalos;
        this.cantidad_de_petalos = cantidad_de_petalos;
        this.color_del_pistilo = color_del_pistilo;
        this.variedad_de_flor = variedad_de_flor;
        this.estacion_florece = estacion_florece;
    }

    public String getColor_de_petalos() {
        return color_de_petalos;
    }

    public void setColor_de_petalos(String color_de_petalos) {
        this.color_de_petalos = color_de_petalos;
    }

    public int getCantidad_de_petalos() {
        return cantidad_de_petalos;
    }

    public void setCantidad_de_petalos(int cantidad_de_petalos) {
        this.cantidad_de_petalos = cantidad_de_petalos;
    }

    public String getColor_del_pistilo() {
        return color_del_pistilo;
    }

    public void setColor_del_pistilo(String color_del_pistilo) {
        this.color_del_pistilo = color_del_pistilo;
    }

    public String getVariedad_de_flor() {
        return variedad_de_flor;
    }

    public void setVariedad_de_flor(String variedad_de_flor) {
        this.variedad_de_flor = variedad_de_flor;
    }

    public String getEstacion_florece() {
        return estacion_florece;
    }

    public void setEstacion_florece(String estacion_florece) {
        this.estacion_florece = estacion_florece;
    }

    @Override
    public void saludar() {
        System.out.println("Hola, soy una flor");
    }
}
