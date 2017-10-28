package vista;

import java.awt.EventQueue;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Time;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

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
import javax.swing.SpinnerDateModel;
import javax.swing.SwingConstants;

import com.toedter.calendar.JSpinnerDateEditor;

import controlador.Gimnasio;
import views.ActividadView;
import views.DeporteView;
import views.EmpleadoView;

import javax.swing.ImageIcon;
import javax.swing.JSpinner;
import javax.swing.JSpinner.DateEditor;

import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.ChangeEvent;

public class AltaClase extends JFrame {

	private JButton btnAlta;

	private JButton btnBuscarOtro;
	private JButton btnNewButton;
	private JComboBox comboBoxDia;
	private JComboBox comboBoxEstado;
	private JPanel contentPane;
	private JSpinner horaFin;
	private JSpinner horaInicio;
	private Icon icono;
	private ImageIcon imagen;
	private JLabel lblActividad;
	private JLabel lblDeporte;
	private JLabel lblDia;
	private JLabel lblEstado;
	private JLabel lblHoraFin;
	private JLabel lblHoraInicio;
	private JLabel lblProfesor;
	private Gimnasio sistema;
	private JTextField tfProfesores;
	private JComboBox comboDeportes;
	private JComboBox comboActividades;
	private ArrayList<DeporteView> deportes;
	private ArrayList<ActividadView> actividades;
	private DeporteView deporteElegido;
	private ArrayList<EmpleadoView> profesores;
	private ArrayList<EmpleadoView> profesoresElegidos;
	private ActividadView actividadElegida;

	private DefaultListModel listModel;

	private DefaultListModel listModel2;

	private JScrollPane scrollPane;

	private JList list;
	private JButton btnProfesores;

	/**
	 * Create the frame.
	 */
	public AltaClase() {
		setResizable(false);
		setTitle("Alta de clase");
		sistema = Gimnasio.getInstancia();

		deportes = new ArrayList<DeporteView>();
		actividades = new ArrayList<ActividadView>();
		profesores = new ArrayList<EmpleadoView>();
		profesoresElegidos = new ArrayList<EmpleadoView>();

		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

		setBounds(100, 100, 451, 309);
		getContentPane().setLayout(null);

		scrollPane = new JScrollPane();
		scrollPane.setBounds(144, 33, 247, 151);
		getContentPane().add(scrollPane);
		list = new JList();

		scrollPane.setViewportView(list);
		scrollPane.setVisible(false);

		lblDeporte = new JLabel("Deporte:");
		lblDeporte.setHorizontalAlignment(SwingConstants.TRAILING);
		lblDeporte.setBounds(24, 33, 64, 14);
		getContentPane().add(lblDeporte);

		lblEstado = new JLabel("Estado:");
		lblEstado.setHorizontalAlignment(SwingConstants.TRAILING);
		lblEstado.setBounds(9, 210, 79, 14);
		getContentPane().add(lblEstado);

		comboBoxEstado = new JComboBox();
		comboBoxEstado.setBounds(111, 207, 307, 20);
		comboBoxEstado.setModel(new DefaultComboBoxModel(new String[] { "Activo", "Inactivo" }));
		getContentPane().add(comboBoxEstado);

		btnAlta = new JButton("Alta");
		btnAlta.setBounds(270, 242, 143, 23);
		btnAlta.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				Date time = (Date) horaInicio.getValue();
				Time inicio = new Time(time.getHours(), time.getMinutes(), 0);
				time = (Date) horaFin.getValue();
				Time fin = new Time(time.getHours(), time.getMinutes(), 0);
				if (checkCampos()) {
					sistema.altaClase(actividadElegida.getCodDeporte(), actividadElegida.getCodActividad(), inicio, fin,
							comboBoxDia.getSelectedItem().toString(), profesoresElegidos,
							comboBoxEstado.getSelectedItem().toString());
					JOptionPane.showMessageDialog(null, "Clase creada correctamente");
					limpiarPantalla();
					comboDeportes.setSelectedIndex(0);
					comboActividades.removeAllItems();
					comboActividades.setEnabled(false);

				} else {
					JOptionPane.showMessageDialog(null, "Complete los campos correctamente");
				}
			}
		});
		getContentPane().add(btnAlta);

		imagen = new ImageIcon(AltaClase.class.getResource("/images/lupa.png"));

		lblActividad = new JLabel("Actividad:");
		lblActividad.setHorizontalAlignment(SwingConstants.TRAILING);
		lblActividad.setBounds(24, 62, 64, 14);
		getContentPane().add(lblActividad);

		SpinnerDateModel model = new SpinnerDateModel(new Date(), null, null, Calendar.HOUR_OF_DAY);
		horaInicio = new JSpinner(model);
		horaInicio.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				checkTimes();
			}
		});
		DateEditor de_horaInicio = new JSpinner.DateEditor(horaInicio, "HH:mm");
		horaInicio.setEditor(de_horaInicio);
		horaInicio.setBounds(111, 119, 153, 20);
		getContentPane().add(horaInicio);

		SpinnerDateModel model2 = new SpinnerDateModel(new Date(), null, null, Calendar.HOUR_OF_DAY);
		horaFin = new JSpinner(model2);
		horaFin.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent arg0) {
				checkTimes();

			}
		});
		DateEditor de_horaFin = new JSpinner.DateEditor(horaFin, "HH:mm");
		horaFin.setEditor(de_horaFin);
		horaFin.setBounds(111, 150, 153, 20);
		getContentPane().add(horaFin);

		lblHoraInicio = new JLabel("Hora de inicio:");
		lblHoraInicio.setHorizontalAlignment(SwingConstants.TRAILING);
		lblHoraInicio.setBounds(0, 119, 88, 14);
		getContentPane().add(lblHoraInicio);

		lblHoraFin = new JLabel("Hora de fin:");
		lblHoraFin.setHorizontalAlignment(SwingConstants.TRAILING);
		lblHoraFin.setBounds(5, 150, 83, 14);
		getContentPane().add(lblHoraFin);

		lblDia = new JLabel("Día:");
		lblDia.setHorizontalAlignment(SwingConstants.TRAILING);
		lblDia.setBounds(42, 92, 46, 14);
		getContentPane().add(lblDia);

		comboBoxDia = new JComboBox();
		comboBoxDia.setModel(new DefaultComboBoxModel(new String[] { "(Seleccione un valor)", "Lunes", "Martes",
				"Miercoles", "Jueves", "Viernes", "Sabado", "Domingo", "Libre" }));
		comboBoxDia.setBounds(111, 90, 307, 20);
		getContentPane().add(comboBoxDia);

		lblProfesor = new JLabel("Profesor/es:");
		lblProfesor.setHorizontalAlignment(SwingConstants.TRAILING);
		lblProfesor.setBounds(5, 182, 83, 14);
		getContentPane().add(lblProfesor);

		tfProfesores = new JTextField();
		tfProfesores.setBounds(111, 180, 269, 20);
		getContentPane().add(tfProfesores);
		tfProfesores.setColumns(10);

		btnBuscarOtro = new JButton("Buscar otro");
		btnBuscarOtro.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				limpiarPantalla();
				comboDeportes.setSelectedIndex(0);
				comboActividades.removeAllItems();
				comboActividades.setEnabled(false);
			}
		});
		btnBuscarOtro.setBounds(111, 242, 143, 23);
		getContentPane().add(btnBuscarOtro);

		comboDeportes = new JComboBox();
		comboDeportes.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {

				int i = comboDeportes.getSelectedIndex() - 1;
				deporteElegido = null;
				limpiarPantalla();
				if (i >= 0) {
					deporteElegido = deportes.get(i);
					comboActividades.setEnabled(true);
					poblarComboActividades();
				} else {
					comboActividades.setEnabled(false);
					comboActividades.removeAllItems();

				}

			}
		});
		comboDeportes.setBounds(111, 30, 307, 20);
		getContentPane().add(comboDeportes);

		comboActividades = new JComboBox();
		comboActividades.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				int i = comboActividades.getSelectedIndex() - 1;
				actividadElegida = null;
				if (i >= 0) {
					actividadElegida = actividades.get(i);
					mostrarPantalla();
				} else {
					limpiarPantalla();
				}
			}
		});
		comboActividades.setBounds(111, 59, 307, 20);
		getContentPane().add(comboActividades);
		comboActividades.setEnabled(false);

		btnProfesores = new JButton("");
		btnProfesores.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (!scrollPane.isVisible()) {
					scrollPane.setVisible(true);
					btnProfesores.setBackground(Color.YELLOW);
				} else {
					scrollPane.setVisible(false);
					profesoresElegidos.clear();
					btnProfesores.setBackground(null);
					for (int position : list.getSelectedIndices()) {
						profesoresElegidos.add(profesores.get(position));
					}
					cargarTfProfesores();
				}
			}
		});

		btnProfesores.setBounds(385, 178, 35, 25);
		imagen = new ImageIcon(AltaActividad.class.getResource("/images/lupa.png"));
		icono = new ImageIcon(imagen.getImage().getScaledInstance(btnProfesores.getWidth(), btnProfesores.getHeight(),
				Image.SCALE_DEFAULT));

		btnProfesores.setIcon(icono);
		getContentPane().add(btnProfesores);
		poblarList();

		limpiarPantalla();
		poblarComboDeportes();

	}

	private boolean checkCampos() {
		return (actividadElegida != null && comboBoxDia.getSelectedIndex() > 0);

	}

	private void poblarList() {
		listModel = new DefaultListModel();
		profesores.clear();
		profesores = sistema.getAllProfesoresView();

		for (EmpleadoView prof : profesores) {
			listModel.addElement(prof);
		}
		list.setModel(listModel);
	}

	private void checkTimes() {
		Date time = (Date) horaInicio.getValue();
		Time inicio = new Time(time.getHours(), time.getMinutes(), 0);
		time = (Date) horaFin.getValue();
		Time fin = new Time(time.getHours(), time.getMinutes(), 0);
		if (!inicio.before(fin)) {
			JOptionPane.showMessageDialog(null, "El horario de fin tiene que ser más tarde que el de inicio");
			fin.setMinutes(inicio.getMinutes() + 1);
			fin.setHours(inicio.getHours());
			horaFin.setValue(fin);
		}
	}

	private void poblarComboActividades() {
		listModel2 = new DefaultListModel();
		actividades.clear();
		actividades = sistema.getActividadesView(deporteElegido.getId());
		comboActividades.setModel(new DefaultComboBoxModel(actividades.toArray()));
		comboActividades.insertItemAt("Seleccione una actividad", 0);
		comboActividades.setSelectedIndex(0);
	}

	private void limpiarPantalla() {
		lblDia.setVisible(false);
		comboBoxDia.setVisible(false);
		lblHoraFin.setVisible(false);
		lblHoraInicio.setVisible(false);
		horaFin.setVisible(false);
		horaInicio.setVisible(false);
		lblProfesor.setVisible(false);
		tfProfesores.setVisible(false);
		lblEstado.setVisible(false);
		comboBoxEstado.setVisible(false);
		btnAlta.setVisible(false);
		btnBuscarOtro.setVisible(false);
		btnProfesores.setVisible(false);
		tfProfesores.setText("");
		comboBoxDia.setSelectedIndex(0);
		scrollPane.setVisible(false);


	}

	private void mostrarPantalla() {

		btnProfesores.setVisible(true);
		lblDia.setVisible(true);
		comboBoxDia.setVisible(true);
		lblHoraFin.setVisible(true);
		lblHoraInicio.setVisible(true);
		horaFin.setVisible(true);
		horaInicio.setVisible(true);
		lblProfesor.setVisible(true);
		tfProfesores.setVisible(true);
		lblEstado.setVisible(true);
		comboBoxEstado.setVisible(true);
		btnAlta.setVisible(true);
		btnBuscarOtro.setVisible(true);

		Date hora = (Date) horaInicio.getValue();
		hora.setMinutes(0);
		horaInicio.setValue(hora);
		hora.setHours(hora.getHours() + 1);
		horaFin.setValue(hora);

	}

	private void poblarComboDeportes() {
		listModel = new DefaultListModel();
		deportes.clear();
		deportes = sistema.getAllDeportesView();
		comboDeportes.setModel(new DefaultComboBoxModel(deportes.toArray()));
		comboDeportes.insertItemAt("Seleccione un deporte", 0);
		comboDeportes.setSelectedIndex(0);

	}

	private void cargarTfProfesores() {
		tfProfesores.setText("");
		for (EmpleadoView e : profesoresElegidos) {
			if (tfProfesores.getText().equals(""))
				tfProfesores.setText(e.toString());
			else
				tfProfesores.setText(tfProfesores.getText() + "; " + e);
		}

	}
}
