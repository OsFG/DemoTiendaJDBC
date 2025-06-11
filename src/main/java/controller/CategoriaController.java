package controller;

import dao.CategoriaDAO;
import factory.ConnectionFactory;
import modelo.Categoria;

public class CategoriaController {
	
	private CategoriaDAO categoriaDao;
	
	public CategoriaController() {
		ConnectionFactory conection = new ConnectionFactory(); 
		this.categoriaDao = new CategoriaDAO(conection.recuperarConexion());
	}
	
/*	public int guardarCategoria(String nombre) {  		--> Creado como práctica, pero no se va a usar
		return categoriaDao.guardarCategoria(nombre);
	}
*/	
	
	public Categoria listar(String nombre) {
		return categoriaDao.buscarCategoria(nombre);
	}

	public Categoria listarConProductos(String nombre) {
		return categoriaDao.buscarCategoriaYProductos(nombre);
	}
	
/*	public int modificarCategoria(int id, String nombre) {  --> Creado como práctica, pero no se va a usar
		return categoriaDao.modificarCategoria(id, nombre); 
		
	}
*/	
	
	
	
}
