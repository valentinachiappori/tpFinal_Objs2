package ar.edu.unq.po2.tpFinal;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class Inmueble {
    private String tipo;
	private int superficie;
	private String pais; // 
	private String ciudad; // 
	private List<String> servicios;
	private int capacidad;
	private List<String> fotos; //que no sean mas de 5 
	private LocalTime checkIn;
	private LocalTime checkOut; 
	//private double precio; //EstrategiaPrecio que dependiendo el dia te de un precio o el otro 
	// si usamos periodoConPrecio no necesitariamos precio como atributo, sino un mensaje.
	private List<String> metodosDePago;
	private List<PeriodoConPrecio> periodosPublicados;
	private List<Integer> calificaciones;

	

	public Inmueble(String tipo, int superficie, String pais, String ciudad, List<String> servicios, int capacidad
			, LocalTime checkIn, LocalTime checkOut, List<String> metodosDePago,
			List<PeriodoConPrecio> periodosPublicados) {
		super();
		this.tipo = tipo;
		this.superficie = superficie;
		this.pais = pais;
		this.ciudad = ciudad;
		this.servicios = servicios;
		this.capacidad = capacidad;
		this.fotos = new ArrayList<String>();
		this.checkIn = checkIn;
		this.checkOut = checkOut;
		this.metodosDePago = metodosDePago;
		this.periodosPublicados = periodosPublicados;
		
		
		this.calificaciones = new ArrayList<Integer>();
	}

	public void agregarFoto(String foto) {
		if (fotos.size() < 5) {
			fotos.add(foto);
		} //que hacemos si hay 5, le avisa? pisa la ultima?
	}

	public void agregarCalificacion(int puntuacion) {
	    if (puntuacion >= 1 && puntuacion <= 5) {
	        this.calificaciones.add(puntuacion);
	    } else {
	        throw new IllegalArgumentException("La calificación debe estar entre 1 y 5.");
	    }
	}  // podriamos hacer que la calificacion se de con Enums asi nos evitamos que salga de ese rango

	public String getCiudad() {
		return ciudad;
	}

	public boolean estaDisponibleEnPeriodo(LocalDate fechaEntrada, LocalDate fechaSalida) {
		
		//como hacemos para saber las fechas q fueron alquiladasssssss
		
		return ;
	}

}


