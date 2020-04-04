package Control;

import model.Tablero;

public class FinalizadorController {

	static Tablero tablero;
	public FinalizadorController() {
		// TODO Auto-generated constructor stub
		//tablero=Tablero.getTablero(lado, numeroBombas);
	}
	public void comprobarVictoria() {
		Tablero.comprobarVictoria();
	}
}
