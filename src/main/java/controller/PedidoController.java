package controller;

import java.util.List;

import dao.PedidoDAO;
import factory.ConnectionFactory;
import modelo.Pedido;
import modelo.PedidoDTO;

public class PedidoController {
	private PedidoDAO pedidoDAO;
	
	public PedidoController() {
		ConnectionFactory connectionF = new ConnectionFactory();
		this.pedidoDAO = new PedidoDAO(connectionF.recuperarConexion());
	}
	

	public int guardarPedido(int idUsuario, List<PedidoDTO> datosPedido) {
		return pedidoDAO.guardarPedido(idUsuario, datosPedido);
	}
	
	public List<PedidoDTO> leerPedido(int idUsuario) {
		return pedidoDAO.leerPedido(idUsuario);
	}

    // CREAR MÉTODO PARA IDENTIFICAR EL ITEMPEDIDO EN LA DB (usado id, nombre y talla) Y ACTUAIZAR LOS VALORES
	public int actualizarPedido(int id, List<PedidoDTO> datosPedido) {
		return pedidoDAO.actualizarPedido(id, datosPedido);
	}


	public void desactivarPedido(int idUsuario) {
		pedidoDAO.desactivarPedido(idUsuario);
	}

}
