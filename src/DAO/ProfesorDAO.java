package DAO;

import java.util.ArrayList;

import Entidad.Profesor;

public interface ProfesorDAO {
	
	public ArrayList<Profesor> ListarProfesores2();
	

	public Profesor obtenerProfesor(String nombre);
	public int obtenerLegajo(String nombre);

	public Profesor obtenerProfesor(int legajo);
	
	//metodos de la DB
	
		public boolean InsertarProfesor(Profesor profe);
		public boolean EliminarProfesor(int legajo);
		public ArrayList<Profesor> ListarProfesores();
		public boolean ModificarProfesor(Profesor unprofe, int legajo);
		public ArrayList<Profesor> ListaFiltradaProfesor(int legajo, String dni, String nombre, String apellido);

}
