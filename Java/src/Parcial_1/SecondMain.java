package Parcial_1;

import java.util.ArrayList;
import java.util.Scanner;

public class SecondMain {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        ArrayList <String> palabras = new ArrayList<String>();
        int cantPalabras = sc.nextInt(); sc.nextLine();
        for(int i = 0; i < cantPalabras; i++){
            palabras.add(sc.nextLine());
        }
        
        ArrayList <Cluster> clusters = new ArrayList<Cluster>();
        int cantClusters = sc.nextInt();
        for (int i = 0; i < cantClusters; i++){
            ArrayList <Double> posicion = new ArrayList<Double>();
            for (int j = 0; j < 26; j++){
                posicion.add(sc.nextDouble());
            }
            clusters.add(new Cluster(new Punto(posicion)));
        }

        int p = sc.nextInt();
        int cantIterciones = sc.nextInt();
        sc.close();
        /* 
        KMeans kmeans = new KMeans(clusters, palabras, cantIterciones, p);
        kmeans.imprimirPalabras();
        */
    }

    //método para pasar de un String a un Punto con sus 26 dimensiones 

    public static Punto calcularPunto(String palabra){
        ArrayList <Double> coordenadas = new ArrayList<Double>();
        // se rellena el array con ceros, para que luego se llene con la cantidad de letras
        for (int i = 0; i < 26; i++){
            coordenadas.add(0.0);
        }
        System.out.println(coordenadas.size());
        //se va sumando 1.0 al valor del caracter por cada vez que aparezca en el String
        for(Character c : palabra.toCharArray()){ //a = 97, z = 122
            coordenadas.set((int)c - 97, coordenadas.get((int)c - 97)+1.0);
        }
        return new Punto(coordenadas);
    }
}


//TODO tengo que hacer que las palabras se pasen a puntos y clcular los clusters con las palabras