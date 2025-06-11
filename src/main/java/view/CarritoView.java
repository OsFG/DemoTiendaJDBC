package view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import controller.CategoriaController;
import controller.PedidoController;
import modelo.Categoria;
import modelo.ItemPedido;
import modelo.Pedido;
import modelo.PedidoDTO;
import modelo.Producto;

import javax.swing.CellEditor;
import javax.swing.JButton;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Vector;
import java.awt.event.ActionEvent;
import java.awt.Color;
import java.awt.Container;
import java.awt.Cursor;

import javax.swing.JList;
import javax.swing.JTextField;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.ListSelectionModel;

public class CarritoView extends JFrame {
	
	private JPanel  panelLoggeo, panelMenu, panelItemsPedidos;
	private JMenuBar menuBar;
	private JMenuItem miInicio, miCatalogo, miCarrito, miUsuario, miIniciarSesion;
	private JLabel lblBienvenido;
	private DefaultTableModel modeloPedido;
	private JTable tablaItemsPedidos;
	private JButton btnMas, btnMenos, btnEliminarTodo, btnGuardarPedido;
	private JScrollPane pedido;

	private PedidoController pedidoController;
	
	private Integer cant, filaSeleccionada;
	private Double precio;

	// Constructor que ejecuta Método (el Método sirve para ejecutar las especificaciones de los gráficos -Swing-)
	public CarritoView() {

		this.pedidoController = new PedidoController();
		
		getContentPane().setLayout(null);
		setSize(767, 462);
		Container contenedorPrincipal = new Container();
		contenedorPrincipal.setLocation(0, 0);;
		contenedorPrincipal.setSize(741, 398);
		getContentPane().add(contenedorPrincipal);
			
		componentesVentana(contenedorPrincipal);
		generarTablaPedido();
		accionesDelFormulario();
		poblarTablaPedido(1);
	}

	private void componentesVentana(Container contenedorPrincipal){
	
		panelMenu = new JPanel();
		panelMenu.setBounds(206, 11, 494, 46);
		contenedorPrincipal.add(panelMenu);
		
		menuBar = new JMenuBar();
		menuBar.setBounds(476, 23, 250, 22);
		panelMenu.add(menuBar);
		
		miInicio = new JMenuItem ("Inicio");
		menuBar.add(miInicio );
		
		miCatalogo = new JMenuItem ("Catálogo");
		menuBar.add(miCatalogo);
		
		miCarrito = new JMenuItem ("Carrito");
		menuBar.add(miCarrito);
		
		miUsuario= new JMenuItem("Usuario");
		menuBar.add(miUsuario);
		
		miIniciarSesion = new JMenuItem("Iniciar Sesión");
		menuBar.add(miIniciarSesion);
		
		panelLoggeo = new JPanel();
		panelLoggeo.setBounds(31, 11, 172, 34);
		contenedorPrincipal.add(panelLoggeo);
		
		lblBienvenido = new JLabel("¡Hola" + " " + "nombreUsuario!");
		lblBienvenido.setFont(new Font("Tahoma", Font.BOLD, 15));
		panelLoggeo.add(lblBienvenido);
		
		panelItemsPedidos = new JPanel();
		panelItemsPedidos.setBounds(10, 68, 731, 302);
		contenedorPrincipal.add(panelItemsPedidos);
		panelItemsPedidos.setLayout(null);
		
		btnMas = new JButton("+");
		btnMas.setBounds(602, 29, 53, 23);
		panelItemsPedidos.add(btnMas);
		
		btnMenos = new JButton("-");
		btnMenos.setBounds(665, 29, 56, 23);
		panelItemsPedidos.add(btnMenos);
		
		btnEliminarTodo = new JButton("Eliminar Todo");
		btnEliminarTodo.setBounds(599, 121, 132, 23);
		panelItemsPedidos.add(btnEliminarTodo);

	}	

	private void generarTablaPedido() {
		tablaItemsPedidos = new JTable();
		
		modeloPedido = (DefaultTableModel) tablaItemsPedidos.getModel();
		
		//Hacer Método para agregar estas columnas 
		modeloPedido.addColumn("ID");
		modeloPedido.addColumn("Nombre");
		modeloPedido.addColumn("Talla");
		modeloPedido.addColumn("Cantidad");
		modeloPedido.addColumn("Precio Total");
		
		pedido = new JScrollPane();
		pedido.setBounds(10, 11, 582, 265);
		pedido.setViewportView(tablaItemsPedidos);
		panelItemsPedidos.add(pedido);
		
		btnGuardarPedido = new JButton("Guardar Pedido");
		btnGuardarPedido.setBounds(599, 73, 132, 23);
		panelItemsPedidos.add(btnGuardarPedido);
	}
		
	
	private void accionesDelFormulario() {
				
		btnEliminarTodo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				eliminarTodo();
			}
		});
			
		
		btnMas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				aumentarTotalPedido();
			}
		});
		
		btnMenos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				disminuirCantidadPedido();
			}
		});
		
		tablaItemsPedidos.addMouseListener(new MouseAdapter() {			
		@Override
	    public void mouseClicked(MouseEvent e) {
			
	        filaSeleccionada = tablaItemsPedidos.getSelectedRow();
	        int columnaSeleccionada = tablaItemsPedidos.getSelectedColumn();
	   // MODIFICAR TODO LO RELACIONADO CON CANT     
	        if (filaSeleccionada != -1 && columnaSeleccionada != -1) {
	        	cant = (Integer) tablaItemsPedidos.getValueAt(filaSeleccionada, 3);
	        	precio = (Double) tablaItemsPedidos.getValueAt(filaSeleccionada, 4);
	        	}
			}
		});
		
		btnGuardarPedido.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				actualizarPedido();    
			}
		});
	}

	private void poblarTablaPedido(int idUsuario) {
		
		List<PedidoDTO> pedido = pedidoController.leerPedido(idUsuario);
		modeloPedido.addRow( new Object[]{
										pedido.get(0).id()});
		
		pedido.forEach(itemPedido -> modeloPedido.addRow(
				new Object[] {"", // Espacio vacío para id
							  itemPedido.nombre(),
							  itemPedido.talla(),
							  itemPedido.cantidad(),
							  itemPedido.precioTotal()
				})
		);
	}
		
	private void aumentarTotalPedido() {
		double total = actualizarPrecioItem();
		cant++;
		precio = cant * total;
		tablaItemsPedidos.setValueAt(cant, filaSeleccionada, 3);
		tablaItemsPedidos.setValueAt(precio, filaSeleccionada, 4);
	}
	
	private void disminuirCantidadPedido() {
		double total = actualizarPrecioItem();
		cant--;
		precio = cant * total;
		tablaItemsPedidos.setValueAt(cant, filaSeleccionada, 3);
		tablaItemsPedidos.setValueAt(precio, filaSeleccionada, 4);
	}
	
	private double actualizarPrecioItem() {
		return precio/cant;
	}
	
	private void actualizarPedido() {
		List<PedidoDTO> datosPedido = new ArrayList<>();
		Vector<Vector> dataVector = modeloPedido.getDataVector();
		int id = (int) modeloPedido.getValueAt(0, 0);

	        for (Vector<Object> row : dataVector) {
	        	if(row.get(1) != null) { 
	        		PedidoDTO ip = new PedidoDTO(id, (String) row.get(1), (String) row.get(2), (Integer) row.get(3), (Double) row.get(4)); 
	        		datosPedido.add(ip);
	        	}	        	
	        }

        int pedido = pedidoController.actualizarPedido(1, datosPedido);
        JOptionPane.showMessageDialog(this, String.format("Pedido número: %d", pedido));
	}
	
	private void eliminarTodo() {
		for (int i = 0; i < modeloPedido.getRowCount(); i++) {
			while(i < modeloPedido.getRowCount()) {
				modeloPedido.removeRow(i);
			}
		}
		
		pedidoController.desactivarPedido(1);
	}
	
	
	
	
	
}

	
