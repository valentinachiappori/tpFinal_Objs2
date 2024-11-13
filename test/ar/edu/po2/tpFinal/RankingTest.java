package ar.edu.po2.tpFinal;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import ar.edu.unq.po2.tpFinal.Puntaje;
import ar.edu.unq.po2.tpFinal.Ranking;

class RankingTest {
	
	private Ranking ranking;
	
	@Mock
    private Map<String, List<Integer>> puntuacionesPorCategoria;
    
    @Mock
    private List<Integer> listaPuntajes;
    
    @BeforeEach
    void setUp() throws Exception {
        ranking = new Ranking() {
            @Override
            public Map<String, List<Integer>> getPuntuacionesPorCategoria() {
                return puntuacionesPorCategoria;
            }
        };
    }

    @Test
    void testAgregarPuntaje() {
        String categoria = "Amabilidad";
        Puntaje puntaje = Puntaje.CUATRO;
        
        when(puntuacionesPorCategoria.get(categoria)).thenReturn(listaPuntajes);
        when(listaPuntajes.add(puntaje.getNivel())).thenReturn(true);
        
        ranking.agregarPuntaje(categoria, puntaje);
 
        verify(puntuacionesPorCategoria).putIfAbsent(eq(categoria), any());
        verify(listaPuntajes).add(eq(puntaje.getNivel()));
    }

}
