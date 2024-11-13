package ar.edu.po2.tpFinal;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import ar.edu.unq.po2.tpFinal.PeriodoConPrecio;

class PeriodoConPrecioTest {
	
	private PeriodoConPrecio unPeriodo; 
	
	@BeforeEach
	void setUp() throws Exception {
		unPeriodo = new PeriodoConPrecio(LocalDate.of(2024,11,18),LocalDate.of(2024, 11,30) , 5000d); 
	}

	@Test
	void testFechaEstaIncluidaEnPeriodo() {
		assertTrue(unPeriodo.incluidaEnPeriodo(LocalDate.of(2024,11,25))) ;
	}
	
	@Test 
	void testGetPrecioPorDia() {
		assertEquals(5000d, unPeriodo.getPrecioPorDia());
	}
	
	@Test
	void testFechaNoEstaIncluidaEnPeriodo() {
		assertFalse(unPeriodo.incluidaEnPeriodo(LocalDate.of(2025,2,25)));
	}
}
