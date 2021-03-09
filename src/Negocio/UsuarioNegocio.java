package Negocio;

import java.util.List;

import Entidad.Usuario;

public interface UsuarioNegocio {
	
	public Usuario getUsuario (String usuario);
	public boolean insert(Usuario user);
	public List<Usuario> listarUsuarios();
	public boolean ModificarUsuario(Usuario unUsuario, String nombreU);
	public boolean delete(String nombreU);

}
