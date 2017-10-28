package vista;

import java.awt.EventQueue;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;

import controlador.Gimnasio;
import views.AbonoView;
import views.ActividadView;

import com.toedter.calendar.JDateChooser;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import javax.swing.event.ChangeListener;
import javax.swing.event.ChangeEvent;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.AbstractListModel;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.ListSelectionEvent;
import java.awt.Color;

public class AltaAbono extends JFrame {

	private JButton btnAlta;

	private JButton button;
	private JComboBox comboBox;
	private JPanel contentPane;
	private JDateChooser dateChooser;
	private JLabel lblActividades;
	private JLabel lblEstado;
	private JLabel lblInicio;
	private JLabel lblMeses;
	private JLabel lblNombre;
	private JLabel lblPeriodo;
	private JLabel lblPrecio;
	private JList list;
	private DefaultListModel listModel;
	private JScrollPane scrollPane;
	private Gimnasio sistema;
	private JSpinner spinner;
	private JTextField tfActividades;
	private JTextField tfNombre;
	private JTextField tfPrecio;
	ArrayList<ActividadView> actividades;
	ArrayList<ActividadView> actividadesElegidas;

	private ImageIcon imagen;
	private Icon icono;
	public AltaAbono() {
		setResizable(false);
		setTitle("Alta de abono");
		sistema = Gimnasio.getInstancia();

		actividadesElegidas = new ArrayList<ActividadView>();
		actividades = new ArrayList<ActividadView>();

		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

		setBounds(100, 100, 451, 266);
		getContentPane().setLayout(null);

		scrollPane = new JScrollPane();
		scrollPane.setBounds(98, 11, 296, 150);
		getContentPane().add(scrollPane);

		list = new JList();

		scrollPane.setViewportView(list);
		scrollPane.setVisible(false);

		lblNombre = new JLabel("Nombre:");
		lblNombre.setHorizontalAlignment(SwingConstants.TRAILING);
		lblNombre.setBounds(18, 33, 70, 14);
		getContentPane().add(lblNombre);

		tfNombre = new JTextField();
		tfNombre.setBounds(111, 30, 307, 20);
		tfNombre.setHorizontalAlignment(SwingConstants.RIGHT);
		getContentPane().add(tfNombre);
		tfNombre.setColumns(10);

		lblPrecio = new JLabel("Precio:");
		lblPrecio.setHorizontalAlignment(SwingConstants.TRAILING);
		lblPrecio.setBounds(-1, 58, 89, 14);
		getContentPane().add(lblPrecio);

		tfPrecio = new JTextField();
		tfPrecio.setBounds(111, 55, 307, 20);
		tfPrecio.setHorizontalAlignment(SwingConstants.RIGHT);
		getContentPane().add(tfPrecio);
		tfPrecio.setColumns(10);

		lblInicio = new JLabel("Inicio Vigencia:");
		lblInicio.setHorizontalAlignment(SwingConstants.TRAILING);
		lblInicio.setBounds(-24, 83, 112, 14);
		getContentPane().add(lblInicio);

		lblPeriodo = new JLabel("Periodo pago:");
		lblPeriodo.setHorizontalAlignment(SwingConstants.TRAILING);
		lblPeriodo.setBounds(-4, 108, 92, 14);
		getContentPane().add(lblPeriodo);

		lblEstado = new JLabel("Estado:");
		lblEstado.setHorizontalAlignment(SwingConstants.TRAILING);
		lblEstado.setBounds(3, 169, 85, 14);
		getContentPane().add(lblEstado);

		comboBox = new JComboBox();
		comboBox.setBounds(111, 166, 307, 20);
		comboBox.setModel(new DefaultComboBoxModel(new String[] { "Activo", "Inactivo" }));
		getContentPane().add(comboBox);

		btnAlta = new JButton("Alta");
		btnAlta.setBounds(111, 202, 307, 23);
		btnAlta.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				sistema.altaAbono(tfNombre.getText(), Float.parseFloat(tfPrecio.getText()),
						dateToLocalDate(dateChooser.getDate()), (Integer) spinner.getValue(), actividadesElegidas,
						comboBox.getSelectedItem().toString());
				JOptionPane.showMessageDialog(null, "Abono creado correctamente");
				limpiarPantalla();
			}
		});
		getContentPane().add(btnAlta);

		dateChooser = new JDateChooser();
		dateChooser.setBounds(111, 81, 307, 20);
		getContentPane().add(dateChooser);

		lblActividades = new JLabel("Actividades:");
		lblActividades.setHorizontalAlignment(SwingConstants.TRAILING);
		lblActividades.setBounds(6, 140, 82, 14);
		getContentPane().add(lblActividades);

		tfActividades = new JTextField();
		tfActividades.setBounds(111, 136, 270, 20);
		getContentPane().add(tfActividades);
		tfActividades.setColumns(10);

		spinner = new JSpinner();
		spinner.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent arg0) {

				int n = (Integer) spinner.getValue();
				String lbl = "Pagar cada " + n;
				if (n == 1)
					lblMeses.setText(lbl + " mes");
				else
					lblMeses.setText(lbl + " meses");
			}
		});
		spinner.setModel(new SpinnerNumberModel(1, 1, 12, 1));
		spinner.setBounds(111, 105, 117, 20);
		getContentPane().add(spinner);

		lblMeses = new JLabel("Pagar cada 1 mes");
		lblMeses.setBounds(238, 108, 146, 14);
		getContentPane().add(lblMeses);

		button = new JButton("");
		button.setOpaque(true);
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (!scrollPane.isVisible()){
					scrollPane.setVisible(true);
					button.setBackground(Color.YELLOW);
				}
				else {
					scrollPane.setVisible(false);
					actividadesElegidas.clear();
					button.setBackground(null);
					for (int position : list.getSelectedIndices()) {
						actividadesElegidas.add(actividades.get(position));
					}
					cargarTfActs();
				}
			}
		});
		button.setBounds(389, 136, 31, 23);
		imagen = new ImageIcon(AltaActividad.class.getResource("/images/lupa.png"));
		icono = new ImageIcon(
				imagen.getImage().getScaledInstance(button.getWidth(), button.getHeight(), Image.SCALE_DEFAULT));

		button.setIcon(icono);
		getContentPane().add(button);

		poblarList();
	}

	private void cargarTfActs() {
		tfActividades.setText("");
		for (ActividadView act : actividadesElegidas) {
			if (tfActividades.getText().equals(""))
				tfActividades.setText(act.toString());
			else
				tfActividades.setText(tfActividades.getText() + "; " + act);
		}

	}

	private LocalDate dateToLocalDate(Date fecha) {

		Instant instant = fecha.toInstant();
		ZonedDateTime zdt = instant.atZone(ZoneId.systemDefault());
		LocalDate date = zdt.toLocalDate();

		return date;
	}

	private void limpiarPantalla() {
		tfNombre.setText("");
		tfPrecio.setText("");
		dateChooser.setDate(null);
		tfActividades.setText("");
		poblarList();

	}

	private Date localDateToDate(LocalDate fecha) {

		Instant instant = fecha.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant();
		return Date.from(instant);

	}

	private void poblarList() {
		listModel = new DefaultListModel();
		actividades.clear();
		actividades = sistema.getAllActividadesView();

		for (ActividadView act : actividades) {
			listModel.addElement(act);
		}
		list.setModel(listModel);
	}
}
