package Control;

import model.Densidad;
import model.Dificultad;
import model.Tablero;
import utiles.Utiles;

public class IniciadorController {
	//HU1  reiniciarjuego
	public Tablero iniciarJuego(Densidad densidad,Dificultad dificultad) {
		return new Tablero(dificultad.getLado(), Utiles.calculaMinas(dificultad.getLado(), densidad.getPorcentaje()));
	}
	

	public Tablero reiniciarJuego(Densidad densidad, Dificultad dificultad) {
		return iniciarJuego(densidad, dificultad);
	}
}
