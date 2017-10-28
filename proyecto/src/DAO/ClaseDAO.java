package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;

import exceptions.ConexionException;
import modelo.Actividad;
import modelo.Clase;
import modelo.Deporte;

public class ClaseDAO {
	private static ClaseDAO instancia;

	private ClaseDAO() {
	}

	public static ClaseDAO getInstancia() {
		if (instancia == null)
			instancia = new ClaseDAO();
		return instancia;
	}

	public Clase findById(int idDeporte, int idAct, int idClase) throws ConexionException, SQLException {
		Connection conexion = AdminConexion.getInstancia().obtenerConexion();
		Clase clase = null;

		String query = "select a.id_deporte as deporte, a.id_actividad as actividad, "
				+ "c.id_clase as clase, c.dia as dia, c.horaInicio as inicio,c.horaFin as fin, c.activo as activo ";
		query += "from clases c inner join actividades a on a.id_actividad=c.id_actividad ";
		query += "where a.id_deporte=? and  a.id_actividad= ?  and c.id_clase=?";

		PreparedStatement pstm = conexion.prepareStatement(query, PreparedStatement.RETURN_GENERATED_KEYS);

		pstm.setInt(1, idDeporte);
		pstm.setInt(2, idAct);
		pstm.setInt(3, idClase);

		ResultSet rs = pstm.executeQuery();
		rs.next();

		clase = new Clase(new Time(11, 11, 11), new Time(12, 11, 11), rs.getString("dia"), null,
				rs.getString("activo"));
		clase.setCodClase(idClase);
		clase.setActividad(ActividadDAO.getInstancia().findById(idDeporte, idAct));

		return clase;
	}

	public Clase save(Clase clase) throws ConexionException, SQLException {

		Connection conexion = AdminConexion.getInstancia().obtenerConexion();

		PreparedStatement pstm = conexion.prepareStatement(
				"insert into clases (id_actividad, dia, horaInicio, horaFin) values(?, ?, ?, ?)",
				PreparedStatement.RETURN_GENERATED_KEYS);

		pstm.setInt(1, clase.getActividad().getCodActividad());
		pstm.setString(2, clase.getDia());
		String time = clase.getHorarioInicio().getHours() + ":" + clase.getHorarioInicio().getMinutes();
		pstm.setString(3, time);
		time = clase.getHorarioFin().getHours() + ":" + clase.getHorarioFin().getMinutes();
		pstm.setString(4, time);

		pstm.execute();
		ResultSet rs = pstm.getGeneratedKeys();
		rs.next();

		// clase.setCodClase(rs.getInt("id_clase"));

		return clase;
	}

	public void delete(Clase clase) throws SQLException, ConexionException {

		Connection conexion = AdminConexion.getInstancia().obtenerConexion();

		PreparedStatement pstm = conexion.prepareStatement("DELETE from clases where id_clase=?",
				PreparedStatement.RETURN_GENERATED_KEYS);
		pstm.setInt(1, clase.getCodClase());

		pstm.execute();

	}

	public Clase update(Clase clase) throws SQLException, ConexionException {

		Connection conexion = AdminConexion.getInstancia().obtenerConexion();

		PreparedStatement pstm = conexion.prepareStatement(
				"UPDATE clases SET dia=?, horaInicio=?, horaFin=? WHERE id_clase=?",
				PreparedStatement.RETURN_GENERATED_KEYS);

		pstm.setString(1, clase.getDia());
		String time = clase.getHorarioInicio().getHours() + ":" + clase.getHorarioInicio().getMinutes();
		pstm.setString(2, time);
		time = clase.getHorarioFin().getHours() + ":" + clase.getHorarioFin().getMinutes();
		pstm.setString(3, time);
		pstm.setInt(4, clase.getCodClase());

		pstm.execute();
		ResultSet rs = pstm.getGeneratedKeys();
		rs.next();

		return clase;
	}

	// public ArrayList<Clase> findAll() throws ConexionException, SQLException
	// {
	//
	// ArrayList<Clase> clasees = new ArrayList<Clase>();
	//
	// Connection conexion = AdminConexion.getInstancia().obtenerConexion();
	//
	// PreparedStatement pstm = conexion.prepareStatement("select * from
	// clasees",
	// PreparedStatement.RETURN_GENERATED_KEYS);
	//
	// ResultSet rs = pstm.executeQuery();
	//
	// while (rs.next()) {
	// Clase a = new Clase(rs.getString("descripcion"), rs.getString("activo"),
	// DeporteDAO.getInstancia().findById(rs.getInt("id_deporte")));
	// a.setCodClase(rs.getInt("id_clase"));
	// clasees.add(a);
	// }
	// return clasees;
	//
	// }

	public ArrayList<Clase> findByActividad(Actividad actividad) throws ConexionException, SQLException {

		ArrayList<Clase> clases = new ArrayList<Clase>();

		Connection conexion = AdminConexion.getInstancia().obtenerConexion();

		PreparedStatement pstm = conexion.prepareStatement("select * from clases where id_actividad= ?",
				PreparedStatement.RETURN_GENERATED_KEYS);

		pstm.setInt(1, actividad.getCodActividad());

		ResultSet rs = pstm.executeQuery();

		while (rs.next()) {
			Clase a = new Clase(null, null, rs.getString("dia"), null, rs.getString("activo"));
			a.setCodClase(rs.getInt("id_clase"));
			clases.add(a);
		}
		return clases;

	}
}
