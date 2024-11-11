package ar.edu.unq.po2.tpFinal;

import java.time.LocalDate;

public class Reserva {
	private LocalDate fechaEntrada;
	private LocalDate fechaSalida;
	private Inmueble inmueble;
	private Usuario inquilino;
	private String estadoReserva; 
	
	public Reserva(LocalDate fechaEntrada, LocalDate fechaSalida, Inmueble inmueble, Usuario inquilino) {
		this.fechaEntrada = fechaEntrada;
		this.fechaSalida = fechaSalida;
		this.inmueble = inmueble;
		this.inquilino = inquilino;
		this.setEstadoReserva("Pendiente");
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

	public Usuario getInquilino() {
		return inquilino;
	}

	public Usuario getPropietario() {
		return this.inmueble.getPropietario();
	}

	public void cambiarEstadoAAceptada() {
		this.setEstadoReserva("Aceptada");
	}

	public void cambiarEstadoAFinalizada() {
		this.setEstadoReserva("Finalizada");
	}

	public String getEstadoReserva() {
		return estadoReserva;
	}

	public void setEstadoReserva(String estadoReserva) {
		this.estadoReserva = estadoReserva;
	}
	
	public Double calcularPrecioEstadia() {
		Double precioTotal = 0d;
		LocalDate fechaActual = fechaEntrada;
		while (fechaActual != fechaSalida) {
			precioTotal += inmueble.calcularPrecioDia(fechaActual);
			fechaActual.plusDays(1);
		}
		return precioTotal;
	}
	
	public String toString() {
	        return "Reserva{" +
	               "Inmueble=" + inmueble +
	               ", Fecha de entrada=" + fechaEntrada +
	               ", Fecha de salida=" + fechaSalida +
	               ", Política de cancelación= " + getInmueble().getPoliticaDeCancelacion() +
	               ", Precio total= $" + calcularPrecioEstadia() +
	               '}';
	    }
	 
}
