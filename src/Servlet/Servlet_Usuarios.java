package Servlet;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DAOImpl.UsuarioDAOImpl;
import Entidad.Usuario;

/**
 * Servlet implementation class Servlet_Alumnos
 */
@WebServlet("/Servlet_Usuarios")
public class Servlet_Usuarios extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public Servlet_Usuarios() {
		super();
		// TODO Auto-generated constructor stub
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

				String nombreU=null;
				
				if (request.getParameter("btnBuscar")!=null) {

				  if ((request.getParameter("txtNombreU")).equals("")) {
					  nombreU=null;				
					} else {nombreU = (request.getParameter("txtNombreU"));}
					
						UsuarioDAOImpl userdao = new UsuarioDAOImpl();
						ArrayList<Usuario> listaFiltrada = userdao.ListaFiltrada(nombreU);
						
				      	//REQUESTDISPATCHER para volver al JSP
			            request.setAttribute("listaF", listaFiltrada);
			            RequestDispatcher miDispacher = request.getRequestDispatcher("/ADMIN/UsuariosMain.jsp"); 
			    		miDispacher.forward(request, response);
				}
		}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		
        boolean actualizo=false;
        if(request.getParameter("btnActualizar")!=null)
        {
        	//se esta modificando un usuario en la bd
        	Usuario user = new Usuario();
        	String nombreU = request.getParameter("txtNombreU");
        	user.setUsuario(request.getParameter("txtNombreU"));
        	user.setContraseña(request.getParameter("txtContra"));
        	user.setPerfilId(2);
	        	
        	UsuarioDAOImpl userdao = new UsuarioDAOImpl();

        	actualizo = userdao.ModificarUsuario(user,nombreU);
        	ArrayList<Usuario> lista = userdao.ListarUsuariosAlta();
        	
        	//REQUESTDISPATCHER para volver al JSP
        	request.setAttribute("act", actualizo);
            request.setAttribute("listaA", lista);
            RequestDispatcher miDispacher = request.getRequestDispatcher("/ADMIN/UsuariosMain.jsp"); 
    		miDispacher.forward(request, response);

        }
		
		
		
		

		if (request.getParameter("btnListar") != null) {
			// lista todos los usuarios

			UsuarioDAOImpl userdao = new UsuarioDAOImpl();
			ArrayList<Usuario> lista = userdao.ListarUsuariosAlta();

			// REQUESTDISPATCHER para volver al JSP
			request.setAttribute("listaA", lista);
			RequestDispatcher miDispacher = request.getRequestDispatcher("/ADMIN/UsuariosMain.jsp");
			miDispacher.forward(request, response);

		}
		

			boolean elimino = false;
		if (request.getParameter("btnEliminar") != null) {
			
			// se esta dando de baja logicamente un usuario de la bd
			
			String nombreU = request.getParameter("user");

			UsuarioDAOImpl userdao = new UsuarioDAOImpl();
			elimino = userdao.delete(nombreU);

			// REQUESTDISPATCHER para volver al JSP

			ArrayList<Usuario> lista = userdao.ListarUsuariosAlta();
			request.setAttribute("eli", elimino);
			request.setAttribute("listaA", lista);
			RequestDispatcher miDispacher = request.getRequestDispatcher("/ADMIN/UsuariosMain.jsp");
			miDispacher.forward(request, response);
			
		}

		if (request.getParameter("btnModificar") != null) {

			Usuario user = new Usuario();
			user.setUsuario((request.getParameter("user").toString()));
			user.setContraseña(request.getParameter("contra").toString());

			// REQUESTDISPATCHER para volver al JSP
			
			request.setAttribute("usuario", user);
			RequestDispatcher miDispacher = request.getRequestDispatcher("/ADMIN/UsuariosDetail.jsp");
			miDispacher.forward(request, response);

		}

		
		 boolean insert=false;

	        if(request.getParameter("btnGuardar")!=null)
	        {
	        	//se esta guardando un usuario en la bd
	        	Usuario user = new Usuario();
	        	
	        	user.setUsuario(request.getParameter("txtNombreU"));
	        	user.setContraseña(request.getParameter("txtContra"));
	        	user.setPerfilId(2);
	        	user.setFechaBaja(null);     	 
	        	
	        	UsuarioDAOImpl userdao = new UsuarioDAOImpl();
	        	
				insert = userdao.insertUsuario(user); 
					
				request.setAttribute("ins", insert);
				ArrayList<Usuario> lista = userdao.ListarUsuariosAlta();
				request.setAttribute("listaA", lista);
	            RequestDispatcher miDispacher = request.getRequestDispatcher("/ADMIN/UsuariosMain.jsp"); 
	    		miDispacher.forward(request, response);
	        }
	        
	        if(request.getParameter("btnNuevo")!=null) {
						
					//REQUESTDISPATCHER para volver al JSP
				    RequestDispatcher miDispacher = request.getRequestDispatcher("/ADMIN/UsuariosDetail.jsp"); 
					miDispacher.forward(request, response);
			}
		
	}

}
