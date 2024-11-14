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
import ar.edu.unq.po2.tpFinal.PeriodoConPrecio;
import ar.edu.unq.po2.tpFinal.PoliticaDeCancelacion;
import ar.edu.unq.po2.tpFinal.Puntaje;
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
    void testSetAndGetNombreCompleto() {
        usuario.setNombreCompleto("pedro");
        
        assertEquals("pedro", usuario.getNombreCompleto());
    }
	
	@Test
    void testSetCorreoElectronico() {
        usuario.setCorreoElectronico("nuevoCorreo");
        
        assertEquals("nuevoCorreo", usuario.getCorreoElectronico());
    }
	
	@Test
    void testSetAndGetNumeroDeTelefono() {
        usuario.setNumeroDeTelefono(12345678);
        
        assertEquals(12345678, usuario.getNumeroDeTelefono());
    }
	
	@Test
	void testGetRankingPropietario() {
		assertTrue(usuario.getRankingPropietario() instanceof Ranking);
	}
	
	@Test
	void testGetRankingInquilino() {
		assertTrue(usuario.getRankingInquilino() instanceof Ranking);
	}
	
	@Test
	void getFechaDeRegistro() {
		assertTrue(usuario.getFechaDeRegistro() instanceof LocalDate);
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

        usuario.publicarInmueble(inmueble1);
	    
	    assertTrue(sitio.getInmuebles().contains(inmueble1));
	    
	    verify(sitio, times(1)).darDeAltaInmueble(inmueble1);
	}
	/*
	@Test
	void testBuscarInmuebles() {
		FiltroCompuesto filtro = mock(FiltroCompuesto.class);
		when(sitio.filtrarInmuebles(filtro)).thenReturn(List.of(inmueble1, inmueble2));
		when(filtro.cumple(inmueble1)).thenReturn(true);
	    when(filtro.cumple(inmueble2)).thenReturn(false);
	    
		List<Inmueble> resultado = usuario.buscarInmuebles(filtro);

	    assertEquals(2, resultado.size());
	    assertTrue(resultado.contains(inmueble1));
	    assertTrue(resultado.contains(inmueble2));
	}*/
	
	@Test
    void testRankearPropietario() {
		when(reserva1.getEstadoReserva()).thenReturn("Finalizada");
        usuario.rankearPropietario(reserva1, "Amabilidad", Puntaje.CUATRO);
        
        verify(sitio, times(1)).rankearPropietario(reserva1, "Amabilidad", Puntaje.CUATRO);
    }
	
	@Test
    void testRankearInquilino() {
		when(reserva1.getEstadoReserva()).thenReturn("Finalizada");
        usuario.rankearInquilino(reserva1, "Responsabilidad", Puntaje.TRES);
        
        verify(sitio, times(1)).rankearInquilino(reserva1, "Responsabilidad", Puntaje.TRES);
    }

	@Test
    void testRankearInmueble() {
		when(reserva1.getEstadoReserva()).thenReturn("Finalizada");
        usuario.rankearInmueble(reserva1, "Limpieza", Puntaje.CINCO);
        
        verify(sitio, times(1)).rankearInmueble(reserva1, "Limpieza", Puntaje.CINCO);
    }
	
	@Test
	void testReservar() {
		usuario.reservar(inmueble1, "Transferencia", LocalDate.of(2024, 11, 15), LocalDate.of(2024, 11, 20));
		verify(sitio, times(1)).reservar(any(Reserva.class));
	}
	
	@Test 
	void testAceptarReserva(){
		when(reserva1.getInmueble()).thenReturn(inmueble1);
		when(reserva1.getInquilino()).thenReturn(usuario);
		
		usuario.aceptarUnaReserva(reserva1);
    
		assertTrue(usuario.getMisReservas().contains(reserva1));
		verify(sitio, times(1)).consolidarReserva(reserva1);
	}
	
	@Test
	void testReservasFuturas() {
		when(reserva1.getFechaEntrada()).thenReturn(LocalDate.of(2024, 12, 1)); 
	    when(reserva2.getFechaEntrada()).thenReturn(LocalDate.of(2024, 10, 1));
	    
	    usuario.registrarReserva(reserva1);
	    usuario.registrarReserva(reserva2);
	    
	    List<Reserva> reservasFuturas = usuario.getReservasFuturas();
	    
	    assertEquals(1, reservasFuturas.size());  
	    assertTrue(reservasFuturas.contains(reserva1));  
	    assertFalse(reservasFuturas.contains(reserva2));
	}
	
	@Test
	void testRechazarReserva() {
		when(reserva1.getInmueble()).thenReturn(inmueble1);
		usuario.rechazarReserva(reserva1);
	    
	    verify(sitio, times(1)).rechazarReserva(reserva1);
	}
	
	@Test
	void testReservasEnCiudad() {
		when(inmueble1.getCiudad()).thenReturn("Buenos Aires");
	    when(inmueble2.getCiudad()).thenReturn("Córdoba");

	    when(reserva1.getInmueble()).thenReturn(inmueble1);
	    when(reserva2.getInmueble()).thenReturn(inmueble2);
	    
	    usuario.registrarReserva(reserva1);
	    usuario.registrarReserva(reserva2);

	    List<Reserva> reservasEnBuenosAires = usuario.getReservasEnCiudad("Buenos Aires");

	    assertEquals(1, reservasEnBuenosAires.size());
	    assertTrue(reservasEnBuenosAires.contains(reserva1));
	    assertFalse(reservasEnBuenosAires.contains(reserva2));
	}
	
	@Test
    void testAgregarComentario() {
        usuario.agregarComentario("Excelente inquilino");

        assertTrue(usuario.getComentariosInquilino().contains("Excelente inquilino"));
    }
	
	@Test
    void testComentarInmueble() {
		when(reserva1.getEstadoReserva()).thenReturn("Finalizada");
		when(reserva1.getInmueble()).thenReturn(inmueble1);
        usuario.comentarInmueble(reserva1, "Hermoso departamento");

        verify(sitio, times(1)).registrarComentarioInmueble(reserva1, "Hermoso departamento");
    }
	
	@Test
    void testComentarInquilino() {
		when(reserva1.getEstadoReserva()).thenReturn("Finalizada");
		when(reserva1.getInquilino()).thenReturn(usuario);
        usuario.comentarInquilino(reserva1, "Excelente inquilino");

        verify(sitio, times(1)).registrarComentarioInquilino(reserva1, "Excelente inquilino");
    }
	
	@Test
    void testGetCiudadesConReserva() {
		when(reserva1.getInmueble()).thenReturn(inmueble1);
        when(reserva2.getInmueble()).thenReturn(inmueble2);
        when(inmueble1.getCiudad()).thenReturn("Buenos Aires");
        when(inmueble2.getCiudad()).thenReturn("Córdoba");
        
        usuario.registrarReserva(reserva1);
        usuario.registrarReserva(reserva2);
        
        List<String> ciudades = usuario.getCiudadesConReserva();

        assertEquals(2, ciudades.size());
        assertTrue(ciudades.contains("Buenos Aires"));
        assertTrue(ciudades.contains("Córdoba"));
    }
	
	@Test
    void testCancelarReserva() {
		PoliticaDeCancelacion politica = mock(PoliticaDeCancelacion.class);
		
		when(reserva1.getInmueble()).thenReturn(inmueble1);
		when(reserva1.getInquilino()).thenReturn(usuario);
		when(reserva1.getPropietario()).thenReturn(usuario);
		when(inmueble1.getPoliticaDeCancelacion()).thenReturn(politica);
		
        usuario.cancelarReserva(reserva1);
        
        verify(sitio, times(1)).cancelarReserva(reserva1);
	}

	@Test
	void testEliminarReserva() {
		usuario.registrarReserva(reserva1);
	
	    usuario.eliminarReserva(reserva1);
	
	    assertFalse(usuario.getMisReservas().contains(reserva1));
	}
	
	@Test
	void testCantVecesQueFueAlquiladoElInmueble() {
		when(reserva1.getEstadoReserva()).thenReturn("Finalizada");
	    when(reserva2.getEstadoReserva()).thenReturn("Pendiente");
	    when(inmueble1.getReservasConfirmadas()).thenReturn(List.of(reserva1, reserva2));
	    
	    int cantidad = usuario.cantVecesQueFueAlquiladoElInmueble(inmueble1);
	
	    assertEquals(1, cantidad);
	}
	
	@Test
	void testCantVecesQueAlquilo() {
		when(reserva1.getEstadoReserva()).thenReturn("Finalizada");
        when(reserva2.getEstadoReserva()).thenReturn("Finalizada");
        when(inmueble1.getReservasConfirmadas()).thenReturn(List.of(reserva1));
        when(inmueble2.getReservasConfirmadas()).thenReturn(List.of(reserva2));
        usuario.agregarInmueble(inmueble1);
        usuario.agregarInmueble(inmueble2);

		int cantidadTotal = usuario.cantVecesQueAlquilo();

        assertEquals(2, cantidadTotal);
    }
	
	@Test
    void testTodosLosInmueblesQueYaFueronAlquilados() {
		when(reserva1.getEstadoReserva()).thenReturn("Finalizada");
        when(reserva2.getEstadoReserva()).thenReturn("Finalizada");
        when(inmueble1.getReservasConfirmadas()).thenReturn(List.of(reserva1));
        when(inmueble2.getReservasConfirmadas()).thenReturn(List.of(reserva2));
        usuario.agregarInmueble(inmueble1);
        usuario.agregarInmueble(inmueble2);
		
        List<Inmueble> inmueblesAlquilados = usuario.todosLosInmueblesQueYaFueronAlquilados();

        assertEquals(2, inmueblesAlquilados.size());
        assertTrue(inmueblesAlquilados.contains(inmueble1));
        assertTrue(inmueblesAlquilados.contains(inmueble2));
    }
	
	@Test
    void testModificarPrecioPeriodo() {
		PeriodoConPrecio periodo = mock(PeriodoConPrecio.class);
        usuario.modificarPrecioPeriodo(1500.0, inmueble1, periodo);

        verify(inmueble1).modificarPrecioPeriodo(periodo, 1500.0);
    }
	
	@Test
	void testModificarPrecioBase() {
        usuario.modificarPrecioBase(2000.0, inmueble1);

        verify(inmueble1).modificarPrecioBase(2000.0);
    }
	
	@Test
    void testHacerCheckOut() {
		when(reserva1.getFechaSalida()).thenReturn(LocalDate.of(2024, 11, 13));
        usuario.hacerCheckOut(reserva1);

        verify(sitio).registrarCheckOut(reserva1, LocalDate.now());
    }
	
	@Test
	void testBuscarInmueble() {
	    // Configuración de los mocks para el inmueble
	    when(inmueble1.getCiudad()).thenReturn("Ciudad");
	    when(inmueble1.estaDisponibleEnPeriodo(any(), any())).thenReturn(true);
	    when(inmueble1.calcularPrecioEstadia(any(), any())).thenReturn(500.0);

	    // Datos de entrada
	    LocalDate fechaEntrada = LocalDate.of(2024, 11, 20);
	    LocalDate fechaSalida = LocalDate.of(2024, 11, 23);

	    // Usamos eq() para los valores específicos y any() para los genéricos
	    when(sitio.filtrarInmuebles(eq("Ciudad"), eq(fechaEntrada), eq(fechaSalida), anyInt(), anyDouble(), anyDouble()))
	        .thenReturn(List.of(inmueble1));

	    // Llamada al método que estamos testeando
	    List<Inmueble> resultado = usuario.buscarInmuebles("Ciudad", fechaEntrada, fechaSalida, null, null, null);
	    
	    // Verificaciones
	    assertEquals(1, resultado.size());
	    assertTrue(resultado.contains(inmueble1)); 
	}
}
