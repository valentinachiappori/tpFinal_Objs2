package ar.edu.po2.tpFinal;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import ar.edu.unq.po2.tpFinal.Evento;
import ar.edu.unq.po2.tpFinal.Inmueble;
import ar.edu.unq.po2.tpFinal.MailSender;
import ar.edu.unq.po2.tpFinal.PoliticaDeCancelacion;
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
	private Reserva reserva;
	
	
	@BeforeEach
	void setUp() throws Exception {
		usuario = mock(Usuario.class);
		inmueble = mock(Inmueble.class);
		mails = mock(MailSender.class);
		sitio = new SitioWeb(mails);
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
	
	@Test
	void registrarComentarioInmueble() {
		when(reserva.getEstadoReserva()).thenReturn("Finalizada");
		when(reserva.getInmueble()).thenReturn(inmueble);
		
		sitio.registrarComentarioInmueble(reserva, "buenisimo");
		
		verify(inmueble, times(1)).agregarComentario("buenisimo");
	}
	
	@Test
	void registrarComentarioInquilino() {
		when(reserva.getEstadoReserva()).thenReturn("Finalizada");
		when(reserva.getInquilino()).thenReturn(usuario);
		
		sitio.registrarComentarioInquilino(reserva, "buenisimo");
		
		verify(usuario, times(1)).agregarComentario("buenisimo");
	}
	
	@Test
	void testReservar() {
		when(reserva.getInmueble()).thenReturn(inmueble);
		when(reserva.getPropietario()).thenReturn(usuario);
		when(reserva.getInquilino()).thenReturn(usuario);
		
		sitio.reservar(reserva);
		
		verify(inmueble,times(1)).recibirReserva(reserva);
	}
	
	@Test
	void testConsolidarReserva() {
		when(reserva.getInmueble()).thenReturn(inmueble);  
	    when(reserva.getInquilino()).thenReturn(usuario);  
	    
	    sitio.consolidarReserva(reserva);
	    
	    verify(reserva, times(1)).cambiarEstadoAAceptada();  
	    verify(inmueble, times(1)).registrarReserva(reserva); 
	    verify(usuario, times(1)).registrarReserva(reserva); 
	    verify(inmueble, times(1)).notificar(Evento.RESERVA, inmueble);
	}
	
	@Test
	void testEnviarMailConfirmacion() {
		when(reserva.getInquilino()).thenReturn(usuario);
		when(usuario.getCorreoElectronico()).thenReturn("email");
		when(reserva.toString()).thenReturn("Detalles de la reserva");
		
		sitio.enviarMailConfirmacion(reserva);

		verify(mails,times(1)).sendMail("email", "Reserva confirmada", "Tu reservaDetalles de la reservaha sido confirmada por su propietario");
	}
	
	@Test
	void testRechazarReserva() {
		when(reserva.getInmueble()).thenReturn(inmueble);
		
		sitio.rechazarReserva(reserva);
		
		verify(inmueble, times(1)).eliminarReservaPendiente(reserva);
	}
	
	@Test
	void testCancelarReserva() {
		PoliticaDeCancelacion pcancelacion = mock(PoliticaDeCancelacion.class);
		
		when(reserva.getInmueble()).thenReturn(inmueble);
	    when(reserva.getInquilino()).thenReturn(usuario);
	    when(reserva.getPropietario()).thenReturn(usuario);
	    when(usuario.getCorreoElectronico()).thenReturn("correo");
	    when(inmueble.getPoliticaDeCancelacion()).thenReturn(pcancelacion);
	    when(reserva.toString()).thenReturn("Detalles de la reserva");
	    
	    sitio.cancelarReserva(reserva);
	    
	    verify(inmueble, times(1)).eliminarReserva(reserva);
	    verify(usuario, times(1)).eliminarReserva(reserva);
		verify(mails,times(1)).sendMail("correo", "Reserva cancelada", "Tu reserva Detalles de la reservaha sido cancelada por el inquilino");
	    verify(pcancelacion, times(1)).ejecutar(reserva);
	    verify(inmueble, times(1)).notificar(Evento.CANCELACION, inmueble);
	}
	
	@Test
	void testTopTen() {
		Usuario usuario2 = mock(Usuario.class);
		Usuario usuario3 = mock(Usuario.class);
		Usuario usuario4 = mock(Usuario.class);

		List<Reserva> reservas1 = Arrays.asList(mock(Reserva.class), mock(Reserva.class), mock(Reserva.class));
		List<Reserva> reservas2 = Arrays.asList(mock(Reserva.class), mock(Reserva.class));
		List<Reserva> reservas3 = Arrays.asList(mock(Reserva.class), mock(Reserva.class), mock(Reserva.class), mock(Reserva.class));

		when(usuario.getMisReservas()).thenReturn(Collections.emptyList());
		when(usuario2.getMisReservas()).thenReturn(reservas1);
		when(usuario3.getMisReservas()).thenReturn(reservas2);
		when(usuario4.getMisReservas()).thenReturn(reservas3);

		sitio.getUsuarios().addAll(Arrays.asList(usuario, usuario4, usuario2, usuario3));

		List<Usuario> topTenInquilinos = sitio.topTenInquilinosConMasAlquileres();

		assertTrue(topTenInquilinos.size() <= 10);
		assertEquals(usuario4, topTenInquilinos.get(0)); 
		assertEquals(usuario2, topTenInquilinos.get(1)); 
		assertEquals(usuario3, topTenInquilinos.get(2));
		assertEquals(usuario, topTenInquilinos.get(3));
		    
		verify(usuario, atLeastOnce()).getMisReservas();
		verify(usuario2, atLeastOnce()).getMisReservas();
		verify(usuario3, atLeastOnce()).getMisReservas();
		verify(usuario4, atLeastOnce()).getMisReservas();
	}
	
	@Test
	void testInmueblesDisponibles() {
		Inmueble inmuebleNoDisponible = mock(Inmueble.class);
		
		when(inmueble.getPropietario()).thenReturn(usuario); 
		when(inmueble.getTipoInmueble()).thenReturn("Casa");
		when(inmuebleNoDisponible.getPropietario()).thenReturn(usuario); 
		when(inmuebleNoDisponible.getTipoInmueble()).thenReturn("Casa");
		when(inmueble.estaDisponibleHoy()).thenReturn(true);
		when(inmuebleNoDisponible.estaDisponibleHoy()).thenReturn(false);

		sitio.darDeAltaUsuario(usuario);
		sitio.darDeAltaTipoInmueble("Casa");
		sitio.darDeAltaInmueble(inmuebleNoDisponible);
		sitio.darDeAltaInmueble(inmueble);

		List<Inmueble> inmueblesLibres = sitio.inmueblesLibres();

		assertEquals(1, inmueblesLibres.size());
		assertTrue(inmueblesLibres.contains(inmueble));
		assertFalse(inmueblesLibres.contains(inmuebleNoDisponible));
	}
	
	@Test
	void testTasaDeOcupacionSinInmuebles() {
        assertEquals(0.0, sitio.tasaDeOcupacion(), 0.01);
    }
	
	@Test
    void testTasaDeOcupacionAlgunosAlquilados() {
		Inmueble inmuebleNoDisponible = mock(Inmueble.class);
		
		when(inmueble.getPropietario()).thenReturn(usuario); 
		when(inmueble.getTipoInmueble()).thenReturn("Casa");
		when(inmuebleNoDisponible.getPropietario()).thenReturn(usuario); 
		when(inmuebleNoDisponible.getTipoInmueble()).thenReturn("Casa");
        
        sitio.darDeAltaUsuario(usuario);
		sitio.darDeAltaTipoInmueble("Casa");
		sitio.darDeAltaInmueble(inmuebleNoDisponible);
		sitio.darDeAltaInmueble(inmueble);
		
		when(inmueble.estaDisponibleHoy()).thenReturn(true);
        when(inmuebleNoDisponible.estaDisponibleHoy()).thenReturn(false);
        
        assertEquals(0.5, sitio.tasaDeOcupacion(), 0.01);
    }
	
	@Test
    void testEjecutarReservaCondicionalConReservasEnCola() {
		Reserva reservaEnCola = mock(Reserva.class);
		
        ArrayList<Reserva> reservasEnCola = new ArrayList<Reserva>();
        reservasEnCola.add(reservaEnCola);
        
        when(reserva.getInmueble()).thenReturn(inmueble);
        when(inmueble.getReservasEnCola()).thenReturn(reservasEnCola);
        
        sitio.ejecutarReservaCondicional(reserva);

        verify(inmueble, times(1)).recibirReserva(reservaEnCola);
        assertTrue(reservasEnCola.isEmpty());
	}
	
	@Test
	void testEjecutarReservaCondicionalSinReservasEnCola() {
        when(reserva.getInmueble()).thenReturn(inmueble);
		when(inmueble.getReservasEnCola()).thenReturn(Collections.emptyList());
		 
        sitio.ejecutarReservaCondicional(reserva);

        verify(inmueble, never()).recibirReserva(any(Reserva.class));
    }
	
	 @Test
	 void testRegistrarCheckOutReservaFinalizada() {
	    LocalDate fechaSalida = LocalDate.of(2024, 11, 10);
	    LocalDate fechaCheckOut = LocalDate.of(2024, 11, 15);

	    when(reserva.getFechaSalida()).thenReturn(fechaSalida);

	    sitio.registrarCheckOut(reserva, fechaCheckOut);

	    verify(reserva, times(1)).cambiarEstadoAFinalizada();
	 }
	 
	 @Test
	 void testRegistrarCheckOutReservaNoFinalizadaLanzaExcepcion() {
	    LocalDate fechaSalida = LocalDate.of(2024, 11, 15);
	    LocalDate fechaCheckOut = LocalDate.of(2024, 11, 10);

	    when(reserva.getFechaSalida()).thenReturn(fechaSalida);
	    
	    assertThrows(IllegalArgumentException.class, () -> {
	            sitio.registrarCheckOut(reserva, fechaCheckOut);
	            });
	    
	    verify(reserva, never()).cambiarEstadoAFinalizada();
	 }
	 
	 @Test
	 void testEnviarMailConReserva() {
		 Usuario inquilino = mock(Usuario.class);
		 when(reserva.getInquilino()).thenReturn(inquilino);
	     when(inquilino.getCorreoElectronico()).thenReturn("inquilino@gmail.com");
	     when(reserva.toString()).thenReturn("Detalles de la reserva");
	        
	     sitio.enviarMailConReserva(reserva);
	     verify(mails).sendMail("inquilino@gmail.com", "Reserva", "Detalles de la reserva");
	 }
	 
	 @Test
	 void testSetCategoriasPorEntidad() {
	     Map<String, Set<String>> categorias = new HashMap<>();
	     Set<String> categoriasPropietario = new HashSet<>(Arrays.asList("Responsable", "Confiable"));
	     categorias.put("Propietario", categoriasPropietario);

	     sitio.setCategoriasPorEntidad(categorias);

	     assertEquals(categoriasPropietario, sitio.getCategoriasPorEntidad("Propietario"));
	 }


    @Test
    void testSetUsuarios() {
        List<Usuario> usuarios = new ArrayList<>();
        usuarios.add(usuario);

        sitio.setUsuarios(usuarios);

        assertEquals(usuarios, sitio.getUsuarios());
    }

    @Test
    void testSetInmuebles() {
        List<Inmueble> inmuebles = new ArrayList<>();
        inmuebles.add(inmueble);

        sitio.setInmuebles(inmuebles);

        assertEquals(inmuebles, sitio.getInmuebles());
    }

    @Test
    void testSetTiposDeInmueble() {
    	List<String> tiposDeInmueble = Arrays.asList("Casa", "Departamento");

    	sitio.setTiposDeInmueble(tiposDeInmueble);

    	assertEquals(tiposDeInmueble, sitio.getTiposDeInmueble());
    }

    @Test
    void testSetServicios() {
    	Set<Servicio> servicios = new HashSet<>();
    	Servicio servicioMock = mock(Servicio.class);
    	servicios.add(servicioMock);

	    sitio.setServicios(servicios);
	    
	    assertEquals(servicios, sitio.getServicios());
	 }

	 @Test
	 void testSetMailSender() {
		 MailSender nuevoMailSender = mock(MailSender.class);

	     sitio.setMailSender(nuevoMailSender);

	     assertEquals(nuevoMailSender, sitio.getMailSender());
	    }

	 @Test
	 void testGetCategoriasPorEntidad() {
		  Map<String, Set<String>> categorias = new HashMap<>();
	      Set<String> categoriasPropietario = new HashSet<>(Arrays.asList("Responsable", "Confiable"));
	      categorias.put("Propietario", categoriasPropietario);
	      sitio.setCategoriasPorEntidad(categorias);
	      
	      Set<String> result = sitio.getCategoriasPorEntidad("Propietario");

	      assertEquals(categoriasPropietario, result);
	 }
	 
	 @Test
	 void testFiltrarInmueblesConTodosLosParametros() {

		  when(inmueble.getCiudad()).thenReturn("Ciudad");
	      when(inmueble.estaDisponibleEnPeriodo(any(), any())).thenReturn(true);
	      when(inmueble.getCapacidad()).thenReturn(4);
	      when(inmueble.calcularPrecioEstadia(any(), any())).thenReturn(500.0);
	      
	      sitio.getInmuebles().add(inmueble);
	      
	      LocalDate fechaEntrada = LocalDate.now().plusDays(1);
	      LocalDate fechaSalida = LocalDate.now().plusDays(3);

	      List<Inmueble> resultado = sitio.filtrarInmuebles("Ciudad", fechaEntrada, fechaSalida, 3, 300.0, 600.0);
	        
	      assertEquals(1, resultado.size());
	      assertTrue(resultado.contains(inmueble));
	}
	 
	 @Test
	 void testFiltrarInmueblesConParametrosObligatoriosNulos() {
	        
	      assertThrows(IllegalArgumentException.class, () -> {
	            sitio.filtrarInmuebles(null, LocalDate.now(), LocalDate.now().plusDays(1), null, null, null);
	      });
	        
	      assertThrows(IllegalArgumentException.class, () -> {
	            sitio.filtrarInmuebles("Ciudad", null, LocalDate.now().plusDays(1), null, null, null);
	      });
	        
	      assertThrows(IllegalArgumentException.class, () -> {
	            sitio.filtrarInmuebles("Ciudad", LocalDate.now(), null, null, null, null);
	      });
	 }
	 
	 @Test
	 void testFiltrarInmueblesConParametrosOpcionalesNulos() {
	     when(inmueble.getCiudad()).thenReturn("Ciudad");
	     when(inmueble.estaDisponibleEnPeriodo(any(), any())).thenReturn(true);
	     
	     sitio.getInmuebles().add(inmueble);

	     LocalDate fechaEntrada = LocalDate.now().plusDays(1);
	     LocalDate fechaSalida = LocalDate.now().plusDays(3);

	     List<Inmueble> resultado = sitio.filtrarInmuebles("Ciudad", fechaEntrada, fechaSalida, null, null, null);
	        
	     assertEquals(1, resultado.size());
	     assertTrue(resultado.contains(inmueble));
	 }
}
