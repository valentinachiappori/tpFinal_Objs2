package ar.edu.unq.po2.tpFinal;

public class FiltroCiudad implements Filtro {
	
	private String dato;

	public FiltroCiudad(String ciudad) {
		this.setDato(ciudad);
	}
	
	
    @SuppressWarnings("unchecked")
	@Override
	public String getDato() {
		return dato;
	}

	private void setDato(String dato) {
		this.dato = dato;
	}

	@Override
	public boolean cumpleElInmubleMiCriterio(Inmueble inmueble) {
		return inmueble.getCiudad().equals(getDato());
		// TODO Auto-generated method stub
	}
}
