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
import views.DeporteView;

public class BajaDeporte extends JFrame {

	private JButton btnBaja;

	private JButton btnBuscar;
	private JButton btnBuscarOtro;
	private JComboBox comboBox;
	private JPanel contentPane;
	private JLabel lblCod;
	private JLabel lblDescripcion;
	private JLabel lblEstado;
	private JLabel lblTitulo;
	private Gimnasio sistema;
	private JTextField tfCod;
	private JTextField tfDescripcion;
	private JTextField tfTitulo;

	/**
	 * Create the frame.
	 */
	public BajaDeporte() {
		setTitle("Baja de deporte");
		sistema = Gimnasio.getInstancia();

		setLocationRelativeTo(null);
		setResizable(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

		setBounds(100, 100, 458, 217);
		getContentPane().setLayout(null);

		lblCod = new JLabel("Codigo:");
		lblCod.setHorizontalAlignment(SwingConstants.TRAILING);
		lblCod.setBounds(42, 33, 59, 14);
		getContentPane().add(lblCod);

		tfCod = new JTextField();
		tfCod.setBounds(111, 30, 307, 20);
		tfCod.setHorizontalAlignment(SwingConstants.RIGHT);
		getContentPane().add(tfCod);

		lblTitulo = new JLabel("T\u00EDtulo:");
		lblTitulo.setHorizontalAlignment(SwingConstants.TRAILING);
		lblTitulo.setBounds(23, 58, 78, 14);
		getContentPane().add(lblTitulo);

		lblDescripcion = new JLabel("Descripci\u00F3n:");
		lblDescripcion.setHorizontalAlignment(SwingConstants.TRAILING);
		lblDescripcion.setBounds(23, 83, 78, 14);
		getContentPane().add(lblDescripcion);

		tfDescripcion = new JTextField();
		tfDescripcion.setEditable(false);
		tfDescripcion.setBounds(111, 80, 307, 20);
		tfDescripcion.setHorizontalAlignment(SwingConstants.RIGHT);
		getContentPane().add(tfDescripcion);

		btnBaja = new JButton("Dar de baja");
		btnBaja.setBounds(267, 142, 151, 23);

		btnBaja.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				sistema.bajaDeporte(Integer.parseInt(tfCod.getText()), comboBox.getSelectedItem().toString());
				limpiarPantalla();
			}
		});
		getContentPane().add(btnBaja);

		btnBuscar = new JButton("Buscar");
		btnBuscar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				DeporteView d = sistema.getDeporteView(Integer.parseInt(tfCod.getText()));
				if (d != null)
					mostrar(d);
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

		tfTitulo = new JTextField();
		tfTitulo.setEditable(false);
		tfTitulo.setHorizontalAlignment(SwingConstants.RIGHT);
		tfTitulo.setEnabled(true);
		tfTitulo.setBounds(111, 55, 307, 20);
		getContentPane().add(tfTitulo);

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
					BajaDeporte frame = new BajaDeporte();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	private void limpiarPantalla() {
		tfDescripcion.setVisible(false);
		lblTitulo.setVisible(false);
		lblDescripcion.setVisible(false);
		tfTitulo.setVisible(false);
		btnBaja.setVisible(false);
		btnBuscar.setVisible(true);
		btnBuscarOtro.setVisible(false);
		tfCod.setEnabled(true);
		comboBox.setVisible(false);
		lblEstado.setVisible(false);
	}

	protected void mostrar(DeporteView d) {
		tfTitulo.setVisible(true);
		tfDescripcion.setVisible(true);
		lblTitulo.setVisible(true);
		lblDescripcion.setVisible(true);
		btnBaja.setVisible(true);
		btnBuscar.setVisible(false);
		tfCod.setEnabled(false);
		btnBuscarOtro.setVisible(true);

		tfTitulo.setText(d.getTitulo());
		tfDescripcion.setText(d.getDescripcion());
		comboBox.setVisible(true);
		lblEstado.setVisible(true);

	}
}
