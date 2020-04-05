package vista;

import java.awt.Color;
import java.awt.Component;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.*;

import Control.DesveladorController;
import Control.FinalizadorController;
import model.Casilla;
import model.Coordenada;
import model.Tablero;


public class Botonera extends JPanel{
	DesveladorController desveladorController;
	FinalizadorController finalizadorController;


	MouseAdapter miMouseAdapter=new MouseAdapter() {
		@Override
		public void mouseClicked(MouseEvent e) {
			super.mouseClicked(e);
			JButton boton = ((JButton) e.getSource());
			
			if(SwingUtilities.isLeftMouseButton(e)) {
				desveladorController.desvelarCasilla(boton.getName());
				finalizadorController.comprobarVictoria();
			
			
			}
			if (SwingUtilities.isRightMouseButton(e)) {
				// queremos marcar
			}
			
			actualizaBotonera(desveladorController.getEntornoGrafico());
			if(Tablero.finPartida) {
			mostrarCasillaBomba();
			}
			
		}
	};
	
	public Botonera(int lado, DesveladorController desveladorController,FinalizadorController finalizador) {
		this.desveladorController=desveladorController;
		this.finalizadorController=finalizador;
		
		// TODO el nombre para cuando hay mas de 10 de lado.
		// debe ser de dos digitos por coordenada aunque el valor<10
		// es decir la coordenada 6:11 debe ser 06:11, por ejemplo.
		setLayout(new GridLayout(lado, lado, 0, 0));
		for (int filas = 0; filas < lado; filas++) {
			for (int columnas = 0; columnas < lado; columnas++) {
				JButton boton = new JButton();
				String nombre = Integer.toString(filas) + Integer.toString(columnas);
				boton.setName(nombre);
				add(boton);
				//Esta linea ahora usar el adapter interno
				boton.addMouseListener(miMouseAdapter);
			}
		}
	}

	public void actualizaBotonera(ElementoGrafico[][] elementos) {
		Component[] components = getComponents();
		for (int i = 0; i < components.length; i++) {
			JButton boton = (JButton)components[i];
			Coordenada coordenada=obtenCoordenada(boton.getName());
			ElementoGrafico elementoGrafico = elementos[coordenada.getPosX()][coordenada.getPosY()];
	//		ConversorGrafico.getCasilla(elementos, elementoGrafico);
			if(!elementoGrafico.isOcultado() && !elementoGrafico.isBomba()) {	
				boton.setText(String.valueOf(elementoGrafico.getValor()));
				this.cambiarColorValorBoton(Integer.parseInt(boton.getText()), boton);
			
			}else if(elementoGrafico.isSenalada()){
				boton.setText("X");
			}else {
				boton.setText("");
			}
		
			
			
			
		}
	}
	private void cambiarColorValorBoton(int valor, JButton boton) {
		
		if(valor==0) {
			boton.setForeground(Color.black);
		}else if(valor==1) {
			boton.setForeground(Color.green);
		}else if(valor==2){
			boton.setForeground(Color.blue);
		}else {
			boton.setForeground(Color.red);
		}
		
	}
	public void mostrarCasillaBomba() {
		Coordenada coordenadas[]=finalizadorController.getTodasCoordenadasBombas();
		Component[] components = getComponents();
		for (int i = 0; i < components.length; i++) {	
			JButton boton = (JButton)components[i];
	   Coordenada coordenada = obtenCoordenada(boton.getName());	
	   for (int j = 0; j < coordenadas.length; j++) {
		   Coordenada coordenada2=coordenadas[j];
		if(coordenada.equals(coordenada2)) {
			boton.setIcon(new ImageIcon(getClass().getResource("/imagenes/bomba2.jpg")));

		}
	}
	  // ElementoGrafico elementoGrafico = elementos[coordenada.getPosX()][coordenada.getPosY()];
		
			}
		}
		
	

	
	
	
	public static Coordenada obtenCoordenada(String name) {
		int pos = name.length() / 2;
		return new Coordenada(Integer.valueOf(name.substring(0, pos)),
				Integer.valueOf(name.substring(pos, name.length())));
	}

}
