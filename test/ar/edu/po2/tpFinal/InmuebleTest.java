package ar.edu.po2.tpFinal;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import org.mockito.*;
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
	private List<PeriodoConPrecio> periodosConPrecios;
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
	    periodosConPrecios = new ArrayList<PeriodoConPrecio>();
	    politicaDeCancelacion = mock(PoliticaDeCancelacion.class);
		inmueble = new Inmueble(usuario,"casa" ,15, "Argentina", "dsd", "123", servicios1, 5
				, LocalTime.of(10,0),LocalTime.of(2,0) , 200d, metodosDePago,
				periodosConPrecios, politicaDeCancelacion );
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
	void testCalcularPrecioEstadia() {
		
		LocalDate fechaEntrada = LocalDate.of(2024, 12, 12);
        LocalDate fechaSalida = LocalDate.of(2024, 12, 14);
		        
        Double precioCalculado = inmueble.calcularPrecioEstadia(fechaEntrada, fechaSalida);
        
        assertEquals(600d, precioCalculado, 0.01); 
        
		verify(inmueble, times(3)).calcularPrecioDia(any(LocalDate.class));
	}
	
}	
