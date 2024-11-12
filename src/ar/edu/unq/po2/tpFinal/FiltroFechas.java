package ar.edu.unq.po2.tpFinal;

import java.time.LocalDate;

public class FiltroFechas implements Filtro {

	private LocalDate fechaFin;
	private LocalDate fechaInicio;
	public FiltroFechas(LocalDate fechaInicio, LocalDate fechaFin) {
		this.setFechaInicio(fechaInicio);
		this.setFechaFin(fechaFin);
	}
	@Override
	public boolean cumple(Inmueble inmueble) {
		// TODO Auto-generated method stub
		return false;
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
	@Override
	public boolean esFiltroValido() {
		// TODO Auto-generated method stub
		return (!((getFechaInicio()== null))&&
				!((getFechaFin() == null))&&
				(getFechaInicio().isBefore(getFechaFin())));
	}

}
