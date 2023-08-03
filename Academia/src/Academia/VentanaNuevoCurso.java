package Academia;

import java.awt.Cursor;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

public class VentanaNuevoCurso extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JButton btnNewButton;
	private JButton btnNewButton_1;
	private AcademiaModel academiaModel;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VentanaNuevoCurso frame = new VentanaNuevoCurso();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public VentanaNuevoCurso() {
		try {
			academiaModel = new AcademiaModel();
		} catch (SQLException e1) {
			e1.printStackTrace();
		}

		setTitle("NuevoCurso");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setLocationRelativeTo(null);

		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblNewLabel = new JLabel("Nombre:");
		lblNewLabel.setBounds(10, 63, 77, 14);
		contentPane.add(lblNewLabel);

		textField = new JTextField();
		textField.setBounds(97, 60, 86, 20);
		contentPane.add(textField);
		textField.setColumns(10);

		JLabel lblNewLabel_1 = new JLabel("Fecha inicio:");
		lblNewLabel_1.setBounds(10, 99, 77, 14);
		contentPane.add(lblNewLabel_1);

		textField_1 = new JTextField();
		textField_1.setBounds(97, 96, 86, 20);
		contentPane.add(textField_1);
		textField_1.setColumns(10);

		JLabel lblNewLabel_2 = new JLabel("Fecha fin: ");
		lblNewLabel_2.setBounds(10, 136, 77, 14);
		contentPane.add(lblNewLabel_2);

		textField_2 = new JTextField();
		textField_2.setBounds(97, 127, 86, 20);
		contentPane.add(textField_2);
		textField_2.setColumns(10);

		JLabel lblNewLabel_3 = new JLabel("Duracion:");
		lblNewLabel_3.setBounds(10, 161, 77, 14);
		contentPane.add(lblNewLabel_3);

		textField_3 = new JTextField();
		textField_3.setBounds(97, 158, 86, 20);
		contentPane.add(textField_3);
		textField_3.setColumns(10);

		JLabel lblNewLabel_4 = new JLabel("DATOS NUEVO CURSO");
		lblNewLabel_4.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNewLabel_4.setBounds(120, 11, 191, 14);
		contentPane.add(lblNewLabel_4);

		btnNewButton = new JButton("GUARDAR");
		btnNewButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnNewButton.setBounds(96, 227, 108, 23);
		contentPane.add(btnNewButton);

		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String nombre = textField.getText();
				String fechaInicio = textField_1.getText();
				String fechaFin = textField_2.getText();
				String duracionStr = textField_3.getText();

				if (nombre.isEmpty() || fechaInicio.isEmpty() || fechaFin.isEmpty() || duracionStr.isEmpty()) {
					System.out.println("Introduce los datos");
					return;
				}

				int duracion;
				try {
					duracion = Integer.parseInt(duracionStr);
				} catch (NumberFormatException ex) {
					System.out.println("Introduce un numero valido.");
					return;
				}

				DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");
				LocalDate fechaInicioParsed;
				LocalDate fechaFinParsed;
				try {
					fechaInicioParsed = LocalDate.parse(fechaInicio, formatter);
					fechaFinParsed = LocalDate.parse(fechaFin, formatter);
				} catch (DateTimeParseException ex) {
					System.out.println("Error. Introduce un dato en este formato yyyy/MM/dd.");
					return;
				}

				Curso curso = new Curso(0, nombre, fechaInicioParsed, fechaFinParsed, duracion);

				try {
					academiaModel.darDeAltaCurso(curso);
					System.out.println("Curso guardado correctamente.");
				} catch (SQLException ex) {
					ex.printStackTrace();
					System.out.println("Error al guardar el curso: " + ex.getMessage());
				}
			}
		});

		btnNewButton_1 = new JButton("CERRAR");
		btnNewButton_1.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnNewButton_1.setBounds(228, 227, 108, 23);

		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});

		contentPane.add(btnNewButton_1);
	}

}
