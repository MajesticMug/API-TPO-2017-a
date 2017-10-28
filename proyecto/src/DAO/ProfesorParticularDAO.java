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
import modelo.ProfesorParticular;

public class ProfesorParticularDAO {
	private static ProfesorParticularDAO instancia;

	private ProfesorParticularDAO() {
	}

	public static ProfesorParticularDAO getInstancia() {
		if (instancia == null)
			instancia = new ProfesorParticularDAO();
		return instancia;
	}

	public ProfesorParticular findByDni(String dni) throws ConexionException, EmpleadoException, SQLException {
		Connection conexion = AdminConexion.getInstancia().obtenerConexion();

		ProfesorParticular empleado = null;

		PreparedStatement pstm = conexion.prepareStatement(
				"select * from empleados e inner join profesores_particulares p on e.dni=p.dni where e.dni= ?",
				PreparedStatement.RETURN_GENERATED_KEYS);

		pstm.setString(1, dni);

		ResultSet rs = pstm.executeQuery();
		rs.next();

		empleado = new ProfesorParticular(dni, rs.getString("nombre"), rs.getString("apellido"),
				rs.getString("domicilio"), rs.getString("telefono"), rs.getString("activo"), rs.getFloat("valorHora"));

		return empleado;
	}

	public ProfesorParticular save(ProfesorParticular empleado) throws ConexionException, SQLException {

		Connection conexion = AdminConexion.getInstancia().obtenerConexion();

		PreparedStatement pstm = conexion.prepareStatement(
				"insert into empleados(dni, nombre, apellido, domicilio, telefono) values(?,?,?,?,?)",
				PreparedStatement.RETURN_GENERATED_KEYS);

		pstm.setString(1, empleado.getDni());
		pstm.setString(2, empleado.getNombre());
		pstm.setString(3, empleado.getApellido());
		pstm.setString(4, empleado.getDomicilio());
		pstm.setString(5, empleado.getTelefono());
		pstm.execute();
		ResultSet rs = pstm.getGeneratedKeys();
		rs.next();

		pstm = conexion.prepareStatement("insert into profesores values (?)", PreparedStatement.RETURN_GENERATED_KEYS);

		pstm.setString(1, empleado.getDni());
		pstm.execute();
		rs = pstm.getGeneratedKeys();
		rs.next();

		pstm = conexion.prepareStatement("insert into profesores_particulares (dni, valorHora) values (?,?)",
				PreparedStatement.RETURN_GENERATED_KEYS);

		pstm.setString(1, empleado.getDni());
		pstm.setFloat(2, empleado.getValorHora());

		pstm.execute();
		rs = pstm.getGeneratedKeys();
		rs.next();

		return empleado;
	}

	public void delete(ProfesorParticular empleado) throws SQLException, ConexionException {

		Connection conexion = AdminConexion.getInstancia().obtenerConexion();

		String query = "delete from profesores_particulares where dni= " + empleado.getDni();
		query += " delete from profesores where dni= " + empleado.getDni();
		query += " delete from empleados where dni= " + empleado.getDni();

		PreparedStatement pstm = conexion.prepareStatement(query, PreparedStatement.RETURN_GENERATED_KEYS);
		pstm.execute();

	}

	public ProfesorParticular update(ProfesorParticular empleado) throws SQLException, ConexionException {

		Connection conexion = AdminConexion.getInstancia().obtenerConexion();

		PreparedStatement pstm = conexion.prepareStatement(
				"UPDATE empleados SET nombre=?, apellido=?, domicilio=?, telefono=? WHERE dni=?",
				PreparedStatement.RETURN_GENERATED_KEYS);

		pstm.setString(1, empleado.getNombre());
		pstm.setString(2, empleado.getApellido());
		pstm.setString(3, empleado.getDomicilio());
		pstm.setString(4, empleado.getTelefono());
		pstm.setString(5, empleado.getDni());
		pstm.execute();
		ResultSet rs = pstm.getGeneratedKeys();
		rs.next();

		pstm = conexion.prepareStatement("UPDATE profesores_particulares SET valorHora=? WHERE dni=?",
				PreparedStatement.RETURN_GENERATED_KEYS);

		pstm.setFloat(1, empleado.getValorHora());
		pstm.setString(2, empleado.getDni());
		pstm.execute();
		rs = pstm.getGeneratedKeys();
		rs.next();
		return empleado;
	}

	public ArrayList<ProfesorParticular> findAll() throws EmpleadoException, ConexionException, SQLException {

		ArrayList<ProfesorParticular> profesores = new ArrayList<ProfesorParticular>();

		Connection conexion = AdminConexion.getInstancia().obtenerConexion();

		PreparedStatement pstm = conexion.prepareStatement(
				"select * from empleados e inner join profesores_particulares p on e.dni=p.dni",
				PreparedStatement.RETURN_GENERATED_KEYS);

		ResultSet rs = pstm.executeQuery();

		while (rs.next()) {
			profesores.add(new ProfesorParticular(rs.getString("dni"), rs.getString("nombre"), rs.getString("apellido"),
					rs.getString("domicilio"), rs.getString("telefono"), rs.getString("activo"),
					rs.getFloat("valorHora")));

		}
		return profesores;

	}
}
