package Academia;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AcademiaModel {

	private BaseDatos bd;

	public AcademiaModel() throws SQLException {
		super();
		bd = new BaseDatos();
	}

	public Curso obtenerCursoPorId(int idCurso) throws SQLException {
		bd.conectar();
		String sql = "SELECT * FROM curso WHERE idCurso=?";
		Object[] params = new Object[1];
		params[0] = idCurso;
		ResultSet resultSet = bd.query(sql, params);

		Curso curso = null;

		if (resultSet.next()) {
			curso = new Curso(resultSet.getInt("idCurso"), resultSet.getString("nombre"),
					resultSet.getDate("fechaInicio").toLocalDate(), resultSet.getDate("fechaFin").toLocalDate(),
					resultSet.getInt("duracion"));
		}

		return curso;
	}

	public List<Curso> obtenerListaCursos() throws SQLException {
		bd.conectar();
		String sql = "SELECT * FROM curso";
		Object[] params = new Object[0];
		ResultSet resultSet = bd.query(sql, params);

		List<Curso> cursos = new ArrayList<>();

		while (resultSet.next()) {
			Curso curso = new Curso(resultSet.getInt("idCurso"), resultSet.getString("nombre"),
					resultSet.getDate("fechaInicio").toLocalDate(), resultSet.getDate("fechaFin").toLocalDate(),
					resultSet.getInt("duracion"));
			cursos.add(curso);
		}

		return cursos;
	}

	public List<Alumnos> obtenerAlumnosDeCurso(int idCurso) throws SQLException {
		bd.conectar();
		String sql = "SELECT a.idAlumnos, a.nombre, a.apellidos, a.titulacionEnum " + "FROM alumnos a "
				+ "INNER JOIN curso c ON a.curso_id = c.idCurso " + "WHERE c.idCurso = ?";
		Object[] params = new Object[1];
		params[0] = idCurso;
		ResultSet resultSet = bd.query(sql, params);

		List<Alumnos> alumnos = new ArrayList<>();

		while (resultSet.next()) {
			Alumnos alumno = new Alumnos(resultSet.getInt("idAlumnos"), resultSet.getString("nombre"),
					resultSet.getString("apellidos"), resultSet.getString("titulacionEnum"));
			alumnos.add(alumno);
		}

		return alumnos;
	}

	public void darDeAltaCurso(Curso curso) throws SQLException {
		bd.conectar();
		String sql = "INSERT INTO curso (nombre, fechaInicio, fechaFin, duracion) VALUES (?, ?, ?, ?)";
		Object[] params = new Object[4];
		params[0] = curso.getNombre();
		params[1] = curso.getFechaInicio();
		params[2] = curso.getFechaFin();
		params[3] = curso.getDuracion();
		bd.execute(sql, params);
	}

	public void eliminarCurso(int idCurso) throws SQLException {
		bd.conectar();
		String sql = "DELETE FROM curso WHERE idCurso=?";
		Object[] params = new Object[1];
		params[0] = idCurso;
		bd.execute(sql, params);
	}

}