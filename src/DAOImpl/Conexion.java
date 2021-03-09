package DAOImpl;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexion {

	public static Conexion instancia;
	private Connection connection;

	Conexion()
	{
		try
		{
			try {
				Class.forName("com.mysql.cj.jdbc.Driver");
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			this.connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/bd_integrador?autoReconnect=true&failOverReadOnly=false&maxReconnects=10","root","root");
			//this.connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/bd_integrador","root","root");
			//this.connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/bd_integrador?autoReconnect=true&failOverReadOnly=false&maxReconnects=10","root","admin");
			//this.connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/bd_integrador","root","admin");
			this.connection.setAutoCommit(false);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	//metodo para abrir la conexion
	public static Conexion getConexion()   
	{								
		if(instancia == null)
		{
			instancia = new Conexion();
		}
		return instancia;
	}
	
	//metodo para abrir la conexion
	public Connection getSQLConexion() 
	{
		return this.connection;
	}
	
	//metodo para cerrar la conexion
	public void cerrarConexion()
	{
		try 
		{
			this.connection.close();
		}
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
		instancia = null;
	}
	
}
