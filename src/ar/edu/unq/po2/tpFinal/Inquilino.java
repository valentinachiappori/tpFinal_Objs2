package ar.edu.unq.po2.tpFinal;

import java.time.LocalDate;
import java.util.List;

public interface Inquilino {
	
	public void hacerCheckOut(Inmueble i);
	
	public void rankearInmueble(Inmueble i);

	public void agregarReserva(Reserva reservas);
	
	public List<Reserva> getReservasFuturas();
	
	public List<Reserva> getReservasEnCiudad(String ciudad);
	
	public List<String> getCiudadesConReserva();

	public void agregarCalificacion(Puntaje puntuacion);

	public Inmueble visualizarInmueble(Inmueble inmueble);
	
	public void reservar(Inmueble inmueble, String metodoPago, LocalDate fechaInicio, LocalDate fechaFin);
	
	public void cancelarReserva(Reserva reserva);
	
	public void rankearInmueble(Inmueble inmueble, Puntaje puntaje) ;

}
