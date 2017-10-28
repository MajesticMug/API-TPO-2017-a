package views;

public class ActividadView {

	private int codActividad;
	private int codDeporte;
	private String descripcion;

	public ActividadView(int codDeporte, int codActividad, String descripcion) {
		super();
		this.codDeporte = codDeporte;
		this.codActividad = codActividad;
		this.descripcion = descripcion;
	}

	public int getCodActividad() {
		return codActividad;
	}

	public int getCodDeporte() {
		return codDeporte;
	}

	public String getDescripcion() {
		return descripcion;
	}

	@Override
	public String toString() {
		return descripcion;
	}

}
