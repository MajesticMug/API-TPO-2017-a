package views;

public class DeporteView {

	private int id;

	private String descripcion;
	private String titulo;

	public DeporteView(int id, String titulo, String descripcion) {
		super();
		this.id = id;
		this.titulo = titulo;
		this.descripcion = descripcion;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public String getTitulo() {
		return titulo;
	}

	public int getId() {
		return id;
	}

	@Override
	public String toString() {
		return titulo;
	}

}
