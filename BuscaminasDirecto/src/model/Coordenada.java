package model;

import utiles.Utiles;

public class Coordenada {
	//clases de apoyo
	private int posX,posY;

	public Coordenada(int posX, int posY) {
		super();
		this.posX = posX;
		this.posY = posY;
	}

	public int getPosX() {
		return posX;
	}

	public void setPosX(int posX) {
		this.posX = posX;
	}

	public int getPosY() {
		return posY;
	}

	public void setPosY(int posY) {
		this.posY = posY;
	}
	
	@Override
	public boolean equals(Object obj) {
		boolean retorno=false;
		if(obj!=null) {
			retorno=this==obj;
			if(!retorno && obj instanceof Coordenada) {//si retorno es diferente de false y el objeto es de tipo coordenada
				Coordenada coordenada=(Coordenada)obj;
				retorno=this.getPosX()==coordenada.getPosX()&&this.getPosY()==coordenada.getPosY();
			}
		}
		return retorno;
	}
	public Coordenada creaCoordenadaAlrededor(int j2) {
		int[] posicion = Utiles.damePosicionAlrededor(j2);
		return new Coordenada(this.getPosX() + posicion[0], this.getPosY() + posicion[1]);
	}

}
