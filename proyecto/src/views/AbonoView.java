package views;

import java.time.LocalDate;
import java.util.ArrayList;

import modelo.Actividad;

public class AbonoView {

	private int codAbono;
	private String nombre;
	private int periodoPago;
	private float precio;

	public AbonoView(int codAbono, String nombre, float precio, int periodoPago) {
		super();
		this.codAbono = codAbono;
		this.nombre = nombre;
		this.precio = precio;
		this.periodoPago = periodoPago;
	}

	public int getCodAbono() {
		return codAbono;
	}

	public String getNombre() {
		return nombre;
	}

	public int getPeriodoPago() {
		return periodoPago;
	}

	public float getPrecio() {
		return precio;
	}

}
