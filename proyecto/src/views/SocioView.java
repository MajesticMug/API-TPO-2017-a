package views;

public class SocioView {

	private String apellido;
	private String dni;
	private String domicilio;
	private String mail;
	private String nombre;
	private String telefono;
	
	public SocioView(String dni, String nombre, String apellido, String domicilio, String telefono, String mail) {
		super();
		this.dni = dni;
		this.nombre = nombre;
		this.apellido = apellido;
		this.domicilio = domicilio;
		this.telefono = telefono;
		this.mail = mail;
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

	public String getMail() {
		return mail;
	}

	public String getNombre() {
		return nombre;
	}

	public String getTelefono() {
		return telefono;
	}	
	
	
	
}
