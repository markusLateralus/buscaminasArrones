package Control;

import model.Casilla;
import model.Coordenada;
import model.Densidad;
import model.Tablero;
import utiles.ConversorGrafico;
import utiles.Utiles;
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
	//public void actualizaTodo() {
		//botonera.actualizarBotonera(ConversorGrafico.convertir(tablero.getCasillas()));
//	}
	
	public ElementoGrafico[][] getEntornoGrafico() {
		return ConversorGrafico.convertir(Tablero.getCasillas());
	}
	/*
	public ElementoGrafico getElementoGrafico(Coordenada coordenada) {
		return ConversorGrafico.getElementoGrafico(tablero.getCasillas(), coordenada);
	}
	*/


	
}
