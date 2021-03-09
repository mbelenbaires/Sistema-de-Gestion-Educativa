package Entidad;

public class AlumnosXCurso {
	
	private int CursoId;
	private int Legajo;
	private int Nota1;
	private int Nota2;
	private int Recueratorio1;
	private int Recueratorio2;
	private int Estado;
	private int Activo;
	private Alumno unAlumno;
	  
	  
	public AlumnosXCurso() {
		// TODO Auto-generated constructor stub
	}
	
	public int getActivo() {
		return Activo;
	}

	public void setActivo(int activo) {
		Activo = activo;
	}
	
	public int getCursoId() {
		return CursoId;
	}
	
	public void setCursoId(int cursoId) {
		this.CursoId = cursoId;
	}
	
	public int getLegajo() {
		return Legajo;
	}
	
	public void setLegajo(int legajo) {
		this.Legajo = legajo;
	}
	
	public int getNota1() {
		return Nota1;
	}
	
	public void setNota1(int nota1) {
		this.Nota1 = nota1;
	}
	
	public int getNota2() {
		return Nota2;
	}
	
	public void setNota2(int nota2) {
		this.Nota2 = nota2;
	}
	
	public int getRecueratorio1() {
		return Recueratorio1;
	}
	
	public void setRecueratorio1(int recueratorio1) {
		this.Recueratorio1 = recueratorio1;
	}
	
	public int getRecueratorio2() {
		return Recueratorio2;
	}
	
	public void setRecueratorio2(int recueratorio2) {
		this.Recueratorio2 = recueratorio2;
	}
	
	public int getEstado() {
		return Estado;
	}
	
	public void setEstado(int estado) {
		this.Estado = estado;
	}
	
	public Alumno getAlumno() {
		return unAlumno;
	}
	
	public void setAlumno(Alumno unAlumno) {
		this.unAlumno = unAlumno;
	}
}
