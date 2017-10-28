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
import modelo.ProfesorFullTime;

public class ProfesorFullTimeDAO {
	private static ProfesorFullTimeDAO instancia;

	private ProfesorFullTimeDAO() {
	}

	public static ProfesorFullTimeDAO getInstancia() {
		if (instancia == null)
			instancia = new ProfesorFullTimeDAO();
		return instancia;
	}

	public ProfesorFullTime findByDni(String dni) throws ConexionException, EmpleadoException, SQLException {
		Connection conexion = AdminConexion.getInstancia().obtenerConexion();
		ProfesorFullTime empleado = null;

		PreparedStatement pstm = conexion.prepareStatement(
				"select * from empleados e inner join profesores_fulltime p on e.dni=p.dni where e.dni= ?",
				PreparedStatement.RETURN_GENERATED_KEYS);

		pstm.setString(1, dni);

		ResultSet rs = pstm.executeQuery();
		rs.next();

		empleado = new ProfesorFullTime(dni, rs.getString("nombre"), rs.getString("apellido"),
				rs.getString("domicilio"), rs.getString("telefono"), rs.getString("activo"),
				rs.getFloat("sueldoBasico"), rs.getFloat("deducciones"), rs.getFloat("retenciones"));

		return empleado;
	}

	public ProfesorFullTime save(ProfesorFullTime empleado) throws ConexionException, SQLException {

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

		pstm = conexion.prepareStatement("insert into profesores_fulltime values (?,?,?,?)",
				PreparedStatement.RETURN_GENERATED_KEYS);

		pstm.setString(1, empleado.getDni());
		pstm.setFloat(2, empleado.getSueldoBasico());
		pstm.setFloat(3, empleado.getDeducciones());
		pstm.setFloat(4, empleado.getRetenciones());
		pstm.execute();
		rs = pstm.getGeneratedKeys();
		rs.next();

		return empleado;
	}

	public void delete(ProfesorFullTime empleado) throws SQLException, ConexionException {

		Connection conexion = AdminConexion.getInstancia().obtenerConexion();

		String query = "delete from profesores_fulltime where dni= " + empleado.getDni();
		query += " delete from profesores where dni= " + empleado.getDni();
		query += " delete from empleados where dni= " + empleado.getDni();

		PreparedStatement pstm = conexion.prepareStatement(query, PreparedStatement.RETURN_GENERATED_KEYS);
		pstm.execute();

	}

	public ProfesorFullTime update(ProfesorFullTime empleado) throws SQLException, ConexionException {

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

		pstm = conexion.prepareStatement(
				"UPDATE profesores_fulltime SET sueldoBasico=?, deducciones=?, retenciones=? WHERE dni=?",
				PreparedStatement.RETURN_GENERATED_KEYS);

		pstm.setFloat(1, empleado.getSueldoBasico());
		pstm.setFloat(2, empleado.getDeducciones());
		pstm.setFloat(3, empleado.getRetenciones());
		pstm.setString(4, empleado.getDni());
		pstm.execute();
		rs = pstm.getGeneratedKeys();
		rs.next();
		return empleado;
	}

	public ArrayList<ProfesorFullTime> findAll() throws EmpleadoException, ConexionException, SQLException {

		ArrayList<ProfesorFullTime> profesores = new ArrayList<ProfesorFullTime>();

		Connection conexion = AdminConexion.getInstancia().obtenerConexion();

		PreparedStatement pstm = conexion.prepareStatement(
				"select * from empleados e inner join profesores_fulltime p on e.dni=p.dni",
				PreparedStatement.RETURN_GENERATED_KEYS);

		ResultSet rs = pstm.executeQuery();

		while (rs.next()) {
			profesores.add(new ProfesorFullTime(rs.getString("dni"), rs.getString("nombre"), rs.getString("apellido"),
					rs.getString("domicilio"), rs.getString("telefono"), rs.getString("activo"),
					rs.getFloat("sueldoBasico"), rs.getFloat("deducciones"), rs.getFloat("retenciones")));

		}
		return profesores;

	}
}
