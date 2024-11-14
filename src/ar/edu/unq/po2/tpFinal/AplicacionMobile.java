package ar.edu.unq.po2.tpFinal;

import static org.mockito.Mockito.RETURNS_DEEP_STUBS;

public class AplicacionMobile implements Interesado{
	
	private PopUpWindow popupwindow;

	public AplicacionMobile(PopUpWindow popupwindow) {
		setPopUpWindow(popupwindow);
	}

	private void setPopUpWindow(PopUpWindow popupwindow) {
		this.popupwindow = popupwindow; 
	}
	
	private PopUpWindow getPopUpWindow() {
		return popupwindow;
	}
	
	@Override
	public void update(Evento evento, Inmueble inmueble) {
		popupwindow.popUp(evento.aplicarMensaje(inmueble), "BLUE", 12);
	}
	

}
