package modelo;

import java.util.ArrayList;
import java.util.List;

public class Categoria {
	
	private Integer id;
	private String nombre;
	private List<Producto> productos; 
	
	public Categoria(Integer id, String nombre, List<Producto> productos) {
		this.id = id;
		this.nombre = nombre;
		this.productos = productos;
	}
	
	public Categoria(Integer id, String nombre) {
		this.id = id;
		this.nombre = nombre;
	}
	
	public Categoria(String nombre, List<Producto> productos) {
		this.nombre = nombre;
		this.productos = productos;
	}
	
	public int getId() {
		return id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public List<Producto> getProductos() {
		return productos;
	}

	public void agregarProducto(Producto producto) {
		if(this.productos == null) {
			this.productos = new ArrayList<>(); 
		}
		this.productos.add(producto);
	}

	@Override
	public String toString() {
		return "Categoria [id=" + id + ", nombre=" + nombre + ", productos=" + productos + "]";
	}
	
	
}
