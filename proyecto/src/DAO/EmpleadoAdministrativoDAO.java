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

public class EmpleadoAdministrativoDAO {
	private static EmpleadoAdministrativoDAO instancia;

	private EmpleadoAdministrativoDAO() {
	}

	public static EmpleadoAdministrativoDAO getInstancia() {
		if (instancia == null)
			instancia = new EmpleadoAdministrativoDAO();
		return instancia;
	}

	public EmpleadoAdministrativo findByDni(String dni) throws ConexionException, EmpleadoException, SQLException {
		Connection conexion = AdminConexion.getInstancia().obtenerConexion();
		EmpleadoAdministrativo empleado = null;

		PreparedStatement pstm = conexion.prepareStatement(
				"select * from empleados e inner join administrativos a on e.dni=a.dni where e.dni= ?",
				PreparedStatement.RETURN_GENERATED_KEYS);

		pstm.setString(1, dni);

		ResultSet rs = pstm.executeQuery();
		rs.next();

		empleado = new EmpleadoAdministrativo(dni, rs.getString("nombre"), rs.getString("apellido"),
				rs.getString("domicilio"), rs.getString("telefono"), rs.getString("activo"), rs.getString("puesto"),
				rs.getFloat("sueldoBasico"), rs.getFloat("deducciones"), rs.getFloat("retenciones"));

		return empleado;
	}

	public EmpleadoAdministrativo save(EmpleadoAdministrativo empleado) throws ConexionException, SQLException {

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

		pstm = conexion.prepareStatement("insert into administrativos values (?,?,?,?,?)",
				PreparedStatement.RETURN_GENERATED_KEYS);

		pstm.setString(1, empleado.getDni());
		pstm.setString(2, empleado.getPuesto());
		pstm.setFloat(3, empleado.getSueldoBasico());
		pstm.setFloat(4, empleado.getDeducciones());
		pstm.setFloat(5, empleado.getRetenciones());
		pstm.execute();
		rs = pstm.getGeneratedKeys();
		rs.next();

		return empleado;
	}

	public void delete(EmpleadoAdministrativo empleado) throws SQLException, ConexionException {

		Connection conexion = AdminConexion.getInstancia().obtenerConexion();

		String query = "delete from administrativos where dni= " + empleado.getDni();
		query += " delete from empleados where dni= " + empleado.getDni();

		PreparedStatement pstm = conexion.prepareStatement(query, PreparedStatement.RETURN_GENERATED_KEYS);
		pstm.execute();

	}

	public EmpleadoAdministrativo update(EmpleadoAdministrativo empleado) throws SQLException, ConexionException {

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
				"UPDATE administrativos SET puesto=?, sueldoBasico=?, deducciones=?, retenciones=? WHERE dni=?",
				PreparedStatement.RETURN_GENERATED_KEYS);

		pstm.setString(1, empleado.getPuesto());
		pstm.setFloat(2, empleado.getSueldoBasico());
		pstm.setFloat(3, empleado.getDeducciones());
		pstm.setFloat(4, empleado.getRetenciones());
		pstm.setString(5, empleado.getDni());
		pstm.execute();
		rs = pstm.getGeneratedKeys();
		rs.next();
		return empleado;
	}

	public ArrayList<EmpleadoAdministrativo> findAll() throws EmpleadoException, ConexionException, SQLException {

		ArrayList<EmpleadoAdministrativo> empleados = new ArrayList<EmpleadoAdministrativo>();

		Connection conexion = AdminConexion.getInstancia().obtenerConexion();

		PreparedStatement pstm = conexion.prepareStatement(
				"select * from empleados e inner join administrativos p on e.dni=p.dni",
				PreparedStatement.RETURN_GENERATED_KEYS);

		ResultSet rs = pstm.executeQuery();

		while (rs.next()) {
			empleados.add(new EmpleadoAdministrativo(rs.getString("dni"), rs.getString("nombre"),
					rs.getString("apellido"), rs.getString("domicilio"), rs.getString("telefono"),
					rs.getString("activo"), rs.getString("puesto"), rs.getFloat("sueldoBasico"),
					rs.getFloat("deducciones"), rs.getFloat("retenciones")));

		}
		return empleados;

	}
}
