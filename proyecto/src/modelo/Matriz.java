package modelo;

import java.sql.Time;

public class Matriz {

	private int columnas;
	private int filas;
	private String[][] matr;

	public Matriz() {
		filas = 25;
		columnas = 8;
		matr = new String[filas][columnas];
		inicializar();
	}

	private boolean columnaVacia(int c) {

		for (int i = 1; i < filas; i++)
			if (!(matr[i][c].equals("")))
				return false;
		return true;
	}

	private boolean filaVacia(int f) {

		for (int i = 1; i < columnas; i++)
			if (!(matr[f][i].equals("")))
				return false;
		return true;
	}

	private void inicializar() {
		set(0, 1, "Lunes");
		set(0, 2, "Martes");
		set(0, 3, "Miercoles");
		set(0, 4, "Jueves");
		set(0, 5, "Viernes");
		set(0, 6, "Sabado");
		set(0, 7, "Domingo");

		Time time = new Time(0, 0, 0);
		for (int f = 1; f < filas; f++) {
			set(f, 0, time.toLocalTime().toString());
			time.setHours(time.getHours() + 1);
		}

		for (int f = 0; f < filas; f++) {
			for (int c = 0; c < columnas; c++) {
				if (elementAt(f, c) == null)
					set(f, c, "");
			}

		}

	}

	public String elementAt(int f, int c) {
		return matr[f][c];
	}

	public String[][] generarTabla() {
		int cantFilas = 0;
		for (int i = 0; i < filas; i++) {
			if (!filaVacia(i))
				cantFilas++;
		}
		String[][] tabla = new String[cantFilas][columnas];
		for (int i = 0, j = 0; i < filas; i++) {
			if (!filaVacia(i)) {
				tabla[j] = matr[i];
				j++;
			}
		}
		return tabla;

	}

	public void set(int f, int c, String valor) {
		matr[f][c] = valor;
	}

	public void toStr() {

		for (int f = 0; f < filas; f++) {
			for (int c = 0; c < columnas; c++)
				System.out.print(elementAt(f, c) + " - ");
			System.out.println();
		}

	}

}
