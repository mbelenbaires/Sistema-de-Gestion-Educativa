package Main;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import DAOImpl.UsuarioDAOImpl;
import Entidad.AlumnosXCurso;
import Entidad.Usuario;
import NegocioImpl.AlumnosXCursoNegocioImpl;


public class Principal {

	public static void main(String[] args) {
		
		UsuarioDAOImpl usu = new UsuarioDAOImpl();
		Usuario unUsu = new Usuario();
		unUsu = usu.getUsuario("admin");
		Date fechaActual = new Date();
        Date auxfecha = new Date();
        
		System.out.println(unUsu.toString());
		
		unUsu = new Usuario();
		unUsu = usu.getUsuario("wwww");
		
		System.out.println(unUsu.toString());
		/*
		CursoNegocioImpl dao = new CursoNegocioImpl();
    	   List<Curso> cursos =  dao.ListarCursos();
    	 for (Curso unCurso : cursos) {
    		 System.out.println(unCurso.getIdcurso());
    		 System.out.println(unCurso.getMateria().getNombre());
    		 System.out.println(unCurso.getCuatrimestre());
    		 System.out.println(unCurso.getAño());
    		 
    	 }*/ 
		/*
    	System.out.println(unUsu.getFechaBaja());
    	UsuarioNegocioImpl usuImp = new UsuarioNegocioImpl();
		unUsu = usuImp.getUsuario("bbaires");
		auxfecha = unUsu.getFechaBaja();
		System.out.println(unUsu.getUsuario());
		System.out.println(unUsu.getPerfilId());
		System.out.println(unUsu.getFechaBaja());
		System.out.println("fecha actual");
		System.out.println(fechaActual);
		System.out.println("comparrar fechas");
		int resu = 0;
		if(auxfecha != null ) {
			 resu = 	fechaActual.compareTo(auxfecha);
		}
		System.out.println("resu" + resu);
		*/
		
		/*
		List<Curso> cursos = new ArrayList<Curso>();
		CursoNegocioImpl negocio = new CursoNegocioImpl();
		cursos = negocio.CursosXProfesor(100);
		 for (Curso cursoAlum : cursos) {
	 		 System.out.println("Curso:"+cursoAlum.getCursoId());
	 		 System.out.println("cuatrime:"+cursoAlum.getCuatrimestre());
	 		 System.out.println("año:"+cursoAlum.getAño());
	 		 System.out.println("materia:"+cursoAlum.getMateria().getNombre());
	 		 
	 	   }
		*/
	 /*
		List<AlumnosXCurso> listNotas = new ArrayList<AlumnosXCurso>();
		AlumnosXCursoNegocioImpl negocioAlumCurso = new AlumnosXCursoNegocioImpl();
		listNotas = negocioAlumCurso.ListarNotasCursos(1);
 	   for (AlumnosXCurso cursoAlum : listNotas) {
 		 System.out.println("Curso:"+cursoAlum.getCursoId());
 		 System.out.println("Recu 1:"+cursoAlum.getRecueratorio1());
 		 System.out.println("Apellido:"+cursoAlum.getAlumno().getApellido());
 		 System.out.println("Legajo:"+cursoAlum.getLegajo());
 		
 	   }
		*/

	}

}