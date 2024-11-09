package ar.edu.unq.po2.tpFinal;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Usuario implements Inquilino, Propietario{
	private SitioWeb sitio;
	private String nombreCompleto;
	private String correoElectronico;
	private int numeroDeTelefono;
	private List<Puntaje> calificaciones;
	private List<Reserva> reservas;
	private List<Inmueble> inmuebles;
		
	public Usuario(String nombreCompleto, int numeroDeTelefono, String correoElectronico) {
		this.nombreCompleto = nombreCompleto;
		this.numeroDeTelefono = numeroDeTelefono;
		this.correoElectronico = correoElectronico;
		this.calificaciones = new ArrayList<Puntaje>();
		this.reservas = new ArrayList<Reserva>();
		
	}
	
	public void hacerCheckOut(Inmueble i) {}
	
	public void rankearInmueble(Inmueble i) {}
 
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


	public Inmueble visualizarInmueble(Inmueble inmueble) {
		return inmueble;
	}
	
	public void reservar(Inmueble inmueble, String metodoPago, LocalDate fechaInicio, LocalDate fechaFin) {
		inmueble.getPropietario().recibirOferta(new Reserva(fechaInicio, fechaFin, inmueble, this, inmueble.calcularPrecioEstadia(fechaInicio, fechaFin)));
	}
	
	
	public void rankearInmueble(Inmueble inmueble, Puntaje puntaje) {
		
	}	
	
	public List<Inmueble> getInmuebles() {
		return inmuebles;
	}

	public void setInmuebles(List<Inmueble> inmuebles) { //no se si lo usamos
		this.inmuebles = inmuebles;
	}
	
	public void ponerEnAlquilerUnInmueble(Inmueble i) {
		this.getSitioWeb().darDeAltaInmueble(i);
	}
	
    public void agregarInmueble(Inmueble i) {
        inmuebles.add(i);
    }


	public void recibirOferta(Reserva reserva) {
		if (reserva.getInmueble().estaDisponibleEnPeriodo(reserva.getFechaEntrada(), reserva.getFechaSalida())) {
			this.posiblesReservas.add(reserva);
		} else {
			reserva.getInmueble().getReservasEnCola().add(reserva); //EXTRACT METHOD
		}
	}
	
	public void evaluarPosibleConcrecionDeReserva(Reserva reserva) {
		System.out.println("Información del Propietario:"+
	       "Nombre: " + reserva.getPropietario().getNombreCompleto() +
	        "Contacto: " + reserva.getPropietario().getCorreoElectronico());
	}
	
	public void aceptarUnaReserva(Reserva reserva) {
		reserva.cambiarEstadoAAceptada();
		this.posiblesReservas.remove(reserva);
		this.getMisReservas().add(reserva);
		this.getSitioWeb().enviarMailConfirmacion(reserva);
	}
	
	public void rechazarReserva(Reserva reserva) {
		reserva.cambiarEstadoACancelada();
		this.posiblesReservas.remove(reserva);
	}
	
	public void cancelarReserva(Reserva reserva) {
		reserva.cambiarEstadoACancelada();
		this.getMisReservas().remove(reserva);
	}
	
	public void contactarPosibleInquilino() {
		//HACEEEEEEEEEEEEER
	}
	
	
	public int cantVecesQueFueAlquiladoElInmueble(Inmueble inmueble) {
		return inmueble.getReservas().size(); //solo hay q contar las finalizadas?
	}
	
	public int cantVecesQueAlquilo(){
		return inmuebles.stream().mapToInt(i -> i.getReservas().size()).sum();
	}
	
	public List<Inmueble> todosLosInmueblesQueYaFueronAlquilados() {
		return inmuebles.stream().filter(i -> i.getReservas().size() >= 1).toList();
	}

	
	public void modificarPrecio(Double precioNuevo, Inmueble inmueble, PeriodoConPrecio periodo) {
		inmueble.modificarPrecioPeriodo(periodo, precioNuevo);
		
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
	
}
