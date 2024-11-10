package ar.edu.unq.po2.tpFinal;

public class AplicacionMobile implements Interesado{
	
	private PopUpWindow popupwindow;

	public AplicacionMobile(PopUpWindow popupwindow) {
		this.popupwindow = popupwindow;
	}

	@Override
	public void update(Evento evento, Inmueble inmueble) {
		popupwindow.popUp(evento.aplicarMensaje(inmueble), "BLUE", 12);
	}
}
