package modelo;

public class ProfesorParticular extends Profesor {
	private int horasTrabajadas;
	private float valorHora;

	public ProfesorParticular(String dni, String nombre, String apellido, String domicilio, String telefono,
			String estado, float valorHora) {
		super(dni, nombre, apellido, domicilio, telefono, estado);
		this.valorHora = valorHora;
		horasTrabajadas = 0;
	}

	public float calcularSueldo() {
		int horas = horasTrabajadas;
		horasTrabajadas = 0;
		return horas * valorHora;

	}

	public int getHorasTrabajadas() {
		return horasTrabajadas;
	}

	public float getValorHora() {
		return valorHora;
	}

	public void setHorasTrabajadas(int horasTrabajadas) {
		this.horasTrabajadas = horasTrabajadas;
	}

	public void setValorHora(float valorHora) {
		this.valorHora = valorHora;
	}
	

}
