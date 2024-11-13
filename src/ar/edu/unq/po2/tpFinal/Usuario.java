package ar.edu.unq.po2.tpFinal;

import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;

public class Usuario implements Inquilino, Propietario{
	private SitioWeb sitio;
	private String nombreCompleto;
	private String correoElectronico;
	private int numeroDeTelefono;
	private List<Reserva> reservas;
	private List<Inmueble> inmuebles;
	private Ranking rankingPropietario;
	private Ranking rankingInquilino;
	private LocalDate fechaDeRegistro;
	private List<String> comentariosInquilino;

	
	public Usuario(SitioWeb sitio, String nombreCompleto, String correoElectronico, int numeroDeTelefono) {
	    setSitio(sitio);
	    setNombreCompleto(nombreCompleto);
	    setCorreoElectronico(correoElectronico);
	    setNumeroDeTelefono(numeroDeTelefono); 
	    setReservas(new ArrayList<Reserva>());
	    setInmuebles(new ArrayList<Inmueble>());
	    setRankingInquilino(new Ranking());
	    setRankingPropietario(new Ranking());
	    setFechaDeRegistro(LocalDate.now());
	    setComentariosInquilino(new ArrayList<String>());
	}
	
	public SitioWeb getSitio() {
		return sitio;
	}

	public void setSitio(SitioWeb sitio) {
		this.sitio = sitio;
	}

	public List<Reserva> getReservas() {
		return reservas;
	}

	public void setReservas(List<Reserva> reservas) {
		this.reservas = reservas;
	}

	public void setInmuebles(List<Inmueble> inmuebles) {
		this.inmuebles = inmuebles;
	}

	public void setRankingPropietario(Ranking rankingPropietario) {
		this.rankingPropietario = rankingPropietario;
	}

	public void setRankingInquilino(Ranking rankingInquilino) {
		this.rankingInquilino = rankingInquilino;
	}

	public void setFechaDeRegistro(LocalDate fechaDeRegistro) {
		this.fechaDeRegistro = fechaDeRegistro;
	}

	public void setComentariosInquilino(List<String> comentariosInquilino) {
		this.comentariosInquilino = comentariosInquilino;
	}

	public SitioWeb getSitioWeb() {
		return sitio;
	}
	
	public void setNombreCompleto(String nombreCompleto) {
		this.nombreCompleto = nombreCompleto;
	}
	
	public String getNombreCompleto() {
		return nombreCompleto;
	}

	public void setCorreoElectronico(String correoElectronico) {
		this.correoElectronico = correoElectronico;
	}
	
	public String getCorreoElectronico() {
		return correoElectronico;
	}
	
	public void setNumeroDeTelefono(int numeroDeTelefono) {
		this.numeroDeTelefono = numeroDeTelefono;
	}
	
	public int getNumeroDeTelefono() {
		return numeroDeTelefono;
	}
	
	public List<Reserva> getMisReservas(){
		return reservas;
	}

	public List<Inmueble> getInmuebles() {
		return inmuebles;
	}
	
	public Ranking getRankingPropietario() {
		return this.rankingPropietario;
	}
	
	public Ranking getRankingInquilino() {
		return this.rankingInquilino;
	}

	public LocalDate getFechaDeRegistro() {
		return this.fechaDeRegistro;
	}
	
	public List<String> getComentariosInquilino(){
		return comentariosInquilino;
	}

	public void publicarInmueble(Inmueble i) {
		getSitio().darDeAltaInmueble(i);
	}
	
	public void agregarInmueble(Inmueble i) {
        getInmuebles().add(i);
    }
	
	public List<Inmueble> buscarInmuebles(FiltroCompuesto filtro) {
		return getSitioWeb().filtrarInmuebles(filtro);
	}
	
	public void rankearPropietario(Reserva reserva, String categoria, Puntaje puntaje) {
		getSitio().rankearPropietario(reserva, categoria, puntaje);
	}
	
	public void rankearInquilino(Reserva reserva, String categoria, Puntaje puntaje) {
		getSitio().rankearInquilino(reserva, categoria, puntaje);
	}
	
	public void rankearInmueble(Reserva reserva, String categoria, Puntaje puntaje) {
		getSitio().rankearInmueble(reserva, categoria, puntaje);
	}
	
	public void agregarComentario(String comentario) {
		this.comentariosInquilino.add(comentario);
	}
	
	public void comentarInmueble(Reserva reserva, String comentario) {
		this.sitio.registrarComentarioInmueble(reserva, comentario);
	}
	
	public String getAntiguedad() {
	    LocalDate fechaActual = LocalDate.now();
	    Period antiguedad = Period.between(this.fechaDeRegistro, fechaActual);

	    int años = antiguedad.getYears();
	    int meses = antiguedad.getMonths();

	    return años + " año(s), " + meses + " meses";
	}
	
	public void  visualizarInmueble(Inmueble inmueble) {
		System.out.println("Datos del Inmueble:");
        System.out.println("Tipo: " + inmueble.getTipoInmueble());
        System.out.println("Ciudad: " + inmueble.getCiudad());
        System.out.println("Precio: " + inmueble.getPrecioBase());

        // Puntajes
        System.out.println("Puntajes del Inmueble:");
        for (String categoria : inmueble.getPropietario().getSitioWeb().getCategoriasPorEntidad("Inmueble")) {
            System.out.println(categoria + ": " + inmueble.getRankingInmueble().obtenerPromedioPorCategoria(categoria));
        }
        System.out.println("Puntaje Promedio Total: " + inmueble.getRankingInmueble().obtenerPromedioTotal());

        // Comentarios
        System.out.println("Comentarios:");
        for (String comentario : inmueble.getComentarios()) {
            System.out.println(comentario);
        }
        
        // Información del propietario
            System.out.println("Información del Propietario:");
            System.out.println("Nombre: " + inmueble.getPropietario().getNombreCompleto());
            System.out.println("Usuario del sitio hace: " + getAntiguedad());
            System.out.println("Veces alquilado el inmueble: " + inmueble.getPropietario().cantVecesQueFueAlquiladoElInmueble(inmueble));
            System.out.println("Veces alquilado: " + inmueble.getPropietario().cantVecesQueAlquilo());
            System.out.println("Puntaje Promedio del Propietario: " + inmueble.getPropietario().getRankingPropietario().obtenerPromedioTotal());
    }
	
	public void reservar(Inmueble inmueble, String metodoPago, LocalDate fechaInicio, LocalDate fechaFin) {
		getSitio().reservar(new Reserva(fechaInicio, fechaFin, inmueble, this));
	}
	
	public void comentarInquilino(Reserva reserva, String comentario) {
		getSitio().registrarComentarioInquilino(reserva, comentario);
	}
	
	public void visualizarInquilino(Usuario inquilino) {
        System.out.println("Información del posible Inquilino:");
        System.out.println("Nombre: " + inquilino.getNombreCompleto());
        System.out.println("Comentarios: ");
        for (String comentario : inquilino.getComentariosInquilino()) {
            System.out.println(comentario);
        }
        System.out.println("Puntaje Promedio: " + inquilino.getRankingPropietario().obtenerPromedioTotal());
        System.out.println("Contacto: " + inquilino.getCorreoElectronico() + ", Teléfono: " + inquilino.getNumeroDeTelefono());
	}

	public void aceptarUnaReserva(Reserva reserva) {
	    getSitio().consolidarReserva(reserva);
	}

	public void registrarReserva(Reserva reserva) {
	    getReservas().add(reserva);
	}

	public void rechazarReserva(Reserva reserva) {
	    getSitio().rechazarReserva(reserva);
	}

	public List<Reserva> getReservasFuturas() {
	    return getReservas().stream()
	            .filter(r -> r.getFechaEntrada().isAfter(LocalDate.now()))
	            .toList();
	}

	public List<Reserva> getReservasEnCiudad(String ciudad) {
	    return getReservas().stream()
	            .filter(r -> r.getInmueble().getCiudad().equals(ciudad))
	            .toList();
	}

	public List<String> getCiudadesConReserva() {
	    return getReservas().stream()
	            .map(r -> r.getInmueble().getCiudad())
	            .distinct()
	            .toList();
	}

	public void cancelarReserva(Reserva reserva) {
	    getSitio().cancelarReserva(reserva);
	}

	public void eliminarReserva(Reserva reserva) {
	    getReservas().remove(reserva);
	}

	public int cantVecesQueFueAlquiladoElInmueble(Inmueble inmueble) {
	    return inmueble.getReservasConfirmadas().stream()
	            .filter(r -> r.getEstadoReserva().equals("Finalizada"))
	            .toList()
	            .size();
	}

	public int cantVecesQueAlquilo() {
	    return getInmuebles().stream()
	            .mapToInt(i -> cantVecesQueFueAlquiladoElInmueble(i))
	            .sum();
	}

	public List<Inmueble> todosLosInmueblesQueYaFueronAlquilados() {
	    return getInmuebles().stream()
	            .filter(i -> cantVecesQueFueAlquiladoElInmueble(i) >= 1)
	            .toList();
	}

	public void modificarPrecioPeriodo(Double precioNuevo, Inmueble inmueble, PeriodoConPrecio periodo) {
	    inmueble.modificarPrecioPeriodo(periodo, precioNuevo);
	}

	public void modificarPrecioBase(Double precioNuevo, Inmueble inmueble) {
	    inmueble.modificarPrecioBase(precioNuevo);
	}

	public void hacerCheckOut(Reserva reserva) {
	    getSitio().registrarCheckOut(reserva, LocalDate.now());
	}
}
