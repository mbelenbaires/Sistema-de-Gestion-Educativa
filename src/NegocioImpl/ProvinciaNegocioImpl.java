package NegocioImpl;

import java.util.ArrayList;

import DAO.ProvinciaDAO;
import DAOImpl.ProvinciaDAOImpl;
import Entidad.Provincia;

public class ProvinciaNegocioImpl {

ProvinciaDAO pdao = new ProvinciaDAOImpl();
	
	public ArrayList<Provincia> listarProvincias() {
		return pdao.ListarProvincias();
	}

	
}
