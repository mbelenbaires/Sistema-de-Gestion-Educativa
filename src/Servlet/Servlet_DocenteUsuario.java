package Servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Entidad.Profesor;
import NegocioImpl.ProfesorNegocioImpl;
import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpSession;
import NegocioImpl.UsuarioNegocioImpl;
import Entidad.Usuario;

/**
 * Servlet implementation class Servlet_DocenteUsuario
 */
@WebServlet("/Servlet_DocenteUsuario")
public class Servlet_DocenteUsuario extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Servlet_DocenteUsuario() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String url = "/DOCENTE/PerfilDocente.jsp";
		HttpSession session = request.getSession();

		int legajo =0;
		if(session.getAttribute("Legajo")!=null)
			legajo = Integer.parseInt(session.getAttribute("Legajo").toString());
		
		ProfesorNegocioImpl negocio = new ProfesorNegocioImpl();
		
		Profesor unProfe = negocio.obtenerProfesor(legajo);
		
		session.setAttribute("Resultado", "");
		request.setAttribute("PerfilProfesor", unProfe);
        RequestDispatcher miDispacher = request.getRequestDispatcher(url);
        miDispacher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String url = "/DOCENTE/PerfilDocente.jsp";
		HttpSession session = request.getSession();
		String resu="";
		
		if(request.getParameter("btnGuardar")!=null){
			
			String contra1 ="";
			if(request.getParameter("txtContrasena1")!=null)
				contra1 = request.getParameter("txtContrasena1").toString();
			
			String contra2 ="";
			if(request.getParameter("txtContrasena2")!=null)
				contra2 = request.getParameter("txtContrasena2").toString();
			
			
			if (contra1.equals(contra2)){
				
				String inUsaurio = " ";
		        if (session.getAttribute("Usuario") != null)
		        	inUsaurio =session.getAttribute("Usuario").toString();
		        
		        Usuario unUsuario = new Usuario();
			    unUsuario.setContraseña(contra1);
			    unUsuario.setUsuario(inUsaurio);   
			    
				UsuarioNegocioImpl unNegocio = new UsuarioNegocioImpl();
				boolean actualizo = unNegocio.ModificarUsuario(unUsuario, inUsaurio);
				
				if (actualizo){
					resu = "Contraseña Actuallizada Correctamente";
				}else{
					resu = "Error: No se Actualizo la Contraseña";
				}
				
			}else{
				resu = "Atención: La Contraseña 1 y 2 son diferentes";
			}
		}
		
		int legajo =0;
		if(session.getAttribute("Legajo")!=null)
			legajo = Integer.parseInt(session.getAttribute("Legajo").toString());
		
		ProfesorNegocioImpl negocio = new ProfesorNegocioImpl();
		Profesor unProfe = negocio.obtenerProfesor( legajo);
		
		request.setAttribute("ResuContrasenaDocente", resu);
		request.setAttribute("PerfilProfesor", unProfe);
        RequestDispatcher miDispacher = request.getRequestDispatcher(url);
        miDispacher.forward(request, response);
        
	}

}
