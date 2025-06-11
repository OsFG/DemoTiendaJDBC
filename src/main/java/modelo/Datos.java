package modelo;

import java.time.LocalDateTime;

public class Datos {
	
	private Integer id;
	private String nombres;
	private String apellidoP;
	private String apellidoM;
	private String telefono;
	private LocalDateTime fechaRegistro;
	private Integer idUsuario;
	
	Datos(Integer id, String  nombres, String apellidoP, String apellidoM, String telefono, LocalDateTime fechaRegistro, Integer idUsuario){
		this.id = id;
		this.nombres = nombres;
		this.apellidoP = apellidoP;
		this.apellidoM = apellidoM;
		this.telefono = telefono;
		this.fechaRegistro = fechaRegistro;
		this.idUsuario = idUsuario;
	}
	
	public Integer getId() {
		return id;
	}

	public String getNombres() {
		return nombres;
	}
	public void setNombres(String nombres) {
		this.nombres = nombres;
	}
	public String getApellidoP() {
		return apellidoP;
	}
	public void setApellidoP(String apellidoP) {
		this.apellidoP = apellidoP;
	}
	public String getApellidoM() {
		return apellidoM;
	}
	public void setApellidoM(String apellidoM) {
		this.apellidoM = apellidoM;
	}
	public String getTelefono() {
		return telefono;
	}
	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}
	public LocalDateTime getFechaRegistro() {
		return fechaRegistro;
	}
	public void setFechaRegistro(LocalDateTime fechaRegistro) {
		this.fechaRegistro = fechaRegistro;
	}
	public Integer getIdUsuario() {
		return idUsuario;
	}
	public void setIdUsuario(Integer idUsuario) {
		this.idUsuario = idUsuario;
	}

	@Override
	public String toString() {
		return "Datos [id=" + id + ", nombres=" + nombres + ", apellidoP=" + apellidoP + ", apellidoM=" + apellidoM
				+ ", telefono=" + telefono + ", fechaRegistro=" + fechaRegistro + ", idUsuario=" + idUsuario + "]";
	}


}
