package model;

import javax.swing.JOptionPane;

import utiles.Utiles;

public class Tablero {

	private Casilla[][] casillas;
	private int lado;

	public Tablero(int lado, int numeroBombas) {
		super();
		this.lado = lado;
		crearTablero(lado);
		colocarMinas(lado, numeroBombas);
	}

	private void establecerMinasAlrededor(Coordenada posicionMinaCoordenada) {
		for (int i = 0; i < 8; i++) {
			Coordenada alrededor = posicionMinaCoordenada.creaCoordenadaAlrededor(i);
			if (validaCoordenada(alrededor)) {
				setMinasAlrededor(alrededor);
				// System.out.println("bombas alrededor " + alrededor.getPosX() +
				// alrededor.getPosY());
			}
		}
	}

	private boolean validaCoordenada(Coordenada posicion) {
		return posicion.getPosX() >= 0 && posicion.getPosY() >= 0 && posicion.getPosX() < getLado()
				&& posicion.getPosY() < getLado();
	}

	private void setMinasAlrededor(Coordenada coordenada) {
		casillas[coordenada.getPosX()][coordenada.getPosY()]
				.setMinasAlrededor(casillas[coordenada.getPosX()][coordenada.getPosY()].getMinasAlrededor() + 1);
	}

	private void colocarMinas(int lado, int numeroMinas) { // aleatorio
		// TODO ?
		for (int i = 0; i < numeroMinas;) {
			Coordenada posicion = new Coordenada(Utiles.dameNumero(getLado()), Utiles.dameNumero(getLado()));
			if (!isMina(posicion)) {
				setMina(posicion, true);
				i++;
				establecerMinasAlrededor(posicion);
				System.out.println("bomba:" + i + " " + posicion.getPosX() + posicion.getPosY());

			}
		}

	}

	private int contarMinasAlrededor(Coordenada posicion) {
		int bombasAlrededor = 0;
		int x = posicion.getPosX();
		int y = posicion.getPosY();
		for (int i = x - 1; i < x + 1; i++) { // (x-1,y-1)esquina izquierda superior,
			for (int j = y - 1; j < y + 1; j++) { // (x+1,y+1) esquina inferior derecha
				Coordenada alrededor = new Coordenada(i, j);
				// No tengo en cuenta la posicion que estoy comprobando
				if (!alrededor.equals(posicion)) {
					// está la posicion dentro del tablero?? la posicion es mina??
					if (isDentroLimites(alrededor, lado) && this.getCasilla(alrededor).isMina()) {
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

	public void desvelarCasilla2(Coordenada coordenada) {//solucionado
		Casilla casilla = this.getCasilla(coordenada);
		casilla.setVelada(false);
		casilla.setMarcada(true);
		if(casilla.isMina()==false) {
		for (int i = 0; i < 8; i++) {
			Coordenada alrededor = coordenada.creaCoordenadaAlrededor(i);
			if (validaCoordenada(alrededor)) {
				Casilla casillaAlrededor = getCasilla(alrededor);
				if (contarMinasAlrededor(alrededor) == 0) {
				if (casillaAlrededor.isVelada() && casillaAlrededor.isMarcada() == false
					&& casillaAlrededor.isMina() == false) {
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

	private void perderPartida() {
		JOptionPane.showMessageDialog(null, "has perdido");
		
	/*	Casilla[][] casillas=getCasillas();
		for (int i = 0; i < casillas.length; i++) {
			for (int j = 0; j < casillas[i].length; j++) {
				Casilla casilla=casillas[i][j];
				casilla.setMarcada(false);
				casilla.setVelada(true);
				
			}
		}*/
	
		//colocarMinas(lado, numeroBombas);
		
	}
	public void reiniciarPartida() {
		
	}

	public boolean marcarCasilla(Coordenada coordenada) {
		Casilla casilla = this.getCasilla(coordenada);
		return casilla.marcar();

	}

	private int getLado() {
		return casillas.length;
	}

	public void crearTablero(int lado) {
		this.casillas = new Casilla[lado][lado];
		for (int i = 0; i < casillas.length; i++) {
			for (int j = 0; j < casillas[i].length; j++) {
				casillas[i][j] = new Casilla(); // creando cada casilla del tablero
			}
		}
	}

	public Casilla[][] getCasillas() {
		return casillas;
	}

	public Casilla getCasilla(Coordenada posicion) { // me devuleve la casilla al introducir una coordenada
		Casilla casilla = casillas[posicion.getPosX()][posicion.getPosY()];

		return casilla;

	}

	private void setMina(Coordenada posicion, boolean bandera) { // cambiamos la propiedad Mina de la casilla
		getCasilla(posicion).setMina(bandera);
	}

	private boolean isMina(Coordenada posicion) { // preguntamos si la casilla con dicha coordenada es mina??
		return getCasilla(posicion).isMina();
	}

	private boolean isMarcada(Coordenada posicion) {// preguntamos si la casilla con dicha coordenada está Marcada??
		return getCasilla(posicion).isMarcada();
	}

	private boolean isVelada(Coordenada posicion) {// preguntamos si la casilla con dicha cooordenada está veleda??
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
