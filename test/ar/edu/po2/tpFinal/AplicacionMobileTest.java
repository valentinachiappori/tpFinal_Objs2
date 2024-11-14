package ar.edu.po2.tpFinal;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import org.mockito.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import ar.edu.unq.po2.tpFinal.AplicacionMobile;
import ar.edu.unq.po2.tpFinal.Evento;
import ar.edu.unq.po2.tpFinal.Inmueble;
import ar.edu.unq.po2.tpFinal.PopUpWindow;

class AplicacionMobileTest {
	private AplicacionMobile aplicacionMobile;
	private PopUpWindow mockPopUpWindow;
	private Evento mockEvento;
	private Inmueble mockInmueble;

	@BeforeEach
	void setUp() {
	    mockPopUpWindow = mock(PopUpWindow.class);
	    aplicacionMobile = new AplicacionMobile(mockPopUpWindow);
	    mockEvento = mock(Evento.class);
	    mockInmueble = mock(Inmueble.class);
}

	@Test
	void testUpdate() {
	    when(mockEvento.aplicarMensaje(mockInmueble)).thenReturn("Mensaje");
	
	    aplicacionMobile.update(mockEvento, mockInmueble);
	
	    verify(mockPopUpWindow).popUp("Mensaje", "BLUE", 12);
	}
}
