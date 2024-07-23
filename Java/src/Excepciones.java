public class Excepciones {
    public static void main(String[] args) {
        try {
            int i = 4/0;
            System.out.println(i);
        } catch (ArithmeticException e) {
            System.out.println("no se puede dividir entre cero");
        }

        try {
            int edades[] = {15,12,30,54};
            System.out.println("La edad de la cuarta persona es: " + edades[4]);
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Índice fuera de la lista");
        }
    }
}
