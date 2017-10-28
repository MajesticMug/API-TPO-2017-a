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
import modelo.Abono;
import modelo.InscripcionNormal;
import modelo.Socio;

public class InscripcionNormalDAO {
	private static InscripcionNormalDAO instancia;

	private InscripcionNormalDAO() {
	}

	public static InscripcionNormalDAO getInstancia() {
		if (instancia == null)
			instancia = new InscripcionNormalDAO();
		return instancia;
	}

	public InscripcionNormal findById(int id) throws ConexionException, SQLException {
		Connection conexion = AdminConexion.getInstancia().obtenerConexion();
		InscripcionNormal inscripcion = null;

		PreparedStatement pstm = conexion.prepareStatement(
				"select i.id_abono as abono, i2.dni as dni from inscripciones i inner join inscripciones_normales i2 on i.id_inscripcion=i2.id_inscripcion where i.id_inscripcion=?",
				PreparedStatement.RETURN_GENERATED_KEYS);

		pstm.setInt(1, id);

		ResultSet rs = pstm.executeQuery();
		rs.next();

		Socio s = SocioDAO.getInstancia().findByDni(rs.getString("dni"));
		Abono a = AbonoDAO.getInstancia().findById(rs.getInt("abono"));

		inscripcion = new InscripcionNormal(a, s);

		return inscripcion;
	}

	public InscripcionNormal save(InscripcionNormal inscripcion) throws ConexionException, SQLException {

		Connection conexion = AdminConexion.getInstancia().obtenerConexion();

		PreparedStatement pstm = conexion.prepareStatement("insert into inscripciones (id_abono) values(?)",
				PreparedStatement.RETURN_GENERATED_KEYS);

		pstm.setInt(1, inscripcion.getAbono().getCodAbono());

		pstm.execute();
		ResultSet rs = pstm.getGeneratedKeys();
		rs.next();

		pstm = conexion.prepareStatement("insert into inscripciones_normales (dni) values (?)",
				PreparedStatement.RETURN_GENERATED_KEYS);

		pstm.setString(1, inscripcion.getSocio().getDni());
		pstm.execute();
		rs = pstm.getGeneratedKeys();
		rs.next();

		return inscripcion;
	}

	public void delete(InscripcionNormal inscripcion) throws SQLException, ConexionException {

		Connection conexion = AdminConexion.getInstancia().obtenerConexion();

		String query = "delete from inscripciones where id_inscripcion= " + inscripcion.getCodInscripcion();
		query += " delete from inscripciones_normales where id_inscripcion= " + inscripcion.getCodInscripcion();

		PreparedStatement pstm = conexion.prepareStatement(query, PreparedStatement.RETURN_GENERATED_KEYS);
		pstm.execute();

	}

	public ArrayList<InscripcionNormal> findAll() throws EmpleadoException, ConexionException, SQLException {

		ArrayList<InscripcionNormal> inscripciones = new ArrayList<InscripcionNormal>();

		Connection conexion = AdminConexion.getInstancia().obtenerConexion();

		PreparedStatement pstm = conexion.prepareStatement(
				"select i.id_abono as abono, i2.dni as dni from inscripciones i inner join inscripciones_normales i2 on i.id_inscripcion=i2.id_inscripcion",
				PreparedStatement.RETURN_GENERATED_KEYS);

		ResultSet rs = pstm.executeQuery();

		while (rs.next()) {
			Socio s = SocioDAO.getInstancia().findByDni(rs.getString("dni"));
			Abono a = AbonoDAO.getInstancia().findById(rs.getInt("abono"));

			inscripciones.add(new InscripcionNormal(a, s));

		}
		return inscripciones;

	}
}
