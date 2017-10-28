package modelo;

import java.sql.Time;
import java.time.LocalDate;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import exceptions.ActividadException;
import exceptions.ClaseException;
import views.ActividadView;
import views.ClaseView;
import views.DeporteView;
import views.EmpleadoView;

public class Deporte {
	private static int proxCod;

	private ArrayList<Actividad> actividades;
	private int codDeporte;
	private String descripcion;
	private String estado;
	private String titulo;

	public Deporte(String titulo, String descripcion, String estado) {
		super();
		this.titulo = titulo;
		this.descripcion = descripcion;
		this.estado = estado;
		codDeporte = ++proxCod;
		actividades = new ArrayList<Actividad>();
	}

	public static int getProxCod() {
		return proxCod;
	}

	public static void setProxCod(int proxCod) {
		Deporte.proxCod = proxCod;
	}

	public void altaActividad(String descripcion, String estado) {
		Actividad actividad = new Actividad(descripcion, estado, this);
		actividades.add(actividad);
	}

	public void altaClase(int codActividad, Time horaInicio, Time horaFin, String dia, ArrayList<Profesor> profesores,
			String estado) throws ActividadException {

		Actividad a = buscarActividad(codActividad);
		a.altaClase(horaInicio, horaFin, dia, profesores, estado);

	}

	public void bajaActividad(int codActividad, String estado) throws ActividadException {

		Actividad a = buscarActividad(codActividad);
		a.setEstado(estado);

	}

	public void bajaClase(int codActividad, int codClase, String estado) throws ActividadException, ClaseException {

		Actividad a = buscarActividad(codActividad);
		a.bajaClase(codClase, estado);

	}

	public Actividad buscarActividad(int codActividad) throws ActividadException {
		for (Actividad actividad : actividades) {
			if (actividad.isActividad(codActividad) && !actividad.estasEnEstado("Inactivo"))
				return actividad;
		}
		throw new ActividadException(
				"No existe la actividad con el código " + codActividad + " para el deporte " + titulo);

	}

	public boolean estasEnEstado(String estado) {
		return this.estado.equalsIgnoreCase(estado);
	}

	public ArrayList<ClaseView> generarCronograma(LocalDate fechaDesde, LocalDate fechaHasta) {
		ArrayList<ClaseView> cronograma = new ArrayList<ClaseView>();
		for (Actividad actividad : actividades) {
			if (!actividad.estasEnEstado("Inactivo"))
				cronograma.addAll(actividad.generarCronograma(fechaDesde, fechaHasta));
		}
		return cronograma;
	}

	public ArrayList<Actividad> getActividades() {
		return actividades;
	}

	public ArrayList<ActividadView> getAllActividadesView() {
		ArrayList<ActividadView> acts = new ArrayList<ActividadView>();
		for (Actividad act : actividades) {
			if (!act.estasEnEstado("Inactivo"))
				acts.add(act.getView());

		}
		return acts;
	}

	public ArrayList<ClaseView> getClasesView(int codActividad) throws ActividadException {
		return buscarActividad(codActividad).getAllClasesView();
	}

	public ClaseView getClaseView(int actividad, int clase) throws ActividadException, ClaseException {

		return buscarActividad(actividad).getClaseView(clase);

	}

	public int getCodDeporte() {
		return codDeporte;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public String getEstado() {
		return estado;
	}

	public String getTitulo() {
		return titulo;
	}

	public DeporteView getView() {
		return new DeporteView(codDeporte, titulo, descripcion);
	}

	public boolean isDeporte(int codDeporte) {
		return this.codDeporte == codDeporte;
	}

	public void modificar(String titulo, String descripcion) {
		this.titulo = titulo;
		this.descripcion = descripcion;
	}

	public void modificarActividad(int codActividad, String descripcion) throws ActividadException {

		Actividad a = buscarActividad(codActividad);
		a.modificar(descripcion);

	}

	public void modificarClase(int codActividad, int codClase, Time horarioInicio, Time horarioFin, String dia,
			ArrayList<Profesor> profesores) throws ActividadException, ClaseException {

		Actividad a = buscarActividad(codActividad);
		a.modificarClase(codClase, horarioInicio, horarioFin, dia, profesores);

	}

	public void setActividades(ArrayList<Actividad> actividades) {
		this.actividades = actividades;
	}

	public void setCodDeporte(int codDeporte) {
		this.codDeporte = codDeporte;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

}
