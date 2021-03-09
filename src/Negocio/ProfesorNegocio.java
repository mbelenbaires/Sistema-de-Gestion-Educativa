package Negocio;

import java.util.List;


import Entidad.Profesor;

public interface ProfesorNegocio {

	//metodos del negocio
	public boolean InsertarProfesor(Profesor profe);
	public List<Profesor> ListarProfesores();
	public boolean ModificarProfesor(Profesor unprofe, int legajo);
	public boolean EliminarProfesor(int legajo);
	public Profesor obtenerProfesor(String nombre);
	public Profesor obtenerProfesor(int id);
	
}
