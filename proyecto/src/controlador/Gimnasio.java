package controlador;

import java.sql.SQLException;
import java.sql.Time;
import java.time.LocalDate;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import DAO.SocioDAO;
import exceptions.AbonoException;
import exceptions.ActividadException;
import exceptions.AptoException;
import exceptions.ClaseException;
import exceptions.ConexionException;
import exceptions.DeporteException;
import exceptions.EmpleadoException;
import exceptions.EmpresaException;
import exceptions.EscalaException;
import exceptions.MailException;
import exceptions.ReciboException;
import exceptions.SocioException;
import exceptions.SocioYaInscriptoException;
import modelo.Abono;
import modelo.Actividad;
import modelo.AptoMedico;
import modelo.Clase;
import modelo.ComunicadorMail;
import modelo.Cronograma;
import modelo.Deporte;
import modelo.Empleado;
import modelo.EmpleadoAdministrativo;
import modelo.Empresa;
import modelo.EscalaSalarial;
import modelo.InscripcionCorporativa;
import modelo.InscripcionNormal;
import modelo.Profesor;
import modelo.ProfesorFullTime;
import modelo.ProfesorParticular;
import modelo.ReciboSueldo;
import modelo.Socio;
import views.AbonoView;
import views.ActividadView;
import views.AptoView;
import views.ClaseView;
import views.DeporteView;
import views.EmpleadoView;
import views.EmpresaView;
import views.EscalaView;
import views.InscripcionCorporativaView;
import views.ReciboView;
import views.SocioView;

public class Gimnasio {
	public static Gimnasio instancia;
	private ArrayList<Abono> abonos;
	private Cronograma cronogramaActual;
	private ArrayList<Deporte> deportes;
	private ArrayList<Empleado> empleados;
	private ArrayList<Empresa> empresas;
	private ArrayList<EscalaSalarial> escalasSalariales;
	private ArrayList<InscripcionNormal> inscripciones;
	private ComunicadorMail mActual;
	private ArrayList<InscripcionCorporativa> planes;
	private ArrayList<Socio> socios;

	public Gimnasio() {
		super();

		abonos = new ArrayList<Abono>();
		deportes = new ArrayList<Deporte>();
		empleados = new ArrayList<Empleado>();
		empresas = new ArrayList<Empresa>();
		escalasSalariales = new ArrayList<EscalaSalarial>();
		inscripciones = new ArrayList<InscripcionNormal>();
		planes = new ArrayList<InscripcionCorporativa>();
		socios = new ArrayList<Socio>();
		mActual = new ComunicadorMail();
		
		/*
		try
		{
			datosPrueba();
		} catch (SocioException e)
		{
			e.printStackTrace();
		}
		*/
	}

	public static Gimnasio getInstancia() {
		if (instancia == null)
			instancia = new Gimnasio();
		return instancia;
	}

	private Abono buscarAbono(int codAbono) throws AbonoException {
		for (Abono abono : abonos) {
			if (abono.isAbono(codAbono) && !abono.estasEnEstado("Inactivo"))
				return abono;
		}
		throw new AbonoException("No existe el abono con el código: " + codAbono);
	}

	private InscripcionCorporativa buscarConvenioCorporativo(Socio socio, Abono abono) {
		/*
		 * Averigua si un a socio se le aplica un plan corporativo para un
		 * determinado abono
		 */
		for (InscripcionCorporativa inscripcion : planes) {
			if (inscripcion.aplicaASocio(socio) && inscripcion.getAbono() == abono)
				return inscripcion;
		}
		return null;
	}

	private Deporte buscarDeporte(int codDeporte) throws DeporteException {
		for (Deporte deporte : deportes) {
			if (deporte.isDeporte(codDeporte) && !deporte.estasEnEstado("Inactivo"))
				return deporte;
		}
		throw new DeporteException("No existe el deporte con el código " + codDeporte);
	}

	private Empleado buscarEmpleado(String dni) throws EmpleadoException {
		for (Empleado empleado : empleados) {
			if (empleado.isEmpleado(dni) && !empleado.estasEnEstado("Inactivo"))
				return empleado;
		}
		throw new EmpleadoException("No existe empleado con el DNI " + dni);
	}

	private Empresa buscarEmpresa(String cuit) throws EmpresaException {
		for (Empresa empresa : empresas) {
			if (empresa.sosLaEmpresa(cuit) && !empresa.estasEnEstado("Inactivo"))
				return empresa;
		}
		throw new EmpresaException("No existe ninguna empresa con el CUIT " + cuit);
	}

	private EscalaSalarial buscarEscalaSalarial(int codEscalaSalarial) throws EscalaException {
		for (EscalaSalarial escala : escalasSalariales) {
			if (escala.isEscala(codEscalaSalarial))
				return escala;
		}
		throw new EscalaException("No existe escala salarial con el código " + codEscalaSalarial);
	}

	private InscripcionCorporativa buscarInscripcionCorporativa(int codInscripcion) {
		for (InscripcionCorporativa ins : planes) {
			if (ins.isInscripcion(codInscripcion))
				return ins;
		}
		return null;
	}

	private Socio buscarSocio(String dni) throws SocioException {
		for (Socio socio : socios) {
			if (socio.isSocio(dni) && !socio.estasEnElEstado("Inactivo"))
				return socio;
		}
		throw new SocioException("No existe el socio con el DNI: " + dni);
	}

	private void datosPrueba() throws SocioException {

		pruebaSocios();
		pruebaDeportes();
		pruebaActividades();
		pruebaEscalasSalariales();
		pruebaEmpleados();
		pruebaClases();
		pruebaAbonos();
		pruebaAptos();
		prubaEmpresas();
		pruebaIngreso();

	}

	private void prubaEmpresas() {
		altaEmpresa("1", "Empresa A", "Activo");
		altaEmpresa("2", "Empresa B", "Activo");
		altaEmpresa("3", "Empresa C", "Activo");
		altaEmpresa("4", "Empresa D", "Activo");
	}

	private void pruebaAbonos() {
		ArrayList<ActividadView> acts = new ArrayList<ActividadView>();
		try {
			acts.add(buscarDeporte(1).buscarActividad(1).getView());
			acts.add(buscarDeporte(1).buscarActividad(1).getView());

			altaAbono("Futbol Infantil Lunes y Miercoles", 500, LocalDate.now(), 1, acts, "Activo");
			acts.clear();
			acts.add(buscarDeporte(1).buscarActividad(2).getView());
			altaAbono("Futbol Infantil Martes y Jueves", 600, LocalDate.now(), 1, acts, "Activo");
			acts.clear();
			acts.add(buscarDeporte(1).buscarActividad(3).getView());
			altaAbono("Futbol Femenino Martes y Miercoles", 700, LocalDate.now(), 1, acts, "Activo");
			acts.clear();
			acts.add(buscarDeporte(2).buscarActividad(5).getView());
			altaAbono("Natacion libre", 300, LocalDate.now(), 1, acts, "Activo");
			acts.add(buscarDeporte(1).buscarActividad(1).getView());
			altaAbono("Futbol femenino + natacion libre", 800, LocalDate.now(), 1, acts, "Activo");
		} catch (ActividadException e) {

		} catch (DeporteException e) {

		}

	}

	private void pruebaActividades() {
		altaActividad(1, "Futbol Infantil Lunes y Miercoles", "Activo");
		altaActividad(1, "Futbol Infantil Martes y Jueves", "Activo");
		altaActividad(1, "Futbol Femenino Martes y Miercoles", "Activo");
		altaActividad(1, "Futbol Femenino Miercoles y Sabados", "Activo");
		altaActividad(2, "Pileta libre", "Activo");
		altaActividad(2, "Natacion recreativa", "Activo");
		altaActividad(2, "Aquagym", "Activo");
		altaActividad(2, "Natacion profesional", "Activo");
		altaActividad(3, "Basquet Lu, Mier y Vier", "Activo");
		altaActividad(4, "Voley adultos", "Activo");
		altaActividad(5, "Dobles", "Activo");
		altaActividad(5, "Singles", "Activo");
	}

	private void pruebaAptos() {
		altaApto("12345678", LocalDate.now(), "Carlos Perez", "OK", "Activo");
		altaApto("45678915", LocalDate.now().minusMonths(3), "Carlos Perez", "OK", "Activo");
		altaApto("36975124", LocalDate.now().minusMonths(7), "Carlos Perez", "OK", "Activo");
		altaApto("74125496", LocalDate.now().minusMonths(4), "Carlos Perez", "OK", "Activo");
	}

	private void pruebaClases() {

		altaClase(1, 1, new Time(13, 0, 0), new Time(16, 00, 00), "LUNES", null, "Activo");
		altaClase(1, 1, new Time(16, 0, 0), new Time(18, 00, 00), "JUEVES", null, "Activo");
		altaClase(1, 2, new Time(19, 0, 0), new Time(20, 00, 00), "MARTES", null, "Activo");
		altaClase(1, 2, new Time(18, 0, 0), new Time(19, 30, 00), "VIERNES", null, "Activo");
		altaClase(5, 12, new Time(17, 0, 0), new Time(17, 30, 00), "JUEVES", null, "Activo");
	}

	private void pruebaDeportes() {
		altaDeporte("Futbol", "Futbol", "Activo");
		altaDeporte("Natacion", "Natacion", "Activo");
		altaDeporte("Basquet", "", "Activo");
		altaDeporte("Voley", "", "Activo");
		altaDeporte("Tenis", "Tenis", "Activo");
	}

	private void pruebaEmpleados() {
		altaEmpleadoAdministrativo("12312312", "Matias", "Marquez", "Lima 365", "12345678", "Activo", "Cajero", 15000,
				10, 5);
		altaEmpleadoAdministrativo("42312312", "Julian", "Gimenez", "Lima 658", "45678552", "Activo", "Cajero", 12000,
				10, 5);
		altaProfesorFullTime("36598742", "Pedro", "Goyena", "Independencia 123", "4567812", "Activo", 15000, 10, 5);
		altaProfesorFullTime("45612899", "Carla", "Díaz", "Independencia 3698", "3567812", "Activo", 18000, 15, 2);
		altaProfesorParticular("44552233", "Luciana", "Castro", "Salta 555", "1234569", "Activo", 100);
	}

	private void pruebaEscalasSalariales() {
		altaEscalaSalarial(10000, 12000, "");
		altaEscalaSalarial(12000, 14000, "");
		altaEscalaSalarial(14000, 16000, "");
		altaEscalaSalarial(16000, 18000, "");
		altaEscalaSalarial(18000, 25000, "");
	}

	private void pruebaIngreso() throws SocioException {
		altaSocio("1", "Juan", "Martinez", "Chile 123", "4657-4657", "juan@gmail.com", "Activo");
		vincularSocioEmpresa("1", "1");
		altaActividad(1, "Soccer", "Activo");

		altaClase(1, 13, new Time(14, 0, 0), new Time(15, 30, 00), "SABADO", null, "Activo");

		ArrayList<ActividadView> acts = new ArrayList<ActividadView>();

		try {
			acts.add(buscarDeporte(1).buscarActividad(1).getView());
			altaAbono("Hola", 500, LocalDate.now(), 1, acts, "Activo");

		} catch (ActividadException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (DeporteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		generarPlanCorporativo(6, "1", LocalDate.now(), LocalDate.now().plusDays(10), 10);
		generarInscripcion("1", 6);
		altaApto("1", LocalDate.now(), "casca", "csaag", "Activo");
	}

	private void pruebaSocios() throws SocioException {
		altaSocio("12345678", "Raul", "Perez", "Lima 123", "4304-3695", "raul@gmail.com", "Activo");
		altaSocio("45678915", "Oscar", "Gomez", "Lima 888", "4304-3695", "oscarGomez@gmail.com", "Activo");
		altaSocio("36975124", "Mario", "Gonzalez", "Independencia 888", "4304-3695", "mariogonzalez@gmail.com",
				"Activo");
		altaSocio("74125496", "Maria", "Gimenez", "Salta 456", "4304-3456", "jaria@hotmail.com", "Activo");
		altaSocio("44236687", "Julieta", "Rodriguez", "San Juan 888", "4304-3695", "juliRodriguez@gmail.com", "Activo");
		altaSocio("52366841", "Marcos", "Martinez", "Lima 888", "4304-4304", "marquitos@yahoo.com", "Activo");
		altaSocio("40036508", "Francisco", "Fernández", "Lima 1561", "4304-1561", "fran.fernandez@gmail.com", "Activo");
	}

	public void actualizarEscalaEmpleado(Empleado e) {
		// Le asigna a un empleado la escala salarial que le corresponde según
		// su último recibo
		float ultSueldo = e.getUltimoSueldo();
		if (ultSueldo > -1) {
			for (EscalaSalarial esc : escalasSalariales) {
				if (esc.aplica(ultSueldo)) {
					e.setEscalaSalarial(esc);
					return;
				}
			}
		}
	}

	public void actualizarEscalas() {
		// Le asigna a todos los empleados la escala salarial que le corresponde
		// según su último recibo

		for (Empleado e : empleados) {
			if (!e.estasEnEstado("Inactivo"))
				actualizarEscalaEmpleado(e);
		}
	}

	public void altaAbono(String nombre, float precio, LocalDate inicioVigencia, int periodoPago,
			ArrayList<ActividadView> acts, String estado) {

		ArrayList<Actividad> actividades = new ArrayList<Actividad>();

		try {
			for (ActividadView actividad : acts) {
				actividades.add(buscarDeporte(actividad.getCodDeporte()).buscarActividad(actividad.getCodActividad()));
			}
		} catch (ActividadException e) {
			mostrarException(e);

		} catch (DeporteException e) {

			mostrarException(e);
		}

		Abono abono = new Abono(nombre, precio, inicioVigencia, periodoPago, actividades, estado);
		abonos.add(abono);
	}

	public void altaActividad(int codDeporte, String descripcion, String estado) {
		try {
			Deporte deporte = buscarDeporte(codDeporte);
			deporte.altaActividad(descripcion, estado);
		} catch (DeporteException e) {

			mostrarException(e);
		}

	}

	public void altaApto(String dni, LocalDate fechaCertif, String nombreProfesional, String observaciones,
			String estado) {

		try {
			Socio socio = buscarSocio(dni);
			socio.addApto(fechaCertif, nombreProfesional, observaciones, estado);
		} catch (SocioException e) {

			mostrarException(e);
		}

	}

	public void altaClase(int codDeporte, int codActividad, Time horaInicio, Time horaFin, String dia,
			ArrayList<EmpleadoView> profesoresElegidos, String estado) {

		try {
			Deporte deporte = buscarDeporte(codDeporte);
			ArrayList<Profesor> profesores = new ArrayList<Profesor>();
			if (profesoresElegidos != null) {

				for (EmpleadoView emp : profesoresElegidos) {
					Profesor p = (Profesor) buscarEmpleado(emp.getDni());
					if (p != null)
						profesores.add(p);
				}
			}
			deporte.altaClase(codActividad, horaInicio, horaFin, dia, profesores, estado);
		} catch (DeporteException e) {
			mostrarException(e);
		} catch (ActividadException e) {
			mostrarException(e);
		} catch (EmpleadoException e) {
			mostrarException(e);
		}
	}

	public void altaDeporte(String titulo, String descripcion, String estado) {
		Deporte deporte = new Deporte(titulo, descripcion, estado);
		deportes.add(deporte);

	}

	public void altaEmpleadoAdministrativo(String dni, String nombre, String apellido, String domicilio,
			String telefono, String estado, String puesto, float sueldoBasico, float deducciones, float retenciones) {

		Empleado empleado;
		try {
			empleado = buscarEmpleado(dni);
		} catch (EmpleadoException e) {
			empleado = new EmpleadoAdministrativo(dni, nombre, apellido, domicilio, telefono, estado, puesto,
					sueldoBasico, deducciones, retenciones);
			empleados.add(empleado);
		}

	}

	public void altaEmpresa(String cuit, String razonSocial, String estado) {
		try {
			Empresa e = buscarEmpresa(cuit);
		} catch (EmpresaException e) {
			Empresa em = new Empresa(cuit, razonSocial, estado);
			empresas.add(em);
		}

	}

	public void altaEscalaSalarial(float sueldoBase, float sueldoHasta, String novedades) {

		EscalaSalarial escala = new EscalaSalarial(sueldoBase, sueldoHasta, novedades);
		escalasSalariales.add(escala);

	}

	public void altaProfesorFullTime(String dni, String nombre, String apellido, String domicilio, String telefono,
			String estado, float sueldoBasico, float deducciones, float retenciones) {
		Empleado empleado;
		try {
			empleado = buscarEmpleado(dni);
			throw new EmpleadoException("Ya existe un empleado con el dni " + dni);

		} catch (EmpleadoException e) {
			empleado = new ProfesorFullTime(dni, nombre, apellido, domicilio, telefono, estado, sueldoBasico,
					deducciones, retenciones);
			empleados.add(empleado);
		}

	}

	public void altaProfesorParticular(String dni, String nombre, String apellido, String domicilio, String telefono,
			String estado, float valorHora) {

		Empleado empleado;
		try {
			empleado = buscarEmpleado(dni);
			throw new EmpleadoException("Ya existe un empleado con el dni " + dni);

		} catch (EmpleadoException e) {
			empleado = new ProfesorParticular(dni, nombre, apellido, domicilio, telefono, estado, valorHora);
			empleados.add(empleado);
		}

	}

	public void altaSocio(String dni, String nombre, String apellido, String domicilio, String telefono, String mail,
			String estado) throws SocioException {
		Socio socio;
		try {
			socio = buscarSocio(dni);
		} catch (SocioException e) {
			socio = new Socio(dni, nombre, apellido, domicilio, telefono, mail, estado);
			try
			{
				SocioDAO.getInstancia().save(socio);
			} catch (ConexionException e1)
			{
				throw new SocioException("No se pudo crear el socio.");
			} catch (SQLException e1)
			{
				throw new SocioException("Problema en la conexion con la base de datos.");
			}
			socios.add(socio);
		}

	}

	public void bajaAbono(int codigo, String estado) {

		try {
			Abono abono = buscarAbono(codigo);
			abono.setEstado(estado);
		} catch (AbonoException e) {

			mostrarException(e);
		}

	}

	private void mostrarException(Exception e) {
		JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
	}

	public void bajaActividad(int codDeporte, int codActividad, String estado) {
		try {
			Deporte deporte = buscarDeporte(codDeporte);
			deporte.bajaActividad(codActividad, estado);
		} catch (DeporteException e) {
			// TODO Auto-generated catch block
			mostrarException(e);
		} catch (ActividadException e) {
			// TODO Auto-generated catch block
			mostrarException(e);
		}

	}

	public void bajaClase(int codDeporte, int codActividad, int codClase, String estado) {

		try {
			Deporte deporte = buscarDeporte(codDeporte);
			deporte.bajaClase(codActividad, codClase, estado);
		} catch (DeporteException e) {
			mostrarException(e);
		} catch (ActividadException e) {
			mostrarException(e);
		} catch (ClaseException e) {
			mostrarException(e);
		}

	}

	public void bajaDeporte(int codigo, String estado) {
		try {
			Deporte deporte = buscarDeporte(codigo);
			deporte.setEstado(estado);
		} catch (DeporteException e) {
			// TODO Auto-generated catch block
			mostrarException(e);
		}

	}

	public void bajaEmpleado(String dni, String estado) {

		try {
			Empleado emp = buscarEmpleado(dni);
			emp.setEstado(estado);

		} catch (EmpleadoException e) {
			mostrarException(e);
		}

	}

	public void bajaEmpresa(String cuit, String estado) {

		try {
			Empresa emp = buscarEmpresa(cuit);
			emp.setEstado(estado);
		} catch (EmpresaException e) {
			// TODO Auto-generated catch block
			mostrarException(e);
		}
	}

	public void bajaEscala(int codEscala) {

		try {
			EscalaSalarial e = buscarEscalaSalarial(codEscala);
			escalasSalariales.remove(e);

		} catch (EscalaException e) {
			mostrarException(e);
		}

	}

	public void bajaInscripcion(int codigo, String estado) {

		for (InscripcionNormal inscripcion : inscripciones) {
			if (inscripcion.isInscripcion(codigo))
				inscripciones.remove(inscripcion);
		}
	}

	public void bajaSocio(String dni, String estado) {

		try {
			Socio socio = buscarSocio(dni);
			socio.setEstado(estado);
		} catch (SocioException e) {

			mostrarException(e);
		}

	}

	public String[][] generarCronograma(LocalDate fechaDesde, LocalDate fechaHasta) {
		cronogramaActual = new Cronograma(fechaDesde, fechaHasta);
		for (Deporte deporte : deportes) {
			if (!deporte.estasEnEstado("Inactivo"))
				cronogramaActual.addClases(deporte.generarCronograma(fechaDesde, fechaHasta));
		}
		return cronogramaActual.generar();

	}

	public void generarInscripcion(String dni, int codAbono) {

		try {
			Socio socio = buscarSocio(dni);
			Abono abono = buscarAbono(codAbono);
			if (!socio.isInscripto(abono)) {

				InscripcionCorporativa i = buscarConvenioCorporativo(socio, abono);
				if (i != null)
					inscripciones.add(i.addInscripcion(socio));
				else {
					InscripcionNormal inscripcion = new InscripcionNormal(abono, socio);
					inscripciones.add(inscripcion);
					socio.addInscripcion(inscripcion);
				}

			}
		} catch (SocioException e) {
			mostrarException(e);
		} catch (AbonoException e) {

			mostrarException(e);
		} catch (SocioYaInscriptoException e) {

			mostrarException(e);
		}

	}

	public void generarMaiInscripcionVencida() {

		ArrayList<Socio> destinatarios = new ArrayList<Socio>();

		for (InscripcionNormal inscripcion : inscripciones) {
			if (inscripcion.estasVencida())
				destinatarios.add(inscripcion.getSocio());
		}

		try {
			mActual.addDestinatarios(destinatarios);
		} catch (Exception e) {
			mostrarException(e);
		}
	}

	public void generarMailAptoMedico(String estado) {

		ArrayList<Socio> destinatarios = new ArrayList<Socio>();
		for (Socio socio : socios) {
			if (socio.tenesAptoEnEstado(estado))
				destinatarios.add(socio);
		}
		try {
			mActual.addDestinatarios(destinatarios);
		} catch (MailException e) {
			mostrarException(e);
		}

	}

	public void generarMailDeporte(int codDeporte) {
		ArrayList<Socio> destinatarios = new ArrayList<Socio>();

		try {
			Deporte deporte = buscarDeporte(codDeporte);
			for (InscripcionNormal inscripcion : inscripciones) {
				if (inscripcion.hacesElDeporte(deporte))
					destinatarios.add(inscripcion.getSocio());
			}
			mActual.addDestinatarios(destinatarios);
		} catch (DeporteException e) {
			mostrarException(e);
		} catch (Exception e) {
		}

	}

	public void generarMailTodos(String estado) {
		ArrayList<Socio> destinatarios = new ArrayList<Socio>();
		for (Socio socio : socios) {
			if (socio.estasEnElEstado(estado))
				destinatarios.add(socio);
		}
		try {
			mActual.addDestinatarios(destinatarios);
		} catch (MailException e) {
			mostrarException(e);
		}
	}

	public void generarMailParticular(String dni) {

		try {
			Socio destinatario = buscarSocio(dni);
			mActual.addDestinatario(destinatario);
		} catch (SocioException e) {
			mostrarException(e);
		}

	}

	public void generarPlanCorporativo(int codAbono, String cuit, LocalDate inicioVigencia, LocalDate finVigencia,
			float descuentoMasivo) {

		try {
			Empresa e = buscarEmpresa(cuit);
			Abono abono = buscarAbono(codAbono);
			InscripcionCorporativa inscripcion = new InscripcionCorporativa(abono, e, inicioVigencia, finVigencia,
					descuentoMasivo);
			planes.add(inscripcion);

		} catch (EmpresaException e) {
			// TODO Auto-generated catch block
			mostrarException(e);
		} catch (AbonoException e1) {
			// TODO Auto-generated catch block
			mostrarException(e1);
		}

	}

	public void generarRecibos() {

		for (Empleado empleado : empleados) {
			if (!empleado.estasEnEstado("Inactivo")) {
				empleado.generarRecibo();
				actualizarEscalaEmpleado(empleado);
			}
		}
	}

	public AbonoView getAbonoView(int cod) {
		try {
			return buscarAbono(cod).getView();
		} catch (AbonoException e) {
			// TODO Auto-generated catch block
			mostrarException(e);
			return null;
		}
	}

	public ActividadView getActividadView(int dep, int act) {
		try {
			return buscarDeporte(dep).buscarActividad(act).getView();
		} catch (ActividadException e) {
			mostrarException(e);
			return null;
		} catch (DeporteException e) {
			mostrarException(e);
			return null;
		}
	}

	public AptoView getAptoView(String dni, int cod) {
		try {
			return buscarSocio(dni).getAptoView(cod);
		} catch (SocioException e) {
			mostrarException(e);
			return null;
		} catch (AptoException e) {
			mostrarException(e);
			return null;
		}
	}

	public ClaseView getClaseView(int deporte, int actividad, int clase) {

		try {
			return buscarDeporte(deporte).getClaseView(actividad, clase);
		} catch (ActividadException e) {
			mostrarException(e);
		} catch (DeporteException e) {
			mostrarException(e);
		} catch (ClaseException e) {
			mostrarException(e);
		}
		return null;
	}

	public DeporteView getDeporteView(int codigo) {
		try {
			return buscarDeporte(codigo).getView();
		} catch (DeporteException e) {
			mostrarException(e);
			return null;
		}
	}

	public EmpleadoView getEmpleadoView(String dni) {
		try {
			return buscarEmpleado(dni).getView();
		} catch (EmpleadoException e) {
			mostrarException(e);
			return null;
		}
	}

	public EmpresaView getEmpresaView(String cuit) {
		try {
			return buscarEmpresa(cuit).getView();
		} catch (EmpresaException e) {
			mostrarException(e);
			return null;
		}
	}

	public EscalaView getEscalaView(int cod) {
		try {
			return buscarEscalaSalarial(cod).getView();
		} catch (EscalaException e) {
			mostrarException(e);
			return null;
		}
	}

	public InscripcionCorporativaView getInscripcionCorporativaView(int cod) {
		return buscarInscripcionCorporativa(cod).getView();
	}

	public SocioView getSocioView(String dni) {

		try {
			return buscarSocio(dni).getView();
		} catch (SocioException e) {
			mostrarException(e);
			return null;
		}
	}

	public void modificarAbono(int codAbono, String nombre, float precio, int periodoPago) {

		try {
			Abono abono = buscarAbono(codAbono);
			abono.modificar(nombre, precio, periodoPago);
		} catch (AbonoException e) {
			mostrarException(e);
		}

	}

	public void modificarActividad(int codDeporte, int codActividad, String descripcion) {

		try {
			Deporte deporte = buscarDeporte(codDeporte);
			deporte.modificarActividad(codActividad, descripcion);
		} catch (DeporteException e) {
			mostrarException(e);
		} catch (ActividadException e) {
			mostrarException(e);
		}

	}

	public void modificarAptoMedico(String dni, int codAptoMedico, LocalDate fechaCertificado,
			String profesionalFirmante, String observaciones) {

		try {
			Socio socio = buscarSocio(dni);
			socio.modificarApto(codAptoMedico, fechaCertificado, profesionalFirmante, observaciones);
		} catch (SocioException e) {
			mostrarException(e);
		} catch (AptoException e) {
			mostrarException(e);
		}

	}

	public void modificarClase(int codDeporte, int codActividad, int codClase, Time horarioInicio, Time horarioFin,
			String dia, ArrayList<EmpleadoView> profesoresElegidos) {

		ArrayList<Profesor> profesores = new ArrayList<Profesor>();
		try {
			if (profesoresElegidos != null) {

				for (EmpleadoView emp : profesoresElegidos) {
					Profesor p = (Profesor) buscarEmpleado(emp.getDni());
					if (p != null)
						profesores.add(p);
				}
			}

			Deporte deporte = buscarDeporte(codDeporte);
			deporte.modificarClase(codActividad, codClase, horarioInicio, horarioFin, dia, profesores);
		} catch (DeporteException e) {

			mostrarException(e);
		} catch (ActividadException e) {
			mostrarException(e);
		} catch (ClaseException e) {
			mostrarException(e);
		} catch (EmpleadoException e) {
			mostrarException(e);
		}

	}

	public void modificarDeporte(int cod, String titulo, String descripcion) {
		try {
			Deporte deporte = buscarDeporte(cod);
			deporte.modificar(titulo, descripcion);
		} catch (DeporteException e) {
			mostrarException(e);
		}

	}

	public void modificarEmpleado(String dni, String nombre, String apellido, String domicilio, String telefono) {

		try {
			Empleado empleado = buscarEmpleado(dni);
			empleado.modificar(nombre, apellido, domicilio, telefono);
			actualizarEscalaEmpleado(empleado);
		} catch (EmpleadoException e) {
			mostrarException(e);
		}

	}

	public void modificarEmpresa(String cuit, String razonSocial) {

		try {
			Empresa e = buscarEmpresa(cuit);
			e.modificar(razonSocial);
		} catch (EmpresaException e) {
			mostrarException(e);
		}

	}

	public void modificarEscalaSalarial(int codEscala, float sueldoBase, float sueldoHasta, String novedades) {

		try {
			EscalaSalarial escala = buscarEscalaSalarial(codEscala);
			escala.modificar(sueldoBase, sueldoHasta, novedades);

		} catch (EscalaException e) {
			mostrarException(e);
		}

	}

	public void modificarPlanCorporativo(int codInscripcion, LocalDate finVigencia, float descuentoMasivo) {

		InscripcionCorporativa inscripcion = buscarInscripcionCorporativa(codInscripcion);
		if (inscripcion != null) {
			inscripcion.modificar(finVigencia, descuentoMasivo);
		}
	}

	public void modificarSocio(String dni, String nombre, String apellido, String domicilio, String telefono,
			String mail) {

		try {
			Socio socio = buscarSocio(dni);
			socio.modificar(nombre, apellido, domicilio, telefono, mail);
		} catch (SocioException e) {
			mostrarException(e);
		}

	}

	public boolean validarIngreso(String dni) {

		try {
			Socio socio = buscarSocio(dni);
			return socio.validarIngreso();
		} catch (SocioException e) {
			return false;
		}

	}

	public void vincularSocioEmpresa(String cuit, String dni) {
		try {
			Socio s = buscarSocio(dni);
			Empresa e = buscarEmpresa(cuit);
			e.addEmpleado(s);

		} catch (SocioException e) {
			mostrarException(e);
		} catch (EmpresaException e1) {

			mostrarException(e1);
		}

	}

	public ReciboView getReciboView(String dni, int mes, int anio) {

		try {
			return buscarEmpleado(dni).getReciboView(mes, anio);
		} catch (ReciboException e) {
			mostrarException(e);
			return null;
		} catch (EmpleadoException e) {
			mostrarException(e);
			return null;
		}
	}

	public String getDestinatariosActuales() {
		return mActual.getDestinatarios();
	}

	public void limpiarDestinatariosMail() {
		mActual.limpiarDestinatarios();
	}

	public boolean enviarMail(String asunto, String cuerpo) {
		try {
			mActual.enviar(asunto, cuerpo);
			return true;
		} catch (MailException e) {
			mostrarException(e);
			return false;
		}
	}

	public boolean mailConContenido() {
		return mActual.mailConContenido();
	}

	public ArrayList<ActividadView> getAllActividadesView() {
		ArrayList<ActividadView> acts = new ArrayList<ActividadView>();
		for (Deporte dep : deportes) {
			if (!dep.estasEnEstado("INACTIVO"))
				acts.addAll(dep.getAllActividadesView());
		}
		return acts;
	}

	public ArrayList<DeporteView> getAllDeportesView() {
		ArrayList<DeporteView> deportesView = new ArrayList<DeporteView>();

		for (Deporte deporte : deportes) {
			if (!deporte.estasEnEstado("Inactivo"))
				deportesView.add(deporte.getView());
		}
		return deportesView;
	}

	public ArrayList<ActividadView> getActividadesView(int id) {
		try {
			return buscarDeporte(id).getAllActividadesView();
		} catch (DeporteException e) {

			mostrarException(e);
			return null;
		}
	}

	public ArrayList<EmpleadoView> getAllProfesoresView() {
		ArrayList<EmpleadoView> profesores = new ArrayList<EmpleadoView>();
		for (Empleado empleado : empleados) {
			if (empleado instanceof Profesor)
				profesores.add(empleado.getView());
		}
		return profesores;
	}

	public ArrayList<ClaseView> getClasesView(int codDeporte, int codActividad) {
		try {
			return buscarDeporte(codDeporte).getClasesView(codActividad);
		} catch (ActividadException e) {
			mostrarException(e);
			return null;
		} catch (DeporteException e) {
			mostrarException(e);
			return null;
		}
	}
}
