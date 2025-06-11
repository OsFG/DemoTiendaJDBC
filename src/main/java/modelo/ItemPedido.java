package modelo;

public class ItemPedido {
	
	private Integer id;
	private Integer idProducto;
	private Integer cantidad;
	private double total;
	private Integer idPedido;
	
	ItemPedido(Integer id, Integer idProducto, Integer cantidad, double total, Integer idPedido){
		this.id = id;
		this.idProducto = idProducto;
		this.cantidad = cantidad;
		this.total = total;
		this.idPedido = idPedido;
	}

	public Integer getId() {
		return id;
	}

	public Integer getIdProducto() {
		return idProducto;
	}

	public void setIdProducto(Integer idProducto) {
		this.idProducto = idProducto;
	}

	public Integer getCantidad() {
		return cantidad;
	}

	public void setCantidad(Integer cantidad) {
		this.cantidad = cantidad;
	}

	public double getTotal() {
		return total;
	}

	public void setTotal(double total) {
		this.total = total;
	}

	public Integer getIdPedido() {
		return idPedido;
	}

	public void setIdPedido(Integer idPedido) {
		this.idPedido = idPedido;
	}

	@Override
	public String toString() {
		return "ItemPedido [id=" + id + ", idProducto=" + idProducto + ", cantidad=" + cantidad + ", total=" + total
				+ ", idPedido=" + idPedido + "]";
	}
	
}
