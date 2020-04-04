package Control;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import utiles.Utiles;
import vista.Botonera;
import vista.UserInterface;

public class ParaUI extends UserInterface{
	DesveladorController desveladorController;
	FinalizadorController finalizadorController;
	IniciadorController iniciadorController;
	
	 int lado = getDificultad().getLado();
	
	public ParaUI() {
		 int minas=Utiles.calculaMinas(lado, getDensidad().getPorcentaje());
		iniciadorController=new IniciadorController();
		iniciadorController.iniciarJuego( lado, minas);
		 desveladorController = new DesveladorController(lado, minas);
		
		finalizadorController=new FinalizadorController();
		btnReiniciar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cargarJuego();
			}
		});
		
	}

	private void cargarJuego() {
		
		//tablero = new Tablero(lado, Utiles.calculaMinas(lado, getDensidad().getPorcentaje()));

		
		//La parte del UI
		getHuecoBotonera().removeAll();
		Botonera botonera = new Botonera(lado, desveladorController, finalizadorController);
		getHuecoBotonera().add(botonera);
		// Obliga al redibujado del ui cuando hay cambio de tamano de la ventana
		//pack();
	}
}
