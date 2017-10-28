package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import exceptions.ConexionException;
import exceptions.EmpleadoException;
import modelo.EmpleadoAdministrativo;
import modelo.Abono;
import modelo.Actividad;
import modelo.Deporte;

public class AbonoDAO {
	private static AbonoDAO instancia;

	private AbonoDAO() {
	}

	public static AbonoDAO getInstancia() {
		if (instancia == null)
			instancia = new AbonoDAO();
		return instancia;
	}

	public Abono findById(int id) throws ConexionException, SQLException {
		Connection conexion = AdminConexion.getInstancia().obtenerConexion();
		Abono abono = null;

		String query = "select a.id_abono as id_abono, a.abono as abono, a.precio as precio,  a.periodoPago as periodoPago,"
				+ "act.id_actividad as id_actividad, act.id_deporte as id_deporte " + "from abonos a ";
		query += "inner join abono_actividad aa ";
		query += "on aa.id_abono=a.id_abono ";
		query += "inner join actividades act ";
		query += "on act.id_actividad=aa.id_actividad ";
		query += "inner join deportes d ";
		query += "on d.id_deporte=act.id_deporte ";
		query += "where a.id_abono= " + id;
		PreparedStatement pstm = conexion.prepareStatement(query, PreparedStatement.RETURN_GENERATED_KEYS);

		ResultSet rs = pstm.executeQuery();
		rs.next();

		String descripcion = rs.getString("abono");
		Float precio = rs.getFloat("precio");
		int cod = rs.getInt("id_abono");
		int periodo = rs.getInt("periodoPago");

		ArrayList<Actividad> actividades = new ArrayList<Actividad>();
		actividades.add(ActividadDAO.getInstancia().findById(rs.getInt("id_deporte"), rs.getInt("id_actividad")));

		while (rs.next()) {
			actividades.add(ActividadDAO.getInstancia().findById(rs.getInt("id_deporte"), rs.getInt("id_actividad")));

		}

		// LOCALDATE
		abono = new Abono(descripcion, precio, LocalDate.now(), periodo, actividades, "SI");
		abono.setCodAbono(cod);
		return abono;
	}

	public Abono save(Abono abono) throws ConexionException, SQLException {

		Connection conexion = AdminConexion.getInstancia().obtenerConexion();

		PreparedStatement pstm = conexion.prepareStatement(
				"insert into abonos (abono, inicioVigencia, periodoPago, precio) values(?,GETDATE(),?, ?)",
				PreparedStatement.RETURN_GENERATED_KEYS);

		pstm.setString(1, abono.getNombre());
		pstm.setInt(2, abono.getPeriodoPago());
		pstm.setFloat(3, abono.getPrecio());

		pstm.execute();
		ResultSet rs = pstm.getGeneratedKeys();
		rs.next();
		abono.setCodAbono(rs.getInt(1));

		for (Actividad actividad : abono.getActividades()) {

			pstm = conexion.prepareStatement("insert into abono_actividad (id_abono, id_actividad) values(?,?)",
					PreparedStatement.RETURN_GENERATED_KEYS);
			pstm.setInt(1, abono.getCodAbono());
			pstm.setInt(2, actividad.getCodActividad());
			pstm.execute();
			rs = pstm.getGeneratedKeys();
			rs.next();

		}

		return abono;
	}

	public void delete(Abono abono) throws SQLException, ConexionException {

		Connection conexion = AdminConexion.getInstancia().obtenerConexion();

		PreparedStatement pstm = conexion.prepareStatement(
				"DELETE from abono_actividad where id_abono=? DELETE from abonos where id_abono=?",
				PreparedStatement.RETURN_GENERATED_KEYS);
		pstm.setInt(1, abono.getCodAbono());
		pstm.setInt(2, abono.getCodAbono());

		pstm.execute();

	}

	public void update(Abono abono) throws SQLException, ConexionException {

		Connection conexion = AdminConexion.getInstancia().obtenerConexion();

		PreparedStatement pstm = conexion.prepareStatement(
				"UPDATE abonos set abono=?, periodoPago=?, precio=? where id_abono=?",
				PreparedStatement.RETURN_GENERATED_KEYS);
		pstm.setString(1, abono.getNombre());
		pstm.setInt(2, abono.getPeriodoPago());
		pstm.setFloat(3, abono.getPrecio());
		pstm.setInt(4, abono.getCodAbono());

		pstm.execute();

	}

	// public ArrayList<Abono> findAll() throws EmpleadoException,
	// ConexionException, SQLException {
	//
	// ArrayList<Abono> abonos = new ArrayList<Abono>();
	// Connection conexion = AdminConexion.getInstancia().obtenerConexion();
	//
	// String query = "select a.id_abono as id_abono, a.abono as abono, a.precio
	// as precio, a.periodoPago as periodoPago,"
	// + "act.id_actividad as id_actividad, act.id_deporte as id_deporte " +
	// "from abonos a ";
	// query += "inner join abono_actividad aa ";
	// query += "on aa.id_abono=a.id_abono ";
	// query += "inner join actividades act ";
	// query += "on act.id_actividad=aa.id_actividad ";
	// query += "inner join deportes d ";
	// query += "on d.id_deporte=act.id_deporte ";
	//
	// PreparedStatement pstm = conexion.prepareStatement(query,
	// PreparedStatement.RETURN_GENERATED_KEYS);
	//
	// ResultSet rs = pstm.executeQuery();
	// ArrayList<Actividad> actividades = new ArrayList<Actividad>();
	//
	// while (rs.next()) {
	//
	// }
	//
	// return abonos;
	//
	// }
}
