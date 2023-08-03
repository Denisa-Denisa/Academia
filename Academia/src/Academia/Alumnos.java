package Academia;

public class Alumnos {

	private int id;
	private String nombre;
	private String apellidos;
	private String titulacionAcademica;

	public Alumnos(int id, String nombre, String apellidos, String titulacionAcademica) {
		this.id = id;
		this.nombre = nombre;
		this.apellidos = apellidos;
		this.titulacionAcademica = titulacionAcademica;
	}

	public int getId() {
		return id;
	}

	public String getNombre() {
		return nombre;
	}

	public String getApellidos() {
		return apellidos;
	}

	public String getTitulacionAcademica() {
		return titulacionAcademica;
	}
}
