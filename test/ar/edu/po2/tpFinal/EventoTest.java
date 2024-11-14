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
    private String mensajeEsperado;
    private String mensajeAplicado;

    @BeforeEach
    public void setUp() {
        inmuebleMock = mock(Inmueble.class);

        evento = Evento.CANCELACION;
    }

    @Test
    public void testAplicarMensajeCancelacion() {
        when(inmuebleMock.getTipoInmueble()).thenReturn("Departamento");
        when(inmuebleMock.calcularPrecioDia(LocalDate.now())).thenReturn(100.0);

        mensajeEsperado = "un inmueble Departamento a tan sólo $100.0";
        mensajeAplicado = evento.aplicarMensaje(inmuebleMock);

        assertEquals(mensajeEsperado, mensajeAplicado);
    }
    
    @Test
    public void testAplicarMensajeBajaPrecio() {
        when(inmuebleMock.getTipoInmueble()).thenReturn("Departamento");
        when(inmuebleMock.calcularPrecioDia(LocalDate.now())).thenReturn(100.0); 
        
        evento = Evento.BAJAPRECIO;
        mensajeEsperado = "El/la Departamento que te interesa se ha liberado! Corre a reservarlo!";
        mensajeAplicado = evento.aplicarMensaje(inmuebleMock);

        assertEquals(mensajeEsperado, mensajeAplicado);
    }
    
    @Test
    public void testAplicarMensajeReserva() {
        when(inmuebleMock.getTipoInmueble()).thenReturn("Departamento");
        when(inmuebleMock.calcularPrecioDia(LocalDate.now())).thenReturn(150.0);
        
        evento = Evento.RESERVA;
        mensajeEsperado = "un inmueble Departamento el inmueble fue reservado";
        mensajeAplicado = evento.aplicarMensaje(inmuebleMock);

        assertEquals(mensajeEsperado, mensajeAplicado);
    }
}