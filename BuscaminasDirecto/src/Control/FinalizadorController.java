package Control;
import model.Coordenada;
import model.Tablero;

public class FinalizadorController {

	static Tablero tablero;
	public FinalizadorController() {

	}
	public void comprobarVictoria() {
		Tablero.comprobarVictoria();
	}
	
	public Coordenada[] getTodasCoordenadasBombas() {
	return Tablero.getTodasCoordenadasMinas();
	}
	
	
}
