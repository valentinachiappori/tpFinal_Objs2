package ar.edu.unq.po2.tpFinal;

import java.time.LocalDate;

public class SitioObservador implements Interesado {
	
	private HomePagePublisher publisher;

	@Override
	public void update(String cambio, Inmueble inmueble) {
		if (cambio.equals("Baja de precio")) {
			publisher.publish("un inmueble " + inmueble.getTipoInmueble() + "a tan sólo $" + inmueble.calcularPrecioDia(LocalDate.now()));
		}
	}

}
