package NegocioImpl;

import java.util.ArrayList;
import java.util.List;

import DAO.AlumnosxCursoDAO;
import DAOImpl.AlumnosxcursoDAOImpl;
import Negocio.AlumnosXCursoNegocio;
import Entidad.AlumnosXCurso;

public class AlumnosXCursoNegocioImpl implements AlumnosXCursoNegocio{

	AlumnosxCursoDAO pdao = new AlumnosxcursoDAOImpl();
	public AlumnosXCursoNegocioImpl() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public List<AlumnosXCurso> ListarNotasCursos(int curso) {
		// TODO Auto-generated method stub
		return pdao.ListarNotasCursos(curso);
	}

	@Override
	public int ListaNotasUpdate(ArrayList<AlumnosXCurso> lista) {
		// TODO Auto-generated method stub
		return pdao.ListaNotasUpdate(lista);
	}

	@Override
	public ArrayList<AlumnosXCurso> ListarNotasCursos(int curso, String nombre, String Apellido) {
		// TODO Auto-generated method stub
		return pdao.ListarNotasCursos(curso,nombre,Apellido);
	}

}
