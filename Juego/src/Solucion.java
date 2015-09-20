public class Solucion {

	private int[] puzzle = new int[16];
	private int movimientos = 0;

	public Solucion(int[] puzzle) {
		this.puzzle = puzzle;
	}

	public void getSolucion() {
		int posicionCero = getPosicionCero(this.puzzle);
		while (solucionado() != 0 && movimientos < 100) {
			hacerMovimiento();
			movimientos++;
		}
	}

	private int getPosicionCero(int[] puzzle) {
		for (int i = 0; i < puzzle.length; i++) {
			if (puzzle[i] == 0) {
				return i;
			}
		}
		return 0;
	}

	private int solucionado() {
		int resultado = 0;
		for (int i = 0; i < puzzle.length; i++) {
			resultado += puzzle[i] - 1;
		}
		return resultado;
	}

	private void hacerMovimiento() {

	}

}