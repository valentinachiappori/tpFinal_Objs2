package ar.edu.unq.po2.tpFinal;

import java.time.LocalDate;

public class PeriodoConPrecio {
	private LocalDate fechaInicio;
	private LocalDate fechaFin;
	private Double precioPorDia;
	
	public PeriodoConPrecio(LocalDate fechaInicio, LocalDate fechaFin, Double precioPorDia) {
		this.fechaInicio = fechaInicio;
		this.fechaFin = fechaFin;
		this.precioPorDia = precioPorDia;
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
		if (this.precioPorDia > precioPorDia) {
			//avisarle al sitio
		}
		this.precioPorDia = precioPorDia;
	}

	public boolean incluidaEnPeriodo(LocalDate fecha) {
		return fecha.isBefore(fechaFin) || fecha.isAfter(fechaInicio) || fecha.isEqual(fechaFin) || fecha.isEqual(fechaInicio);
	}

	
}
