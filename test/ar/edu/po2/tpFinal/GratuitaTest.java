package ar.edu.po2.tpFinal;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.time.LocalDate;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import ar.edu.unq.po2.tpFinal.Gratuita;
import ar.edu.unq.po2.tpFinal.Inmueble;
import ar.edu.unq.po2.tpFinal.Reserva;

public class GratuitaTest {

    private Reserva reservaMock;
    private Inmueble inmuebleMock;
    private Gratuita politicaCancelacion;
    private ByteArrayOutputStream outputStream;

    @BeforeEach
    public void setUp() {
        reservaMock = mock(Reserva.class);
        inmuebleMock = mock(Inmueble.class);
        politicaCancelacion = new Gratuita();
        
        // Capturar la salida de System.out
        outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));
    }

    @Test
    public void testCancelacionGratuitaAntesDeFechaLimite() {
        // Configurar fecha de entrada y límite de cancelación
        LocalDate fechaEntrada = LocalDate.now().plusDays(15);
        when(reservaMock.getFechaEntrada()).thenReturn(fechaEntrada);

        // Ejecutar el método
        politicaCancelacion.ejecutar(reservaMock);

        // Verificar la salida
        String output = outputStream.toString().trim();
        assertEquals("Cancelación gratuita.", output);
    }

    @Test
    public void testCancelacionConCargoDespuesDeFechaLimite() {
        // Configurar fecha de entrada y límite de cancelación
        LocalDate fechaEntrada = LocalDate.now().plusDays(5);
        when(reservaMock.getFechaEntrada()).thenReturn(fechaEntrada);

        // Configurar el inmueble y el cargo calculado
        when(reservaMock.getInmueble()).thenReturn(inmuebleMock);
        when(inmuebleMock.calcularPrecioDia(fechaEntrada)).thenReturn(100.0);

        // Ejecutar el método
        politicaCancelacion.ejecutar(reservaMock);

        // Verificar la salida
        String output = outputStream.toString().trim();
        assertEquals("Cancelación con cargo. Cargo aplicado: $200.0", output);
    }
}