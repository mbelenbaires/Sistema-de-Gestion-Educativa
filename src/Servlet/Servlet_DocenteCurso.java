package Servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Entidad.AlumnosXCurso;
import Entidad.Curso;
import NegocioImpl.AlumnosXCursoNegocioImpl;
import NegocioImpl.CursoNegocioImpl;

import java.util.ArrayList;
import java.util.List;
import javax.servlet.RequestDispatcher;


/**
 * Servlet implementation class Servlet_DocenteCurso
 */
@WebServlet("/Servlet_DocenteCurso")
public class Servlet_DocenteCurso extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Servlet_DocenteCurso() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int curso=0;
		List<AlumnosXCurso> listNotas = new ArrayList<AlumnosXCurso>();
		Curso unCurso = new Curso();
		
		if(request.getParameter("btnSeleccionar")!=null)
        {
			curso = Integer.parseInt(request.getParameter("btnSeleccionar"));
			AlumnosXCursoNegocioImpl negAlumCurso = new AlumnosXCursoNegocioImpl();
			listNotas = negAlumCurso.ListarNotasCursos(curso);
			
			CursoNegocioImpl negCurso = new CursoNegocioImpl();
			unCurso = negCurso.UnCursoGet(curso);
			
        }
		
		HttpSession session = request.getSession();
		session.setAttribute("IdCurso", curso);
		request.setAttribute("IdCurso", curso);
		request.setAttribute("unCurso", unCurso);
		request.setAttribute("listaCurso", listNotas);
		
		RequestDispatcher miDispacher = request.getRequestDispatcher("/DOCENTE/CursosDocenteDetalle.jsp"); 
		miDispacher.forward(request, response);
	}

}
