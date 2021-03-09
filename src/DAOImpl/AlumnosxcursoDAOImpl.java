package DAOImpl;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import DAO.AlumnosxCursoDAO;
import Entidad.Alumno;
import Entidad.AlumnosXCurso;

public class AlumnosxcursoDAOImpl implements AlumnosxCursoDAO {
	
	public AlumnosxcursoDAOImpl() {
		// TODO Auto-generated constructor stub
	}

	@Override	
	public ArrayList<Integer> LegajosxCurso(int idcurso)
	{
		
		ArrayList<Integer> legajos = new ArrayList<Integer>();
		String consulta = "SELECT Legajo FROM bd_integrador.alumnosxcurso where CursoId = ?;" ;
		PreparedStatement statement;
		Conexion conexion = Conexion.getConexion();
		try{
			statement = conexion.getSQLConexion().prepareStatement(consulta);
			statement.setInt(1, idcurso);
			ResultSet resultado = statement.executeQuery();
			
			while(resultado.next()){
				legajos.add(resultado.getInt("Legajo"));
			}
			
		}catch (SQLException e) 
		{
			e.printStackTrace();
		}

		return legajos;
	}
	
	@Override	
	public ArrayList<AlumnosXCurso> ListarNotasCursos(int curso)
	{
		String readall = "select * from (select @id := ?)alias,notascursos;";
		PreparedStatement statement;
		ResultSet resultSet; //Guarda el resultado de la query
		ArrayList<AlumnosXCurso> listNotas = new ArrayList<AlumnosXCurso>();
		Conexion conexion = Conexion.getConexion();
		try 
		{
			statement = conexion.getSQLConexion().prepareStatement(readall);
			statement.setInt(1, curso);
			resultSet = statement.executeQuery();
			AlumnosXCurso unaNota;
			while(resultSet.next()){
				unaNota = new AlumnosXCurso();
				unaNota.setCursoId(curso);
				unaNota.setLegajo(resultSet.getInt("Legajo"));
				unaNota.setNota1(resultSet.getInt("Nota1"));
				unaNota.setNota2(resultSet.getInt("Nota2"));
				unaNota.setRecueratorio1(resultSet.getInt("Recuperatorio1"));
				unaNota.setRecueratorio2(resultSet.getInt("Recuperatorio2"));
				unaNota.setEstado(resultSet.getInt("Estado"));
				
				Alumno unAlumno = new Alumno();
				unAlumno.setNombre(resultSet.getString("AlumnoNombre"));
				unAlumno.setApellido(resultSet.getString("AlumnoApellido"));
				unaNota.setAlumno(unAlumno);
				
				listNotas.add(unaNota);
			}
			conexion.cerrarConexion();
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
		return listNotas;
	}

	public boolean delete(int legajo, int idcurso)
	{

		CallableStatement cst;
		Connection conexion = Conexion.getConexion().getSQLConexion();
		boolean isdeleteExitoso = false;
		try 
		{
			cst = conexion.prepareCall("CALL delete_alumno_curso(?,?)");
			cst.setInt(1, legajo);
			cst.setInt(2, idcurso);
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
	
	public int obtenerActivo(int legajo, int idcurso) {
		
		int activo = -1;
		String consulta = "SELECT activo from alumnosxcurso where legajo = ? and cursoId = ? ";
		Conexion conexion = Conexion.getConexion();
		PreparedStatement statement;
		
		try{
			statement = conexion.getSQLConexion().prepareStatement(consulta);
			statement.setInt(1, legajo);
			statement.setInt(2, idcurso);
			ResultSet resultado = statement.executeQuery();
			
			while(resultado.next()){
				
			activo = resultado.getInt("activo");

			}
			//conexion.cerrarConexion();
			
		}catch (SQLException e) 
		{
			e.printStackTrace();
		}


		return activo;

	}
	
	public boolean AltaAlumno(int legajo, int idcurso) {
		
		CallableStatement cst;
		Connection conexion = Conexion.getConexion().getSQLConexion();
		boolean isInsertExitoso = false;
		try
		{
			 cst = conexion.prepareCall("CALL Insert_alumno_curso(?,?)");
			 cst.setInt(1, legajo);
			 cst.setInt(2, idcurso);
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
	
	public boolean Reactivar(int legajo, int idcurso) {
		
		CallableStatement cst;
		Connection conexion = Conexion.getConexion().getSQLConexion();
		boolean reactivo = false;
		try
		{
			 cst = conexion.prepareCall("CALL reactivar_alumno_curso(?,?)");
			 cst.setInt(1, legajo);
			 cst.setInt(2, idcurso);
			 if(cst.executeUpdate()> 0)
				{
					conexion.commit();
					reactivo = true;
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
		
		return reactivo;
	}
	
	@Override
	public int ListaNotasUpdate(ArrayList<AlumnosXCurso> lista)
	{
		
		
		CallableStatement cst;
		Connection conexion = Conexion.getConexion().getSQLConexion();
		int cant  = 0;
		try 
		{
			 for (AlumnosXCurso cursoAlum : lista) {
			     cst = conexion.prepareCall("CALL ActualizarNotas(?,?,?,?,?,?,?)");
				 cst.setInt(1, cursoAlum.getCursoId());
				 cst.setInt(2, cursoAlum.getLegajo());
				 cst.setInt(3, cursoAlum.getNota1());
				 cst.setInt(4, cursoAlum.getNota2());
				 cst.setInt(5, cursoAlum.getRecueratorio1());
				 cst.setInt(6, cursoAlum.getRecueratorio2());
				 cst.setInt(7, cursoAlum.getEstado());
				 if(cst.executeUpdate()> 0)
					{
					 conexion.commit();
						cant += 1;
					}
				
			 }
		

		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
		return cant;
	}	
	
	@Override	
	public ArrayList<AlumnosXCurso> ListarNotasCursos(int curso, String nombre, String apellido)
	{
		String readall = "SELECT `alumnosxcurso`.`CursoId`,`alumnosxcurso`.`Legajo`,`alumnosxcurso`.`Nota1`,`alumnosxcurso`.`Nota2`,`alumnosxcurso`.`Recuperatorio1`,`alumnosxcurso`.`Recuperatorio2`,`alumnosxcurso`.`Estado`, AlumnoNombre, AlumnoApellido FROM `bd_integrador`.`alumnosxcurso` inner join (select `alumno`.`Legajo` AlumnoLegajo,`alumno`.`Nombre` AlumnoNombre, `alumno`.`Apellido` AlumnoApellido FROM `bd_integrador`.`alumno`) alum on (alum.AlumnoLegajo = `alumnosxcurso`.`Legajo`) where `alumnosxcurso`.`CursoId` = ?";
		
		if (!(nombre.isEmpty()) ) 
			readall = readall + " and AlumnoNombre like ?";
		if (!(apellido.isEmpty())) 
			readall = readall + " and AlumnoApellido like ?";
		
		PreparedStatement statement;
		ResultSet resultSet; //Guarda el resultado de la query
		ArrayList<AlumnosXCurso> listNotas = new ArrayList<AlumnosXCurso>();
		Conexion conexion = Conexion.getConexion();
		try 
		{
			statement = conexion.getSQLConexion().prepareStatement(readall);
			statement.setInt(1, curso);
			if (!(nombre.isEmpty()) ) {
				statement.setString(2, nombre);
				if (!(apellido.isEmpty())) 
				statement.setString(3, apellido);
			} else {
				if (!(apellido.isEmpty())) 
					statement.setString(2, apellido);
			}
			
			
			resultSet = statement.executeQuery();
			AlumnosXCurso unaNota;
			while(resultSet.next()){
				unaNota = new AlumnosXCurso();
				unaNota.setCursoId(resultSet.getInt("CursoId"));
				unaNota.setLegajo(resultSet.getInt("Legajo"));
				unaNota.setNota1(resultSet.getInt("Nota1"));
				unaNota.setNota2(resultSet.getInt("Nota2"));
				unaNota.setRecueratorio1(resultSet.getInt("Recuperatorio1"));
				unaNota.setRecueratorio2(resultSet.getInt("Recuperatorio2"));
				unaNota.setEstado(resultSet.getInt("Estado"));
				
				Alumno unAlumno = new Alumno();
				unAlumno.setNombre(resultSet.getString("AlumnoNombre"));
				unAlumno.setApellido(resultSet.getString("AlumnoApellido"));
				unaNota.setAlumno(unAlumno);
				
				listNotas.add(unaNota);
			}
			conexion.cerrarConexion();
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
		return listNotas;
	}

		
}
	
