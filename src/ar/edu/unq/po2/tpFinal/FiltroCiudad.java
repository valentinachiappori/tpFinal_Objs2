package ar.edu.unq.po2.tpFinal;

public class FiltroCiudad implements Filtro {
	
	
	private String ciudad;
	
	public FiltroCiudad(String ciudad) {
		this.setCiudad(ciudad);
	}
	
	public String getCiudad() {
		return ciudad;
	}
	public void setCiudad(String ciudad) {
		this.ciudad = ciudad;
	}
	
	@Override
	public boolean cumple(Inmueble inmueble) {
		// TODO Auto-generated method stub
		return inmueble.getCiudad().equals(getCiudad());
	}

	@Override
	public boolean esFiltroValido() {
		// TODO Auto-generated method stub
		return (!(getCiudad().equals(null)));
	}

}
