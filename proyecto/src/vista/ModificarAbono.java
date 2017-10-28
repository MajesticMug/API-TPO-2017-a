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
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import controlador.Gimnasio;
import views.AbonoView;

import com.toedter.calendar.JDateChooser;

import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;

public class ModificarAbono extends JFrame {

	private JButton btnBuscar;

	private JButton btnBuscarOtro;
	private JButton btnModificar;
	private JPanel contentPane;
	private JLabel lblCodigo;
	private JLabel lblMeses;
	private JLabel lblNombre;
	private JLabel lblPeriodo;
	private JLabel lblPrecio;
	private Gimnasio sistema;
	private JSpinner spinner;
	private JTextField tfCodigo;
	private JTextField tfNombre;
	private JTextField tfPrecio;

	
	public ModificarAbono() {
		setResizable(false);
		setTitle("Modificar abono");
		sistema = Gimnasio.getInstancia();

		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

		setBounds(100, 100, 451, 203);
		getContentPane().setLayout(null);

		lblNombre = new JLabel("Nombre:");
		lblNombre.setHorizontalAlignment(SwingConstants.TRAILING);
		lblNombre.setBounds(15, 61, 70, 14);
		getContentPane().add(lblNombre);

		tfNombre = new JTextField();
		tfNombre.setBounds(108, 58, 307, 20);
		tfNombre.setHorizontalAlignment(SwingConstants.TRAILING);
		getContentPane().add(tfNombre);
		tfNombre.setColumns(10);

		lblPrecio = new JLabel("Precio:");
		lblPrecio.setHorizontalAlignment(SwingConstants.TRAILING);
		lblPrecio.setBounds(-4, 86, 89, 14);
		getContentPane().add(lblPrecio);

		tfPrecio = new JTextField();
		tfPrecio.setBounds(108, 83, 307, 20);
		tfPrecio.setHorizontalAlignment(SwingConstants.TRAILING);
		getContentPane().add(tfPrecio);
		tfPrecio.setColumns(10);

		lblPeriodo = new JLabel("Periodo pago:");
		lblPeriodo.setHorizontalAlignment(SwingConstants.TRAILING);
		lblPeriodo.setBounds(-7, 114, 92, 14);
		getContentPane().add(lblPeriodo);

		btnModificar = new JButton("Modificar");
		btnModificar.setBounds(267, 142, 143, 23);
		btnModificar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				ArrayList<Integer> acts = new ArrayList<Integer>();
				acts.add(1);
				sistema.modificarAbono(Integer.parseInt(tfCodigo.getText()), tfNombre.getText(),
						Float.parseFloat(tfPrecio.getText()), (Integer) spinner.getValue());
				JOptionPane.showMessageDialog(null, "Abono modificado correctamente");
				limpiarPantalla();

			}
		});
		getContentPane().add(btnModificar);

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
		btnBuscarOtro.setBounds(108, 142, 149, 23);
		getContentPane().add(btnBuscarOtro);

		spinner = new JSpinner();
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
		limpiarPantalla();

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
		btnModificar.setVisible(false);
		btnBuscarOtro.setVisible(false);
		btnBuscar.setVisible(true);

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
		btnModificar.setVisible(true);
		btnBuscarOtro.setVisible(true);
		btnBuscar.setVisible(false);

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
