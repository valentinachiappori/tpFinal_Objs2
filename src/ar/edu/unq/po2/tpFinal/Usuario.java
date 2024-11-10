package ar.edu.unq.po2.tpFinal;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Usuario implements Inquilino, Propietario{
	private SitioWeb sitio;
	private String nombreCompleto;
	private String correoElectronico;
	private int numeroDeTelefono;
	private List<Reserva> reservas;
	private List<Inmueble> inmuebles;
	private Ranking rankingPropietario;
	private Ranking rankingInquilino;
	private LocalDate fechaDeRegistro;

	
	public Usuario(String nombreCompleto, String correoElectronico, int numeroDeTelefono) {
		//sitio
		this.nombreCompleto = nombreCompleto;
		this.correoElectronico = correoElectronico;
		this.numeroDeTelefono = numeroDeTelefono;
		this.reservas = new ArrayList<Reserva>();
		this.inmuebles = new ArrayList<Inmueble>();
	}
	
	public SitioWeb getSitioWeb() {
		return sitio;
	}
	
	public void setNombreCompleto(String nombreCompleto) {
		this.nombreCompleto = nombreCompleto;
	}
	
	public String getNombreCompleto() {
		return nombreCompleto;
	}

	public void setCorreoElectronico(String correoElectronico) {
		this.correoElectronico = correoElectronico;
	}
	
	public String getCorreoElectronico() {
		return correoElectronico;
	}
	
	public void setNumeroDeTelefono(int numeroDeTelefono) {
		this.numeroDeTelefono = numeroDeTelefono;
	}
	
	public int getNumeroDeTelefono() {
		return numeroDeTelefono;
	}
	
	public List<Reserva> getMisReservas(){
		return reservas;
	}

	public List<Inmueble> getInmuebles() {
		return inmuebles;
	}
	
	public Ranking getRankingPropietario() {
		return this.rankingPropietario;
	}
	
	public Ranking getRankingInquilino() {
		return this.rankingInquilino;
	}

	public LocalDate getFechaDeRegistro() {
		return this.fechaDeRegistro;
	}
	
	
	public void publicarInmueble(Inmueble i) {
		this.sitio.darDeAltaInmueble(i);
	}
	
	public List<Inmueble> buscarInmuebles(String ciudad,LocalDate fechaEntrada, LocalDate fechaSalida, int cantHuespuedes, Double precioMin, Double precioMax) {
		return this.sitio.filtrarInmuebles(ciudad, fechaEntrada, fechaSalida, cantHuespuedes, precioMin, precioMax);
	}
	
	public void rankearPropietario(Reserva reserva, String categoria, Puntaje puntaje) {
		this.sitio.rankearPropietario(reserva, categoria, puntaje);
	}
	
	public void rankearInquilino(Reserva reserva, String categoria, Puntaje puntaje) {
		this.sitio.rankearInquilino(reserva, categoria, puntaje);
	}
	
	public void rankearInmueble(Reserva reserva, String categoria, Puntaje puntaje) {
		this.sitio.rankearInmueble(reserva, categoria, puntaje);
	}
	
	public void comentarInmueble(Reserva reserva, String comentario) {
		this.sitio.registrarComentario(reserva, comentario);
	}
	
	public void getAntiguedad() {
		//string?? o que devuelve
	}
	
	public void  visualizarInmueble(Inmueble inmueble) {
		System.out.println("Datos del Inmueble:");
        System.out.println("Tipo: " + inmueble.getTipoInmueble());
        System.out.println("Ciudad: " + inmueble.getCiudad());
        System.out.println("Precio: " + inmueble.getPrecioBase());

        // Puntajes
        System.out.println("Puntajes del Inmueble:");
        for (String categoria : inmueble.getPropietario().getSitioWeb().getCategoriasPorEntidad("Inmueble")) {
            System.out.println(categoria + ": " + inmueble.getRankingInmueble().obtenerPromedioPorCategoria(categoria));
        }
        System.out.println("Puntaje Promedio Total: " + inmueble.getRankingInmueble().obtenerPromedioTotal());

        // Comentarios
        System.out.println("Comentarios:");
        for (String comentario : inmueble.getComentarios()) {
            System.out.println(comentario);
        }
        
        // Información del propietario
            System.out.println("Información del Propietario:");
            System.out.println("Nombre: " + inmueble.getPropietario().getNombreCompleto());
            System.out.println("Veces alquilado el inmueble: " + inmueble.getPropietario().cantVecesQueFueAlquiladoElInmueble(inmueble));
            System.out.println("Veces alquilado: " + inmueble.getPropietario().cantVecesQueAlquilo());
            System.out.println("Puntaje Promedio del Propietario: " + inmueble.getPropietario().getRankingPropietario().obtenerPromedioTotal());
    }
	
	//hasta aca llegue
	public void hacerCheckOut(Inmueble i) {}
 
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
	
	public void reservar(Inmueble inmueble, String metodoPago, LocalDate fechaInicio, LocalDate fechaFin) {
		inmueble.getPropietario().recibirOferta(new Reserva(fechaInicio, fechaFin, inmueble, this, inmueble.calcularPrecioEstadia(fechaInicio, fechaFin)));
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
	
	
	
	
	
	/*public void agregarCalificacion(Puntaje puntuacion) {
	        this.calificaciones.add(puntuacion);
	}*/
	
}
