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

class SinCancelacionTest {

	private SinCancelacion sinCancelacion;
    private Reserva reserva;
    private ByteArrayOutputStream outputStream;

    @BeforeEach
    void setUp() {
        reserva = mock(Reserva.class);
        sinCancelacion = new SinCancelacion();
        
        outputStream = mock(ByteArrayOutputStream.class);
        System.setOut(new PrintStream(outputStream));
    }

    @Test
    void testEjecutar() {
        double precioEstadia = 100.0;
        when(reserva.calcularPrecioEstadia()).thenReturn(precioEstadia);
        
        sinCancelacion.ejecutar(mockReserva);
        
        // Verificar que el mensaje esperado ha sido impreso
        String expectedOutput = "Cancelación con cargo. Cargo aplicado: $" + precioEstadia + "\n";
        assertEquals(expectedOutput, outputStream.toString());
        
        // Verificar que el método calcularPrecioEstadia fue llamado
        verify(mockReserva).calcularPrecioEstadia();
    }

}
