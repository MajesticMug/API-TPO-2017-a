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
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.awt.Color;
import com.toedter.calendar.JDateChooser;
import javax.swing.JFormattedTextField;
import javax.swing.JSpinner;
import java.text.Format;

public class AltaEscala extends JFrame {

	private JTextField base;

	private JButton btnAlta;
	private JPanel contentPane;
	private JLabel label;
	private JLabel label2;
	private JLabel lblBase;
	private JLabel lblCount;
	private JLabel lblNovedades;
	private JLabel lblTope;
	private JEditorPane novedades;
	private JScrollPane scrollPane;
	private Gimnasio sistema;
	private JTextField tope;

	/**
	 * Create the frame.
	 */
	public AltaEscala() {

		setTitle("Alta de escala salarial");
		sistema = Gimnasio.getInstancia();

		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

		setBounds(100, 100, 336, 269);
		getContentPane().setLayout(null);

		lblBase = new JLabel("Sueldo base:");
		lblBase.setHorizontalAlignment(SwingConstants.TRAILING);
		lblBase.setBounds(0, 14, 84, 14);
		getContentPane().add(lblBase);

		lblTope = new JLabel("Sueldo tope:");
		lblTope.setHorizontalAlignment(SwingConstants.TRAILING);
		lblTope.setBounds(0, 39, 84, 14);
		getContentPane().add(lblTope);

		lblNovedades = new JLabel("Novedades:");
		lblNovedades.setHorizontalAlignment(SwingConstants.TRAILING);
		lblNovedades.setBounds(0, 64, 87, 14);
		getContentPane().add(lblNovedades);

		btnAlta = new JButton("Alta");
		btnAlta.setBounds(94, 201, 214, 23);
		btnAlta.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				sistema.altaEscalaSalarial(Float.parseFloat(base.getText()), Float.parseFloat(tope.getText()),
						novedades.getText());
				JOptionPane.showMessageDialog(null, "Escala salarial creada correctamente");
				limpiarPantalla();
			}
		});
		getContentPane().add(btnAlta);

		novedades = new JEditorPane();
		novedades.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent arg0) {
				int count = novedades.getText().length() + 1;
				lblCount.setText(count + "/500");

				if (count >= 500)
					lblCount.setForeground(Color.RED);
				else
					lblCount.setForeground(Color.BLACK);

			}
		});
		novedades.setBounds(135, 111, 200, 58);
		getContentPane().add(novedades);

		scrollPane = new JScrollPane(novedades);
		scrollPane.setBounds(94, 64, 214, 125);
		getContentPane().add(scrollPane);

		lblCount = new JLabel("0/500");
		lblCount.setHorizontalAlignment(SwingConstants.CENTER);
		lblCount.setBounds(21, 79, 63, 14);
		getContentPane().add(lblCount);
		lblCount.setForeground(Color.BLACK);

		label2 = new JLabel("$");
		label2.setHorizontalAlignment(SwingConstants.CENTER);
		label2.setBounds(94, 14, 17, 14);
		getContentPane().add(label2);

		label = new JLabel("$");
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setBounds(94, 39, 17, 14);
		getContentPane().add(label);

		base = new JTextField();
		base.setBounds(113, 11, 115, 20);
		getContentPane().add(base);
		base.setColumns(10);

		tope = new JTextField();
		tope.setBounds(112, 36, 116, 20);
		getContentPane().add(tope);
		tope.setColumns(10);

	}

	private void limpiarPantalla() {
		base.setText("");
		tope.setText("");
		novedades.setText("");

	}

}
