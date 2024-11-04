package ar.edu.unq.po2.tpFinal;

import java.time.LocalDate;

public class Gratuita implements PoliticaDeCancelacion{

	public void ejecutar(Reserva reserva) {
		 LocalDate fechaLimiteCancelacion = reserva.getFechaEntrada().minusDays(10);

        if (LocalDate.now().isBefore(fechaLimiteCancelacion)) {
            System.out.println("Cancelación gratuita.");
        } else {
            double cargo = reserva.getInmueble().calcularPrecioDia(reserva.getFechaEntrada()) * 2;
            System.out.println("Cancelación con cargo. Cargo aplicado: $" + cargo);
        }
    }

}
