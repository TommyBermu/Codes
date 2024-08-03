package Taller_2;
public class Fraccion{
    private int num_crudo, den_crudo, num, den, coc, res;
    private String signo;

    public Fraccion(int num, int den) {
        this.num_crudo = num;
        this.den_crudo = den;
        this.signo = this.num_crudo>=0 ^ this.den_crudo>=0 ? "-" : "";
        this.num = this.num_crudo < 0 ? -this.num_crudo : this.num_crudo;
        this.den = this.den_crudo < 0 ? -this.den_crudo : this.den_crudo;
        this.coc = this.num/this.den;
        this.res = this.num%this.den;
    }
    
    String Propio(){
        return "" + this.signo + this.num +"/"+ this.den;
    }

    String Mixto(){
        return "" + this.signo + (this.coc != 0 ? this.coc : "") + (this.coc != 0 && this.res != 0 ? " " : "") + (this.res != 0 ? + this.res +"/"+ this.den : "");
    }

    Fraccion Suma(Fraccion target){
        return new Fraccion(this.num_crudo * target.den_crudo + this.den_crudo * target.num_crudo, this.den_crudo * target.den_crudo);
    }

    Fraccion Resta(Fraccion target){
        return new Fraccion(this.num_crudo * target.den_crudo - this.den_crudo * target.num_crudo, this.den_crudo * target.den_crudo);
    }
    
    Fraccion Multiplicacion(Fraccion target){
        return new Fraccion(this.num_crudo * target.num_crudo, this.den_crudo * target.den_crudo);
    }
    
    Fraccion Division(Fraccion target){
        return new Fraccion(this.num_crudo * target.den_crudo, this.den_crudo * target.num_crudo);
    }

    boolean esMayor(Fraccion target){
        return (double)(this.num_crudo)/(double)(this.den_crudo) > (double)(target.num_crudo)/(double)(target.den_crudo);
    }

    boolean esMenor(Fraccion target){
        return !esMayor(target);
    }

    double Diferencia(Fraccion sustraendo){
        return (double)(this.num_crudo * sustraendo.den_crudo - this.den_crudo * sustraendo.num_crudo) / (double)(this.den_crudo * sustraendo.den_crudo);
    }

    Fraccion simplificar(){
        return new Fraccion(num, den);
    }
}