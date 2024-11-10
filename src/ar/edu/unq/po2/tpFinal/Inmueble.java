package ar.edu.unq.po2.tpFinal;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Inmueble {
	private Usuario propietario;
    private String tipo;
	private int superficie;
	private String pais; 
	private String ciudad;
	private String direccion;
	private Set<Servicio> servicios;
	private int capacidad;
	private List<String> fotos;
	private LocalTime checkIn; 
	private LocalTime checkOut; 
	private Double precioBase; 
	private List<String> metodosDePago;
	private List<PeriodoConPrecio> periodosPublicados;
	private List<Reserva> reservas;
	private Ranking rankingInmueble;
	private List<String> comentarios;
	
	//
	private List<Reserva> reservasEnCola;
	//
	
	private PoliticaDeCancelacion politicaDeCancelacion;
	private Map<EVENTO, List<Interesado> > interesados;

	
	public Inmueble(Usuario propietario, String tipo, int superficie, String pais, String ciudad, String direccion, Set<Servicio> servicios, int capacidad
			, LocalTime checkIn, LocalTime checkOut, double precioBase, List<String> metodosDePago,
			List<PeriodoConPrecio> periodosPublicados, PoliticaDeCancelacion politicaDeCancelacion ) {
		this.propietario = propietario;
		this.tipo = tipo;
		this.superficie = superficie;
		this.pais = pais;
		this.ciudad = ciudad;
		this.direccion = direccion;
		this.servicios = servicios;
		this.capacidad = capacidad;
		this.fotos = new ArrayList<String>();
		this.checkIn = checkIn;
		this.checkOut = checkOut;
		this.precioBase = precioBase;
		this.metodosDePago = metodosDePago;
		this.periodosPublicados = periodosPublicados;
		this.reservas = new ArrayList<Reserva>();
		this.politicaDeCancelacion = politicaDeCancelacion;
		this.reservasEnCola = new ArrayList<Reserva>();
		this.interesados = new HashMap<EVENTO, List<Interesado>>();	
		}

	
	public Usuario getPropietario() {
		return propietario;
	}
	
	public String getTipoInmueble() {
		return tipo;
	}
	
	public String getCiudad() {
		return ciudad;
	}
	
	public int getCapacidad() {
		return capacidad;
	}
	
	public List<Reserva> getReservas() {
		return reservas;
	}

	public void setPoliticaDeCancelacion(PoliticaDeCancelacion politica) {
		this.politicaDeCancelacion = politica;
	}
	
	public PoliticaDeCancelacion getPoliticaDeCancelacion() {
		return this.politicaDeCancelacion;
	}

	public Ranking getRankingInmueble() {
		return this.rankingInmueble;
	}
	
	public List<String> getComentarios(){
		return comentarios;
	}
	
	
	public void agregarFoto(String foto) {
		if (fotos.size() < 5) {
			fotos.add(foto);
		} else {
	        throw new IllegalArgumentException("No se pueden agregar más de 5 fotos.");
	    }
	}

	public Double calcularPrecioDia(LocalDate fecha) {
		for (PeriodoConPrecio periodo : periodosPublicados) {
			if (periodo.incluidaEnPeriodo(fecha)) {
				return periodo.getPrecioPorDia();
			} 
		}
		return precioBase;
	}
	
	//hasta aqui llegué
	public boolean estaDisponibleEnPeriodo(LocalDate fechaEntrada, LocalDate fechaSalida) {
		
		return reservas.stream().noneMatch(r -> r.getFechaEntrada().isBefore(fechaSalida) && 
				r.getFechaSalida().isAfter(fechaEntrada));
	}
	
	//es de aca o de reserva?
	public Double calcularPrecioEstadia(LocalDate fechaEntrada, LocalDate fechaSalida) {
		Double precioTotal = 0d;
		LocalDate fechaActual = fechaEntrada;
		while (fechaActual != fechaSalida) {
			precioTotal += calcularPrecioDia(fechaActual);
			fechaActual.plusDays(1);
		}
		return precioTotal;
	}

	public void eliminarReserva(Reserva reserva) {
		this.reservas.remove(reserva);
	}

	public boolean estaDisponibleHoy() {
		LocalDate hoy = LocalDate.now();
		return reservas.stream().noneMatch(r -> !hoy.isBefore(r.getFechaEntrada()) && 
				!hoy.isAfter(r.getFechaSalida()));
	}

	
	
	public List<Reserva> getReservasEnCola() {
		return this.reservasEnCola;
	}

	public void modificarPrecioPeriodo(PeriodoConPrecio periodo, Double precioNuevo) {
		if (periodo.getPrecioPorDia() > precioNuevo) {
			this.getPropietario().getSitioWeb().notify("Baja de precio", this);
		}
	}
	//se puede usar map para categorias a rankear!!!!!
	public double getPrecioBase() {
		return precioBase;
	}
	
	
	
	
	public void modificarPrecioBase(Double precioNuevo) {
		if (precioBase > precioNuevo) {
		//	this.getPropietario().getSitioWeb().notify("Baja de precio", this); HAY QUE ENVIARLE AL DUEÑO QUE SE CAMBIO EL PRECIO?
			this.notificar(EVENTO.BAJAPRECIO,this);
		}
	}
	

	public void subscribir(EVENTO evento, Interesado interesado) {
	    this.interesados.computeIfAbsent(evento, k -> new ArrayList<>()).add(interesado);
    }

	public void desubscribir(EVENTO evento, Interesado interesado) {
	    List<Interesado> interesadosDelEvento = this.interesados.get(evento);
	    if (interesadosDelEvento != null) {
	    	interesadosDelEvento.remove(interesado);
	    }
    }

	public void notificar(EVENTO evento, Inmueble inmueble) {
	    List<Interesado> interesadosDelEvento = this.interesados.get(evento);

	    if (interesadosDelEvento != null) {
	        interesadosDelEvento.stream().forEach(i -> i.update(evento, inmueble));
	    }
	}
	    
	/*
	public List<Interesado> getInteresados() {
		return interesados;
	}            hay que cambiarlo para map
	*/
	
}


