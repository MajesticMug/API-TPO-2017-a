package vista;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import controlador.Gimnasio;
import javax.swing.JSeparator;

public class MenuPrincipal extends JFrame {

	private JPanel contentPane;
	private Gimnasio sistema;

	/**
	 * Create the frame.
	 */
	public MenuPrincipal() {
		sistema = Gimnasio.getInstancia();
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

		setBounds(100, 100, 575, 342);
		setLocationRelativeTo(null);
		setResizable(false);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JMenuBar menuBar = new JMenuBar();
		menuBar.setBounds(0, 0, getWidth(), 21);
		contentPane.add(menuBar);

		JMenu mnSocios = new JMenu("Socios");
		menuBar.add(mnSocios);

		JMenuItem mntmAlta = new JMenuItem("Alta");
		mntmAlta.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				AltaSocio w = new AltaSocio();
				w.setVisible(true);
			}
		});
		mnSocios.add(mntmAlta);

		JMenuItem mntmBaja = new JMenuItem("Baja");
		mntmBaja.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				BajaSocio w = new BajaSocio();
				w.setVisible(true);
			}
		});
		mnSocios.add(mntmBaja);

		JMenuItem mntmModificar = new JMenuItem("Modificar");
		mntmModificar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ModificarSocio w = new ModificarSocio();
				w.setVisible(true);
			}
		});
		mnSocios.add(mntmModificar);

		JSeparator separator_2 = new JSeparator();
		mnSocios.add(separator_2);

		JMenu mnAptosMdicos = new JMenu("Aptos M\u00E9dicos");
		mnSocios.add(mnAptosMdicos);

		JMenuItem mntmAlta_4 = new JMenuItem("Alta");
		mntmAlta_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AltaApto w = new AltaApto();
				w.setVisible(true);
			}
		});
		mnAptosMdicos.add(mntmAlta_4);

		JMenuItem mntmModificar_4 = new JMenuItem("Modificar");
		mntmModificar_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				ModificarApto m = new ModificarApto();
				m.setVisible(true);
			}
		});
		mnAptosMdicos.add(mntmModificar_4);

		JMenu mnDeportes = new JMenu("Deportes");
		menuBar.add(mnDeportes);

		JMenuItem mntmAltaD = new JMenuItem("Alta");
		mntmAltaD.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				AltaDeporte w = new AltaDeporte();
				w.setVisible(true);

			}
		});
		mnDeportes.add(mntmAltaD);

		JMenuItem mntmBajaD = new JMenuItem("Baja");
		mntmBajaD.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				BajaDeporte w = new BajaDeporte();
				w.setVisible(true);
			}
		});
		mnDeportes.add(mntmBajaD);

		JMenuItem mntmModificarD = new JMenuItem("Modificar");
		mntmModificarD.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ModificarDeporte w = new ModificarDeporte();
				w.setVisible(true);
			}
		});
		mnDeportes.add(mntmModificarD);

		JMenu mnActividades = new JMenu("Actividades");
		menuBar.add(mnActividades);

		JMenuItem mntmAlta_2 = new JMenuItem("Alta");
		mntmAlta_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				AltaActividad a = new AltaActividad();
				a.setVisible(true);
			}
		});
		mnActividades.add(mntmAlta_2);

		JMenuItem mntmBaja_2 = new JMenuItem("Baja");
		mntmBaja_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				BajaActividad a = new BajaActividad();
				a.setVisible(true);
			}
		});
		mnActividades.add(mntmBaja_2);

		JMenuItem mntmModificar_2 = new JMenuItem("Modificar");
		mntmModificar_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				ModificarActividad m = new ModificarActividad();
				m.setVisible(true);
			}
		});
		mnActividades.add(mntmModificar_2);

		JSeparator separator_3 = new JSeparator();
		mnActividades.add(separator_3);

		JMenu mnClases = new JMenu("Clases");
		mnActividades.add(mnClases);

		JMenuItem mntmAlta_1 = new JMenuItem("Alta");
		mntmAlta_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AltaClase m = new AltaClase();
				m.setVisible(true);
			}
		});
		mnClases.add(mntmAlta_1);

		JMenuItem mntmBaja_1 = new JMenuItem("Baja");
		mntmBaja_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				BajaClase m = new BajaClase();
				m.setVisible(true);
			}
		});
		mnClases.add(mntmBaja_1);

		JMenuItem mntmModificar_1 = new JMenuItem("Modificar");
		mntmModificar_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ModificarClase c = new ModificarClase();
				c.setVisible(true);
			}
		});
		mnClases.add(mntmModificar_1);

		JMenu mnAbono = new JMenu("Abono");
		menuBar.add(mnAbono);

		JMenuItem mntmAlta_3 = new JMenuItem("Alta");
		mntmAlta_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AltaAbono m = new AltaAbono();
				m.setVisible(true);

			}
		});
		mnAbono.add(mntmAlta_3);

		JMenuItem mntmBaja_3 = new JMenuItem("Baja");
		mntmBaja_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				BajaAbono m = new BajaAbono();
				m.setVisible(true);
			}
		});
		mnAbono.add(mntmBaja_3);

		JMenuItem mntmModificar_3 = new JMenuItem("Modificar");
		mntmModificar_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ModificarAbono m = new ModificarAbono();
				m.setVisible(true);
			}
		});
		mnAbono.add(mntmModificar_3);

		JMenu mnEmpleados = new JMenu("Empleados");
		menuBar.add(mnEmpleados);

		JMenuItem mntmAlta_6 = new JMenuItem("Alta");
		mntmAlta_6.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AltaEmpleado m = new AltaEmpleado();
				m.setVisible(true);
			}
		});
		mnEmpleados.add(mntmAlta_6);

		JMenuItem mntmBaja_5 = new JMenuItem("Baja");
		mntmBaja_5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				BajaEmpleado m = new BajaEmpleado();
				m.setVisible(true);
			}
		});
		mnEmpleados.add(mntmBaja_5);

		JMenuItem mntmModificar_6 = new JMenuItem("Modificar");
		mntmModificar_6.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ModificarEmpleado m = new ModificarEmpleado();
				m.setVisible(true);
			}
		});
		mnEmpleados.add(mntmModificar_6);

		JMenuItem mntmRecibos = new JMenuItem("Consultar recibo");
		mntmRecibos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				ConsultarRecibo c = new ConsultarRecibo();
				c.setVisible(true);
			}
		});
		mnEmpleados.add(mntmRecibos);

		JSeparator separator_4 = new JSeparator();
		mnEmpleados.add(separator_4);

		JMenuItem mntmActualizarEscalasSalariales = new JMenuItem("Actualizar escalas salariales");
		mntmActualizarEscalasSalariales.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				if (JOptionPane.showConfirmDialog(null,
						"Se actualizarán las escalas salariales correspondientes a cada empleado según su útlimo recibo de sueldo\nDesea continuar?",
						"Actualizar escalas salariales", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
					sistema.actualizarEscalas();
					JOptionPane.showMessageDialog(null, "Se han actualizado las escalas salariales de los empleados");
				}
			}
		});
		mnEmpleados.add(mntmActualizarEscalasSalariales);

		JMenu mnEmpresas = new JMenu("Empresas");
		menuBar.add(mnEmpresas);

		JMenuItem mntmCrear = new JMenuItem("Alta");
		mntmCrear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AltaEmpresa a = new AltaEmpresa();
				a.setVisible(true);
			}
		});
		mnEmpresas.add(mntmCrear);

		JMenuItem mntmModificar_7 = new JMenuItem("Modificar");
		mntmModificar_7.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ModificarEmpresa m = new ModificarEmpresa();
				m.setVisible(true);
			}
		});
		mnEmpresas.add(mntmModificar_7);

		JMenuItem mntmBaja_6 = new JMenuItem("Baja");
		mntmBaja_6.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				BajaEmpresa b = new BajaEmpresa();
				b.setVisible(true);

			}
		});
		mnEmpresas.add(mntmBaja_6);

		JSeparator separator = new JSeparator();
		mnEmpresas.add(separator);

		JMenuItem mntmCrearConvenio = new JMenuItem("Alta convenio");
		mntmCrearConvenio.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AltaConvenio m = new AltaConvenio();
				m.setVisible(true);
			}
		});
		mnEmpresas.add(mntmCrearConvenio);

		JMenuItem mntmModificarConvenio = new JMenuItem("Modificar convenio");
		mntmModificarConvenio.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ModificarConvenio m = new ModificarConvenio();
				m.setVisible(true);
			}
		});
		mnEmpresas.add(mntmModificarConvenio);

		JSeparator separator_1 = new JSeparator();
		mnEmpresas.add(separator_1);

		JMenuItem mntmVincularASocio = new JMenuItem("Vincular a socio");
		mntmVincularASocio.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				EmpresaSocio m = new EmpresaSocio();
				m.setVisible(true);
			}
		});
		mnEmpresas.add(mntmVincularASocio);

		JMenu mnEscalaSalariales = new JMenu("Escala salariales");
		menuBar.add(mnEscalaSalariales);

		JMenuItem mntmAlta_5 = new JMenuItem("Alta");
		mntmAlta_5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AltaEscala esc = new AltaEscala();
				esc.setVisible(true);
			}
		});
		mnEscalaSalariales.add(mntmAlta_5);

		JMenuItem mntmBaja_4 = new JMenuItem("Baja");
		mntmBaja_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				BajaEscala esc = new BajaEscala();
				esc.setVisible(true);
			}
		});
		mnEscalaSalariales.add(mntmBaja_4);

		JMenuItem mntmModificar_5 = new JMenuItem("Modificar");
		mntmModificar_5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ModificarEscala esc = new ModificarEscala();
				esc.setVisible(true);
			}
		});
		mnEscalaSalariales.add(mntmModificar_5);

		JButton btnGenerarCronograma = new JButton("Generar cronograma");
		btnGenerarCronograma.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				ConsultarCronograma c = new ConsultarCronograma();
				c.setVisible(true);
			}
		});
		btnGenerarCronograma.setBounds(141, 57, 250, 23);
		contentPane.add(btnGenerarCronograma);

		JButton btnInscripciones = new JButton("Generar inscripción");
		btnInscripciones.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				GenerarInscripcion m = new GenerarInscripcion();
				m.setVisible(true);

			}
		});
		btnInscripciones.setBounds(141, 99, 250, 23);
		contentPane.add(btnInscripciones);

		JButton btnEnviarMails = new JButton("Enviar mails");
		btnEnviarMails.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				EnviarMail e = new EnviarMail();
				e.setVisible(true);
			}
		});
		btnEnviarMails.setBounds(141, 143, 250, 23);
		contentPane.add(btnEnviarMails);

		JButton btnValidarIngreso = new JButton("Validar ingreso");
		btnValidarIngreso.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String dni = JOptionPane.showInputDialog(null, "Dni:");
				if (dni != null) {
					if (sistema.validarIngreso(dni))
						JOptionPane.showMessageDialog(null, "Aceptado", "Ingreso correcto",
								JOptionPane.INFORMATION_MESSAGE);
					else
						JOptionPane.showMessageDialog(null, "Denegado", "Ingreso denegado", JOptionPane.ERROR_MESSAGE);
				}

			}
		});
		btnValidarIngreso.setBounds(141, 185, 250, 23);
		contentPane.add(btnValidarIngreso);

		JButton btnGenerarRecibos = new JButton("Liquidar sueldos");
		btnGenerarRecibos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (JOptionPane.showConfirmDialog(null,
						"Desea generar los recibos correspondientes al mes " + LocalDate.now().getMonthValue() + "?",
						"Generar recibos", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
					sistema.generarRecibos();
					JOptionPane.showMessageDialog(null, "Se generaron los recibos");

				}
			}
		});
		btnGenerarRecibos.setBounds(141, 225, 250, 23);
		contentPane.add(btnGenerarRecibos);

	}

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MenuPrincipal frame = new MenuPrincipal();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}
