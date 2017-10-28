package DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import exceptions.ConexionException;

public class AdminConexion {

	public static AdminConexion instancia;

	private AdminConexion() throws ConexionException {

		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
		} catch (ClassNotFoundException e) {
			throw new ConexionException("No es posible conectarse, consulte al administrador");
		}
	}

	public static AdminConexion getInstancia() throws ConexionException {
		if (instancia == null) {
			instancia = new AdminConexion();
		}
		return instancia;
	}

	public Connection obtenerConexion() throws ConexionException {
		Connection conexion = null;
		String url, usu, pwd;
		url = "jdbc:sqlserver://127.0.0.1"; // "\\dv:1433/AI4799__07"
		usu = "TP_Gimnasio";
		pwd = "123";
		try {
			conexion = DriverManager.getConnection(url, usu, pwd);
		} catch (SQLException e) {
			throw new ConexionException("Los datos de conexion ingresados no son validos");
		}
		return conexion;
	}
}
