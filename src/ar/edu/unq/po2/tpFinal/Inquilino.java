package ar.edu.unq.po2.tpFinal;

import java.util.List;

public class Inquilino extends Usuario {
	private List<Reserva> reservas;
	private List<Integer> calificaciones;

	public Inquilino(String nombreCompleto, int numeroDeTelefono, String correoElectronico) {
		super(nombreCompleto, numeroDeTelefono, correoElectronico);
		// TODO Auto-generated constructor stub
	}

	public void agregarReserva(Reserva reserva) {
	        reservas.add(reserva);
	}	
	public List<Reserva>  getMisReservas() {
		return reservas;
	}
// el public de este metodo esta raro
	public void setMisReservas (List<Reserva>  misReservas) {
		this.reservas = misReservas;
	}
	
	public void agregarCalificacion(int puntuacion) {
	    if (puntuacion >= 1 && puntuacion <= 5) {
	        calificaciones.add(puntuacion);
	    } else {
	        throw new IllegalArgumentException("La calificación debe estar entre 1 y 5.");
	    }
	}  // podriamos hacer que la calificacion se de con Enums asi nos evitamos que salga de ese rango

}
