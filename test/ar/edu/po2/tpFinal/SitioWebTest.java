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
import ar.edu.unq.po2.tpFinal.Servicio;
import ar.edu.unq.po2.tpFinal.SitioWeb;
import ar.edu.unq.po2.tpFinal.Usuario;

class SitioWebTest {

	private SitioWeb sitio;
	private Usuario usuario;
	private Inmueble inmueble;
	private MailSender mails;
	private Set<Servicio> servicios;
	
	@BeforeEach
	void setUp() throws Exception {
		servicios = new HashSet<Servicio>();
		usuario = mock(Usuario.class);
		inmueble = mock(Inmueble.class);
		mails = mock(MailSender.class);
		sitio = new SitioWeb(servicios,mails);
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
	

}
