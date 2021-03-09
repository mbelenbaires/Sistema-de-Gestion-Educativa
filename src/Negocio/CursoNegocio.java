package Negocio;

import java.util.List;

import Entidad.Curso;

public interface CursoNegocio {
	public List<Curso> ListarCursos();
	public List<Curso> CursosXProfesor(int legajo);
	public Curso UnCursoGet(int curso);

}
