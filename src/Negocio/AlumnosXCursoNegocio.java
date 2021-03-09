package Negocio;

import java.util.ArrayList;
import java.util.List;

import Entidad.AlumnosXCurso;

public interface AlumnosXCursoNegocio {
	
	public List<AlumnosXCurso> ListarNotasCursos(int curso);
	public int ListaNotasUpdate(ArrayList<AlumnosXCurso> lista);
	public ArrayList<AlumnosXCurso> ListarNotasCursos(int curso, String nombre, String apellido);
	
}
