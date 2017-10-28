package vista;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import controlador.Gimnasio;
import views.EmpleadoView;

public class BajaEmpleado extends JFrame {

	private JButton btnBaja;

	private JButton btnBuscar;
	private JButton btnBuscarOtro;
	private JComboBox comboBox;
	private JPanel contentPane;
	private JLabel lblApellido;
	private JLabel lblDni;
	private JLabel lblEstado;
	private JLabel lblNombre;
	private Gimnasio sistema;
	private JTextField tfApellido;
	private JTextField tfDni;
	private JTextField tfNombre;

	/**
	 * Create the frame.
	 */
	public BajaEmpleado() {

		setTitle("Baja de socio");
		sistema = Gimnasio.getInstancia();

		setLocationRelativeTo(null);
		setResizable(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

		setBounds(100, 100, 458, 217);
		getContentPane().setLayout(null);

		lblDni = new JLabel("DNI:");
		lblDni.setHorizontalAlignment(SwingConstants.TRAILING);
		lblDni.setBounds(42, 33, 59, 14);
		getContentPane().add(lblDni);

		tfDni = new JTextField();
		tfDni.setBounds(111, 30, 307, 20);
		tfDni.setHorizontalAlignment(SwingConstants.RIGHT);
		getContentPane().add(tfDni);

		lblNombre = new JLabel("Nombre:");
		lblNombre.setHorizontalAlignment(SwingConstants.TRAILING);
		lblNombre.setBounds(23, 58, 78, 14);
		getContentPane().add(lblNombre);

		lblApellido = new JLabel("Apellido:");
		lblApellido.setHorizontalAlignment(SwingConstants.TRAILING);
		lblApellido.setBounds(23, 83, 78, 14);
		getContentPane().add(lblApellido);

		tfApellido = new JTextField();
		tfApellido.setEditable(false);
		tfApellido.setBounds(111, 80, 307, 20);
		tfApellido.setHorizontalAlignment(SwingConstants.RIGHT);
		getContentPane().add(tfApellido);

		btnBaja = new JButton("Dar de baja");
		btnBaja.setBounds(267, 142, 151, 23);

		btnBaja.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				sistema.bajaEmpleado(tfDni.getText(), comboBox.getSelectedItem().toString());
				limpiarPantalla();
			}
		});
		getContentPane().add(btnBaja);

		btnBuscar = new JButton("Buscar");
		btnBuscar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				EmpleadoView s = sistema.getEmpleadoView(tfDni.getText());
				if (s != null)
					mostrar(s);
			}
		});
		btnBuscar.setBounds(111, 54, 307, 23);
		getContentPane().add(btnBuscar);

		lblEstado = new JLabel("Estado:");
		lblEstado.setHorizontalAlignment(SwingConstants.TRAILING);
		lblEstado.setBounds(55, 114, 46, 14);
		getContentPane().add(lblEstado);

		comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(new String[] { "Inactivo" }));
		comboBox.setBounds(111, 111, 307, 20);
		getContentPane().add(comboBox);

		tfNombre = new JTextField();
		tfNombre.setEditable(false);
		tfNombre.setHorizontalAlignment(SwingConstants.RIGHT);
		tfNombre.setEnabled(true);
		tfNombre.setBounds(111, 55, 307, 20);
		getContentPane().add(tfNombre);

		btnBuscarOtro = new JButton("Buscar otro");
		btnBuscarOtro.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				limpiarPantalla();

			}
		});
		btnBuscarOtro.setBounds(111, 142, 126, 23);
		getContentPane().add(btnBuscarOtro);

		limpiarPantalla();

	}

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					BajaEmpleado frame = new BajaEmpleado();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	private void limpiarPantalla() {
		tfApellido.setVisible(false);
		lblNombre.setVisible(false);
		lblApellido.setVisible(false);
		btnBaja.setVisible(false);
		btnBuscar.setVisible(true);
		tfDni.setEnabled(true);
		comboBox.setVisible(false);
		lblEstado.setVisible(false);
		btnBuscarOtro.setVisible(false);
	}

	protected void mostrar(EmpleadoView s) {
		tfNombre.setVisible(true);
		tfApellido.setVisible(true);
		lblNombre.setVisible(true);
		lblApellido.setVisible(true);
		btnBaja.setVisible(true);
		btnBuscar.setVisible(false);
		btnBuscarOtro.setVisible(true);
		tfDni.setEnabled(false);

		tfNombre.setText(s.getNombre());
		tfApellido.setText(s.getApellido());
		comboBox.setVisible(true);
		lblEstado.setVisible(true);

	}
}
