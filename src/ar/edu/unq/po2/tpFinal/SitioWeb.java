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
	
	//constructor
	public SitioWeb( Set<Servicio> servicios, MailSender mailSender) {
		this.usuarios = new ArrayList<Usuario>();
		this.inmuebles = new ArrayList<Inmueble>();
		this.tiposDeInmueble = new ArrayList<String>();
		this.servicios = servicios;
		this.mailSender = mailSender;
		this.categoriasPorEntidad = new HashMap<>();
		
		//inicializamos las entidades para las categorias
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

	public void setMailSender(MailSender mailSender) {
		this.mailSender = mailSender;
	}

	public Set<String> getCategoriasPorEntidad(String entidad){
		return this.categoriasPorEntidad.get(entidad);
	}
	
	
	//Dar de alta los tipos de inmuebles 
	public void darDeAltaTipoInmueble(String tipoDeInmueble) {
		this.tiposDeInmueble.add(tipoDeInmueble);
	}
	
	//Dar de alta categoria en entidad
	public void darDeAltaCategoriaParaEntidad(String entidad, String categoria) {
		this.categoriasPorEntidad.get(entidad).add(categoria);
	}
	
	//Dar de alta usuario
	public void darDeAltaUsuario(Usuario usuario) {
		this.usuarios.add(usuario);
	}
	
	//Dar de alta servicio
	public void darDeAltaServicio(Servicio servicio) {
		this.servicios.add(servicio);
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
		return this.usuarios.contains(usuario);
	}
	
	//Filtrar inmuebles
	public List<Inmueble> filtrarInmuebles(String ciudad, LocalDate fechaEntrada, LocalDate fechaSalida, Integer cantHuespedes,
			Double precioMin, Double precioMax) {
		if (ciudad == null || fechaEntrada == null || fechaSalida == null) {
			 throw new IllegalArgumentException("Por favor, completa la ciudad y las fechas de entrada y salida para realizar la búsqueda.");
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
		if (reserva.getEstadoReserva().equals("Finalizada") && this.categoriasPorEntidad.get("Inquilino").contains(categoria)) {
		 reserva.getInmueble().getRankingInmueble().agregarPuntaje(categoria, puntaje);
		}
	}
	
	public void registrarComentario(Reserva reserva, String comentario) {
		if (reserva.getEstadoReserva().equals("Finalizada")){
			reserva.getInmueble().getComentarios().add(comentario);
		}
	}
	
	//hasta que llegue
	public void enviarMailConfirmacion(Reserva reserva) {
		this.mailSender.sendMail(reserva.getInquilino().getCorreoElectronico(),"Reserva confirmada", "Tu reserva" + reserva.toString() + "ha sido confirmada por su propietario");
		notify("reserva de inmueble", reserva.getInmueble());
	}

	public void cancelarReserva(Reserva reserva) {
		reserva.getPropietario().cancelarReserva(reserva);
		ejecutarReservaCondicional(reserva);
		this.mailSender.sendMail(reserva.getPropietario().getCorreoElectronico(),"Reserva cancelada", "Tu reserva " + reserva.toString() + "ha sido cancelada por el inquilino");
		reserva.getInmueble().eliminarReserva(reserva);
		reserva.getInmueble().getPoliticaDeCancelacion().ejecutar(reserva);
		notify("cancelación de reserva", reserva.getInmueble());
	}
	
	
	private void ejecutarReservaCondicional(Reserva reserva) {
		if (!reserva.getInmueble().getReservasEnCola().isEmpty()) {
			reserva.getPropietario().recibirOferta(reserva.getInmueble().getReservasEnCola().getFirst());
			reserva.getInmueble().getReservasEnCola().removeFirst();
		}
	}

	public void enviarMailConReserva(Reserva reserva) {
		this.mailSender.sendMail(reserva.getInquilino().getCorreoElectronico(),"Reserva", reserva.toString());
	}
	
	public List<Usuario> getInquilinos(){
		return this.usuarios.stream().filter(u -> u.).toList();
	}
	
	public List<Usuario> topTenInquilinosConMasAlquileres(){
		return this.getInquilinos().stream().sorted((i1, i2) -> 
		Integer.compare(i2.getMisReservas().size(), i1.getMisReservas().size())).limit(10).toList();
	}
	
	public List<Inmueble> inmueblesLibres(){
		return inmuebles.stream().filter(i -> i.estaDisponibleHoy()).toList();
	}
	
	public float tasaDeOcupacion() {
		int inmueblesAlquilados = inmuebles.stream().filter(i -> !i.estaDisponibleHoy()).toList().size();  
		int totalInmuebles = inmuebles.size();
		return inmueblesAlquilados / totalInmuebles;
	}

	
}
