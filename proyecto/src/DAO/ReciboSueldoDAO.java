package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import exceptions.ConexionException;
import exceptions.EmpleadoException;
import modelo.EmpleadoAdministrativo;
import modelo.ReciboSueldo;

public class ReciboSueldoDAO {
	private static ReciboSueldoDAO instancia;

	private ReciboSueldoDAO() {
	}

	public static ReciboSueldoDAO getInstancia() {
		if (instancia == null)
			instancia = new ReciboSueldoDAO();
		return instancia;
	}

	public ReciboSueldo findById(int id) throws ConexionException, EmpleadoException, SQLException {
		Connection conexion = AdminConexion.getInstancia().obtenerConexion();
		ReciboSueldo reciboSueldo = null;

		PreparedStatement pstm = conexion.prepareStatement("select * from recibos_sueldos where id_recibo= ?",
				PreparedStatement.RETURN_GENERATED_KEYS);

		pstm.setInt(1, id);

		ResultSet rs = pstm.executeQuery();
		rs.next();

		reciboSueldo = new ReciboSueldo(rs.getFloat(("monto")));
		reciboSueldo.setCodRecibo(id);
		// LOCALDATE reciboSueldo.setFechaPago(rs.getDate("fechaPago"));
		return reciboSueldo;
	}

	public ReciboSueldo findByRecibo(String dni, int mes, int anio)
			throws ConexionException, EmpleadoException, SQLException {
		Connection conexion = AdminConexion.getInstancia().obtenerConexion();
		ReciboSueldo reciboSueldo = null;

		PreparedStatement pstm = conexion.prepareStatement(
				"select * from recibos_sueldos where dni=? AND DATEPART(month, fechaPago)=? AND DATEPART(year, fechaPago)=?",
				PreparedStatement.RETURN_GENERATED_KEYS);

		pstm.setString(1, dni);
		pstm.setInt(2, mes);
		pstm.setInt(3, anio);

		ResultSet rs = pstm.executeQuery();
		rs.next();

		reciboSueldo = new ReciboSueldo(rs.getFloat(("sueldo")));
		reciboSueldo.setCodRecibo(rs.getInt(rs.getInt("id_recibo")));
		// LOCALDATE reciboSueldo.setFechaPago(rs.getDate("fechaPago"));
		return reciboSueldo;
	}

	public ReciboSueldo save(ReciboSueldo reciboSueldo) throws ConexionException, SQLException {

		Connection conexion = AdminConexion.getInstancia().obtenerConexion();

		PreparedStatement pstm = conexion.prepareStatement(
				"insert into recibos_sueldos(dni, fechaPago, sueldo) values(?,GETDATE(),?)",
				PreparedStatement.RETURN_GENERATED_KEYS);

		pstm.setString(1, reciboSueldo.getEmpleado().getDni());
		pstm.setFloat(2, reciboSueldo.getMonto());

		pstm.execute();
		ResultSet rs = pstm.getGeneratedKeys();
		rs.next();

		reciboSueldo.setCodRecibo(rs.getInt("id_recibo"));

		return reciboSueldo;
	}

	public void delete(ReciboSueldo reciboSueldo) throws SQLException, ConexionException {

		Connection conexion = AdminConexion.getInstancia().obtenerConexion();

		PreparedStatement pstm = conexion.prepareStatement("DELETE from reciboSueldos where id_recibo=?",
				PreparedStatement.RETURN_GENERATED_KEYS);
		pstm.setInt(1, reciboSueldo.getCodRecibo());

		pstm.execute();

	}

	public ArrayList<ReciboSueldo> findAll() throws EmpleadoException, ConexionException, SQLException {

		ArrayList<ReciboSueldo> reciboSueldos = new ArrayList<ReciboSueldo>();

		Connection conexion = AdminConexion.getInstancia().obtenerConexion();

		PreparedStatement pstm = conexion.prepareStatement("select * from recibos_sueldos",
				PreparedStatement.RETURN_GENERATED_KEYS);

		ResultSet rs = pstm.executeQuery();

		while (rs.next()) {
			reciboSueldos.add(new ReciboSueldo(rs.getFloat("monto")));

		}
		return reciboSueldos;

	}
}
