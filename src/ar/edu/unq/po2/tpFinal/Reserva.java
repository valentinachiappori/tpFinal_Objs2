package ar.edu.unq.po2.tpFinal;

import java.time.LocalDate;

public class Reserva {
	private LocalDate fechaEntrada;
	private LocalDate fechaSalida;
	
	public Reserva(LocalDate fechaEntrada, LocalDate fechaSalida) {
		this.fechaEntrada = fechaEntrada;
		this.fechaSalida = fechaSalida;
	}

	public LocalDate getFechaEntrada() {
		return fechaEntrada;
	}
	
	public LocalDate getFechaSalida() {
		return fechaSalida;
	}

	
}
