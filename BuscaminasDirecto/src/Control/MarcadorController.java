package Control;

import model.Casilla;
import model.Coordenada;
import model.Tablero;
import vista.Botonera;

public class MarcadorController {

	
	static Tablero tablero;
	
	//marca y desmarca casilla
	public void  comprobarCasillaSospechosa(String nombre) {
		Coordenada coordenada=Botonera.obtenCoordenada(nombre);
		 Tablero.comprobarCasillaSospechosa(coordenada);
	}
	
	
	public Casilla getCasilla(String nombre) {
		Coordenada coordenada=Botonera.obtenCoordenada(nombre);
		return Tablero.getCasilla(coordenada);
	}
	
	public Coordenada[] getTodasCoordenadasMarcadas(Coordenada coordenada) {
		return Tablero.getTodasCoordenadasMarcadas(coordenada);
	}


	public boolean marcar(String nombre) {
		Coordenada coordenada=Botonera.obtenCoordenada(nombre);
		return Tablero.marcarCasilla(coordenada);
		
	}


	public Casilla[] getTodasCasillasAlrededor(String nombre) {
		// TODO Auto-generated method stub
		Coordenada coordenada=Botonera.obtenCoordenada(nombre);
		return Tablero.getTodasCoordenadasAlrededor(coordenada);
	}


	public int getMinasAlrededor(String nombre) {
		// TODO Auto-generated method stub
		Coordenada coordenada=Botonera.obtenCoordenada(nombre);
		return Tablero.getMinasAlrededor(coordenada);
	}


	public boolean isVisiblesTodasCasillasAlrededor(Casilla[] casillas) {
		// TODO Auto-generated method stub
		return Tablero.isVisibleTodasCasillasAlrededor(casillas);
	}


	public Casilla[] getTodasCasillasMarcadasAlrededor(Casilla casillaMaestra) {
		// TODO Auto-generated method stub
		return Tablero.getTodasCasillasMarcadasAlrededor(casillaMaestra);
	}
}

