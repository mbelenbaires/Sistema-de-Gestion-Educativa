package DAO;

import java.util.ArrayList;
import Entidad.Materia;

public interface MateriaDAO {
	
	public ArrayList<Materia> ListarMaterias();
	public int obtenerMateria(String nombre);
	public Materia obtenerMateria(int id);
}
