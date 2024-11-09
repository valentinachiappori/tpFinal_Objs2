package ar.edu.unq.po2.tpFinal;


public class SitioObservador implements Interesado {
	
	private HomePagePublisher publisher;

	@Override
	public void update(EVENTO evento, Inmueble inmueble) {
		// TODO Auto-generated method stub
	        String mensaje = evento.aplicarMensaje(inmueble);
	        publisher.publish(mensaje);
	}
	
	//q el observer tenga los 3 mensajes
	//ranking (lounico que debemos pensar), pasar las cosas a usuario
	//filtros, admin (decidir implementacion)
	//test!!!
}
