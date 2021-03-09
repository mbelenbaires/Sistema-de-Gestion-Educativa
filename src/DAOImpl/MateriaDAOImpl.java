package DAOImpl;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import DAO.MateriaDAO;
import Entidad.Materia;

public class MateriaDAOImpl implements MateriaDAO{


	
private static final String readall = "SELECT * FROM materia";
	
	public MateriaDAOImpl() {
		// TODO Auto-generated constructor stub
	}

	public ArrayList<Materia> ListarMaterias()
	{

		PreparedStatement statement;
		ResultSet resultSet; //Guarda el resultado de la query
		ArrayList<Materia> materias= new ArrayList<Materia>();
		Conexion conexion = Conexion.getConexion();
			
		try 
		{
			statement = conexion.getSQLConexion().prepareStatement(readall);
			resultSet = statement.executeQuery();
			while(resultSet.next())
			{
				materias.add(getMateria(resultSet));
			}
			//conexion.cerrarConexion();
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
		return materias;
	}

	private Materia getMateria(ResultSet resultSet) throws SQLException
	{
		
		int id = resultSet.getInt("materiaId");
		String nombre = resultSet.getString("nombre");
		return new Materia(id,nombre);
	}
	
	

	@Override
	public int obtenerMateria(String nombre) {		
			
			int idmat = 0;
			String consulta = "SELECT materiaId from materia where nombre like '"+nombre+"' ";
			Conexion conexion = Conexion.getConexion();
			PreparedStatement statement;
			
			try{
				statement = conexion.getSQLConexion().prepareStatement(consulta);
				ResultSet resultado = statement.executeQuery();
				
				while(resultado.next()){
					idmat = (resultado.getInt("materiaId"));
				}

				
				
			}catch (SQLException e) 
			{
				e.printStackTrace();
			}

			//conexion.cerrarConexion();
			return idmat;
		}

	
	public ArrayList<Integer> obtenerMaterias(String nombre) {		
		
		ArrayList<Integer> materias = new ArrayList<Integer>();
		String consulta = "SELECT materiaId from bd_integrador.materia where materia.nombre like '%"+nombre+"%';";
		Conexion conexion = Conexion.getConexion();
		PreparedStatement statement;
		
		try{
			statement = conexion.getSQLConexion().prepareStatement(consulta);
			ResultSet resultado = statement.executeQuery();
			
			while(resultado.next()){
				int id = resultado.getInt("materiaId");
				materias.add(id);
			}

			
			
		}catch (SQLException e) 
		{
			e.printStackTrace();
		}

		//conexion.cerrarConexion();
		return materias;
	}

	@Override
	public Materia obtenerMateria(int idmat) {
			
			Materia mat = new Materia();
			String consulta = "SELECT nombre from materia where materiaId like ? ";
			Conexion conexion = Conexion.getConexion();
			PreparedStatement statement;
			
			try{
				statement = conexion.getSQLConexion().prepareStatement(consulta);
				statement.setInt(1, idmat);
				ResultSet resultado = statement.executeQuery();
				
				while(resultado.next()){
					mat.setNombre(resultado.getString("nombre"));
				}
				mat.setIdmateria(idmat);
				
				
			}catch (SQLException e) 
			{
				e.printStackTrace();
			}

			return mat;
	}
		
			
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
	/*	
	Materia mat = null;
		String consulta = "SELECT * from materia where id = ? ";
		Connection conexion = null;
		try{
			conexion = DriverManager.getConnection("jdbc:mysql://localhost:3306/bd_integrador","root","root");
			PreparedStatement miSentencia = conexion.prepareStatement(consulta);
			miSentencia.setInt(1, idmat);
			ResultSet resultado = miSentencia.executeQuery();
			
			while(resultado.next()){
				mat = getMateria(resultado);
			}
			conexion.close();
			
		}catch (SQLException e) 
		{
			e.printStackTrace();
		}


		return mat;
	}
	
	*/
	
	
}
