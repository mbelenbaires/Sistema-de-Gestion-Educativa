package Entidad;

public class Localidad {
	int id;
	Provincia provincia;
	String nombre;
	int cp;
	
	
	
	public Localidad(int id, Provincia provincia, String nombre, int cp) {
		super();
		this.id = id;
		this.provincia = provincia;
		this.nombre = nombre;
		this.cp = cp;
	}


	public Localidad() {
		// TODO Auto-generated constructor stub
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
	public int getCp() {
		return cp;
	}
	public void setCp(int cp) {
		this.cp = cp;
	}

	@Override
	public String toString() {
		return "Localidad [id=" + id + ", provincia=" + provincia + ", nombre=" + nombre + ", cp=" + cp + "]";
	}

	public Provincia getProvincia() {
		return provincia;
	}

	public void setProvincia(Provincia provincia) {
		this.provincia = provincia;
	}
	
	
	
	

}
