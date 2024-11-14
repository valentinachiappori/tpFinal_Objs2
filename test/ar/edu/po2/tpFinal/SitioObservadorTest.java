package ar.edu.po2.tpFinal;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import ar.edu.unq.po2.tpFinal.Evento;
import ar.edu.unq.po2.tpFinal.HomePagePublisher;
import ar.edu.unq.po2.tpFinal.Inmueble;
import ar.edu.unq.po2.tpFinal.SitioObservador;

public class SitioObservadorTest {

    private HomePagePublisher publisherMock;
    private Inmueble inmuebleMock;
    private Evento eventoMock;
    private SitioObservador observador;

    @BeforeEach
    public void setUp() {
        publisherMock = mock(HomePagePublisher.class);
        inmuebleMock = mock(Inmueble.class);
        eventoMock = mock(Evento.class);

        observador = new SitioObservador(publisherMock);
    }

    @Test
    public void testUpdate() {
        String mensajeEsperado = "Mensaje esperado";
        when(eventoMock.aplicarMensaje(inmuebleMock)).thenReturn(mensajeEsperado);

        observador.update(eventoMock, inmuebleMock);

        verify(publisherMock).publish(mensajeEsperado);
    }
}
