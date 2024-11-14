package ar.edu.po2.tpFinal;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.time.LocalDate;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import ar.edu.unq.po2.tpFinal.CancelacionIntermedia;
import ar.edu.unq.po2.tpFinal.Reserva;

public class CancelacionIntermediaTest {

    private Reserva reservaMock;
    private CancelacionIntermedia politicaCancelacion;
    private ByteArrayOutputStream outputStream;

    @BeforeEach
    public void setUp() {
        reservaMock = mock(Reserva.class);
        politicaCancelacion = new CancelacionIntermedia();
        
        outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));
    }

    @Test
    public void testCancelacionSinCargoAntesDe19Dias() {
        LocalDate fechaEntrada = LocalDate.now().plusDays(20);
        when(reservaMock.getFechaEntrada()).thenReturn(fechaEntrada);

        politicaCancelacion.ejecutar(reservaMock);

        String output = outputStream.toString().trim();
        assertEquals("Cancelación sin cargo.", output);
    }

    @Test
    public void testCancelacionConCargoMitadEntre10Y19DiasAntes() {
        LocalDate fechaEntrada = LocalDate.now().plusDays(15);
        when(reservaMock.getFechaEntrada()).thenReturn(fechaEntrada);

        when(reservaMock.calcularPrecioEstadia()).thenReturn(200.0);

        politicaCancelacion.ejecutar(reservaMock);

        String output = outputStream.toString().trim();
        assertEquals("Cancelación con cargo. Cargo aplicado: $100.0", output);
    }

    @Test
    public void testCancelacionConCargoCompletoDentroDe10Dias() {
        LocalDate fechaEntrada = LocalDate.now().plusDays(5);
        when(reservaMock.getFechaEntrada()).thenReturn(fechaEntrada);

        when(reservaMock.calcularPrecioEstadia()).thenReturn(300.0);

        politicaCancelacion.ejecutar(reservaMock);

        String output = outputStream.toString().trim();
        assertEquals("Cancelación con cargo. Cargo aplicado: $300.0", output);
    }
}