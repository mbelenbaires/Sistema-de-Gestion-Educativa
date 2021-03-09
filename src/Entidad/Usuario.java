package Entidad;

import java.sql.Date;

public class Usuario {
	
	private String usuario;
	private String contraseña;
	private int perfilId;
	private Date fechaBaja;
	private Profesor profesor;
	
	public Usuario() {
		// TODO Auto-generated constructor stub
	}
	


	public Usuario(String usuario, String contraseña, int perfilId, Date fechaBaja, Profesor profesor) {
		super();
		this.usuario = usuario;
		this.contraseña = contraseña;
		this.perfilId = perfilId;
		this.fechaBaja = fechaBaja;
		this.profesor = profesor;
	}
	
	public Usuario(String usuario, String contraseña, int perfilId, Date fechaBaja) {
		super();
		this.usuario = usuario;
		this.contraseña = contraseña;
		this.perfilId = perfilId;
		this.fechaBaja = fechaBaja;
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String Usuario) {
		this.usuario = Usuario;
	}

	public String getContraseña() {
		return contraseña;
	}

	public void setContraseña(String Contraseña) {
		this.contraseña = Contraseña;
	}
	
	public int getPerfilId() {
		return perfilId;
	}
	
	public void setPerfilId(int perfil) {
		this.perfilId = perfil;
	}
	
	public Date getFechaBaja() {
		return fechaBaja;
	}
	
	public void setFechaBaja(Date fechaBaja) {
		this.fechaBaja = fechaBaja;
	}
	


	@Override
	public String toString() {
		return "Usuario [usuario=" + usuario + ", contraseña=" + contraseña + ", perfilId=" + perfilId + ", fechaBaja="
				+ fechaBaja + ", profesor=" + profesor + "]";
	}



	public Profesor getProfesor() {
		return profesor;
	}

	public void setProfesor(Profesor profesor) {
		this.profesor = profesor;
	}
}
