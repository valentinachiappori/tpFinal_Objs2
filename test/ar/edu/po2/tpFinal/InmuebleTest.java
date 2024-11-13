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
import ar.edu.unq.po2.tpFinal.PeriodoConPrecio;
import ar.edu.unq.po2.tpFinal.PoliticaDeCancelacion;
import ar.edu.unq.po2.tpFinal.Reserva;
import ar.edu.unq.po2.tpFinal.Servicio;
import ar.edu.unq.po2.tpFinal.Usuario;


class InmuebleTest {

	
	private Inmueble inmueble;
	private Usuario usuario;
	private Set<Servicio> servicios1;
	private Servicio servicio;
	private List<String> metodosDePago;
	private List<PeriodoConPrecio> periodoConPrecios;
	private PeriodoConPrecio periodoConPrecio;
	private PoliticaDeCancelacion politicaDeCancelacion;
	private Reserva reserva;
	private List<Reserva> reservas;
	
	@BeforeEach
	void setUp() throws Exception {
	    usuario = mock(Usuario.class);
	    servicio = mock(Servicio.class);
	    periodoConPrecio = mock(PeriodoConPrecio.class);
	    servicios1 = new HashSet<Servicio>();
	    politicaDeCancelacion = mock(PoliticaDeCancelacion.class);
		inmueble = new Inmueble(usuario,"casa" ,15, "Argentina", "dsd", "123", servicios1, 5
				, LocalTime.of(10,0),LocalTime.of(2,0) , 200d, metodosDePago,
				periodoConPrecios, politicaDeCancelacion );
		reserva = mock(Reserva.class);	
		}
	
	@Test
	void testInmuebleDisponibleEnPeriodo() {
		inmueble.getReservasConfirmadas().add(reserva);
		when(reserva.getFechaEntrada()).thenReturn(LocalDate.of(2024, 12, 12)); 
		when(reserva.getFechaSalida()).thenReturn(LocalDate.of(2024, 12, 15)); 
		
		assertTrue(inmueble.estaDisponibleEnPeriodo(LocalDate.of(2024, 9, 11),LocalDate.of(2024, 7, 12))) ;
	}
	
	@Test
	void testInmuebleDisponibleHoy() {
		inmueble.getReservasConfirmadas().add(reserva);
		when(reserva.getFechaEntrada()).thenReturn(LocalDate.of(2024, 12, 12)); 
		when(reserva.getFechaSalida()).thenReturn(LocalDate.of(2024, 12, 15)); 
		
		assertTrue(inmueble.estaDisponibleHoy());
	}
	
	@Test
	void testAgregarFoto() {
		inmueble.agregarFoto("foto1");
		
		assertEquals(inmueble.getFotos().size(),1);
	}
	
	@Test
	void registrarReservaEnColaPorqueEstaReservadoElInmueble() {
		when(reserva.getFechaEntrada()).thenReturn(LocalDate.of(2024, 12, 12)); 
		when(reserva.getFechaSalida()).thenReturn(LocalDate.of(2024, 12, 15));
		when(inmueble.estaDisponibleEnPeriodo(reserva.getFechaEntrada(), reserva.getFechaSalida())).thenReturn(false);
		inmueble.recibirReserva(reserva);
		
		assertEquals(inmueble.getReservasEnCola().size(),1);
		assertTrue(inmueble.getReservasPendientes().isEmpty());
	}
	
	
}	
