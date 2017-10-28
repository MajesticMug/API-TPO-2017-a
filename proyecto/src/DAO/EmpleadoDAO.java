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
import modelo.Empleado;
import modelo.EmpleadoAdministrativo;
import modelo.ProfesorFullTime;
import modelo.ProfesorParticular;

public class EmpleadoDAO {
	private static EmpleadoDAO instancia;

	private EmpleadoDAO() {
	}

	public static EmpleadoDAO getInstancia() {
		if (instancia == null)
			instancia = new EmpleadoDAO();
		return instancia;
	}

	public Empleado findByDni(String dni) throws ConexionException, EmpleadoException, SQLException {
		Empleado e = EmpleadoAdministrativoDAO.getInstancia().findByDni(dni);
		if (e == null)
			e = ProfesorParticularDAO.getInstancia().findByDni(dni);
		if (e == null)
			e = ProfesorFullTimeDAO.getInstancia().findByDni(dni);
		return e;

	}

	public void delete(Empleado empleado) throws SQLException, ConexionException {

		EmpleadoAdministrativoDAO.getInstancia().delete((EmpleadoAdministrativo) empleado);

		ProfesorParticularDAO.getInstancia().delete((ProfesorParticular) empleado);

		ProfesorFullTimeDAO.getInstancia().delete((ProfesorFullTime) empleado);

	}

	public Empleado update(Empleado empleado) throws SQLException, ConexionException {

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

		return empleado;
	}

	public ArrayList<Empleado> findAll() throws EmpleadoException, ConexionException, SQLException {

		ArrayList<Empleado> empleados = new ArrayList<Empleado>();

		empleados.addAll(EmpleadoAdministrativoDAO.getInstancia().findAll());
		empleados.addAll(ProfesorParticularDAO.getInstancia().findAll());
		empleados.addAll(ProfesorFullTimeDAO.getInstancia().findAll());
		return empleados;

	}
}
