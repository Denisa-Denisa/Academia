package Academia;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class BaseDatos {

	private Connection conn;

	public void conectar() throws SQLException {
		String url = "jdbc:mysql://localhost:3306/academia";
		String user = "root";
		String password = "Contraseña";
		conn = DriverManager.getConnection(url, "root", "Contraseña");
	}

	public ResultSet query(String sql, Object[] parametros) throws SQLException {

		PreparedStatement stm = conn.prepareStatement(sql);
		for (int i = 0; i < parametros.length; i++) {
			stm.setObject(i + 1, parametros[i]);
		}
		return stm.executeQuery();
	}

	public int execute(String sql, Object[] parametros) throws SQLException {

		PreparedStatement stm = conn.prepareStatement(sql);
		for (int i = 0; i < parametros.length; i++) {
			stm.setObject(i + 1, parametros[i]);
		}
		return stm.executeUpdate();
	}

}
