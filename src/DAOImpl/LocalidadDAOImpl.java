package DAOImpl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import DAO.LocalidadDAO;
import Entidad.Localidad;
import Entidad.Provincia;

public class LocalidadDAOImpl implements LocalidadDAO {


	
private static final String readall = "SELECT * FROM localidad";
	
	public LocalidadDAOImpl() {
		// TODO Auto-generated constructor stub
	}

	public ArrayList<Localidad> ListarLocalidades()
	{

		PreparedStatement statement;
		ResultSet resultSet; //Guarda el resultado de la query
		ArrayList<Localidad> localidades = new ArrayList<Localidad>();
		Conexion conexion = Conexion.getConexion();
		
		try 
		{
			statement = conexion.getSQLConexion().prepareStatement(readall);
			resultSet = statement.executeQuery();
			while(resultSet.next())
			{
				localidades.add(getLocalidad(resultSet));
			}
			conexion.cerrarConexion();
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
		return localidades;
	}	
	
	
	private Localidad getLocalidad(ResultSet resultSet) throws SQLException
	{
		
		int id = resultSet.getInt("id");
		String nombre = resultSet.getString("nombre");
		int cp = resultSet.getInt("codigopostal");
		Provincia prov = new Provincia();
		
		prov.setId(resultSet.getInt("provincia_id"));
		ProvinciaDAOImpl pdao = new ProvinciaDAOImpl();
		prov.setNombre(pdao.obtenerProvincia(resultSet.getInt("provincia_id")));	

		return new Localidad(id,prov,nombre,cp);
	}

	@Override
	public String obtenerLocalidad(int idloc) {
		
		String nombre=null;
		String consulta = "SELECT nombre from localidad where id = ? ";
		Conexion conexion = Conexion.getConexion();
		PreparedStatement statement;
		try{
			statement = conexion.getSQLConexion().prepareStatement(consulta);
			statement.setInt(1, idloc);
			ResultSet resultado = statement.executeQuery();
			
			while(resultado.next()){
				nombre = (resultado.getString("nombre"));
			}
		//	conexion.cerrarConexion();
			
		}catch (SQLException e) 
		{
			e.printStackTrace();
		}


		return nombre;
	}
	
	
	
	@Override
	public int obtenerId(String nombre) {
		
		int idloc = -1;
		String consulta = "SELECT id from localidad where nombre like ? ";
		Conexion conexion = Conexion.getConexion();
		PreparedStatement statement;
		
		try{
			statement = conexion.getSQLConexion().prepareStatement(consulta);
			statement.setString(1, nombre);
			ResultSet resultado = statement.executeQuery();
			
			while(resultado.next()){
				idloc = (resultado.getInt("id"));
			}
			
			//conexion.cerrarConexion();
			
		}catch (SQLException e) 
		{
			e.printStackTrace();
		}


		return idloc;
	}

	@Override
	public int obtenerIdProvincia(int idloc) {


		
		try {
				Class.forName("com.mysql.cj.jdbc.Driver");
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		int idprov=0;
			String consulta = "SELECT provincia_id from localidad where id = ? ";
			Connection conexion = null;
			try{
				conexion = DriverManager.getConnection("jdbc:mysql://localhost:3306/bd_integrador","root","root");
				PreparedStatement miSentencia = conexion.prepareStatement(consulta);
				miSentencia.setInt(1, idloc);
				ResultSet resultado = miSentencia.executeQuery();
				
				while(resultado.next()){
					idprov = (resultado.getInt("provincia_id"));
				}
				
			}catch (SQLException e) 
			{
				e.printStackTrace();
			}


			return idprov;
	}

	
}
