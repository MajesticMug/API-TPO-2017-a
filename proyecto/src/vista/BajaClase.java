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
import views.ClaseView;
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

public class BajaClase extends JFrame {

	private JButton btnBaja;

	private JButton btnBuscarOtro;
	private JButton btnNewButton;
	private JComboBox comboBoxDia;
	private JPanel contentPane;
	private JSpinner horaFin;
	private JSpinner horaInicio;
	private Icon icono;
	private ImageIcon imagen;
	private JLabel lblActividad;
	private JLabel lblDeporte;
	private JLabel lblDia;
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
	private ArrayList<ClaseView> clases;
	private ClaseView claseElegida;

	private DefaultListModel listModel;

	private DefaultListModel listModel2;
	private JLabel lblClase;
	private JComboBox comboClases;

	private DefaultListModel listModel3;
	private JLabel lblEstado;
	private JComboBox comboBox;

	/**
	 * Create the frame.
	 */
	public BajaClase() {
		setResizable(false);
		setTitle("Modificar clase");
		sistema = Gimnasio.getInstancia();

		deportes = new ArrayList<DeporteView>();
		actividades = new ArrayList<ActividadView>();
		profesores = new ArrayList<EmpleadoView>();
		profesoresElegidos = new ArrayList<EmpleadoView>();
		clases = new ArrayList<ClaseView>();

		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

		setBounds(100, 100, 451, 331);
		getContentPane().setLayout(null);

		lblDeporte = new JLabel("Deporte:");
		lblDeporte.setHorizontalAlignment(SwingConstants.TRAILING);
		lblDeporte.setBounds(24, 33, 64, 14);
		getContentPane().add(lblDeporte);

		btnBaja = new JButton("Baja");
		btnBaja.setBounds(270, 269, 143, 23);
		btnBaja.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				sistema.bajaClase(actividadElegida.getCodDeporte(), actividadElegida.getCodActividad(),
						claseElegida.getCodClase(), comboBox.getSelectedItem().toString());
				JOptionPane.showMessageDialog(null, "Clase borrada correctamente");
				limpiarPantalla();
				comboDeportes.setSelectedIndex(0);
				comboActividades.removeAllItems();
				comboActividades.setEnabled(false);
				comboClases.removeAllItems();
				comboClases.setEnabled(false);

			}
		});
		getContentPane().add(btnBaja);

		lblActividad = new JLabel("Actividad:");
		lblActividad.setHorizontalAlignment(SwingConstants.TRAILING);
		lblActividad.setBounds(24, 62, 64, 14);
		getContentPane().add(lblActividad);

		SpinnerDateModel model = new SpinnerDateModel(new Date(), null, null, Calendar.HOUR_OF_DAY);
		horaInicio = new JSpinner(model);
		horaInicio.setEnabled(false);
		horaInicio.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				checkTimes();
			}
		});
		DateEditor de_horaInicio = new JSpinner.DateEditor(horaInicio, "HH:mm");
		horaInicio.setEditor(de_horaInicio);
		horaInicio.setBounds(111, 147, 153, 20);
		getContentPane().add(horaInicio);

		SpinnerDateModel model2 = new SpinnerDateModel(new Date(), null, null, Calendar.HOUR_OF_DAY);
		horaFin = new JSpinner(model2);
		horaFin.setEnabled(false);
		horaFin.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent arg0) {
				checkTimes();

			}
		});
		DateEditor de_horaFin = new JSpinner.DateEditor(horaFin, "HH:mm");
		horaFin.setEditor(de_horaFin);
		horaFin.setBounds(111, 178, 153, 20);
		getContentPane().add(horaFin);

		lblHoraInicio = new JLabel("Hora de inicio:");
		lblHoraInicio.setHorizontalAlignment(SwingConstants.TRAILING);
		lblHoraInicio.setBounds(0, 147, 88, 14);
		getContentPane().add(lblHoraInicio);

		lblHoraFin = new JLabel("Hora de fin:");
		lblHoraFin.setHorizontalAlignment(SwingConstants.TRAILING);
		lblHoraFin.setBounds(5, 178, 83, 14);
		getContentPane().add(lblHoraFin);

		lblDia = new JLabel("Día:");
		lblDia.setHorizontalAlignment(SwingConstants.TRAILING);
		lblDia.setBounds(42, 120, 46, 14);
		getContentPane().add(lblDia);

		comboBoxDia = new JComboBox();
		comboBoxDia.setEnabled(false);
		comboBoxDia.setModel(new DefaultComboBoxModel(new String[] { "(Seleccione un valor)", "Lunes", "Martes",
				"Miercoles", "Jueves", "Viernes", "Sabado", "Domingo", "Libre" }));
		comboBoxDia.setBounds(111, 118, 307, 20);
		getContentPane().add(comboBoxDia);

		lblProfesor = new JLabel("Profesor/es:");
		lblProfesor.setHorizontalAlignment(SwingConstants.TRAILING);
		lblProfesor.setBounds(5, 210, 83, 14);
		getContentPane().add(lblProfesor);

		tfProfesores = new JTextField();
		tfProfesores.setEnabled(false);
		tfProfesores.setBounds(111, 208, 302, 20);
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
		btnBuscarOtro.setBounds(111, 269, 143, 23);
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
					comboClases.setEnabled(true);
					poblarComboClases();
				} else {
					comboClases.setEnabled(false);
					comboClases.removeAllItems();
				}
			}
		});
		comboActividades.setBounds(111, 59, 307, 20);
		getContentPane().add(comboActividades);
		comboActividades.setEnabled(false);
		imagen = new ImageIcon(AltaActividad.class.getResource("/images/lupa.png"));

		comboClases = new JComboBox();
		comboClases.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				int i = comboClases.getSelectedIndex() - 1;
				claseElegida = null;
				if (i >= 0) {
					claseElegida = clases.get(i);
					mostrarPantalla();
				} else {
					limpiarPantalla();
				}
			}
		});
		comboClases.setEnabled(false);
		comboClases.setBounds(111, 87, 307, 20);
		getContentPane().add(comboClases);

		lblClase = new JLabel("Clase:");
		lblClase.setHorizontalAlignment(SwingConstants.TRAILING);
		lblClase.setBounds(45, 87, 46, 14);
		getContentPane().add(lblClase);

		lblEstado = new JLabel("Estado:");
		lblEstado.setHorizontalAlignment(SwingConstants.TRAILING);
		lblEstado.setBounds(0, 243, 88, 14);
		getContentPane().add(lblEstado);

		comboBox = new JComboBox();
		comboBox.setEnabled(false);
		comboBox.setModel(new DefaultComboBoxModel(new String[] { "Inactivo", "Activo" }));
		comboBox.setBounds(111, 239, 302, 20);
		getContentPane().add(comboBox);
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
		btnBaja.setVisible(false);
		btnBuscarOtro.setVisible(false);
		tfProfesores.setText("");
		comboBoxDia.setSelectedIndex(0);
		comboBox.setVisible(false);
		lblEstado.setVisible(false);

	}

	private void mostrarPantalla() {

		lblDia.setVisible(true);
		comboBoxDia.setVisible(true);
		lblHoraFin.setVisible(true);
		lblHoraInicio.setVisible(true);
		horaFin.setVisible(true);
		horaInicio.setVisible(true);
		lblProfesor.setVisible(true);
		tfProfesores.setVisible(true);
		btnBaja.setVisible(true);
		btnBuscarOtro.setVisible(true);
		comboBox.setVisible(true);
		lblEstado.setVisible(true);

		Date hora = (Date) horaInicio.getValue();
		hora.setMinutes(0);
		horaInicio.setValue(hora);
		hora.setHours(hora.getHours() + 1);
		horaFin.setValue(hora);

		if (claseElegida != null) {
			comboBoxDia.setSelectedIndex(claseElegida.diaANumbero());
			horaInicio.setValue(claseElegida.getHorarioInicio());
			horaFin.setValue(claseElegida.getHorarioFin());
			profesoresElegidos = claseElegida.getProfesores();
			cargarTfProfesores();
		}

	}

	private void poblarComboDeportes() {
		listModel = new DefaultListModel();
		deportes.clear();
		deportes = sistema.getAllDeportesView();
		comboDeportes.setModel(new DefaultComboBoxModel(deportes.toArray()));
		comboDeportes.insertItemAt("Seleccione un deporte", 0);
		comboDeportes.setSelectedIndex(0);

	}

	private void poblarComboClases() {
		listModel3 = new DefaultListModel();
		clases.clear();
		clases = sistema.getClasesView(actividadElegida.getCodDeporte(), actividadElegida.getCodActividad());
		comboClases.setModel(new DefaultComboBoxModel(clases.toArray()));
		comboClases.insertItemAt("Seleccione una clase", 0);
		comboClases.setSelectedIndex(0);

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
