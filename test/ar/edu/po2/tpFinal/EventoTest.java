package ar.edu.po2.tpFinal;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import ar.edu.unq.po2.tpFinal.Evento;
import ar.edu.unq.po2.tpFinal.Inmueble;

import static org.mockito.Mockito.*;

import java.time.LocalDate;
import java.util.function.Function;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class EventoTest {

    private Inmueble inmuebleMock;
    private Evento evento;

    @BeforeEach
    public void setUp() {
        inmuebleMock = mock(Inmueble.class);

        evento = Evento.CANCELACION;
    }

    @Test
    public void testAplicarMensajeCancelacion() {
        // Simular el comportamiento del mock de inmueble para el precio
        when(inmuebleMock.getTipoInmueble()).thenReturn("Apartamento");
        when(inmuebleMock.calcularPrecioDia(LocalDate.now())).thenReturn(100.0);
        // Aplicar el mensaje para el evento CANCELACION
        String mensajeEsperado = "un inmueble Apartamento a tan sólo $100.0";
        String mensajeAplicado = evento.aplicarMensaje(inmuebleMock);

        // Verificar que el mensaje aplicado sea el esperado
        assertEquals(mensajeEsperado, mensajeAplicado);
    }
    
    @Test
    public void testAplicarMensajeBajaPrecio() {
        // Simular el comportamiento del mock de inmueble para obtener el tipo de inmueble y el precio
        when(inmuebleMock.getTipoInmueble()).thenReturn("Apartamento");
        when(inmuebleMock.calcularPrecioDia(LocalDate.now())).thenReturn(100.0);  // Simular un precio distinto de 0.0
        
        evento = Evento.BAJAPRECIO;
        // Aplicar el mensaje para el evento BAJAPRECIO
        String mensajeEsperado = "El/la Apartamento que te interesa se ha liberado! Corre a reservarlo!";
        String mensajeAplicado = evento.aplicarMensaje(inmuebleMock);

        // Verificar que el mensaje aplicado sea el esperado
        assertEquals(mensajeEsperado, mensajeAplicado);
    }
    
    @Test
    public void testAplicarMensajeReserva() {
        // Simular el comportamiento del mock de inmueble para el precio
        when(inmuebleMock.getTipoInmueble()).thenReturn("Apartamento");
        when(inmuebleMock.calcularPrecioDia(LocalDate.now())).thenReturn(150.0);
        
        evento = Evento.RESERVA;
        // Aplicar el mensaje para el evento RESERVA
        String mensajeEsperado = "un inmueble Apartamento el inmueble fue reservado";
        String mensajeAplicado = evento.aplicarMensaje(inmuebleMock);

        // Verificar que el mensaje aplicado sea el esperado
        assertEquals(mensajeEsperado, mensajeAplicado);
    }
}