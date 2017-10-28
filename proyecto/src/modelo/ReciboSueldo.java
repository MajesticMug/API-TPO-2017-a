package modelo;

import java.time.LocalDate;

import views.ReciboView;

public class ReciboSueldo {
	private static int proxCod;
	private int codRecibo;
	private Empleado empleado;
	private LocalDate fechaPago;
	private float monto;

	public ReciboSueldo(float monto) {
		super();
		codRecibo = ++proxCod;
		this.fechaPago = LocalDate.now();
		this.monto = monto;
	}

	public static int getProxCod() {
		return proxCod;
	}

	public static void setProxCod(int proxCod) {
		ReciboSueldo.proxCod = proxCod;
	}

	public int getCodRecibo() {
		return codRecibo;
	}

	public Empleado getEmpleado() {
		return empleado;
	}

	public LocalDate getFechaPago() {
		return fechaPago;
	}

	public float getMonto() {
		return monto;
	}

	public ReciboView getView() {
		return new ReciboView(fechaPago, monto);
	}

	public boolean pagadoEn(int mes, int anio) {
		return fechaPago.getMonthValue() == mes && fechaPago.getYear() == anio;
	}

	public void setCodRecibo(int codRecibo) {
		this.codRecibo = codRecibo;
	}

	public void setEmpleado(Empleado empleado) {
		this.empleado = empleado;
	}

	public void setFechaPago(LocalDate fechaPago) {
		this.fechaPago = fechaPago;
	}

	public void setMonto(float monto) {
		this.monto = monto;
	}

}
