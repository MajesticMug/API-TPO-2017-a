package modelo;

import java.time.LocalDate;

import views.AptoView;

public class AptoMedico {
	private static int proxCod;
	private int codApto;
	private String estado;
	private LocalDate fechaCertificado;
	private String observaciones;
	private String profesionalFirmante;

	public AptoMedico(LocalDate fechaCertificado, String profesionalFirmante, String observaciones, String estado) {
		super();
		this.fechaCertificado = fechaCertificado;
		this.profesionalFirmante = profesionalFirmante;
		this.observaciones = observaciones;
		this.estado = estado;
		codApto = ++proxCod;
	}

	public boolean estasEnEstado(String estado) {
		if (validarVigencia())
			estado="Activo";
		else
			estado="Inactivo";
		return estado.equalsIgnoreCase(estado);
	}

	public String getEstado() {
		if (validarVigencia())
			estado="Activo";
		else
			estado="Inactivo";
		return estado;
	}

	public AptoView getView() {

		return new AptoView(fechaCertificado, profesionalFirmante, observaciones);
	}

	public boolean isApto(int codAptoMedico) {
		return codApto == codAptoMedico;
	}

	public void modificar(LocalDate fechaCertificado, String profesionalFirmante, String observaciones) {
		this.fechaCertificado = fechaCertificado;
		this.profesionalFirmante = profesionalFirmante;
		this.observaciones = observaciones;

	}

	public boolean validarVigencia() {
		return fechaCertificado.plusMonths(6).isAfter(LocalDate.now());

	}

}
