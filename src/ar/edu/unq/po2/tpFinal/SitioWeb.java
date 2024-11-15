package ar.edu.unq.po2.tpFinal;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class SitioWeb {
	private List<Usuario> usuarios;
	private List<Inmueble> inmuebles;
	private List<String> tiposDeInmueble;
	private Set<Servicio> servicios;
	private MailSender mailSender;
	private Map<String, Set<String>> categoriasPorEntidad;
	
	public SitioWeb( MailSender mailSender) {
		this.usuarios = new ArrayList<Usuario>();
		this.inmuebles = new ArrayList<Inmueble>();
		this.tiposDeInmueble = new ArrayList<String>();
		this.servicios = new HashSet<Servicio>();
		this.mailSender = mailSender;
		this.categoriasPorEntidad = new HashMap<>();
		

		categoriasPorEntidad.put("Propietario", new HashSet<>());
		categoriasPorEntidad.put("Inquilino", new HashSet<>());
		categoriasPorEntidad.put("Inmueble", new HashSet<>());
	}
	
	public List<Usuario> getUsuarios() {
		return usuarios;
	}

	public List<Inmueble> getInmuebles() {
		return inmuebles;
	}

	public List<String> getTiposDeInmueble() {
		return tiposDeInmueble;
	}

	public Set<Servicio> getServicios() {
		return servicios;
	}

	public MailSender getMailSender() {
		return mailSender;
	}

	public Map<String, Set<String>> getCategoriasPorEntidad() {
		return categoriasPorEntidad;
	}

	public void setCategoriasPorEntidad(Map<String, Set<String>> categoriasPorEntidad) {
		this.categoriasPorEntidad = categoriasPorEntidad;
	}

	public void setUsuarios(List<Usuario> usuarios) {
		this.usuarios = usuarios;
	}

	public void setInmuebles(List<Inmueble> inmuebles) {
		this.inmuebles = inmuebles;
	}

	public void setTiposDeInmueble(List<String> tiposDeInmueble) {
		this.tiposDeInmueble = tiposDeInmueble;
	}

	public void setServicios(Set<Servicio> servicios) {
		this.servicios = servicios;
	}

	public void setMailSender(MailSender mailSender) {
		this.mailSender = mailSender;
	}

	public Set<String> getCategoriasPorEntidad(String entidad){
		return this.categoriasPorEntidad.get(entidad);
	}
	
	
	public void darDeAltaTipoInmueble(String tipoDeInmueble) {
		getTiposDeInmueble().add(tipoDeInmueble);
	}
	
	public void darDeAltaCategoriaParaEntidad(String entidad, String categoria) {
		getCategoriasPorEntidad().get(entidad).add(categoria);
	}
	
	public void darDeAltaUsuario(Usuario usuario) {
		getUsuarios().add(usuario);
	}
	
	public void darDeAltaServicio(Servicio servicio) {
		getServicios().add(servicio);
	}
	
	public void darDeAltaInmueble(Inmueble i) {
		if (esUsuarioRegistrado(i.getPropietario()) && verificarExisteTipoInmueble(i.getTipoInmueble())) {
			getInmuebles().add(i);
			i.getPropietario().agregarInmueble(i);
		}
	}
	
	public boolean esUsuarioRegistrado(Usuario usuario) {
		return getUsuarios().contains(usuario);
	}
	
	public boolean verificarExisteTipoInmueble(String tipo) {
		return getTiposDeInmueble().contains(tipo);
	}
	
	public void rankearPropietario(Reserva reserva, String categoria, Puntaje puntaje) {
		if (esReservaFinalizada(reserva) && this.categoriasPorEntidad.get("Propietario").contains(categoria)) {
		 reserva.getPropietario().getRankingPropietario().agregarPuntaje(categoria, puntaje);
		}
	}
	
	public void rankearInquilino(Reserva reserva, String categoria, Puntaje puntaje) {
		if (esReservaFinalizada(reserva) && this.categoriasPorEntidad.get("Inquilino").contains(categoria)) {
		 reserva.getInquilino().getRankingInquilino().agregarPuntaje(categoria, puntaje);
		}
	}
	
	public void rankearInmueble(Reserva reserva, String categoria, Puntaje puntaje) {
		if (esReservaFinalizada(reserva) && this.categoriasPorEntidad.get("Inmueble").contains(categoria)) {
		 reserva.getInmueble().getRankingInmueble().agregarPuntaje(categoria, puntaje);
		}
	}
	
	private boolean esReservaFinalizada(Reserva reserva) {
	    return "Finalizada".equals(reserva.getEstadoReserva());
	}
	
	public void registrarComentarioInmueble(Reserva reserva, String comentario) {
		if (reserva.getEstadoReserva().equals("Finalizada")){
			reserva.getInmueble().agregarComentario(comentario); //extract method?
		}
	}
	
	public void registrarComentarioInquilino(Reserva reserva, String comentario) {
		if (reserva.getEstadoReserva().equals("Finalizada")){
			reserva.getInquilino().agregarComentario(comentario); //extract method?
		}
	}
	
	public void reservar(Reserva reserva) {
		reserva.getInmueble().recibirReserva(reserva);
	}
	
	public void consolidarReserva(Reserva reserva) {
		reserva.cambiarEstadoAAceptada();
		reserva.getInmueble().registrarReserva(reserva);
		reserva.getInquilino().registrarReserva(reserva);
		enviarMailConfirmacion(reserva);
		reserva.getInmueble().notificar(Evento.RESERVA, reserva.getInmueble());
	}
	
	public void enviarMailConfirmacion(Reserva reserva) {
		getMailSender().sendMail(reserva.getInquilino().getCorreoElectronico(),"Reserva confirmada", "Tu reserva" + reserva.toString() + "ha sido confirmada por su propietario");
	}

	public void rechazarReserva(Reserva reserva){
		reserva.getInmueble().eliminarReservaPendiente(reserva);
	}
	
	public void cancelarReserva(Reserva reserva) {
		reserva.getInmueble().eliminarReserva(reserva);
		reserva.getInquilino().eliminarReserva(reserva);
		this.mailSender.sendMail(reserva.getPropietario().getCorreoElectronico(),"Reserva cancelada", "Tu reserva " + reserva.toString() + "ha sido cancelada por el inquilino");
		reserva.getInmueble().getPoliticaDeCancelacion().ejecutar(reserva);
		reserva.getInmueble().notificar(Evento.CANCELACION, reserva.getInmueble());	
		ejecutarReservaCondicional(reserva);
	}

	public void enviarMailConReserva(Reserva reserva) {
		this.mailSender.sendMail(reserva.getInquilino().getCorreoElectronico(),"Reserva", reserva.toString());
	}
	
	public List<Usuario> topTenInquilinosConMasAlquileres(){
		return this.usuarios.stream().sorted((i1, i2) -> 
		Integer.compare(i2.getMisReservas().size(), i1.getMisReservas().size())).limit(10).toList();
	}
	
	public List<Inmueble> inmueblesLibres(){
		return getInmuebles().stream().filter(i -> i.estaDisponibleHoy()).toList();
	}
	
	public double tasaDeOcupacion() {
		int inmueblesAlquilados = inmuebles.stream().filter(i -> !i.estaDisponibleHoy()).toList().size();  
		int totalInmuebles = inmuebles.size();
		
		if (totalInmuebles == 0) {
	        return 0.0;
	    }
		
		return (double) inmueblesAlquilados / totalInmuebles;
	}

	public void ejecutarReservaCondicional(Reserva reserva) {
		if (!reserva.getInmueble().getReservasEnCola().isEmpty()) {
			reserva.getInmueble().recibirReserva(reserva.getInmueble().getReservasEnCola().getFirst());
			reserva.getInmueble().getReservasEnCola().removeFirst();
		}
	}
	
	public void registrarCheckOut(Reserva reserva, LocalDate fecha){
		if (reserva.getFechaSalida().isBefore(fecha)) {
			reserva.cambiarEstadoAFinalizada();
		} else {
			throw new IllegalArgumentException("La reserva aún no ha finalizado");
		}
	}

	public List<Inmueble> filtrarInmuebles(String ciudad, LocalDate fechaEntrada, LocalDate fechaSalida, Integer cantHuespedes, Double precioMin, Double precioMax) {
		if (ciudad == null || fechaEntrada == null || fechaSalida == null) {
		    throw new IllegalArgumentException("Ciudad, fecha de entrada y fecha de salida no pueden ser nulas");
		}
		List<Inmueble> inmueblesFiltrados = inmuebles.stream()
		.filter(i -> i.getCiudad().equals(ciudad))
		.filter(i -> i.estaDisponibleEnPeriodo(fechaEntrada, fechaSalida))
		.filter(i -> cantHuespedes == null || i.getCapacidad() >= cantHuespedes)
		.filter(i -> precioMin == null || i.calcularPrecioEstadia(fechaEntrada, fechaSalida) >= precioMin)
		.filter(i -> precioMax == null || i.calcularPrecioEstadia(fechaEntrada, fechaSalida) <= precioMax)
		.toList();
		return inmueblesFiltrados;
	}
}
