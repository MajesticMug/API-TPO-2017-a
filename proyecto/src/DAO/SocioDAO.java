package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import exceptions.ConexionException;
import exceptions.EmpleadoException;
import modelo.EmpleadoAdministrativo;
import modelo.Socio;

public class SocioDAO {
	private static SocioDAO instancia;

	private SocioDAO() {
	}

	public static SocioDAO getInstancia() {
		if (instancia == null)
			instancia = new SocioDAO();
		return instancia;
	}

	public Socio findByDni(String dni) throws ConexionException, SQLException {
		Connection conexion = AdminConexion.getInstancia().obtenerConexion();
		Socio socio = null;

		PreparedStatement pstm = conexion.prepareStatement("select * from socios where dni= ?",
				PreparedStatement.RETURN_GENERATED_KEYS);

		pstm.setString(1, dni);

		ResultSet rs = pstm.executeQuery();
		rs.next();

		socio = new Socio(dni, rs.getString("nombre"), rs.getString("apellido"), rs.getString("domicilio"),
				rs.getString("telefono"), rs.getString("mail"), rs.getString("activo"));

		return socio;
	}

	public Socio save(Socio socio) throws ConexionException, SQLException {

		Connection conexion = AdminConexion.getInstancia().obtenerConexion();

		PreparedStatement pstm = conexion.prepareStatement(
				"insert into socios(dni, nombre, apellido, domicilio, telefono, mail) values(?,?,?,?,?, ?)",
				PreparedStatement.RETURN_GENERATED_KEYS);

		pstm.setString(1, socio.getDni());
		pstm.setString(2, socio.getNombre());
		pstm.setString(3, socio.getApellido());
		pstm.setString(4, socio.getDomicilio());
		pstm.setString(5, socio.getTelefono());
		pstm.setString(6, socio.getMail());
		pstm.execute();
		ResultSet rs = pstm.getGeneratedKeys();
		rs.next();

		return socio;
	}

	public void delete(Socio socio) throws SQLException, ConexionException {

		Connection conexion = AdminConexion.getInstancia().obtenerConexion();

		PreparedStatement pstm = conexion.prepareStatement("DELETE from socios where dni=?",
				PreparedStatement.RETURN_GENERATED_KEYS);
		pstm.setString(1, socio.getDni());

		pstm.execute();

	}

	public Socio update(Socio socio) throws SQLException, ConexionException {

		Connection conexion = AdminConexion.getInstancia().obtenerConexion();

		PreparedStatement pstm = conexion.prepareStatement(
				"UPDATE socios SET nombre=?, apellido=?, domicilio=?, telefono=?, mail=? WHERE dni=?",
				PreparedStatement.RETURN_GENERATED_KEYS);

		pstm.setString(1, socio.getNombre());
		pstm.setString(2, socio.getApellido());
		pstm.setString(3, socio.getDomicilio());
		pstm.setString(4, socio.getTelefono());
		pstm.setString(5, socio.getMail());
		pstm.setString(6, socio.getDni());
		pstm.execute();
		ResultSet rs = pstm.getGeneratedKeys();
		rs.next();

		return socio;
	}

	public ArrayList<Socio> findAll() throws ConexionException, SQLException {

		ArrayList<Socio> socios = new ArrayList<Socio>();

		Connection conexion = AdminConexion.getInstancia().obtenerConexion();

		PreparedStatement pstm = conexion.prepareStatement("select * from socios",
				PreparedStatement.RETURN_GENERATED_KEYS);

		ResultSet rs = pstm.executeQuery();

		while (rs.next()) {
			socios.add(new Socio(rs.getString("dni"), rs.getString("nombre"), rs.getString("apellido"),
					rs.getString("domicilio"), rs.getString("telefono"), rs.getString("mail"), rs.getString("activo")));

		}
		return socios;

	}
}
