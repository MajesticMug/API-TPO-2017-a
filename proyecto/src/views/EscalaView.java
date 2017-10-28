package views;

public class EscalaView {
	private String novedades;
	private float sueldoBase;
	private float sueldoHasta;

	public EscalaView(float sueldoBase, float sueldoHasta, String novedades) {
		super();
		this.sueldoBase = sueldoBase;
		this.sueldoHasta = sueldoHasta;
		this.novedades = novedades;
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

}
