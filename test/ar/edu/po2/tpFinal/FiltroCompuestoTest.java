package ar.edu.po2.tpFinal;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import ar.edu.unq.po2.tpFinal.Filtro;
import ar.edu.unq.po2.tpFinal.FiltroCapacidad;
import ar.edu.unq.po2.tpFinal.FiltroCiudad;
import ar.edu.unq.po2.tpFinal.FiltroCompuesto;
import ar.edu.unq.po2.tpFinal.FiltroFechas;
import ar.edu.unq.po2.tpFinal.FiltroPrecioMin;
import ar.edu.unq.po2.tpFinal.FiltroSimple;
import ar.edu.unq.po2.tpFinal.Inmueble;

class FiltroCompuestoTest {

    private FiltroCiudad filtroCiudadMock;
    private FiltroFechas filtroFechasMock;
    private FiltroCompuesto filtroCompuesto;
    

    @BeforeEach
    public void setUp() {

        filtroCompuesto = new FiltroCompuesto("Buenos Aires", LocalDate.now(), LocalDate.now().plusDays(5));
    }
    
    @Test
    public void constructor_conFiltrosValidos_noLanzaExcepcion() {
    	
    	
        assertDoesNotThrow(() -> new FiltroCompuesto("Buenos Aires", LocalDate.now(), LocalDate.now().plusDays(5)));
        assertTrue(filtroCompuesto.esFiltroValido());;
    }

    @Test
    public void constructor_conFiltroInvalido_lanzaExcepcion() {


        // Ejecuta el constructor con los mocks
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
        	new FiltroCompuesto("Buenos Aires", LocalDate.now(), null);
        });

        // Verifica que el mensaje de la excepción es el esperado
        assertEquals("Uno de los FiltrosObligatorios tiene un valorNull", exception.getMessage());
    }
    
    @Test
    public void cumple_conTodosLosFiltrosQueCumplen_devuelveTrue() {
        Inmueble inmuebleMock = mock(Inmueble.class);
        FiltroCiudad filtroCiudadMock = mock(FiltroCiudad.class);
        FiltroFechas filtroFechasMock = mock(FiltroFechas.class);
        FiltroSimple filtroCapacidadMock = mock(FiltroCapacidad.class);

        when(filtroCiudadMock.cumple(inmuebleMock)).thenReturn(true);
        when(filtroFechasMock.cumple(inmuebleMock)).thenReturn(true);
        when(filtroCapacidadMock.cumple(inmuebleMock)).thenReturn(true);
        when(filtroCiudadMock.esFiltroValido()).thenReturn(true);
        when(filtroFechasMock.esFiltroValido()).thenReturn(true);
        when(filtroCapacidadMock.esFiltroValido()).thenReturn(true);        
        when(inmuebleMock.getCiudad()).thenReturn("Buenos Aires");

        FiltroCompuesto filtroCompuesto = new FiltroCompuesto("Buenos Aires", LocalDate.now(), LocalDate.now().plusDays(5));

        filtroCompuesto.agregarFiltro(filtroCapacidadMock);

        filtroCompuesto.setFiltroCiudad(filtroCiudadMock);
        filtroCompuesto.setFiltroFechas(filtroFechasMock);

        assertTrue(filtroCompuesto.cumple(inmuebleMock));
    }

    @Test
    public void cumple_conAlgunFiltroQueNoCumple_devuelveFalse() {
        Inmueble inmuebleMock = mock(Inmueble.class);
        FiltroCiudad filtroCiudadMock = mock(FiltroCiudad.class);
        FiltroFechas filtroFechasMock = mock(FiltroFechas.class);
        FiltroSimple filtroCapacidadMock = mock(FiltroCapacidad.class);

        when(filtroCiudadMock.cumple(inmuebleMock)).thenReturn(false);
        when(filtroFechasMock.cumple(inmuebleMock)).thenReturn(true);
        when(filtroCapacidadMock.cumple(inmuebleMock)).thenReturn(true);
        when(filtroCiudadMock.esFiltroValido()).thenReturn(true);
        when(filtroFechasMock.esFiltroValido()).thenReturn(true);
        when(filtroCapacidadMock.esFiltroValido()).thenReturn(true);        
        when(inmuebleMock.getCiudad()).thenReturn("Buenos Aires");

        FiltroCompuesto filtroCompuesto = new FiltroCompuesto("Buenos Aires", LocalDate.now(), LocalDate.now().plusDays(5));

        filtroCompuesto.agregarFiltro(filtroCapacidadMock);

        filtroCompuesto.setFiltroCiudad(filtroCiudadMock);
        filtroCompuesto.setFiltroFechas(filtroFechasMock);

        assertTrue(filtroCompuesto.cumple(inmuebleMock));
    }
    
    @Test
    public void agregoUnFiltroQueNecesitaFechasYCumple() {
        Inmueble inmuebleMock = mock(Inmueble.class);
        FiltroCiudad filtroCiudadMock = mock(FiltroCiudad.class);
        FiltroFechas filtroFechasMock = mock(FiltroFechas.class);
        FiltroSimple filtroPrecioMinMock = mock(FiltroPrecioMin.class);

        when(filtroCiudadMock.cumple(inmuebleMock)).thenReturn(false);
        when(filtroFechasMock.cumple(inmuebleMock)).thenReturn(true);
        when(filtroPrecioMinMock.cumple(inmuebleMock)).thenReturn(true);
        when(filtroCiudadMock.esFiltroValido()).thenReturn(true);
        when(filtroFechasMock.esFiltroValido()).thenReturn(true);
        when(filtroPrecioMinMock.esFiltroValido()).thenReturn(true);
        when(filtroPrecioMinMock.precisaFecha()).thenReturn(true);
        when(inmuebleMock.getCiudad()).thenReturn("Buenos Aires");
        when(filtroPrecioMinMock.getFechaInicio()).thenReturn(LocalDate.now());
        when(filtroPrecioMinMock.getFechaFin()).thenReturn(LocalDate.now().plusDays(5));
        FiltroCompuesto filtroCompuesto = new FiltroCompuesto("Buenos Aires", LocalDate.now(), LocalDate.now().plusDays(5));

        filtroCompuesto.agregarFiltro(filtroPrecioMinMock);

        filtroCompuesto.setFiltroCiudad(filtroCiudadMock);
        filtroCompuesto.setFiltroFechas(filtroFechasMock);
        verify(filtroPrecioMinMock, times(1)).setFechaInicio(LocalDate.now());
        assertTrue(filtroCompuesto.cumple(inmuebleMock));
    }
 
    @Test
    public void agregoUnFiltroPeroNoEsValido() {
        Inmueble inmuebleMock = mock(Inmueble.class);
        FiltroCiudad filtroCiudadMock = mock(FiltroCiudad.class);
        FiltroFechas filtroFechasMock = mock(FiltroFechas.class);
        FiltroSimple filtroPrecioMinMock = mock(FiltroPrecioMin.class);

        when(filtroCiudadMock.cumple(inmuebleMock)).thenReturn(false);
        when(filtroFechasMock.cumple(inmuebleMock)).thenReturn(true);
        when(filtroCiudadMock.esFiltroValido()).thenReturn(true);
        when(filtroFechasMock.esFiltroValido()).thenReturn(true);
        when(filtroPrecioMinMock.esFiltroValido()).thenReturn(false);
        
        when(filtroPrecioMinMock.precisaFecha()).thenReturn(true);
        when(inmuebleMock.getCiudad()).thenReturn("Buenos Aires");
        when(filtroPrecioMinMock.getFechaInicio()).thenReturn(LocalDate.now());
        when(filtroPrecioMinMock.getFechaFin()).thenReturn(LocalDate.now().plusDays(5));
        FiltroCompuesto filtroCompuesto = new FiltroCompuesto("Buenos Aires", LocalDate.now(), LocalDate.now().plusDays(5));


        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            filtroCompuesto.agregarFiltro(filtroPrecioMinMock);
        }); 
        assertEquals("el filtro es invalido", exception.getMessage());
        
     }
    
    @Test
    public void testSetFechaInicio() {
        FiltroCompuesto filtroCompuesto = new FiltroCompuesto("Buenos Aires", LocalDate.now(), LocalDate.now().plusDays(5));

        LocalDate nuevaFechaInicio =LocalDate.now().plusDays(1);

        filtroCompuesto.setFechaInicio(nuevaFechaInicio);

        assertEquals(nuevaFechaInicio, filtroCompuesto.getFechaInicio());
    }
    
    @Test
    public void testSetFiltros() {
        FiltroCompuesto filtroCompuesto = new FiltroCompuesto("Buenos Aires", LocalDate.now(), LocalDate.now().plusDays(5));

        List<Filtro> filtros = new ArrayList<>();
        FiltroSimple filtroCapacidadMock = mock(FiltroCapacidad.class);
        filtros.add(filtroCapacidadMock);

        filtroCompuesto.setFiltros(filtros);

        assertEquals(filtros, filtroCompuesto.getFiltros());
    }
    
    @Test
    public void testGetFechaFin() {
        LocalDate fechaInicio = LocalDate.now();
        LocalDate fechaFin = fechaInicio.plusDays(5);
        FiltroCompuesto filtroCompuesto = new FiltroCompuesto("Buenos Aires", fechaInicio, fechaFin);

        assertEquals(fechaFin, filtroCompuesto.getFechaFin());
    }
}
