package com.origen.DemoTiendaRopaJDBC;

import java.awt.EventQueue;

import view.InicioView;

/**
 * Hello world!
 */
public class App {
    public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					InicioView frame = new InicioView();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
    }
}
