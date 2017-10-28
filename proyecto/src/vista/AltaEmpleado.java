package vista;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import controlador.Gimnasio;
import javax.swing.JSeparator;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;

public class AltaEmpleado extends JFrame {

	private JButton btnAlta;

	private JComboBox comboBox;
	private JComboBox comboTipo;
	private JPanel contentPane;
	private JLabel lblApellido;
	private JLabel lblDeducciones;
	private JLabel lblDni;
	private JLabel lblDomicilio;
	private JLabel lblEstado;
	private JLabel lblNombre;
	private JLabel lblPuesto;
	private JLabel lblRetenciones;
	private JLabel lblSueldoBasico;
	private JLabel lblTelfono;
	private JLabel lblTipo;
	private JLabel lblValorHora;
	private JSeparator separator;
	private Gimnasio sistema;
	private JTextField tfApellido;
	private JTextField tfDeducciones;
	private JTextField tfDni;
	private JTextField tfDomicilio;
	private JTextField tfNombre;
	private JTextField tfPuesto;
	private JTextField tfRetenciones;
	private JTextField tfSueldoBasico;
	private JTextField tfTelefono;
	private JTextField tfValorHora;

	/**
	 * Create the frame.
	 */
	public AltaEmpleado() {
		setTitle("Alta de nuevo empleado");
		sistema = Gimnasio.getInstancia();

		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

		setBounds(100, 100, 451, 363);
		getContentPane().setLayout(null);

		lblDni = new JLabel("DNI:");
		lblDni.setHorizontalAlignment(SwingConstants.TRAILING);
		lblDni.setBounds(34, 54, 46, 14);
		getContentPane().add(lblDni);

		tfDni = new JTextField();
		tfDni.setBounds(103, 51, 307, 20);
		tfDni.setHorizontalAlignment(SwingConstants.TRAILING);
		getContentPane().add(tfDni);
		tfDni.setColumns(10);

		lblNombre = new JLabel("Nombre:");
		lblNombre.setHorizontalAlignment(SwingConstants.TRAILING);
		lblNombre.setBounds(15, 79, 65, 14);
		getContentPane().add(lblNombre);

		tfNombre = new JTextField();
		tfNombre.setBounds(103, 76, 307, 20);
		tfNombre.setHorizontalAlignment(SwingConstants.TRAILING);
		getContentPane().add(tfNombre);
		tfNombre.setColumns(10);

		lblApellido = new JLabel("Apellido:");
		lblApellido.setHorizontalAlignment(SwingConstants.TRAILING);
		lblApellido.setBounds(15, 104, 65, 14);
		getContentPane().add(lblApellido);

		tfApellido = new JTextField();
		tfApellido.setBounds(103, 101, 307, 20);
		tfApellido.setHorizontalAlignment(SwingConstants.TRAILING);
		getContentPane().add(tfApellido);
		tfApellido.setColumns(10);

		lblDomicilio = new JLabel("Domicilio:");
		lblDomicilio.setHorizontalAlignment(SwingConstants.TRAILING);
		lblDomicilio.setBounds(12, 129, 68, 14);
		getContentPane().add(lblDomicilio);

		tfDomicilio = new JTextField();
		tfDomicilio.setBounds(103, 126, 307, 20);
		tfDomicilio.setHorizontalAlignment(SwingConstants.TRAILING);
		getContentPane().add(tfDomicilio);
		tfDomicilio.setColumns(10);

		lblTelfono = new JLabel("Tel\u00E9fono:");
		lblTelfono.setHorizontalAlignment(SwingConstants.TRAILING);
		lblTelfono.setBounds(10, 154, 70, 14);
		getContentPane().add(lblTelfono);

		tfTelefono = new JTextField();
		tfTelefono.setBounds(103, 151, 307, 20);
		tfTelefono.setHorizontalAlignment(SwingConstants.TRAILING);
		getContentPane().add(tfTelefono);
		tfTelefono.setColumns(10);

		lblEstado = new JLabel("Estado:");
		lblEstado.setHorizontalAlignment(SwingConstants.TRAILING);
		lblEstado.setBounds(15, 244, 65, 14);
		getContentPane().add(lblEstado);

		comboBox = new JComboBox();
		comboBox.setBounds(103, 241, 307, 20);
		comboBox.setModel(new DefaultComboBoxModel(new String[] { "Activo", "Inactivo" }));
		getContentPane().add(comboBox);

		btnAlta = new JButton("Alta");
		btnAlta.setBounds(99, 277, 302, 23);
		btnAlta.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				switch (comboTipo.getSelectedIndex()) {
				case 0:
					sistema.altaEmpleadoAdministrativo(tfDni.getText(), tfNombre.getText(), tfApellido.getText(),
							tfDomicilio.getText(), tfTelefono.getText(), comboBox.getSelectedItem().toString(),
							tfPuesto.getText(), Float.parseFloat(tfSueldoBasico.getText()),
							Float.parseFloat(tfDeducciones.getText()), Float.parseFloat(tfRetenciones.getText()));
					limpiarTextFields();
					break;
				case 1:
					sistema.altaProfesorFullTime(tfDni.getText(), tfNombre.getText(), tfApellido.getText(),
							tfDomicilio.getText(), tfTelefono.getText(), comboBox.getSelectedItem().toString(),
							Float.parseFloat(tfSueldoBasico.getText()), Float.parseFloat(tfDeducciones.getText()),
							Float.parseFloat(tfRetenciones.getText()));
					limpiarTextFields();
					break;
				case 2:
					sistema.altaProfesorParticular(tfDni.getText(), tfNombre.getText(), tfApellido.getText(),
							tfDomicilio.getText(), tfTelefono.getText(), comboBox.getSelectedItem().toString(),
							Float.parseFloat(tfValorHora.getText()));
					limpiarTextFields();
					break;
				}
				JOptionPane.showMessageDialog(null, "Empleado creado correctamente");
				limpiarPantalla();

			}
		});
		getContentPane().add(btnAlta);

		comboTipo = new JComboBox();
		comboTipo.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent arg0) {

				switch (comboTipo.getSelectedIndex()) {
				case 0:
					mostrarAdministrativo();
					limpiarTextFields();
					break;
				case 1:
					mostrarFullTime();
					limpiarTextFields();
					break;
				case 2:
					mostrarParticular();
					limpiarTextFields();
					break;
				}

			}

		});
		comboTipo.setModel(new DefaultComboBoxModel(
				new String[] { "Empleado administrativo", "Profesor full time", "Profesor particular" }));
		comboTipo.setBounds(103, 11, 307, 20);
		getContentPane().add(comboTipo);

		separator = new JSeparator();
		separator.setBounds(0, 42, 442, 10);
		getContentPane().add(separator);

		lblTipo = new JLabel("Tipo:");
		lblTipo.setHorizontalAlignment(SwingConstants.TRAILING);
		lblTipo.setBounds(34, 14, 46, 14);
		getContentPane().add(lblTipo);

		lblPuesto = new JLabel("Puesto:");
		lblPuesto.setHorizontalAlignment(SwingConstants.TRAILING);
		lblPuesto.setBounds(34, 179, 46, 14);
		getContentPane().add(lblPuesto);

		tfPuesto = new JTextField();
		tfPuesto.setHorizontalAlignment(SwingConstants.TRAILING);
		tfPuesto.setBounds(103, 176, 307, 20);
		getContentPane().add(tfPuesto);
		tfPuesto.setColumns(10);

		lblSueldoBasico = new JLabel("Sueldo basico");
		lblSueldoBasico.setHorizontalAlignment(SwingConstants.CENTER);
		lblSueldoBasico.setBounds(103, 203, 95, 14);
		getContentPane().add(lblSueldoBasico);

		tfSueldoBasico = new JTextField();
		tfSueldoBasico.setHorizontalAlignment(SwingConstants.TRAILING);
		tfSueldoBasico.setBounds(103, 216, 95, 20);
		getContentPane().add(tfSueldoBasico);
		tfSueldoBasico.setColumns(10);

		lblRetenciones = new JLabel("Retenciones");
		lblRetenciones.setHorizontalAlignment(SwingConstants.CENTER);
		lblRetenciones.setBounds(330, 203, 80, 14);
		getContentPane().add(lblRetenciones);

		tfRetenciones = new JTextField();
		tfRetenciones.setHorizontalAlignment(SwingConstants.TRAILING);
		tfRetenciones.setColumns(10);
		tfRetenciones.setBounds(330, 216, 80, 20);
		getContentPane().add(tfRetenciones);

		lblDeducciones = new JLabel("Deducciones");
		lblDeducciones.setHorizontalAlignment(SwingConstants.CENTER);
		lblDeducciones.setBounds(219, 203, 80, 14);
		getContentPane().add(lblDeducciones);

		tfDeducciones = new JTextField();
		tfDeducciones.setHorizontalAlignment(SwingConstants.TRAILING);
		tfDeducciones.setColumns(10);
		tfDeducciones.setBounds(219, 216, 80, 20);
		getContentPane().add(tfDeducciones);

		lblValorHora = new JLabel("Valor hora:");
		lblValorHora.setHorizontalAlignment(SwingConstants.TRAILING);
		lblValorHora.setBounds(0, 219, 80, 14);
		getContentPane().add(lblValorHora);

		tfValorHora = new JTextField();
		tfValorHora.setHorizontalAlignment(SwingConstants.TRAILING);
		tfValorHora.setBounds(103, 216, 307, 20);
		getContentPane().add(tfValorHora);
		tfValorHora.setColumns(10);

		mostrarAdministrativo();
	}

	private void limpiarPantalla() {
		comboBox.setSelectedItem(0);
		comboTipo.setSelectedItem(0);
		tfApellido.setText("");
		tfDeducciones.setText("");
		tfDni.setText("");
		tfDomicilio.setText("");
		tfNombre.setText("");
		tfPuesto.setText("");
		tfRetenciones.setText("");
		tfSueldoBasico.setText("");
		tfTelefono.setText("");
		tfValorHora.setText("");
	}

	private void limpiarTextFields() {
		tfDni.setText("");
		tfNombre.setText("");
		tfApellido.setText("");
		tfDomicilio.setText("");
		tfTelefono.setText("");
		tfPuesto.setText("");
		tfValorHora.setText("");
		tfSueldoBasico.setText("");
		tfRetenciones.setText("");
		tfDeducciones.setText("");

	}

	private void mostrarAdministrativo() {
		lblSueldoBasico.setVisible(true);
		tfSueldoBasico.setVisible(true);
		lblDeducciones.setVisible(true);
		tfDeducciones.setVisible(true);
		lblRetenciones.setVisible(true);
		tfRetenciones.setVisible(true);
		lblPuesto.setVisible(true);
		tfPuesto.setVisible(true);

		lblValorHora.setVisible(false);
		tfValorHora.setVisible(false);

	}

	private void mostrarFullTime() {
		lblSueldoBasico.setVisible(true);
		tfSueldoBasico.setVisible(true);
		lblDeducciones.setVisible(true);
		tfDeducciones.setVisible(true);
		lblRetenciones.setVisible(true);
		tfRetenciones.setVisible(true);

		lblPuesto.setVisible(false);
		tfPuesto.setVisible(false);

		lblValorHora.setVisible(false);
		tfValorHora.setVisible(false);

	}

	private void mostrarParticular() {
		lblSueldoBasico.setVisible(false);
		tfSueldoBasico.setVisible(false);
		lblDeducciones.setVisible(false);
		tfDeducciones.setVisible(false);
		lblRetenciones.setVisible(false);
		tfRetenciones.setVisible(false);
		lblPuesto.setVisible(false);
		tfPuesto.setVisible(false);

		lblValorHora.setVisible(true);
		tfValorHora.setVisible(true);

	}
}
