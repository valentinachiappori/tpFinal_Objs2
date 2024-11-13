package ar.edu.po2.tpFinal;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import ar.edu.unq.po2.tpFinal.Inmueble;
import ar.edu.unq.po2.tpFinal.Ranking;
import ar.edu.unq.po2.tpFinal.Reserva;
import ar.edu.unq.po2.tpFinal.SitioWeb;
import ar.edu.unq.po2.tpFinal.Usuario;

class UsuarioTest {
	
	private Usuario usuario;
	
	private SitioWeb sitio;
	private String nombreCompleto;
	private String correoElectronico;
	private int numeroDeTelefono;
	private List<Reserva> reservas;
	private List<Inmueble> inmuebles;
	private Ranking rankingPropietario;
	private Ranking rankingInquilino;
	private LocalDate fechaDeRegistro;
	private List<String> comentariosInquilino;
	
	private Reserva reserva1;
	private Reserva reserva2;
	
	private Inmueble inmueble1;
	private Inmueble inmueble2;
	
	@BeforeEach
	void setUp() throws Exception {
		
		sitio = mock(SitioWeb.class);
		rankingPropietario = mock(Ranking.class);
		rankingInquilino = mock(Ranking.class);
		fechaDeRegistro = LocalDate.of(2020, 9, 13);
		reserva1 = mock(Reserva.class);
		reserva2 = mock(Reserva.class);
		inmueble1 = mock(Inmueble.class);
		inmueble2 = mock(Inmueble.class);
		reservas = new ArrayList<Reserva>();
		inmuebles = new ArrayList<Inmueble>();
		comentariosInquilino = new ArrayList<String>();
		nombreCompleto = "Juan Perez";
		correoElectronico = "juanperez@gmail.com";
		
		usuario = new Usuario(sitio, nombreCompleto, correoElectronico, 4567);

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

	/*
	@Test
	void testPublicarInmueble() {
	    when(inmueble1.getTipoInmueble()).thenReturn("CASA");
	    when(inmueble1.getPropietario()).thenReturn(usuario);
	    
	    when(sitio.verificarExisteTipoInmueble("CASA")).thenReturn(true);
	    when(sitio.esUsuarioRegistrado(usuario)).thenReturn(true);
	    usuario.publicarInmueble(inmueble1);

	    assertTrue(usuario.getInmuebles().contains(inmueble1));
	}*/


}
