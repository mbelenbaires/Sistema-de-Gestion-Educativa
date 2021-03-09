package DAOImpl;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import DAO.AlumnoDao;
import Entidad.Alumno;
import Entidad.Localidad;
import Entidad.Provincia;

public class AlumnoDAOImpl implements AlumnoDao
{

	
	//private static final String readall = "SELECT * FROM alumno";
	
	public AlumnoDAOImpl() {
		// TODO Auto-generated constructor stub
	}
	
	public boolean insertAlumno(Alumno alumno)
	{
		CallableStatement cst;
		Connection conexion = Conexion.getConexion().getSQLConexion();
		boolean isInsertExitoso = false;
		try
		{
			 cst = conexion.prepareCall("CALL Insert_Alumno(?,?,?,?,?,?,?,?)");
			 //cst.setInt(1, alumno.getLegajo());
			 cst.setString(1, alumno.getDni());
			 cst.setString(2, alumno.getNombre());
			 cst.setString(3, alumno.getApellido());
			 cst.setString(4, alumno.getFechanac());
			 cst.setString(5, alumno.getDireccion());
			 cst.setInt(6, alumno.getLocalidad().getId());
			 cst.setString(7, alumno.getMail());
			 cst.setString(8, alumno.getTelefono());
			 //cst.setInt(9, alumno.getEstado());
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
	
	public boolean delete(int legajo)
	{

		CallableStatement cst;
		Connection conexion = Conexion.getConexion().getSQLConexion();
		boolean isdeleteExitoso = false;
		try 
		{
			cst = conexion.prepareCall("CALL delete_alumno(?)");
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
	

	
	public boolean ModificarAlumno(Alumno unAlumno, int legajo)
	{
		
		
		CallableStatement cst;
		Connection conexion = Conexion.getConexion().getSQLConexion();
		boolean modifico = false;
		try 
		{
			cst = conexion.prepareCall("CALL modificar_alumno(?,?,?,?,?,?,?,?,?)");
			cst.setInt(1, legajo);
			cst.setString(2, unAlumno.getDni());
			 cst.setString(3, unAlumno.getNombre());
			 cst.setString(4, unAlumno.getApellido());
			 cst.setString(5, unAlumno.getFechanac());
			 cst.setString(6, unAlumno.getDireccion());
			 cst.setInt(7, unAlumno.getLocalidad().getId());
			 cst.setString(8, unAlumno.getMail());
			 cst.setString(9, unAlumno.getTelefono());
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
	
	
	
	
	public ArrayList<Alumno> ListarAlumnosAlta()
	{
		
		PreparedStatement statement;
		ResultSet resultSet1; //Guarda el resultado de la query
		ArrayList<Alumno> alumnos = new ArrayList<Alumno>();
		Conexion conexion = Conexion.getConexion();
		
		try 
		{
			String query = "SELECT * FROM bd_integrador.alumno where estado = 1;";
			statement = conexion.getSQLConexion().prepareStatement(query);
			resultSet1 = statement.executeQuery();
			while(resultSet1.next())
			{

				alumnos.add(getAlumno(resultSet1));
			}
			
			
			
			conexion.cerrarConexion();
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
		return alumnos;
	}
	
	

	
	private Alumno getAlumno(ResultSet resultSet) throws SQLException
	{
		
		int legajo = resultSet.getInt("Legajo");
		String dni = resultSet.getString("Dni");
		String nombre = resultSet.getString("Nombre");
		String apellido = resultSet.getString("Apellido");
		String fechanac = resultSet.getString("Fechanac");
		String direccion = resultSet.getString("Direccion");
		String mail = resultSet.getString("Email");
		String telefono = resultSet.getString("Telefono");
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
		
		
		return new Alumno(legajo,dni, nombre, apellido,fechanac,direccion,localidad,mail,telefono,estado,provincia);
	}
	
	
	public ArrayList<Alumno> ListaFiltrada(int lega, String dnia, String nombrea, String apellidoa)
	{
		
		PreparedStatement statement;
		ResultSet resultSet; //Guarda el resultado de la query
		ArrayList<Alumno> alumnos = new ArrayList<Alumno>();
		Conexion conexion = Conexion.getConexion();		
		try 
		{
			String query = ("SELECT * FROM alumno where legajo like '"+lega+"' or dni like '"+dnia+"' or nombre like '"+nombrea+"' or apellido like '"+apellidoa+"'");
			statement = conexion.getSQLConexion().prepareStatement(query);
			resultSet = statement.executeQuery();
			while(resultSet.next())
			{

				alumnos.add(getAlumno(resultSet));
			}
			conexion.cerrarConexion();
		} 
		
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
		return alumnos;
	}
	
	
	
	@Override
	public Alumno obtenerAlumno(int legajo) {
			
		Alumno alu = null;
		String consulta = "SELECT * from alumno where legajo = ? ";
		Conexion conexion = Conexion.getConexion();
		PreparedStatement statement;
		
		try{
			statement = conexion.getSQLConexion().prepareStatement(consulta);
			statement.setInt(1, legajo);
			ResultSet resultado = statement.executeQuery();
			
			while(resultado.next()){
				
			alu = getAlumno(resultado);

			}
			
		}catch (SQLException e) 
		{
			e.printStackTrace();
		}


		return alu;

	}

	public ArrayList<Alumno> ListarAlumnosxCurso(int legajo)
	{
		
		PreparedStatement statement;
		ResultSet resultSet; //Guarda el resultado de la query
		ArrayList<Alumno> alumnos = new ArrayList<Alumno>();
		Conexion conexion = Conexion.getConexion();
		
		try 
		{
			String query = "SELECT * FROM bd_integrador.alumno where estado = 1;";
			statement = conexion.getSQLConexion().prepareStatement(query);
			resultSet = statement.executeQuery();
			while(resultSet.next())
			{

				alumnos.add(getAlumno(resultSet));
			}
			conexion.cerrarConexion();
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
		return alumnos;
	}



}
