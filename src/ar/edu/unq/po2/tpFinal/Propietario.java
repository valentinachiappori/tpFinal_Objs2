package ar.edu.unq.po2.tpFinal;

import java.util.ArrayList;
import java.util.List;

public class Propietario extends Usuario {
	
	private List<Inmueble> inmuebles;
	
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

	
}
