package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import modelo.Pedido;
import modelo.PedidoDTO;

public class PedidoDAO {

	private Connection conexion;
	
	public PedidoDAO(Connection conexion){
		this.conexion = conexion;
	}
	
// MÉTODOS DE NEGOCIO
	public int buscarPedidoActivo(int idUsuario) {
		int idPedido = 0;
		try {
			final PreparedStatement pS =  conexion.prepareStatement("SELECT id FROM pedidos WHERE idUsuario = ? AND inactivo = 0 ");
			
			try(pS){
				pS.setInt(1, idUsuario);
				final ResultSet rs = pS.executeQuery();
				
				try(rs){
					while(rs.next()) {
						idPedido = rs.getInt(1);	
					}
				}
			}
		return idPedido;
		}catch(SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	
// MÉTODOS CRUD	
	public int guardarPedido(int idUsuario, List<PedidoDTO> datosPedido) {
		int idPedido = buscarPedidoActivo(idUsuario);
		if(idPedido != 0) { agregarAlPedido(idPedido, datosPedido);
		}else {
		
			try {
				conexion.setAutoCommit(false);
				final PreparedStatement ps = conexion.prepareStatement("INSERT INTO pedidos(idUsuario) VALUES (?)",
																 Statement.RETURN_GENERATED_KEYS);
				try(ps){
					ps.setInt(1, idUsuario);
					ps.execute();
					
					final ResultSet resultSet = ps.getGeneratedKeys();
					try(resultSet){
						while(resultSet.next()) {
							idPedido = resultSet.getInt(1);
							PreparedStatement ps2 = conexion.prepareStatement(
									"INSERT INTO itemspedidos(idProducto, cantidad, total, idPedido) "
									+ "VALUES ( "
									+ "(SELECT id FROM productos WHERE nombre= ? AND idTalla ="
										+ "(SELECT id FROM tallas WHERE talla = ?) "
									+ "), "
									+ "?, "
									+ "?, "
									+ "?)");
							try(ps2){
							for(PedidoDTO pedido : datosPedido) {
								ps2.setString(1, pedido.nombre());
								ps2.setString(2, pedido.talla());
								ps2.setInt(3, pedido.cantidad());
								ps2.setDouble(4, pedido.precioTotal());
								ps2.setInt(5, idPedido);
								
								ps2.executeUpdate();
								}
							}
						}
					} 
				}
			
				conexion.commit();
				conexion.setAutoCommit(true);
				
			} catch (SQLException e) {
				try {
					conexion.rollback();
					conexion.setAutoCommit(true);
				} catch (SQLException e1) {
					throw new RuntimeException(e1);
				}			
				throw new RuntimeException(e);
			}		
		}
		return idPedido;
	}


	public List<PedidoDTO> leerPedido(int idUsuario) {
		List<PedidoDTO> items = new ArrayList<>();
		
		try {
			PreparedStatement pS =
				conexion.prepareStatement("SELECT ped.id AS Pedido, prod.nombre AS Nombre, t.talla AS Talla, ip.cantidad AS Cantidad, ip.total AS Total "
										+ "FROM pedidos ped "
										+ "INNER JOIN itemspedidos ip ON ped.id= ip.idPedido "
										+ "INNER JOIN productos prod ON ip.idProducto = prod.id "
										+ "INNER JOIN tallas t ON prod.idTalla = t.id "
										+ "WHERE ped.idUsuario = ? AND inactivo = false "
										+ "GROUP BY ped.id, prod.nombre, t.talla, ip.cantidad, ip.total "
										+ "HAVING ped.id = MAX(ped.id)");
		try(pS){
			pS.setInt(1, idUsuario);
			
			ResultSet resultSet = pS.executeQuery();
			
			try(resultSet){
				while(resultSet.next()) {
					PedidoDTO item = new PedidoDTO(resultSet.getInt("Pedido"), resultSet.getString("Nombre"), resultSet.getString("Talla"), 
												resultSet.getInt("Cantidad"), resultSet.getDouble("Total"));
					items.add(item);
				}
			}
		}
		return items;	
		}catch(SQLException e){
			throw new RuntimeException(e);
		}
	}
	

	private void agregarAlPedido(int idPedido, List<PedidoDTO> datosPedido) {
		datosPedido.forEach(pedidoDTO ->
		{
			try {
				conexion.setAutoCommit(false);
				PreparedStatement ps = conexion.prepareStatement("INSERT INTO itemspedidos(idProducto, cantidad, total, idPedido) VALUES("
										+ 	"(SELECT p.id FROM productos p "
										+ 	"INNER JOIN tallas t ON p.idTalla = t.id "
										+ 	"WHERE p.nombre = ? AND t.talla = ?), "
										+ "?, "
										+ "?, "
										+ "?)");
				try(ps){
					ps.setString(1, pedidoDTO.nombre());
					ps.setString(2, pedidoDTO.talla());
					ps.setInt(3, pedidoDTO.cantidad());
					ps.setDouble(4, pedidoDTO.precioTotal());
					ps.setInt(5, idPedido);
					
					ps.executeUpdate();
				}
				
				conexion.commit();
				conexion.setAutoCommit(true);
				
			} catch (SQLException e) {
				try {
					conexion.rollback();
					conexion.setAutoCommit(true);
				}catch(SQLException e2) {
					throw new RuntimeException(e2);
				}
				
				throw new RuntimeException(e);
			}
		}
		 );
	}

	public int actualizarPedido(int id, List<PedidoDTO> datosPedido) {
		int idPedido = buscarPedidoActivo(id);
		
			try {
				conexion.setAutoCommit(false);
				PreparedStatement pS = 
					conexion.prepareStatement("UPDATE itemspedidos ip SET "
											+ "ip.cantidad = ?, "
											+ "ip.total = ? "
											+ "WHERE idProducto = (SELECT p.id FROM productos p "
																+ "INNER JOIN tallas t ON p.idTalla = t.id "
																+ "WHERE p.nombre = ? AND t.talla = ?) "
											+ "AND idPedido = ?");
				try(pS){
					for(PedidoDTO pedido : datosPedido) {
						pS.setInt(1, pedido.cantidad());
						pS.setDouble(2, pedido.precioTotal());
						pS.setString(3, pedido.nombre());
						pS.setString(4, pedido.talla());
						pS.setInt(5, idPedido);
						
						System.out.println(pedido.toString() + pedido.nombre() + pedido.talla());
						pS.executeUpdate();
						
					}
					conexion.commit();
					conexion.setAutoCommit(true);
				}
			} catch (SQLException e) {
				try {
					conexion.rollback();
					conexion.setAutoCommit(true);
				} catch (SQLException e1) {
					throw new RuntimeException(e1);
				}
			throw new RuntimeException(e);
			} 
		
		return idPedido;
	}
	

	public void desactivarPedido(int idUsuario) {
		int idPedido = buscarPedidoActivo(idUsuario);
		
		try {
			conexion.setAutoCommit(false);
			
			try(PreparedStatement ps = conexion.prepareStatement("UPDATE pedidos SET inactivo = 1 WHERE id = ?")){
				ps.setInt(1, idPedido);
				
				ps.executeUpdate();
			}
			
			conexion.commit();
			conexion.setAutoCommit(true);
			 
		} catch (SQLException e) {
			try {
				conexion.rollback();
				conexion.setAutoCommit(true);
			} catch (SQLException e1) {
				throw new RuntimeException(e1);
			}
			throw new RuntimeException(e);			
		}
	}
	
	
	public void eliminarPedido(int idUsuario) {
		
		int idPedido = buscarPedidoActivo(idUsuario);
		try {
			
			conexion.setAutoCommit(false);
			PreparedStatement ps = conexion.prepareStatement("DELETE FROM pedidos WHERE id = ?");
			
			try(ps){
				ps.setInt(1, idPedido);
				
				ps.executeUpdate();
			}
			
			conexion.commit();
			conexion.setAutoCommit(true);
		} catch (SQLException e) {
			try {
				conexion.rollback();
				conexion.setAutoCommit(true);
			}catch(SQLException e1){
				throw new RuntimeException(e1);
			}
			
			throw new RuntimeException(e);
		}
	}
	
}
