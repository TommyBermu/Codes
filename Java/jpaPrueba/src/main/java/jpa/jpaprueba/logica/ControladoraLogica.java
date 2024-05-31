package jpa.jpaprueba.logica;

import jpa.jpaprueba.persistencia.ControladoraPersistencia;

public class ControladoraLogica {
    
    ControladoraPersistencia controlPersis = new ControladoraPersistencia();
    
    public void crearAlumno(Alumno alu) {
        controlPersis.crearAlumno(alu);
    }
    
}
