package jpa.jpaprueba.persistencia;

import jpa.jpaprueba.logica.Alumno;

public class ControladoraPersistencia {
    
    AlumnoJpaController aluJpa = new AlumnoJpaController();
    
    public void crearAlumno(Alumno alu) {
        aluJpa.create(alu);
    }
}
