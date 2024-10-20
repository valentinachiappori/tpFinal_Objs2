package ar.edu.unq.po2.tpFinal;

import java.util.List;

public class Inquilino extends Usuario {
	private List<Inmueble> misReservas;
	
	public Inquilino(String nombreCompleto, int numeroDeTelefono, String correoElectronico) {
		super(nombreCompleto, numeroDeTelefono, correoElectronico);
		// TODO Auto-generated constructor stub
	}

	public List<Inmueble> getMisReservas() {
		return misReservas;
	}
// el public de este metodo esta raro
	public void setMisReservas (List<Inmueble> misReservas) {
		this.misReservas = misReservas;
	}
	
	
}
