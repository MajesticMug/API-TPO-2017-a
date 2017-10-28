package modelo;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;

import views.AbonoView;

public class Abono {

	private static int proxCod;

	private ArrayList<Actividad> actividades;

	private int codAbono;

	private String estado;

	private LocalDate inicioVigencia;

	private String nombre;

	private int periodoPago;

	private float precio;

	public Abono(String nombre, float precio, LocalDate inicioVigencia, int periodoPago,
			ArrayList<Actividad> actividades, String estado) {
		super();
		this.nombre = nombre;
		this.precio = precio;
		this.inicioVigencia = inicioVigencia;
		this.periodoPago = periodoPago;
		this.actividades = actividades;
		this.estado = estado;
		codAbono = ++proxCod;
	}

	@Override
	public boolean equals(Object arg0) {

		return this.isAbono(((Abono) arg0).getCodAbono());
	}

	public boolean estasEnEstado(String estado) {
		return this.estado.equalsIgnoreCase(estado);
	}

	public ArrayList<Actividad> getActividades() {
		return actividades;
	}

	public int getCodAbono() {
		return codAbono;
	}

	public String getEstado() {
		return estado;
	}

	public LocalDate getInicioVigencia() {
		return inicioVigencia;
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

	public AbonoView getView() {
		return new AbonoView(codAbono, nombre, precio, periodoPago);
	}

	public boolean isAbono(int codAbono) {
		return this.codAbono == codAbono;
	}

	public void modificar(String nombre, float precio, int periodoPago) {
		this.nombre = nombre;
		this.precio = precio;
		this.periodoPago = periodoPago;

	}

	public void setActividades(ArrayList<Actividad> actividades) {
		this.actividades = actividades;
	}

	public void setCodAbono(int codAbono) {
		this.codAbono = codAbono;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public void setInicioVigencia(LocalDate inicioVigencia) {
		this.inicioVigencia = inicioVigencia;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public void setPeriodoPago(int periodoPago) {
		this.periodoPago = periodoPago;
	}

	public void setPrecio(float precio) {
		this.precio = precio;
	}

	public boolean validarIngresoAhora() {
		for (Actividad actividad : actividades) {
			return actividad.validarIngresoAhora();

		}
		return false;

	}
}
