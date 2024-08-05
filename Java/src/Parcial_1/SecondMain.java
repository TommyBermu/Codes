package Parcial_1;

import java.util.HashMap;
import java.util.Scanner;

public class SecondMain {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        HashMap <String, String> asociacion = new HashMap<>();
        
        int cantPalabras = sc.nextInt(); sc.nextLine();
        Punto[] palabrasComoPunto = new Punto[cantPalabras];

        for(int i = 0; i < cantPalabras; i++){
            String palabra = sc.nextLine();
            Punto palabraCalculada = calcularPunto(palabra);
            palabrasComoPunto[i] = palabraCalculada;
            asociacion.put(palabraCalculada.toString(), palabra);
        }
        
        int cantClusters = sc.nextInt();
        Cluster[] clusters = new Cluster[cantClusters];
        
        for (int i = 0; i < cantClusters; i++){
            double[] posicion = new double[26];
            for (int j = 0; j < 26; j++){
                posicion[j] = sc.nextDouble();
            }
            clusters[i] = new Cluster(new Punto(posicion));
        }

        int p = sc.nextInt();
        int cantIterciones = sc.nextInt();
        sc.close();

        KMeans kmeans = new KMeans(clusters, palabrasComoPunto, cantIterciones, (double)p);
        kmeans.imprimirPalabras(asociacion);
        
    }

    public static Punto calcularPunto(String palabra){
        double[] coordenadas = {0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0};

        for(Character c : palabra.toCharArray()){
            coordenadas[(int)c - 97]++;
        }
        return new Punto(coordenadas);
    }
}