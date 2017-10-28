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
import modelo.EscalaSalarial;

public class EscalaSalarialDAO {
	private static EscalaSalarialDAO instancia;

	private EscalaSalarialDAO() {
	}

	public static EscalaSalarialDAO getInstancia() {
		if (instancia == null)
			instancia = new EscalaSalarialDAO();
		return instancia;
	}

	public EscalaSalarial findById(int id) throws ConexionException, SQLException {
		Connection conexion = AdminConexion.getInstancia().obtenerConexion();
		EscalaSalarial escala = null;

		PreparedStatement pstm = conexion.prepareStatement("select * from escalas_salariales where id_escala= ?",
				PreparedStatement.RETURN_GENERATED_KEYS);

		pstm.setInt(1, id);

		ResultSet rs = pstm.executeQuery();
		rs.next();

		escala = new EscalaSalarial(rs.getFloat("sueldoBase"), rs.getFloat("sueldoHasta"), rs.getString("novedades"));
		escala.setCodEscalaSalarial(rs.getInt("id_escala"));

		return escala;
	}

	public EscalaSalarial save(EscalaSalarial escala) throws ConexionException, SQLException {

		Connection conexion = AdminConexion.getInstancia().obtenerConexion();

		PreparedStatement pstm = conexion.prepareStatement(
				"insert into escalas_salariales (novedades, sueldoBase, sueldoHasta) values(?, ? ,?)",
				PreparedStatement.RETURN_GENERATED_KEYS);

		pstm.setString(1, escala.getNovedades());
		pstm.setFloat(2, escala.getSueldoBase());
		pstm.setFloat(3, escala.getSueldoHasta());

		pstm.execute();
		ResultSet rs = pstm.getGeneratedKeys();
		rs.next();

		return escala;
	}

	public void delete(EscalaSalarial escala) throws SQLException, ConexionException {

		Connection conexion = AdminConexion.getInstancia().obtenerConexion();

		PreparedStatement pstm = conexion.prepareStatement("DELETE from escalas_salariales where id_escala=?",
				PreparedStatement.RETURN_GENERATED_KEYS);
		pstm.setInt(1, escala.getCodEscalaSalarial());

		pstm.execute();

	}

	public EscalaSalarial update(EscalaSalarial escala) throws SQLException, ConexionException {

		Connection conexion = AdminConexion.getInstancia().obtenerConexion();

		PreparedStatement pstm = conexion.prepareStatement(
				"UPDATE escalas SET novedades=?, sueldoBase=?, sueldoTope=? WHERE id_escala=?",
				PreparedStatement.RETURN_GENERATED_KEYS);

		pstm.setString(1, escala.getNovedades());
		pstm.setFloat(2, escala.getSueldoBase());
		pstm.setFloat(3, escala.getSueldoHasta());

		pstm.execute();
		ResultSet rs = pstm.getGeneratedKeys();
		rs.next();

		return escala;
	}

	public ArrayList<EscalaSalarial> findAll() throws ConexionException, SQLException {

		ArrayList<EscalaSalarial> escalas = new ArrayList<EscalaSalarial>();

		Connection conexion = AdminConexion.getInstancia().obtenerConexion();

		PreparedStatement pstm = conexion.prepareStatement("select * from escalas_salariales",
				PreparedStatement.RETURN_GENERATED_KEYS);

		ResultSet rs = pstm.executeQuery();

		while (rs.next()) {
			EscalaSalarial esc = new EscalaSalarial(rs.getFloat("sueldoBase"), rs.getFloat("sueldoHasta"),
					rs.getString("novedades"));
			esc.setCodEscalaSalarial(rs.getInt("id_escala"));
			escalas.add(esc);
		}
		return escalas;

	}
}
