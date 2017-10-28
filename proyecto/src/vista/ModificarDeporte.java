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
import javax.swing.border.EmptyBorder;

import controlador.Gimnasio;
import views.DeporteView;

public class ModificarDeporte extends JFrame {

	private JButton btnBuscar;
	private JButton btnModificar;
	private JPanel contentPane;
	private JLabel lblCodigo;
	private JLabel lblDescripcin;
	private JLabel lblTitulo;
	private Gimnasio sistema;
	private JTextField tfCodigo;
	private JTextField tfDescripcion;
	private JTextField tfTitulo;

	public ModificarDeporte() {
		sistema = Gimnasio.getInstancia();
		setResizable(false);
		setTitle("Modificar deporte");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 470, 177);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		lblCodigo = new JLabel("C\u00F3digo:");
		lblCodigo.setHorizontalAlignment(SwingConstants.TRAILING);
		lblCodigo.setBounds(31, 38, 62, 14);
		contentPane.add(lblCodigo);

		tfCodigo = new JTextField();
		tfCodigo.setHorizontalAlignment(SwingConstants.TRAILING);
		tfCodigo.setBounds(98, 35, 361, 20);
		contentPane.add(tfCodigo);
		tfCodigo.setColumns(10);

		lblTitulo = new JLabel("T\u00EDtulo:");
		lblTitulo.setHorizontalAlignment(SwingConstants.TRAILING);
		lblTitulo.setBounds(38, 63, 55, 14);
		contentPane.add(lblTitulo);

		tfTitulo = new JTextField();
		tfTitulo.setBounds(98, 60, 361, 20);
		tfTitulo.setHorizontalAlignment(SwingConstants.TRAILING);
		contentPane.add(tfTitulo);
		tfTitulo.setColumns(10);

		lblDescripcin = new JLabel("Descripci\u00F3n:");
		lblDescripcin.setHorizontalAlignment(SwingConstants.TRAILING);
		lblDescripcin.setBounds(10, 88, 83, 14);
		contentPane.add(lblDescripcin);

		tfDescripcion = new JTextField();
		tfDescripcion.setBounds(98, 85, 361, 20);
		tfDescripcion.setHorizontalAlignment(SwingConstants.TRAILING);
		contentPane.add(tfDescripcion);
		tfDescripcion.setColumns(10);

		btnModificar = new JButton("Modificar");
		btnModificar.setBounds(98, 116, 361, 23);
		btnModificar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				sistema.modificarDeporte(Integer.parseInt(tfCodigo.getText()), tfTitulo.getText(),
						tfDescripcion.getText());
				JOptionPane.showMessageDialog(null, "Deporte modificado correctamente");
				limpiarPantalla();
			}
		});
		contentPane.add(btnModificar);

		btnBuscar = new JButton("Buscar");
		btnBuscar.setBounds(98, 59, 361, 23);
		contentPane.add(btnBuscar);

		btnBuscar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DeporteView d = sistema.getDeporteView(Integer.parseInt(tfCodigo.getText()));
				if (d != null)
					mostrar(d);
			}
		});

		limpiarPantalla();

	}


	private void limpiarPantalla() {
		tfTitulo.setVisible(false);
		tfDescripcion.setVisible(false);
		btnModificar.setVisible(false);
		lblDescripcin.setVisible(false);
		lblTitulo.setVisible(false);
		lblCodigo.setVisible(true);
		tfCodigo.setVisible(true);
		btnBuscar.setVisible(true);
		tfCodigo.setEnabled(true);
	}

	protected void mostrar(DeporteView d) {
		tfTitulo.setVisible(true);
		tfTitulo.setText(d.getTitulo());
		tfDescripcion.setVisible(true);
		tfDescripcion.setText(d.getDescripcion());
		btnModificar.setVisible(true);
		lblDescripcin.setVisible(true);
		lblTitulo.setVisible(true);
		tfCodigo.setEnabled(false);
		btnBuscar.setVisible(false);
	}

}
