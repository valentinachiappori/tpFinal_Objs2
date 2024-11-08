package ar.edu.unq.po2.tpFinal;

import java.time.LocalDate;
import java.util.List;

public class Inquilino extends Usuario {
	private List<Puntaje> calificaciones;

	public Inquilino(String nombreCompleto, int numeroDeTelefono, String correoElectronico) {
		super(nombreCompleto, numeroDeTelefono, correoElectronico);
	}

	public void agregarReserva(Reserva reserva) {
	        this.getMisReservas().add(reserva);
	}
	
	public List<Reserva> getReservasFuturas(){
		return this.getMisReservas().stream().filter(r -> r.getFechaEntrada().isAfter(LocalDate.now())).toList();
	}
	
	public List<Reserva> getReservasEnCiudad(String ciudad){
		return this.getMisReservas().stream().filter(r -> r.getInmueble().getCiudad().equals(ciudad)).toList();
	}
	
	public List<String> getCiudadesConReserva(){
		return this.getMisReservas().stream().map(r -> r.getInmueble().getCiudad()).toList();
	}

	
	public void agregarCalificacion(Puntaje puntuacion) {
	        calificaciones.add(puntuacion);
	}

	public Inmueble visualizarInmueble(Inmueble inmueble) {
		return inmueble;
	}
	
	public void reservar(Inmueble inmueble, String metodoPago, LocalDate fechaInicio, LocalDate fechaFin) {
		inmueble.getPropietario().recibirOferta(new Reserva(fechaInicio, fechaFin, inmueble, this, inmueble.calcularPrecioEstadia(fechaInicio, fechaFin)));
	}
	
	public void cancelarReserva(Reserva reserva) {
		this.getMisReservas().remove(reserva);
		this.getSitioWeb().cancelarReserva(reserva);
	}
	
	public void rankearInmueble(Inmueble inmueble, Puntaje puntaje) {
		
	}

}
