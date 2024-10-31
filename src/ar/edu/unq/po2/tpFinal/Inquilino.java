package ar.edu.unq.po2.tpFinal;

import java.time.LocalDate;
import java.util.List;

public class Inquilino extends Usuario {
	private List<Reserva> reservas;
	private List<Puntaje> calificaciones;

	public Inquilino(String nombreCompleto, int numeroDeTelefono, String correoElectronico) {
		super(nombreCompleto, numeroDeTelefono, correoElectronico);
	}

	public void agregarReserva(Reserva reserva) {
	        reservas.add(reserva);
	}	
	
	public List<Reserva> getMisReservas() {
		return reservas;
	}
	
	public List<Reserva> getReservasFuturas(){
		return reservas.stream().filter(r -> r.getFechaEntrada().isAfter(LocalDate.now())).toList();
	}
	
	// el public de este metodo esta raro
	public void setMisReservas (List<Reserva>  misReservas) {
		this.reservas = misReservas;
	}
	
	public void agregarCalificacion(Puntaje puntuacion) {
	        calificaciones.add(puntuacion);
	}

	public Inmueble visualizarInmueble(Inmueble inmueble) {
		return inmueble;
	}
	
	public void reservar(Inmueble inmueble, String metodoPago, LocalDate fechaInicio, LocalDate fechaFin) {
		inmueble.getPropietario().recibirOferta(new Reserva(fechaInicio, fechaFin, inmueble));
	}
	
	
	
}
