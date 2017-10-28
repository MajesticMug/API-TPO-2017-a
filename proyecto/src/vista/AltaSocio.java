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
import exceptions.SocioException;

public class AltaSocio extends JFrame {

	private JButton btnAlta;

	private JComboBox comboBox;
	private JPanel contentPane;
	private JLabel lblApellido;
	private JLabel lblDni;
	private JLabel lblDomicilio;
	private JLabel lblEstado;
	private JLabel lblMail;
	private JLabel lblNombre;
	private JLabel lblTelfono;
	private Gimnasio sistema;
	private JTextField tfApellido;
	private JTextField tfDni;
	private JTextField tfDomicilio;
	private JTextField tfMail;
	private JTextField tfNombre;
	private JTextField tfTelefono;

	/**
	 * Create the frame.
	 */
	public AltaSocio() {
		setTitle("Alta de nuevo socio");
		sistema = Gimnasio.getInstancia();

		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

		setBounds(100, 100, 451, 278);
		getContentPane().setLayout(null);

		lblDni = new JLabel("DNI:");
		lblDni.setHorizontalAlignment(SwingConstants.TRAILING);
		lblDni.setBounds(42, 33, 46, 14);
		getContentPane().add(lblDni);

		tfDni = new JTextField();
		tfDni.setBounds(111, 30, 307, 20);
		tfDni.setHorizontalAlignment(SwingConstants.RIGHT);
		getContentPane().add(tfDni);
		tfDni.setColumns(10);

		lblNombre = new JLabel("Nombre:");
		lblNombre.setHorizontalAlignment(SwingConstants.TRAILING);
		lblNombre.setBounds(23, 58, 65, 14);
		getContentPane().add(lblNombre);

		tfNombre = new JTextField();
		tfNombre.setBounds(111, 55, 307, 20);
		tfNombre.setHorizontalAlignment(SwingConstants.RIGHT);
		getContentPane().add(tfNombre);
		tfNombre.setColumns(10);

		lblApellido = new JLabel("Apellido:");
		lblApellido.setHorizontalAlignment(SwingConstants.TRAILING);
		lblApellido.setBounds(23, 83, 65, 14);
		getContentPane().add(lblApellido);

		tfApellido = new JTextField();
		tfApellido.setBounds(111, 80, 307, 20);
		tfApellido.setHorizontalAlignment(SwingConstants.RIGHT);
		getContentPane().add(tfApellido);
		tfApellido.setColumns(10);

		lblDomicilio = new JLabel("Domicilio:");
		lblDomicilio.setHorizontalAlignment(SwingConstants.TRAILING);
		lblDomicilio.setBounds(20, 108, 68, 14);
		getContentPane().add(lblDomicilio);

		tfDomicilio = new JTextField();
		tfDomicilio.setBounds(111, 105, 307, 20);
		tfDomicilio.setHorizontalAlignment(SwingConstants.RIGHT);
		getContentPane().add(tfDomicilio);
		tfDomicilio.setColumns(10);

		lblTelfono = new JLabel("Tel\u00E9fono:");
		lblTelfono.setHorizontalAlignment(SwingConstants.TRAILING);
		lblTelfono.setBounds(18, 133, 70, 14);
		getContentPane().add(lblTelfono);

		tfTelefono = new JTextField();
		tfTelefono.setBounds(111, 130, 307, 20);
		tfTelefono.setHorizontalAlignment(SwingConstants.RIGHT);
		getContentPane().add(tfTelefono);
		tfTelefono.setColumns(10);

		lblMail = new JLabel("Mail:");
		lblMail.setHorizontalAlignment(SwingConstants.TRAILING);
		lblMail.setBounds(42, 158, 46, 14);
		getContentPane().add(lblMail);

		tfMail = new JTextField();
		tfMail.setBounds(111, 155, 307, 20);
		tfMail.setHorizontalAlignment(SwingConstants.RIGHT);
		getContentPane().add(tfMail);
		tfMail.setColumns(10);

		lblEstado = new JLabel("Estado:");
		lblEstado.setHorizontalAlignment(SwingConstants.TRAILING);
		lblEstado.setBounds(27, 183, 61, 14);
		getContentPane().add(lblEstado);

		comboBox = new JComboBox();
		comboBox.setBounds(111, 180, 307, 20);
		comboBox.setModel(new DefaultComboBoxModel(new String[] { "Activo", "Inactivo" }));
		getContentPane().add(comboBox);

		btnAlta = new JButton("Alta");
		btnAlta.setBounds(111, 216, 302, 23);
		btnAlta.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try
				{
					sistema.altaSocio(tfDni.getText(), tfNombre.getText(), tfApellido.getText(), tfDomicilio.getText(),
							tfTelefono.getText(), tfMail.getText(), comboBox.getSelectedItem().toString());
					JOptionPane.showMessageDialog(null, "Socio creado correctamente");
					limpiarPantalla();
				} catch (SocioException e)
				{
					JOptionPane.showMessageDialog(null, e.getMessage());
				}
							}
		});
		getContentPane().add(btnAlta);

	}

	private void limpiarPantalla() {
		tfApellido.setText("");
		tfDni.setText("");
		tfDomicilio.setText("");
		tfMail.setText("");
		tfNombre.setText("");
		tfTelefono.setText("");
	}

}
