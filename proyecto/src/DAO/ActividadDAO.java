package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import exceptions.ConexionException;

import modelo.Actividad;
import modelo.Deporte;

public class ActividadDAO {
	private static ActividadDAO instancia;

	private ActividadDAO() {
	}

	public static ActividadDAO getInstancia() {
		if (instancia == null)
			instancia = new ActividadDAO();
		return instancia;
	}

	public Actividad findById(int idDeporte, int idActividad) throws ConexionException, SQLException {
		Connection conexion = AdminConexion.getInstancia().obtenerConexion();
		Actividad actividad = null;

		PreparedStatement pstm = conexion.prepareStatement(
				"select * from actividades where id_deporte=? AND id_actividad=?",
				PreparedStatement.RETURN_GENERATED_KEYS);

		pstm.setInt(1, idDeporte);
		pstm.setInt(2, idActividad);

		ResultSet rs = pstm.executeQuery();
		rs.next();

		actividad = new Actividad(rs.getString("descripcion"), rs.getString("activo"),
				DeporteDAO.getInstancia().findById(idDeporte));
		actividad.setCodActividad(idActividad);

		return actividad;
	}

	public Actividad save(Actividad actividad) throws ConexionException, SQLException {

		Connection conexion = AdminConexion.getInstancia().obtenerConexion();

		PreparedStatement pstm = conexion.prepareStatement(
				"insert into actividades (id_deporte, descripcion) values(?, ?)",
				PreparedStatement.RETURN_GENERATED_KEYS);

		pstm.setInt(1, actividad.getDeporte().getCodDeporte());
		pstm.setString(2, actividad.getDescripcion());

		pstm.execute();
		ResultSet rs = pstm.getGeneratedKeys();
		rs.next();

		actividad.setCodActividad(rs.getInt(1));

		return actividad;
	}

	public void delete(Actividad actividad) throws SQLException, ConexionException {

		Connection conexion = AdminConexion.getInstancia().obtenerConexion();

		PreparedStatement pstm = conexion.prepareStatement("DELETE from actividades where id_actividad=?",
				PreparedStatement.RETURN_GENERATED_KEYS);
		pstm.setInt(1, actividad.getCodActividad());

		pstm.execute();

	}

	public Actividad update(Actividad actividad) throws SQLException, ConexionException {

		Connection conexion = AdminConexion.getInstancia().obtenerConexion();

		PreparedStatement pstm = conexion.prepareStatement("UPDATE actividades SET descripcion=? WHERE id_actividad=?",
				PreparedStatement.RETURN_GENERATED_KEYS);

		pstm.setString(1, actividad.getDescripcion());
		pstm.setInt(2, actividad.getCodActividad());

		pstm.execute();
		ResultSet rs = pstm.getGeneratedKeys();
		rs.next();

		return actividad;
	}

	public ArrayList<Actividad> findAll() throws ConexionException, SQLException {

		ArrayList<Actividad> actividades = new ArrayList<Actividad>();

		Connection conexion = AdminConexion.getInstancia().obtenerConexion();

		PreparedStatement pstm = conexion.prepareStatement("select * from actividades",
				PreparedStatement.RETURN_GENERATED_KEYS);

		ResultSet rs = pstm.executeQuery();

		while (rs.next()) {
			Actividad a = new Actividad(rs.getString("descripcion"), rs.getString("activo"),
					DeporteDAO.getInstancia().findById(rs.getInt("id_deporte")));
			a.setCodActividad(rs.getInt("id_actividad"));
			actividades.add(a);
		}
		return actividades;

	}

	public ArrayList<Actividad> findByDeporte(Deporte d) throws ConexionException, SQLException {

		ArrayList<Actividad> actividades = new ArrayList<Actividad>();

		Connection conexion = AdminConexion.getInstancia().obtenerConexion();

		PreparedStatement pstm = conexion.prepareStatement("select * from actividades where id_deporte=?",
				PreparedStatement.RETURN_GENERATED_KEYS);

		pstm.setInt(1, d.getCodDeporte());

		ResultSet rs = pstm.executeQuery();

		while (rs.next()) {
			Actividad a = new Actividad(rs.getString("descripcion"), rs.getString("activo"), d);
			a.setCodActividad(rs.getInt("id_actividad"));
			actividades.add(a);
		}
		return actividades;

	}
}
