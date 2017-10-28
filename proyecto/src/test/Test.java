package test;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Time;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.Random;

import javax.swing.JOptionPane;

import DAO.AbonoDAO;
import DAO.ActividadDAO;
import DAO.AdminConexion;
import DAO.ClaseDAO;
import DAO.DeporteDAO;
import DAO.EmpleadoAdministrativoDAO;
import DAO.EmpresaDAO;
import DAO.ProfesorFullTimeDAO;
import DAO.ProfesorParticularDAO;
import DAO.ReciboSueldoDAO;
import DAO.SocioDAO;
import controlador.Gimnasio;
import exceptions.ConexionException;
import exceptions.EmpleadoException;
import modelo.Abono;
import modelo.Actividad;
import modelo.Clase;
import modelo.Deporte;
import modelo.EmpleadoAdministrativo;
import modelo.Empresa;
import modelo.Matriz;
import modelo.ProfesorFullTime;
import modelo.ProfesorParticular;
import modelo.ReciboSueldo;
import modelo.Socio;

public class Test {

	public static void main(String[] args) {

		
	}

	public static Date localDateToDate(LocalDate fecha) {

		Instant instant = fecha.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant();
		return Date.from(instant);

	}

	public static LocalDate dateToLocalDate(Date fecha) {

		Instant instant = fecha.toInstant();
		ZonedDateTime zdt = instant.atZone(ZoneId.systemDefault());
		LocalDate date = zdt.toLocalDate();

		return date;
	}

	private static void imprimir(String[][] cronograma) {
		Time time = new Time(0, 0, 0);
		System.out.println("Lunes | Martes | Miercoles | Jueves | Viernes | Sabado | Domingo");
		for (int i = 0; i < 24; i++) {
			System.out.print(time.toLocalTime() + " - ");
			time.setHours(time.getHours() + 1);
			for (int j = 0; j < 7; j++)
				System.out.print(cronograma[i][j] + "| ");
			System.out.println();
		}
	}

}
