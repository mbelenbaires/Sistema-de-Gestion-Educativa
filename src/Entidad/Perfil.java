package Entidad;

public class Perfil {

	private int perfilId;
	private String descripcion;
	
	public Perfil() {
		// TODO Auto-generated constructor stub
	}

	public int getPerfilId() {
		return perfilId;
	}

	public void setPerfilId(int idPerfil) {
		this.perfilId = idPerfil;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String desc) {
		this.descripcion = desc;
	}
	
	@Override
	public String toString() {
		return "Alumno [Perfil Id=" + perfilId + ", Descripción=" + descripcion  + "]";
	}
	
}
