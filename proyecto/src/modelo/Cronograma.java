package modelo;

import java.time.LocalDate;
import java.util.ArrayList;

import views.ClaseView;

public class Cronograma {
	private ArrayList<ClaseView> clases;
	private LocalDate fechaDesde;
	private LocalDate fechaHasta;
	private Matriz tabla;

	public Cronograma(LocalDate fechaDesde, LocalDate fechaHasta) {
		super();
		this.fechaDesde = fechaDesde;
		this.fechaHasta = fechaHasta;
		clases = new ArrayList<ClaseView>();
	}

	public void addClases(ArrayList<ClaseView> clases) {

		this.clases.addAll(clases);
	}

	public String[][] generar() {

		tabla = new Matriz();
		for (ClaseView cl : clases) {
			int c = cl.diaANumbero();
			int hInicio = cl.getHorarioInicio().getHours();
			int hFin = cl.getHorarioFin().getHours();
			if (cl.getHorarioFin().getMinutes() > 0)
				hFin++;
			while (hInicio < hFin) {
				tabla.set(hInicio + 1, c, tabla.elementAt(hInicio, c) + cl.getActividad());
				hInicio++;
			}
		}
		return tabla.generarTabla();
	}

	public void setClases(ArrayList<ClaseView> clases) {
		this.clases = clases;
	}
}