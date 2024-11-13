package ar.edu.unq.po2.tpFinal;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Ranking {
	
	private Map<String, List<Integer>> puntuacionesPorCategoria;

    //Constructor 
    public Ranking () {
        setPuntuacionesPorCategoria(new HashMap<>());
    }
	
    public Map<String, List<Integer>> getPuntuacionesPorCategoria() {
		return puntuacionesPorCategoria;
	}

	public void setPuntuacionesPorCategoria(Map<String, List<Integer>> puntuacionesPorCategoria) {
		this.puntuacionesPorCategoria = puntuacionesPorCategoria;
	}

	public void agregarPuntaje(String categoria, Puntaje puntaje) {
    	getPuntuacionesPorCategoria().putIfAbsent(categoria, new ArrayList<Integer>());
        getPuntuacionesPorCategoria().get(categoria).add(puntaje.getNivel());
	}
    
    public double obtenerPromedioPorCategoria(String categoria) {
        List<Integer> puntuaciones = puntuacionesPorCategoria.get(categoria);
        if (puntuaciones.isEmpty()) {
            return 0;
        }
        int total = puntuaciones.stream().mapToInt(i -> i.intValue()).sum();
        return (double) total / puntuaciones.size();
    }
    
    public double obtenerPromedioTotal() {
        int totalPuntuaciones = 0;
        int totalCantidad = 0;
        
        for (List<Integer> puntuaciones : puntuacionesPorCategoria.values()) {
            totalPuntuaciones += puntuaciones.stream().mapToInt(i -> i.intValue()).sum();
            totalCantidad += puntuaciones.size();
        }
        
        if (totalCantidad > 0) {
            return (double) totalPuntuaciones / totalCantidad;
        } else {
            return 0;
        }
    }
    
    public Map<String, Double> obtenerPromediosPorCategoria() {
        Map<String, Double> promedios = new HashMap<>();
        for (String categoria : getPuntuacionesPorCategoria().keySet()) {
            promedios.put(categoria, obtenerPromedioPorCategoria(categoria));
        }
        return promedios;
    }
    
}
