package vista;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

import controlador.Gimnasio;

import javax.swing.JTable;
import java.awt.Color;
import java.awt.SystemColor;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Date;

import javax.swing.JScrollPane;
import javax.swing.JSpinner.DateEditor;
import javax.swing.JLabel;
import com.toedter.calendar.JDateChooser;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ConsultarCronograma extends JFrame {

	private JButton btnGenerar;
	private JPanel contentPane;
	private JDateChooser desde;
	private JDateChooser hasta;
	private JLabel lblDesde;
	private JLabel lblHasta;
	private JScrollPane scrollPane;
	private Gimnasio sistema;
	private JTable table;

	public ConsultarCronograma() {
		setResizable(false);
		setTitle("Cronograma");
		sistema = Gimnasio.getInstancia();
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setSize(1200, 525);

		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 50, 1160, 417);
		contentPane.add(scrollPane);

		table = new JTable();
		scrollPane.setViewportView(table);

		lblDesde = new JLabel("Desde:");
		lblDesde.setBounds(10, 15, 54, 14);
		contentPane.add(lblDesde);

		lblHasta = new JLabel("Hasta:");
		lblHasta.setBounds(262, 15, 54, 14);
		contentPane.add(lblHasta);

		desde = new JDateChooser();
		desde.setBounds(56, 11, 176, 20);
		contentPane.add(desde);

		hasta = new JDateChooser();
		hasta.setBounds(322, 11, 176, 20);
		contentPane.add(hasta);

		btnGenerar = new JButton("Generar");
		btnGenerar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				poblarTabla();
			}
		});
		btnGenerar.setBounds(566, 9, 180, 30);
		contentPane.add(btnGenerar);
		scrollPane.setVisible(false);

	}

	private LocalDate dateToLocalDate(Date fecha) {

		Instant instant = fecha.toInstant();
		ZonedDateTime zdt = instant.atZone(ZoneId.systemDefault());
		LocalDate date = zdt.toLocalDate();

		return date;
	}

	private void poblarTabla() {
		String[][] datos = sistema.generarCronograma(dateToLocalDate(desde.getDate()),
				dateToLocalDate(hasta.getDate()));
		DefaultTableModel modelo = new DefaultTableModel(new Object[][] {}, datos[0]);
		scrollPane.setViewportView(table);
		for (int i = 1; i < datos.length; i++) {
			modelo.addRow(datos[i]);

		}
		table.setModel(modelo);
		scrollPane.setVisible(true);

	}
}
