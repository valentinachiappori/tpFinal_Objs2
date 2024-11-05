package ar.edu.unq.po2.tpFinal;

public class AplicacionMobile implements Interesado{
	private PopUpWindow popupwindow;

	public void update(String cambio, Inmueble inmueble) {
		if (cambio.equals("cancelación de reserva")) {
			popupwindow.popUp("El/la " + inmueble.getTipoInmueble() 
			+ "que te interesa se ha liberado! Corre a reservarlo!", "BLUE", 12);
		}
	}
}
