package Taller_1;
import java.util.Scanner;

class Solution3 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int d = sc.nextInt();
        Fraccion fraccion = new Fraccion(n ,d);
        System.out.println(fraccion.Solution());
        sc.close();
    }
}

class Fraccion {
    int num, den, coc, res;
    boolean signo;

    public Fraccion(int num, int den) {
        this.num = num;
        this.den = den;
        this.signo = this.num>=0 ^ this.den>=0;
        this.num = this.num < 0 ? -this.num : this.num;
        this.den = this.den < 0 ? -this.den : this.den;
        this.coc = this.num/this.den;
        this.res = this.num%this.den;
    }
    String Propio(boolean signo, int num, int den){
        return "" + (signo ? "-" : "") + num +"/"+ den;
    }
    String Mixto(boolean signo, int den, int coc, int res){
        return "" + (signo ? "-" : "") + (coc != 0 ? coc + " ": "") + (res != 0 ? res +"/"+ den : "");
    }
    String Solution(){
        return Propio(this.signo, this.num , this.den) + " " + Mixto(this.signo, this.den, this.coc, this.res);
    }
}