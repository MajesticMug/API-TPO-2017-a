package modelo;

import java.util.ArrayList;

import views.EmpresaView;

public class Empresa {

	private String cuit;
	private ArrayList<Socio> empleados;
	private String estado;
	private String razonSocial;

	public Empresa(String cuit, String razonSocial, String estado) {
		super();
		this.cuit = cuit;
		this.razonSocial = razonSocial;
		empleados = new ArrayList<Socio>();
		this.estado = estado;
	}

	public void addEmpleado(Socio s) {
		if (!empleados.contains(s))
			empleados.add(s);
	}

	@Override
	public boolean equals(Object obj) {
		return this.sosLaEmpresa(((Empresa) obj).getCuit());
	}

	public boolean estasEnEstado(String estado) {
		return this.estado.equalsIgnoreCase(estado);
	}

	public String getCuit() {
		return cuit;
	}

	public ArrayList<Socio> getEmpleados() {
		return empleados;
	}

	public String getEstado() {
		return estado;
	}

	public String getRazonSocial() {
		return razonSocial;
	}

	public EmpresaView getView() {
		return new EmpresaView(razonSocial);
	}

	public void modificar(String razonSocial) {
		this.razonSocial = razonSocial;

	}

	public void setCuit(String cuit) {
		this.cuit = cuit;
	}

	public void setEmpleados(ArrayList<Socio> empleados) {
		this.empleados = empleados;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public void setRazonSocial(String razonSocial) {
		this.razonSocial = razonSocial;
	}

	public boolean sosLaEmpresa(String cuit) {
		return this.cuit.equalsIgnoreCase(cuit);
	}

	public boolean trabajaElSocio(Socio socio) {
		return empleados.contains(socio);
	}

}
