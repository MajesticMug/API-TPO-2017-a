package vista;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.util.ArrayList;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import controlador.Gimnasio;
import views.AbonoView;

import com.toedter.calendar.JDateChooser;

import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;

public class BajaAbono extends JFrame {

	private JButton btnBaja;

	private JButton btnBuscar;
	private JButton btnBuscarOtro;
	private JComboBox comboBox;
	private JPanel contentPane;
	private JLabel lblCodigo;
	private JLabel lblEstado;
	private JLabel lblMeses;
	private JLabel lblNombre;
	private JLabel lblPeriodo;
	private JLabel lblPrecio;
	private Gimnasio sistema;
	private JSpinner spinner;
	private JTextField tfCodigo;
	private JTextField tfNombre;
	private JTextField tfPrecio;

	/**
	 * Create the frame.
	 */
	public BajaAbono() {
		setResizable(false);
		setTitle("Baja abono");
		sistema = Gimnasio.getInstancia();

		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

		setBounds(100, 100, 451, 226);
		getContentPane().setLayout(null);

		lblNombre = new JLabel("Nombre:");
		lblNombre.setHorizontalAlignment(SwingConstants.TRAILING);
		lblNombre.setBounds(15, 61, 70, 14);
		getContentPane().add(lblNombre);

		tfNombre = new JTextField();
		tfNombre.setEnabled(false);
		tfNombre.setBounds(108, 58, 307, 20);
		tfNombre.setHorizontalAlignment(SwingConstants.TRAILING);
		getContentPane().add(tfNombre);
		tfNombre.setColumns(10);

		lblPrecio = new JLabel("Precio:");
		lblPrecio.setHorizontalAlignment(SwingConstants.TRAILING);
		lblPrecio.setBounds(-4, 86, 89, 14);
		getContentPane().add(lblPrecio);

		tfPrecio = new JTextField();
		tfPrecio.setEnabled(false);
		tfPrecio.setBounds(108, 83, 307, 20);
		tfPrecio.setHorizontalAlignment(SwingConstants.TRAILING);
		getContentPane().add(tfPrecio);
		tfPrecio.setColumns(10);

		lblPeriodo = new JLabel("Periodo pago:");
		lblPeriodo.setHorizontalAlignment(SwingConstants.TRAILING);
		lblPeriodo.setBounds(-7, 114, 92, 14);
		getContentPane().add(lblPeriodo);

		btnBaja = new JButton("Baja");
		btnBaja.setBounds(267, 164, 148, 23);
		btnBaja.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				sistema.bajaAbono(Integer.parseInt(tfCodigo.getText()), comboBox.getSelectedItem().toString());
				limpiarPantalla();

			}
		});
		getContentPane().add(btnBaja);

		lblCodigo = new JLabel("Codigo:");
		lblCodigo.setHorizontalAlignment(SwingConstants.TRAILING);
		lblCodigo.setBounds(39, 36, 46, 14);
		getContentPane().add(lblCodigo);

		tfCodigo = new JTextField();
		tfCodigo.setHorizontalAlignment(SwingConstants.TRAILING);
		tfCodigo.setBounds(108, 33, 307, 20);
		getContentPane().add(tfCodigo);
		tfCodigo.setColumns(10);

		btnBuscar = new JButton("Buscar");
		btnBuscar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AbonoView a = sistema.getAbonoView(Integer.parseInt(tfCodigo.getText()));
				if (a != null)
					mostrar(a);
			}
		});
		btnBuscar.setBounds(253, 57, 162, 23);
		getContentPane().add(btnBuscar);

		btnBuscarOtro = new JButton("Buscar otro");
		btnBuscarOtro.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				limpiarPantalla();
			}
		});
		btnBuscarOtro.setBounds(108, 164, 149, 23);
		getContentPane().add(btnBuscarOtro);

		spinner = new JSpinner();
		spinner.setEnabled(false);
		spinner.setModel(new SpinnerNumberModel(1, 1, 12, 1));
		spinner.setBounds(108, 111, 149, 20);
		getContentPane().add(spinner);
		spinner.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent arg0) {

				setLblMeses();
			}

		});

		lblMeses = new JLabel();
		setLblMeses();
		lblMeses.setBounds(267, 114, 148, 14);
		getContentPane().add(lblMeses);

		lblEstado = new JLabel("Estado:");
		lblEstado.setHorizontalAlignment(SwingConstants.TRAILING);
		lblEstado.setBounds(3, 139, 82, 14);
		getContentPane().add(lblEstado);

		comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(new String[] { "Inactivo" }));
		comboBox.setBounds(108, 136, 307, 20);
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
					BajaAbono frame = new BajaAbono();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	private void limpiarPantalla() {

		tfCodigo.setEnabled(true);
		lblNombre.setVisible(false);
		tfNombre.setVisible(false);
		lblPrecio.setVisible(false);
		tfPrecio.setVisible(false);
		lblPeriodo.setVisible(false);
		spinner.setVisible(false);
		lblMeses.setVisible(false);
		btnBaja.setVisible(false);
		btnBuscarOtro.setVisible(false);
		btnBuscar.setVisible(true);
		lblEstado.setVisible(false);
		comboBox.setVisible(false);

	}

	private void mostrar(AbonoView a) {

		tfCodigo.setEnabled(false);
		lblNombre.setVisible(true);
		tfNombre.setVisible(true);
		tfNombre.setText(a.getNombre());
		lblPrecio.setVisible(true);
		tfPrecio.setVisible(true);
		tfPrecio.setText(String.valueOf(a.getPrecio()));
		lblPeriodo.setVisible(true);
		spinner.setVisible(true);
		spinner.setValue(a.getPeriodoPago());
		lblMeses.setVisible(true);
		btnBaja.setVisible(true);
		btnBuscarOtro.setVisible(true);
		btnBuscar.setVisible(false);
		lblEstado.setVisible(true);
		comboBox.setVisible(true);

	}

	private void setLblMeses() {
		int n = (Integer) spinner.getValue();
		String lbl = "Pagar cada " + n;
		if (n == 1)
			lblMeses.setText(lbl + " mes");
		else
			lblMeses.setText(lbl + " meses");
	}
}
