package Taller_2;
public class Fraccion{
    int num_crudo, den_crudo, num, den, coc, res;
    boolean signo;

    public Fraccion(int num, int den) {
        this.num_crudo = num;
        this.den_crudo = den;
        this.signo = this.num_crudo>=0 ^ this.den_crudo>=0;
        this.num = this.num_crudo < 0 ? -this.num_crudo : this.num_crudo;
        this.den = this.den_crudo < 0 ? -this.den_crudo : this.den_crudo;
        this.coc = this.num/this.den;
        this.res = this.num%this.den;
    }

    int Numerador(){
        return num_crudo;
    }

    int Denominador(){
        return den_crudo;
    }
    
    String Propio(){
        return "" + (this.signo ? "-" : "") + this.num +"/"+ this.den;
    }

    String Mixto(){
        return "" + (this.signo ? "-" : "") + (this.coc != 0 ? this.coc : "") + (this.coc != 0 && this.res != 0 ? " " : "") + (this.res != 0 ? + this.res +"/"+ this.den : "");
    }

    Fraccion Division(Fraccion frac){
        return new Fraccion(this.Numerador() * frac.Denominador(), this.Denominador() * frac.Numerador());
    }

    double Diferencia(Fraccion sustraendo){
        return (double)(this.Numerador() * sustraendo.Denominador() - this.Denominador() * sustraendo.Numerador()) / (double)(this.Denominador() * sustraendo.Denominador());
    }

    Fraccion Resta(Fraccion target){
        return new Fraccion(this.Numerador() * target.Denominador() - this.Denominador() * target.Numerador(), this.Denominador() * target.Denominador());
    }
}