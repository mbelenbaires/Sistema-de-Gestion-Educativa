package Negocio;

import java.util.List;

import Entidad.Alumno;

public interface AlumnoNegocio {
	
	public boolean insert(Alumno alumno);
	public List<Alumno> listarAlumnos();
	public boolean ModificarAlumno(Alumno unAlumno, int legajo);
	public boolean delete(int legajo);
	
}
