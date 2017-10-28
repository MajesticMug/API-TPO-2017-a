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
import views.EmpresaView;

public class ModificarEmpresa extends JFrame {

	private JButton btnBuscar;

	private JButton btnBuscarOtro;
	private JButton btnModificar;
	private JPanel contentPane;
	private Icon icono;
	private ImageIcon imagen;
	private JLabel lblCuit;
	private JLabel lblRazonSocial;
	private Gimnasio sistema;

	private JTextField tfCuit;

	private JTextField tfRazonSocial;

	public ModificarEmpresa() {
		setResizable(false);
		setTitle("Modificar empresa");
		sistema = Gimnasio.getInstancia();

		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

		setBounds(100, 100, 405, 145);
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

		btnModificar = new JButton("Modificar");
		btnModificar.setBounds(242, 86, 114, 23);
		btnModificar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				sistema.modificarEmpresa(tfCuit.getText(), tfRazonSocial.getText());
				JOptionPane.showMessageDialog(null, "Empresa modificado correctamente");
				limpiarPantalla();
			}
		});
		getContentPane().add(btnModificar);

		btnBuscarOtro = new JButton("Buscar otro");
		btnBuscarOtro.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				limpiarPantalla();
			}
		});
		btnBuscarOtro.setBounds(111, 86, 114, 23);
		getContentPane().add(btnBuscarOtro);

		btnBuscar = new JButton("");
		btnBuscar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				EmpresaView emp = sistema.getEmpresaView(tfCuit.getText());
				if (emp != null)
					mostrar(emp);
			}
		});
		btnBuscar.setBounds(366, 27, 22, 23);

		imagen = new ImageIcon(BajaClase.class.getResource("/images/lupa.png"));
		icono = new ImageIcon(
				imagen.getImage().getScaledInstance(btnBuscar.getWidth(), btnBuscar.getHeight(), Image.SCALE_DEFAULT));

		btnBuscar.setIcon(icono);
		getContentPane().add(btnBuscar);

		limpiarPantalla();

	}

	private void limpiarPantalla() {
		tfRazonSocial.setVisible(false);
		lblRazonSocial.setVisible(false);
		btnBuscarOtro.setVisible(false);
		btnModificar.setVisible(false);

		tfCuit.setEnabled(true);
	}

	protected void mostrar(EmpresaView emp) {
		tfRazonSocial.setVisible(true);
		lblRazonSocial.setVisible(true);
		btnBuscarOtro.setVisible(true);
		btnModificar.setVisible(true);
		tfRazonSocial.setText(emp.getRazonSocial());

		tfCuit.setEnabled(false);
	}
}
