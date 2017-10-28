package vista;

import java.awt.EventQueue;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
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
import javax.swing.border.EmptyBorder;

import controlador.Gimnasio;

public class AltaDeporte extends JFrame {

	private JButton btnAlta;
	private JComboBox comboBox;
	private JPanel contentPane;
	private JLabel lblDescripcin;
	private JLabel lblEstado;
	private JLabel lblTitulo;
	private Gimnasio sistema;
	private JTextField tfDescripcion;
	private JTextField tfTitulo;

	/**
	 * Create the frame.
	 */
	public AltaDeporte() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		sistema = Gimnasio.getInstancia();
		setResizable(false);
		setTitle("Alta deporte");
		setBounds(100, 100, 462, 177);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		GridBagLayout gbl_contentPane = new GridBagLayout();
		gbl_contentPane.columnWidths = new int[] { 0, 0, 0, 0 };
		gbl_contentPane.rowHeights = new int[] { 0, 0, 0, 0, 0, 0 };
		gbl_contentPane.columnWeights = new double[] { 0.0, 0.0, 1.0, Double.MIN_VALUE };
		gbl_contentPane.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE };
		contentPane.setLayout(gbl_contentPane);

		lblTitulo = new JLabel("T\u00EDtulo:");
		GridBagConstraints gbc_lblTitulo = new GridBagConstraints();
		gbc_lblTitulo.anchor = GridBagConstraints.EAST;
		gbc_lblTitulo.insets = new Insets(0, 0, 5, 5);
		gbc_lblTitulo.gridx = 1;
		gbc_lblTitulo.gridy = 1;
		contentPane.add(lblTitulo, gbc_lblTitulo);

		tfTitulo = new JTextField();
		tfTitulo.setHorizontalAlignment(SwingConstants.TRAILING);
		GridBagConstraints gbc_tfTitulo = new GridBagConstraints();
		gbc_tfTitulo.anchor = GridBagConstraints.SOUTH;
		gbc_tfTitulo.insets = new Insets(0, 0, 5, 0);
		gbc_tfTitulo.fill = GridBagConstraints.HORIZONTAL;
		gbc_tfTitulo.gridx = 2;
		gbc_tfTitulo.gridy = 1;
		contentPane.add(tfTitulo, gbc_tfTitulo);
		tfTitulo.setColumns(10);

		lblDescripcin = new JLabel("Descripci\u00F3n:");
		GridBagConstraints gbc_lblDescripcin = new GridBagConstraints();
		gbc_lblDescripcin.anchor = GridBagConstraints.EAST;
		gbc_lblDescripcin.insets = new Insets(0, 0, 5, 5);
		gbc_lblDescripcin.gridx = 1;
		gbc_lblDescripcin.gridy = 2;
		contentPane.add(lblDescripcin, gbc_lblDescripcin);

		tfDescripcion = new JTextField();
		tfDescripcion.setHorizontalAlignment(SwingConstants.TRAILING);
		GridBagConstraints gbc_tfDescripcion = new GridBagConstraints();
		gbc_tfDescripcion.anchor = GridBagConstraints.SOUTH;
		gbc_tfDescripcion.insets = new Insets(0, 0, 5, 0);
		gbc_tfDescripcion.fill = GridBagConstraints.HORIZONTAL;
		gbc_tfDescripcion.gridx = 2;
		gbc_tfDescripcion.gridy = 2;
		contentPane.add(tfDescripcion, gbc_tfDescripcion);
		tfDescripcion.setColumns(10);

		lblEstado = new JLabel("Estado:");
		GridBagConstraints gbc_lblEstado = new GridBagConstraints();
		gbc_lblEstado.anchor = GridBagConstraints.EAST;
		gbc_lblEstado.insets = new Insets(0, 0, 5, 5);
		gbc_lblEstado.gridx = 1;
		gbc_lblEstado.gridy = 3;
		contentPane.add(lblEstado, gbc_lblEstado);

		comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(new String[] { "Activo", "Inactivo" }));
		GridBagConstraints gbc_comboBox = new GridBagConstraints();
		gbc_comboBox.insets = new Insets(0, 0, 5, 0);
		gbc_comboBox.anchor = GridBagConstraints.SOUTH;
		gbc_comboBox.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboBox.gridx = 2;
		gbc_comboBox.gridy = 3;
		contentPane.add(comboBox, gbc_comboBox);

		btnAlta = new JButton("Alta");
		btnAlta.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				sistema.altaDeporte(tfTitulo.getText(), tfDescripcion.getText(), comboBox.getSelectedItem().toString());
				JOptionPane.showMessageDialog(null, "Deporte creado correctamente");
				limpiarPantalla();
			}
		});
		GridBagConstraints gbc_btnAlta = new GridBagConstraints();
		gbc_btnAlta.fill = GridBagConstraints.BOTH;
		gbc_btnAlta.gridx = 2;
		gbc_btnAlta.gridy = 4;
		contentPane.add(btnAlta, gbc_btnAlta);
	}

	private void limpiarPantalla() {
		tfTitulo.setText("");
		tfDescripcion.setText("");
	}

}
