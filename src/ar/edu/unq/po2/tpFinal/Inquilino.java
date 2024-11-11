package ar.edu.unq.po2.tpFinal;

import java.time.LocalDate;
import java.util.List;

public interface Inquilino {
	
	public List<Reserva> getMisReservas();
	
	public Ranking getRankingInquilino();
	
	public List<String> getComentariosInquilino();
	
	public void hacerCheckOut(Reserva reserva);
	
	public void rankearPropietario(Reserva reserva, String categoria, Puntaje puntaje);
	
	public void rankearInmueble(Reserva reserva, String categoria, Puntaje puntaje);
	
	public void agregarComentario(String comentario);
	
	public void comentarInmueble(Reserva reserva, String comentario);
	
	public void visualizarInmueble(Inmueble inmueble);
	
	public void reservar(Inmueble inmueble, String metodoPago, LocalDate fechaInicio, LocalDate fechaFin);

	public void registrarReserva(Reserva reserva);
	
	public List<Reserva> getReservasFuturas();
	
	public List<Reserva> getReservasEnCiudad(String ciudad);
	
	public List<String> getCiudadesConReserva();
	
	public void cancelarReserva(Reserva reserva);
	
	public void eliminarReserva(Reserva reserva);

}
