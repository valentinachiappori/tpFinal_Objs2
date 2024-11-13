package ar.edu.po2.tpFinal;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import org.mockito.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import ar.edu.unq.po2.tpFinal.Evento;
import ar.edu.unq.po2.tpFinal.Inmueble;
import ar.edu.unq.po2.tpFinal.MailSender;
import ar.edu.unq.po2.tpFinal.Puntaje;
import ar.edu.unq.po2.tpFinal.Ranking;
import ar.edu.unq.po2.tpFinal.Reserva;
import ar.edu.unq.po2.tpFinal.Servicio;
import ar.edu.unq.po2.tpFinal.SitioWeb;
import ar.edu.unq.po2.tpFinal.Usuario;

class SitioWebTest {

	private SitioWeb sitio;
	private Usuario usuario;
	private Inmueble inmueble;
	private MailSender mails;
	private Set<Servicio> servicios;
	private Reserva reserva;
	
	
	@BeforeEach
	void setUp() throws Exception {
		servicios = new HashSet<Servicio>();
		usuario = mock(Usuario.class);
		inmueble = mock(Inmueble.class);
		mails = mock(MailSender.class);
		sitio = new SitioWeb(servicios,mails);
		reserva = mock(Reserva.class);
	}

	@Test
	void testDarDeAltaTipoDeInmueble() {
		sitio.darDeAltaTipoInmueble("Casa");
		
		assertEquals(1,sitio.getTiposDeInmueble().size());
		assertTrue(sitio.getTiposDeInmueble().contains("Casa"));
	}
	
	@Test
	void testDarDeAltaCategoriaParaPropietario() {
		sitio.darDeAltaCategoriaParaEntidad("Propietario", "Amabilidad");
		
		assertTrue(sitio.getCategoriasPorEntidad().get("Propietario").contains("Amabilidad"));
		assertFalse(sitio.getCategoriasPorEntidad().get("Inquilino").contains("Amabilidad"));
		assertFalse(sitio.getCategoriasPorEntidad().get("Inmueble").contains("Amabilidad"));
	}
	
	@Test
	void testDarDeAltaUsuario() {
		sitio.darDeAltaUsuario(usuario);
		
		assertEquals(1,sitio.getUsuarios().size());
		assertTrue(sitio.getUsuarios().contains(usuario));
		assertTrue(sitio.esUsuarioRegistrado(usuario));
	}
	
	@Test
	void testDarDeAltaServicio() {
		sitio.darDeAltaServicio(Servicio.AireAcondicionado);
		
		assertEquals(1,sitio.getServicios().size());
		assertTrue(sitio.getServicios().contains(Servicio.AireAcondicionado));
	}
	
	
	@Test
	void testDarDeAltaInmueble() {
		sitio.darDeAltaUsuario(usuario);
		sitio.darDeAltaTipoInmueble("Casa");
		
		when(inmueble.getPropietario()).thenReturn(usuario); 
		when(inmueble.getTipoInmueble()).thenReturn("Casa"); 
		
		sitio.darDeAltaInmueble(inmueble);
		
		assertEquals(1,sitio.getInmuebles().size());
		assertTrue(sitio.getInmuebles().contains(inmueble));
	}
	
	@Test
	void testRankearPropietario() {
		Ranking ranking = mock(Ranking.class);
		
		when(reserva.getPropietario()).thenReturn(usuario);
		when(usuario.getRankingPropietario()).thenReturn(ranking);
		when(reserva.getEstadoReserva()).thenReturn("Finalizada");
		
		sitio.darDeAltaCategoriaParaEntidad("Propietario", "Amabilidad");
		
		sitio.rankearPropietario(reserva, "Amabilidad", Puntaje.CUATRO);
		
		verify(ranking, times(1)).agregarPuntaje("Amabilidad", Puntaje.CUATRO);
	}

	@Test
	void testRankearInquilino() {
		Ranking ranking = mock(Ranking.class);
		
		when(reserva.getInquilino()).thenReturn(usuario);
		when(usuario.getRankingInquilino()).thenReturn(ranking);
		when(reserva.getEstadoReserva()).thenReturn("Finalizada");
		
		sitio.darDeAltaCategoriaParaEntidad("Inquilino", "Responsabilidad");
		
		sitio.rankearInquilino(reserva, "Responsabilidad", Puntaje.CUATRO);
		
		verify(ranking, times(1)).agregarPuntaje("Responsabilidad", Puntaje.CUATRO);
	}
	
	@Test
	void testRankearInmueble() {
		Ranking ranking = mock(Ranking.class);
		
		when(reserva.getInmueble()).thenReturn(inmueble);
		when(inmueble.getRankingInmueble()).thenReturn(ranking);
		when(reserva.getEstadoReserva()).thenReturn("Finalizada");
		
		sitio.darDeAltaCategoriaParaEntidad("Inmueble", "Limpieza");
		
		sitio.rankearInmueble(reserva, "Limpieza", Puntaje.TRES);
		
		verify(ranking, times(1)).agregarPuntaje("Limpieza", Puntaje.TRES);
	}
	
}
