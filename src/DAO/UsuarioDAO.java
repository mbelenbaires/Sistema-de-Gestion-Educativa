package DAO;

import java.util.ArrayList;

import Entidad.Usuario;

public interface UsuarioDAO {
	public Usuario getUsuario (String usuario);
	public boolean insertUsuario(Usuario user);
	public boolean delete(String nombreU);
	public ArrayList<Usuario> ListarUsuariosAlta();
	public boolean ModificarUsuario(Usuario unUsuario, String nombreU);
	public ArrayList<Usuario> ListaFiltrada(String nombreU);	
}
