package utiles;
public class Utiles {

	public static int dameNumero(int limite) {
		assert limite > 0;
		return (int) (Math.random() * (limite));//los resultados son entre 0(incluido) y limite-1
	}

	public static int calculaMinas(int lado, int porcentaje) {
		assert lado>0 && porcentaje>=0 && porcentaje<=100;
		return lado * lado * porcentaje / 100;
	}
	public static int[] damePosicionAlrededor(int lugar) {
		int[][] posicion = { { -1, -1 }, { -1, 0 }, { -1, +1 }, { 0, -1 }, { 0, +1 }, { +1, -1 }, { +1, 0 },
				{ +1, +1 } };
		return posicion[lugar];
	}
}
