package ar.edu.unq.po2.tpFinal;

import java.util.List;

public class ValidadorDeReservasParaSitioWeb implements ValidadorDeReservas {

	@Override
	public void verificarSiLosFiltrosSonValidos(List<Filtro> filtros) {
		// TODO Auto-generated method stub
		for (int i=0 ; i <= 2; i++) {
			if (filtros.get(i).equals(null)){
				 throw new IllegalArgumentException();
			}
		}
	}
}
