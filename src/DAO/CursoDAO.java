package DAO;

import java.util.ArrayList;
import java.util.List;

import Entidad.Curso;

public interface CursoDAO {
	

	public boolean insertCurso(Curso curso);
	public boolean delete(int idcurso);
	public ArrayList<Curso> ListarCursos();
	public boolean ModificarCurso(Curso unCurso);
	public ArrayList<Curso> ListaFiltrada(String materia, String profesor);
	public List<Curso> CursosXProfesor(int legajo);
	public Curso UnCursoGet(int curso);
}
