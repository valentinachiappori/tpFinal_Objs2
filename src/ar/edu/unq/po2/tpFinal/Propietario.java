package ar.edu.unq.po2.tpFinal;

import java.util.List;

public interface Propietario  {
	

	
	public List<Inmueble> getInmuebles() ;

	public void setInmuebles(List<Inmueble> inmuebles);
	public void ponerEnAlquilerUnInmueble(Inmueble i) ;
	
    public void agregarInmueble(Inmueble i) ;

	public void agregarCalificacion(Puntaje puntuacion) ;

	public void recibirOferta(Reserva reserva) ;
	
	public void evaluarPosibleConcrecionDeReserva(Reserva reserva);
	
	public void aceptarUnaReserva(Reserva reserva) ;
	
	public void rechazarReserva(Reserva reserva) ;
	
	public void cancelarReserva(Reserva reserva);
	
	public void contactarPosibleInquilino();
		
	public int cantVecesQueFueAlquiladoElInmueble(Inmueble inmueble) ;
	
	public int cantVecesQueAlquilo();
	
	public List<Inmueble> todosLosInmueblesQueYaFueronAlquilados() ;

	public void modificarPrecio(Double precioNuevo, Inmueble inmueble, PeriodoConPrecio periodo);
	
}
