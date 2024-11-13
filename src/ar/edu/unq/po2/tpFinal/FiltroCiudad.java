package ar.edu.unq.po2.tpFinal;

public class FiltroCiudad implements Filtro {
	
	
	private String ciudad;
	
	public FiltroCiudad(String ciudad) {
		this.setCiudad(ciudad);
	}
	@Override
	public boolean cumple(Inmueble inmueble) {
		// TODO Auto-generated method stub
		return inmueble.getCiudad().equals(getDato());
	}
	public String getDato() {
		return ciudad;
	}
	public void setCiudad(String ciudad) {
		this.ciudad = ciudad;
	}
	@Override
	public boolean esFiltroValido() {
		// TODO Auto-generated method stub
		return (!(getDato()==null));
	}

}
