package vista;

import java.awt.EventQueue;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultComboBoxModel;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.WindowConstants;

import controlador.Gimnasio;
import views.ActividadView;

public class BajaActividad extends JFrame {

	private JButton btnBaja;
	private JButton btnBuscarActividad;
	private JButton btnBuscarDeporte;

	private JButton btnBuscarOtro;
	private JButton btnNewButton;
	private JComboBox comboBox;
	private JPanel contentPane;
	private Icon icono;
	private ImageIcon imagen;
	private JLabel lblActividad;
	private JLabel lblDeporte;
	private JLabel lblDescripcion;
	private JLabel lblEstado;
	private Gimnasio sistema;
	private JTextField tfActividad;
	private JTextField tfDeporte;
	private JTextField tfDescripcion;

	/**
	 * Create the frame.
	 */
	public BajaActividad() {
		setTitle("Baja actividad");
		sistema = Gimnasio.getInstancia();

		setLocationRelativeTo(null);
		setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);

		setBounds(100, 100, 484, 222);
		getContentPane().setLayout(null);

		lblDeporte = new JLabel("Deporte:");
		lblDeporte.setHorizontalAlignment(SwingConstants.TRAILING);
		lblDeporte.setBounds(24, 33, 64, 14);
		getContentPane().add(lblDeporte);

		tfDeporte = new JTextField();
		tfDeporte.setBounds(111, 30, 307, 20);
		tfDeporte.setHorizontalAlignment(SwingConstants.RIGHT);
		getContentPane().add(tfDeporte);
		tfDeporte.setColumns(10);

		lblDescripcion = new JLabel("Descripci\u00F3n:");
		lblDescripcion.setHorizontalAlignment(SwingConstants.TRAILING);
		lblDescripcion.setBounds(5, 82, 83, 14);
		getContentPane().add(lblDescripcion);

		tfDescripcion = new JTextField();
		tfDescripcion.setBounds(111, 79, 307, 20);
		tfDescripcion.setHorizontalAlignment(SwingConstants.RIGHT);
		getContentPane().add(tfDescripcion);
		tfDescripcion.setColumns(10);

		btnBaja = new JButton("Baja");
		btnBaja.setBounds(275, 147, 143, 23);
		btnBaja.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				sistema.bajaActividad(Integer.parseInt(tfDeporte.getText()), Integer.parseInt(tfActividad.getText()),
						comboBox.getSelectedItem().toString());
				limpiarPantalla();

			}
		});
		getContentPane().add(btnBaja);

		btnBuscarDeporte = new JButton("");
		btnBuscarDeporte.setBounds(428, 29, 30, 23);

		imagen = new ImageIcon(ModificarActividad.class.getResource("/images/lupa.png"));
		icono = new ImageIcon(imagen.getImage().getScaledInstance(btnBuscarDeporte.getWidth(),
				btnBuscarDeporte.getHeight(), Image.SCALE_DEFAULT));

		btnBuscarDeporte.setIcon(icono);

		getContentPane().add(btnBuscarDeporte);

		lblActividad = new JLabel("Actividad:");
		lblActividad.setHorizontalAlignment(SwingConstants.TRAILING);
		lblActividad.setBounds(-12, 58, 100, 14);
		getContentPane().add(lblActividad);

		tfActividad = new JTextField();
		tfActividad.setHorizontalAlignment(SwingConstants.TRAILING);
		tfActividad.setBounds(111, 55, 307, 20);
		getContentPane().add(tfActividad);
		tfActividad.setColumns(10);

		btnBuscarActividad = new JButton();
		btnBuscarActividad.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ActividadView a = sistema.getActividadView(Integer.parseInt(tfDeporte.getText()),
						Integer.parseInt(tfActividad.getText()));
				if (a != null)
					mostrar(a);
			}
		});
		btnBuscarActividad.setBounds(428, 54, 30, 23);
		icono = new ImageIcon(imagen.getImage().getScaledInstance(btnBuscarActividad.getWidth(),
				btnBuscarActividad.getHeight(), Image.SCALE_DEFAULT));
		btnBuscarActividad.setIcon(icono);
		getContentPane().add(btnBuscarActividad);

		btnBuscarOtro = new JButton("Buscar otro");
		btnBuscarOtro.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				limpiarPantalla();
			}
		});
		btnBuscarOtro.setBounds(111, 147, 117, 23);
		getContentPane().add(btnBuscarOtro);

		lblEstado = new JLabel("Estado:");
		lblEstado.setHorizontalAlignment(SwingConstants.TRAILING);
		lblEstado.setBounds(42, 113, 46, 14);
		getContentPane().add(lblEstado);

		comboBox = new JComboBox();
		comboBox.setEnabled(false);
		comboBox.setModel(new DefaultComboBoxModel(new String[] { "Inactivo", "Activo" }));
		comboBox.setBounds(111, 110, 307, 20);
		getContentPane().add(comboBox);

		limpiarPantalla();

	}

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					BajaActividad frame = new BajaActividad();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	private void limpiarPantalla() {
		tfDescripcion.setVisible(false);
		btnBaja.setVisible(false);
		tfActividad.setEnabled(true);
		tfDeporte.setEnabled(true);
		lblDescripcion.setVisible(false);
		btnBuscarOtro.setVisible(false);
		lblEstado.setVisible(false);
		comboBox.setVisible(false);
	}

	private void mostrar(ActividadView a) {
		tfDescripcion.setText(a.getDescripcion());
		tfDescripcion.setVisible(true);
		btnBaja.setVisible(true);
		tfActividad.setEnabled(false);
		tfDeporte.setEnabled(false);
		lblDescripcion.setVisible(true);
		btnBuscarOtro.setVisible(true);
		lblEstado.setVisible(true);
		comboBox.setVisible(true);

	}
}
