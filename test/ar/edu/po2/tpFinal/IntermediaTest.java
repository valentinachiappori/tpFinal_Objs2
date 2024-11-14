package ar.edu.po2.tpFinal;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.time.LocalDate;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import ar.edu.unq.po2.tpFinal.Intermedia;
import ar.edu.unq.po2.tpFinal.Reserva;

public class IntermediaTest {

    private Reserva reservaMock;
    private Intermedia politicaCancelacion;
    private ByteArrayOutputStream outputStream;

    @BeforeEach
    public void setUp() {
        reservaMock = mock(Reserva.class);
        politicaCancelacion = new Intermedia();
        
        // Capturar la salida de System.out
        outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));
    }

    @Test
    public void testCancelacionSinCargoAntesDe19Dias() {
        // Configurar fecha de entrada y límite de cancelación
        LocalDate fechaEntrada = LocalDate.now().plusDays(20);
        when(reservaMock.getFechaEntrada()).thenReturn(fechaEntrada);

        // Ejecutar el método
        politicaCancelacion.ejecutar(reservaMock);

        // Verificar la salida
        String output = outputStream.toString().trim();
        assertEquals("Cancelación sin cargo.", output);
    }

    @Test
    public void testCancelacionConCargoMitadEntre10Y19DiasAntes() {
        // Configurar fecha de entrada y límite de cancelación
        LocalDate fechaEntrada = LocalDate.now().plusDays(15);
        when(reservaMock.getFechaEntrada()).thenReturn(fechaEntrada);

        // Configurar el precio de la estadía
        when(reservaMock.calcularPrecioEstadia()).thenReturn(200.0);

        // Ejecutar el método
        politicaCancelacion.ejecutar(reservaMock);

        // Verificar la salida
        String output = outputStream.toString().trim();
        assertEquals("Cancelación con cargo. Cargo aplicado: $100.0", output);
    }

    @Test
    public void testCancelacionConCargoCompletoDentroDe10Dias() {
        // Configurar fecha de entrada y límite de cancelación
        LocalDate fechaEntrada = LocalDate.now().plusDays(5);
        when(reservaMock.getFechaEntrada()).thenReturn(fechaEntrada);

        // Configurar el precio de la estadía
        when(reservaMock.calcularPrecioEstadia()).thenReturn(300.0);

        // Ejecutar el método
        politicaCancelacion.ejecutar(reservaMock);

        // Verificar la salida
        String output = outputStream.toString().trim();
        assertEquals("Cancelación con cargo. Cargo aplicado: $300.0", output);
    }
}