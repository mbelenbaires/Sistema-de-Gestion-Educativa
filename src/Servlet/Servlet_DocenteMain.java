package Servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Entidad.Curso;
import NegocioImpl.CursoNegocioImpl;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class Servlet_DocenteMain
 */
@WebServlet("/Servlet_DocenteMain")
public class Servlet_DocenteMain extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Servlet_DocenteMain() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String url = "/DOCENTE/CursosDocenteMain.jsp";
		HttpSession session = request.getSession();
		int legajo = Integer.parseInt(session.getAttribute("Legajo").toString());
		List<Curso> cursos = new ArrayList<Curso>();
		CursoNegocioImpl negocio = new CursoNegocioImpl();
		cursos = negocio.CursosXProfesor(legajo);
		session.setAttribute("listaCursoXProfesor", cursos);
		request.setAttribute("listaCursoXProfesor", cursos);
		RequestDispatcher miDispacher = request.getRequestDispatcher(url);
        miDispacher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String url = "/DOCENTE/CursosDocenteMain.jsp";
		HttpSession session = request.getSession();
		int legajo = Integer.parseInt(session.getAttribute("Legajo").toString());
		List<Curso> cursos = new ArrayList<Curso>();
		CursoNegocioImpl negocio = new CursoNegocioImpl();
		cursos = negocio.CursosXProfesor(legajo);
		session.setAttribute("listaCursoXProfesor", cursos);
		request.setAttribute("listaCursoXProfesor", cursos);
		RequestDispatcher miDispacher = request.getRequestDispatcher(url);
        miDispacher.forward(request, response);
	}

}
