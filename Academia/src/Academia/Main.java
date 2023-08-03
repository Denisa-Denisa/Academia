package Academia;

import java.sql.SQLException;

import javax.swing.JOptionPane;

public class Main {

	public static void main(String[] args) {

		BaseDatos baseDatos = new BaseDatos();
		try {
			baseDatos.conectar();
			AcademiaModel academiaModel = new AcademiaModel();
			VentanaMenu1 VentanaMenu1 = new VentanaMenu1();
			VentanaMenu1.setVisible(true);

		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Error al conectar con la base de datos", "Error",
					JOptionPane.ERROR_MESSAGE);
			e.printStackTrace();

		}
	}
}
