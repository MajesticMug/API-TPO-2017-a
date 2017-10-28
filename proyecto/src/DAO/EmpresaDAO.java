package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import exceptions.ConexionException;

import modelo.Empresa;

public class EmpresaDAO {
	private static EmpresaDAO instancia;

	private EmpresaDAO() {
	}

	public static EmpresaDAO getInstancia() {
		if (instancia == null)
			instancia = new EmpresaDAO();
		return instancia;
	}

	public Empresa findByCuit(String cuit) throws ConexionException, SQLException {
		Connection conexion = AdminConexion.getInstancia().obtenerConexion();
		Empresa empresa = null;

		PreparedStatement pstm = conexion.prepareStatement("select * from empresas where cuit= ?",
				PreparedStatement.RETURN_GENERATED_KEYS);

		pstm.setString(1, cuit);

		ResultSet rs = pstm.executeQuery();
		rs.next();

		empresa = new Empresa(cuit, rs.getString("razonSocial"), rs.getString("activo"));

		return empresa;
	}

	public Empresa save(Empresa empresa) throws ConexionException, SQLException {

		Connection conexion = AdminConexion.getInstancia().obtenerConexion();

		PreparedStatement pstm = conexion.prepareStatement("insert into empresas (cuit, razonSocial) values(?,?)",
				PreparedStatement.RETURN_GENERATED_KEYS);

		pstm.setString(1, empresa.getCuit());
		pstm.setString(2, empresa.getRazonSocial());

		pstm.execute();
		ResultSet rs = pstm.getGeneratedKeys();
		rs.next();

		return empresa;
	}

	public void delete(Empresa empresa) throws SQLException, ConexionException {

		Connection conexion = AdminConexion.getInstancia().obtenerConexion();

		PreparedStatement pstm = conexion.prepareStatement("DELETE from empresas where cuit=?",
				PreparedStatement.RETURN_GENERATED_KEYS);
		pstm.setString(1, empresa.getCuit());

		pstm.execute();

	}

	public Empresa update(Empresa empresa) throws SQLException, ConexionException {

		Connection conexion = AdminConexion.getInstancia().obtenerConexion();

		PreparedStatement pstm = conexion.prepareStatement("UPDATE empresas SET razonSocial=? WHERE cuit=?",
				PreparedStatement.RETURN_GENERATED_KEYS);

		pstm.setString(1, empresa.getRazonSocial());
		pstm.setString(2, empresa.getCuit());

		pstm.execute();
		ResultSet rs = pstm.getGeneratedKeys();
		rs.next();

		return empresa;
	}

	public ArrayList<Empresa> findAll() throws ConexionException, SQLException {

		ArrayList<Empresa> empresas = new ArrayList<Empresa>();

		Connection conexion = AdminConexion.getInstancia().obtenerConexion();

		PreparedStatement pstm = conexion.prepareStatement("select * from empresas where activo='SI'",
				PreparedStatement.RETURN_GENERATED_KEYS);

		ResultSet rs = pstm.executeQuery();

		while (rs.next()) {
			empresas.add(new Empresa(rs.getString("cuit"), rs.getString("razonSocial"), rs.getString("activo")));

		}
		return empresas;

	}
}
