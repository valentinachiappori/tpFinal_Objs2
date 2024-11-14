package ar.edu.po2.tpFinal;

import static org.junit.jupiter.api.Assertions.*;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import ar.edu.unq.po2.tpFinal.Inmueble;
import ar.edu.unq.po2.tpFinal.PoliticaDeCancelacion;
import ar.edu.unq.po2.tpFinal.Reserva;
import ar.edu.unq.po2.tpFinal.Usuario;


class ReservaTest {

	private Reserva reserva;
	private LocalDate fechaEntrada;
	private LocalDate fechaSalida;
	private Inmueble inmueble;
	private Usuario inquilino;
	private Usuario propietario;
	
	
	@BeforeEach
	void setUp() throws Exception {
		inmueble = mock(Inmueble.class);
		inquilino = mock(Usuario.class);
		fechaEntrada = LocalDate.of(2024, 9, 11);
		fechaSalida = LocalDate.of(2024, 12, 11);
		reserva = new Reserva(fechaEntrada, fechaSalida, inmueble, inquilino);
		propietario = mock(Usuario.class);
	}

	
	@Test
	void testGetPropietario() {
		when(inmueble.getPropietario()).thenReturn(propietario);
		assertEquals(reserva.getPropietario(), propietario);
	}
	
	@Test
	void testGetInquilino() {
		assertEquals(reserva.getInquilino(), inquilino);
	}

	@Test
	void testGetFechaEntrada() {
		assertEquals(reserva.getFechaEntrada(), fechaEntrada);
	}
	
	@Test
	void testGetFechaSalida() {
		assertEquals(reserva.getFechaSalida(), fechaSalida);
	}
	
	@Test
	void testCambiarEstadoAAceptada() {
		reserva.cambiarEstadoAAceptada();
		
		assertEquals(reserva.getEstadoReserva(), "Aceptada");
	}
	
	@Test
	void testCambiarEstadoAFinalizada() {
		reserva.cambiarEstadoAAceptada();
		
		reserva.cambiarEstadoAFinalizada();
		
		assertEquals(reserva.getEstadoReserva(), "Finalizada");
	}
	
	@Test
	void testToString() {
	    PoliticaDeCancelacion politica = mock(PoliticaDeCancelacion.class);
	    when(politica.toString()).thenReturn("Gratuita");
	    when(inmueble.getPoliticaDeCancelacion()).thenReturn(politica);
	    when(inmueble.toString()).thenReturn("Departamento");
	    when(reserva.calcularPrecioEstadia()).thenReturn(300d);
	    String esperado = "Reserva{Inmueble=Departamento, Fecha de entrada=2024-09-11, Fecha de salida=2024-12-11, Política de cancelación= Gratuita, Precio total= $300.0}";


	    assertEquals(esperado, reserva.toString());
	}


	
}


