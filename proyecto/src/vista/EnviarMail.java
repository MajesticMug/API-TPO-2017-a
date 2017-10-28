package vista;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import controlador.Gimnasio;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JCheckBox;
import javax.swing.JSpinner;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.JSeparator;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;
import javax.swing.JEditorPane;
import javax.swing.SpinnerNumberModel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class EnviarMail extends JFrame {

	private JTextField asunto;
	private JButton btnEnviar;
	private JButton btnLimpiar;
	private JButton button;
	private JButton button_1;
	private JCheckBox chkAptoVencido;
	private JCheckBox chkDeporte;
	private JCheckBox chkInscripcionVencida;
	private JCheckBox chkTodos;
	private JSpinner codDeporte;
	private JPanel contentPane;
	private JEditorPane cuerpo;
	private JTextField destinatarios;
	private JTextField dni;
	private JLabel label;
	private JLabel lblAsunto;
	private JLabel lblGenerarMailPara;
	private JLabel lblPara;
	private JLabel limpiar;
	private JScrollPane scrollPane;
	private final JSeparator separator = new JSeparator();
	private JSeparator separator_1;
	private Gimnasio sistema;

	/**
	 * Create the frame.
	 */
	public EnviarMail() {
		setResizable(false);
		setTitle("Enviar mail");

		sistema = Gimnasio.getInstancia();
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 641, 648);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		lblGenerarMailPara = new JLabel("Generar mail para:");
		lblGenerarMailPara.setBounds(100, 11, 108, 14);
		contentPane.add(lblGenerarMailPara);

		chkInscripcionVencida = new JCheckBox("Socios con inscripci\u00F3n vencida");
		chkInscripcionVencida.setBounds(110, 32, 276, 23);
		contentPane.add(chkInscripcionVencida);

		chkAptoVencido = new JCheckBox("Socios con apto m\u00E9dico vencido");
		chkAptoVencido.setBounds(110, 58, 276, 23);
		contentPane.add(chkAptoVencido);

		chkDeporte = new JCheckBox("Socios que hacen el deporte de c\u00F3digo:");
		chkDeporte.setBounds(110, 84, 276, 23);
		contentPane.add(chkDeporte);

		codDeporte = new JSpinner();
		codDeporte.setModel(new SpinnerNumberModel(new Integer(0), new Integer(0), null, new Integer(1)));
		codDeporte.setBounds(392, 85, 61, 20);
		contentPane.add(codDeporte);

		limpiar = new JLabel("Limpiar destinatarios");
		limpiar.setHorizontalAlignment(SwingConstants.TRAILING);
		limpiar.setForeground(Color.RED);
		limpiar.setBounds(485, 241, 130, 23);
		limpiar.setVisible(false);
		contentPane.add(limpiar);

		chkTodos = new JCheckBox("Todos los socios");
		chkTodos.setBounds(111, 110, 229, 23);
		contentPane.add(chkTodos);
		separator.setBounds(0, 206, 651, 15);
		contentPane.add(separator);

		lblPara = new JLabel("Para:");
		lblPara.setHorizontalAlignment(SwingConstants.TRAILING);
		lblPara.setBounds(20, 220, 46, 14);
		contentPane.add(lblPara);

		lblAsunto = new JLabel("Asunto:");
		lblAsunto.setHorizontalAlignment(SwingConstants.TRAILING);
		lblAsunto.setBounds(10, 245, 56, 14);
		contentPane.add(lblAsunto);

		destinatarios = new JTextField();
		destinatarios.setBounds(76, 217, 501, 20);
		contentPane.add(destinatarios);
		destinatarios.setColumns(10);

		asunto = new JTextField();

		asunto.setColumns(10);
		asunto.setBounds(76, 242, 501, 20);
		contentPane.add(asunto);

		separator_1 = new JSeparator();
		separator_1.setBounds(-16, 271, 651, 15);
		contentPane.add(separator_1);

		scrollPane = new JScrollPane();
		scrollPane.setBounds(20, 290, 558, 278);
		contentPane.add(scrollPane);

		cuerpo = new JEditorPane();
		scrollPane.setViewportView(cuerpo);

		btnEnviar = new JButton("Enviar");
		btnEnviar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				if (sistema.enviarMail(asunto.getText(), cuerpo.getText())) {
					JOptionPane.showMessageDialog(null, "Mail enviado correctamente");

					asunto.setText("");
					cuerpo.setText("");
					dni.setText("");
					codDeporte.setValue(0);
				}
				destinatarios.setText(sistema.getDestinatariosActuales());

			}
		});
		btnEnviar.setBounds(100, 574, 406, 30);
		contentPane.add(btnEnviar);

		button_1 = new JButton("Generar destinatarios");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				if (chkAptoVencido.isSelected())
					sistema.generarMailAptoMedico("Inactivo");
				if (chkDeporte.isSelected())
					sistema.generarMailDeporte(Integer.parseInt(codDeporte.getValue().toString()));
				if (chkInscripcionVencida.isSelected())
					sistema.generarMaiInscripcionVencida();
				if (chkTodos.isSelected())
					sistema.generarMailTodos("Activo");

				String para = sistema.getDestinatariosActuales();
				destinatarios.setText(para);
				if (destinatarios.equals(""))
					JOptionPane.showMessageDialog(null,
							"No se encontraron destinatarios para su criterio de selección");

			}
		});
		button_1.setBounds(100, 139, 406, 30);
		contentPane.add(button_1);

		label = new JLabel("Socio espec\u00EDfico por DNI:");
		label.setBounds(110, 181, 199, 14);
		contentPane.add(label);

		button = new JButton("+");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				sistema.generarMailParticular(dni.getText());
				destinatarios.setText(sistema.getDestinatariosActuales());
			}
		});
		button.setBounds(465, 170, 41, 30);
		contentPane.add(button);

		dni = new JTextField();
		dni.setHorizontalAlignment(SwingConstants.TRAILING);
		dni.setColumns(10);
		dni.setBounds(352, 176, 101, 20);
		contentPane.add(dni);

		btnLimpiar = new JButton("-");
		btnLimpiar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent arg0) {
				limpiar.setVisible(true);
			}

			@Override
			public void mouseExited(MouseEvent e) {
				limpiar.setVisible(false);

			}
		});

		btnLimpiar.setForeground(Color.RED);
		btnLimpiar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				sistema.limpiarDestinatariosMail();
				destinatarios.setText(sistema.getDestinatariosActuales());
			}
		});
		btnLimpiar.setBounds(583, 218, 41, 23);
		contentPane.add(btnLimpiar);

	}

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					EnviarMail frame = new EnviarMail();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}
