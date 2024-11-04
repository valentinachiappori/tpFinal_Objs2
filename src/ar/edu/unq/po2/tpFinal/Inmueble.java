package ar.edu.unq.po2.tpFinal;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.Set;

public class Inmueble {
    private String tipo;
	private int superficie;
	private String pais; // 
	private String ciudad; // 
	private Propietario propietario;
	private Set<Servicio> servicios; //ver esto
	private int capacidad;
	private List<String> fotos; //que no sean mas de 5 
	private LocalTime checkIn;
	private LocalTime checkOut; 
	//private double precio;
	// si usamos periodoConPrecio no necesitariamos precio como atributo, sino un mensaje.
	private List<String> metodosDePago;
	private List<PeriodoConPrecio> periodosPublicados;
	private List<Integer> calificaciones;
	private List<Reserva> reservas;
	private List<Reserva> reservasEnCola;
	private PoliticaDeCancelacion politicaDeCancelacion;

	
	public Inmueble(String tipo, int superficie, String pais, String ciudad, Propietario propietario, Set<Servicio> servicios, int capacidad
			, LocalTime checkIn, LocalTime checkOut, List<String> metodosDePago,
			List<PeriodoConPrecio> periodosPublicados, PoliticaDeCancelacion politicaDeCancelacion ) {
		super();
		this.tipo = tipo;
		this.superficie = superficie;
		this.pais = pais;
		this.ciudad = ciudad;
		this.propietario = propietario;
		this.servicios = servicios;
		this.capacidad = capacidad;
		this.fotos = new ArrayList<String>();
		this.checkIn = checkIn;
		this.checkOut = checkOut;
		this.metodosDePago = metodosDePago;
		this.periodosPublicados = periodosPublicados;
		this.reservas = new ArrayList<Reserva>();
		this.politicaDeCancelacion = politicaDeCancelacion;
		this.reservasEnCola = new ArrayList<Reserva>();
		
		
 		this.calificaciones = new ArrayList<Integer>();
	}

	public void agregarFoto(String foto) {
		if (fotos.size() < 5) {
			fotos.add(foto);
		} //tirar error
	}

	public void agregarCalificacion(int puntuacion) {
	    if (puntuacion >= 1 && puntuacion <= 5) {
	        this.calificaciones.add(puntuacion);
	    } else {
	        throw new IllegalArgumentException("La calificación debe estar entre 1 y 5.");
	    }
	}  // podriamos hacer que la calificacion se de con Enums asi nos evitamos que salga de ese rango

	public String getCiudad() {
		return ciudad;
	}

	public boolean estaDisponibleEnPeriodo(LocalDate fechaEntrada, LocalDate fechaSalida) {
		
		return reservas.stream().noneMatch(r -> r.getFechaEntrada().isBefore(fechaSalida) && 
				r.getFechaSalida().isAfter(fechaEntrada));
	}

	public int getCapacidad() {
		return capacidad;
	}
	
	public Propietario getPropietario() {
		return propietario;
	}

	public Double calcularPrecioDia(LocalDate fecha) {
		for (PeriodoConPrecio periodo : periodosPublicados) {
			if (periodo.incluidaEnPeriodo(fecha)) {
				return periodo.getPrecioPorDia();
			}
		}
		throw new IllegalArgumentException("Fecha inválida");
	}
	
	public Double calcularPrecioEstadia(LocalDate fechaEntrada, LocalDate fechaSalida) {
		Double precioTotal = 0d;
		LocalDate fechaActual = fechaEntrada;
		while (fechaActual != fechaSalida) {
			precioTotal += calcularPrecioDia(fechaActual);
			fechaActual.plusDays(1);
		}
		return precioTotal;
	}

	public List<Reserva> getReservas() {
		return reservas;
	}

	public String getTipoInmueble() {
		return tipo;
	}

	public void eliminarReserva(Reserva reserva) {
		this.reservas.remove(reserva);
	}

	public boolean estaDisponibleHoy() {
		LocalDate hoy = LocalDate.now();
		return reservas.stream().noneMatch(r -> !hoy.isBefore(r.getFechaEntrada()) && 
				!hoy.isAfter(r.getFechaSalida()));
	}

	public PoliticaDeCancelacion getPoliticaDeCancelacion() {
		return this.politicaDeCancelacion;
	}

	public List<Reserva> getReservasEnCola() {
		return this.reservasEnCola;
	}
	
	
	/*
	public boolean cumplenConLosFiltros(List<Filtro> filtros) {
		// TODO Auto-generated method stub
		return filtros.stream().allMatch(f -> f.cumpleElInmubleMiCriterio(this));
	}
	*/
}


