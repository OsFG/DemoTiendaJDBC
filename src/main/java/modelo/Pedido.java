package modelo;

import java.util.ArrayList;
import java.util.List;

public class Pedido {
	
	private Integer id;
	private Integer idUsuario;
	private List<ItemPedido> items;
	
	public Pedido(Integer id, Integer idUsuario){
		this.id = id;
		this.idUsuario = idUsuario;
	}
	
	public Pedido(){
	}

	public Integer getId() {
		return id;
	}

	public Integer getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(Integer idUsuario) {
		this.idUsuario = idUsuario;
	}

	public List<ItemPedido> getItems() {
		return items;
	}

	public void agregarItem(ItemPedido item) {
		if (this.items == null) {
			items = new ArrayList<>();
		}
		this.items.add(item);
	}


}
