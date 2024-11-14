package ar.edu.unq.po2.tpFinal;

import java.time.LocalDate;

public class CancelacionIntermedia implements PoliticaDeCancelacion{

	public void ejecutar(Reserva reserva) {
		 LocalDate fecha19DiasAntes = reserva.getFechaEntrada().minusDays(19);
		 LocalDate fecha10DiasAntes = reserva.getFechaEntrada().minusDays(10);
		 LocalDate hoy = LocalDate.now();

		 if (hoy.isAfter(fecha10DiasAntes)) {
			 System.out.println("Cancelación con cargo. Cargo aplicado: $" + reserva.calcularPrecioEstadia());
		 } else if (hoy.isAfter(fecha19DiasAntes) && hoy.isBefore(fecha10DiasAntes)){
			 System.out.println("Cancelación con cargo. Cargo aplicado: $" + reserva.calcularPrecioEstadia() / 2);
		 } else {
			 System.out.println("Cancelación sin cargo.");
		 }
	}

}
