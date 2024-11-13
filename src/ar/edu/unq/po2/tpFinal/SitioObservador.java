package ar.edu.unq.po2.tpFinal;


public class SitioObservador implements Interesado {
	
	private HomePagePublisher publisher;
	
	public SitioObservador(HomePagePublisher publisher) {
        setPublisher(publisher);
    }

	public HomePagePublisher getPublisher() {
		return publisher;
	}

	public void setPublisher(HomePagePublisher publisher) {
		this.publisher = publisher;
	}

	@Override
	public void update(Evento evento, Inmueble inmueble) {
	        getPublisher().publish(evento.aplicarMensaje(inmueble));
	}
	
}
