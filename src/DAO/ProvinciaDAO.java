package DAO;

import java.util.ArrayList;

import Entidad.Provincia;

public interface ProvinciaDAO {
	
	public ArrayList<Provincia> ListarProvincias();
	public String obtenerProvincia(int id);

}
