package Entidad;

public class Provincia {

	int id;
	String nombre;
	String codigo;
	
	public Provincia(int id, String nombre, String codigo) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.codigo = codigo;
	}
	public Provincia() {
		// TODO Auto-generated constructor stub
	}
	@Override
	public String toString() {
		return "Provincia [id=" + id + ", nombre=" + nombre + ", codigo=" + codigo + "]";
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getCodigo() {
		return codigo;
	}
	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}
	
	
	
}

