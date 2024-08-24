package Taller_3_1;

import java.text.NumberFormat;
import java.text.ParseException;
import java.util.Locale;
import java.util.Scanner;

public class Solution {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        NumberFormat format = NumberFormat.getInstance(Locale.GERMANY);
        int n = sc.nextInt();
        for (int i = 0; i < n; i++){
            String cliente = sc.next();
            String ingresosStr = sc.next();
            String deudasStr = sc.next();
            try {
                double ingresos = format.parse(ingresosStr).doubleValue();
                double deudas = format.parse(deudasStr).doubleValue();

                if (cliente.equals("PersonaNatural")){
                    Natural natural = new Natural(ingresos, deudas);
                    System.out.println(natural.toString());
                } else if (cliente.equals("EmpresaPequena")){
                    Empresa empresa = new Empresa(ingresos, deudas, sc.nextInt());
                    System.out.println(empresa.toString());
                } else {
                    Corporacion corporacion = new Corporacion(ingresos, deudas, format.parse(sc.next()).doubleValue(), sc.nextInt());
                    System.out.println(corporacion.toString());
                }
            } catch (ParseException e) {
                System.out.println("Error al parsear los números: " + e.getMessage());
            }
            
        }   
        sc.close();
    }
}
