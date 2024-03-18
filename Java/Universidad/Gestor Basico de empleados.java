package Universidad;
import java.util.Scanner;
class Empleado {
    String Nombre, Genero, Departamento;
    int Edad, semanasCotizadas;
    double Salario;
    void __init__(String Nombre, String Genero, String Departamento, int Edad, int Semanas, double Salario){
        this.Nombre = Nombre;
        this.Genero = Genero;
        this.Departamento = Departamento;
        this.Edad = Edad;
        this.semanasCotizadas = Semanas;
        this.Salario = Salario;
    } 
    public double obtenerBonificacion(int semanasCotizadas) {
        return semanasCotizadas <= 325 ? 0.10
                :semanasCotizadas <= 650 ? 0.15
                :semanasCotizadas <= 975 ? 0.20: 0.25;
    }
    public void AumentarSalario(double porcentaje){
        Salario += Salario*porcentaje;
    }
    public void AumentarSemanasCotizadas(){
        semanasCotizadas += 1;
    }
    public boolean DerechoPensionarse(String genero, int semanas_cotizadas,int edad){
        return (semanas_cotizadas >= 1300 && ((genero.equals("H") && edad >= 62) || (genero.equals("M") && edad >= 57))) ? true : false;
    }
    public int CuantasSemanasFaltanParaPensioanrse(int semanasCotizadas){
        return 1300-semanasCotizadas;
    }
    public String toString(){
        return "Nombre:"+Nombre+" edad:"+Edad+" genero:"+Genero+" departamento:"+Departamento+" semanas Cotizadas:"+semanasCotizadas+" Salario:"+Salario+" pesos";
    }
}

class solution2 {
    public static void main(String[] args) {
        Empleado mano = new Empleado();
        Scanner sc = new Scanner(System.in);

        String Nombre = sc.nextLine();
        int Edad = sc.nextInt(); sc.nextLine();
        String Genero = sc.nextLine(); 
        String Departamento = sc.nextLine();
        int Semanas  = sc.nextInt(); sc.nextLine();
        double Salario = sc.nextDouble();

        mano.__init__(Nombre, Genero, Departamento, Edad, Semanas, Salario);
        mano.AumentarSalario(mano.obtenerBonificacion(mano.semanasCotizadas));
        boolean comp = mano.DerechoPensionarse(mano.Genero, mano.semanasCotizadas, mano.Edad);
        System.out.println("Su nuevo salario es:" + mano.Salario);
        System.out.println(comp ? mano.Nombre + " tiene derecho a la pension" : "Aun no tiene derecho a la pension");
        System.out.println(comp ? "Ya cumplio las semanas" : "Le hacen falta: " + mano.CuantasSemanasFaltanParaPensioanrse(mano.semanasCotizadas) + " semanas");
        mano.AumentarSemanasCotizadas();
        System.out.println(mano.toString());
        sc.close();
    }
}