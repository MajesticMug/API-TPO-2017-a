package vista;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.Date;

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
import com.toedter.calendar.JDateChooser;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import javax.swing.event.ChangeListener;
import javax.swing.event.ChangeEvent;

public class AltaConvenio extends JFrame {

	private JButton btnAlta;

	private JPanel contentPane;
	private JSpinner descuento;
	private JDateChooser fin;
	private JDateChooser inicio;
	private JLabel lblAbono;
	private JLabel lblCuit;
	private JLabel lblDescuentoMasivo;
	private JLabel lblFin;
	private JLabel lblInicio;
	private JLabel lblPorc;
	private Gimnasio sistema;
	private JTextField tfAbono;
	private JTextField tfCuit;

	public AltaConvenio() {
		setResizable(false);
		setTitle("Alta de convenio");
		sistema = Gimnasio.getInstancia();

		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

		setBounds(100, 100, 451, 232);
		getContentPane().setLayout(null);

		lblAbono = new JLabel("Abono:");
		lblAbono.setHorizontalAlignment(SwingConstants.TRAILING);
		lblAbono.setBounds(42, 45, 70, 14);
		getContentPane().add(lblAbono);

		tfAbono = new JTextField();
		tfAbono.setBounds(122, 44, 307, 20);
		tfAbono.setHorizontalAlignment(SwingConstants.TRAILING);
		getContentPane().add(tfAbono);
		tfAbono.setColumns(10);

		lblInicio = new JLabel("Inicio Vigencia:");
		lblInicio.setHorizontalAlignment(SwingConstants.TRAILING);
		lblInicio.setBounds(11, 72, 101, 14);
		getContentPane().add(lblInicio);

		btnAlta = new JButton("Alta");
		btnAlta.setBounds(122, 158, 307, 23);
		btnAlta.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (checkInfo()) {
					sistema.generarPlanCorporativo(Integer.parseInt(tfAbono.getText()), tfCuit.getText(),
							dateToLocalDate(inicio.getDate()), dateToLocalDate(fin.getDate()),
							Float.parseFloat(descuento.getValue().toString()));
					JOptionPane.showMessageDialog(null, "Convenio creado correctamente");
					limpiarPantalla();
				} else
					JOptionPane.showMessageDialog(null, "Complete los campos correctamente");
			}
		});
		getContentPane().add(btnAlta);

		inicio = new JDateChooser();
		inicio.setBounds(122, 72, 307, 20);
		getContentPane().add(inicio);

		lblFin = new JLabel("Fin Vigencia:");
		lblFin.setHorizontalAlignment(SwingConstants.TRAILING);
		lblFin.setBounds(0, 100, 112, 14);
		getContentPane().add(lblFin);

		fin = new JDateChooser();
		fin.setBounds(122, 100, 307, 20);
		getContentPane().add(fin);

		lblDescuentoMasivo = new JLabel("Descuento masivo:");
		lblDescuentoMasivo.setHorizontalAlignment(SwingConstants.TRAILING);
		lblDescuentoMasivo.setBounds(0, 130, 111, 14);
		getContentPane().add(lblDescuentoMasivo);

		descuento = new JSpinner();
		descuento.setModel(new SpinnerNumberModel(new Float(0), new Float(0), new Float(100), new Float(1)));
		descuento.setBounds(122, 127, 70, 20);
		getContentPane().add(descuento);

		lblPorc = new JLabel("%");
		lblPorc.setBounds(195, 131, 20, 14);
		getContentPane().add(lblPorc);

		lblCuit = new JLabel("CUIT:");
		lblCuit.setHorizontalAlignment(SwingConstants.TRAILING);
		lblCuit.setBounds(66, 20, 46, 14);
		getContentPane().add(lblCuit);

		tfCuit = new JTextField();
		tfCuit.setHorizontalAlignment(SwingConstants.TRAILING);
		tfCuit.setBounds(122, 17, 307, 20);
		getContentPane().add(tfCuit);
		tfCuit.setColumns(10);

	}

	private boolean checkInfo() {
		return !tfCuit.equals("") && !tfAbono.equals("") && inicio != null && fin != null;
	}

	private void limpiarPantalla() {
		descuento.setValue(0);
		fin.setDate(null);
		inicio.setDate(null);
		tfAbono.setText("");
		tfCuit.setText("");
	}

	private LocalDate dateToLocalDate(Date fecha) {

		Instant instant = fecha.toInstant();
		ZonedDateTime zdt = instant.atZone(ZoneId.systemDefault());
		LocalDate date = zdt.toLocalDate();

		return date;
	}

	private Date localDateToDate(LocalDate fecha) {

		Instant instant = fecha.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant();
		return Date.from(instant);

	}
}
