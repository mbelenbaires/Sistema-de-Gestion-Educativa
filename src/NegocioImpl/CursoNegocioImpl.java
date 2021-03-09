package NegocioImpl;

import java.util.List;


import DAO.CursoDAO;
import DAOImpl.CursoDAOImpl;
import Entidad.Curso;
import Negocio.CursoNegocio;

public class CursoNegocioImpl implements CursoNegocio{

	CursoDAO dao = new CursoDAOImpl();
	
	public CursoNegocioImpl() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public List<Curso> ListarCursos() {
		// TODO Auto-generated method stub
		return dao.ListarCursos();
	}

	@Override
	public List<Curso> CursosXProfesor(int legajo) {
		// TODO Auto-generated method stub
		return dao.CursosXProfesor(legajo);
	}

	@Override
	public Curso UnCursoGet(int curso) {
		// TODO Auto-generated method stub
		return dao.UnCursoGet(curso);
	}
	

}
