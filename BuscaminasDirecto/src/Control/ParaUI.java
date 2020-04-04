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
	 int lado ;
	 int minas;
	public ParaUI() {
	//	lado= getDificultad().getLado();
		//minas=Utiles.calculaMinas(lado, getDensidad().getPorcentaje());
		//iniciadorController=new IniciadorController(lado, minas);
		finalizadorController=new FinalizadorController();		
		jugar();
		btnReiniciar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				reiniciar();
			
			}
		});
		
	}
	private void jugar() {
		lado= getDificultad().getLado();
		minas=Utiles.calculaMinas(lado, getDensidad().getPorcentaje());
		iniciadorController=new IniciadorController(lado, minas);
	//	iniciadorController.iniciarJuego(getDensidad(), getDificultad());
		desveladorController = new DesveladorController(lado, minas);
		Botonera botonera = new Botonera(lado, desveladorController, finalizadorController);
		getHuecoBotonera().add(botonera);
		
	}

	private void reiniciar() {
		lado= getDificultad().getLado();
		minas=Utiles.calculaMinas(lado, getDensidad().getPorcentaje());		
		//La parte del UI
		iniciadorController=new IniciadorController(lado, minas);
		iniciadorController.reiniciarJuego(getDensidad(), getDificultad());
		getHuecoBotonera().removeAll();
		desveladorController = new DesveladorController(lado, minas);
		Botonera botonera = new Botonera(lado, desveladorController, finalizadorController);
		getHuecoBotonera().add(botonera);	
		pack();
	}
}
