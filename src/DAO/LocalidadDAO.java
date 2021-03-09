package DAO;

import java.util.ArrayList;

import Entidad.Localidad;

public interface LocalidadDAO {
	public ArrayList<Localidad> ListarLocalidades();
	public String obtenerLocalidad(int id);
	int obtenerId(String nombre);
	public int obtenerIdProvincia(int id);
}
