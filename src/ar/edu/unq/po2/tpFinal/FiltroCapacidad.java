package ar.edu.unq.po2.tpFinal;

import java.time.LocalDate;

public class FiltroCapacidad implements FiltroSimple{
	
	
	private int capacidad;

	public FiltroCapacidad(int capacidad) {
		this.setCapacidad(capacidad);
	}

	public int getCapacidad() {
		return capacidad;
	}

	public void setCapacidad(int capacidad) {
		this.capacidad = capacidad;
	}
	
	@Override
	public boolean cumple(Inmueble inmueble) {
		// TODO Auto-generated method stub
		return inmueble.getCapacidad() == getCapacidad();
	}

	@Override
	public boolean esFiltroValido() {
		// TODO Auto-generated method stub
		return (getCapacidad()>=0);
	}
	
	@Override
	public boolean precisaFecha() {
		// TODO Auto-generated method stub
		return false;
	}



	@Override
	public void setFechaInicio(LocalDate fechaInicio) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setFechaFin(LocalDate fechaFin) {
		// TODO Auto-generated method stub
		
	}

}
