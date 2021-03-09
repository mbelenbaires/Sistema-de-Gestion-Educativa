package Servlet;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DAOImpl.AlumnoDAOImpl;
import DAOImpl.AlumnosxcursoDAOImpl;
import DAOImpl.CursoDAOImpl;
import DAOImpl.ProfesorDAOImpl;
import DAOImpl.MateriaDAOImpl;
import Entidad.Alumno;
import Entidad.Curso;
import Entidad.Profesor;
import Entidad.Materia;

/**
 * Servlet implementation class Servlet_Cursos
 */
@WebServlet("/Servlet_Cursos")
public class Servlet_Cursos extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Servlet_Cursos() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		
	
		String materia = null;
		String profesor= null;
		
		if (request.getParameter("btnBuscar")!=null) {			

			
		  if ((request.getParameter("txtMateria")).equals("")) {
			  materia=null;				
			} else {materia = (request.getParameter("txtMateria"));}
		  
		  if ((request.getParameter("txtProfesor")).equals("")) {
			  profesor=null;				
			} else {profesor = (request.getParameter("txtProfesor"));}

			
				CursoDAOImpl cdao = new CursoDAOImpl();
				ArrayList<Curso> listaFiltrada = cdao.ListaFiltrada(materia,profesor);
				
		      	//REQUESTDISPATCHER para volver al JSP
	            request.setAttribute("listaF", listaFiltrada);
	            RequestDispatcher miDispacher = request.getRequestDispatcher("/ADMIN/Cursos.jsp"); 
	    		miDispacher.forward(request, response);
			
			
			
		}
		

		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 boolean actualizo=false;
	        if(request.getParameter("btnActualizar")!=null)
	        {
	        	//se esta modificando un curso en la bd
	        	
	        	Curso cur = new Curso();    	
	        	
	        	MateriaDAOImpl mdao = new MateriaDAOImpl();
	        	Materia mat = mdao.obtenerMateria(Integer.parseInt(request.getParameter("selMateria")));		        	
	        	cur.setMateria(mat);
	        	
	        	cur.setCuatrimestre(Integer.parseInt(request.getParameter("selCuatri")));
	        	
	        	cur.setCursoId(Integer.parseInt(request.getParameter("txtIdcurso")));
	        	
	        	cur.setAño(Integer.parseInt(request.getParameter("selAño")));
	        	
	        	ProfesorDAOImpl pdao = new ProfesorDAOImpl();
	        	Profesor profe = pdao.obtenerProfesor(Integer.parseInt(request.getParameter("selProfesor")));
	        	cur.setProfesor(profe);
	        	
	        	CursoDAOImpl cdao = new CursoDAOImpl();        	
	        	
				actualizo = cdao.ModificarCurso(cur);	        	        	

	        	ArrayList<Curso> lista = cdao.ListarCursos();
	        	
	        	//REQUESTDISPATCHER para volver al JSP
	        	request.setAttribute("act", actualizo);
	            request.setAttribute("listaC", lista);
	            RequestDispatcher miDispacher = request.getRequestDispatcher("/ADMIN/Cursos.jsp"); 
	    		miDispacher.forward(request, response);

	        }
			
			
			
			

			if (request.getParameter("btnListar") != null) {
				// lista todos los cursos

				CursoDAOImpl cdao = new CursoDAOImpl();
				ArrayList<Curso> lista = cdao.ListarCursos();

				// REQUESTDISPATCHER para volver al JSP
				request.setAttribute("listaC", lista);
				RequestDispatcher miDispacher = request.getRequestDispatcher("/ADMIN/Cursos.jsp");
				miDispacher.forward(request, response);

			}
			
			
			
			
				boolean elimino = false;
			if (request.getParameter("btnEliminar") != null) {
				// se esta dando de baja logicamente un curso de la bd
				int cursoid = Integer.parseInt(request.getParameter("id").toString());
				
				CursoDAOImpl cdao = new CursoDAOImpl();
				elimino = cdao.delete(cursoid);

				
				// REQUESTDISPATCHER para volver al JSP

				ArrayList<Curso> lista = cdao.ListarCursos();
				request.setAttribute("eli", elimino);
				request.setAttribute("listaC", lista);
				RequestDispatcher miDispacher = request.getRequestDispatcher("/ADMIN/Cursos.jsp");
				miDispacher.forward(request, response);				
				
			}
			
	        boolean inserto = false;
	        	if(request.getParameter("btnAgregarAlu")!=null) {
	        	
	        	
	        	int lega = Integer.parseInt(request.getParameter("txtLegajo"));
	        	int idcur = Integer.parseInt(request.getParameter("id"));
	        	
	        	AlumnosxcursoDAOImpl axcdao = new AlumnosxcursoDAOImpl();		        	
	        	ArrayList<Alumno> alumnosxcurso = new ArrayList<Alumno>();
	        	ArrayList<Integer> alumnos = axcdao.LegajosxCurso((idcur));
	        	
	        	if (alumnos.contains(lega)) {
	        		inserto = axcdao.Reactivar(lega,idcur);
	        	}
	        	else {
	        		inserto = axcdao.AltaAlumno(lega,idcur);
	        		} 
	        	
	        	AlumnoDAOImpl aludao = new AlumnoDAOImpl();					
				
				for(int leg : alumnos)
		    	{if (axcdao.obtenerActivo(leg, idcur)==1) {
					
				alumnosxcurso.add(aludao.obtenerAlumno(leg)); 
		    	}
						
		    	}	 

				CursoDAOImpl cdao = new CursoDAOImpl();
				Curso curso = cdao.conseguirCurso(idcur);	
				
	        	
				request.setAttribute("curso", curso);
				request.setAttribute("ins", inserto);
				request.setAttribute("listaAxC", alumnosxcurso);
			    RequestDispatcher miDispacher = request.getRequestDispatcher("/ADMIN/CursosxAlumnos.jsp"); 
				miDispacher.forward(request, response);
	        	
	        	
	        }
			
			boolean eliminoalu = false;

			if (request.getParameter("btnEliminarAlu") != null) {
				// se esta dando de baja un alumno de un curso de la bd
				
				int lega = Integer.parseInt(request.getParameter("lega").toString());   	
	        	
	        	int cursoid = Integer.parseInt(request.getParameter("id").toString());
	        	
	        	
	        	//ProfesorDAOImpl pdao = new ProfesorDAOImpl();
	        	//Profesor profe = pdao.obtenerProfesor(request.getParameter("profe"));
	        	//cur.setProfesor(profe);
				
				AlumnosxcursoDAOImpl axcdao = new AlumnosxcursoDAOImpl();
				eliminoalu = axcdao.delete(lega,cursoid);
				AlumnoDAOImpl aludao = new AlumnoDAOImpl();
				ArrayList<Alumno> alumnosxcurso = new ArrayList<Alumno>();
				ArrayList<Integer> alumnos = axcdao.LegajosxCurso(cursoid);
				
				
				for(int legajo : alumnos)
		    	{if (axcdao.obtenerActivo(legajo, cursoid)==1) {
					
				alumnosxcurso.add(aludao.obtenerAlumno(legajo)); 
		    	} }
				
				CursoDAOImpl cdao = new CursoDAOImpl();
				Curso curso = cdao.conseguirCurso(cursoid);

				// REQUESTDISPATCHER para volver al JSP

				request.setAttribute("curso", curso);
				request.setAttribute("elialu", eliminoalu);
				request.setAttribute("listaAxC", alumnosxcurso);
				RequestDispatcher miDispacher = request.getRequestDispatcher("/ADMIN/CursosxAlumnos.jsp");
				miDispacher.forward(request, response);				
				
			}

			if (request.getParameter("btnModificar") != null) {
				// se esta modificando un curso de la bd
				
				MateriaDAOImpl mdao = new MateriaDAOImpl();
				ProfesorDAOImpl pdao = new ProfesorDAOImpl();
				AlumnoDAOImpl aludao = new AlumnoDAOImpl();
				AlumnosxcursoDAOImpl axcdao = new AlumnosxcursoDAOImpl();
				ArrayList<Alumno> alumnosxcurso = new ArrayList<Alumno>();
				Curso cur = new Curso();	
				
				cur.setCursoId(Integer.parseInt(request.getParameter("id").toString()));
				cur.setMateria(mdao.obtenerMateria(Integer.parseInt(request.getParameter("materia").toString())));
				cur.setCuatrimestre(Integer.parseInt(request.getParameter("cuatri").toString()));
				cur.setAño(Integer.parseInt(request.getParameter("año").toString()));
				cur.setProfesor(pdao.obtenerProfesor(Integer.parseInt(request.getParameter("profe").toString())));				
				ArrayList<Integer> alumnos = axcdao.LegajosxCurso((Integer.parseInt(request.getParameter("id"))));
				
				
				for(int lega : alumnos)
		    	{if (axcdao.obtenerActivo(lega, cur.getCursoId())==1) {
					
				alumnosxcurso.add(aludao.obtenerAlumno(lega)); 
		    	}
						
		    	}
				
				cur.setAlumnos(alumnosxcurso);
								
        		//lista materias				
				ArrayList<Materia> listam = mdao.ListarMaterias();				
		        
				//lista profesores				
				ArrayList<Profesor> listap = pdao.ListarProfesores();
				
		
				//REQUESTDISPATCHER para volver al JSP
			    request.setAttribute("listaM", listam);
			    request.setAttribute("listaP", listap);
				request.setAttribute("curso", cur);
				//request.setAttribute("listaAxC", alumnosxcurso);
				RequestDispatcher miDispacher = request.getRequestDispatcher("/ADMIN/CursosDetail.jsp");
				miDispacher.forward(request, response);		
				

			}

			
			 boolean insert=false;

		        if(request.getParameter("btnGuardar")!=null)
		        {
		        	//se esta guardando un curso en la bd
		        	Curso cur = new Curso();
		        	
		        	MateriaDAOImpl mdao = new MateriaDAOImpl();
		        	Materia mat = mdao.obtenerMateria(Integer.parseInt(request.getParameter("selMateria")));		        	
		        	cur.setMateria(mat);
		        	cur.setCuatrimestre(Integer.parseInt(request.getParameter("selCuatri")));
		        	
		        	cur.setAño(Integer.parseInt(request.getParameter("selAño")));
		        	
		        	ProfesorDAOImpl pdao = new ProfesorDAOImpl();
		        	Profesor profe = pdao.obtenerProfesor(Integer.parseInt(request.getParameter("selProfesor")));
		        	cur.setProfesor(profe);
		        	
		        	CursoDAOImpl cdao = new CursoDAOImpl();      	
		        	
					insert = cdao.insertCurso(cur); 
						
						
					ArrayList<Curso> lista = cdao.ListarCursos();
					request.setAttribute("ins", insert);
					request.setAttribute("listaC", lista);
			        RequestDispatcher miDispacher = request.getRequestDispatcher("/ADMIN/Cursos.jsp"); 
			    	miDispacher.forward(request, response);	        	

		        }
		        
		        
		        
		        if(request.getParameter("btnNuevo")!=null) {
					
		        		//lista materias						
						MateriaDAOImpl mdao = new MateriaDAOImpl();
						ArrayList<Materia> listam = mdao.ListarMaterias();
						
				        
						//lista profesores							
						ProfesorDAOImpl pdao = new ProfesorDAOImpl();
						ArrayList<Profesor> listap = pdao.ListarProfesores();

				
						//REQUESTDISPATCHER para volver al JSP
					    request.setAttribute("listaM", listam);
					    request.setAttribute("listaP", listap);
					    RequestDispatcher miDispacher = request.getRequestDispatcher("/ADMIN/CursosDetail.jsp"); 
						miDispacher.forward(request, response);
								
				
				
				}
		        
		        if (request.getParameter("btnAlumnos")!=null) {
		        	
		        	MateriaDAOImpl mdao = new MateriaDAOImpl();
					ProfesorDAOImpl pdao = new ProfesorDAOImpl();
					AlumnoDAOImpl aludao = new AlumnoDAOImpl();
					AlumnosxcursoDAOImpl axcdao = new AlumnosxcursoDAOImpl();
					ArrayList<Alumno> alumnosxcurso = new ArrayList<Alumno>();
					Curso cur = new Curso();	
					
					cur.setCursoId(Integer.parseInt(request.getParameter("txtIdcurso")));
					cur.setMateria(mdao.obtenerMateria(Integer.parseInt(request.getParameter("selMateria"))));
					cur.setCuatrimestre(Integer.parseInt(request.getParameter("selCuatri")));
					cur.setAño(Integer.parseInt(request.getParameter("selAño")));
					cur.setProfesor(pdao.obtenerProfesor(Integer.parseInt(request.getParameter("selProfesor"))));				
					ArrayList<Integer> alumnos = axcdao.LegajosxCurso((Integer.parseInt(request.getParameter("txtIdcurso"))));
					
					
					for(int lega : alumnos)
			    	{if (axcdao.obtenerActivo(lega, cur.getCursoId())==1) {
						
					alumnosxcurso.add(aludao.obtenerAlumno(lega)); 
			    	}
							
			    	}
					
					cur.setAlumnos(alumnosxcurso);
		        	
		        	
		        	
					//REQUESTDISPATCHER para volver al JSP
					request.setAttribute("curso", cur);
					request.setAttribute("listaAxC", alumnosxcurso);
				    RequestDispatcher miDispacher = request.getRequestDispatcher("/ADMIN/CursosxAlumnos.jsp"); 
					miDispacher.forward(request, response);
		        	
		        }
		        

		        
	}

}
