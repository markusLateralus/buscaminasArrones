package Control;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import model.Tablero;
import utiles.Utiles;
import vista.Botonera;
import vista.UserInterface;

public class ParaUI extends UserInterface{
	DesveladorController desveladorController;
	
	 int lado = getDificultad().getLado();
	public ParaUI() {
	
		desveladorController = new DesveladorController(lado, Utiles.calculaMinas(lado, getDensidad().getPorcentaje()));

		btnReiniciar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cargarJuego();
			}
		});
		
	}

	private void cargarJuego() {
		
		//tablero = new Tablero(lado, Utiles.calculaMinas(lado, getDensidad().getPorcentaje()));
		Botonera botonera = new Botonera(lado, desveladorController);
		
		//La parte del UI
		getHuecoBotonera().removeAll();
		getHuecoBotonera().add(botonera);
		// Obliga al redibujado del ui cuando hay cambio de tamano de la ventana
		//pack();
	}
}
