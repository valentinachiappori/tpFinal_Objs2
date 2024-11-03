package ar.edu.unq.po2.tpFinal;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;



public class SitioWeb {
	private Administrador administrador;
	private List<Inquilino> inquilinos;
	private List<Propietario> propietarios;
	private List<Inmueble> inmuebles;
	//constructor
	
	//getters y setters
	
	
	public List<Inmueble> filtrarInmuebles(String ciudad, LocalDate fechaEntrada, LocalDate fechaSalida, Integer cantHuespedes,
			Double precioMin, Double precioMax) {
		if (ciudad.equals(null)||fechaEntrada.equals(null)|| fechaSalida.equals(null)) {
			 new IllegalArgumentException();
		}
		List<Inmueble> inmueblesFiltrados = inmuebles.stream()
		.filter(i -> i.getCiudad().equals(ciudad))
		.filter(i -> i.estaDisponibleEnPeriodo(fechaEntrada, fechaSalida))
		.filter(i -> cantHuespedes == null || i.getCapacidad() >= cantHuespedes)
		.filter(i -> precioMin == null || i.calcularPrecioEstadia(fechaEntrada, fechaSalida) >= precioMin)
		.filter(i -> precioMax == null || i.calcularPrecioEstadia(fechaEntrada, fechaSalida) <= precioMax)
		.toList();
		
		return inmueblesFiltrados;
	}
	
	
	/*
	public List<Inmueble> filtrarInmuebles(String ciudad, LocalDate fechaEntrada, LocalDate fechaSalida, int cantHuespedes,
			Double precioMin, Double precioMax){
		if (ciudad.equals(null)||fechaEntrada.equals(null)|| fechaSalida.equals(null)) {
			 new IllegalArgumentException();
		}
		
		List<Filtro> filtros = Stream.of(
	    	new FiltroCiudad(ciudad),
	        new FiltroFechaEntradaYSalida(fechaEntrada, fechaSalida),
			precioMin != null ? new FiltroPrecioMinDeFecha(precioMin, fechaEntrada, fechaSalida) : null,
        	precioMax != null ? new FiltroPrecioMaxDeFecha(precioMax, fechaEntrada, fechaSalida) : null,
        	cantHuespedes > 0 ? new FiltroCantHuespedes(cantHuespedes) : null
	    ).filter(filtro -> filtro != null).toList(); 
		return this.inmuebles.stream().filter(i-> i.cumplenConLosFiltros(filtros)).toList();
	}
	
	*/
}
