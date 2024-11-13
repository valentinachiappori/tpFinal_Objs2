package ar.edu.po2.tpFinal;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import org.mockito.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import ar.edu.unq.po2.tpFinal.Inmueble;
import ar.edu.unq.po2.tpFinal.MailSender;
import ar.edu.unq.po2.tpFinal.Ranking;
import ar.edu.unq.po2.tpFinal.Reserva;
import ar.edu.unq.po2.tpFinal.SitioWeb;
import ar.edu.unq.po2.tpFinal.Usuario;

class UsuarioTest {
	
	private Usuario usuario;
	
	private SitioWeb sitio;
	private Reserva reserva1;
	private Reserva reserva2;
	private Inmueble inmueble1;
	private Inmueble inmueble2;
	
	@BeforeEach
	void setUp() throws Exception {
		sitio = spy(new SitioWeb(mock(MailSender.class)));
		reserva1 = mock(Reserva.class);
		reserva2 = mock(Reserva.class);
		inmueble1 = mock(Inmueble.class);
		inmueble2 = mock(Inmueble.class);
		
		usuario = new Usuario(sitio, "maria", "correo", 4567);
	}
	
	@Test
	void testAgregarInmueble() {
	    usuario.agregarInmueble(inmueble1);
	    
	    assertEquals(1, usuario.getInmuebles().size());
	    assertTrue(usuario.getInmuebles().contains(inmueble1));
	}

	@Test
	void testGetAntiguedad() {
	    LocalDate fechaDeRegistro = LocalDate.of(2020, 9, 15);
	    usuario.setFechaRegistro(fechaDeRegistro);

	    LocalDate fechaActual = LocalDate.of(2024, 11, 13);

	    Period antiguedad = Period.between(fechaDeRegistro, fechaActual);
	    String esperado = antiguedad.getYears() + " año(s), " + antiguedad.getMonths() + " meses";

	    assertEquals(esperado, usuario.getAntiguedad());
	}

	@Test
	void testPublicarInmueble() {
	    
	    when(inmueble1.getTipoInmueble()).thenReturn("CASA");
	    when(inmueble1.getPropietario()).thenReturn(usuario);

	    doReturn(true).when(sitio).verificarExisteTipoInmueble("CASA");
        doReturn(true).when(sitio).esUsuarioRegistrado(usuario);
	    // Llamar al método que queremos probar
	    usuario.publicarInmueble(inmueble1);

	    // Verificar que se haya llamado al método darDeAltaInmueble
	    verify(sitio, times(1)).darDeAltaInmueble(inmueble1);
	    
	    // Verificar que el inmueble fue agregado correctamente a los inmuebles del sitio
	    assertTrue(sitio.getInmuebles().contains(inmueble1));
	}


}
