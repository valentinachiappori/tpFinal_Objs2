package ar.edu.po2.tpFinal;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Stream;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import ar.edu.unq.po2.tpFinal.Puntaje;
import ar.edu.unq.po2.tpFinal.Ranking;

class RankingTest {

    private Ranking ranking;

    @BeforeEach
    void setUp() {
        ranking = new Ranking();
    }
    
    @Test
    void testAgregarPuntaje() {
        String categoria = "Amabilidad";
        Puntaje puntaje = Puntaje.CUATRO;

        ranking.agregarPuntaje(categoria, puntaje);

        Map<String, List<Integer>> puntuacionesPorCategoria = ranking.getPuntuacionesPorCategoria();
        assertEquals(1, puntuacionesPorCategoria.get(categoria).size());
        assertEquals(puntaje.getNivel(), puntuacionesPorCategoria.get(categoria).get(0));
    }

    
    @Test
    void testObtenerPromedioTotal() {
        ranking.agregarPuntaje("Amabilidad", Puntaje.CINCO);
        ranking.agregarPuntaje("Amabilidad", Puntaje.CUATRO);
        ranking.agregarPuntaje("Profesionalismo", Puntaje.DOS);
        ranking.agregarPuntaje("Profesionalismo", Puntaje.CINCO);
        
        double promedioEsperado = (5 + 4 + 2 + 5) / 4.0;

        assertEquals(promedioEsperado, ranking.obtenerPromedioTotal());
    }
    
    
    @Test
    void testObtenerPromediosPorCategoria() {
    	
    	ranking.agregarPuntaje("Amabilidad", Puntaje.CINCO);
        ranking.agregarPuntaje("Amabilidad", Puntaje.CINCO);
        ranking.agregarPuntaje("Amabilidad", Puntaje.CINCO);
        ranking.agregarPuntaje("Amabilidad", Puntaje.CINCO);
        
        ranking.agregarPuntaje("Profesionalismo", Puntaje.CINCO);
        ranking.agregarPuntaje("Profesionalismo", Puntaje.CINCO);
        ranking.agregarPuntaje("Profesionalismo", Puntaje.CINCO);

        Map<String, Double> promedios = ranking.obtenerPromediosPorCategoria();

        assertEquals(5.0, promedios.get("Amabilidad"));
        assertEquals(5.0, promedios.get("Profesionalismo"));
    }

}
