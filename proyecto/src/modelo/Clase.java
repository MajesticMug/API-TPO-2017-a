package modelo;

import java.sql.Time;
import java.time.LocalDate;
import java.util.ArrayList;

import views.ClaseView;
import views.EmpleadoView;

public class Clase {
	private static int proxCod;
	private Actividad actividad;
	private int codClase;
	private String dia;
	private String estado;
	private Time horarioFin;
	private Time horarioInicio;
	private ArrayList<Profesor> profesores;

	public Clase(Time horarioInicio, Time horarioFin, String dia, ArrayList<Profesor> profesores, String estado) {
		super();
		this.horarioInicio = horarioInicio;
		this.horarioFin = horarioFin;
		this.dia = dia;
		this.profesores = profesores;
		this.estado = estado;
		codClase = ++proxCod;
	}

	public static int getProxCod() {
		return proxCod;
	}

	public static void setProxCod(int proxCod) {
		Clase.proxCod = proxCod;
	}

	private String traducir(String dia) {

		if (dia.equalsIgnoreCase("LUNES"))
			return "Monday";
		else if (dia.equalsIgnoreCase("MARTES"))
			return "Tuesday";
		else if (dia.equalsIgnoreCase("MIERCOLES"))
			return "Wednesday";
		else if (dia.equalsIgnoreCase("JUEVES"))
			return "Thursday";
		else if (dia.equalsIgnoreCase("VIERNES"))
			return "Friday";
		else if (dia.equalsIgnoreCase("SABADO"))
			return "Saturday";
		else if (dia.equalsIgnoreCase("DOMINGO"))
			return "Sunday";
		else
			return dia;

	}

	public boolean estasEnEstado(String estado) {

		return this.estado.equals(estado);
	}

	public Actividad getActividad() {
		return actividad;
	}

	public int getCodClase() {
		return codClase;
	}

	public String getDia() {
		return dia;
	}

	public String getEstado() {
		return estado;
	}

	public Time getHorarioFin() {
		return horarioFin;
	}

	public Time getHorarioInicio() {
		return horarioInicio;
	}

	public ArrayList<Profesor> getProfesores() {
		return profesores;
	}

	public ClaseView getView(String descripcion) {
		ArrayList<EmpleadoView> profesores = new ArrayList<EmpleadoView>();
		for (Profesor pr : this.profesores) {
			profesores.add(pr.getView());
		}
		return new ClaseView(codClase, horarioInicio, horarioFin, dia, profesores, descripcion);
	}

	public boolean isClase(int codClase) {
		return this.codClase == codClase;
	}

	public void modificar(Time horarioInicio, Time horarioFin, String dia, ArrayList<Profesor> profesores) {
		this.horarioInicio = horarioInicio;
		this.horarioFin = horarioFin;
		this.dia = dia;
		this.profesores = profesores;
	}

	public boolean seHaceEntreLasFechas(LocalDate fechaDesde, LocalDate fechaHasta) {
		while (fechaDesde.isBefore(fechaHasta.plusDays(1))) {
			if (fechaDesde.getDayOfWeek().toString().equalsIgnoreCase(traducir(dia)))
				return true;
			fechaDesde = fechaDesde.plusDays(1);
		}

		return false;

	}

	public void setActividad(Actividad actividad) {
		this.actividad = actividad;
	}

	public void setCodClase(int codClase) {
		this.codClase = codClase;
	}

	public void setDia(String dia) {
		this.dia = dia;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public void setHorarioFin(Time horarioFin) {
		this.horarioFin = horarioFin;
	}

	public void setHorarioInicio(Time horarioInicio) {
		this.horarioInicio = horarioInicio;
	}

	public void setProfesores(ArrayList<Profesor> profesores) {
		this.profesores = profesores;
	}

	public boolean validarIngresoAhora() {
		return LocalDate.now().getDayOfWeek().toString().equalsIgnoreCase(traducir(dia));

	}
}
