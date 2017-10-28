package vista;

import java.awt.EventQueue;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.DefaultComboBoxModel;
import javax.swing.Icon;
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
import views.InscripcionCorporativaView;

import com.toedter.calendar.JDateChooser;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import javax.swing.event.ChangeListener;
import javax.swing.event.ChangeEvent;

public class ModificarConvenio extends JFrame {

	private JButton btnBuscar;

	private JButton btnBuscarOtro;
	private JButton btnModificar;
	private JPanel contentPane;
	private JSpinner descuento;
	private JDateChooser finVig;
	private Icon icono;
	private ImageIcon imagen;
	private JLabel lblCod;
	private JLabel lblDescuento;
	private JLabel lblEmpresa;
	private JLabel lblFin;

	private JLabel lblPorc;
	private Gimnasio sistema;
	private JTextField tfCod;
	private JTextField tfEmpresa;

	public ModificarConvenio() {
		setResizable(false);
		setTitle("Modificar convenio");
		sistema = Gimnasio.getInstancia();

		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

		setBounds(100, 100, 451, 201);
		getContentPane().setLayout(null);

		btnModificar = new JButton("Modificar");
		btnModificar.setBounds(288, 140, 150, 23);
		btnModificar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				sistema.modificarPlanCorporativo(Integer.parseInt(tfCod.getText()), dateToLocalDate(finVig.getDate()),
						Float.parseFloat(descuento.getValue().toString()));
				JOptionPane.showMessageDialog(null, "Convenio modificado correctamente");
				limpiarPantalla();
			}
		});
		getContentPane().add(btnModificar);

		lblFin = new JLabel("Fin Vigencia:");
		lblFin.setHorizontalAlignment(SwingConstants.TRAILING);
		lblFin.setBounds(10, 82, 112, 14);
		getContentPane().add(lblFin);

		finVig = new JDateChooser();
		finVig.setBounds(132, 82, 307, 20);
		getContentPane().add(finVig);

		lblDescuento = new JLabel("Descuento masivo:");
		lblDescuento.setHorizontalAlignment(SwingConstants.TRAILING);
		lblDescuento.setBounds(21, 112, 100, 14);
		getContentPane().add(lblDescuento);

		descuento = new JSpinner();
		descuento.setModel(new SpinnerNumberModel(new Float(0), new Float(0), new Float(100), new Float(1)));
		descuento.setBounds(132, 109, 70, 20);
		getContentPane().add(descuento);

		lblPorc = new JLabel("%");
		lblPorc.setBounds(205, 113, 20, 14);
		getContentPane().add(lblPorc);

		lblCod = new JLabel("C\u00F3digo");
		lblCod.setHorizontalAlignment(SwingConstants.TRAILING);
		lblCod.setBounds(10, 32, 112, 14);
		getContentPane().add(lblCod);

		tfCod = new JTextField();
		tfCod.setHorizontalAlignment(SwingConstants.TRAILING);
		tfCod.setBounds(132, 26, 273, 20);
		getContentPane().add(tfCod);
		tfCod.setColumns(10);

		btnBuscarOtro = new JButton("Buscar otro");
		btnBuscarOtro.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				limpiarPantalla();

			}
		});
		btnBuscarOtro.setBounds(132, 140, 150, 23);
		getContentPane().add(btnBuscarOtro);

		btnBuscar = new JButton("");
		btnBuscar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				InscripcionCorporativaView v = sistema.getInscripcionCorporativaView(Integer.parseInt(tfCod.getText()));
				if (v != null)
					mostrar(v);
			}
		});
		btnBuscar.setBounds(413, 26, 25, 22);
		imagen = new ImageIcon(AltaActividad.class.getResource("/images/lupa.png"));
		icono = new ImageIcon(
				imagen.getImage().getScaledInstance(btnBuscar.getWidth(), btnBuscar.getHeight(), Image.SCALE_DEFAULT));

		btnBuscar.setIcon(icono);

		getContentPane().add(btnBuscar);

		lblEmpresa = new JLabel("Empresa:");
		lblEmpresa.setHorizontalAlignment(SwingConstants.TRAILING);
		lblEmpresa.setBounds(52, 57, 70, 14);
		getContentPane().add(lblEmpresa);

		tfEmpresa = new JTextField();
		tfEmpresa.setHorizontalAlignment(SwingConstants.TRAILING);
		tfEmpresa.setEnabled(false);
		tfEmpresa.setBounds(132, 55, 303, 20);
		getContentPane().add(tfEmpresa);
		tfEmpresa.setColumns(10);

		limpiarPantalla();

	}

	private LocalDate dateToLocalDate(Date fecha) {

		Instant instant = fecha.toInstant();
		ZonedDateTime zdt = instant.atZone(ZoneId.systemDefault());
		LocalDate date = zdt.toLocalDate();

		return date;
	}

	private void limpiarPantalla() {

		lblEmpresa.setVisible(false);
		tfEmpresa.setVisible(false);
		lblFin.setVisible(false);
		finVig.setVisible(false);
		lblDescuento.setVisible(false);
		descuento.setVisible(false);
		lblPorc.setVisible(false);
		btnBuscarOtro.setVisible(false);
		btnModificar.setVisible(false);

		tfCod.setEnabled(true);

	}

	private Date localDateToDate(LocalDate fecha) {

		Instant instant = fecha.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant();
		return Date.from(instant);

	}

	private void mostrar(InscripcionCorporativaView v) {

		lblEmpresa.setVisible(true);
		tfEmpresa.setVisible(true);
		lblFin.setVisible(true);
		finVig.setVisible(true);
		lblDescuento.setVisible(true);
		descuento.setVisible(true);
		lblPorc.setVisible(true);
		btnBuscarOtro.setVisible(true);
		btnModificar.setVisible(true);
		finVig.setDate(localDateToDate(v.getFinVigencia()));

		tfCod.setEnabled(false);

		tfEmpresa.setText(v.getEmpresa());
		descuento.setValue(v.getDescuentoMasivo());

	}
}
