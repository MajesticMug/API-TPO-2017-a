package vista;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.Icon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import controlador.Gimnasio;
import views.DeporteView;

import javax.swing.ImageIcon;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.ListSelectionEvent;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;

public class AltaActividad extends JFrame {

	private JButton btnAlta;
	private JButton btnBuscar;
	private JButton btnNewButton;

	private JComboBox comboBox;
	private JPanel contentPane;
	private Icon icono;
	private ImageIcon imagen;
	private JLabel lblDeporte;
	private JLabel lblDescripcion;
	private JLabel lblEstado;
	private Gimnasio sistema;
	private JTextField tfDescripcion;
	private DefaultListModel listModel;
	private ArrayList<DeporteView> deportes;
	private DeporteView deporteElegido;
	private JComboBox comboDeportes;

	/**
	 * Create the frame.
	 */
	public AltaActividad() {
		setResizable(false);
		setTitle("Alta de actividad");
		sistema = Gimnasio.getInstancia();
		deportes = new ArrayList<DeporteView>();

		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

		setBounds(100, 100, 446, 199);
		getContentPane().setLayout(null);

		lblDeporte = new JLabel("Deporte:");
		lblDeporte.setHorizontalAlignment(SwingConstants.TRAILING);
		lblDeporte.setBounds(24, 33, 64, 14);
		getContentPane().add(lblDeporte);

		lblDescripcion = new JLabel("Descripci\u00F3n:");
		lblDescripcion.setHorizontalAlignment(SwingConstants.TRAILING);
		lblDescripcion.setBounds(5, 58, 83, 14);
		getContentPane().add(lblDescripcion);

		tfDescripcion = new JTextField();
		tfDescripcion.setBounds(110, 55, 300, 20);
		tfDescripcion.setHorizontalAlignment(SwingConstants.RIGHT);
		getContentPane().add(tfDescripcion);
		tfDescripcion.setColumns(10);

		lblEstado = new JLabel("Estado:");
		lblEstado.setHorizontalAlignment(SwingConstants.TRAILING);
		lblEstado.setBounds(9, 86, 79, 14);
		getContentPane().add(lblEstado);

		comboBox = new JComboBox();
		comboBox.setBounds(110, 83, 300, 20);
		comboBox.setModel(new DefaultComboBoxModel(new String[] { "Activo", "Inactivo" }));
		getContentPane().add(comboBox);

		btnAlta = new JButton("Alta");
		btnAlta.setBounds(111, 119, 302, 23);
		btnAlta.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				if (checkPantalla()) {
					sistema.altaActividad(deporteElegido.getId(), tfDescripcion.getText(),
							comboBox.getSelectedItem().toString());
					JOptionPane.showMessageDialog(null, "Actividad creada correctamente");
					limpiarPantalla();
				}
			}
		});
		getContentPane().add(btnAlta);

		comboDeportes = new JComboBox();
		comboDeportes.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent arg0) {
				int i = comboDeportes.getSelectedIndex() - 1;
				deporteElegido = null;
				if (i >= 0)
					deporteElegido = deportes.get(i);

			}
		});
		comboDeportes.setBounds(110, 30, 300, 20);
		getContentPane().add(comboDeportes);
		poblarComboBox();

	}

	private boolean checkPantalla() {

		if (deporteElegido != null && !tfDescripcion.getText().equals(""))
			return true;
		JOptionPane.showMessageDialog(null, "Por favor complete todos los campos");
		return false;
	}

	private void limpiarPantalla() {
		comboDeportes.setSelectedIndex(0);
		tfDescripcion.setText("");

	}

	private void poblarComboBox() {
		listModel = new DefaultListModel();
		deportes.clear();
		deportes = sistema.getAllDeportesView();
		comboDeportes.setModel(new DefaultComboBoxModel(deportes.toArray()));
		comboDeportes.insertItemAt("Seleccione un deporte", 0);
		comboDeportes.setSelectedIndex(0);

	}
}
