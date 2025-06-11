package modelo;

import java.util.List;

public class Producto {
	
	private Integer id;
	private Integer idCategoria;
	private String nombre;
	private String genero;
	private Talla talla;
	private double precio; 
	
	public Producto(Integer id, Integer idCategoria, String nombre, String genero, Talla talla, double precio) {
		this.id = id;
		this.idCategoria = idCategoria;
		this.nombre = nombre;
		this.genero = genero;
		this.talla = talla;
		this.precio = precio;
	}
	
	public Producto(String nombre, String genero, Talla talla, double precio) {
		this.nombre = nombre;
		this.genero = genero;
		this.talla = talla;
		this.precio = precio;
	}

	public Integer getId() {
		return id;
	}

	public Integer getIdCategoria() {
		return idCategoria;
	}

	public void setIdCategoria(Integer idCategoria) {
		this.idCategoria = idCategoria;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getGenero() {
		return genero;
	}

	public void setGenero(String genero) {
		this.genero = genero;
	}

	public Talla getTalla() {
		return talla;
	}

	public void setTalla(Talla talla) {
		this.talla = talla;
	}

	public double getPrecio() {
		return precio;
	}

	public void setPrecio(double precio) {
		this.precio = precio;
	}

	@Override
	public String toString() {
		return "Producto [id=" + id + ", idCategoria=" + idCategoria + ", nombre=" + nombre + ", genero=" + genero
				+ ", talla=" + talla + ", precio=" + precio + "]";
	}
	
	
}
