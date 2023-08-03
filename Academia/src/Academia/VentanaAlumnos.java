package Academia;

import java.awt.BorderLayout;
import java.awt.Cursor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class VentanaAlumnos extends JFrame {

	private JPanel contentPane;
	private JTextArea ListaAlumnos;
	private String cursoSeleccionado;

	/**
	 * Launch the application.
	 */

	/**
	 * Create the frame.
	 */

	public VentanaAlumnos(List<Alumnos> alumno) {
		super("Lista de Asignaturas");

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(400, 300);
		setLocationRelativeTo(null);
		getContentPane().setLayout(new BorderLayout());
		setLocationRelativeTo(null);

		ListaAlumnos = new JTextArea();
		ListaAlumnos.setEditable(false);

		StringBuilder sb = new StringBuilder();
		for (Alumnos alumnos : alumno) {
			sb.append("Nombre del alumno: ").append(alumnos.getNombre()).append("\n");
			sb.append("Apellidos del alumno: ").append(alumnos.getApellidos()).append("\n");
		}
		ListaAlumnos.setText(sb.toString());

		JButton btnCerrar = new JButton("Cerrar");
		btnCerrar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnCerrar.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});

		getContentPane().add(new JScrollPane(ListaAlumnos), BorderLayout.CENTER);
		getContentPane().add(btnCerrar, BorderLayout.SOUTH);
	}
}
