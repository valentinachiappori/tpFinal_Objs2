package ar.edu.unq.po2.tpFinal;

import java.time.LocalDate;

public interface FiltroSimple extends Filtro {
	
	boolean precisaFecha();
	void setFechaInicio(LocalDate fechaInicio);
	void setFechaFin(LocalDate fechaFin);
	LocalDate getFechaInicio();
	LocalDate getFechaFin();
}
