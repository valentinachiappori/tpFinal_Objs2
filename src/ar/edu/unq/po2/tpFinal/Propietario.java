package ar.edu.unq.po2.tpFinal;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Propietario extends Usuario {
	
	private List<Inmueble> inmuebles;
	private List<Puntaje> calificaciones;
	private List<Reserva> posiblesReservas; //ver
	private List<Reserva> reservas;
	
	public Propietario(String nombreCompleto, int numeroDeTelefono, String correoElectronico) {
		super(nombreCompleto, numeroDeTelefono, correoElectronico);
		this.inmuebles = new ArrayList<Inmueble>();
	}
	
	public List<Inmueble> getInmuebles() {
		return inmuebles;
	}

	public void setInmuebles(List<Inmueble> inmuebles) { //no se si lo usamos
		this.inmuebles = inmuebles;
	}
	
	public void ponerEnAlquilerUnImbueble(Inmueble i) {
		
	}
	
    public void agregarInmueble(Inmueble i) {
        inmuebles.add(i);
    }

	public void agregarCalificacion(Puntaje puntuacion) {
	    this.calificaciones.add(puntuacion);
	}

	public void recibirOferta(Reserva reserva) {
		
		
	}
	
	public int cantVecesQueFueAlquiladoElInmueble(Inmueble inmueble) {
		return inmueble.getReservas().size(); //solo hay q contar las finalizadas?
	}
	
	public int cantVecesQueAlquilo(){
		return inmuebles.stream().mapToInt(i -> i.getReservas().size()).sum();
	}
	
	public List<Inmueble> todosLosInmueblesQueYaFueronAlquilados() {
		return inmuebles.stream().filter(i -> i.getReservas().size() >= 1).toList();
	}

}
