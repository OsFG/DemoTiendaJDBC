package view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JLabel;
import java.awt.Font;

public class DireccionView extends JFrame {

	private JPanel ventanaInicio;

	// Constructor que ejecuta Método (el Método sirve para ejecutar las especificaciones de los gráficos -Swing-)
	public DireccionView() {
		generarVentanaInicio();
	}
	
	public void generarVentanaInicio(){
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 690, 446);
		ventanaInicio = new JPanel();
		ventanaInicio.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(ventanaInicio);
		ventanaInicio.setLayout(null);
		
		JPanel panelMenu = new JPanel();
		panelMenu.setBounds(252, 11, 412, 46);
		ventanaInicio.add(panelMenu);

		
		JMenuBar menuBar = new JMenuBar();
		menuBar.setBounds(124, 86, 101, 22);
		panelMenu.add(menuBar);
		
		JMenuItem miInicio = new JMenuItem ("Inicio");
		menuBar.add(miInicio );
		
		JMenuItem miCatalogo = new JMenuItem ("Catálogo");
		menuBar.add(miCatalogo);
		
		JMenuItem miCarrito = new JMenuItem ("Carrito");
		menuBar.add(miCarrito);
		
		JMenuItem miUsuario= new JMenuItem("Usuario");
		menuBar.add(miUsuario);
		
		JPanel panelLoggeeo = new JPanel();
		panelLoggeeo.setBounds(31, 11, 172, 34);
		ventanaInicio.add(panelLoggeeo);
		
		JLabel lblBienvenido = new JLabel("¡Hola" + " " + "nombreUsuario!");
		lblBienvenido.setFont(new Font("Tahoma", Font.BOLD, 15));
		panelLoggeeo.add(lblBienvenido);
		
		JPanel boxImagen1 = new JPanel();
		boxImagen1.setBounds(10, 68, 218, 310);
		ventanaInicio.add(boxImagen1);
		
		JPanel boxImagen2 = new JPanel();
		boxImagen2.setBounds(229, 68, 218, 310);
		ventanaInicio.add(boxImagen2);
		
		JPanel boxImagen3 = new JPanel();
		boxImagen3.setBounds(446, 68, 218, 310);
		ventanaInicio.add(boxImagen3);
		
		


	}
}
