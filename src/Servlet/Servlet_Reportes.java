package Servlet;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DAOImpl.CursoDAOImpl;
import DAOImpl.MateriaDAOImpl;
import DAOImpl.ProfesorDAOImpl;
import Entidad.Curso;

/**
 * Servlet implementation class Servlet_Alumnos
 */
@WebServlet("/Servlet_Reportes")
public class Servlet_Reportes extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public Servlet_Reportes() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		
	}

	
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		if (request.getParameter("btnListar") != null) {
			// lista todos los cursos

			CursoDAOImpl cdao = new CursoDAOImpl();
			ArrayList<Curso> lista = cdao.ListarCursos();
						

			// REQUESTDISPATCHER para volver al JSP
			request.setAttribute("listaC", lista);
			RequestDispatcher miDispacher = request.getRequestDispatcher("/ADMIN/Reportes.jsp");
			miDispacher.forward(request, response);

		}
			
			if(request.getParameter("btnReporte")!=null) {
				MateriaDAOImpl mdao = new MateriaDAOImpl();
				ProfesorDAOImpl pdao = new ProfesorDAOImpl();
				
				Curso cur = new Curso();				
				cur.setCursoId(Integer.parseInt(request.getParameter("id")));
				cur.setMateria(mdao.obtenerMateria(Integer.parseInt(request.getParameter("materia"))));
				cur.setCuatrimestre(Integer.parseInt(request.getParameter("cuatri")));
				cur.setAño(Integer.parseInt(request.getParameter("año")));
				cur.setProfesor(pdao.obtenerProfesor(Integer.parseInt(request.getParameter("profe"))));	
				
				CursoDAOImpl cdao = new CursoDAOImpl();
				
				int apro = cdao.Aprobados(Integer.parseInt(request.getParameter("id")));
				int desapro = cdao.Desaprobados(Integer.parseInt(request.getParameter("id")));				
				
				// REQUESTDISPATCHER para volver al JSP
				request.setAttribute("curso", cur);
				request.setAttribute("apro", apro);
				request.setAttribute("desapro", desapro);
				RequestDispatcher miDispacher = request.getRequestDispatcher("/ADMIN/ReportesDetalle.jsp");
				miDispacher.forward(request, response);
				
				
			}

		
	}

	}
	
