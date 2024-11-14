package ar.edu.po2.tpFinal;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import org.mockito.*;

import ar.edu.unq.po2.tpFinal.Reserva;
import ar.edu.unq.po2.tpFinal.SinCancelacion;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

class SinCancelacionTest {

    private SinCancelacion sinCancelacion;
    private Reserva reservaMock;

    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();

    @BeforeEach
    void setUp() {
        reservaMock = mock(Reserva.class);
        sinCancelacion = new SinCancelacion();
        System.setOut(new PrintStream(outContent));
    }

    @Test
    void testEjecutar() {
        when(reservaMock.calcularPrecioEstadia()).thenReturn(100.0);

        sinCancelacion.ejecutar(reservaMock);

        verify(reservaMock).calcularPrecioEstadia();
        assertTrue(outContent.toString().contains("Cancelación con cargo. Cargo aplicado: $100.0"));
    }
}
