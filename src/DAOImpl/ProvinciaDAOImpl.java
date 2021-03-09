package DAOImpl;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.sql.Connection;
import java.sql.DriverManager;
import DAO.ProvinciaDAO;
import Entidad.Provincia;

public class ProvinciaDAOImpl implements ProvinciaDAO {
	
private static final String readall = "SELECT * FROM provincia";
	
	public ProvinciaDAOImpl() {
		// TODO Auto-generated constructor stub
	}

	public ArrayList<Provincia> ListarProvincias()
	{

		PreparedStatement statement;
		ResultSet resultSet; //Guarda el resultado de la query
		ArrayList<Provincia> provincias = new ArrayList<Provincia>();
		Conexion conexion = Conexion.getConexion();
			
		try 
		{
			statement = conexion.getSQLConexion().prepareStatement(readall);
			resultSet = statement.executeQuery();
			while(resultSet.next())
			{
				provincias.add(getProvincia(resultSet));
			}
			
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
		return provincias;
	}

	private Provincia getProvincia(ResultSet resultSet) throws SQLException
	{
		
		int id = resultSet.getInt("id");
		String nombre = resultSet.getString("nombre");
		String codigo = resultSet.getString("codigo31662");
		return new Provincia(id,nombre,codigo);
	}

	@Override
	public String obtenerProvincia(int idprov) {

	String nombreprov=null;
		String consulta = "SELECT nombre from provincia where id = ? ";
		Connection conexion = null;
		try{
			conexion = DriverManager.getConnection("jdbc:mysql://localhost:3306/bd_integrador","root","root");
			PreparedStatement miSentencia = conexion.prepareStatement(consulta);
			miSentencia.setInt(1, idprov);
			ResultSet resultado = miSentencia.executeQuery();
			
			while(resultado.next()){
				nombreprov = (resultado.getString("nombre"));
			}
			
		}catch (SQLException e) 
		{
			e.printStackTrace();
		}


		return nombreprov;
	}
	

}
