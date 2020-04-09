package Control;

import model.Casilla;
import model.Coordenada;
import model.Tablero;
import utiles.ConversorGrafico;
import vista.Botonera;
import vista.ElementoGrafico;

public class DesveladorController {

	static Tablero tablero;
ElementoGrafico elementoGrafico;

	public DesveladorController(int lado, int numeroBombas) {
		super();
	}
	
	public void desvelarCasilla(String nombre) {
		Coordenada coordenada=Botonera.obtenCoordenada(nombre);
		
		  Tablero.desvelarCasilla2(coordenada);
		  
	}
	
	
	public Coordenada[] getTodasCoordenadasBombas() {
	return Tablero.getTodasCoordenadasMinas();
	}
	
	
	//public void actualizaTodo() {
		//botonera.actualizarBotonera(ConversorGrafico.convertir(tablero.getCasillas()));
//	}
	
	public ElementoGrafico[][] convertirAelementosGraficos() {
		return ConversorGrafico.convertir(Tablero.getCasillas());
	}
	
	public Casilla getCasilla(String nombre) {
		Coordenada coordenada=Botonera.obtenCoordenada(nombre);
		return Tablero.getCasilla(coordenada);
	}
	/*
	public ElementoGrafico getElementoGrafico(Coordenada coordenada) {
		return ConversorGrafico.getElementoGrafico(tablero.getCasillas(), coordenada);
	}
	*/

	public void desvelarCasilla(Casilla casilla) {
		Coordenada coordenada=Tablero.getCoordenada(casilla);
		 Tablero.desvelarCasilla2(coordenada);
		
	}

	public void desvelarCasillas(Casilla[] casillas) {
		// TODO Auto-generated method stub
		 Tablero.desvelarCasillas(casillas);
	}

	


	
}
