package Taller_3_2;

public class CursoRegular implements Curso{
    private int examen;
    private int tarea;
    private int proyecto;
    
    public CursoRegular(int examen, int tarea, int proyecto) {
        this.examen = examen;
        this.tarea = tarea;
        this.proyecto = proyecto;
    }

    @Override
    public double calcularNotaFinal() {
        return (examen * 0.6) + (tarea * 0.3) + (proyecto * 0.1);
    }

    @Override
    public boolean aproboCurso() {
        if (calcularNotaFinal() >= 60){
            return true;
        }
        return false;
    }

    @Override
    public String rendimientoEstudiante() {
        Double nota = calcularNotaFinal();
        if (nota >= 90){
            return "Excelente";
        } else if (nota >= 75){
            return "Bueno";
        } else if (nota >= 60){
            return "Suficiente";
        }
        return "Insuficiente";
    }

    @Override
    public String toString() {
        return "(CursoRegular):\n" + "Nota Final: " + calcularNotaFinal() + "\nEstado: " + (aproboCurso()?"Aprobado":"No Aprobado") + "\nRendimiento: " + rendimientoEstudiante() + "\n"; 
    }
}
