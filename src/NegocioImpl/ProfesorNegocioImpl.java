package NegocioImpl;

import java.util.ArrayList;
import DAO.ProfesorDAO;
import DAOImpl.ProfesorDAOImpl;
import Entidad.Profesor;
import Negocio.ProfesorNegocio;


public class ProfesorNegocioImpl implements ProfesorNegocio {

	
	
	public ProfesorNegocioImpl() {
		// TODO Auto-generated constructor stub
	}

	
	
ProfesorDAO pdao = new ProfesorDAOImpl();
	
	@Override
	public boolean InsertarProfesor(Profesor profe) {
		
		boolean estado=false;
		estado=pdao.InsertarProfesor(profe);
		
		return estado;
	}
	
	
	@Override
	public boolean EliminarProfesor(int legajo) {
		boolean estado=false;
			estado=pdao.EliminarProfesor(legajo);
		
		return estado;
	}

	
	@Override
	public ArrayList<Profesor> ListarProfesores() {
		return pdao.ListarProfesores();
	}


	@Override
	public boolean ModificarProfesor(Profesor unprofe, int legajo) {
		// TODO Auto-generated method stub
		boolean estado=false;
		estado = pdao.ModificarProfesor(unprofe, legajo);
	
		return estado;
	}
	

	@Override
	public Profesor obtenerProfesor(String nombre) {
		// TODO Auto-generated method stub
		return pdao.obtenerProfesor(nombre);
	}


	@Override
	public Profesor obtenerProfesor(int id) {
		// TODO Auto-generated method stub
		return pdao.obtenerProfesor(id);
	}
	
	//Desarrollo metodos Negocio
	
	
	
}
