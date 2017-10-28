package vista;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.time.LocalDate;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JTextField;
import com.toedter.calendar.JYearChooser;

import controlador.Gimnasio;
import views.ReciboView;

import javax.swing.JSpinner;
import javax.swing.SwingConstants;
import javax.swing.SpinnerNumberModel;
import javax.swing.JSeparator;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ConsultarRecibo extends JFrame {

	private JSpinner anio;
	private JButton btnConsultar;
	private JButton btnConsultarOtro;
	private JPanel contentPane;
	private JTextField dni;
	private JLabel lblAnio;
	private JLabel lblDni;
	private JLabel lblMes;
	private JLabel lblPesos;
	private JLabel lblSueldo;
	private JSpinner mes;
	private JSeparator separator;
	private Gimnasio sistema;
	private JLabel sueldo;

	/**
	 * Create the frame.
	 */
	public ConsultarRecibo() {
		sistema = Gimnasio.getInstancia();
		setResizable(false);
		setTitle("Consultar recibo");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 288, 219);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		lblDni = new JLabel("DNI:");
		lblDni.setHorizontalAlignment(SwingConstants.TRAILING);
		lblDni.setBounds(10, 13, 60, 14);
		contentPane.add(lblDni);

		lblMes = new JLabel("Mes:");
		lblMes.setHorizontalAlignment(SwingConstants.TRAILING);
		lblMes.setBounds(10, 38, 60, 14);
		contentPane.add(lblMes);

		lblAnio = new JLabel("A\u00F1o:");
		lblAnio.setHorizontalAlignment(SwingConstants.TRAILING);
		lblAnio.setBounds(10, 63, 60, 14);
		contentPane.add(lblAnio);

		btnConsultar = new JButton("Consultar");
		btnConsultar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				ReciboView r = sistema.getReciboView(dni.getText(), Integer.parseInt(mes.getValue().toString()),
						Integer.parseInt(anio.getValue().toString()));
				if (r != null)
					mostrar(r);
			}
		});
		btnConsultar.setBounds(49, 88, 200, 25);
		contentPane.add(btnConsultar);

		dni = new JTextField();
		dni.setHorizontalAlignment(SwingConstants.TRAILING);
		dni.setBounds(94, 11, 153, 20);
		contentPane.add(dni);
		dni.setColumns(10);

		mes = new JSpinner();
		mes.setModel(new SpinnerNumberModel(1, 1, 12, 1));
		mes.setBounds(94, 36, 153, 20);
		contentPane.add(mes);

		anio = new JSpinner();
		anio.setModel(new SpinnerNumberModel(LocalDate.now().getYear(), 1900, 9999, 1));
		anio.setBounds(94, 61, 153, 20);
		contentPane.add(anio);

		lblSueldo = new JLabel("Sueldo:");
		lblSueldo.setHorizontalAlignment(SwingConstants.RIGHT);
		lblSueldo.setBounds(10, 115, 60, 14);
		contentPane.add(lblSueldo);

		lblPesos = new JLabel("$");
		lblPesos.setHorizontalAlignment(SwingConstants.CENTER);
		lblPesos.setBounds(94, 115, 21, 14);
		contentPane.add(lblPesos);

		btnConsultarOtro = new JButton("Consultar otro");
		btnConsultarOtro.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				limpiarPantalla();
			}
		});
		btnConsultarOtro.setBounds(49, 145, 200, 25);
		contentPane.add(btnConsultarOtro);

		sueldo = new JLabel("");
		sueldo.setHorizontalAlignment(SwingConstants.CENTER);
		sueldo.setBounds(125, 115, 111, 14);
		contentPane.add(sueldo);

		separator = new JSeparator();
		separator.setBounds(0, 101, 300, 5);
		contentPane.add(separator);
		limpiarPantalla();
	}

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ConsultarRecibo frame = new ConsultarRecibo();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	private void limpiarPantalla() {
		dni.setText("");
		dni.setEnabled(true);
		anio.setEnabled(true);
		mes.setEnabled(true);
		btnConsultar.setVisible(true);

		separator.setVisible(false);
		lblPesos.setVisible(false);
		sueldo.setVisible(false);
		lblSueldo.setVisible(false);
		btnConsultarOtro.setVisible(false);

	}

	private void mostrar(ReciboView r) {

		dni.setEnabled(false);
		anio.setEnabled(false);
		mes.setEnabled(false);
		btnConsultar.setVisible(false);

		separator.setVisible(true);
		lblPesos.setVisible(true);
		sueldo.setVisible(true);
		lblSueldo.setVisible(true);
		btnConsultarOtro.setVisible(true);
		sueldo.setText(String.valueOf(r.getMonto()));

	}
}
