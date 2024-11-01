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
	
	public List<Reserva> getReservasEnCiudad(String ciudad){
		return reservas.stream().filter(r -> r.getInmueble().getCiudad().equals(ciudad)).toList();
	}
	
	public List<String> getCiudadesConReserva(){
		return reservas.stream().map(r -> r.getInmueble().getCiudad()).toList();
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
		inmueble.getPropietario().recibirOferta(new Reserva(fechaInicio, fechaFin, inmueble, this));
	}
	
	public void cancelarReserva(Reserva reserva) {
		//implementacion
		//eliminarla del sistema y avisarle por mail al dueño
	}
	
	
}
