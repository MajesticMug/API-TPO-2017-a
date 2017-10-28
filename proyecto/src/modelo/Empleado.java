package modelo;

import java.sql.SQLException;
import java.util.ArrayList;

import DAO.ReciboSueldoDAO;
import exceptions.ConexionException;
import exceptions.ReciboException;
import views.EmpleadoView;
import views.ReciboView;

public abstract class Empleado {

	private String apellido;

	private String dni;

	private String domicilio;

	private EscalaSalarial escalaSalarial;

	private String estado;

	private String nombre;

	private ArrayList<ReciboSueldo> recibos;

	private String telefono;

	public Empleado(String dni, String nombre, String apellido, String domicilio, String telefono, String estado) {
		super();
		this.dni = dni;
		this.nombre = nombre;
		this.apellido = apellido;
		this.domicilio = domicilio;
		this.telefono = telefono;
		this.estado = estado;
		recibos = new ArrayList<ReciboSueldo>();
	}

	public abstract float calcularSueldo();

	@Override
	public boolean equals(Object arg0) {
		return this.isEmpleado(((Empleado) arg0).getDni());
	}

	public boolean estasEnEstado(String estado) {
		return this.estado.equalsIgnoreCase(estado);
	}

	public void generarRecibo() {
		ReciboSueldo recibo = new ReciboSueldo(calcularSueldo());

		recibos.add(recibo);
		// recibo.setEmpleado(this);
		// ReciboSueldoDAO.getInstancia().save(recibo);

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
	public EscalaSalarial getEscalaSalarial() {
		return escalaSalarial;
	}
	public String getEstado() {
		return estado;
	}
	public String getNombre() {
		return nombre;
	}
	public ArrayList<ReciboSueldo> getRecibos() {
		return recibos;
	}
	public ReciboView getReciboView(int mes, int anio) throws ReciboException {
		for (ReciboSueldo recibo : recibos) {
			if (recibo.pagadoEn(mes, anio))
				return recibo.getView();
		}
		throw new ReciboException("No se registra ningún recibo para " + nombre + " " + apellido + " en el mes " + mes
				+ " del año " + anio);
	}

	public String getTelefono() {
		return telefono;
	}

	public float getUltimoSueldo() {
		if (recibos.isEmpty())
			return -1;
		ReciboSueldo r = recibos.get(recibos.size() - 1);
		if (r == null)
			return -1;
		else
			return r.getMonto();
	}

	public EmpleadoView getView() {
		return new EmpleadoView(dni, nombre, apellido, domicilio, telefono);
	}

	public boolean isEmpleado(String dni) {

		return this.dni.equalsIgnoreCase(dni);
	}

	public void modificar(String nombre, String apellido, String domicilio, String telefono) {
		this.nombre = nombre;
		this.apellido = apellido;
		this.domicilio = domicilio;
		this.telefono = telefono;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public void setDni(String dni) {
		this.dni = dni;
	}

	public void setDomicilio(String domicilio) {
		this.domicilio = domicilio;
	}

	public void setEscalaSalarial(EscalaSalarial escalaSalarial) {
		this.escalaSalarial = escalaSalarial;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public void setRecibos(ArrayList<ReciboSueldo> recibos) {
		this.recibos = recibos;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	@Override
	public String toString() {
		return nombre + " " + apellido;
	}

}
