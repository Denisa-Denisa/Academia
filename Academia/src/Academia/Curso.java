package Academia;

import java.time.LocalDate;

public class Curso {

	private int id;
	private String nombre;
	private LocalDate fechaInicio;
	private LocalDate fechaFin;
	private int duracion;

	public Curso(int id, String nombre, LocalDate fechaInicio, LocalDate fechaFin, int duracion) {
		this.id = id;
		this.nombre = nombre;
		this.fechaInicio = fechaInicio;
		this.fechaFin = fechaFin;
		this.duracion = duracion;
	}

	public int getId() {
		return id;
	}

	public String getNombre() {
		return nombre;
	}

	public LocalDate getFechaInicio() {
		return fechaInicio;
	}

	public LocalDate getFechaFin() {
		return fechaFin;
	}

	public int getDuracion() {
		return duracion;
	}
}
