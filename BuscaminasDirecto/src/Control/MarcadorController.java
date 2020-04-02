package Control;

import model.Coordenada;
import model.Tablero;

public class MarcadorController {

	
	Tablero tablero;
	
	//marca y desmarca casilla
	public boolean marcarCasilla(Coordenada coordenada) {
		return tablero.marcarCasilla(coordenada);
	}
}
