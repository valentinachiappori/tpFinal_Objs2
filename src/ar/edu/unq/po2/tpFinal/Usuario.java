package ar.edu.unq.po2.tpFinal;

public class Usuario {
	private String nombreCompleto;

	private int numeroDeTelefono;

	private String correoElectronico;
	
	public Usuario(String nombreCompleto, int numeroDeTelefono, String correoElectronico) {
		setNombreCompleto(nombreCompleto);
		setNumeroDeTelefono(numeroDeTelefono);
		setCorreoElectronico(correoElectronico);
	}
	
	public void setNombreCompleto(String nombreCompleto) {
		this.nombreCompleto = nombreCompleto;
	}
	
	public String getNombreCompleto() {
		return nombreCompleto;
	}

	public int getNumeroDeTelefono() {
		return numeroDeTelefono;
	}

	public void setNumeroDeTelefono(int numeroDeTelefono) {
		this.numeroDeTelefono = numeroDeTelefono;
	}

	public String getCorreoElectronico() {
		return correoElectronico;
	}

	public void setCorreoElectronico(String correoElectronico) {
		this.correoElectronico = correoElectronico;
	}
}
