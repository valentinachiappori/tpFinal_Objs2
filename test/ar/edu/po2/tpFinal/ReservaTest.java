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
import ar.edu.unq.po2.tpFinal.Reserva;
import ar.edu.unq.po2.tpFinal.Usuario;


class ReservaTest {

	private Reserva reserva;
	private LocalDate fechaEntrada;
	private LocalDate fechaSalida;
	private Inmueble inmueble;
	private Usuario inquilino;
	private String estadoReserva;
	
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

}
