import javax.swing.JOptionPane;

class CondicionalesSimples {
    public static void main(String[] args) {
        int numero, dato = 5;
        numero = Integer.parseInt(JOptionPane.showInputDialog("Digita un numero: "));
        if (numero == dato){
            JOptionPane.showMessageDialog(null,"El número es 5");
        } else {
            JOptionPane.showMessageDialog(null,"El número es diferente de 5");
        }
    }    
}

class CondicionalesMultiples {
    public static void main(String[] args) {
        int dato;
        dato = Integer.parseInt(JOptionPane.showInputDialog("Digitra un numero entre 1 y 5"));

        switch (dato) {
            case 1: JOptionPane.showMessageDialog(null,"Es el numero 1");
                break; // esto sale del switch
            case 2: JOptionPane.showMessageDialog(null,"Es el numero 2");
                break; // si se quita el break, no sale de la sentencia switch y continua al siguiente caso
            case 3: JOptionPane.showMessageDialog(null,"Es el numero 3");
                break;
            case 4: JOptionPane.showMessageDialog(null,"Es el numero 4");
                break;
            case 5: JOptionPane.showMessageDialog(null,"Es el numero 5");
                break;
            default: JOptionPane.showMessageDialog(null,"El número no esta en el rango entre 1 y 5");
            // default es como el else de una sentencia if
                break; // como es el único caso, no es tan necesario colocar el break
        } 
    }
}