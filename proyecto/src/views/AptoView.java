package views;

import java.time.LocalDate;
import java.util.ArrayList;

import modelo.Actividad;

public class AptoView {

	private LocalDate fechaCertificado;
	private String observaciones;
	private String profesionalFirmante;

	public AptoView(LocalDate fechaCertificado, String profesionalFirmante, String observaciones) {
		super();
		this.fechaCertificado = fechaCertificado;
		this.profesionalFirmante = profesionalFirmante;
		this.observaciones = observaciones;
	}

	public LocalDate getFechaCertificado() {
		return fechaCertificado;
	}

	public String getObservaciones() {
		return observaciones;
	}

	public String getProfesionalFirmante() {
		return profesionalFirmante;
	}

}
