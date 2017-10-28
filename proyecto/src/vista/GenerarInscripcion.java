package vista;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import controlador.Gimnasio;

import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class GenerarInscripcion extends JFrame {

	private JButton btnInscribir;
	private JPanel contentPane;
	private JLabel lblAbono;
	private JLabel lblDni;
	private Gimnasio sistema;
	private JTextField tfAbono;
	private JTextField tfDni;

	/**
	 * Create the frame.
	 */
	public GenerarInscripcion() {
		setTitle("Generar inscripcion");
		sistema = Gimnasio.getInstancia();
		setResizable(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 246, 123);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		lblDni = new JLabel("DNI:");
		lblDni.setHorizontalAlignment(SwingConstants.CENTER);
		lblDni.setBounds(10, 13, 97, 14);
		contentPane.add(lblDni);

		lblAbono = new JLabel("C\u00F3digo de abono:");
		lblAbono.setHorizontalAlignment(SwingConstants.CENTER);
		lblAbono.setBounds(10, 38, 97, 14);
		contentPane.add(lblAbono);

		tfDni = new JTextField();
		tfDni.setHorizontalAlignment(SwingConstants.CENTER);
		tfDni.setBounds(117, 11, 100, 20);
		contentPane.add(tfDni);
		tfDni.setColumns(10);

		tfAbono = new JTextField();
		tfAbono.setHorizontalAlignment(SwingConstants.CENTER);
		tfAbono.setBounds(117, 36, 100, 20);
		contentPane.add(tfAbono);
		tfAbono.setColumns(10);

		btnInscribir = new JButton("Inscribir");
		btnInscribir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				sistema.generarInscripcion(tfDni.getText(), Integer.parseInt(tfAbono.getText()));
			}
		});
		btnInscribir.setBounds(10, 61, 207, 23);
		contentPane.add(btnInscribir);
	}

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GenerarInscripcion frame = new GenerarInscripcion();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}
