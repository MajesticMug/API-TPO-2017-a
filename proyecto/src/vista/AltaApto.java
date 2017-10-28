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
import javax.swing.JTextArea;
import javax.swing.JEditorPane;
import javax.swing.JScrollPane;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Date;
import java.awt.Color;
import com.toedter.calendar.JDateChooser;

public class AltaApto extends JFrame {

	private JButton btnAlta;

	private JButton btnBuscar;
	private JButton btnBuscarOtro;
	private JComboBox comboBox;
	private JPanel contentPane;
	private JDateChooser dateChooser;
	private JEditorPane editorPane;
	private JLabel lblCount;
	private JLabel lblDni;
	private JLabel lblEstado;
	private JLabel lblFecha;
	private JLabel lblObservaciones;
	private JLabel lblProfesional;
	private JScrollPane scrollPane;
	private Gimnasio sistema;
	private JTextField tfDni;
	private JTextField tfProfesional;

	/**
	 * Create the frame.
	 */
	public AltaApto() {

		setTitle("Alta de Apto M\u00E9dico");
		sistema = Gimnasio.getInstancia();

		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

		setBounds(100, 100, 451, 358);
		getContentPane().setLayout(null);

		lblDni = new JLabel("DNI del socio:");
		lblDni.setHorizontalAlignment(SwingConstants.TRAILING);
		lblDni.setBounds(22, 33, 103, 14);
		getContentPane().add(lblDni);

		tfDni = new JTextField();
		tfDni.setBounds(135, 30, 283, 20);
		tfDni.setHorizontalAlignment(SwingConstants.RIGHT);
		getContentPane().add(tfDni);
		tfDni.setColumns(10);

		lblFecha = new JLabel("Fecha de certificado:");
		lblFecha.setHorizontalAlignment(SwingConstants.TRAILING);
		lblFecha.setBounds(3, 58, 122, 14);
		getContentPane().add(lblFecha);

		lblProfesional = new JLabel("Profesional firmante:");
		lblProfesional.setHorizontalAlignment(SwingConstants.TRAILING);
		lblProfesional.setBounds(3, 83, 122, 14);
		getContentPane().add(lblProfesional);

		tfProfesional = new JTextField();
		tfProfesional.setBounds(135, 80, 283, 20);
		tfProfesional.setHorizontalAlignment(SwingConstants.RIGHT);
		getContentPane().add(tfProfesional);
		tfProfesional.setColumns(10);

		lblObservaciones = new JLabel("Observaciones");
		lblObservaciones.setHorizontalAlignment(SwingConstants.TRAILING);
		lblObservaciones.setBounds(0, 108, 125, 14);
		getContentPane().add(lblObservaciones);

		lblEstado = new JLabel("Estado:");
		lblEstado.setHorizontalAlignment(SwingConstants.TRAILING);
		lblEstado.setBounds(27, 250, 98, 14);
		getContentPane().add(lblEstado);

		comboBox = new JComboBox();
		comboBox.setBounds(135, 247, 283, 20);
		comboBox.setModel(new DefaultComboBoxModel(new String[] { "Activo", "Inactivo" }));
		getContentPane().add(comboBox);

		btnAlta = new JButton("Alta");
		btnAlta.setBounds(285, 278, 133, 23);
		btnAlta.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				sistema.altaApto(tfDni.getText(), dateToLocalDate(dateChooser.getDate()), tfProfesional.getText(),
						editorPane.getText(), comboBox.getSelectedItem().toString());
				JOptionPane.showMessageDialog(null, "Apto médico creado");
				limpiarPantalla();
			}
		});
		getContentPane().add(btnAlta);

		editorPane = new JEditorPane();
		editorPane.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent arg0) {

				int count = editorPane.getText().length() + 1;
				lblCount.setText(count + "/500");

				if (count >= 500)
					lblCount.setForeground(Color.RED);
				else
					lblCount.setForeground(Color.BLACK);

			}
		});
		editorPane.setBounds(135, 111, 200, 58);
		getContentPane().add(editorPane);

		scrollPane = new JScrollPane(editorPane);
		scrollPane.setBounds(135, 111, 283, 125);
		getContentPane().add(scrollPane);

		lblCount = new JLabel("0/500");
		lblCount.setHorizontalAlignment(SwingConstants.CENTER);
		lblCount.setBounds(54, 123, 62, 14);
		getContentPane().add(lblCount);
		lblCount.setForeground(Color.BLACK);

		btnBuscarOtro = new JButton("Buscar otro");
		btnBuscarOtro.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				limpiarPantalla();
			}
		});
		btnBuscarOtro.setBounds(135, 278, 133, 23);
		getContentPane().add(btnBuscarOtro);

		btnBuscar = new JButton("Buscar");
		btnBuscar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (sistema.getSocioView(tfDni.getText()) != null)
					mostrar();
			}
		});
		btnBuscar.setBounds(285, 54, 133, 23);
		getContentPane().add(btnBuscar);

		dateChooser = new JDateChooser();
		dateChooser.setBounds(135, 56, 149, 20);
		getContentPane().add(dateChooser);
		limpiarPantalla();

	}

	private LocalDate dateToLocalDate(Date fecha) {

		Instant instant = fecha.toInstant();
		ZonedDateTime zdt = instant.atZone(ZoneId.systemDefault());
		LocalDate date = zdt.toLocalDate();

		return date;
	}

	private void limpiarPantalla() {
		tfDni.setEditable(true);
		lblFecha.setVisible(false);
		dateChooser.setVisible(false);
		lblProfesional.setVisible(false);
		tfProfesional.setVisible(false);
		lblObservaciones.setVisible(false);
		lblCount.setVisible(false);
		editorPane.setVisible(false);
		lblEstado.setVisible(false);
		comboBox.setVisible(false);
		btnAlta.setVisible(false);
		btnBuscarOtro.setVisible(false);
		scrollPane.setVisible(false);
		btnBuscar.setVisible(true);

		comboBox.setSelectedItem(0);
		;
		dateChooser.setDate(null);
		tfDni.setText("");
		tfProfesional.setText("");
		editorPane.setText("");

	}

	private Date localDateToDate(LocalDate fecha) {

		Instant instant = fecha.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant();
		return Date.from(instant);

	}

	private void mostrar() {
		tfDni.setEditable(false);
		lblFecha.setVisible(true);
		dateChooser.setVisible(true);
		lblProfesional.setVisible(true);
		tfProfesional.setVisible(true);
		lblObservaciones.setVisible(true);
		lblCount.setVisible(true);
		editorPane.setVisible(true);
		lblEstado.setVisible(true);
		comboBox.setVisible(true);
		btnAlta.setVisible(true);
		btnBuscarOtro.setVisible(true);
		btnBuscar.setVisible(false);
		scrollPane.setVisible(true);

	}

}
