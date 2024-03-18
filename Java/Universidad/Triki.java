package Universidad;
//import java.util.Scanner;

import java.util.Scanner;

class Triki {
    String triki[][] = new String[3][3];
    public void triki() {
        for (int i = 0; i < triki.length; i++) {
            for (int j = 0; j < triki[i].length; j++) {
                triki[i][j] = "a";
            }
        }
    }
    
    public void marcarCasilla(String simbolo, int fila, int columna) {
        triki[fila][columna] = simbolo;
    }

    String verificarGanador(){
        // verificar filas
        if (triki[0][0] == triki[0][1] && triki[0][1] == triki[0][2]){
            return triki[0][0];
        }
        if (triki[1][0] == triki[1][1] && triki[1][1] == triki[1][2]){
            return triki[1][0];
        }
        if (triki[2][0] == triki[2][1] && triki[2][1] == triki[2][2]){
            return triki[2][0];
        }
        // Verificar columnas
        if (triki[0][0] == triki[1][0] && triki[1][0] == triki[2][0]){
            return triki[0][0];
        }
        if (triki[0][1] == triki[1][1] && triki[1][1] == triki[2][1]){
            return triki[0][1];
        }
        if (triki[0][2] == triki[1][2] && triki[1][2] == triki[2][2]){
            return triki[0][2];
        }
        //verificar diagonales
        if (triki[0][0] == triki[1][1] && triki[1][1] == triki[2][2]){
            return triki[0][0];
        }
        if (triki[0][2] == triki[1][1] && triki[1][1] == triki[2][0]){
            return triki[0][2];
        }
        return "a";
    }

    String verificarCasilla(int fila, int columna){
        return triki[fila][columna];
    }

    void imprimirTablero(){
        for (byte i = 0; i < triki.length; i++) {
            for (byte j = 0; j < triki[i].length; j++) {
                System.out.print(triki[i][j] + " ");
            }
            System.out.println();
        }
    }
}

class Solution0 {
    public static void main(String[] args) {
        Triki mat = new Triki();
        Scanner sc = new Scanner(System.in);
        while (true){
            
            mat.marcarCasilla(sc.nextLine(), sc.nextInt(), sc.nextInt());
        }
        sc.close();
    }
}