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
	private List<String> comentarios;
	private Ranking rankingInmueble;

	private List<Reserva> reservasConfirmadas;
	private List<Reserva> reservasPendientes;
	private List<Reserva> reservasEnCola;

	private PoliticaDeCancelacion politicaDeCancelacion;
	private Map<Evento, List<Interesado> > interesados;

	
	public Inmueble(Usuario propietario, String tipo, int superficie, String pais, String ciudad, String direccion, Set<Servicio> servicios, int capacidad
			, LocalTime checkIn, LocalTime checkOut, double precioBase, List<String> metodosDePago,
			List<PeriodoConPrecio> periodosPublicados, PoliticaDeCancelacion politicaDeCancelacion ) {
		setPropietario(propietario);
		setTipo(tipo);
		setSuperficie(superficie);
		setPais(pais);
		setCiudad(ciudad);
		setDireccion(direccion);
		setServicios(servicios);
		setCapacidad(capacidad);
		setFotos(new ArrayList<String>());
		setCheckIn(checkIn);
		setCheckOut(checkOut);
		setPrecioBase(precioBase);
		setMetodosDePago(metodosDePago);
		setPeriodosPublicados(periodosPublicados);
		setReservasConfirmadas(new ArrayList<Reserva>());
		setReservasPendientes( new ArrayList<Reserva>());
		setReservasEnCola( new ArrayList<Reserva>());
		setPoliticaDeCancelacion( politicaDeCancelacion);
		setInteresados(new HashMap<Evento, List<Interesado>>());	
		}


	public void setPoliticaDeCancelacion(PoliticaDeCancelacion politica) {
		this.politicaDeCancelacion = politica;
	}
	public void setCheckOut(LocalTime checkOut) {
		this.checkOut = checkOut;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}


	public void setPeriodosPublicados(List<PeriodoConPrecio> periodosPublicados) {
		this.periodosPublicados = periodosPublicados;
	}


	public void setPropietario(Usuario propietario) {
		this.propietario = propietario;
	}


	public void setCiudad(String ciudad) {
		this.ciudad = ciudad;
	}


	public void setCapacidad(int capacidad) {
		this.capacidad = capacidad;
	}


	public void setFotos(List<String> fotos) {
		this.fotos = fotos;
	}


	public void setPrecioBase(Double precioBase) {
		this.precioBase = precioBase;
	}


	public void setComentarios(List<String> comentarios) {
		this.comentarios = comentarios;
	}


	public void setRankingInmueble(Ranking rankingInmueble) {
		this.rankingInmueble = rankingInmueble;
	}


	public void setReservasConfirmadas(List<Reserva> reservasConfirmadas) {
		this.reservasConfirmadas = reservasConfirmadas;
	}


	public void setReservasPendientes(List<Reserva> reservasPendientes) {
		this.reservasPendientes = reservasPendientes;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}
	
	public void setReservasEnCola(List<Reserva> reservasEnCola) {
		this.reservasEnCola = reservasEnCola;
	}

	public void setPais(String pais) {
		this.pais = pais;
	}
	
	public void setInteresados(Map<Evento, List<Interesado>> interesados) {
		this.interesados = interesados;
	}
	public void setMetodosDePago(List<String> metodosDePago) {
		this.metodosDePago = metodosDePago;
	}
	
	public void setSuperficie(int superficie) {
		this.superficie = superficie;
	}
	
	public List<PeriodoConPrecio> getPeriodosPublicados() {
		return periodosPublicados;
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
	
	public double getPrecioBase() {
		return precioBase;
	}
	
	public List<Reserva> getReservasConfirmadas() {
		return reservasConfirmadas;
	}
	
	public List<Reserva> getReservasPendientes() {
		return reservasPendientes;
	}
	
	public List<Reserva> getReservasEnCola() {
		return reservasEnCola;
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
	
	public Map<Evento, List<Interesado>> getInteresados() {
		return interesados;
	} 
	
	public List<String> getFotos(){
		return fotos;
	}
	

	public int getSuperficie() {
		return superficie;
	}



	public String getPais() {
		return pais;
	}


	public String getDireccion() {
		return direccion;
	}


	public Set<Servicio> getServicios() {
		return servicios;
	}


	public void setServicios(Set<Servicio> servicios) {
		this.servicios = servicios;
	}


	public LocalTime getCheckIn() {
		return checkIn;
	}


	public void setCheckIn(LocalTime checkIn) {
		this.checkIn = checkIn;
	}


	public LocalTime getCheckOut() {
		return checkOut;
	}


	public List<String> getMetodosDePago() {
		return metodosDePago;
	}


	public void agregarComentario(String comentario) {
		getComentarios().add(comentario);
	}
	public void agregarFoto(String foto) {
		if (getFotos().size() < 5) {
			getFotos().add(foto);
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
	
	public void recibirReserva(Reserva reserva) {
		if (estaDisponibleEnPeriodo(reserva.getFechaEntrada(), reserva.getFechaSalida())) {
			getReservasPendientes().add(reserva);
		} else {
			getReservasEnCola().add(reserva);
		}
	}
	
	public boolean estaDisponibleEnPeriodo(LocalDate fechaEntrada, LocalDate fechaSalida) {
		return getReservasConfirmadas().stream()
				.noneMatch(r -> r.getFechaEntrada().isBefore(fechaSalida) && 
				r.getFechaSalida().isAfter(fechaEntrada));
	}
	
	public void registrarReserva(Reserva reserva) {
		getReservasConfirmadas().add(reserva);
		getReservasPendientes().remove(reserva);
	}
	
	public void eliminarReservaPendiente(Reserva reserva) {
		getReservasPendientes().remove(reserva);
	}

	public void eliminarReserva(Reserva reserva) {
		getReservasConfirmadas().remove(reserva);
	}

	public boolean estaDisponibleHoy() {
		LocalDate hoy = LocalDate.now();
		return getReservasConfirmadas().stream().noneMatch(r -> !hoy.isBefore(r.getFechaEntrada()) && 
				!hoy.isAfter(r.getFechaSalida()));
	}

	public void modificarPrecioPeriodo(PeriodoConPrecio periodo, Double precioNuevo) {
		if (periodo.getPrecioPorDia() > precioNuevo) {
			notificar(Evento.BAJAPRECIO,this);		//
		}
		periodo.setPrecioPorDia(precioNuevo);
	}	
	
	public void modificarPrecioBase(Double precioNuevo) {
		if (precioBase > precioNuevo) {
			notificar(Evento.BAJAPRECIO,this);
		}
		setPrecioBase(precioNuevo);
	}
	
	public void subscribir(Evento evento, Interesado interesado) {
	    getInteresados().computeIfAbsent(evento, k -> new ArrayList<>()).add(interesado);
    }

	public void desubscribir(Evento evento, Interesado interesado) {
	    List<Interesado> interesadosDelEvento = getInteresados().get(evento);
	    if (interesadosDelEvento != null) {
	    	interesadosDelEvento.remove(interesado);
	    }
    }

	public void notificar(Evento evento, Inmueble inmueble) {
	    List<Interesado> interesadosDelEvento = getInteresados().get(evento);

	    if (interesadosDelEvento != null) {
	        interesadosDelEvento.stream().forEach(i -> i.update(evento, inmueble));
	    }
	}
	
	public Double calcularPrecioEstadia(LocalDate fechaEntrada, LocalDate fechaSalida) {
		Double precioTotal = 0d;
		LocalDate fechaActual = fechaEntrada;
		while (!fechaActual.isEqual(fechaSalida)) {
			precioTotal += calcularPrecioDia(fechaActual);
			fechaActual = fechaActual.plusDays(1);
		}
		return precioTotal;
	}



}


