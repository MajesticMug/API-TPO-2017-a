package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import exceptions.ConexionException;
import modelo.Abono;
import modelo.Empresa;
import modelo.InscripcionCorporativa;
import modelo.Socio;

public class InscripcionCorporativaDAO {
	private static InscripcionCorporativaDAO instancia;

	private InscripcionCorporativaDAO() {
	}

	public static InscripcionCorporativaDAO getInstancia() {
		if (instancia == null)
			instancia = new InscripcionCorporativaDAO();
		return instancia;
	}

	public InscripcionCorporativa findById(int id) throws ConexionException, SQLException {
		Connection conexion = AdminConexion.getInstancia().obtenerConexion();
		InscripcionCorporativa inscripcion = null;

		PreparedStatement pstm = conexion.prepareStatement(
				"select i.id_abono as abono, i2.cuit as cuit, i2.porc_descuento as descuento from inscripciones i inner join corporativas i2 on i.id_inscripcion=i2.id_inscripcion where i.id_inscripcion=?",
				PreparedStatement.RETURN_GENERATED_KEYS);

		pstm.setInt(1, id);

		ResultSet rs = pstm.executeQuery();
		rs.next();

		Empresa empresa = EmpresaDAO.getInstancia().findByCuit(rs.getString("cuit"));
		Abono abono = AbonoDAO.getInstancia().findById(rs.getInt("abono"));

		inscripcion = new InscripcionCorporativa(abono, empresa, LocalDate.now(), LocalDate.now(),
				rs.getFloat(("descuento")));

		return inscripcion;
	}

	public InscripcionCorporativa save(InscripcionCorporativa inscripcion) throws ConexionException, SQLException {

		Connection conexion = AdminConexion.getInstancia().obtenerConexion();

		PreparedStatement pstm = conexion.prepareStatement("insert into inscripciones (id_abono) values(?)",
				PreparedStatement.RETURN_GENERATED_KEYS);

		pstm.setInt(1, inscripcion.getAbono().getCodAbono());

		pstm.execute();
		ResultSet rs = pstm.getGeneratedKeys();
		rs.next();

		pstm = conexion.prepareStatement("insert into corporativas (cuit, porc_descuento) values (?, ?)",
				PreparedStatement.RETURN_GENERATED_KEYS);

		pstm.setString(1, inscripcion.getEmpresaConvenio().getCuit());
		pstm.setInt(2, (int) inscripcion.getDescuentoMasivo());
		pstm.execute();
		rs = pstm.getGeneratedKeys();
		rs.next();

		return inscripcion;
	}

	public void delete(InscripcionCorporativa inscripcion) throws SQLException, ConexionException {

		Connection conexion = AdminConexion.getInstancia().obtenerConexion();

		String query = "delete from inscripciones where id_inscripcion= " + inscripcion.getCodInscripcion();
		query += " delete from corporativas where id_inscripcion= " + inscripcion.getCodInscripcion();

		PreparedStatement pstm = conexion.prepareStatement(query, PreparedStatement.RETURN_GENERATED_KEYS);
		pstm.execute();

	}

	public ArrayList<InscripcionCorporativa> findAll() throws ConexionException, SQLException {

		ArrayList<InscripcionCorporativa> inscripciones = new ArrayList<InscripcionCorporativa>();

		Connection conexion = AdminConexion.getInstancia().obtenerConexion();

		PreparedStatement pstm = conexion.prepareStatement(
				"select i.id_abono as abono, i2.cuit as cuit, i2.porc_descuento as descuento from inscripciones i inner join corporativas i2 on i.id_inscripcion=i2.id_inscripcion",
				PreparedStatement.RETURN_GENERATED_KEYS);

		ResultSet rs = pstm.executeQuery();

		while (rs.next()) {
			Empresa empresa = EmpresaDAO.getInstancia().findByCuit(rs.getString("cuit"));
			Abono abono = AbonoDAO.getInstancia().findById(rs.getInt("abono"));

			inscripciones.add(new InscripcionCorporativa(abono, empresa, LocalDate.now(), LocalDate.now(),
					rs.getFloat(("descuento"))));

		}
		return inscripciones;

	}
}
