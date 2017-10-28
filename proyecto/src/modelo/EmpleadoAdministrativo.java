package modelo;

public class EmpleadoAdministrativo extends Empleado {
	private float deducciones;
	private String puesto;
	private float retenciones;
	private float sueldoBasico;

	public EmpleadoAdministrativo(String dni, String nombre, String apellido, String domicilio, String telefono,
			String estado, String puesto, float sueldoBasico, float deducciones, float retenciones) {
		super(dni, nombre, apellido, domicilio, telefono, estado);
		this.puesto = puesto;
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

	public String getPuesto() {
		return puesto;
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

	public void setPuesto(String puesto) {
		this.puesto = puesto;
	}

	public void setRetenciones(float retenciones) {
		this.retenciones = retenciones;
	}

	public void setSueldoBasico(float sueldoBasico) {
		this.sueldoBasico = sueldoBasico;
	}

}
