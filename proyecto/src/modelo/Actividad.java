package modelo;

import java.sql.Time;
import java.time.LocalDate;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import exceptions.ClaseException;
import views.ActividadView;
import views.ClaseView;

public class Actividad {
	private static int proxCod;
	private int codActividad;
	private Deporte deporte;
	private String descripcion;
	private String estado;
	private ArrayList<Clase> horarios;

	public Actividad(String descripcion, String estado, Deporte deporte) {
		super();
		this.descripcion = descripcion;
		this.estado = estado;
		codActividad = ++proxCod;
		horarios = new ArrayList<Clase>();
		this.deporte = deporte;
	}

	public static int getProxCod() {
		return proxCod;
	}

	public static void setProxCod(int proxCod) {
		Actividad.proxCod = proxCod;
	}

	public void altaClase(Time horaInicio, Time horaFin, String dia, ArrayList<Profesor> profesores, String estado) {

		Clase c = new Clase(horaInicio, horaFin, dia, profesores, estado);
		horarios.add(c);
	}

	public void bajaClase(int codClase, String estado) throws ClaseException {

		Clase clase = buscarClase(codClase);
		clase.setEstado(estado);

	}

	public Clase buscarClase(int codClase) throws ClaseException {
		for (Clase clase : horarios) {
			if (clase.isClase(codClase) && !clase.estasEnEstado("Inactivo"))
				return clase;
		}
		throw new ClaseException("No existe la clase con código " + codClase + " para la actividad " + descripcion);

	}

	public boolean estasEnEstado(String estado) {
		return this.estado.equalsIgnoreCase(estado);
	}

	public ArrayList<ClaseView> generarCronograma(LocalDate fechaDesde, LocalDate fechaHasta) {
		ArrayList<ClaseView> cronograma = new ArrayList<ClaseView>();
		for (Clase clase : horarios) {
			if (clase.seHaceEntreLasFechas(fechaDesde, fechaHasta) && !clase.estasEnEstado("Inactivo"))
				cronograma.add(clase.getView(descripcion));
		}
		return cronograma;
	}

	public ArrayList<ClaseView> getAllClasesView() {
		ArrayList<ClaseView> clases = new ArrayList<ClaseView>();
		for (Clase clase : horarios) {
			if (!clase.estasEnEstado("Inactivo"))
				clases.add(clase.getView(descripcion));
		}
		return clases;
	}

	public ClaseView getClaseView(int clase) throws ClaseException {
		return buscarClase(clase).getView(descripcion);
	}

	public int getCodActividad() {
		return codActividad;
	}

	public Deporte getDeporte() {
		return deporte;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public String getEstado() {
		return estado;
	}

	public ArrayList<Clase> getHorarios() {
		return horarios;
	}

	public ActividadView getView() {
		return new ActividadView(deporte.getCodDeporte(), codActividad, descripcion);
	}

	public boolean isActividad(int codActividad) {
		return this.codActividad == codActividad;
	}

	public void modificar(String descripcion) {
		this.descripcion = descripcion;
	}

	public void modificarClase(int codClase, Time horarioInicio, Time horarioFin, String dia,
			ArrayList<Profesor> profesores) throws ClaseException {
		Clase c = buscarClase(codClase);
		c.modificar(horarioInicio, horarioFin, dia, profesores);
	}

	public void setCodActividad(int codActividad) {
		this.codActividad = codActividad;
	}

	public void setDeporte(Deporte deporte) {
		this.deporte = deporte;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public void setHorarios(ArrayList<Clase> horarios) {
		this.horarios = horarios;
	}

	public boolean validarIngresoAhora() {
		for (Clase clase : horarios) {
			if (clase.validarIngresoAhora())
				return true;
		}
		return false;

	}

}
