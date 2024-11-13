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
	
	public SitioWeb( Set<Servicio> servicios, MailSender mailSender) {
		this.usuarios = new ArrayList<Usuario>();
		this.inmuebles = new ArrayList<Inmueble>();
		this.tiposDeInmueble = new ArrayList<String>();
		this.servicios = servicios;
		this.mailSender = mailSender;
		this.categoriasPorEntidad = new HashMap<>();
		
		
		categoriasPorEntidad.put("Propietario", new HashSet<>());
		categoriasPorEntidad.put("Inquilino", new HashSet<>());
		categoriasPorEntidad.put("Inmueble", new HashSet<>());
	}
	
	//getters y setters
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
	
	
	//Dar de alta los tipos de inmuebles 
	public void darDeAltaTipoInmueble(String tipoDeInmueble) {
		getTiposDeInmueble().add(tipoDeInmueble);
	}
	
	//Dar de alta categoria en entidad
	public void darDeAltaCategoriaParaEntidad(String entidad, String categoria) {
		getCategoriasPorEntidad().get(entidad).add(categoria);
	}
	
	//Dar de alta usuario
	public void darDeAltaUsuario(Usuario usuario) {
		getUsuarios().add(usuario);
	}
	
	//Dar de alta servicio
	public void darDeAltaServicio(Servicio servicio) {
		getServicios().add(servicio);
	}
	
	//Dar de alta inmueble
	public void darDeAltaInmueble(Inmueble i) {
		if (esUsuarioRegistrado(i.getPropietario()) && this.tiposDeInmueble.contains(i.getTipoInmueble())) {
			this.inmuebles.add(i);
			i.getPropietario().agregarInmueble(i);
		}
	}
	
	//Verificar es usuario registrado
	private boolean esUsuarioRegistrado(Usuario usuario) {
		return getUsuarios().contains(usuario);
	}
	
	//Filtrar inmuebles


	public void rankearPropietario(Reserva reserva, String categoria, Puntaje puntaje) {
		if (reserva.getEstadoReserva().equals("Finalizada") && this.categoriasPorEntidad.get("Propietario").contains(categoria)) {
		 reserva.getPropietario().getRankingPropietario().agregarPuntaje(categoria, puntaje);
		}
	}
	
	public void rankearInquilino(Reserva reserva, String categoria, Puntaje puntaje) {
		if (reserva.getEstadoReserva().equals("Finalizada") && this.categoriasPorEntidad.get("Inquilino").contains(categoria)) {
		 reserva.getInquilino().getRankingInquilino().agregarPuntaje(categoria, puntaje);
		}
	}
	
	public void rankearInmueble(Reserva reserva, String categoria, Puntaje puntaje) {
		if (reserva.getEstadoReserva().equals("Finalizada") && this.categoriasPorEntidad.get("Inmueble").contains(categoria)) {
		 reserva.getInmueble().getRankingInmueble().agregarPuntaje(categoria, puntaje);
		}
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
		reserva.getPropietario().visualizarInquilino(reserva.getInquilino());
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
	
	public float tasaDeOcupacion() {
		int inmueblesAlquilados = inmuebles.stream().filter(i -> !i.estaDisponibleHoy()).toList().size();  
		int totalInmuebles = inmuebles.size();
		return inmueblesAlquilados / totalInmuebles;
	}

	private void ejecutarReservaCondicional(Reserva reserva) {
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

	public List<Inmueble> filtrarInmuebles(FiltroCompuesto filtro) {
		// TODO Auto-generated method stub
		return getInmuebles().stream().filter(i -> filtro.cumple(i)).toList();
	}
}
