package Taller_3_2;

public class CursoAvanzado implements Curso{
    private int examen;
    private int tarea;
    private int proyecto;
    
    public CursoAvanzado(int examen, int tarea, int proyecto) {
        this.examen = examen;
        this.tarea = tarea;
        this.proyecto = proyecto;
    }

    @Override
    public double calcularNotaFinal() {
        return (examen * 0.7) + (tarea * 0.1) + (proyecto * 0.2);
    }

    @Override
    public boolean aproboCurso() {
        if (calcularNotaFinal()  >= 70){
            return true;
        }
        return false;
    }

    @Override
    public String rendimientoEstudiante() {
        Double nota = calcularNotaFinal();
        if (nota >= 90){
            return "Excelente";
        } else if (nota >= 80){
            return "Bueno";
        } else if (nota >= 70){
            return "Suficiente";
        }
        return "Insuficiente";
    }

    @Override
    public String toString() {
        return "(CursoAvanzado):\n" + "Nota Final: " + calcularNotaFinal() + "\nEstado: " + (aproboCurso()?"Aprobado":"No Aprobado") + "\nRendimiento: " + rendimientoEstudiante() + "\n"; 
    }
}
