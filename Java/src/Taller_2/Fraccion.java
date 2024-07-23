package Taller_2;
public class Fraccion{
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
    String Propio(){
        return "" + (this.signo ? "-" : "") + this.num +"/"+ this.den;
    }
    String Mixto(){
        return "" + (this.signo ? "-" : "") + (this.coc != 0 ? this.coc : "") + (this.coc != 0 && this.res != 0 ? " " : "") + (this.res != 0 ? + this.res +"/"+ this.den : "");
    }
    String Solution(){
        return Propio() + " " + Mixto();
    }
}