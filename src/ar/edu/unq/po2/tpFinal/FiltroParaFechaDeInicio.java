package ar.edu.unq.po2.tpFinal;

import java.time.LocalDate;

public class FiltroParaFechaDeInicio implements Filtro{
	
	
	private LocalDate dato;

	public  FiltroParaFechaDeInicio(LocalDate fechaInicio) {
		this.dato =(fechaInicio);
	}
	
	@Override
	public boolean cumpleElInmubleMiCriterio(Inmueble inmueble) {
		// TODO Auto-generated method stub
		return true;
	}

	@SuppressWarnings("unchecked")
	@Override
	public <T> T getDato() {
		// TODO Auto-generated method stub
		return (T) this.dato;
	}

}
