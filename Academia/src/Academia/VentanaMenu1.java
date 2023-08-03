package Academia;

import java.awt.Cursor;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

public class VentanaMenu1 extends JFrame {

	private JPanel contentPane;
	private JTable table;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VentanaMenu1 frame = new VentanaMenu1();
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
	public VentanaMenu1() {
		setTitle("ACADEMIA");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setLocationRelativeTo(null);

		setContentPane(contentPane);
		contentPane.setLayout(null);

		JButton btnNewButton = new JButton("ALUMNOS");
		btnNewButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnNewButton.setBounds(10, 216, 100, 23);
		contentPane.add(btnNewButton);
		btnNewButton.setBounds(10, 216, 100, 23);

		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int filaSeleccionada = table.getSelectionModel().getLeadSelectionIndex();
				if (filaSeleccionada == -1) {
					JOptionPane.showMessageDialog(VentanaMenu1.this, "Debes seleccionar un curso", "Error",
							JOptionPane.ERROR_MESSAGE);
				} else {
					String cursoSeleccionado = table.getValueAt(filaSeleccionada, 0).toString();
					try {
						AcademiaModel academiaModel = new AcademiaModel();
						List<Curso> listaCursos = academiaModel.obtenerListaCursos();
						int idCurso = -1;
						for (Curso curso : listaCursos) {
							if (curso.getNombre().equals(cursoSeleccionado)) {
								idCurso = curso.getId();
								break;
							}
						}
						if (idCurso != -1) {
							List<Alumnos> alumnosDelCurso = academiaModel.obtenerAlumnosDeCurso(idCurso);
							if (!alumnosDelCurso.isEmpty()) {
								VentanaAlumnos ventanaAlumnos = new VentanaAlumnos(alumnosDelCurso);
								ventanaAlumnos.setVisible(true);
							} else {
								JOptionPane.showMessageDialog(VentanaMenu1.this,
										"El curso seleccionado no tiene alumnos", "Error", JOptionPane.ERROR_MESSAGE);
							}
						} else {
							final VentanaMenu1 ventanaMenu = VentanaMenu1.this;
							JOptionPane.showMessageDialog(ventanaMenu, "No se encontró el curso seleccionado", "Error",
									JOptionPane.ERROR_MESSAGE);
						}
					} catch (SQLException ex) {
						ex.printStackTrace();
					}
				}
			}
		});

		contentPane.add(btnNewButton);

		JButton btnNewButton_1 = new JButton("NUEVO");
		btnNewButton_1.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnNewButton_1.setBounds(120, 216, 89, 23);

		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				VentanaNuevoCurso VentanaNuevoCurso = new VentanaNuevoCurso();
				VentanaNuevoCurso.setVisible(true);
			}
		});

		contentPane.add(btnNewButton_1);

		JButton btnNewButton_2 = new JButton("ELIMINAR");
		btnNewButton_2.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnNewButton_2.setBounds(219, 216, 89, 23);

		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int filaSeleccionada = table.getSelectionModel().getLeadSelectionIndex();
				if (filaSeleccionada == -1) {
					JOptionPane.showMessageDialog(VentanaMenu1.this, "Debes seleccionar un curso", "Error",
							JOptionPane.ERROR_MESSAGE);
				} else {
					String cursoSeleccionado = table.getValueAt(filaSeleccionada, 0).toString();
					try {
						AcademiaModel academiaModel = new AcademiaModel();
						List<Curso> listaCursos = academiaModel.obtenerListaCursos();
						int idCurso = -1;
						for (Curso curso : listaCursos) {
							if (curso.getNombre().equals(cursoSeleccionado)) {
								idCurso = curso.getId();
								break;
							}
						}
						if (idCurso != -1) {
							int confirmacion = JOptionPane.showConfirmDialog(VentanaMenu1.this,
									"¿Estas seguro de que deseas eliminar el curso?", "Confirmar eliminacion",
									JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
							if (confirmacion == JOptionPane.YES_OPTION) {
								academiaModel.eliminarCurso(idCurso);
								JOptionPane.showMessageDialog(VentanaMenu1.this, "Curso eliminado correctamente");
								actualizarTablaCursos();
							}
						} else {
							JOptionPane.showMessageDialog(VentanaMenu1.this, "No se encontro el curso seleccionado",
									"Error", JOptionPane.ERROR_MESSAGE);
						}
					} catch (SQLException ex) {
						ex.printStackTrace();
					}
				}
			}
		});

		contentPane.add(btnNewButton_2);

		JButton btnNewButton_3 = new JButton("SALIR");
		btnNewButton_3.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mostrarConfirmacionSalir();
			}
		});

		btnNewButton_3.setBounds(318, 216, 89, 23);
		contentPane.add(btnNewButton_3);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(25, 61, 383, 87);
		contentPane.add(scrollPane);

		table = new JTable();
		table.setFont(new Font("Tahoma", Font.BOLD, 11));
		DefaultTableModel tableModel = new DefaultTableModel(new String[] { "CURSOS DISPONIBLES" }, 0);
		table.setModel(tableModel);

		try {
			AcademiaModel academiaModel = new AcademiaModel();
			List<Curso> listaCursos = academiaModel.obtenerListaCursos();

			for (Curso curso : listaCursos) {
				tableModel.addRow(new Object[] { curso.getNombre() });
			}
		} catch (SQLException ex) {
			ex.printStackTrace();
		}

		scrollPane.setViewportView(table);
	}

	private void mostrarConfirmacionSalir() {
		int option = JOptionPane.showConfirmDialog(VentanaMenu1.this, "¿Estas seguro de que deseas salir?",
				"Confirmar salida", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
		if (option == JOptionPane.YES_OPTION) {
			dispose();
			System.exit(0);
		}
	}

	private void actualizarTablaCursos() {
		DefaultTableModel tableModel = (DefaultTableModel) table.getModel();
		tableModel.setRowCount(0);

		try {
			AcademiaModel academiaModel = new AcademiaModel();
			List<Curso> listaCursos = academiaModel.obtenerListaCursos();

			for (Curso curso : listaCursos) {
				tableModel.addRow(new Object[] { curso.getNombre() });
			}
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
	}
}
