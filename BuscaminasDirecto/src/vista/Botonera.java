package vista;

import java.awt.Color;
import java.awt.Component;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.*;

import Control.DesveladorController;
import Control.FinalizadorController;
import Control.MarcadorController;
import model.Casilla;
import model.Coordenada;
import model.Tablero;
import utiles.ConversorGrafico;

public class Botonera extends JPanel {
	DesveladorController desveladorController;
	FinalizadorController finalizadorController;
	MarcadorController marcadorController;
	Casilla[] casillas;
	Casilla [] casillasMarcadas;
	Casilla casillaMaestra;
	Casilla casillaAuxiliar;
	String nombreBoton;
	String nombreBotonAuxiliar;
	MouseAdapter miMouseAdapter = new MouseAdapter() {
		@Override
		public void mouseClicked(MouseEvent e) {
			super.mouseClicked(e);
			JButton boton = ((JButton) e.getSource());
		    nombreBoton=boton.getName();	
		  //  System.out.println(nombreBoton );
			if (SwingUtilities.isLeftMouseButton(e)) {
			    casillaMaestra=desveladorController.getCasilla(boton.getName());
			    nombreBotonAuxiliar=boton.getName();	

				casillas=marcadorController.getTodasCasillasAlrededor(boton.getName());	
			casillasMarcadas=marcadorController.getTodasCasillasMarcadasAlrededor(casillas);
		System.out.println(casillasMarcadas.length);
				if( marcadorController.getTodasCasillasMarcadasAlrededor(casillas)==null) {
				    System.out.println("tamanao" + marcadorController.getTodasCasillasMarcadasAlrededor(casillas));
				}
				else if(casillasMarcadas.length==0) {
					desveladorController.desvelarCasilla(boton.getName());
					finalizadorController.comprobarVictoria();
				}
				else if(casillasMarcadas.length>0){
					
					
					desveladorController.desvelarCasillas(casillasMarcadas);
					finalizadorController.comprobarVictoria();
				}
			}
			
			if (SwingUtilities.isRightMouseButton(e)) {
				Casilla casillaMarcada=marcadorController.getCasilla(boton.getName());
				boolean bandera=marcadorController.marcar(casillaMarcada);
				ponerBanderaCasilla(boton,bandera);
				int minasAlrededor=marcadorController.getMinasAlrededor(casillaMaestra);
				int contador=0;			
				
				if(minasAlrededor>0) {				
					for (int i = 0; i < casillasMarcadas.length; i++) {
						Casilla casillaAuxiliar=casillas[i];
						if(contador<=casillasMarcadas.length) {
						if(casillaAuxiliar.equals(casillaMarcada)){
							if(contador<minasAlrededor) {
								contador++;
							}
							
							//ss
							}
						
						}
					}
					if(contador==minasAlrededor) {
							
					
						}
					
					
				}else {
					bandera=marcadorController.marcar(casillaMarcada);
					ponerBanderaCasilla(boton,bandera);
				}
			
				
			//boolean casillaMarcada=marcadorController.marcar(boton.getName());
			//ponerBanderaCasilla(boton,casillaMarcada);
			
				
			}

			actualizaBotonera(desveladorController.convertirAelementosGraficos());
			if (Tablero.finPartida) {
				mostrarCasillaBomba();
			}

		}
	};

	public Botonera(int lado, DesveladorController desveladorController, FinalizadorController finalizador, MarcadorController marcador) {
		this.desveladorController = desveladorController;
		this.finalizadorController = finalizador;
		this.marcadorController=marcador;

		// TODO el nombre para cuando hay mas de 10 de lado.
		// debe ser de dos digitos por coordenada aunque el valor<10
		// es decir la coordenada 6:11 debe ser 06:11, por ejemplo.
		setLayout(new GridLayout(lado, lado, 0, 0));
		String nombre;
		for (int filas = 0; filas < lado; filas++) {
			for (int columnas = 0; columnas < lado; columnas++) {
				JButton boton = new JButton();
				if(filas>9 || columnas>9) {
					 nombre= Integer.toString(0)+ Integer.toString(filas) +Integer.toString(0)+ Integer.toString(columnas);
					boton.setName(nombre);
					add(boton);
					boton.addMouseListener(miMouseAdapter);
				}
				else{
					nombre = Integer.toString(filas) + Integer.toString(columnas);
					boton.setName(nombre);
					add(boton);
					boton.addMouseListener(miMouseAdapter);
				}
			
				
				// Esta linea ahora usar el adapter interno
			
			}
		}
	}

	public void actualizaBotonera(ElementoGrafico[][] elementos) {
		Component[] components = getComponents();
		for (int i = 0; i < components.length; i++) {
			JButton boton = (JButton) components[i];
			Coordenada coordenada = obtenCoordenada(boton.getName());
			ElementoGrafico elementoGrafico = elementos[coordenada.getPosX()][coordenada.getPosY()];
			if (!elementoGrafico.isOcultado() && !elementoGrafico.isBomba()) {
				boton.setText(String.valueOf(elementoGrafico.getValor()));
				this.cambiarColorValorBoton(Integer.parseInt(boton.getText()), boton);

			} else if (elementoGrafico.isSenalado()) {
				//ponerBanderaCasilla(boton);
			} else {
				boton.setText("");
			}

		}
	}

	
	private void ponerBanderaCasilla(JButton boton, boolean casillaMarcada) {
		ImageIcon imagen=new ImageIcon(getClass().getResource("/imagenes/bandera.jpg"));
		if(casillaMarcada) {
			boton.setIcon(imagen);
		}
		else {
			boton.setIcon(null);
			
		}

		
	}
	
	private void cambiarColorValorBoton(int valor, JButton boton) {

		if (valor == 0) {
			boton.setForeground(Color.black);
		} else if (valor == 1) {
			boton.setForeground(Color.green);
		} else if (valor == 2) {
			boton.setForeground(Color.blue);
		} else {
			boton.setForeground(Color.red);
		}

	}

	public void mostrarCasillaBomba() {
		Coordenada coordenadas[] = desveladorController.getTodasCoordenadasBombas();
		Component[] components = getComponents();
		for (int i = 0; i < components.length; i++) {
			JButton boton = (JButton) components[i];
			Coordenada coordenada = obtenCoordenada(boton.getName());
			for (int j = 0; j < coordenadas.length; j++) {
				Coordenada coordenada2 = coordenadas[j];
				if (coordenada.equals(coordenada2)) {
					boton.setIcon(new ImageIcon(getClass().getResource("/imagenes/bomba2.jpg")));

				}
			}

		}
	}

	public static Coordenada obtenCoordenada(String name) {
		int pos = name.length() / 2;
		return new Coordenada(Integer.valueOf(name.substring(0, pos)),
				Integer.valueOf(name.substring(pos, name.length())));
	}

}
