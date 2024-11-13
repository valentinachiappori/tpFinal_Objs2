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
        // Configuración de los mocks comunes
        publisherMock = mock(HomePagePublisher.class);
        inmuebleMock = mock(Inmueble.class);
        eventoMock = mock(Evento.class);

        // Crear el SitioObservador con el mock de HomePagePublisher
        observador = new SitioObservador(publisherMock);
    }

    @Test
    public void testUpdate() {
        // Simular el comportamiento del mensaje en el Evento
        String mensajeEsperado = "Mensaje esperado";
        when(eventoMock.aplicarMensaje(inmuebleMock)).thenReturn(mensajeEsperado);

        // Llamar al método update
        observador.update(eventoMock, inmuebleMock);

        // Verificar que se haya llamado al método publish con el mensaje esperado
        verify(publisherMock).publish(mensajeEsperado);
    }
}
