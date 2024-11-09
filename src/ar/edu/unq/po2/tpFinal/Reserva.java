package ar.edu.unq.po2.tpFinal;

import java.time.LocalDate;

public class Reserva {
	private LocalDate fechaEntrada;
	private LocalDate fechaSalida;
	private Inmueble inmueble;
	private Usuario inquilino;
	private String estadoReserva; 
	private Double precioTotal;
	
	public Reserva(LocalDate fechaEntrada, LocalDate fechaSalida, Inmueble inmueble, Usuario inquilino, Double precioTotal) {
		this.fechaEntrada = fechaEntrada;
		this.fechaSalida = fechaSalida;
		this.inmueble = inmueble;
		this.inquilino = inquilino;
		this.setEstadoReserva("Pendiente");
		this.precioTotal = precioTotal;
	}

	public LocalDate getFechaEntrada() {
		return fechaEntrada;
	}
	
	public LocalDate getFechaSalida() {
		return fechaSalida;
	}

	public Inmueble getInmueble() {
		return inmueble;
	}

	public Inquilino getInquilino() {
		return inquilino;
	}

	public Propietario getPropietario() {
		return this.inmueble.getPropietario();
	}

	public void cambiarEstadoAAceptada() {
		this.setEstadoReserva("Aceptada");
	}

	public void cambiarEstadoACancelada() {
		this.setEstadoReserva("Cancelada");
	}

	public String getEstadoReserva() {
		return estadoReserva;
	}

	public void setEstadoReserva(String estadoReserva) {
		this.estadoReserva = estadoReserva;
	}

	public Double getPrecioTotal() {
		return this.precioTotal;
	}
	
	 public String toString() {
	        return "Reserva{" +
	               "Inmueble=" + inmueble +
	               ", Fecha de entrada=" + fechaEntrada +
	               ", Fecha de salida=" + fechaSalida +
	               ", Política de cancelación= " + getInmueble().getPoliticaDeCancelacion() +
	               ", Precio total= $" + precioTotal + 
	               '}';
	    }
	 
	 //metodo hacer checkOut!!!!!!
}
