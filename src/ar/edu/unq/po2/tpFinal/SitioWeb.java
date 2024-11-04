package ar.edu.unq.po2.tpFinal;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;



public class SitioWeb {
	private Administrador administrador;
	private List<Usuario> usuarios;
	private List<Inmueble> inmuebles;
	private List<String> tiposDeInmueble;
	private Set<Servicio> servicios;
	//constructor
	
	//getters y setters
	
	//Dar de alta los Tipos de Inmuebles que se utilizan en el sistema.
	public void darDeAltaTipoInmueble(String tipoDeInmueble) {
		this.tiposDeInmueble.add(tipoDeInmueble);
	}
	
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

	public void darDeAltaInmueble(Inmueble i) {
		if (verificarEsPropietarioRegistrado(i.getPropietario()) && this.tiposDeInmueble.contains(i.getTipoInmueble())) {
			this.inmuebles.add(i);
		}
	}
	
	public boolean verificarEsPropietarioRegistrado(Usuario usuario) {
		return esUsuarioRegistrado(usuario) && esPropietario(usuario);
	}

	private boolean esUsuarioRegistrado(Usuario usuario) {
		return this.usuarios.contains(usuario);
	}

	private boolean esPropietario(Usuario usuario) {
		return usuario.esPropietario();
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
