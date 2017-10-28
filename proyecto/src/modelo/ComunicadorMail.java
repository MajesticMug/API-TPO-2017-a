package modelo;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;

import exceptions.MailException;

public class ComunicadorMail {
	private String asunto;
	private String cuerpo;
	private HashSet<Socio> destinatarios;
	private boolean enviado;
	private LocalDate fechaEnvio;
	private String para;

	public ComunicadorMail() {
		destinatarios = new HashSet<Socio>();
		para = "";
		asunto = "";
		cuerpo = "";
		enviado = false;

	}

	public void addDestinatario(Socio destinatario) {
		this.destinatarios.add(destinatario);

	}

	public void addDestinatarios(ArrayList<Socio> destinatarios) throws MailException {
		if (destinatarios.isEmpty())
			throw new MailException("No se encontraron coincidencias con su criterio de búsqueda");
		this.destinatarios.addAll(destinatarios);

	}

	public void enviar(String asunto, String cuerpo) throws MailException {
		if (destinatarios.isEmpty())
			throw new MailException("Ingrese al meonos un destinatario");
		if (cuerpo.equals(""))
			throw new MailException("El cuerpo del mail no puede estar vacío");
		this.asunto = asunto;
		this.cuerpo = cuerpo;
		// luego de enviar, limpia todo
		this.asunto = "";
		this.cuerpo = "";
		limpiarDestinatarios();

	}

	public String getAsunto() {
		return asunto;
	}

	public String getCuerpo() {
		return cuerpo;
	}

	public String getDestinatarios() {

		para = "";
		for (Socio socio : destinatarios) {
			if (para.equals(""))
				para += socio.getMail();
			else
				para += "; " + socio.getMail();

		}
		return para;
	}

	public LocalDate getFechaEnvio() {
		return fechaEnvio;
	}

	public void limpiarDestinatarios() {
		destinatarios.clear();
	}

	public boolean mailConContenido() {

		return !asunto.equals("") && !cuerpo.equals("");
	}

	public void setAsunto(String asunto) {
		this.asunto = asunto;
	}

	public void setCuerpo(String cuerpo) {
		this.cuerpo = cuerpo;
	}

	public void setFechaEnvio(LocalDate fechaEnvio) {
		this.fechaEnvio = fechaEnvio;
	}
}
