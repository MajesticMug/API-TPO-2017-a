package views;

public class EmpleadoView {

	private String apellido;
	private String dni;
	private String domicilio;
	private String nombre;
	private String telefono;

	public EmpleadoView(String dni, String nombre, String apellido, String domicilio, String telefono) {
		super();
		this.dni = dni;
		this.nombre = nombre;
		this.apellido = apellido;
		this.domicilio = domicilio;
		this.telefono = telefono;
	}

	public String getApellido() {
		return apellido;
	}

	public String getDni() {
		return dni;
	}

	public String getDomicilio() {
		return domicilio;
	}

	public String getNombre() {
		return nombre;
	}

	public String getTelefono() {
		return telefono;
	}

	@Override
	public String toString() {
		return nombre + " " + apellido;
	}

	@Override
	public boolean equals(Object obj) {
		return this.dni.equalsIgnoreCase(((EmpleadoView) obj).getDni());
	}

}
