package Parcial_2_2;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        Scanner sc = new Scanner(new File("C:/Users/bermu/Desktop/input1.txt"));
        boolean ganador = false;

        System.out.println("Comienza la batalla");

        String nombre_heroe = sc.nextLine();
        int vida_heroe = sc.nextInt(); sc.nextLine();
        int dano_heroe = sc.nextInt(); sc.nextLine();
        int creditos_heroe = sc.nextInt(); sc.nextLine();
        Heroe heroe = new Heroe(nombre_heroe, vida_heroe, dano_heroe, creditos_heroe);

        String nombre_villano = sc.nextLine();
        int vida_villano = sc.nextInt(); sc.nextLine();
        int dano_villano = sc.nextInt(); sc.nextLine();
        Villano villano = new Villano(nombre_villano, vida_villano, dano_villano);

        while (!ganador){
            int opcion = sc.nextInt();
            if (sc.hasNextLine())
                sc.nextLine();

            switch (opcion){
                case 1:
                    heroe.atacar(villano);
                    if (villano.getVida() <= 0){
                        ganador = true;
                        System.out.println("Has derrotado al villano.");
                    }
                    break;
                case 2:
                    String habilidad = sc.nextLine();
                    if (habilidad.equals("aumentar_vida")){
                        heroe.aumentarVida();
                    } else if (habilidad.equals("duplicar_ataque")){
                        heroe.duplicarDano();
                    }
                    break;
                case 3:
                    String compra = sc.nextLine();
                    if (compra.equals("aumentar_vida")){
                        heroe.comprarVida();
                    } else if (compra.equals("duplicar_ataque")){
                        heroe.comprarDano();
                    }
                    break;
            }
            if (!ganador){
                villano.atacar(heroe);
                if (heroe.getVida() <= 0){
                    ganador = true;
                    System.out.println("El heroe ha sido derrotado");
                }
            }
        }
        sc.close();
    }
}