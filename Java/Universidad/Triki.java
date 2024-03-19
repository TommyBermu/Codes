package Universidad;
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

    String verificarGanador() {
        // Verificar filas y columnas
        for (int i = 0; i < 3; i++) {
            if (triki[i][0].equals(triki[i][1]) && triki[i][1].equals(triki[i][2]) && !triki[i][0].equals("a")) {
                return triki[i][0];
            }
            if (triki[0][i].equals(triki[1][i]) && triki[1][i].equals(triki[2][i]) && !triki[0][i].equals("a")) {
                return triki[0][i];
            }
        }
        
        // Verificar diagonales
        if (triki[0][0].equals(triki[1][1]) && triki[1][1].equals(triki[2][2]) && !triki[0][0].equals("a")) {
            return triki[0][0];
        }
        if (triki[0][2].equals(triki[1][1]) && triki[1][1].equals(triki[2][0]) && !triki[0][2].equals("a")) {
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
                System.out.print(triki[i][j]);
            }
            System.out.println();
        }
    }
}

class Solution0 {
    public static void main(String[] args) {
        Triki mat = new Triki();
        Scanner sc = new Scanner(System.in);
        mat.triki();
        String casilla;
        int fila, columna;
        while (mat.verificarGanador().equals("a")){
            casilla = sc.nextLine();
            while (!casilla.equals("O") && !casilla.equals("X")){
                    System.out.println("SNA");
                    casilla = sc.nextLine();
            }
            System.out.println("SA");

            fila = sc.nextInt();
            columna = sc.nextInt();
            while (fila < 0 || fila > 2 || columna < 0 || columna > 2 || !mat.verificarCasilla(fila, columna).equals("a")) {
                System.out.println("CNA");
                fila = sc.nextInt();
                columna = sc.nextInt();
            }
            if(sc.hasNextLine())sc.nextLine();
            System.out.println("CA");
            
            mat.marcarCasilla(casilla, fila, columna);
        }
        mat.imprimirTablero();
        System.out.println("El simbolo ganador fue: " + mat.verificarGanador());
        sc.close();
    }
}