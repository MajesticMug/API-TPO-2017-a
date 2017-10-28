package vista;

import java.awt.EventQueue;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.DefaultComboBoxModel;
import javax.swing.Icon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import controlador.Gimnasio;
import views.ActividadView;
import views.SocioView;

import javax.swing.ImageIcon;

public class ModificarActividad extends JFrame {

	private JButton btnBuscarActividad;
	private JButton btnBuscarDeporte;
	private JButton btnModificar;

	private JButton btnNewButton;
	private JPanel contentPane;
	private Icon icono;
	private ImageIcon imagen;
	private JLabel lblActividad;
	private JLabel lblDeporte;
	private JLabel lblDescripcion;
	private Gimnasio sistema;
	private JTextField tfActividad;
	private JTextField tfDeporte;
	private JTextField tfDescripcion;

	public ModificarActividad() {
		setResizable(false);
		setTitle("Modificar actividad");
		sistema = Gimnasio.getInstancia();

		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

		setBounds(100, 100, 484, 199);
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

		btnModificar = new JButton("Modificar");
		btnModificar.setBounds(111, 119, 302, 23);
		btnModificar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				sistema.modificarActividad(Integer.parseInt(tfDeporte.getText()),
						Integer.parseInt(tfActividad.getText()), tfDescripcion.getText());
				JOptionPane.showMessageDialog(null, "Actividad modificada correctamente");
				limpiarPantalla();

			}
		});
		getContentPane().add(btnModificar);

		btnBuscarDeporte = new JButton("");
		btnBuscarDeporte.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
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

		limpiarPantalla();

	}


	private void limpiarPantalla() {
		tfDescripcion.setVisible(false);
		btnModificar.setVisible(false);
		tfActividad.setEnabled(true);
		tfDeporte.setEnabled(true);
		lblDescripcion.setVisible(false);
	}

	private void mostrar(ActividadView a) {
		tfDescripcion.setText(a.getDescripcion());
		tfDescripcion.setVisible(true);
		btnModificar.setVisible(true);
		tfActividad.setEnabled(false);
		tfDeporte.setEnabled(false);
		lblDescripcion.setVisible(true);

	}
}
