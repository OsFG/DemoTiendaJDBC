package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;

import modelo.Producto;

public class ProductoDAO {

	private Connection conexion;
	
	ProductoDAO(Connection conexion){
		this.conexion = conexion;
	}
	
/*	public void guardar(Producto producto) {
		
		PreparedStatement statement = conexion.prepareStatement("");
	}*/
}
