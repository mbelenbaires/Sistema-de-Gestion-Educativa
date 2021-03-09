package NegocioImpl;

import java.util.ArrayList;

import DAO.AlumnoDao;
import DAOImpl.AlumnoDAOImpl;
import Entidad.Alumno;
import Negocio.AlumnoNegocio;


public class AlumnoNegocioImpl implements AlumnoNegocio {

	AlumnoDao pdao = new AlumnoDAOImpl();
	
	@Override
	public boolean insert(Alumno alumno) {
		
		boolean estado=false;
		estado=pdao.insertAlumno(alumno);
		
		return estado;
	}
	
	
	@Override
	public boolean delete(int legajo) {
		boolean estado=false;
			estado=pdao.delete(legajo);
		
		return estado;
	}

	
	@Override
	public ArrayList<Alumno> listarAlumnos() {
		return pdao.ListarAlumnosAlta();
	}


	@Override
	public boolean ModificarAlumno(Alumno unAlumno, int legajo) {
		// TODO Auto-generated method stub
		boolean estado=false;
		estado = pdao.ModificarAlumno(unAlumno, legajo);
	
		return estado;
	}

}
