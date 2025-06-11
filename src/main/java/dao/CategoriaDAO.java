package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import modelo.Categoria;
import modelo.Producto;
import modelo.Talla;

public class CategoriaDAO {
	
	private Connection conexion;
	
	public CategoriaDAO(Connection conexion){
		this.conexion = conexion;
	}
	
	
	public int guardarCategoria(String nombre) {
		int primaryKey = 0;  // NOTA: si no funciona bien, cambiar la declaración de la Variable dentro del try, directo en la asignación del Valor
		try {
			conexion.setAutoCommit(false);
			
			final PreparedStatement statement = conexion.prepareStatement("INSERT INTO categorias (nombre) " +
											"VALUES(?)",
											Statement.RETURN_GENERATED_KEYS);
			try(statement){
				statement.setString(1, nombre);
				
				statement.executeUpdate();
				conexion.commit();
				
				final ResultSet resulSet = statement.getGeneratedKeys();
				try(resulSet){
					while(resulSet.next()) {
						primaryKey = resulSet.getInt(1); //AQUÍ
						System.out.println("Número de identificador del producto agregado: " + primaryKey);
					}
				}
				
			}
			conexion.setAutoCommit(true);
			return primaryKey;
		} catch (SQLException e) {
			try {
				conexion.rollback();
				conexion.setAutoCommit(true);
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			throw new RuntimeException(e);	
		}
	}

	
	
	public Categoria buscarCategoria(String nombre) {
		Categoria categoria = null;
		
		try {
			final PreparedStatement pS = conexion
									.prepareStatement("SELECT * FROM categorias" +
													  "WHERE nombre = ?");
			try(pS){
				pS.setString(1, nombre);
				
				pS.execute();
				
				
				final ResultSet resultset = pS.getResultSet();
				try(resultset){
					while(resultset.next()) {
						int id = resultset.getInt(1);
						categoria = new Categoria(id, resultset.getString(2));
					}
				}
			}
			
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		return categoria;
		
	}
	
	public Categoria buscarCategoriaYProductos(String nombre) {
		List<Producto> listaDeProductos = new ArrayList<>();
		Categoria cat = null;
		
		try {
			
			PreparedStatement pS = conexion
									.prepareStatement("SELECT p.nombre AS nombre, p.genero AS genero, t.talla AS talla, p.precio AS precio " +
														"FROM categorias c " +
														"INNER JOIN productos p ON c.id = p.idCategoria " +
														"INNER JOIN tallas t ON p.idTalla = t.id " +
														"WHERE c.nombre = ?");
			
			try(pS){
				pS.setString(1, nombre);
				final ResultSet resultset = pS.executeQuery();
				
				try(resultset){
					while(resultset.next()) {
						String pNombre = resultset.getString("nombre");
						String pGenero = resultset.getString("genero");
						String pTalla = resultset.getString("talla");
						double pPrecio = resultset.getDouble("precio");
						
						Producto producto = new Producto(pNombre, pGenero, new Talla(pTalla), pPrecio);
						listaDeProductos.add(producto);
					}
				}
			}
			cat = new Categoria(nombre, listaDeProductos);	
			
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		
	return cat;	
	}
	
	
	private int modificarCategoria(int id, String nombre) {
		
		try{
			conexion.setAutoCommit(false);
			
			try(PreparedStatement pS = conexion.prepareStatement("UPDATE categorias SET "
									  + "nombre = ? "
									  + "id = ?")){
				pS.setString(1, nombre);
				pS.setInt(2, id);
				
				pS.executeUpdate();
				conexion.commit();
				conexion.setAutoCommit(true);
								
				int contadorModificaciones = pS.getUpdateCount();
				return contadorModificaciones;
			}			
		}catch(SQLException e) {
			try {
				conexion.rollback();
				conexion.setAutoCommit(true);
			}catch(SQLException e1){
				throw new RuntimeException(e1);
			}
			throw new RuntimeException(e);
		}

	}
	
	private int eliminarCategoria(int id) {
		
		try {
			conexion.setAutoCommit(false);
			
			PreparedStatement ps =
					conexion.prepareStatement("DELETE FROM categorias WHERE id = ?");
			try(ps){
				ps.setInt(1, id);
				
				ps.executeUpdate();
				
				int filasEliminadas = ps.getUpdateCount();
				conexion.commit();
				conexion.setAutoCommit(true);
				
				return filasEliminadas;
			}
		}catch(SQLException e){
			try {
				conexion.rollback();
				conexion.setAutoCommit(true);
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			throw new RuntimeException(e);
		}	
	}
}
