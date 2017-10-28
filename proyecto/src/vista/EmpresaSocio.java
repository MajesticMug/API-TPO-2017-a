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

public class EmpresaSocio extends JFrame {

	private JButton btnInscribir;
	private JPanel contentPane;
	private JLabel lblCuit;
	private JLabel lblDni;
	private Gimnasio sistema;
	private JTextField tfAbono;
	private JTextField tfDni;

	/**
	 * Create the frame.
	 */
	public EmpresaSocio() {
		setTitle("Vincular socio a empresa");
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
		lblDni.setBounds(10, 13, 76, 14);
		contentPane.add(lblDni);

		lblCuit = new JLabel("CUIT:");
		lblCuit.setHorizontalAlignment(SwingConstants.CENTER);
		lblCuit.setBounds(10, 38, 76, 14);
		contentPane.add(lblCuit);

		tfDni = new JTextField();
		tfDni.setHorizontalAlignment(SwingConstants.CENTER);
		tfDni.setBounds(85, 11, 132, 20);
		contentPane.add(tfDni);
		tfDni.setColumns(10);

		tfAbono = new JTextField();
		tfAbono.setHorizontalAlignment(SwingConstants.CENTER);
		tfAbono.setBounds(85, 36, 132, 20);
		contentPane.add(tfAbono);
		tfAbono.setColumns(10);

		btnInscribir = new JButton("Vincular");
		btnInscribir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				sistema.vincularSocioEmpresa(tfAbono.getText(), tfDni.getText());
			}
		});
		btnInscribir.setBounds(20, 61, 197, 23);
		contentPane.add(btnInscribir);
	}

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					EmpresaSocio frame = new EmpresaSocio();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}
