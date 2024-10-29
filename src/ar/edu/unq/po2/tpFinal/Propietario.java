package ar.edu.unq.po2.tpFinal;

import java.util.ArrayList;
import java.util.List;

public class Propietario extends Usuario {
	
	private List<Inmueble> inmuebles;
	private List<Integer> calificaciones;
	
	public Propietario(String nombreCompleto, int numeroDeTelefono, String correoElectronico) {
		super(nombreCompleto, numeroDeTelefono, correoElectronico);
		// TODO Auto-generated constructor stub
		setInmuebles(new ArrayList<Inmueble>());
	}
	
	public void ponerEnAlquilerUnImbueble(Inmueble i) {
		
	}

	public List<Inmueble> getInmuebles() {
		return inmuebles;
	}

	public void setInmuebles(List<Inmueble> inmuebles) {
		this.inmuebles = inmuebles;
	}
	
    public void agregarInmueble(Inmueble i) {
        inmuebles.add(i);
    }

	public void agregarCalificacion(int puntuacion) {
	    if (puntuacion >= 1 && puntuacion <= 5) {
	        this.calificaciones.add(puntuacion);
	    } else {
	        throw new IllegalArgumentException("La calificación debe estar entre 1 y 5.");
	    }
	}  // podriamos hacer que la calificacion se de con Enums asi nos evitamos que salga de ese rango

}
