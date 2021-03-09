package DAOImpl;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import DAO.CursoDAO;
import Entidad.Alumno;
import Entidad.Curso;
import Entidad.Materia;
import Entidad.Profesor;

public class CursoDAOImpl implements CursoDAO{
	

	private static final String readCursoDocente = "SELECT `curso`.`cursoId`,`curso`.`cuatrimestre`,`curso`.`anio`,`curso`.`profesorLegajo`,`curso`.`materiaId`,  mat.`nombre` FROM `bd_integrador`.`curso` inner join (SELECT `materia`.`materiaId`,`materia`.`nombre` FROM `bd_integrador`.`materia`) mat on (mat.`materiaId` = `curso`.`materiaId`) where `curso`.`profesorLegajo` = ?";
	
	public CursoDAOImpl() {
		// TODO Auto-generated constructor stub
	}
	
	
	public ArrayList<Curso> ListarCursos()
	{

		PreparedStatement statement;
		ResultSet resultSet; //Guarda el resultado de la query*/
		ArrayList<Curso> cursos = new ArrayList<Curso>();
		Conexion conexion = Conexion.getConexion();
		String query = "SELECT * FROM bd_integrador.curso where fechaBaja is null;";
		try 
		{
			statement = conexion.getSQLConexion().prepareStatement(query);
			resultSet = statement.executeQuery();
			while(resultSet.next())
			{						
				cursos.add(getCurso(resultSet));
			}
			
			
		} 
		
		catch (SQLException e) 
		{
			e.printStackTrace();
		}

		conexion.cerrarConexion();
		return cursos;
		
		
	}
	
	public Curso getCurso(ResultSet resultSet) throws SQLException
	{
		ProfesorDAOImpl pdao = new ProfesorDAOImpl();
		MateriaDAOImpl mdao = new MateriaDAOImpl();
		AlumnoDAOImpl aludao = new AlumnoDAOImpl();
		AlumnosxcursoDAOImpl axcdao = new AlumnosxcursoDAOImpl();
		ArrayList<Alumno> alumnosxcurso = new ArrayList<Alumno>();
		
		int idcurso = resultSet.getInt("cursoId");
		Materia materia = mdao.obtenerMateria(resultSet.getInt("materiaId"));
		int cuatri = resultSet.getInt("cuatrimestre");
		int ano = resultSet.getInt("anio");
		Profesor prof = pdao.obtenerProfesor((resultSet.getInt("profesorLegajo")));
		java.sql.Date fechabaja = resultSet.getDate("fechaBaja");		
		ArrayList<Integer> alumnos = axcdao.LegajosxCurso(idcurso);
		
		for(int lega : alumnos)
    	{
			
		alumnosxcurso.add(aludao.obtenerAlumno(lega)); 
				
    	}
		return new Curso (idcurso,materia,cuatri,ano,prof,fechabaja,alumnosxcurso);

	}
	
	@Override
	public Curso UnCursoGet(int curso)
	{
		Curso unCurso = new Curso();
		String consulta = "SELECT `curso`.`cursoId`,`curso`.`cuatrimestre`,`curso`.`anio`,`curso`.`profesorLegajo`,`curso`.`fechaBaja`,`curso`.`materiaId`,  mat.`nombre` FROM `bd_integrador`.`curso` inner join (SELECT `materia`.`materiaId`,`materia`.`nombre` FROM `bd_integrador`.`materia`) mat on (mat.`materiaId` = `curso`.`materiaId`) where `curso`.`cursoId` = ?";
		PreparedStatement statement;
		ResultSet resultSet; //Guarda el resultado de la query
		Conexion conexion = Conexion.getConexion();
		try 
		{
			statement = conexion.getSQLConexion().prepareStatement(consulta);
			statement.setInt(1,curso);
			resultSet = statement.executeQuery();
			resultSet.next();
			
			unCurso.setCursoId(resultSet.getInt("cursoId"));
			unCurso.setAño(resultSet.getInt("anio"));
			unCurso.setCuatrimestre(resultSet.getInt("cuatrimestre"));
			unCurso.setFechabaja(resultSet.getDate("fechaBaja"));
			
			Profesor unProfesor = new Profesor();
			unProfesor.setLegajo(resultSet.getInt("profesorLegajo"));
			unCurso.setProfesor(unProfesor);
				
			Materia unaMateria = new Materia();
			unaMateria.setIdmateria(resultSet.getInt("materiaId"));
			unaMateria.setNombre(resultSet.getString("nombre"));
				
			unCurso.setMateria(unaMateria);
			conexion.cerrarConexion();
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
		return unCurso;
	}
	
	@Override
	public boolean insertCurso(Curso curso) {
		
		CallableStatement cst;
		Connection conexion = Conexion.getConexion().getSQLConexion();
		boolean isInsertExitoso = false;
		try
		{
			 cst = conexion.prepareCall("CALL Insert_Curso(?,?,?,?)");
			 cst.setInt(1, (curso.getMateria()).getIdmateria());
			 cst.setInt(2, curso.getCuatrimestre());
			 cst.setInt(3, curso.getAño());
			 cst.setInt(4, (curso.getProfesor()).getLegajo());
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

	@Override
	public boolean delete(int idcurso) {
		CallableStatement cst;
		Connection conexion = Conexion.getConexion().getSQLConexion();
		boolean isdeleteExitoso = false;
		try 
		{
			cst = conexion.prepareCall("CALL delete_curso(?)");
			cst.setInt(1, idcurso);
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


	@Override
	public boolean ModificarCurso(Curso unCurso) {
		
		CallableStatement cst;
		Connection conexion = Conexion.getConexion().getSQLConexion();
		boolean modifico = false;
		try 
		{
			cst = conexion.prepareCall("CALL modificar_curso(?,?,?,?,?)");
			cst.setInt(1,unCurso.getCursoId());
			 cst.setInt(2, unCurso.getMateria().getIdmateria());
			 cst.setInt(3, unCurso.getCuatrimestre());
			 cst.setInt(4, unCurso.getAño());
			 cst.setInt(5, unCurso.getProfesor().getLegajo());
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


	public List<Curso> CursosXProfesor(int legajo)
	{
		PreparedStatement statement;
		ResultSet resultSet; //Guarda el resultado de la query
		ArrayList<Curso> cursos = new ArrayList<Curso>();
		Conexion conexion = Conexion.getConexion();
		try 
		{
			statement = conexion.getSQLConexion().prepareStatement(readCursoDocente);
			statement.setInt(1,legajo);
			resultSet = statement.executeQuery();
			Curso unCurso;
			while(resultSet.next()){
				unCurso = new Curso();
				unCurso.setAño(resultSet.getInt("anio"));
				unCurso.setCuatrimestre(resultSet.getInt("cuatrimestre"));
				unCurso.setCursoId(resultSet.getInt("cursoId"));
				
				Profesor unProfesor = new Profesor();
				unProfesor.setLegajo(resultSet.getInt("profesorLegajo"));
				unCurso.setProfesor(unProfesor);
				
				Materia unaMateria = new Materia();
				unaMateria.setIdmateria(resultSet.getInt("materiaId"));
				unaMateria.setNombre(resultSet.getString("nombre"));
				
				unCurso.setMateria(unaMateria);
				cursos.add(unCurso);
			}
			conexion.cerrarConexion();
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
		return cursos;
	}

	
	public Curso conseguirCurso(int id) {
		Curso curso = null;

			String consulta = "SELECT * from curso where cursoId = ? ";
			Conexion conexion = Conexion.getConexion();
			PreparedStatement statement;
			
			try{
				statement = conexion.getSQLConexion().prepareStatement(consulta);
				statement.setInt(1, id);
				ResultSet resultado = statement.executeQuery();
				
				while(resultado.next()){
					
				curso = getCurso(resultado);

				}
				
			}catch (SQLException e) 
			{
				e.printStackTrace();
			}
		
		
		return curso;
	}

//	@Override
	public ArrayList<Curso> ListaFiltrada(String materia, String profesor) {
		PreparedStatement statement;
		ResultSet resultSet; //Guarda el resultado de la query
		ArrayList<Curso> cursos = new ArrayList<Curso>();
		Conexion conexion = Conexion.getConexion();		
		int lega = 0;
		try 
		{
			
			if (profesor!=null) {
				ProfesorDAOImpl pdao = new ProfesorDAOImpl();
				Profesor profe = pdao.obtenerProfesor(profesor);
				lega = profe.getLegajo();
			} 
			
			MateriaDAOImpl mdao = new MateriaDAOImpl();

			ArrayList<Integer> materias = mdao.obtenerMaterias(materia);
			
			if(materias.isEmpty()) {
				int mat =0;
				String query = ("SELECT * FROM curso where fechaBaja is null and (profesorLegajo='"+lega+"' or materiaId='"+mat+"');");
				
				statement = conexion.getSQLConexion().prepareStatement(query);
				resultSet = statement.executeQuery();
				while(resultSet.next())
				{

					cursos.add(getCurso(resultSet));
				}
				//conexion.cerrarConexion();

			}
			else {
				for (int mat : materias) {
					
					//VER QUERY
					String query = ("SELECT * FROM curso where fechaBaja is null and (profesorLegajo='"+lega+"' or materiaId='"+mat+"');");
					
					statement = conexion.getSQLConexion().prepareStatement(query);
					resultSet = statement.executeQuery();
					while(resultSet.next())
					{

						cursos.add(getCurso(resultSet));
					}
					//conexion.cerrarConexion();
					
					
				}
				
				
			}
			

		} 
		
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
		return cursos;
	}
	
	public int cantidadAlumnos(int idCurso) {
		int cantidad = 0;
		String consulta = "call bd_integrador.alumnosxcurso(?);";
		PreparedStatement statement;
		Conexion conexion = Conexion.getConexion();
		try{
			statement = conexion.getSQLConexion().prepareStatement(consulta);
			statement.setInt(1, idCurso);
			ResultSet resultado = statement.executeQuery();
			
			while(resultado.next()){
				cantidad = resultado.getInt("CANTIDAD_ALUMNOS");
			}
			conexion.cerrarConexion();
			
		}catch (SQLException e) 
		{
			e.printStackTrace();
		}

		return cantidad;
	}


	public int Aprobados(int idCurso) {
		int cantidad = 0;
		String consulta = "call porcentajeaprobadosxcurso(?);";
		PreparedStatement statement;
		Conexion conexion = Conexion.getConexion();
		try{
			statement = conexion.getSQLConexion().prepareStatement(consulta);
			statement.setInt(1, idCurso);
			ResultSet resultado = statement.executeQuery();
			
			while(resultado.next()){
				cantidad = resultado.getInt("apro");
			}
			conexion.cerrarConexion();
			
		}catch (SQLException e) 
		{
			e.printStackTrace();
		}

		return cantidad;
	}


	public int Desaprobados(int idCurso) {
		int cantidad = 0;
		String consulta = "call bd_integrador.porcentajedesaprobadosxcurso(?);";
		PreparedStatement statement;
		Conexion conexion = Conexion.getConexion();
		try{
			statement = conexion.getSQLConexion().prepareStatement(consulta);
			statement.setInt(1, idCurso);
			ResultSet resultado = statement.executeQuery();
			
			while(resultado.next()){
				cantidad = resultado.getInt("desapro");
			}
			conexion.cerrarConexion();
			
		}catch (SQLException e) 
		{
			e.printStackTrace();
		}

		return cantidad;
	}
	
	public int obtenerReporte(int idCurso) {
		
		
		
		
		return 0;
	}

}
