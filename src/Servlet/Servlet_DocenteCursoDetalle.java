package Servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Entidad.Alumno;
import Entidad.AlumnosXCurso;
import Entidad.Curso;
import NegocioImpl.AlumnosXCursoNegocioImpl;
import NegocioImpl.CursoNegocioImpl;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpSession;

import DAOImpl.AlumnoDAOImpl;

/**
 * Servlet implementation class Servlet_DocenteCursoDetalle
 */
@WebServlet("/Servlet_DocenteCursoDetalle")
public class Servlet_DocenteCursoDetalle extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Servlet_DocenteCursoDetalle() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 String url = " ";
		 HttpSession session = request.getSession();
		 url = "/DOCENTE/CursosDocenteDetalle.jsp";
		 request.setAttribute("listaCurso", 0);
		 
		 
	     if(request.getParameter("btnGuardar")!=null)
	     {
	    	 int legajo = Integer.parseInt(request.getAttribute("txtLegajoProfesor").toString());
	    	 List<Curso> cursos = new ArrayList<Curso>();
	    	 ArrayList<AlumnosXCurso> listaNotasactualizar ;
	    	 int CantActualizados = 0;
	    	 
	    	 if(request.getAttribute("listaCursoGuarda")!=null)
		     {
	    		listaNotasactualizar = (ArrayList<AlumnosXCurso>)request.getAttribute("listaCursoGuarda");
	    		AlumnosXCursoNegocioImpl negocioAlumCurso = new AlumnosXCursoNegocioImpl();
	    		CantActualizados = negocioAlumCurso.ListaNotasUpdate(listaNotasactualizar);
	    		request.setAttribute("CantActualizados", CantActualizados);
	    		url = "${pageContext.request.contextPath}/Servlet_DocenteMain";
		     }
	    	
			 cursos = new ArrayList<Curso>();
			 CursoNegocioImpl negocio = new CursoNegocioImpl();
			 cursos = negocio.CursosXProfesor(legajo);
			 
			 session.setAttribute("Legajo", legajo);
			 session.setAttribute("listaCursoXProfesor", cursos);
			 
			 request.setAttribute("listaCursoXProfesor", cursos);
			 url = "/DOCENTE/CursosDocenteMain.jsp";
	    	 
	     }
		 
		 if(request.getParameter("btnGuardarUnaNota")!=null)
		 {	
			 List<AlumnosXCurso> listNotas = new ArrayList<AlumnosXCurso>();
			 ArrayList<AlumnosXCurso> listaNotasactualizar ;
			 int CantActualizados = 0;
	    	 listaNotasactualizar = new ArrayList<AlumnosXCurso>();
	    	 AlumnoDAOImpl aludao = new AlumnoDAOImpl();
	    	 
	    	 AlumnosXCurso unCurso = new AlumnosXCurso();
	    	 unCurso.setCursoId(Integer.parseInt(request.getParameter("txtIdCurso")));
	    	// Alumno alu = aludao.obtenerAlumno(Integer.parseInt(request.getParameter("txtLegajo")));
	    	// unCurso.setAlumno(alu);
	    	 unCurso.setLegajo(Integer.parseInt(request.getParameter("txtLegajo")));
	    	
	    	 if(request.getParameter("txtNota1")!=null)
			 {
	    		unCurso.setNota1(Integer.parseInt(request.getParameter("txtNota1")));
			 }else
			 {	unCurso.setNota1(0);}
	    		
	    	 if(request.getParameter("txtNota2")!=null)
			 {
	    		 unCurso.setNota2(Integer.parseInt(request.getParameter("txtNota2")));
			 }else
			 {	unCurso.setNota2(0);}
	    		
	    	 if(request.getParameter("txtRecu1")!=null)
			 {
	    		unCurso.setRecueratorio1(Integer.parseInt(request.getParameter("txtRecu1")));
			 }else
			 {	unCurso.setRecueratorio1(0);}
	    		
	    	 if(request.getParameter("txtRecu2")!=null)
			 {
	    		unCurso.setRecueratorio2(Integer.parseInt(request.getParameter("txtRecu2")));
			 }else
			 {	unCurso.setRecueratorio2(0);}
	    		
	    	 if(request.getParameter("soEstado")!=null)
			 {
	    		unCurso.setEstado(Integer.parseInt(request.getParameter("soEstado")));
			 }else
			 {	unCurso.setEstado(0);}
	    	 
	    	 listaNotasactualizar.add(unCurso);
	    	 AlumnosXCursoNegocioImpl negocioAlumCurso = new AlumnosXCursoNegocioImpl();
	    	 CantActualizados = negocioAlumCurso.ListaNotasUpdate(listaNotasactualizar);
	    	 
	    	 //session: Usuario 
	    	 //Atribute: Curso + CantActualizados + List<AlumnosXCurso> = "listaCurso"
	    	 CursoNegocioImpl negCurso = new CursoNegocioImpl();
			 Curso CursoSesion = negCurso.UnCursoGet(unCurso.getCursoId());
			 listNotas = negocioAlumCurso.ListarNotasCursos(unCurso.getCursoId());
			 
			 request.setAttribute("unCurso", CursoSesion);
	    	 request.setAttribute("CantActualizados", CantActualizados);
	    	 request.setAttribute("listaCurso", listNotas);
				
	    	 url = "/DOCENTE/CursosDocenteDetalle.jsp";
		 }
		 
		 
		 if(request.getParameter("btnModificarUnaNota")!=null)
		 {	
	    	 
	    	 AlumnosXCurso unCurso = new AlumnosXCurso();
	    	 Alumno unAlum = new Alumno();
	    	 
	    	 unCurso.setCursoId(Integer.parseInt(request.getParameter("txtIdCurso")));
	    	 unCurso.setLegajo(Integer.parseInt(request.getParameter("txtLegajo")));
	    	 
	    	 if(request.getParameter("txtAlumNombre")!=null)
	    		 unAlum.setNombre(request.getParameter("txtAlumNombre").toString());
	    	 
	    	 if(request.getParameter("txtAlumApellido")!=null)
	    		 unAlum.setApellido(request.getParameter("txtAlumApellido").toString());
	    	 
	    	 unCurso.setAlumno(unAlum);
	    	 
	    	 if(request.getParameter("txtNota1")!=null)
			 {
	    		unCurso.setNota1(Integer.parseInt(request.getParameter("txtNota1")));
			 }else
			 {	unCurso.setNota1(0);}
	    		
	    	 if(request.getParameter("txtNota2")!=null)
			 {
	    		 unCurso.setNota2(Integer.parseInt(request.getParameter("txtNota2")));
			 }else
			 {	unCurso.setNota2(0);}
	    		
	    	 if(request.getParameter("txtRecu1")!=null)
			 {
	    		unCurso.setRecueratorio1(Integer.parseInt(request.getParameter("txtRecu1")));
			 }else
			 {	unCurso.setRecueratorio1(0);}
	    		
	    	 if(request.getParameter("txtRecu2")!=null)
			 {
	    		unCurso.setRecueratorio2(Integer.parseInt(request.getParameter("txtRecu2")));
			 }else
			 {	unCurso.setRecueratorio2(0);}
	    		
	    	 if(request.getParameter("txtEstado")!=null)
			 {
	    		unCurso.setEstado(Integer.parseInt(request.getParameter("txtEstado")));
			 }else
			 {	unCurso.setEstado(0);}
	    	 
	    	 
			 request.setAttribute("unCursoModificar", unCurso);
	    	 url = "/DOCENTE/DetalleNota.jsp";
		 }
	     
		 if(request.getParameter("btnCancelar")!=null || request.getParameter("btnVolver")!=null)
		 {	

			 	session = request.getSession();
				int legajo = Integer.parseInt(session.getAttribute("Legajo").toString());
				List<Curso> cursos = new ArrayList<Curso>();
				CursoNegocioImpl negocio = new CursoNegocioImpl();
				cursos = negocio.CursosXProfesor(legajo);
				session.setAttribute("listaCursoXProfesor", cursos);
				request.setAttribute("listaCursoXProfesor", cursos);
				
				url = "/DOCENTE/CursosDocenteMain.jsp";
				RequestDispatcher miDispacher = request.getRequestDispatcher(url);
		        miDispacher.forward(request, response);
		 }
		 
		 if(request.getParameter("btnBuscar")!=null )
		 {	 
			 List<AlumnosXCurso> listNotas = new ArrayList<AlumnosXCurso>();
			 ArrayList<AlumnosXCurso> listaNotasactualizar ;
			 listaNotasactualizar = new ArrayList<AlumnosXCurso>();
			 AlumnosXCursoNegocioImpl negocioAlumCurso = new AlumnosXCursoNegocioImpl();
			 int CursoId = Integer.parseInt(request.getParameter("txtId"));
			 
			 String nombre = "";
			 if(request.getParameter("txtBuscarNombre")!=null)
				 nombre = request.getParameter("txtBuscarNombre");
			 
			 String apellido = "";
			 if(request.getParameter("txtBuscarApellido")!=null)
				 apellido = request.getParameter("txtBuscarApellido");
			 
			 //session: Usuario 
	    	 //Atribute: Curso + CantActualizados + List<AlumnosXCurso> = "listaCurso"
	    	 CursoNegocioImpl negCurso = new CursoNegocioImpl();
			 Curso CursoSesion = negCurso.UnCursoGet(CursoId);
			 listNotas = negocioAlumCurso.ListarNotasCursos(CursoId,nombre,apellido);
			 
			 request.setAttribute("unCurso", CursoSesion);
	    	 request.setAttribute("CantActualizados", 0);
	    	 request.setAttribute("listaCurso", listNotas);
				
	    	 url = "/DOCENTE/CursosDocenteDetalle.jsp";
		 }
		 
		 
	     RequestDispatcher miDispacher = request.getRequestDispatcher(url);
	     miDispacher.forward(request, response);
	}

}
