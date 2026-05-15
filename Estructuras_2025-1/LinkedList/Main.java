package Structures.LinkedList;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        try {
            Scanner sc = new Scanner(System.in);

            DoublyLinkedList<Integer> lista = new DoublyLinkedList<>();

            outerloop:
            while (true){
                System.out.println("\nEscoge un metodo para ejecutar: \n1) pushFront    2) topFront    3) popFront \n4) pushBack     5) topBack     6) popBack       \n7) find         8) erase       9) isEmpty     \n10) addBefore   11) addAfter   12) salir");
                int met = sc.nextInt();
                int data, key;
                switch (met) {
                    case 1:
                        System.out.println("pushFront");
                        data = sc.nextInt();
                        lista.pushFront(data);
                        System.out.println(lista);
                        break;
                    case 2:
                        System.out.println("topFront");
                        System.out.println(lista.topFront());
                        System.out.println(lista);
                        break;
                    case 3:
                        System.out.println("popFront");
                        System.out.println(lista.popFront());
                        System.out.println(lista);
                        break;
                    case 4:
                        System.out.println("pushBack");
                        data = sc.nextInt();
                        lista.pushBack(data);
                        System.out.println(lista);
                        break;
                    case 5:
                        System.out.println("topBack");
                        System.out.println(lista.topBack());
                        System.out.println(lista);
                        break;
                    case 6:
                        System.out.println("popBack");
                        System.out.println(lista.popBack());
                        System.out.println(lista);
                        break;
                    case 7:
                        System.out.println("find");
                        key = sc.nextInt();
                        System.out.println(lista.find(key));
                        System.out.println(lista);
                        break;
                    case 8:
                        System.out.println("erase");
                        key = sc.nextInt();
                        lista.erase(key);
                        System.out.println(lista);
                        break;
                    case 9:
                        System.out.println("isEmpty");
                        System.out.println(lista.isEmpty());
                        System.out.println(lista);
                        break;
                    case 10:
                        System.out.println("addBefore");
                        data = sc.nextInt();
                        key = sc.nextInt();
                        lista.addBefore(data, lista.fetch(key));
                        System.out.println(lista);
                        break;
                    case 11:
                        System.out.println("addAfter");
                        data = sc.nextInt();
                        key = sc.nextInt();
                        lista.addAfter(data, lista.fetch(key));
                        System.out.println(lista);
                        break;
                    case 12:
                        System.out.println("saliendo");
                        break outerloop;
                    default:
                        System.out.println("no se escogio una opcion valida");
                        break;
                }
            }

            sc.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}