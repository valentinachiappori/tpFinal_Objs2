package ar.edu.po2.tpFinal;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import org.mockito.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import ar.edu.unq.po2.tpFinal.Evento;
import ar.edu.unq.po2.tpFinal.Inmueble;
import ar.edu.unq.po2.tpFinal.PeriodoConPrecio;
import ar.edu.unq.po2.tpFinal.PoliticaDeCancelacion;
import ar.edu.unq.po2.tpFinal.Reserva;
import ar.edu.unq.po2.tpFinal.Servicio;
import ar.edu.unq.po2.tpFinal.Usuario;
import ar.edu.unq.po2.tpFinal.Interesado;

class InmuebleTest {

	private Inmueble inmueble;
	private Usuario usuario;
	private Set<Servicio> servicios;
	private List<String> metodosDePago;
	private List<PeriodoConPrecio> periodosConPrecios;
	private PeriodoConPrecio periodoConPrecio;
	private PoliticaDeCancelacion politicaDeCancelacion;
	private Reserva reserva;
	private Interesado interesado;
	
	@BeforeEach
	void setUp() throws Exception {
	    usuario = mock(Usuario.class);
	    interesado = mock(Interesado.class);
	    periodoConPrecio = mock(PeriodoConPrecio.class);
	    servicios = new HashSet<Servicio>();
	    periodosConPrecios = new ArrayList<PeriodoConPrecio>();
	    politicaDeCancelacion = mock(PoliticaDeCancelacion.class);
		inmueble = new Inmueble(usuario,"casa" ,15, "Argentina", "dsd", "123", servicios, 
					5, LocalTime.of(10,0),LocalTime.of(2,0) , 200d, metodosDePago, periodosConPrecios, politicaDeCancelacion );
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
	void testNoSePuedenAgregarMasDeCincoFotos() {
		inmueble.agregarFoto("foto1");
        inmueble.agregarFoto("foto2");
        inmueble.agregarFoto("foto3");
        inmueble.agregarFoto("foto4");
        inmueble.agregarFoto("foto5");

        assertThrows(IllegalArgumentException.class, () -> {
            inmueble.agregarFoto("foto6"); }, "No se pueden agregar más de 5 fotos.");
	}
	
	@Test
	void testRecibirEnInmuebleUnaReservaPendiente() {
		inmueble.recibirReserva(reserva);
		
		assertEquals(1,inmueble.getReservasPendientes().size());
		assertTrue(inmueble.getReservasPendientes().contains(reserva));
		assertTrue(inmueble.getReservasEnCola().isEmpty());
	}
	
	@Test
	void testCalcularPrecioPorDiaDeUnPeriodoEspecial() {
		LocalDate fecha = LocalDate.of(2024, 12, 12);
		
		when(periodoConPrecio.incluidaEnPeriodo(fecha)).thenReturn(true); 
	    when(periodoConPrecio.getPrecioPorDia()).thenReturn(300d);
	    
	    periodosConPrecios.add(periodoConPrecio);
	    
	    Double precioCalculado = inmueble.calcularPrecioDia(fecha);
	    
	    assertEquals(300d, precioCalculado, 0.01); 

	    verify(periodoConPrecio).incluidaEnPeriodo(fecha);
	    verify(periodoConPrecio).getPrecioPorDia();
	}
	
	@Test
	void testModificarPrecioBase(){
		inmueble.modificarPrecioBase(300d);
		
		assertEquals(300d, inmueble.getPrecioBase());
	}
	
	@Test
	void testModificarPrecioPeriodo(){
		
		inmueble.getPeriodosPublicados().add(periodoConPrecio); 

	    inmueble.modificarPrecioPeriodo(periodoConPrecio, 300d);

	    when(periodoConPrecio.getPrecioPorDia()).thenReturn(300d);

		assertEquals(300d, periodoConPrecio.getPrecioPorDia());
		
		verify(periodoConPrecio, times(1)).setPrecioPorDia(300d);
	}
	
	@Test
	void testRegistrarReserva() {
		inmueble.recibirReserva(reserva);
		inmueble.registrarReserva(reserva);
		
		assertEquals(1,inmueble.getReservasConfirmadas().size());
		assertTrue(inmueble.getReservasConfirmadas().contains(reserva));
		assertTrue(inmueble.getReservasEnCola().isEmpty());
		assertTrue(inmueble.getReservasPendientes().isEmpty());
	}
	
	@Test 
	void testEliminarReserva() {
		inmueble.recibirReserva(reserva);
		inmueble.registrarReserva(reserva);
		inmueble.eliminarReserva(reserva);
		
		assertEquals(0,inmueble.getReservasConfirmadas().size());
		assertTrue(inmueble.getReservasEnCola().isEmpty());
		assertTrue(inmueble.getReservasPendientes().isEmpty());
	}

	@Test
	void testSubscribirUnInteresado() {
		inmueble.subscribir(Evento.BAJAPRECIO, interesado);
		
		assertTrue(inmueble.getInteresados().get(Evento.BAJAPRECIO).contains(interesado));
	    assertEquals(1, inmueble.getInteresados().get(Evento.BAJAPRECIO).size());
	}
	
	@Test
	void testDesubscribirUnInteresado() {
		inmueble.subscribir(Evento.BAJAPRECIO, interesado);
		assertTrue(inmueble.getInteresados().get(Evento.BAJAPRECIO).contains(interesado));
		
		inmueble.desubscribir(Evento.BAJAPRECIO, interesado);

		assertFalse(inmueble.getInteresados().get(Evento.BAJAPRECIO).contains(interesado));
	    assertEquals(0, inmueble.getInteresados().get(Evento.BAJAPRECIO).size());
	}
	
	@Test
	void testNotificar() {
		inmueble.subscribir(Evento.BAJAPRECIO, interesado);
		inmueble.modificarPrecioBase(100d);
		
		verify(interesado, times(1)).update(Evento.BAJAPRECIO, inmueble);
	}
	
	@Test
	void testCalcularPrecioEstadia() {	    
	    when(periodoConPrecio.getPrecioPorDia()).thenReturn(100.0);
	    when(periodoConPrecio.getFechaInicio()).thenReturn(LocalDate.of(2024, 11, 20));
	    when(periodoConPrecio.getFechaFin()).thenReturn(LocalDate.of(2024, 11, 21));
	    
	    Double precioCalculado = inmueble.calcularPrecioEstadia(LocalDate.of(2024, 11, 20), LocalDate.of(2024, 11, 21));

	    assertEquals(100.0 * 2, precioCalculado);
	}
}	
