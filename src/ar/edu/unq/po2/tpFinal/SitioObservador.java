package ar.edu.unq.po2.tpFinal;


public class SitioObservador implements Interesado {
	
	private HomePagePublisher publisher;
	
	public SitioObservador(HomePagePublisher publisher) {
        this.publisher = publisher;
    }

	@Override
	public void update(Evento evento, Inmueble inmueble) {
	        publisher.publish(evento.aplicarMensaje(inmueble));
	}
	
}
