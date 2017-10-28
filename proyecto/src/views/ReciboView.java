package views;

import java.time.LocalDate;

public class ReciboView {

	private LocalDate fechaPago;
	private float monto;

	public ReciboView(LocalDate fechaPago, float monto) {
		super();
		this.fechaPago = fechaPago;
		this.monto = monto;
	}

	public LocalDate getFechaPago() {
		return fechaPago;
	}

	public float getMonto() {
		return monto;
	}

}
