package modelo;

import java.time.LocalDate;
import java.util.ArrayList;

import views.InscripcionCorporativaView;

public class InscripcionCorporativa extends Inscripcion {
	private float descuentoMasivo;
	private Empresa empresaConvenio;
	private LocalDate finVigencia;
	private LocalDate inicioVigencia;
	private ArrayList<InscripcionNormal> socios;

	public InscripcionCorporativa(Abono abono, Empresa empresa, LocalDate inicioVigencia, LocalDate finVigencia,
			float descuentoMasivo) {
		super(abono);
		this.inicioVigencia = inicioVigencia;
		this.finVigencia = finVigencia;
		this.descuentoMasivo = descuentoMasivo;
		socios = new ArrayList<InscripcionNormal>();
		empresaConvenio = empresa;

	}

	public InscripcionNormal addInscripcion(Socio socio) {
		InscripcionNormal inscripcion = new InscripcionNormal(getAbono(), socio);
		socio.addInscripcion(inscripcion);
		return inscripcion;
	}

	public boolean aplicaASocio(Socio socio) {
		return empresaConvenio.trabajaElSocio(socio);
	}

	public float getDescuentoMasivo() {
		return descuentoMasivo;
	}

	public Empresa getEmpresaConvenio() {
		return empresaConvenio;
	}

	public LocalDate getFinVigencia() {
		return finVigencia;
	}

	public LocalDate getInicioVigencia() {
		return inicioVigencia;
	}

	public ArrayList<InscripcionNormal> getSocios() {
		return socios;
	}

	public InscripcionCorporativaView getView() {
		return new InscripcionCorporativaView(inicioVigencia, finVigencia, descuentoMasivo,
				empresaConvenio.getRazonSocial());
	}

	@Override
	public boolean isInscripto(Abono abono, Socio socio) {
		return getAbono() == abono && socios.contains(socio);
	}

	public void modificar(LocalDate finVigencia, float descuentoMasivo) {
		this.finVigencia = finVigencia;
		this.descuentoMasivo = descuentoMasivo;

	}

	public void setDescuentoMasivo(float descuentoMasivo) {
		this.descuentoMasivo = descuentoMasivo;
	}

	public void setEmpresaConvenio(Empresa empresaConvenio) {
		this.empresaConvenio = empresaConvenio;
	}

	public void setFinVigencia(LocalDate finVigencia) {
		this.finVigencia = finVigencia;
	}

	public void setInicioVigencia(LocalDate inicioVigencia) {
		this.inicioVigencia = inicioVigencia;
	}

	public void setSocios(ArrayList<InscripcionNormal> socios) {
		this.socios = socios;
	}

}
