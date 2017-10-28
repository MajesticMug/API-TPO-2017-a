package views;

import java.time.LocalDate;

public class InscripcionCorporativaView {
	private float descuentoMasivo;
	private String empresa;
	private LocalDate finVigencia;
	private LocalDate inicioVigencia;

	public InscripcionCorporativaView(LocalDate inicioVigencia, LocalDate finVigencia, float descuentoMasivo,
			String empresa) {
		super();
		this.inicioVigencia = inicioVigencia;
		this.finVigencia = finVigencia;
		this.descuentoMasivo = descuentoMasivo;
		this.empresa = empresa;
	}

	public float getDescuentoMasivo() {
		return descuentoMasivo;
	}

	public String getEmpresa() {
		return empresa;
	}

	public LocalDate getFinVigencia() {
		return finVigencia;
	}

	public LocalDate getInicioVigencia() {
		return inicioVigencia;
	}

}
