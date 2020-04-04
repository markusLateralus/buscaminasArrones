package Control;

import model.Densidad;
import model.Dificultad;
import model.Tablero;
import utiles.Utiles;

public class IniciadorController {
	//HU1  reiniciarjuego
	static Tablero tablero;
	Densidad densidad;
	Dificultad dificultad;
	public IniciadorController(int lado, int numeroBombas) {
		tablero=Tablero.getTablero(lado, numeroBombas);
	}
	public Tablero iniciarJuego(Densidad densidad,Dificultad dificultad) {
		//return new Tablero(dificultad.getLado(), Utiles.calculaMinas(dificultad.getLado(), densidad.getPorcentaje()));
	return  tablero;
	}
	

	public Tablero reiniciarJuego(Densidad densidad, Dificultad dificultad) {
		return iniciarJuego(densidad, dificultad);
	}
}
