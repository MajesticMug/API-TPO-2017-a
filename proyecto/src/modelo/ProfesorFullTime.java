package modelo;

public class ProfesorFullTime extends Profesor {
	private float deducciones;
	private float retenciones;
	private float sueldoBasico;

	public ProfesorFullTime(String dni, String nombre, String apellido, String domicilio, String telefono,
			String estado, float sueldoBasico, float deducciones, float retenciones) {
		super(dni, nombre, apellido, domicilio, telefono, estado);
		this.sueldoBasico = sueldoBasico;
		this.deducciones = deducciones;
		this.retenciones = retenciones;
	}

	public float calcularSueldo() {
		return sueldoBasico - deducciones - retenciones;

	}

	public float getDeducciones() {
		return deducciones;
	}

	public float getRetenciones() {
		return retenciones;
	}

	public float getSueldoBasico() {
		return sueldoBasico;
	}

	public void setDeducciones(float deducciones) {
		this.deducciones = deducciones;
	}

	public void setRetenciones(float retenciones) {
		this.retenciones = retenciones;
	}

	public void setSueldoBasico(float sueldoBasico) {
		this.sueldoBasico = sueldoBasico;
	}
	

}
