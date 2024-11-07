package ar.edu.unq.po2.tpFinal;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public abstract class Usuario {
	private SitioWeb sitio;
	private String nombreCompleto;
	private String correoElectronico;
	private int numeroDeTelefono;
	private List<Puntaje> calificaciones;
	private List<Reserva> reservas;

	public Usuario(String nombreCompleto, int numeroDeTelefono, String correoElectronico) {
		this.nombreCompleto = nombreCompleto;
		this.numeroDeTelefono = numeroDeTelefono;
		this.correoElectronico = correoElectronico;
		this.calificaciones = new ArrayList<Puntaje>();
		this.reservas = new ArrayList<Reserva>();
		
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
	
	public List<Reserva> getMisReservas(){
		return reservas;
	}

	public void setNumeroDeTelefono(int numeroDeTelefono) {
		this.numeroDeTelefono = numeroDeTelefono;
	}

	public String getCorreoElectronico() {
		return correoElectronico;
	}
	
	public SitioWeb getSitioWeb() {
		return sitio;
	}

	public void setCorreoElectronico(String correoElectronico) {
		this.correoElectronico = correoElectronico;
	}
	
	public List<Inmueble> buscarInmuebles(String ciudad,LocalDate fechaEntrada, LocalDate fechaSalida, int cantHuespuedes, Double precioMin, Double precioMax) {
		return this.sitio.filtrarInmuebles(ciudad, fechaEntrada, fechaSalida, cantHuespuedes, precioMin, precioMax);
	}
	
	
	public void agregarCalificacion(Puntaje puntuacion) {
	        this.calificaciones.add(puntuacion);
	}

	public abstract boolean esPropietario();

	public abstract boolean esInquilino();

}
