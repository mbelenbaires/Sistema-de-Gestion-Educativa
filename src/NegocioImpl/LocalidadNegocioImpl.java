package NegocioImpl;

import java.util.ArrayList;

import DAO.LocalidadDAO;
import DAOImpl.LocalidadDAOImpl;
import Entidad.Localidad;

public class LocalidadNegocioImpl {

LocalidadDAO ldao = new LocalidadDAOImpl();
	
	public ArrayList<Localidad> listarLocalidades() {
		return ldao.ListarLocalidades();
	}
	
}
