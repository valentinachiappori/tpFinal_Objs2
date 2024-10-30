package ar.edu.unq.po2.tpFinal;

import java.util.ArrayList;
import java.util.List;

public class Inmueble {
    private String tipo;
	private int superficie;
	private String pais; // 
	private String Ciudad; // 
	private List<String> servicios;
	private int capacidad;
	private List<String> fotos;
	private String horarios; // ni idea como hacerlo jsjsjs Locals Dates(?
	private double precio; //EstrategiaPrecio que dependiendo el dia te de un precio o el otro
	private List<Integer> calificaciones;

	public Inmueble(String tipo, int superficie, List<String> servicios, int capacidad, List<String> fotos, String horarios, double precio) {
	    this.tipo = tipo;
	    this.superficie = superficie;
	    this.servicios = servicios;
	    this.capacidad = capacidad;
	    this.fotos = fotos;
	    this.horarios = horarios;
	    this.precio = precio;
	    this.calificaciones = new ArrayList<Integer>();
	}

	public void agregarCalificacion(int puntuacion) {
	    if (puntuacion >= 1 && puntuacion <= 5) {
	        this.calificaciones.add(puntuacion);
	    } else {
	        throw new IllegalArgumentException("La calificación debe estar entre 1 y 5.");
	    }
	}  // podriamos hacer que la calificacion se de con Enums asi nos evitamos que salga de ese rango

}


