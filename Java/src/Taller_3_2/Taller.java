package Taller_3_2;

public class Taller implements Curso{
    private int examen;
    private int tarea;
    private int proyecto;
    
    public Taller(int examen, int tarea, int proyecto) {
        this.examen = examen;
        this.tarea = tarea;
        this.proyecto = proyecto;
    }

    @Override
    public double calcularNotaFinal() {
        return (examen * 0.3) + (tarea * 0.2) + (proyecto * 0.5);
    }

    @Override
    public boolean aproboCurso() {
        if (calcularNotaFinal() >= 65){
            return true;
        }
        return false;
    }

    @Override
    public String rendimientoEstudiante() {
        Double nota = calcularNotaFinal();
        if (nota >= 85){
            return "Excelente";
        } else if (nota >= 75){
            return "Bueno";
        } else if (nota >= 65){
            return "Suficiente";
        }
        return "Insuficiente";
    }

    @Override
    public String toString() {
        return "(Taller):\n" + "Nota Final: " + calcularNotaFinal() + "\nEstado: " + (aproboCurso()?"Aprobado":"No Aprobado") + "\nRendimiento: " + rendimientoEstudiante() + "\n"; 
    }
}
