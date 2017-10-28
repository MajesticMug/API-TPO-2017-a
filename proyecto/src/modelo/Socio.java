package modelo;

import java.time.LocalDate;
import java.util.ArrayList;

import exceptions.AptoException;
import exceptions.SocioYaInscriptoException;
import views.AptoView;
import views.SocioView;

public class Socio {

	private String apellido;
	private ArrayList<AptoMedico> aptos;
	private String dni;
	private String domicilio;
	private String estado;
	private ArrayList<Ingreso> ingresos;
	private ArrayList<InscripcionNormal> inscripciones;
	private String mail;
	private String nombre;
	private String telefono;

	public Socio(String dni, String nombre, String apellido, String domicilio, String telefono, String mail,
			String estado) {
		super();
		this.dni = dni;
		this.nombre = nombre;
		this.apellido = apellido;
		this.domicilio = domicilio;
		this.telefono = telefono;
		this.mail = mail;
		this.estado = estado;
		ingresos = new ArrayList<Ingreso>();
		aptos = new ArrayList<AptoMedico>();
		inscripciones = new ArrayList<InscripcionNormal>();

	}

	private boolean validarApto() {
		for (AptoMedico apto : aptos) {
			if (apto.validarVigencia())
				return true;
		}
		return false;
	}

	private boolean validarInscripcion() {
		for (Inscripcion inscripcion : inscripciones) {
			if (inscripcion.validarIngresoAhora())
				return true;
		}
		return false;
	}

	public void addApto(LocalDate fechaCertif, String nombreProfesional, String observaciones, String estado) {
		AptoMedico apto = new AptoMedico(fechaCertif, nombreProfesional, observaciones, estado);
		aptos.add(apto);
	}

	public void addInscripcion(InscripcionNormal inscripcion) {
		inscripciones.add(inscripcion);
	}

	public AptoMedico buscarApto(int cod) throws AptoException {
		for (AptoMedico aptoMedico : aptos) {
			if (aptoMedico.isApto(cod))
				return aptoMedico;
		}
		throw new AptoException ("No existe apto médico con el código "+cod+" para el socio "+nombre+" "+apellido);
	}

	public AptoMedico buscarAptoMedico(int codAptoMedico) {
		for (AptoMedico apto : aptos) {
			if (apto.isApto(codAptoMedico))
				return apto;
		}
		return null;

	}

	@Override
	public boolean equals(Object arg0) {
		return this.isSocio(((Socio) arg0).getDni());
	}

	public boolean estasEnElEstado(String estado) {
		return this.estado.equalsIgnoreCase(estado);

	}

	public String getApellido() {
		return apellido;
	}

	public ArrayList<AptoMedico> getAptos() {
		return aptos;
	}

	public AptoView getAptoView(int cod) throws AptoException {
		return buscarApto(cod).getView();
	}

	public String getDni() {
		return dni;
	}

	public String getDomicilio() {
		return domicilio;
	}

	public String getEstado() {
		return estado;
	}

	public ArrayList<Ingreso> getIngresos() {
		return ingresos;
	}

	public ArrayList<InscripcionNormal> getInscripciones() {
		return inscripciones;
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

	public SocioView getView() {
		return new SocioView(dni, nombre, apellido, domicilio, telefono, mail);
	}

	public boolean isInscripto(Abono abono) throws SocioYaInscriptoException {
		for (Inscripcion insc : inscripciones) {
			if (insc.getAbono() == abono)
				throw new SocioYaInscriptoException(
						"El socio " + nombre + " " + apellido + "ya está inscripto para el abono ingresado");
		}
		return false;
	}

	public boolean isSocio(String dni) {
		return this.dni.equalsIgnoreCase(dni);
	}

	public void modificar(String nombre, String apellido, String domicilio, String telefono, String mail) {
		this.nombre = nombre;
		this.apellido = apellido;
		this.domicilio = domicilio;
		this.telefono = telefono;
		this.mail = mail;
	}

	public void modificarApto(int codApto, LocalDate fechaCertificado, String profesionalFirmante,
			String observaciones) throws AptoException {
		AptoMedico a = buscarApto(codApto);
		if (a != null)
			a.modificar(fechaCertificado, profesionalFirmante, observaciones);

	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public void setAptos(ArrayList<AptoMedico> aptos) {
		this.aptos = aptos;
	}

	public void setDni(String dni) {
		this.dni = dni;
	}

	public void setDomicilio(String domicilio) {
		this.domicilio = domicilio;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public void setIngresos(ArrayList<Ingreso> ingresos) {
		this.ingresos = ingresos;
	}

	public void setInscripciones(ArrayList<InscripcionNormal> inscripciones) {
		this.inscripciones = inscripciones;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public boolean tenesAptoEnEstado(String estado) {
		for (AptoMedico aptoMedico : aptos) {
			if (aptoMedico.estasEnEstado(estado))
				return true;
		}
		return false;
	}

	@Override
	public String toString() {
		return "Socio [dni=" + dni + ", nombre=" + nombre + "]";
	}

	public boolean validarIngreso() {
		boolean ingreso = validarApto() && validarInscripcion();
		Ingreso i = new Ingreso(LocalDate.now(), ingreso);
		ingresos.add(i);
		return ingreso;

	}

}
