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
import DAOImpl.LocalidadDAOImpl;
import DAOImpl.ProvinciaDAOImpl;
import Entidad.Alumno;
import Entidad.Localidad;
import Entidad.Provincia;

/**
 * Servlet implementation class Servlet_Alumnos
 */
@WebServlet("/Servlet_Alumnos")
public class Servlet_Alumnos extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public Servlet_Alumnos() {
		super();
		// TODO Auto-generated constructor stub
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		
		int legajo = 0;
		String dni = null;
		String nombre=null;
		String apellido=null;
		
		if (request.getParameter("btnBuscar")!=null) {			
						
			
		/*	if (request.getParameter("txtLegajo")!=null) {
				legajo = (Integer.parseInt(request.getParameter("txtLegajo")));					
			}*/
			
			if ((request.getParameter("txtLegajo")).equals("")) {
				  legajo=0;				
				} else {legajo = Integer.parseInt(request.getParameter("txtLegajo"));}
			
		  if ((request.getParameter("txtDni")).equals("")) {
			  dni=null;				
			} else {dni = (request.getParameter("txtDni"));}
		  
		  if ((request.getParameter("txtNombre")).equals("")) {
			  nombre=null;				
			} else {nombre = (request.getParameter("txtNombre"));}
		  
		  if ((request.getParameter("txtApellido")).equals("")) {
			  apellido=null;				
			} else {apellido = (request.getParameter("txtApellido"));}
		
		  /*
		  if (request.getParameter("txtNombre")!=null) {
				nombre = (request.getParameter("txtNombre"));				
			}
			
		  if (request.getParameter("txtApellido")!=null) {
				apellido = (request.getParameter("txtApellido"));
			}*/
			
				AlumnoDAOImpl aludao = new AlumnoDAOImpl();
				ArrayList<Alumno> listaFiltrada = aludao.ListaFiltrada(legajo,dni,nombre,apellido);
				
		      	//REQUESTDISPATCHER para volver al JSP
	            request.setAttribute("listaF", listaFiltrada);
	            RequestDispatcher miDispacher = request.getRequestDispatcher("/ADMIN/AlumnosMain.jsp"); 
	    		miDispacher.forward(request, response);
			
			
			
		}
		

		
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		
        boolean actualizo=false;
        if(request.getParameter("btnActualizar")!=null)
        {
        	//se esta modificando un alumno en la bd
        	Alumno alu = new Alumno();        	
                	
        	int legajo = (Integer.parseInt(request.getParameter("txtLegajo")));
        	alu.setDni(request.getParameter("txtDni"));
        	alu.setNombre(request.getParameter("txtNombre"));
        	alu.setApellido(request.getParameter("txtApellido"));
        	alu.setFechanac(request.getParameter("txtFechanac"));
        	alu.setTelefono(request.getParameter("txtTel"));
        	alu.setDireccion(request.getParameter("txtDir"));        	
        	alu.setMail(request.getParameter("txtEmail"));
        	
        	Localidad localidad = new Localidad();
        	LocalidadDAOImpl ldao = new LocalidadDAOImpl();        	     	
        	
        	localidad.setId(Integer.parseInt(request.getParameter("selLocalidad")));
        	localidad.setNombre(ldao.obtenerLocalidad(Integer.parseInt(request.getParameter("selLocalidad"))));
        	
        	alu.setLocalidad(localidad);       	        	        	
        	AlumnoDAOImpl aludao = new AlumnoDAOImpl();        	
        	
        	actualizo = aludao.ModificarAlumno(alu,legajo);
        	ArrayList<Alumno> lista = aludao.ListarAlumnosAlta();
        	
        	//REQUESTDISPATCHER para volver al JSP
            request.setAttribute("listaA", lista);
        	request.setAttribute("act", actualizo);
            RequestDispatcher miDispacher = request.getRequestDispatcher("/ADMIN/AlumnosMain.jsp"); 
    		miDispacher.forward(request, response);

        }
		
		
		
		

		if (request.getParameter("btnListar") != null) {
			// lista todos los alumnos

			AlumnoDAOImpl aludao = new AlumnoDAOImpl();
			ArrayList<Alumno> lista = aludao.ListarAlumnosAlta();

			// REQUESTDISPATCHER para volver al JSP
			request.setAttribute("listaA", lista);
			RequestDispatcher miDispacher = request.getRequestDispatcher("/ADMIN/AlumnosMain.jsp");
			miDispacher.forward(request, response);

		}
		
		
		
		
			boolean elimino = false;
		if (request.getParameter("btnEliminar") != null) {
			// se esta dando de baja logicamente un alumno de la bd
			int legajo = Integer.parseInt(request.getParameter("lega").toString());

			AlumnoDAOImpl aludao = new AlumnoDAOImpl();
			elimino = aludao.delete(legajo);

			// REQUESTDISPATCHER para volver al JSP

			ArrayList<Alumno> lista = aludao.ListarAlumnosAlta();
			request.setAttribute("listaA", lista);
			request.setAttribute("eli", elimino);
			RequestDispatcher miDispacher = request.getRequestDispatcher("/ADMIN/AlumnosMain.jsp");
			miDispacher.forward(request, response);

			
			
			
			
		}

		if (request.getParameter("btnModificar") != null) {
			// se esta modificando un alumno de la bd
			Alumno alu = new Alumno();
			alu.setLegajo(Integer.parseInt(request.getParameter("lega").toString()));
			alu.setDni(request.getParameter("dni").toString());
			alu.setNombre(request.getParameter("nom").toString());
			alu.setApellido(request.getParameter("ape").toString());
			alu.setFechanac(request.getParameter("fechanac").toString());
			alu.setDireccion(request.getParameter("dir").toString());			
			alu.setMail(request.getParameter("mail").toString());
			alu.setTelefono(request.getParameter("tel").toString());
			
			LocalidadDAOImpl ldao = new LocalidadDAOImpl();
			ProvinciaDAOImpl pdao = new ProvinciaDAOImpl();
			
			Localidad localidad = new Localidad();
			localidad.setId(Integer.parseInt(request.getParameter("loc").toString()));
			localidad.setNombre(ldao.obtenerLocalidad(Integer.parseInt(request.getParameter("loc").toString())));			
			alu.setLocalidad(localidad);
			
			Provincia provincia = new Provincia();
			provincia.setId(Integer.parseInt(request.getParameter("prov").toString()));
			provincia.setNombre(pdao.obtenerProvincia(Integer.parseInt(request.getParameter("prov").toString())));
			alu.setProvincia(provincia);
			
    		//lista provincias
			ArrayList<Provincia> listap = pdao.ListarProvincias();			
	        
			//lista localidades	
			ArrayList<Localidad> listal = ldao.ListarLocalidades();

			
			// REQUESTDISPATCHER para volver al JSP

			//ArrayList<Alumno> lista = aludao.ListarAlumnosAlta();
			request.setAttribute("listaL", listal);
		    request.setAttribute("listaP", listap);
			request.setAttribute("alumno", alu);
			RequestDispatcher miDispacher = request.getRequestDispatcher("/ADMIN/AlumnosDetail.jsp");
			miDispacher.forward(request, response);
			
			

		}

		
		 boolean insert=false;

	        if(request.getParameter("btnGuardar")!=null)
	        {
	        	//se esta guardando un alumno en la bd
	        	Alumno alu = new Alumno();
	        	
	        	//alu.setLegajo(Integer.parseInt(request.getParameter("txtLegajo")));
	        	alu.setDni(request.getParameter("txtDni"));
	        	alu.setNombre(request.getParameter("txtNombre"));
	        	alu.setApellido(request.getParameter("txtApellido"));
	        	alu.setFechanac(request.getParameter("txtFechanac"));
	        	alu.setTelefono(request.getParameter("txtTel"));
	        	alu.setDireccion(request.getParameter("txtDir"));	
	        	alu.setMail(request.getParameter("txtEmail"));
	        	
	        	Localidad localidad = new Localidad();
	        	localidad.setNombre((request.getParameter("selLocalidad")));	        	
	        	LocalidadDAOImpl ldao = new LocalidadDAOImpl();
	        	localidad.setId(ldao.obtenerId((request.getParameter("selLocalidad"))));	        	
	        	alu.setLocalidad(localidad);         	 
	        	
	        	AlumnoDAOImpl aludao = new AlumnoDAOImpl();
	        	
	        	
				insert = aludao.insertAlumno(alu); 
					
					request.setAttribute("ins", insert);
					ArrayList<Alumno> lista = aludao.ListarAlumnosAlta();
					request.setAttribute("listaA", lista);
		            RequestDispatcher miDispacher = request.getRequestDispatcher("/ADMIN/AlumnosMain.jsp"); 
		    		miDispacher.forward(request, response);

	        	

	        }
	        
	        
	        
	        if(request.getParameter("btnNuevo")!=null) {
				
	        		//lista provincias
					
					ProvinciaDAOImpl pdao = new ProvinciaDAOImpl();
					ArrayList<Provincia> listap = pdao.ListarProvincias();
					
			        
					//lista localidades
						
					LocalidadDAOImpl ldao = new LocalidadDAOImpl();
					ArrayList<Localidad> listal = ldao.ListarLocalidades();
					
						
					//REQUESTDISPATCHER para volver al JSP
				    request.setAttribute("listaL", listal);
				    request.setAttribute("listaP", listap);
				    RequestDispatcher miDispacher = request.getRequestDispatcher("/ADMIN/AlumnosDetail.jsp"); 
					miDispacher.forward(request, response);
							
			
			
			}


	        

		
	}

}
