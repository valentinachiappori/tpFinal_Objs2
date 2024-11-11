package ar.edu.unq.po2.tpFinal;

import java.util.List;

public interface Propietario  {
	
	public List<Inmueble> getInmuebles() ;
	
	public Ranking getRankingPropietario();
	
	public void publicarInmueble(Inmueble i);
	
    public void agregarInmueble(Inmueble i);

	public void rankearInquilino(Reserva reserva, String categoria, Puntaje puntaje);
	
	public void comentarInquilino(Reserva reserva, String comentario);
	
	public void visualizarInquilino(Usuario inquilino);
		
	public void aceptarUnaReserva(Reserva reserva);
	
	public void rechazarReserva(Reserva reserva);
	
	public int cantVecesQueFueAlquiladoElInmueble(Inmueble inmueble);
	
	public int cantVecesQueAlquilo();
	
	public List<Inmueble> todosLosInmueblesQueYaFueronAlquilados();

	public void modificarPrecioPeriodo(Double precioNuevo, Inmueble inmueble, PeriodoConPrecio periodo);
	
	public void modificarPrecioBase(Double precioNuevo, Inmueble inmueble);
	
}
