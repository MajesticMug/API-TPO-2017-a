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
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import controlador.Gimnasio;

public class AltaEmpresa extends JFrame {

	private JButton btnAlta;

	private JComboBox comboBox;
	private JPanel contentPane;
	private JLabel lblCuit;
	private JLabel lblEstado;
	private JLabel lblRazonSocial;
	private Gimnasio sistema;
	private JTextField tfCuit;
	private JTextField tfRazonSocial;

	public AltaEmpresa() {
		setResizable(false);
		setTitle("Alta de empresa");
		sistema = Gimnasio.getInstancia();

		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

		setBounds(100, 100, 385, 169);
		getContentPane().setLayout(null);

		lblCuit = new JLabel("CUIT:");
		lblCuit.setHorizontalAlignment(SwingConstants.TRAILING);
		lblCuit.setBounds(18, 33, 70, 14);
		getContentPane().add(lblCuit);

		tfCuit = new JTextField();
		tfCuit.setBounds(111, 30, 245, 20);
		tfCuit.setHorizontalAlignment(SwingConstants.RIGHT);
		getContentPane().add(tfCuit);
		tfCuit.setColumns(10);

		lblRazonSocial = new JLabel("Razón social:");
		lblRazonSocial.setHorizontalAlignment(SwingConstants.TRAILING);
		lblRazonSocial.setBounds(0, 58, 88, 14);
		getContentPane().add(lblRazonSocial);

		tfRazonSocial = new JTextField();
		tfRazonSocial.setBounds(111, 55, 245, 20);
		tfRazonSocial.setHorizontalAlignment(SwingConstants.RIGHT);
		getContentPane().add(tfRazonSocial);
		tfRazonSocial.setColumns(10);

		btnAlta = new JButton("Alta");
		btnAlta.setBounds(111, 112, 245, 23);
		btnAlta.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				sistema.altaEmpresa(tfCuit.getText(), tfRazonSocial.getText(), comboBox.getSelectedItem().toString());
				JOptionPane.showMessageDialog(null, "Empresa creada correctamente");
				limpiarPantalla();
			}
		});
		getContentPane().add(btnAlta);

		lblEstado = new JLabel("Estado:");
		lblEstado.setHorizontalAlignment(SwingConstants.TRAILING);
		lblEstado.setBounds(18, 83, 70, 14);
		getContentPane().add(lblEstado);

		comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(new String[] { "Activo", "Inactivo" }));
		comboBox.setBounds(111, 81, 245, 20);
		getContentPane().add(comboBox);

	}

	protected void limpiarPantalla() {
		 tfCuit.setText ("");
		 tfRazonSocial.setText ("");		
	}

}
