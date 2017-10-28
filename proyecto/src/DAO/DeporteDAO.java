package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import exceptions.ConexionException;

import modelo.Deporte;

public class DeporteDAO {
	private static DeporteDAO instancia;

	private DeporteDAO() {
	}

	public static DeporteDAO getInstancia() {
		if (instancia == null)
			instancia = new DeporteDAO();
		return instancia;
	}

	public Deporte findById(int id) throws ConexionException, SQLException {
		Connection conexion = AdminConexion.getInstancia().obtenerConexion();
		Deporte deporte = null;

		PreparedStatement pstm = conexion.prepareStatement("select * from deportes where id_deporte= ?",
				PreparedStatement.RETURN_GENERATED_KEYS);

		pstm.setInt(1, id);

		ResultSet rs = pstm.executeQuery();
		rs.next();

		deporte = new Deporte(rs.getString("titulo"), rs.getString("descripcion"), rs.getString("activo"));
		deporte.setCodDeporte(id);

		return deporte;
	}

	public Deporte save(Deporte deporte) throws ConexionException, SQLException {

		Connection conexion = AdminConexion.getInstancia().obtenerConexion();

		PreparedStatement pstm = conexion.prepareStatement("insert into deportes (titulo, descripcion) values(?,?)",
				PreparedStatement.RETURN_GENERATED_KEYS);

		pstm.setString(1, deporte.getTitulo());
		pstm.setString(2, deporte.getDescripcion());

		pstm.execute();
		ResultSet rs = pstm.getGeneratedKeys();
		rs.next();

		return deporte;
	}

	public void delete(Deporte deporte) throws SQLException, ConexionException {

		Connection conexion = AdminConexion.getInstancia().obtenerConexion();

		PreparedStatement pstm = conexion.prepareStatement("DELETE from deportes where id_deporte=?",
				PreparedStatement.RETURN_GENERATED_KEYS);
		pstm.setInt(1, deporte.getCodDeporte());

		pstm.execute();

	}

	public Deporte update(Deporte deporte) throws SQLException, ConexionException {

		Connection conexion = AdminConexion.getInstancia().obtenerConexion();

		PreparedStatement pstm = conexion.prepareStatement(
				"UPDATE deportes SET titulo=?, descripcion=? WHERE id_deporte=?",
				PreparedStatement.RETURN_GENERATED_KEYS);

		pstm.setString(1, deporte.getTitulo());
		pstm.setString(2, deporte.getDescripcion());
		pstm.setInt(3, deporte.getCodDeporte());

		pstm.execute();
		ResultSet rs = pstm.getGeneratedKeys();
		rs.next();

		return deporte;
	}

	public ArrayList<Deporte> findAll() throws ConexionException, SQLException {

		ArrayList<Deporte> deportes = new ArrayList<Deporte>();

		Connection conexion = AdminConexion.getInstancia().obtenerConexion();

		PreparedStatement pstm = conexion.prepareStatement("select * from deportes where activo='SI'",
				PreparedStatement.RETURN_GENERATED_KEYS);

		ResultSet rs = pstm.executeQuery();

		while (rs.next()) {
			Deporte d = new Deporte(rs.getString("titulo"), rs.getString("descripcion"), rs.getString("activo"));
			d.setCodDeporte(rs.getInt("id_deporte"));
			deportes.add(d);
		}
		return deportes;

	}
}
