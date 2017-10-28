package views;

import java.sql.Time;
import java.util.ArrayList;

import modelo.Profesor;

public class ClaseView {

	private String actividad;
	private int cod;
	private String dia;
	private Time horarioFin;
	private Time horarioInicio;
	private ArrayList<EmpleadoView> profesores;

	public ClaseView(int cod, Time horarioInicio, Time horarioFin, String dia, ArrayList<EmpleadoView> profesores,
			String actividad) {
		super();
		this.cod = cod;
		this.horarioInicio = horarioInicio;
		this.horarioFin = horarioFin;
		this.dia = dia;
		this.profesores = profesores;
		this.actividad = actividad;
	}

	public int diaANumbero() {
		if (dia.equalsIgnoreCase("LUNES"))
			return 1;
		else if (dia.equalsIgnoreCase("MARTES"))
			return 2;
		else if (dia.equalsIgnoreCase("MIERCOLES"))
			return 3;
		else if (dia.equalsIgnoreCase("JUEVES"))
			return 4;
		else if (dia.equalsIgnoreCase("VIERNES"))
			return 5;
		else if (dia.equalsIgnoreCase("SABADO"))
			return 6;
		else if (dia.equalsIgnoreCase("DOMINGO"))
			return 7;
		else // Libre
			return 8;

	}

	public String getActividad() {
		return actividad;
	}

	public int getCodClase() {
		return cod;
	}

	public String getDia() {
		return dia;
	}

	public Time getHorarioFin() {
		return horarioFin;
	}

	public Time getHorarioInicio() {
		return horarioInicio;
	}

	public ArrayList<EmpleadoView> getProfesores() {
		return profesores;
	}

	@Override
	public String toString() {
		return dia + " de " + horarioInicio + " a " + horarioFin;
	}

}
