package modelo;

public class Direccion {
	
	private Integer id;
	private String calle;
	private String numero;
	private String colonia;
	private String ciudad;
	private String estado;
	private String cp;
	private Integer idUsuario;
	
	public Direccion(String calle, String numero, String colonia, String ciudad, String estado, String  cp, Integer idUsuario) {
		this.calle = calle;
		this.numero = numero;
		this.colonia = colonia;
		this.ciudad = ciudad;
		this.estado = estado;
		this.cp = cp;
		this.idUsuario = idUsuario;
	}

	public Integer getId() {
		return id;
	}

	public String getCalle() {
		return calle;
	}

	public void setCalle(String calle) {
		this.calle = calle;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public String getColonia() {
		return colonia;
	}

	public void setColonia(String colonia) {
		this.colonia = colonia;
	}

	public String getCiudad() {
		return ciudad;
	}

	public void setCiudad(String ciudad) {
		this.ciudad = ciudad;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public String getCp() {
		return cp;
	}

	public void setCp(String cp) {
		this.cp = cp;
	}

	public Integer getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(Integer idUsuario) {
		this.idUsuario = idUsuario;
	}

	@Override
	public String toString() {
		return "Direccion [id=" + id + ", calle=" + calle + ", numero=" + numero + ", colonia=" + colonia + ", ciudad="
				+ ciudad + ", estado=" + estado + ", cp=" + cp + ", idUsuario=" + idUsuario + "]";
	}

	
}
