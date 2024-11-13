package ar.edu.unq.po2.tpFinal;

import java.time.LocalDate;
import java.util.function.Function;

public enum Evento {
	  CANCELACION(inmueble -> "un inmueble " + inmueble.getTipoInmueble() + "a tan sólo $" + inmueble.calcularPrecioDia(LocalDate.now())),
	  RESERVA(inmueble -> "un inmueble " + inmueble.getTipoInmueble() + "a tan sólo $" + inmueble.calcularPrecioDia(LocalDate.now())),
	  BAJAPRECIO(inmueble -> "El/la " + inmueble.getTipoInmueble() + "que te interesa se ha liberado! Corre a reservarlo!");


	private Function<Inmueble, String> mensaje;
	

	Evento(Function<Inmueble, String> mensaje) {
		setMensaje(mensaje);
};
    
    public String aplicarMensaje(Inmueble inmueble) {
        return mensaje.apply(inmueble);
    }
    
    private void setMensaje(Function<Inmueble, String> mensaje) {
    	this.mensaje = mensaje;
    }
}
