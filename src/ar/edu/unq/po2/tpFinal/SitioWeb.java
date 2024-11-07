package ar.edu.unq.po2.tpFinal;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class SitioWeb {
	private List<Usuario> usuarios;
	private List<Inmueble> inmuebles;
	private List<String> tiposDeInmueble;
	private Set<Servicio> servicios;
	private MailSender mailSender;
	private List<Interesado> interesados;
	
	//constructor
	public SitioWeb( Set<Servicio> servicios, MailSender mailSender) {
		this.usuarios = new ArrayList<Usuario>();
		this.inmuebles = new ArrayList<Inmueble>();
		this.tiposDeInmueble = new ArrayList<String>();
		this.servicios = servicios;
		this.mailSender = mailSender;
		this.interesados = new ArrayList<Interesado>();
	}
	
	//getters y setters
	
	//Dar de alta los Tipos de Inmuebles que se utilizan en el sistema.
	public void darDeAltaTipoInmueble(String tipoDeInmueble) {
		this.tiposDeInmueble.add(tipoDeInmueble);
	}

	public List<Inmueble> filtrarInmuebles(String ciudad, LocalDate fechaEntrada, LocalDate fechaSalida, Integer cantHuespedes,
			Double precioMin, Double precioMax) {
		if (ciudad.equals(null)||fechaEntrada.equals(null)|| fechaSalida.equals(null)) {
			 new IllegalArgumentException();
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

	public void darDeAltaInmueble(Inmueble i) {
		if (verificarEsPropietarioRegistrado(i.getPropietario()) && this.tiposDeInmueble.contains(i.getTipoInmueble())) {
			this.inmuebles.add(i);
		}
	}
	
	public boolean verificarEsPropietarioRegistrado(Usuario usuario) {
		return esUsuarioRegistrado(usuario) && esPropietario(usuario);
	}

	private boolean esUsuarioRegistrado(Usuario usuario) {
		return this.usuarios.contains(usuario);
	}

	private boolean esPropietario(Usuario usuario) {
		return usuario.esPropietario();
	}

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
		return this.usuarios.stream().filter(u -> u.esInquilino()).toList();
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
	
	
	public void notify(String cambio,Inmueble inmueble) {
		for (Interesado interesado : interesados) {
			interesado.update(cambio, inmueble);
		}
	}
	
	public void agregarInteresado(Interesado interesado) {
		interesados.add(interesado);
	}
	
	/*no logro descifrar esto!!! 
	 pq si lo dejamos acá está bien pq puede haber un interesado en varios inmuebles, y para no
	 tener que agrearlo las veces que sea necesario a cada inmueble, es mejor directo en el sitio.
	 Pero a la vez me suena raroooo :| */
	
	public void removerInteresado(Interesado interesado) {
		interesados.remove(interesado);
	}
	
	/*
	public List<Inmueble> filtrarInmuebles(String ciudad, LocalDate fechaEntrada, LocalDate fechaSalida, int cantHuespedes,
			Double precioMin, Double precioMax){
		if (ciudad.equals(null)||fechaEntrada.equals(null)|| fechaSalida.equals(null)) {
			 new IllegalArgumentException();
		}
		
		List<Filtro> filtros = Stream.of(
	    	new FiltroCiudad(ciudad),
	        new FiltroFechaEntradaYSalida(fechaEntrada, fechaSalida),
			precioMin != null ? new FiltroPrecioMinDeFecha(precioMin, fechaEntrada, fechaSalida) : null,
        	precioMax != null ? new FiltroPrecioMaxDeFecha(precioMax, fechaEntrada, fechaSalida) : null,
        	cantHuespedes > 0 ? new FiltroCantHuespedes(cantHuespedes) : null
	    ).filter(filtro -> filtro != null).toList(); 
		return this.inmuebles.stream().filter(i-> i.cumplenConLosFiltros(filtros)).toList();
	}
	
	*/
}
