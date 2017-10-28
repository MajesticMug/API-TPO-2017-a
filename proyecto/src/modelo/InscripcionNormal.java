package modelo;

import java.time.LocalDate;
import java.util.ArrayList;

public class InscripcionNormal extends Inscripcion {
	private Socio socio;

	public LocalDate fechaInscripcion;

	public InscripcionNormal(Abono abono, Socio socio) {
		super(abono);
		this.socio = socio;
		fechaInscripcion = LocalDate.now();
	}

	public LocalDate getFechaInscripcion() {
		return fechaInscripcion;
	}
	public Socio getSocio() {
		return socio;
	}

	@Override
	public boolean isInscripto(Abono abono, Socio socio) {
		return abono == getAbono() && socio == this.socio;
	}

	public void setFechaInscripcion(LocalDate fechaInscripcion) {
		this.fechaInscripcion = fechaInscripcion;
	}

	public void setSocio(Socio socio) {
		this.socio = socio;
	}

}