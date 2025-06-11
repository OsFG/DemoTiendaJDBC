package view;

import java.awt.Dimension;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JMenu; 
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;

public class InicioView extends JFrame {

	private JPanel panelGeneralInicio;

	// Constructor que ejecuta Método (el Método sirve para ejecutar las especificaciones de los gráficos -Swing-)
	public InicioView() {
		generarVentanaInicio();
	}
	
	public void generarVentanaInicio(){
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 726, 446);
		panelGeneralInicio = new JPanel();
		panelGeneralInicio.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(panelGeneralInicio);
		panelGeneralInicio.setLayout(null);
		
		JPanel panelMenu = new JPanel();
		panelMenu.setBounds(195, 11, 505, 46);
		panelGeneralInicio.add(panelMenu);
		
				
				JMenuBar menuBar = new JMenuBar();
				panelMenu.add(menuBar);
				menuBar.setFont(new Font("Segoe UI", Font.PLAIN, 9));
				
				// PENDIENTE = iNVESTIGAR RECURSIVIDAD Y CERRAR VENTNA ANTERIOR
				JMenuItem miInicio = new JMenuItem("Inicio");
				miInicio.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {

					}
				});
				menuBar.add(miInicio );
				
				JMenuItem miCatalogo = new JMenuItem("Catálogo");
				miCatalogo.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						CatalogoView catalogoView = new CatalogoView();
						catalogoView.setVisible(true);
					}
				});
				menuBar.add(miCatalogo);
				
				JMenuItem miCarrito = new JMenuItem("Carrito");
				miCarrito.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						CarritoView carritoView = new CarritoView();
						carritoView.setVisible(true);
					}
				});
				menuBar.add(miCarrito);
				
				JMenuItem miUsuario= new JMenuItem("Usuario");
				miUsuario.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						CatalogoView catalogoView = new CatalogoView();
						catalogoView.setVisible(true);
					}
				});
				menuBar.add(miUsuario);
				
				JMenuItem miIniciarSesion = new JMenuItem("Iniciar Sesión");
				miIniciarSesion.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						CatalogoView catalogoView = new CatalogoView();
						catalogoView.setVisible(true);
					}
				});
				menuBar.add(miIniciarSesion);
		
		JPanel boxImagen1 = new JPanel();
		boxImagen1.setBounds(10, 68, 218, 310);
		panelGeneralInicio.add(boxImagen1);
		
		JPanel panelLogeado = new JPanel();
		boxImagen1.add(panelLogeado);
		
		JPanel boxImagen2 = new JPanel();
		boxImagen2.setBounds(229, 68, 218, 310);
		panelGeneralInicio.add(boxImagen2);
		
		JPanel boxImagen3 = new JPanel();
		boxImagen3.setBounds(446, 68, 218, 310);
		panelGeneralInicio.add(boxImagen3);
		
		JLabel lblBienvenido = new JLabel("¡Hola" + " " + "nombreUsuario!");
		lblBienvenido.setBounds(10, 21, 162, 19);
		panelGeneralInicio.add(lblBienvenido);
		lblBienvenido.setFont(new Font("Tahoma", Font.BOLD, 15));
		

	}
}
