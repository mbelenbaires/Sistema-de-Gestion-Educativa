package Entidad;
import java.sql.Date;
import java.util.ArrayList;

public class Curso {
private int cursoId;
private Materia materia;
private int cuatrimestre;
private int a�o;
private Profesor profesor;
private Date fechabaja;
private ArrayList<Alumno> Alumnos;

public Curso()
{}



public Curso(int cursoId, Materia materia, int cuatrimestre, int a�o, Profesor profesor, Date fechabaja,
		ArrayList<Alumno> alumnos) {
	super();
	this.cursoId = cursoId;
	this.materia = materia;
	this.cuatrimestre = cuatrimestre;
	this.a�o = a�o;
	this.profesor = profesor;
	this.fechabaja = fechabaja;
	Alumnos = alumnos;
}




@Override
public String toString() {
	return "Curso [cursoId=" + cursoId + ", materia=" + materia + ", cuatrimestre=" + cuatrimestre + ", a�o=" + a�o
			+ ", profesor=" + profesor + ", fechabaja=" + fechabaja + ", Alumnos=" + Alumnos + "]";
}




public ArrayList<Alumno> getAlumnos() {
	return Alumnos;
}



public void setAlumnos(ArrayList<Alumno> alumnos) {
	Alumnos = alumnos;
}



public int getCursoId() {
	return cursoId;
}

public void setCursoId(int cursoId) {
	this.cursoId = cursoId;
}

public Materia getMateria() {
	return materia;
}

public void setMateria(Materia materia) {
	this.materia = materia;
}

public int getCuatrimestre() {
	return cuatrimestre;
}

public void setCuatrimestre(int cuatrimestre) {
	this.cuatrimestre = cuatrimestre;
}

public int getA�o() {
	return a�o;
}

public void setA�o(int a�o) {
	this.a�o = a�o;
}

public Profesor getProfesor() {
	return profesor;
}

public void setProfesor(Profesor profesor) {
	this.profesor = profesor;
}

public Date getFechabaja() {
	return fechabaja;
}

public void setFechabaja(Date fechabaja) {
	this.fechabaja = fechabaja;
}


}