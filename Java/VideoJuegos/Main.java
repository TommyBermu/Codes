package VideoJuegos;

import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        ArrayList<VideoJuego> juegos = new ArrayList<>();

        VideoJuego minecraft = new VideoJuego(1, "Minecraft", "PC", 8, "Supervivencia");
        VideoJuego eve_online = new VideoJuego(2, "Eve Online", "PC", 20000, "Aventura");
        VideoJuego just_cause = new VideoJuego(3, "Just Cause 3", "Xbox One", 1 , "Acción");
        VideoJuego sims = new VideoJuego(4, "Sims 4", "Xbox One", 1, "Simulación");
        VideoJuego the_forest = new VideoJuego(5, "The Forest", "PC", 4, "Supervivencia");

        juegos.add(minecraft);
        juegos.add(eve_online);
        juegos.add(just_cause);
        juegos.add(sims);
        juegos.add(the_forest);

        // punto 3 - recorrido
        for (VideoJuego juego: juegos) {
            System.out.println("Título: " + juego.getTitulo() + ", Consola: " + juego.getPlataforma() + ", Camtidad de Jugadores: " + juego.getCantJugadores());
        }

        // punto 4 - cambio de nombre y jugadores
        sims.setCantJugadores(8);
        sims.setTitulo("Proyect Rene");

        the_forest.setCantJugadores(6);
        the_forest.setTitulo("Sons of the Forest");

        // punto 5
        for (VideoJuego juego: juegos) {
            if (juego.getPlataforma().equals("PC")) {
            System.out.println(juego.toString());
            }
        }
    }
}