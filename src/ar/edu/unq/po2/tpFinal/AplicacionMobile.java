package ar.edu.unq.po2.tpFinal;

public class AplicacionMobile implements Interesado{
	private PopUpWindow popupwindow;


	@Override
	public void update(EVENTO evento, Inmueble inmueble) {
		// TODO Auto-generated method stub
		 
			popupwindow.popUp(evento.aplicarMensaje(inmueble), "BLUE", 12);
		
	}
}
