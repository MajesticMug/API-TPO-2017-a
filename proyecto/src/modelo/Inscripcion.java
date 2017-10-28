package modelo;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Random;

public abstract class Inscripcion {
	private static int proxCod;
	private Abono abono;
	private int codInscripcion;
	private String estado;
	private LocalDate fechaProxVencimiento;
	private LocalDate fechaUltimoPago;

	public Inscripcion(Abono abono) {
		super();
		this.abono = abono;
		codInscripcion = ++proxCod;
	}

	public static int getProxCod() {
		return proxCod;
	}

	public static void setProxCod(int proxCod) {
		Inscripcion.proxCod = proxCod;
	}

	public boolean estasVencida() {
		if (fechaProxVencimiento == null)
			fechaProxVencimiento = LocalDate.now().plusDays(-10 + (int) (Math.random() * 10));
		return fechaProxVencimiento.isBefore(LocalDate.now());

	}

	public Abono getAbono() {
		return abono;
	}

	public int getCodInscripcion() {
		return codInscripcion;
	}

	public String getEstado() {
		return estado;
	}

	public LocalDate getFechaProxVencimiento() {
		return fechaProxVencimiento;
	}

	public LocalDate getFechaUltimoPago() {
		return fechaUltimoPago;
	}

	public boolean hacesElDeporte(Deporte deporte) {
		ArrayList<Actividad> actividades = abono.getActividades();
		for (Actividad actividad : actividades) {
			if (actividad.getDeporte() == deporte)
				return true;
		}
		return false;
	}

	public boolean isInscripcion(int codInscripcion) {

		return this.codInscripcion == codInscripcion;
	}

	public abstract boolean isInscripto(Abono abono, Socio socio);

	public void setAbono(Abono abono) {
		this.abono = abono;
	}

	public void setCodInscripcion(int codInscripcion) {
		this.codInscripcion = codInscripcion;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public void setFechaProxVencimiento(LocalDate fechaProxVencimiento) {
		this.fechaProxVencimiento = fechaProxVencimiento;
	}

	public void setFechaUltimoPago(LocalDate fechaUltimoPago) {
		this.fechaUltimoPago = fechaUltimoPago;
	}

	public boolean validarIngresoAhora() {
		return abono.validarIngresoAhora();

	}

}
