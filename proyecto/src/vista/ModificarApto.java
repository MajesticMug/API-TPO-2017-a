package vista;

import java.awt.EventQueue;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import controlador.Gimnasio;
import views.AptoView;

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

public class ModificarApto extends JFrame {

	private JButton btnBuscarOtro;
	private JButton btnBuscarSocio;
	private JButton btnModificar;
	private JComboBox comboBox;
	private JPanel contentPane;
	private JDateChooser dateChooser;
	private JEditorPane editorPane;
	private ImageIcon icono;
	private ImageIcon imagen;
	private JLabel lblCount;
	private JLabel lblDni;
	private JLabel lblEstado;
	private JLabel lblFecha;
	private JLabel lblObservaciones;
	private JLabel lblProfesional;
	private JScrollPane scrollPane;
	private Gimnasio sistema;

	private JTextField tfApto;

	private JTextField tfDni;

	private JTextField tfProfesional;

	public ModificarApto() {

		setTitle("Modificar Medico");
		sistema = Gimnasio.getInstancia();

		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

		setBounds(100, 100, 479, 397);
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
		lblFecha.setBounds(3, 87, 122, 14);
		getContentPane().add(lblFecha);

		lblProfesional = new JLabel("Profesional firmante:");
		lblProfesional.setHorizontalAlignment(SwingConstants.TRAILING);
		lblProfesional.setBounds(3, 112, 122, 14);
		getContentPane().add(lblProfesional);

		tfProfesional = new JTextField();
		tfProfesional.setBounds(135, 112, 283, 20);
		tfProfesional.setHorizontalAlignment(SwingConstants.RIGHT);
		getContentPane().add(tfProfesional);
		tfProfesional.setColumns(10);

		lblObservaciones = new JLabel("Observaciones");
		lblObservaciones.setHorizontalAlignment(SwingConstants.TRAILING);
		lblObservaciones.setBounds(0, 137, 125, 14);
		getContentPane().add(lblObservaciones);

		lblEstado = new JLabel("Estado:");
		lblEstado.setHorizontalAlignment(SwingConstants.TRAILING);
		lblEstado.setBounds(22, 279, 98, 14);
		getContentPane().add(lblEstado);

		comboBox = new JComboBox();
		comboBox.setBounds(130, 276, 283, 20);
		comboBox.setModel(new DefaultComboBoxModel(new String[] { "Activo", "Inactivo" }));
		getContentPane().add(comboBox);

		btnModificar = new JButton("Modificar");
		btnModificar.setBounds(280, 307, 133, 23);
		btnModificar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				sistema.modificarAptoMedico(tfDni.getText(), Integer.parseInt(tfApto.getText()),
						dateToLocalDate(dateChooser.getDate()), tfProfesional.getText(), editorPane.getText());
				JOptionPane.showMessageDialog(null, "Apto médico modificado correctamente");
				limpiarPantalla();
			}
		});
		getContentPane().add(btnModificar);

		editorPane = new JEditorPane();
		editorPane.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent arg0) {

				cuentaEditorPane();

			}

		});
		editorPane.setBounds(135, 111, 200, 58);
		getContentPane().add(editorPane);

		scrollPane = new JScrollPane(editorPane);
		scrollPane.setBounds(135, 140, 283, 125);
		getContentPane().add(scrollPane);

		lblCount = new JLabel("0/500");
		lblCount.setHorizontalAlignment(SwingConstants.CENTER);
		lblCount.setBounds(54, 152, 62, 14);
		getContentPane().add(lblCount);
		lblCount.setForeground(Color.BLACK);

		btnBuscarOtro = new JButton("Buscar otro");
		btnBuscarOtro.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				limpiarPantalla();
			}
		});
		btnBuscarOtro.setBounds(130, 307, 133, 23);
		getContentPane().add(btnBuscarOtro);

		btnBuscarSocio = new JButton();
		btnBuscarSocio.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				AptoView a = sistema.getAptoView(tfDni.getText(), Integer.parseInt(tfApto.getText()));
				if (a != null)
					mostrar(a);
			}
		});

		btnBuscarSocio.setBounds(428, 58, 26, 23);
		getContentPane().add(btnBuscarSocio);
		imagen = new ImageIcon(ModificarApto.class.getResource("/images/lupa.png"));
		icono = new ImageIcon(imagen.getImage().getScaledInstance(btnBuscarSocio.getWidth(), btnBuscarSocio.getHeight(),
				Image.SCALE_DEFAULT));

		btnBuscarSocio.setIcon(icono);

		dateChooser = new JDateChooser();
		dateChooser.setBounds(135, 87, 149, 20);
		getContentPane().add(dateChooser);

		JLabel lblCod = new JLabel("Codigo de apto:");
		lblCod.setHorizontalAlignment(SwingConstants.TRAILING);
		lblCod.setBounds(22, 62, 103, 14);
		getContentPane().add(lblCod);

		tfApto = new JTextField();
		tfApto.setHorizontalAlignment(SwingConstants.RIGHT);
		tfApto.setEditable(true);
		tfApto.setColumns(10);
		tfApto.setBounds(135, 62, 283, 20);
		getContentPane().add(tfApto);
		limpiarPantalla();

	}

	private void cuentaEditorPane() {
		int count = editorPane.getText().length() + 1;
		lblCount.setText(count + "/500");

		if (count >= 500)
			lblCount.setForeground(Color.RED);
		else
			lblCount.setForeground(Color.BLACK);
	}

	private LocalDate dateToLocalDate(Date fecha) {

		Instant instant = fecha.toInstant();
		ZonedDateTime zdt = instant.atZone(ZoneId.systemDefault());
		LocalDate date = zdt.toLocalDate();

		return date;
	}

	private void limpiarPantalla() {
		tfDni.setEditable(true);
		tfApto.setEditable(true);
		lblFecha.setVisible(false);
		dateChooser.setVisible(false);
		lblProfesional.setVisible(false);
		tfProfesional.setVisible(false);
		lblObservaciones.setVisible(false);
		lblCount.setVisible(false);
		editorPane.setVisible(false);
		lblEstado.setVisible(false);
		comboBox.setVisible(false);
		btnModificar.setVisible(false);
		btnBuscarOtro.setVisible(false);
		scrollPane.setVisible(false);
		btnBuscarSocio.setVisible(true);

	}

	private Date localDateToDate(LocalDate fecha) {

		Instant instant = fecha.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant();
		return Date.from(instant);

	}

	private void mostrar(AptoView a) {
		tfDni.setEditable(false);
		lblFecha.setVisible(true);
		dateChooser.setVisible(true);
		dateChooser.setDate(localDateToDate(a.getFechaCertificado()));

		lblProfesional.setVisible(true);
		tfProfesional.setVisible(true);
		tfProfesional.setText(a.getProfesionalFirmante());
		lblObservaciones.setVisible(true);
		editorPane.setText(a.getObservaciones());
		lblCount.setVisible(true);
		editorPane.setVisible(true);
		lblEstado.setVisible(true);
		comboBox.setVisible(true);
		btnModificar.setVisible(true);
		btnBuscarOtro.setVisible(true);
		btnBuscarSocio.setVisible(false);
		scrollPane.setVisible(true);
		tfApto.setEditable(false);
		cuentaEditorPane();

	}
}
