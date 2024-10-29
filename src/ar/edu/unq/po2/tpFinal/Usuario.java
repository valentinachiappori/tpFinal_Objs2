package ar.edu.unq.po2.tpFinal;

import java.util.List;

public class Usuario {
	private String nombreCompleto;
	private int numeroDeTelefono;
	private String correoElectronico;
	private List<Integer> calificaciones;

	public Usuario(String nombreCompleto, int numeroDeTelefono, String correoElectronico) {
		setNombreCompleto(nombreCompleto);
		setNumeroDeTelefono(numeroDeTelefono);
		setCorreoElectronico(correoElectronico);
	}
	    
	public void setNombreCompleto(String nombreCompleto) {
		this.nombreCompleto = nombreCompleto;
	}
	
	public String getNombreCompleto() {
		return nombreCompleto;
	}

	public int getNumeroDeTelefono() {
		return numeroDeTelefono;
	}

	public void setNumeroDeTelefono(int numeroDeTelefono) {
		this.numeroDeTelefono = numeroDeTelefono;
	}

	public String getCorreoElectronico() {
		return correoElectronico;
	}

	public void setCorreoElectronico(String correoElectronico) {
		this.correoElectronico = correoElectronico;
	}
	public void agregarCalificacion(int puntuacion) {
	    if (puntuacion >= 1 && puntuacion <= 5) {
	        this.calificaciones.add(puntuacion);
	    } else {
	        throw new IllegalArgumentException("La calificación debe estar entre 1 y 5.");
	    }
	}  // podriamos hacer que la calificacion se de con Enums asi nos evitamos que salga de ese rango

}
