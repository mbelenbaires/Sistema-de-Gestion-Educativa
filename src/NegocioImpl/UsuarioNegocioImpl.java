package NegocioImpl;

import java.util.ArrayList;

import DAO.UsuarioDAO;
import DAOImpl.UsuarioDAOImpl;
import Entidad.Usuario;
import Negocio.UsuarioNegocio;

public class UsuarioNegocioImpl implements UsuarioNegocio{

	UsuarioDAO pDao = new UsuarioDAOImpl();
	
	@Override
	public Usuario getUsuario(String usuario) {
		// TODO Auto-generated method stub
		return pDao.getUsuario(usuario);
	}
	
	@Override
	public boolean insert(Usuario user) {
		
		boolean estado=false;
		estado=pDao.insertUsuario(user);
		
		return estado;
	}
	
	
	@Override
	public boolean delete(String nombreU) {
		boolean estado=false;
			estado=pDao.delete(nombreU);
		
		return estado;
	}

	
	@Override
	public ArrayList<Usuario> listarUsuarios() {
		return pDao.ListarUsuariosAlta();
	}

	@Override
	public boolean ModificarUsuario(Usuario unUsuario, String nombreU) {
		// TODO Auto-generated method stub
		boolean estado=false;
		estado = pDao.ModificarUsuario(unUsuario, nombreU);
	
		return estado;
	}

}
