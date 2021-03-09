package DAO;
import java.util.ArrayList;

import Entidad.AlumnosXCurso;

public interface AlumnosxCursoDAO {
	
	public ArrayList<Integer> LegajosxCurso (int idcurso);
	public ArrayList<AlumnosXCurso> ListarNotasCursos(int curso);
	public int ListaNotasUpdate(ArrayList<AlumnosXCurso> lista);
	public ArrayList<AlumnosXCurso> ListarNotasCursos(int curso, String nombre, String apellido);
	
}
