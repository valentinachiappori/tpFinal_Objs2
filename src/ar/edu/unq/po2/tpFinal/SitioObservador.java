package ar.edu.unq.po2.tpFinal;

import java.time.LocalDate;

public class SitioObservador implements Interesado {
	
	private HomePagePublisher publisher;

	@Override
	public void update(String cambio, Inmueble inmueble) {
		if (cambio.equals("Baja de precio")) {
			publisher.publish(cambio.funcion()/x-> "un inmueble " + x.getTipoInmueble() + "a tan sólo $" + x.calcularPrecioDia(LocalDate.now()) inmueble);
		}
	}
	
	//q el observer tenga los 3 mensajes
	//ranking (lounico que debemos pensar), pasar las cosas a usuario
	//filtros, admin (decidir implementacion)
	//test!!!
}
