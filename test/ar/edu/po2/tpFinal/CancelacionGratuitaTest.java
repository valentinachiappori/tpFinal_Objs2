package ar.edu.po2.tpFinal;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.time.LocalDate;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import ar.edu.unq.po2.tpFinal.CancelacionGratuita;
import ar.edu.unq.po2.tpFinal.Inmueble;
import ar.edu.unq.po2.tpFinal.Reserva;

public class CancelacionGratuitaTest {

    private Reserva reservaMock;
    private Inmueble inmuebleMock;
    private CancelacionGratuita politicaCancelacion;
    private ByteArrayOutputStream outputStream;

    @BeforeEach
    public void setUp() {
        reservaMock = mock(Reserva.class);
        inmuebleMock = mock(Inmueble.class);
        politicaCancelacion = new CancelacionGratuita();
        
        outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));
    }

    @Test
    public void testCancelacionGratuitaAntesDeFechaLimite() {
        LocalDate fechaEntrada = LocalDate.now().plusDays(15);
        when(reservaMock.getFechaEntrada()).thenReturn(fechaEntrada);

        politicaCancelacion.ejecutar(reservaMock);

        String output = outputStream.toString().trim();
        assertEquals("Cancelación gratuita.", output);
    }

    @Test
    public void testCancelacionConCargoDespuesDeFechaLimite() {
        LocalDate fechaEntrada = LocalDate.now().plusDays(5);
        when(reservaMock.getFechaEntrada()).thenReturn(fechaEntrada);

        when(reservaMock.getInmueble()).thenReturn(inmuebleMock);
        when(inmuebleMock.calcularPrecioDia(fechaEntrada)).thenReturn(100.0);

        politicaCancelacion.ejecutar(reservaMock);

        String output = outputStream.toString().trim();
        assertEquals("Cancelación con cargo. Cargo aplicado: $200.0", output);
    }
}