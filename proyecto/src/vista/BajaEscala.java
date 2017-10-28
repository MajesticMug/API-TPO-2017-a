package vista;

import java.awt.EventQueue;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
import views.EscalaView;

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

public class BajaEscala extends JFrame {

	private JTextField base;

	private JButton btnBaja;
	private JButton btnBuscar;
	private JButton btnBuscarOtro;
	private JPanel contentPane;
	private Icon icono;
	private ImageIcon imagen;
	private JLabel label;
	private JLabel label2;
	private JLabel lblBase;
	private JLabel lblCod;
	private JLabel lblCount;
	private JLabel lblNovedades;
	private JLabel lblTope;
	private JEditorPane novedades;

	private JScrollPane scrollPane;

	private Gimnasio sistema;
	private JFormattedTextField tfCod;
	private JTextField tope;

	/**
	 * Create the frame.
	 */
	public BajaEscala() {

		setTitle("Baja escala salarial");
		sistema = Gimnasio.getInstancia();

		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

		setBounds(100, 100, 373, 299);
		getContentPane().setLayout(null);

		lblBase = new JLabel("Sueldo base:");
		lblBase.setHorizontalAlignment(SwingConstants.TRAILING);
		lblBase.setBounds(0, 40, 84, 14);
		getContentPane().add(lblBase);

		lblTope = new JLabel("Sueldo tope:");
		lblTope.setHorizontalAlignment(SwingConstants.TRAILING);
		lblTope.setBounds(0, 65, 84, 14);
		getContentPane().add(lblTope);

		lblNovedades = new JLabel("Novedades:");
		lblNovedades.setHorizontalAlignment(SwingConstants.TRAILING);
		lblNovedades.setBounds(0, 90, 87, 14);
		getContentPane().add(lblNovedades);

		btnBaja = new JButton("Baja");
		btnBaja.setBounds(216, 227, 120, 23);
		btnBaja.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				sistema.bajaEscala(Integer.parseInt(tfCod.getText()));
				limpiarPantalla();
			}
		});
		getContentPane().add(btnBaja);

		novedades = new JEditorPane();
		novedades.setEnabled(false);
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
		scrollPane.setBounds(94, 90, 242, 125);
		getContentPane().add(scrollPane);

		lblCount = new JLabel("0/500");
		lblCount.setHorizontalAlignment(SwingConstants.CENTER);
		lblCount.setBounds(21, 105, 63, 14);
		getContentPane().add(lblCount);
		lblCount.setForeground(Color.BLACK);

		label2 = new JLabel("$");
		label2.setHorizontalAlignment(SwingConstants.CENTER);
		label2.setBounds(94, 40, 17, 14);
		getContentPane().add(label2);

		label = new JLabel("$");
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setBounds(94, 65, 17, 14);
		getContentPane().add(label);

		btnBuscarOtro = new JButton("Buscar otro");
		btnBuscarOtro.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				limpiarPantalla();
			}
		});
		btnBuscarOtro.setBounds(94, 227, 120, 23);
		getContentPane().add(btnBuscarOtro);

		lblCod = new JLabel("C\u00F3digo:");
		lblCod.setHorizontalAlignment(SwingConstants.TRAILING);
		lblCod.setBounds(21, 15, 63, 14);
		getContentPane().add(lblCod);

		tfCod = new JFormattedTextField(new DecimalFormat("#"));
		tfCod.setHorizontalAlignment(SwingConstants.TRAILING);
		tfCod.setBounds(94, 11, 201, 21);
		getContentPane().add(tfCod);

		btnBuscar = new JButton("");
		btnBuscar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				EscalaView esc = sistema.getEscalaView(Integer.parseInt(tfCod.getText()));
				if (esc != null)
					mostrar(esc);
			}
		});
		btnBuscar.setBounds(305, 9, 31, 23);

		imagen = new ImageIcon(AltaActividad.class.getResource("/images/lupa.png"));
		icono = new ImageIcon(
				imagen.getImage().getScaledInstance(btnBuscar.getWidth(), btnBuscar.getHeight(), Image.SCALE_DEFAULT));

		btnBuscar.setIcon(icono);
		getContentPane().add(btnBuscar);

		base = new JTextField();
		base.setEnabled(false);
		base.setHorizontalAlignment(SwingConstants.CENTER);
		base.setBounds(114, 37, 181, 20);
		getContentPane().add(base);
		base.setColumns(10);

		tope = new JTextField();
		tope.setEnabled(false);
		tope.setHorizontalAlignment(SwingConstants.CENTER);
		tope.setBounds(114, 62, 181, 20);
		getContentPane().add(tope);
		tope.setColumns(10);

		limpiarPantalla();

	}

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					BajaEscala frame = new BajaEscala();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	private void limpiarPantalla() {
		tfCod.setEnabled(true);

		lblBase.setVisible(false);
		lblTope.setVisible(false);
		base.setVisible(false);
		tope.setVisible(false);
		lblNovedades.setVisible(false);
		lblCount.setVisible(false);
		scrollPane.setVisible(false);
		label.setVisible(false);
		label2.setVisible(false);
		btnBuscarOtro.setVisible(false);
		btnBaja.setVisible(false);
	}

	private void mostrar(EscalaView esc) {

		tfCod.setEnabled(false);

		lblBase.setVisible(true);
		lblTope.setVisible(true);
		base.setVisible(true);
		base.setText(String.valueOf(esc.getSueldoBase()));
		tope.setVisible(true);
		tope.setText(String.valueOf(esc.getSueldoHasta()));
		lblNovedades.setVisible(true);
		lblCount.setVisible(true);
		scrollPane.setVisible(true);
		label.setVisible(true);
		label2.setVisible(true);
		novedades.setText(esc.getNovedades());
		btnBuscarOtro.setVisible(true);
		btnBaja.setVisible(true);

		base.setVisible(true);
		tope.setVisible(true);
	}

}
