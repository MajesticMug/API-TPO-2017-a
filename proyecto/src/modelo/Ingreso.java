package modelo;

import java.time.LocalDate;



public class Ingreso {
	private boolean aceptado;
	private LocalDate fechaIngreso;
	public Ingreso(LocalDate fechaIngreso, boolean aceptado) {
		super();
		this.fechaIngreso = fechaIngreso;
		this.aceptado = aceptado;
	}
	
	
	
}
