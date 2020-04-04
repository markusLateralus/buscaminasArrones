package model;

import javax.swing.JOptionPane;


import utiles.Utiles;

public class Tablero {

	private static Casilla[][] casillas;
	private static int lado;
	private static int numeroBombas;                                      
	public static Tablero tablero=null;
	private Tablero(int lado, int numeroBombas) {
		super();
		this.lado = lado;
		crearTablero(lado);
		colocarMinas(lado, numeroBombas);
	}
	public static Tablero getTablero(int lado, int numeroBombas) {
		if(tablero==null) {
			new Tablero(lado, numeroBombas);
		}
		return tablero;
	}

	private static void establecerMinasAlrededor(Coordenada posicionMinaCoordenada) {
		for (int i = 0; i < 8; i++) {
			Coordenada alrededor = posicionMinaCoordenada.creaCoordenadaAlrededor(i);
			if (validaCoordenada(alrededor)) {
				setMinasAlrededor(alrededor);
				// System.out.println("bombas alrededor " + alrededor.getPosX() +
				// alrededor.getPosY());
			}
		}
	}

	private static boolean validaCoordenada(Coordenada posicion) {
		return posicion.getPosX() >= 0 && posicion.getPosY() >= 0 && posicion.getPosX() < getLado()
				&& posicion.getPosY() < getLado();
	}

	private static void setMinasAlrededor(Coordenada coordenada) {
		casillas[coordenada.getPosX()][coordenada.getPosY()]
				.setMinasAlrededor(casillas[coordenada.getPosX()][coordenada.getPosY()].getMinasAlrededor() + 1);
	}

	private static int colocarMinas(int lado, int numeroMinas) { // aleatorio
		// TODO ?
		numeroBombas=numeroMinas;
		for (int i = 0; i < numeroMinas;) {
			Coordenada posicion = new Coordenada(Utiles.dameNumero(getLado()), Utiles.dameNumero(getLado()));
			if (!isMina(posicion)) {
				setMina(posicion, true);
				i++;
				establecerMinasAlrededor(posicion);
				System.out.println("bomba:" + i + " " + posicion.getPosX() + posicion.getPosY());

			}
		}
		return numeroBombas;

	}

	private static int contarMinasAlrededor(Coordenada posicion) {
		int bombasAlrededor = 0;
		int x = posicion.getPosX();
		int y = posicion.getPosY();
		for (int i = x - 1; i < x + 1; i++) { // (x-1,y-1)esquina izquierda superior,
			for (int j = y - 1; j < y + 1; j++) { // (x+1,y+1) esquina inferior derecha
				Coordenada alrededor = new Coordenada(i, j);
				// No tengo en cuenta la posicion que estoy comprobando
				if (!alrededor.equals(posicion)) {
					// est� la posicion dentro del tablero?? la posicion es mina??
					if (isDentroLimites(alrededor, lado) && getCasilla(alrededor).isMina()) {
						bombasAlrededor++;
					}
				}
			}
		}
		return bombasAlrededor;
	}

	private static boolean isDentroLimites(Coordenada alrededor, int lado) {
		return alrededor.getPosX() >= 0 && alrededor.getPosX() < lado && alrededor.getPosY() >= 0
				&& alrededor.getPosY() < lado;
	}

	public static void desvelarCasilla2(Coordenada coordenada) {//solucionado
		Casilla casilla = getCasilla(coordenada);
		 final int contadorVictoria=1;
		 int contador=0;
		casilla.setVelada(false);
		casilla.setMarcada(true);
		
		if(casilla.isMina()==false) {
	
			contador++;
		
		for (int i = 0; i < 8; i++) {
			Coordenada alrededor = coordenada.creaCoordenadaAlrededor(i);
			if (validaCoordenada(alrededor)) {
				Casilla casillaAlrededor = getCasilla(alrededor);
				if (contarMinasAlrededor(alrededor) == 0) {
				if (casillaAlrededor.isVelada() && casillaAlrededor.isMarcada() == false
					&& casillaAlrededor.isMina() == false) {
					contador++;
					casillaAlrededor.setVelada(false);
					casillaAlrededor.setMarcada(true);
			
					
					desvelarCasilla2(alrededor);
				
				}

			}
		}
		}
	
		}else {
			perderPartida();
		
			
		}

	}

public static void comprobarVictoria() {
	
	int contador=0;
	int total=(Tablero.lado * Tablero.lado) - (Tablero.numeroBombas);
	for(int i = 0; i < Tablero.lado; i++) {
		for(int j = 0; j < Tablero.lado; j++) {
			Casilla casilla=Tablero.getCasilla(new Coordenada(i,j));
			
			if(!casilla.isVelada()) {
				contador++;
			}
		}
	}
	if(contador==total){
		gano();
	}

	}
private static void gano() {
		JOptionPane.showMessageDialog(null, "has ganado");
	}
	

	private static Tablero perderPartida() {
		JOptionPane.showMessageDialog(null, "has perdido");
		
		Tablero.getTablero(lado, numeroBombas);
		 Tablero.colocarMinas(lado, numeroBombas);
		 return tablero;
		
	}
	public void reiniciarPartida() {
		
	}

	public boolean marcarCasilla(Coordenada coordenada) {
		Casilla casilla = Tablero.getCasilla(coordenada);
		return casilla.marcar();

	}

	private static int getLado() {
		return casillas.length;
	}

	public void crearTablero(int lado) {
		Tablero.casillas = new Casilla[lado][lado];
		for (int i = 0; i < casillas.length; i++) {
			for (int j = 0; j < casillas[i].length; j++) {
				casillas[i][j] = new Casilla(); // creando cada casilla del tablero
			}
		}
	}

	public static Casilla[][] getCasillas() {
		return casillas;
	}

	public static Casilla getCasilla(Coordenada posicion) { // me devuleve la casilla al introducir una coordenada
		Casilla casilla = casillas[posicion.getPosX()][posicion.getPosY()];

		return casilla;

	}

	private static void setMina(Coordenada posicion, boolean bandera) { // cambiamos la propiedad Mina de la casilla
		getCasilla(posicion).setMina(bandera);
	}

	private static boolean isMina(Coordenada posicion) { // preguntamos si la casilla con dicha coordenada es mina??
		return getCasilla(posicion).isMina();
	}

	private boolean isMarcada(Coordenada posicion) {// preguntamos si la casilla con dicha coordenada est� Marcada??
		return getCasilla(posicion).isMarcada();
	}

	private boolean isVelada(Coordenada posicion) {// preguntamos si la casilla con dicha cooordenada est� veleda??
		return getCasilla(posicion).isVelada();
	}

	public int getMinasAlrededor(Coordenada posicion) {// me dice cuantas minas alrdedor tiene la casilla con dicha
														// coordenada
		return getCasilla(posicion).getMinasAlrededor();
	}

	private void setVelada(Coordenada posicion, boolean b) { // cambio la propiedad Velada de la casilla
		getCasilla(posicion).setVelada(b);

	}

}
