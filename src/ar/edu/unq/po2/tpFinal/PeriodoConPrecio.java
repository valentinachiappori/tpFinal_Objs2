package ar.edu.unq.po2.tpFinal;

import java.time.LocalDate;

public class PeriodoConPrecio {
	private LocalDate fechaInicio;
	private LocalDate fechaFin;
	private Double precioPorDia;
	
	public PeriodoConPrecio(LocalDate fechaInicio, LocalDate fechaFin, Double precioPorDia) {
		setFechaInicio(fechaInicio);
		setFechaFin(fechaFin);
		setPrecioPorDia(precioPorDia);
	}
	
	public LocalDate getFechaInicio() {
		return fechaInicio;
	}
	public void setFechaInicio(LocalDate fechaInicio) {
		this.fechaInicio = fechaInicio;
	}
	public LocalDate getFechaFin() {
		return fechaFin;
	}
	public void setFechaFin(LocalDate fechaFin) {
		this.fechaFin = fechaFin;
	}
	public Double getPrecioPorDia() {
		return precioPorDia;
	}
	public void setPrecioPorDia(Double precioPorDia) {
		this.precioPorDia = precioPorDia;
	}

	public boolean incluidaEnPeriodo(LocalDate fecha) {
		return (fecha.isEqual(getFechaInicio()) || fecha.isAfter(getFechaInicio())) 
		           && (fecha.isEqual(getFechaFin()) || fecha.isBefore(getFechaFin()));
	}

}
