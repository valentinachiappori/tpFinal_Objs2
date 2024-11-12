package ar.edu.unq.po2.tpFinal;

public class FiltroCiudad implements Filtro {
	
	
	private String dato;
	
	public FiltroCiudad(String ciudad) {
		this.setDato(ciudad);
	}
	@Override
	public boolean cumple(Inmueble inmueble) {
		// TODO Auto-generated method stub
		return false;
	}
	public String getDato() {
		return dato;
	}
	public void setDato(String dato) {
		this.dato = dato;
	}
	@Override
	public boolean esFiltroValido() {
		// TODO Auto-generated method stub
		return (!(getDato()==null));
	}

}
