package Taller_3_2;

import java.util.Scanner;

public class Solution {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();
        for (int i = 1; i <= n; i++){
            String curso = sc.next();
            int examen = sc.nextInt();
            int tarea = sc.nextInt();
            int proyecto = sc.nextInt();
            
            if (curso.equals("CursoRegular")){
                CursoRegular cursoRegular = new CursoRegular(examen, tarea, proyecto);
                System.out.println("Curso " + i + " " + cursoRegular.toString());
            } else if (curso.equals("CursoAvanzado")){
                CursoAvanzado cursoAvanzado = new CursoAvanzado(examen, tarea, proyecto);
                System.out.println("Curso " + i + " " + cursoAvanzado.toString());
            } else {
                Taller taller = new Taller(examen, tarea, proyecto);
                System.out.println("Curso " + i + " " + taller.toString());
            }
        }

        sc.close();
    }
}