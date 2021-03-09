package Entidad;

public class Alumno {
	private int legajo;
	private String dni;
	private String nombre;
	private String apellido;
	private String fechanac;
	private String direccion;
	private Localidad localidad;
	private String mail;
	private String telefono;
	private int estado;
	private Provincia provincia;
	
	public Alumno()
	{}

	

	public Alumno(int legajo, String dni, String nombre, String apellido, String fechanac, String direccion,
			Localidad localidad, String mail, String telefono, int estado, Provincia provincia) {
		super();
		this.legajo = legajo;
		this.dni = dni;
		this.nombre = nombre;
		this.apellido = apellido;
		this.fechanac = fechanac;
		this.direccion = direccion;
		this.localidad = localidad;
		this.mail = mail;
		this.telefono = telefono;
		this.estado = estado;
		this.provincia = provincia;
	}



	public Localidad getLocalidad() {
		return localidad;
	}

	public void setLocalidad(Localidad localidad) {
		this.localidad = localidad;
	}
	
	public Provincia getProvincia() {
		return provincia;
	}


	public void setProvincia(Provincia provincia) {
		this.provincia = provincia;
	}



	public int getLegajo() {
		return legajo;
	}

	public void setLegajo(int legajo) {
		this.legajo = legajo;
	}

	public String getDni() {
		return dni;
	}

	public void setDni(String dni) {
		this.dni = dni;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public String getFechanac() {
		return fechanac;
	}

	public void setFechanac(String fechanac) {
		this.fechanac = fechanac;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}


	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}


	
	public int getEstado() {
		return estado;
	}


	public void setEstado(int estado) {
		this.estado = estado;
	}
	
	
}
