package utiles;

import model.Casilla;
import vista.ElementoGrafico;

public class ConversorGrafico {
	public static ElementoGrafico[][] convertir(Casilla[][] casilla) {
		ElementoGrafico elementos[][] = new ElementoGrafico[casilla.length][casilla.length];
		for (int i = 0; i < casilla.length; i++) {
			for (int j = 0; j < casilla.length; j++) {
				int valor = casilla[i][j].getMinasAlrededor();
				elementos[i][j] = new ElementoGrafico(casilla[i][j].isVelada(), casilla[i][j].isMarcada(), casilla[i][j].isMina(), valor);
			}
		}
		return elementos;
	}
	
	public static ElementoGrafico getElementoGrafico(Casilla casilla) {
		int valor=casilla.getMinasAlrededor();
		ElementoGrafico elemento= new ElementoGrafico(casilla.isVelada(),casilla.isMarcada(), casilla.isMina(),valor);
					return elemento;
				
		
	}
	
	
	
	public static Casilla [][] convertirAcasillas(	ElementoGrafico elementos[][]) {
		Casilla casillas[][] = new Casilla[elementos.length][elementos.length];
		for (int i = 0; i < elementos.length; i++) {
			for (int j = 0; j < elementos.length; j++) {
			//	int valor = elementos[i][j].getMinasAlrededor();
				casillas[i][j] = new Casilla(elementos[i][j].isOcultado(), elementos[i][j].isSenalado(), elementos[i][j].isBomba());
			}
		}
		return casillas;
	}
	
	
	
	

}
