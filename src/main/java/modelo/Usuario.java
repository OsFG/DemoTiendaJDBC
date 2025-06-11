package modelo;

import java.time.LocalDateTime;
import java.util.List;

public class Usuario {
	
	private Integer id;
	private String nombreUsuario;
	private String correo;
	private boolean membresia;

	
	public Usuario(String nombreUsuario, String correo, boolean membresia) {
		this.nombreUsuario = nombreUsuario;
		this.correo = correo;
		this.membresia = membresia;
	}


	public Integer getId() {
		return id;
	}

	public String getNombreUsuario() {
		return nombreUsuario;
	}


	public void setNombreUsuario(String nombreUsuario) {
		this.nombreUsuario = nombreUsuario;
	}


	public String getCorreo() {
		return correo;
	}


	public void setCorreo(String correo) {
		this.correo = correo;
	}


	public boolean isMembresia() {
		return membresia;
	}


	public void setMembresia(boolean membresia) {
		this.membresia = membresia;
	}


	@Override
	public String toString() {
		return "Usuario [id=" + id + ", nombreUsuario=" + nombreUsuario + ", correo=" + correo + ", membresia="
				+ membresia + "]";
	}
	
	

}
