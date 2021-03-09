package DAOImpl;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import DAO.UsuarioDAO;
import Entidad.Profesor;
import Entidad.Usuario;

public class UsuarioDAOImpl implements UsuarioDAO {

	public UsuarioDAOImpl() {
		// TODO Auto-generated constructor stub
	}
	
	public Usuario getUsuario (String usuario)
	{
		ProfesorDAOImpl pdao = new ProfesorDAOImpl();
		Usuario unUsu = new Usuario();
		String consulta = "SELECT * FROM usuario where usuario = ?" ;
		PreparedStatement statement;
		Conexion conexion = Conexion.getConexion();
		try{
			statement = conexion.getSQLConexion().prepareStatement(consulta);
			statement.setString(1, usuario);
			ResultSet resultado = statement.executeQuery();
			
			while(resultado.next()){
				unUsu.setUsuario(resultado.getString("usuario"));
				unUsu.setContraseña(resultado.getString("contrasena"));
				unUsu.setPerfilId(resultado.getInt("perfilId"));
				unUsu.setFechaBaja(resultado.getDate("fechaBaja"));
				Profesor profe = pdao.obtenerProfesor(resultado.getInt("legajo"));
				unUsu.setProfesor(profe);
			}
			conexion.cerrarConexion();
			
		}catch (SQLException e) 
		{
			e.printStackTrace();
		}

		return unUsu;
	}
	
	public boolean insertUsuario(Usuario user)
	{
		CallableStatement cst;
		Connection conexion = Conexion.getConexion().getSQLConexion();
		boolean isInsertExitoso = false;
		try
		{
			 cst = conexion.prepareCall("CALL Insert_Usuario(?,?,?)");
			 cst.setString(1, user.getUsuario());
			 cst.setString(2, user.getContraseña());
			 cst.setInt(3, user.getProfesor().getLegajo());
			 if(cst.executeUpdate()> 0)
				{
					conexion.commit();
					isInsertExitoso = true;
				}
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
			try {
				conexion.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}
		
		return isInsertExitoso;
	}
	
	public boolean delete(String nombreU)
	{

		CallableStatement cst;
		Connection conexion = Conexion.getConexion().getSQLConexion();
		boolean isdeleteExitoso = false;
		try 
		{
			cst = conexion.prepareCall("CALL Delete_Usuario(?)");
			cst.setString(1, nombreU);
			if(cst.executeUpdate() > 0)
				{
					conexion.commit();
					isdeleteExitoso = true;
				}
			
		} 
		
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
		return isdeleteExitoso;
	}
	

	
	public boolean ModificarUsuario(Usuario unUsuario, String nombreU)
	{
		
		
		CallableStatement cst;
		Connection conexion = Conexion.getConexion().getSQLConexion();
		boolean modifico = false;
		try 
		{
			cst = conexion.prepareCall("CALL Update_Usuario(?,?)");
			cst.setString(1, nombreU);
			cst.setString(2, unUsuario.getContraseña());
			
			if(cst.executeUpdate() > 0)
				{
					conexion.commit();
					modifico = true;
				}
			
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
		return modifico;
	}
	
	
	
	
	public ArrayList<Usuario> ListarUsuariosAlta()
	{
		
		PreparedStatement statement;
		ResultSet resultSet; //Guarda el resultado de la query
		ArrayList<Usuario> usuarios = new ArrayList<Usuario>();
		Conexion conexion = Conexion.getConexion();
		
		try 
		{
			String query = "SELECT * FROM bd_integrador.usuario where fechaBaja is null";
			statement = conexion.getSQLConexion().prepareStatement(query);
			resultSet = statement.executeQuery();
			while(resultSet.next())
			{

				usuarios.add(getUsuario(resultSet));
			}
			//conexion.cerrarConexion();
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
		return usuarios;
	}
	
	private Usuario getUsuario(ResultSet resultSet) throws SQLException
	{
		
		String nombreU = resultSet.getString("usuario");
		String contra = resultSet.getString("contrasena");
		Integer perfil = resultSet.getInt("perfilId");
		Date fechaB = resultSet.getDate("fechaBaja");
		
		return new Usuario(nombreU,contra, perfil, (java.sql.Date) fechaB);
	}
	
	
	public ArrayList<Usuario> ListaFiltrada(String nombreU)
	{
		
		PreparedStatement statement;
		ResultSet resultSet; //Guarda el resultado de la query
		ArrayList<Usuario> usuarios = new ArrayList<Usuario>();
		Conexion conexion = Conexion.getConexion();		
		try 
		{
			String query = ("SELECT * FROM usuario where usuario like '%"+nombreU+"%'");
			statement = conexion.getSQLConexion().prepareStatement(query);
			resultSet = statement.executeQuery();
			while(resultSet.next())
			{

				usuarios.add(getUsuario(resultSet));
			}
			conexion.cerrarConexion();
		} 
		
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
		return usuarios;
	}
	
	public Usuario obtenerUsuario(int legajo) {
		Usuario usuario = null;
		PreparedStatement statement;
		ResultSet resultSet; //Guarda el resultado de la query
		Conexion conexion = Conexion.getConexion();		
		try 
		{
			String query = ("SELECT * FROM usuario where legajo ='"+legajo+"'");
			statement = conexion.getSQLConexion().prepareStatement(query);
			resultSet = statement.executeQuery();
			while(resultSet.next())
			{

				usuario = getUsuario(resultSet);
			}
			conexion.cerrarConexion();
		} 
		
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
		
		
		
		return usuario;
	}

}
