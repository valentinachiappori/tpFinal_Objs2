package ar.edu.unq.po2.tpFinal;

import java.time.LocalDate;

public class Reserva {
	private LocalDate fechaEntrada;
	private LocalDate fechaSalida;
	private Inmueble inmueble;
	private Inquilino inquilino;
	private EstadoReserva estadoReserva; 
	
	public Reserva(LocalDate fechaEntrada, LocalDate fechaSalida, Inmueble inmueble, Inquilino inquilino) {
		this.fechaEntrada = fechaEntrada;
		this.fechaSalida = fechaSalida;
		this.inmueble = inmueble;
		this.inquilino = inquilino;
		this.estadoReserva = new Pendiente();
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
		// TODO Auto-generated method stub
		return this.inmueble.getPropietario();
	}

	public void esAceptada() {
		this.estadoReserva = new Aceptada();
	}


}
