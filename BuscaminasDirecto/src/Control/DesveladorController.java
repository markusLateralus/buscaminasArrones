package Control;

import model.Casilla;
import model.Coordenada;
import model.Tablero;
import utiles.ConversorGrafico;
import vista.Botonera;
import vista.ElementoGrafico;

public class DesveladorController {

	Tablero tablero;
ElementoGrafico elementoGrafico;

	public DesveladorController(Tablero tablero) {
		super();
		this.tablero = tablero;
		//elementoGrafico=new ElementoGrafico();
	
	}

	// El Controller tambien hace de Adaptador
	// entre el UI que me da x, y
	// y el tablero que usa Coordenada
	
	public void desvelarCasilla(String nombre) {
		Coordenada coordenada=Botonera.obtenCoordenada(nombre);
		
		  tablero.desvelarCasilla2(coordenada);
		  
	}
	//public void actualizaTodo() {
		//botonera.actualizarBotonera(ConversorGrafico.convertir(tablero.getCasillas()));
//	}
	
	public ElementoGrafico[][] getEntornoGrafico() {
		return ConversorGrafico.convertir(tablero.getCasillas());
	}
	/*
	public ElementoGrafico getElementoGrafico(Coordenada coordenada) {
		return ConversorGrafico.getElementoGrafico(tablero.getCasillas(), coordenada);
	}
	public Casilla getCasilla(Coordenada coordenada) {
		return ConversorGrafico.getCasilla(tablero.getCasillas(), coordenada);
	}
	*/
}