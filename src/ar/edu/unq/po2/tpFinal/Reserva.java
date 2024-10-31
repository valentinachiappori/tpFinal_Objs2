package ar.edu.unq.po2.tpFinal;

import java.time.LocalDate;

public class Reserva {
	private LocalDate fechaEntrada;
	private LocalDate fechaSalida;
	private Inmueble inmueble;
	
	public Reserva(LocalDate fechaEntrada, LocalDate fechaSalida, Inmueble inmueble) {
		this.fechaEntrada = fechaEntrada;
		this.fechaSalida = fechaSalida;
		this.inmueble = inmueble;
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
	
}
