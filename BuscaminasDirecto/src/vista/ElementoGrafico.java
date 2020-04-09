package vista;

public class ElementoGrafico {
	private boolean ocultado;
	private boolean senalado;
	public boolean bomba;
	private int valor;
	
	public ElementoGrafico(boolean mostrada, boolean senalada, boolean bomba, int valor) {
		super();
		this.ocultado = mostrada;
		this.senalado = senalada;
		this.bomba=bomba;
		this.valor = valor;
	}
	public boolean isOcultado() {
		return ocultado;
	}
	public boolean isSenalado() {
		return senalado;
	}
	public void setSenalado(boolean senalado) {
		this.senalado = senalado;
	}
	public void setOcultado(boolean ocultado) {
		this.ocultado = ocultado;
	}
	public void setBomba(boolean bomba) {
		this.bomba = bomba;
	}

	public int getValor() {
		return valor;
	}
	public boolean isBomba() {
		// TODO Auto-generated method stub
		return bomba;
	}
}
