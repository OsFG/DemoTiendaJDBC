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

public class CatalogoView extends JFrame {
	
	private JPanel  panelLoggeo, opcionesCatalogo, panelTabla, panelMenu, itemsPedidos;
	private JMenuBar menuBar;
	private JMenuItem miInicio, miCatalogo, miCarrito, miUsuario, miIniciarSesion;
	private JLabel lblBienvenido, lblProducto, lblCantidad;
	private DefaultTableModel modeloCatalogo, modeloPedido;
	private JTable tablaCatalogo, tablaItemsPedidos;
	private JButton btnPlayeras , btnGorras, btnAgregar, btnMas, btnMenos, btnEliminarTodo, btnMas_1, btnMenos_1, btnGuardarPedido;
	private JScrollPane scrollPane, pedido;
	
	private CategoriaController categoriaController;
	private PedidoController pedidoController;
	private JTextField productoEscogido, cantidadPedida, tallaEscogida;
	
	private Integer cant, filaSeleccionada;
	private double precioEscogido;

	// Constructor que ejecuta Método (el Método sirve para ejecutar las especificaciones de los gráficos -Swing-)
	public CatalogoView() {
		this.categoriaController = new CategoriaController();
		this.pedidoController = new PedidoController();
		
		getContentPane().setLayout(null);
		setSize(726, 646);
		Container contenedorPrincipal = new Container();;
		contenedorPrincipal.setSize(726, 646);
		getContentPane().add(contenedorPrincipal);
			
		componentesVentana(contenedorPrincipal);
		generarTablaCatalogo(contenedorPrincipal);
		generarTablaPedido();
		accionesDelFormulario();
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
		
		opcionesCatalogo = new JPanel();
		opcionesCatalogo.setBounds(10, 68, 690, 34);
		contenedorPrincipal.add(opcionesCatalogo);
		opcionesCatalogo.setLayout(null);
		
		btnGorras = new JButton("Gorras");

		btnGorras.setBounds(197, 0, 88, 34);
		opcionesCatalogo.add(btnGorras);
		
		btnPlayeras = new JButton("Playeras");
		btnPlayeras.setBounds(324, 0, 88, 34);
		opcionesCatalogo.add(btnPlayeras);
		
		itemsPedidos = new JPanel();
		itemsPedidos.setBounds(10, 336, 690, 151);
		contenedorPrincipal.add(itemsPedidos);
		itemsPedidos.setLayout(null);
		
		lblProducto = new JLabel("Producto");
		lblProducto.setBounds(10, 11, 70, 14);
		itemsPedidos.add(lblProducto);
		
		lblCantidad = new JLabel("Cantidad");
		lblCantidad.setBounds(10, 65, 59, 14);
		itemsPedidos.add(lblCantidad);
		
		btnAgregar = new JButton("Agregar");
		btnAgregar.setBounds(10, 90, 89, 23);
		itemsPedidos.add(btnAgregar);
		
		btnMas = new JButton("+");
		btnMas.setBounds(597, 27, 45, 23);
		itemsPedidos.add(btnMas);
		
		btnMenos = new JButton("-");
		btnMenos.setBounds(548, 27, 45, 23);
		itemsPedidos.add(btnMenos);
		
		btnEliminarTodo = new JButton("Eliminar Todo");
		btnEliminarTodo.setBounds(548, 61, 132, 23);
		itemsPedidos.add(btnEliminarTodo);

	}
	
	private void generarTablaCatalogo(Container contenedorPrincipal) {
		tablaCatalogo = new JTable();
		tablaCatalogo.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tablaCatalogo.setBackground(Color.WHITE);

		
		modeloCatalogo = (DefaultTableModel) tablaCatalogo.getModel();
		modeloCatalogo.addColumn("Nombre");
		modeloCatalogo.addColumn("Genero");
		modeloCatalogo.addColumn("Talla");
		modeloCatalogo.addColumn("Precio");
		
		
		scrollPane = new JScrollPane(tablaCatalogo);
		scrollPane.setBounds(10, 117, 690, 208);
		scrollPane.setBackground(Color.WHITE);
		
		contenedorPrincipal.add(scrollPane);
    
	}
	

	private void generarTablaPedido() {
		tablaItemsPedidos = new JTable();
		
		modeloPedido = (DefaultTableModel) tablaItemsPedidos.getModel();
		
		//Hacer Método para agregar estas columnas 
		modeloPedido.addColumn("Nombre");
		modeloPedido.addColumn("Talla");
		modeloPedido.addColumn("Cantidad");
		modeloPedido.addColumn("Precio Total");
		
		pedido = new JScrollPane();
		pedido.setBounds(242, 11, 296, 129);
		pedido.setViewportView(tablaItemsPedidos);
		itemsPedidos.add(pedido);
		
		btnMas_1 = new JButton("+");
		btnMas_1.setBounds(187, 61, 45, 23);
		itemsPedidos.add(btnMas_1);
		
		btnMenos_1 = new JButton("-");
		btnMenos_1.setBounds(136, 61, 45, 23);
		itemsPedidos.add(btnMenos_1);
		
		btnGuardarPedido = new JButton("Guardar Pedido");
		btnGuardarPedido.setBounds(548, 95, 132, 23);
		itemsPedidos.add(btnGuardarPedido);
		
		productoEscogido = new JTextField();
		productoEscogido.setBounds(74, 8, 158, 20);
		itemsPedidos.add(productoEscogido);
		productoEscogido.setColumns(10);
		
		cantidadPedida = new JTextField();
		cantidadPedida.setText("1");
		cantidadPedida.setBounds(74, 62, 52, 20);
		itemsPedidos.add(cantidadPedida);
		cantidadPedida.setColumns(10);
		
		JLabel lblTalla = new JLabel("Talla");
		lblTalla.setBounds(10, 40, 59, 14);
		itemsPedidos.add(lblTalla);
		
		tallaEscogida = new JTextField();
		tallaEscogida.setColumns(10);
		tallaEscogida.setBounds(74, 37, 158, 20);
		itemsPedidos.add(tallaEscogida);
	}
	
	private void accionesDelFormulario() {
		
		btnGorras.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				poblarTablaCatalogo("gorras");
			}
		});
		
		btnPlayeras.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				poblarTablaCatalogo("playeras");
			}
		});
		
		btnMas_1.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				sumarCantidad();
			}
		});
	
		btnMenos_1.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				restarCantidad();
			}
		});
	
		btnEliminarTodo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				eliminarTodo();
			}
		});
	
		tablaCatalogo.addMouseListener(new MouseAdapter() {
		    @Override
		    public void mouseClicked(MouseEvent e) {
		        int filaSeleccionada = tablaCatalogo.getSelectedRow();
		        int columnaSeleccionada = tablaCatalogo.getSelectedColumn();
		        
		        
		        if (filaSeleccionada != -1 && columnaSeleccionada != -1) {
		           productoEscogido.setText((String) modeloCatalogo.getValueAt(filaSeleccionada, 0));
		           tallaEscogida.setText((String) modeloCatalogo.getValueAt(filaSeleccionada, 2));
		           precioEscogido = (double) modeloCatalogo.getValueAt(filaSeleccionada, 3);
		        }
		        return; 
		    }
		});
		
		btnAgregar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String nombreProducto = productoEscogido.getText();
				String tallaPedida = tallaEscogida.getText();
				Integer cantidad = Integer.parseInt(cantidadPedida.getText());
				poblarTablaPedido(nombreProducto, tallaPedida, cantidad, precioEscogido);
			} 
		});
		
		btnMas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				sumarCantidadPedido();
			}
		});
		
		btnMenos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				restarCantidadPedido();
			}
		});
		
		tablaItemsPedidos.addMouseListener(new MouseAdapter() {			
		@Override
	    public void mouseClicked(MouseEvent e) {
			
	        filaSeleccionada = tablaItemsPedidos.getSelectedRow();
	        int columnaSeleccionada = tablaItemsPedidos.getSelectedColumn();
	        
	        if (filaSeleccionada != -1 && columnaSeleccionada != -1) {
	        	cant = (Integer) tablaItemsPedidos.getValueAt(filaSeleccionada, 2);
	        	}
			}
		});
		
		btnGuardarPedido.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {		
				guardarPedido();    
			}
		});
		
		
	}
	
	private void poblarTablaCatalogo(String nombre) {
		
		modeloCatalogo.getDataVector().clear();
		Categoria cat = categoriaController.listarConProductos(nombre);
		
		List<Producto> productos = cat.getProductos();
		productos.forEach(producto -> modeloCatalogo.addRow(
										new Object[] {producto.getNombre(),
											  	  producto.getGenero(),
											  	  producto.getTalla().getNombre(),
											  	producto.getPrecio()}
				));
		
	}
	
	private void poblarTablaPedido(String nombre, String talla, Integer cantidad, double precioEscogido) {
		double precioTotal = precioEscogido * cantidad;
		modeloPedido.addRow(new Object[] {nombre,
										  talla,
										 cantidad,
										 precioTotal}
		);
	}
	
	private void sumarCantidad() {
		
		try {
			Integer cantIngresada = Integer.parseInt(cantidadPedida.getText());
			if(cantIngresada < 0 || cantIngresada >= 99) {
				JOptionPane.showMessageDialog(this, "Valores aceptados: 1 - 99");
	            return;
			}
			cantIngresada++;
			
			cantidadPedida.setText(String.valueOf(cantIngresada));
			
		}catch(ArithmeticException | InputMismatchException e) {
			JOptionPane.showMessageDialog(this, "Cantidad no válida");
            return;
		}		
	}
	
	private void restarCantidad() {
		try {
			Integer cantIngresada = Integer.parseInt(cantidadPedida.getText());
			if(cantIngresada <=0 || cantIngresada > 100) {
				JOptionPane.showMessageDialog(this, "Valores aceptados: 1 - 99");
	            return;
			}
			cantIngresada--;
			cantidadPedida.setText(String.valueOf(cantIngresada));
			
		}catch(ArithmeticException | InputMismatchException e) {
			JOptionPane.showMessageDialog(this, "Cantidad no válida");
            return;
		}
	}
	
	private void sumarCantidadPedido() {
		cant++;
		tablaItemsPedidos.setValueAt(cant, filaSeleccionada, 2);
	}
	
	private void restarCantidadPedido() {
		cant--;
		tablaItemsPedidos.setValueAt(cant, filaSeleccionada, 2);
	}
	
	private void guardarPedido() {
		List<PedidoDTO> datosPedido = new ArrayList<>();
		Vector<Vector> dataVector = modeloPedido.getDataVector();

		
	        // Mostrar los datos en consola
	        for (Vector<Object> row : dataVector) {
	        		PedidoDTO pedido = new PedidoDTO((String) row.get(0), (String) row.get(1), (Integer) row.get(2), (Double) row.get(3)); 
	        		datosPedido.add(pedido);
	        }
        int pedido = pedidoController.guardarPedido(1, datosPedido);
        JOptionPane.showMessageDialog(this, String.format("Pedido número: %d", pedido));
	}
	
	private void eliminarTodo() {
		for (int i = 0; i < modeloPedido.getRowCount(); i++) {
			while(i < modeloPedido.getRowCount()) {
				modeloPedido.removeRow(i);
			}
		}
	}
}

	
