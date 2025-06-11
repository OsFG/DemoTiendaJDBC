package modelo;

public class Talla {
	private Integer id;
	private String nombre;
	
	public Talla(Integer id, String nombre){
		this.id = id;
		this.nombre = nombre;
	}
	
	public Talla(String nombre){
		this.nombre = nombre;
	}

	public Integer getId() {
		return id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	@Override
	public String toString() {
		return "Talla [id=" + id + ", nombre=" + nombre + "]";
	}
	
	
}
