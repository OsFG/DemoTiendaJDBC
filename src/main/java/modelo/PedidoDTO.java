package modelo;

public record PedidoDTO(
		int id,
		String nombre,
		String talla,
		int cantidad,
		double precioTotal
){
	public PedidoDTO(String nombre, String talla, int cantidad, double precioTotal) {
		this(0, nombre, talla, cantidad, precioTotal);
	}
	

}
