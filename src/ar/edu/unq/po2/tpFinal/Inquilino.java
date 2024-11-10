package ar.edu.unq.po2.tpFinal;

import java.time.LocalDate;
import java.util.List;

public interface Inquilino {
	
	public void hacerCheckOut(Inmueble i);
	
	public void rankearPropietario(Reserva reserva, String categoria, Puntaje puntaje);
	
	public void rankearInmueble(Reserva reserva, String categoria, Puntaje puntaje);
	
	public void comentarInmueble(Reserva reserva, String comentario);

	public void agregarReserva(Reserva reservas);
	
	public List<Reserva> getReservasFuturas();
	
	public List<Reserva> getReservasEnCiudad(String ciudad);
	
	public List<String> getCiudadesConReserva();

	public void visualizarInmueble(Inmueble inmueble);
	
	public void reservar(Inmueble inmueble, String metodoPago, LocalDate fechaInicio, LocalDate fechaFin);
	
	public void cancelarReserva(Reserva reserva);

}
