package Servlet;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DAOImpl.ProfesorDAOImpl;
import Entidad.Profesor;

import DAOImpl.LocalidadDAOImpl;
import DAOImpl.ProvinciaDAOImpl;
import DAOImpl.UsuarioDAOImpl;
import Entidad.Localidad;
import Entidad.Provincia;
import Entidad.Usuario;


@WebServlet("/Servlet_Profesor")
public class Servlet_Profesor extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
    public Servlet_Profesor() {
        super();
        // TODO Auto-generated constructor stub
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		int legajo = 0;
		String dni = null;
		String nombre=null;
		String apellido=null;
		
		if (request.getParameter("btnBuscar")!=null) {			
			
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
					
				ProfesorDAOImpl profdao = new ProfesorDAOImpl();
				ArrayList<Profesor> listaFiltrada = profdao.ListaFiltradaProfesor(legajo,dni,nombre,apellido);
				
		      	//REQUESTDISPATCHER para volver al JSP
	            request.setAttribute("listaF", listaFiltrada);
	            RequestDispatcher miDispacher = request.getRequestDispatcher("/ADMIN/Profesores.jsp"); 
	    		miDispacher.forward(request, response);
			
		}
		
	}

	
	
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		
		 boolean actualizo=false;
	        if(request.getParameter("btnActualizar")!=null)
	        {
	        	//se esta modificando un prof en la bd
	        	Profesor prof = new Profesor();        	
	                	
	        	int legajo = (Integer.parseInt(request.getParameter("txtLegajo")));
	        	prof.setDni(request.getParameter("txtDni"));
	        	prof.setNombre(request.getParameter("txtNombre"));
	        	prof.setApellido(request.getParameter("txtApellido"));
	        	prof.setFechanac(request.getParameter("txtFechanac"));
	        	prof.setTelefono(request.getParameter("txtTel"));
	        	prof.setDireccion(request.getParameter("txtDir"));        	
	        	prof.setMail(request.getParameter("txtEmail"));

	        	
	        	Localidad localidad = new Localidad();
	        	LocalidadDAOImpl ldao = new LocalidadDAOImpl();

	        	localidad.setId(Integer.parseInt(request.getParameter("selLocalidad")));        	
	        	localidad.setNombre(ldao.obtenerLocalidad(Integer.parseInt(request.getParameter("selLocalidad"))));
	        	prof.setLocalidad(localidad);
	        	
	        	ProfesorDAOImpl profdao = new ProfesorDAOImpl();
	        
	        	
	        	actualizo = profdao.ModificarProfesor(prof,legajo);
	        	ArrayList<Profesor> lista = profdao.ListarProfesores();
	        	
	        	//REQUESTDISPATCHER para volver al JSP
	        	request.setAttribute("act", actualizo);
	            request.setAttribute("listaA", lista);
	            RequestDispatcher miDispacher = request.getRequestDispatcher("/ADMIN/Profesores.jsp"); 
	    		miDispacher.forward(request, response);

	        }
			
			
			
			

			if (request.getParameter("btnListar") != null) {
				// lista todos los profes

				ProfesorDAOImpl profdao = new ProfesorDAOImpl();
				ArrayList<Profesor> lista = profdao.ListarProfesores();

				// REQUESTDISPATCHER para volver al JSP
				request.setAttribute("listaA", lista);
				RequestDispatcher miDispacher = request.getRequestDispatcher("/ADMIN/Profesores.jsp");
				miDispacher.forward(request, response);

			}
			
			
			//
			
				boolean elimino = false;
			if (request.getParameter("btnEliminar") != null) {
				// se esta dando de baja logicamente un prof de la bd
				int legajo = Integer.parseInt(request.getParameter("lega").toString());

				ProfesorDAOImpl profdao = new ProfesorDAOImpl();
				elimino = profdao.EliminarProfesor(legajo);
				
				UsuarioDAOImpl udao = new UsuarioDAOImpl();
				String nombre = (udao.obtenerUsuario(legajo)).getUsuario();
				udao.delete(nombre);

				// REQUESTDISPATCHER para volver al JSP

				ArrayList<Profesor> lista = profdao.ListarProfesores();
				
				
				request.setAttribute("eli", elimino);
				request.setAttribute("listaA", lista);
				RequestDispatcher miDispacher = request.getRequestDispatcher("/ADMIN/Profesores.jsp");
				miDispacher.forward(request, response);
				
			}
			
			
			

			if (request.getParameter("btnModificar") != null) {
				
				Profesor prof = new Profesor();
				prof.setLegajo(Integer.parseInt(request.getParameter("lega").toString()));
				prof.setDni(request.getParameter("dni").toString());
				prof.setNombre(request.getParameter("nom").toString());
				prof.setApellido(request.getParameter("ape").toString());
				prof.setFechanac(request.getParameter("fechanac").toString());
				prof.setDireccion(request.getParameter("dir").toString());
				//prof.setLocalidad(Integer.parseInt(request.getParameter("loc").toString()));
				prof.setMail(request.getParameter("mail").toString());
				prof.setTelefono(request.getParameter("tel").toString());
				
	    		

				LocalidadDAOImpl ldao = new LocalidadDAOImpl();
				ProvinciaDAOImpl pdao = new ProvinciaDAOImpl();
				
				Localidad localidad = new Localidad();
				localidad.setId(Integer.parseInt(request.getParameter("loc").toString()));
				localidad.setNombre(ldao.obtenerLocalidad(Integer.parseInt(request.getParameter("loc").toString())));			
				prof.setLocalidad(localidad);
				
				Provincia provincia = new Provincia();
				provincia.setId(Integer.parseInt(request.getParameter("prov").toString()));
				provincia.setNombre(pdao.obtenerProvincia(Integer.parseInt(request.getParameter("prov").toString())));
				prof.setProvincia(provincia);
	
				//lista provincias
				ArrayList<Provincia> listap = pdao.ListarProvincias();			
		        
				//lista localidades	
				ArrayList<Localidad> listal = ldao.ListarLocalidades();


				
				// REQUESTDISPATCHER para volver al JSP

				
				request.setAttribute("listaL", listal);
			    request.setAttribute("listaP", listap);
				request.setAttribute("profesor", prof);
				RequestDispatcher miDispacher = request.getRequestDispatcher("/ADMIN/ProfesoresDetalle.jsp");
				miDispacher.forward(request, response);
			
			}

			
			 boolean insert=false;

		        if(request.getParameter("btnGuardar")!=null)
		        {
		        	//se esta guardando un prof en la bd
		        	Profesor prof = new Profesor();

		        	prof.setDni(request.getParameter("txtDni"));
		        	prof.setNombre(request.getParameter("txtNombre"));
		        	prof.setApellido(request.getParameter("txtApellido"));
		        	prof.setFechanac(request.getParameter("txtFechanac"));
		        	prof.setTelefono(request.getParameter("txtTel"));
		        	prof.setDireccion(request.getParameter("txtDir"));	
		        	prof.setMail(request.getParameter("txtEmail"));
		        	
		        	/*prof.setLocalidad_nombre(request.getParameter("selLocalidad"));
		        	
		        	LocalidadDAOImpl ldao = new LocalidadDAOImpl();
		        	prof.setLocalidad(ldao.obtenerId(request.getParameter("selLocalidad")));     */    	 
		        	

		        	Localidad localidad = new Localidad();
		        	localidad.setNombre((request.getParameter("selLocalidad")));	        	
		        	LocalidadDAOImpl ldao = new LocalidadDAOImpl();
		        	localidad.setId(ldao.obtenerId((request.getParameter("selLocalidad"))));	        	
		        	prof.setLocalidad(localidad);         
		        	
		        	Usuario usuario = new Usuario();
		        	usuario.setUsuario(request.getParameter("txtUsuario"));
		        	usuario.setContraseña(request.getParameter("txtContraseña"));
		        	
		        	UsuarioDAOImpl udao = new UsuarioDAOImpl();		        	
		        	
		        	ProfesorDAOImpl profdao = new ProfesorDAOImpl();
		        	
		        	int legajo = profdao.obtenerUltimo();
	        	
        	
					insert = profdao.InsertarProfesor(prof); 
		        	Profesor profe = profdao.obtenerProfesor(legajo);
		        	usuario.setProfesor(profe);		        	
		        	udao.insertUsuario(usuario);  
						
						request.setAttribute("ins", insert);
						ArrayList<Profesor> lista = profdao.ListarProfesores();
						request.setAttribute("listaA", lista);
			            RequestDispatcher miDispacher = request.getRequestDispatcher("/ADMIN/Profesores.jsp"); 
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
					    RequestDispatcher miDispacher = request.getRequestDispatcher("/ADMIN/ProfesoresDetalle.jsp"); 
						miDispacher.forward(request, response);
								
				
				
				}


		        
		
		
		
		//doGet(request, response);
	}

}
