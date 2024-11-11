package ar.edu.unq.po2.tpFinal;

public class SinCancelacion implements PoliticaDeCancelacion {
	
	public void ejecutar(Reserva reserva) {
           System.out.println("Cancelación con cargo. Cargo aplicado: $" + reserva.calcularPrecioEstadia());
	}
}
