package Control;

import model.Densidad;
import model.Dificultad;
import model.Tablero;
import utiles.Utiles;

public class IniciadorController {

	static Tablero tablero;

	public IniciadorController(int lado, int numeroBombas) {
		tablero=Tablero.getTablero(lado, numeroBombas);
	}
	public Tablero iniciarJuego(Densidad densidad,Dificultad dificultad) {
	return  tablero;
	}
	

	public Tablero reiniciarJuego(Densidad densidad, Dificultad dificultad) {		
		return iniciarJuego(densidad, dificultad);
	}
}
