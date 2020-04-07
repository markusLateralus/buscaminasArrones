package Control;

import model.Coordenada;
import model.Tablero;
import vista.Botonera;

public class MarcadorController {

	
	static Tablero tablero;
	
	//marca y desmarca casilla
	public boolean  marcarCasilla(String nombre) {
		Coordenada coordenada=Botonera.obtenCoordenada(nombre);
		return Tablero.marcarCasilla(coordenada);
	}
}
