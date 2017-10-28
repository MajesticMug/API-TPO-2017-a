package modelo;

public abstract class Profesor extends Empleado {

	public Profesor(String dni, String nombre, String apellido, String domicilio, String telefono, String estado) {
		super(dni, nombre, apellido, domicilio, telefono, estado);
		// TODO Auto-generated constructor stub
	}

	public abstract float calcularSueldo();

}
