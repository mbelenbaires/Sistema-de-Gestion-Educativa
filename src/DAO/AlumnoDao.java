package DAO;

import java.util.ArrayList;

import Entidad.Alumno;

public interface AlumnoDao {

	public boolean insertAlumno(Alumno alumno);
	public boolean delete(int legajo);
	public ArrayList<Alumno> ListarAlumnosAlta();
	public boolean ModificarAlumno(Alumno unAlumno, int legajo);
	public ArrayList<Alumno> ListaFiltrada(int legajo, String dni, String nombre, String apellido);
	Alumno obtenerAlumno(int legajo);	

}
