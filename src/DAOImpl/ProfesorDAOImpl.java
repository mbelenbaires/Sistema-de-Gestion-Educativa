package DAOImpl;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import DAO.ProfesorDAO;
import Entidad.Localidad;
import Entidad.Profesor;
import Entidad.Provincia;

public class ProfesorDAOImpl implements ProfesorDAO {
	
	public ProfesorDAOImpl() {
		// TODO Auto-generated constructor stub
	}

	
	private static final String readall = "SELECT * FROM profesor";
	
	public ArrayList<Profesor> ListarProfesores2()
	{

		PreparedStatement statement;
		ResultSet resultSet; //Guarda el resultado de la query
		ArrayList<Profesor> profesores= new ArrayList<Profesor>();
		Conexion conexion = Conexion.getConexion();
			
		try 
		{
			statement = conexion.getSQLConexion().prepareStatement(readall);
			resultSet = statement.executeQuery();
			while(resultSet.next())
			{
				profesores.add(getProfesor(resultSet));
			}
			conexion.cerrarConexion();
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
		return profesores;
	}

	

	@Override
	public Profesor obtenerProfesor(String nombre) {
		Profesor prof = null;
		PreparedStatement statement;
		ResultSet resultSet; //Guarda el resultado de la query
		Conexion conexion = Conexion.getConexion();		
		try 
		{
			String query = ("SELECT * from bd_integrador.profesor where nombre like '%"+nombre+"%' or apellido like '%"+nombre+"%'");
			statement = conexion.getSQLConexion().prepareStatement(query);
			resultSet = statement.executeQuery();
			while(resultSet.next())
			{

				prof = getProfesor(resultSet);
			}
			//conexion.cerrarConexion();
		} 
		
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
		return prof;

	}
	
	
	@Override
	public Profesor obtenerProfesor(int legajo) {
			
		Profesor prof = null;
		String consulta = "SELECT * from profesor where legajo = ? ";
		Conexion conexion = Conexion.getConexion();
		PreparedStatement statement;
		
		try{
			statement = conexion.getSQLConexion().prepareStatement(consulta);
			statement.setInt(1, legajo);
			ResultSet resultado = statement.executeQuery();
			
			while(resultado.next()){
				
				prof = getProfesor(resultado);

			}
			
		}catch (SQLException e) 
		{
			e.printStackTrace();
		}


		return prof;

	}

	

	//metodos de la DB desarrollados
	
	public boolean InsertarProfesor(Profesor profe)
	{
		CallableStatement cst;
		Connection conexion = Conexion.getConexion().getSQLConexion();
		boolean isInsertExitoso = false;
		try
		{
			 cst = conexion.prepareCall("CALL Insert_Profesor(?,?,?,?,?,?,?,?)");
			 cst.setString(1, profe.getDni());
			 cst.setString(2, profe.getNombre());
			 cst.setString(3, profe.getApellido());
			 cst.setString(4, profe.getFechanac());
			 cst.setString(5, profe.getDireccion());
			 cst.setInt(6, profe.getLocalidad().getId());
			 cst.setString(7, profe.getMail());
			 cst.setString(8, profe.getTelefono());
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
	
	public boolean EliminarProfesor(int legajo)
	{

		CallableStatement cst;
		Connection conexion = Conexion.getConexion().getSQLConexion();
		boolean isdeleteExitoso = false;
		try 
		{
			cst = conexion.prepareCall("CALL Delete_Profesor(?)");
			cst.setInt(1, legajo);
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
	

	
	public boolean ModificarProfesor(Profesor unprofe, int legajo)
	{
		
		
		CallableStatement cst;
		Connection conexion = Conexion.getConexion().getSQLConexion();
		boolean modifico = false;
		try 
		{
			cst = conexion.prepareCall("CALL Modificar_Profesor(?,?,?,?,?,?,?,?,?)");
			cst.setInt(1, legajo);
			cst.setString(2, unprofe.getDni());
			 cst.setString(3, unprofe.getNombre());
			 cst.setString(4, unprofe.getApellido());
			 cst.setString(5, unprofe.getFechanac());
			 cst.setString(6, unprofe.getDireccion());
			 cst.setInt(7, unprofe.getLocalidad().getId());
			 cst.setString(8, unprofe.getMail());
			 cst.setString(9, unprofe.getTelefono());
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
	
	
	
	
	public ArrayList<Profesor> ListarProfesores()
	{
		
		PreparedStatement statement;
		ResultSet resultSet; //Guarda el resultado de la query
		ArrayList<Profesor> prof = new ArrayList<Profesor>();
		Conexion conexion = Conexion.getConexion();
		
		try 
		{
			String query = "SELECT * FROM bd_integrador.profesor where estado = 1;";
			statement = conexion.getSQLConexion().prepareStatement(query);
			resultSet = statement.executeQuery();
			while(resultSet.next())
			{

				prof.add(getProfesor(resultSet));
			}
			//conexion.cerrarConexion();
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
		return prof;
	}
	
	
	private Profesor getProfesor(ResultSet resultSet) throws SQLException
	{
		
		int legajo = resultSet.getInt("legajo");
		String dni = resultSet.getString("dni");
		String nombre = resultSet.getString("nombre");
		String apellido = resultSet.getString("apellido");
		String fechanac = resultSet.getString("fechanac");
		String direccion = resultSet.getString("direccion");
		//int localidad = resultSet.getInt("IdLocalidad");
		String mail = resultSet.getString("email");
		String telefono = resultSet.getString("telefono");
		int estado = resultSet.getInt("estado");
		
		LocalidadDAOImpl ldao = new LocalidadDAOImpl();
		int idloc = resultSet.getInt("IdLocalidad");
		String nombreloc = ldao.obtenerLocalidad(resultSet.getInt("IdLocalidad"));
		Localidad localidad = new Localidad();
		localidad.setId(idloc);
		localidad.setNombre(nombreloc);
		
		ProvinciaDAOImpl pdao = new ProvinciaDAOImpl();
		Provincia provincia = new Provincia();
		int idprov = ldao.obtenerIdProvincia(idloc);
		String nombreprov = pdao.obtenerProvincia(idprov);
		provincia.setId(idprov);
		provincia.setNombre(nombreprov);
		
		
		return new Profesor(legajo,dni, nombre, apellido,fechanac,direccion,localidad, provincia,mail,telefono,estado);
	}
	
	
	public ArrayList<Profesor> ListaFiltradaProfesor(int legajo, String dni, String nombre, String apellido)
	{
		
		PreparedStatement statement;
		ResultSet resultSet; //Guarda el resultado de la query
		ArrayList<Profesor> profes = new ArrayList<Profesor>();
		Conexion conexion = Conexion.getConexion();		
		try 
		{
			String query = ("SELECT * FROM profesor where legajo like '"+legajo+"' or dni like '"+dni+"' or nombre like '"+nombre+"' or apellido like '"+apellido+"'");
			statement = conexion.getSQLConexion().prepareStatement(query);
			resultSet = statement.executeQuery();
			while(resultSet.next())
			{

				profes.add(getProfesor(resultSet));
			}
			conexion.cerrarConexion();
		} 
		
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
		return profes;
	}



	@Override
	public int obtenerLegajo(String nombre) {
		// TODO Auto-generated method stub
		return 0;
	}



	public int obtenerUltimo() {
		int ultimo = 0;
		PreparedStatement statement;
		ResultSet resultSet; //Guarda el resultado de la query
		Conexion conexion = Conexion.getConexion();
		
		try 
		{
			String query = "select max(legajo) from profesor;";
			statement = conexion.getSQLConexion().prepareStatement(query);
			resultSet = statement.executeQuery();
			while(resultSet.next())
			{
				ultimo = (resultSet.getInt(1));
			}
		
			
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}		
		int prox = ultimo+1;
		return prox;
	}
	
	
}
