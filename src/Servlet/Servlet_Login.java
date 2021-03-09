package Servlet;

import java.io.IOException;
import java.util.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Entidad.Usuario;
import NegocioImpl.UsuarioNegocioImpl;

/**
 * Servlet implementation class Servlet_Login
 */
@WebServlet("/Servlet_Login")
public class Servlet_Login extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Servlet_Login() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String url = "/Login.jsp";
        String inUsuario = " ";
        String auxUusario = " ";
        String auxContraseña = " ";
        String contraseña = " ";
        Date fechaActual = new Date();
        Date auxfecha = new Date();
        int resu = 0;
        int legajo = 0;
        int perfil = 0;
        HttpSession session = request.getSession();
      
        if(request.getParameter("btnIngresar")!=null)

        {

            if(request.getParameter("txtUsuario")!=null)

              {

            	   inUsuario = request.getParameter("txtUsuario");
            	   
                   UsuarioNegocioImpl usuImp = new UsuarioNegocioImpl();
                   Usuario unUsu = new Usuario();
                   unUsu = usuImp.getUsuario(inUsuario);
                   perfil = unUsu.getPerfilId();
                   auxUusario = unUsu.getUsuario();
                   auxContraseña = unUsu.getContraseña();
                   auxfecha = unUsu.getFechaBaja();
           		   if(auxfecha != null ) {
        			 resu = fechaActual.compareTo(auxfecha);
           		   }
           		   else {resu = -1;}
           		   
           		   if(unUsu.getProfesor()!=null)
           		   {
                   legajo = unUsu.getProfesor().getLegajo();
           		   }
              }

            if(request.getParameter("txtContrase")!=null)
            	contraseña = request.getParameter("txtContrase");
            
        
        if( (contraseña.equals(auxContraseña))  && (inUsuario.equals(auxUusario))  )

        {
        	if  (resu < 0) { 
        		
        		getServletContext().setAttribute("var", inUsuario);
        		
	        	session.setAttribute("Usuario", inUsuario);
	        	session.setAttribute("Legajo", legajo);
	        	switch (perfil) {
	        		case 1:
	        			url = "/ADMIN/AdministradorMain.jsp";
	        			break;
	        		case 2:
	        			url = "/DOCENTE/DocenteMain.jsp";
	        			break;
	        		default:
	        			request.setAttribute("Error", "Atención: Perfil Incorrecto");
	        	}
	        	
        	}else {
        			request.setAttribute("Error", "Usuario dado de baja");
    			  }
        }
        else {
				request.setAttribute("Error", "Atención: Usuario o Contraseña incorrecta");
			 }
        
        RequestDispatcher miDispacher = request.getRequestDispatcher(url);
        miDispacher.forward(request, response);
	}
}
}