package ar.edu.unq.po2.tpFinal;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class FiltroCompuesto implements Filtro {
    private List<Filtro> filtros;
    private final Filtro filtroCiudad;
    private final Filtro filtroFechas;
    private LocalDate fechaInicio;
    private LocalDate fechaFin;
    
    
    public FiltroCompuesto( String ciudad, LocalDate fechaEntrada, LocalDate fechaSalida) {
        this.filtroCiudad = new FiltroCiudad(ciudad);
        this.filtroFechas = new FiltroFechas(fechaEntrada, fechaSalida);
        validarFiltros(filtroCiudad,filtroFechas);
        this.fechaInicio = fechaEntrada;
        this.setFechaFin(fechaSalida);
         
        filtros = new ArrayList<Filtro>();
        filtros.add(filtroCiudad);
        filtros.add(filtroFechas);
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

	private void validarFiltros(Filtro filtroCiudad, Filtro filtroFechas) {
		// TODO Auto-generated method stub
		if (!(esFiltroValido(filtroCiudad) || esFiltroValido(filtroFechas))) {
			 throw new IllegalArgumentException("Uno de los FiltrosObligatorios tiene un valorNull");
		};
	}

	private boolean esFiltroValido(Filtro filtro) {
		// TODO Auto-generated method stub
		return filtro.esFiltroValido();
	}



	public void agregarFiltro(FiltroSimple filtro) {

		if (!(esFiltroValido(filtro))) {
			throw new IllegalArgumentException("el filtro es invalido");
		}
		if (filtro.precisaFecha()) {
			filtro.setFechaInicio(getFechaInicio());
		}
		filtros.add(filtro);
	}

	@Override
	public boolean cumple(Inmueble inmueble) {
		// TODO Auto-generated method stub
		return this.filtros.stream().allMatch(f -> f.cumple(inmueble));
	}


	@Override
	public boolean esFiltroValido() {
		// TODO Auto-generated method stub
		return true ;
	}

    
}