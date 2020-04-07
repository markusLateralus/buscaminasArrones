package model;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import utiles.Utiles;

class TableroTest {
	private int lado;
	private int porcentaje;
	private int minas;
	private static Tablero tablero;

	// SE ejecuta antes de cada una de las pruebas
	@BeforeEach
	void setUp() {
		lado = 4;
		porcentaje = 10;
		minas = Utiles.calculaMinas(lado, porcentaje);
		Tablero.getTablero(lado, minas);
	}
	@Test
	void testIncrementarMinasAlrededor() {
		int posX=0;
		int posY=0;
		int lado=4;
		Coordenada miMinaCoordenada=new Coordenada(posX, posY);
	
		Tablero.getCasilla(miMinaCoordenada).setMina(true);
		Tablero.establecerMinasAlrededor(miMinaCoordenada);
		int resultado[][]= {
				{0,1,0,0},
				{1,1,0,0},
				{0,0,0,0},
				{0,0,0,0}};
		probando(tablero, resultado);
		//Si coloco la segundaMina;
		posX=3;
		posY=3;
		miMinaCoordenada=new Coordenada(posX, posY);
		Tablero.getCasilla(miMinaCoordenada).setMina(true);
		Tablero.establecerMinasAlrededor(miMinaCoordenada);
		int resultadoDos[][]= {
				{0,1,0,0},
				{1,1,0,0},
				{0,0,1,1},
				{0,0,1,0}};
		probando(tablero, resultadoDos);

	}

	private void probando(Tablero tablero, int[][] resultado) {
		Casilla[][] casillas = Tablero.getCasillas();
		for (int i = 0; i < casillas.length; i++) {
			for (int j = 0; j < casillas.length; j++) {
				assertEquals(resultado[i][j],casillas[i][j].getMinasAlrededor());
			}
		}
	}
	
	//este metodo necesita que colocarMinas funcione

	@Test
	public void mostrarTablero() {
		for (int i = 0; i < lado; i++) {
			for (int j = 0; j < lado; j++) {
				Coordenada posicion = new Coordenada(i, j);
				Casilla casilla = Tablero.getCasilla(posicion);
				String letrero;
				if (casilla.isVelada()) {
					letrero = "#";

					if (casilla.isMina()) {
						letrero = "X";
					} else if (casilla.isMina()) {
						letrero = "M";
					}
				} else {
					letrero = String.valueOf(casilla.getMinasAlrededor());
				}
				System.out.println(" " + letrero);

			}
			System.out.println();
		}
	}

	
	@Test
	public void testDesvelarCasilla() {
		// Hay que probar que se desvela si no esta marcada
		// Si hay 0 minas debe comenzar un proceso recursivo y
		// debÃ©is probar que desvela las casillas contiguas que tb
		// son cero
		boolean error=false;
		int i=0;
		int j=0;
		
		do {
			do {
				Coordenada posicion=new Coordenada(i,j);
				Casilla actual=Tablero.getCasilla(posicion);
				mostrarTablero();
				if(!actual.isMina() && actual.getMinasAlrededor()==0 && actual.isVelada()) {
					Tablero.desvelarCasilla2(posicion);
					error=comprobarDesvelo();//sino es false continua con la ejecucion
				}
				mostrarTablero();
			}while(++j<lado && !error);
			j=0;
		}while(++i<lado && !error);
		assertTrue(!error);
		
	}
	
	
	@Test
	private boolean comprobarDesvelo() {
	
	boolean error=false;
	int i=0;
	int j=0;
	
	do {
		do {
			Coordenada posicion=new Coordenada(i,j);
			Casilla actual=Tablero.getCasilla(posicion);
			if(actual.getMinasAlrededor()==0 && !actual.isVelada()) {
				error=desveladasAroundMe(posicion);
			}
		
		}while(++j<lado && !error);
		j=0;
	}while( ++i<lado && !error);
	return error;

			
	}
	
	private boolean desveladasAroundMe(Coordenada posicion) {
		boolean error=false;
		for(int i=0; i<8 && !error; i++) {
			Coordenada alrededor=posicion.creaCoordenadaAlrededor(i);
			if(Tablero.isDentroLimites(alrededor, lado)) {
				Casilla actual=Tablero.getCasilla(alrededor);
				if(!posicion.equals(actual)) {
					error=actual.isVelada();
				}
			}
		}
		return error;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	@Test
	void testTableroColocarMinas() {
		int contadorMinas = 0;
		for (int i = 0; i < lado; i++) {
			for (int j = 0; j < lado; j++) {
				if (tablero.getCasilla(new Coordenada(i, j)).isMina()) {
					contadorMinas++;
				}
			}
		}
		assertEquals(minas, contadorMinas);
	}
	
	
	

	// Deberia probar este codigo
	private int contarCasillasSinBombas(Tablero tablero, Coordenada posicion) {
		int totalVeladas = 0;
		int x = posicion.getPosX();
		int y = posicion.getPosY();
		for (int i = x - 1; i < x + 1; i++) { // (x-1,y-1)esquina izquierda superior,
			for (int j = y - 1; j < y + 1; j++) { // (x+1,y+1) esquina inferior derecha
				Coordenada alrededor = new Coordenada(i, j);
				// No tengo en cuenta la posicion que estoy comprobando
				if (!alrededor.equals(posicion)) {
					// está la posicion dentro del tablero?? la posicion es mina??
					if (isDentroLimites(alrededor, lado) && tablero.getCasilla(alrededor).isMarcada()
							&& tablero.getCasilla(alrededor).isMina() == false) {
						totalVeladas++;
					}
				}
			}
		}
		return totalVeladas;
	}

	@Test
	void testTableroIncrementaMinas() {
		for (int i = 0; i < lado; i++) {
			for (int j = 0; j < lado; j++) {
				Coordenada posicion = new Coordenada(i, j);
				Casilla actual = tablero.getCasilla(posicion);
				int minasAlrededor = actual.getMinasAlrededor();
				// necesito algo que cuente las minas aldedor
				assertEquals(minasAlrededor, contarMinasAlrededor(tablero, posicion));
			}
		}
	}

	// Deberia probar este codigo
	private int contarMinasAlrededor(Tablero tablero, Coordenada posicion) {
		int bombasAlrededor = 0;
		int x = posicion.getPosX();
		int y = posicion.getPosY();
		for (int i = x - 1; i < x + 1; i++) { // (x-1,y-1)esquina izquierda superior,
			for (int j = y - 1; j < y + 1; j++) { // (x+1,y+1) esquina inferior derecha
				Coordenada alrededor = new Coordenada(i, j);
				// No tengo en cuenta la posicion que estoy comprobando
				if (!alrededor.equals(posicion)) {
					// está la posicion dentro del tablero?? la posicion es mina??
					if (isDentroLimites(alrededor, lado) && tablero.getCasilla(alrededor).isMina()) {
						bombasAlrededor++;
					}
				}
			}
		}
		return bombasAlrededor;
	}

	private boolean isDentroLimites(Coordenada alrededor, int lado) {
		return alrededor.getPosX() >= 0 && alrededor.getPosX() < lado && alrededor.getPosY() >= 0
				&& alrededor.getPosY() < lado;
	}
}
