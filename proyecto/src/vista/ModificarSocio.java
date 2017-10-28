package vista;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import controlador.Gimnasio;
import views.SocioView;

public class ModificarSocio extends JFrame {

	private JButton btnBuscar;

	private JButton btnModificar;
	private JPanel contentPane;
	private JLabel lblApellido;
	private JLabel lblDni;
	private JLabel lblDomicilio;
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

	public ModificarSocio() {
		setTitle("Modificar socio");
		sistema = Gimnasio.getInstancia();

		setLocationRelativeTo(null);
		setResizable(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

		setBounds(100, 100, 448, 262);
		getContentPane().setLayout(null);

		lblDni = new JLabel("DNI:");
		lblDni.setBounds(42, 33, 59, 14);
		getContentPane().add(lblDni);

		tfDni = new JTextField();
		tfDni.setBounds(111, 30, 307, 20);
		tfDni.setHorizontalAlignment(SwingConstants.RIGHT);
		getContentPane().add(tfDni);

		lblNombre = new JLabel("Nombre:");
		lblNombre.setBounds(23, 58, 78, 14);
		getContentPane().add(lblNombre);

		tfNombre = new JTextField();
		tfNombre.setBounds(111, 55, 307, 20);
		tfNombre.setHorizontalAlignment(SwingConstants.RIGHT);
		getContentPane().add(tfNombre);

		lblApellido = new JLabel("Apellido:");
		lblApellido.setBounds(23, 83, 78, 14);
		getContentPane().add(lblApellido);

		tfApellido = new JTextField();
		tfApellido.setBounds(111, 80, 307, 20);
		tfApellido.setHorizontalAlignment(SwingConstants.RIGHT);
		getContentPane().add(tfApellido);

		lblDomicilio = new JLabel("Domicilio:");
		lblDomicilio.setBounds(20, 108, 81, 14);
		getContentPane().add(lblDomicilio);

		tfDomicilio = new JTextField();
		tfDomicilio.setBounds(111, 105, 307, 20);
		tfDomicilio.setHorizontalAlignment(SwingConstants.RIGHT);
		getContentPane().add(tfDomicilio);

		lblTelfono = new JLabel("Tel\u00E9fono:");
		lblTelfono.setBounds(18, 133, 83, 14);
		getContentPane().add(lblTelfono);

		tfTelefono = new JTextField();
		tfTelefono.setBounds(111, 130, 307, 20);
		tfTelefono.setHorizontalAlignment(SwingConstants.RIGHT);
		getContentPane().add(tfTelefono);

		lblMail = new JLabel("Mail:");
		lblMail.setBounds(42, 158, 59, 14);
		getContentPane().add(lblMail);

		tfMail = new JTextField();
		tfMail.setBounds(111, 155, 307, 20);
		tfMail.setHorizontalAlignment(SwingConstants.RIGHT);
		getContentPane().add(tfMail);

		btnModificar = new JButton("Modificar");
		btnModificar.setBounds(111, 198, 307, 23);

		btnModificar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				sistema.modificarSocio(tfDni.getText(), tfNombre.getText(), tfApellido.getText(), tfDomicilio.getText(),
						tfTelefono.getText(), tfMail.getText());
				JOptionPane.showMessageDialog(null, "Socio modificado correctamente");
				limpiarPantalla();
			}
		});
		getContentPane().add(btnModificar);

		btnBuscar = new JButton("Buscar");
		btnBuscar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				SocioView s = sistema.getSocioView(tfDni.getText());
				if (s != null)
					mostrar(s);
			}
		});
		btnBuscar.setBounds(111, 54, 307, 23);
		getContentPane().add(btnBuscar);

		limpiarPantalla();

	}

	
	private void limpiarPantalla() {
		tfNombre.setVisible(false);
		tfApellido.setVisible(false);
		tfDomicilio.setVisible(false);
		tfTelefono.setVisible(false);
		tfMail.setVisible(false);
		lblNombre.setVisible(false);
		lblApellido.setVisible(false);
		lblDomicilio.setVisible(false);
		lblTelfono.setVisible(false);
		lblMail.setVisible(false);
		btnModificar.setVisible(false);
		btnBuscar.setVisible(true);
		tfDni.setEnabled(true);
	}

	protected void mostrar(SocioView s) {
		tfNombre.setVisible(true);
		tfApellido.setVisible(true);
		tfDomicilio.setVisible(true);
		tfTelefono.setVisible(true);
		tfMail.setVisible(true);
		lblNombre.setVisible(true);
		lblApellido.setVisible(true);
		lblDomicilio.setVisible(true);
		lblTelfono.setVisible(true);
		lblMail.setVisible(true);
		btnModificar.setVisible(true);
		btnBuscar.setVisible(false);
		tfDni.setEnabled(false);

		tfNombre.setText(s.getNombre());
		tfApellido.setText(s.getApellido());
		tfDomicilio.setText(s.getDomicilio());
		tfTelefono.setText(s.getTelefono());
		tfMail.setText(s.getMail());

	}
}
