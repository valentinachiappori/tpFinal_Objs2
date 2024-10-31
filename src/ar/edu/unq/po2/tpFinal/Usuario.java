package ar.edu.unq.po2.tpFinal;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Usuario {
	private SitioWeb sitio;
	private String nombreCompleto;
	private String correoElectronico;
	private int numeroDeTelefono;
	private List<Integer> calificaciones;

	public Usuario(String nombreCompleto, int numeroDeTelefono, String correoElectronico) {
		this.nombreCompleto = nombreCompleto;
		this.numeroDeTelefono = numeroDeTelefono;
		this.correoElectronico = correoElectronico;
		this.calificaciones = new ArrayList<Integer>();
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
	
	public List<Inmueble> buscarInmuebles(String ciudad,LocalDate fechaEntrada, LocalDate fechaSalida, int cantHuespuedes, Double precioMin, Double precioMax) {
		return this.sitio.filtrarInmuebles(ciudad, fechaEntrada, fechaSalida, cantHuespuedes, precioMin, precioMax);
	}
	
	
	public void agregarCalificacion(int puntuacion) {
	    if (puntuacion >= 1 && puntuacion <= 5) {
	        this.calificaciones.add(puntuacion);
	    } else {
	        throw new IllegalArgumentException("La calificación debe estar entre 1 y 5.");
	    }
	}  // podriamos hacer que la calificacion se de con Enums asi nos evitamos que salga de ese rango

}
