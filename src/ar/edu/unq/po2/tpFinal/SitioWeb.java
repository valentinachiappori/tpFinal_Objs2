package ar.edu.unq.po2.tpFinal;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

public class SitioWeb {
	private Administrador administrador;
	private List<Inquilino> inquilinos;
	private List<Propietario> propietarios;
	private List<Inmueble> inmuebles;
	
	//constructor
	
	//getters y setters
	
	public List<Inmueble> filtrarInmuebles(String ciudad, LocalDate fechaEntrada, LocalDate fechaSalida, Integer cantHuespedes,
			Double precioMin, Double precioMax) {
		List<Inmueble> inmueblesFiltrados = inmuebles.stream()
		.filter(i -> i.getCiudad().equals(ciudad))
		.filter(i -> i.estaDisponibleEnPeriodo(fechaEntrada, fechaSalida))
		.filter(i -> cantHuespedes == null || i.getCapacidad() >= cantHuespedes)
		.filter(i -> precioMin == null || i.calcularPrecioEstadia(fechaEntrada, fechaSalida) >= precioMin)
		.filter(i -> precioMax == null || i.calcularPrecioEstadia(fechaEntrada, fechaSalida) <= cantHuespedes)
		.toList();
		
		return inmueblesFiltrados;
	}
	
	
	
	
}
