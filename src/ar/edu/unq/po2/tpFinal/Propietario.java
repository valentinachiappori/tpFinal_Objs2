package ar.edu.unq.po2.tpFinal;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Propietario extends Usuario {
	
	private List<Inmueble> inmuebles;
	private List<Puntaje> calificaciones;
	private List<Reserva> posiblesReservas;
	
	public Propietario(String nombreCompleto, int numeroDeTelefono, String correoElectronico) {
		super(nombreCompleto, numeroDeTelefono, correoElectronico);
		this.inmuebles = new ArrayList<Inmueble>();
	}
	
	public void ponerEnAlquilerUnImbueble(Inmueble i) {
		
	}

	public List<Inmueble> getInmuebles() {
		return inmuebles;
	}

	public void setInmuebles(List<Inmueble> inmuebles) { //no se si lo usamos
		this.inmuebles = inmuebles;
	}
	
    public void agregarInmueble(Inmueble i) {
        inmuebles.add(i);
    }

	public void agregarCalificacion(Puntaje puntuacion) {
	    this.calificaciones.add(puntuacion);
	}

	public void recibirOferta(Reserva reserva) {
		
		
	}

}
