package modelo;

import java.util.ArrayList;

import views.EscalaView;

public class EscalaSalarial {
	private static int proxCod;
	private int codEscalaSalarial;
	private ArrayList<Empleado> empleados;
	private String novedades;
	private float sueldoBase;
	private float sueldoHasta;

	public EscalaSalarial(float sueldoBase, float sueldoHasta, String novedades) {
		super();
		this.sueldoBase = sueldoBase;
		this.sueldoHasta = sueldoHasta;
		this.novedades = novedades;
		codEscalaSalarial = ++proxCod;
	}

	public static int getProxCod() {
		return proxCod;
	}

	public static void setProxCod(int proxCod) {
		EscalaSalarial.proxCod = proxCod;
	}

	public boolean aplica(float ultSueldo) {
		return sueldoBase < ultSueldo && ultSueldo < sueldoHasta;
	}

	public int getCodEscalaSalarial() {
		return codEscalaSalarial;
	}

	public ArrayList<Empleado> getEmpleados() {
		return empleados;
	}

	public String getNovedades() {
		return novedades;
	}

	public float getSueldoBase() {
		return sueldoBase;
	}

	public float getSueldoHasta() {
		return sueldoHasta;
	}

	public EscalaView getView() {

		return new EscalaView(sueldoBase, sueldoHasta, novedades);
	}

	public boolean isEscala(int codEscalaSalarial) {
		return this.codEscalaSalarial == codEscalaSalarial;
	}

	public void modificar(float sueldoBase, float sueldoHasta, String novedades) {
		this.sueldoBase = sueldoBase;
		this.sueldoHasta = sueldoHasta;
		this.novedades = novedades;
	}

	public void setCodEscalaSalarial(int codEscalaSalarial) {
		this.codEscalaSalarial = codEscalaSalarial;
	}

	public void setEmpleados(ArrayList<Empleado> empleados) {
		this.empleados = empleados;
	}

	public void setNovedades(String novedades) {
		this.novedades = novedades;
	}

	public void setSueldoBase(float sueldoBase) {
		this.sueldoBase = sueldoBase;
	}

	public void setSueldoHasta(float sueldoHasta) {
		this.sueldoHasta = sueldoHasta;
	}
}
