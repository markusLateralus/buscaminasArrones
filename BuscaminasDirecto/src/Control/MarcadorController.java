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


	public boolean marcar(Casilla casilla) {
	//	Coordenada coordenada=Botonera.obtenCoordenada(nombre);
		return Tablero.marcarCasilla(casilla);
		
	}


	public Casilla[] getTodasCasillasAlrededor(String nombre) {
		// TODO Auto-generated method stub
		Coordenada coordenada=Botonera.obtenCoordenada(nombre);
		return Tablero.getTodasCoordenadasAlrededor(coordenada);
	}


	public int getMinasAlrededor(Casilla casillaMaestra) {
		// TODO Auto-generated method stub
		//Coordenada coordenada=Botonera.obtenCoordenada(casillaMaestra);
		return Tablero.getMinasAlrededor(casillaMaestra);
	}


	public boolean isVisiblesTodasCasillasAlrededor(Casilla[] casillas) {
		// TODO Auto-generated method stub
		return Tablero.isVisibleTodasCasillasAlrededor(casillas);
	}


	public Casilla[] getTodasCasillasMarcadasAlrededor(Casilla[] casillas) {
		// TODO Auto-generated method stub
		return Tablero.getTodasCasillasMarcadasAlrededor(casillas);
	}
}

