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
	
	public static void getCasilla(ElementoGrafico[][] elementos,ElementoGrafico elementoGrafico ) {
		Casilla casilla;
		for (int i = 0; i < elementos.length; i++) {
			for (int j = 0; j < elementos.length; j++) {
				if(elementos[i][j].equals(elementoGrafico)) {
				//	0Casilla casilla=elementoGrafico.getValor();
					System.out.println(elementoGrafico.getValor());
					//return casilla;
				}
			}
			
			}
		
	}

}
