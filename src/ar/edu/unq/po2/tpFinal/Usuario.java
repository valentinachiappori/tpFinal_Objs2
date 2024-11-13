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
		this.sitio = sitio;
		this.nombreCompleto = nombreCompleto;
		this.correoElectronico = correoElectronico;
		this.numeroDeTelefono = numeroDeTelefono;
		this.reservas = new ArrayList<Reserva>();
		this.inmuebles = new ArrayList<Inmueble>();
		this.rankingInquilino = new Ranking();
		this.rankingPropietario = new Ranking();
		this.fechaDeRegistro = LocalDate.now();
		this.comentariosInquilino = new ArrayList<String>();
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
	
	public void setFechaRegistro(LocalDate f) {
		fechaDeRegistro = f;
	}

	public void publicarInmueble(Inmueble i) {
		this.sitio.darDeAltaInmueble(i);
	}
	
	public void agregarInmueble(Inmueble i) {
        inmuebles.add(i);
    }
	
	public List<Inmueble> buscarInmuebles(FiltroCompuesto filtro) {
		return getSitioWeb().filtrarInmuebles(filtro);
	}
	
	public void rankearPropietario(Reserva reserva, String categoria, Puntaje puntaje) {
		this.sitio.rankearPropietario(reserva, categoria, puntaje);
	}
	
	public void rankearInquilino(Reserva reserva, String categoria, Puntaje puntaje) {
		this.sitio.rankearInquilino(reserva, categoria, puntaje);
	}
	
	public void rankearInmueble(Reserva reserva, String categoria, Puntaje puntaje) {
		this.sitio.rankearInmueble(reserva, categoria, puntaje);
	}
	
	public void agregarComentario(String comentario) {
		this.comentariosInquilino.add(comentario);
	}
	
	public void comentarInmueble(Reserva reserva, String comentario) {
		this.sitio.registrarComentarioInmueble(reserva, comentario);
	}
	
	public String getAntiguedad() {
	    LocalDate fechaActual = LocalDate.now();
	    Period antiguedad = Period.between(fechaDeRegistro, fechaActual);

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
		sitio.reservar(new Reserva(fechaInicio, fechaFin, inmueble, this));
	}
	
	public void comentarInquilino(Reserva reserva, String comentario) {
		this.sitio.registrarComentarioInquilino(reserva, comentario);
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
		this.sitio.consolidarReserva(reserva);
	}
	
	public void registrarReserva(Reserva reserva) {
		this.reservas.add(reserva);
	}
	
	public void rechazarReserva(Reserva reserva) {
		this.sitio.rechazarReserva(reserva);
	}
 
	public List<Reserva> getReservasFuturas(){
		return this.reservas.stream().filter(r -> r.getFechaEntrada().isAfter(LocalDate.now())).toList();
	}
	
	public List<Reserva> getReservasEnCiudad(String ciudad){
		return this.reservas.stream().filter(r -> r.getInmueble().getCiudad().equals(ciudad)).toList();
	}
	
	public List<String> getCiudadesConReserva(){
		return this.reservas.stream().map(r -> r.getInmueble().getCiudad()).toList();
	}
	
	public void cancelarReserva(Reserva reserva) {
		this.sitio.cancelarReserva(reserva);
	}
	
	public void eliminarReserva(Reserva reserva) {
		this.reservas.remove(reserva);
	}
	
	public int cantVecesQueFueAlquiladoElInmueble(Inmueble inmueble) {
		return inmueble.getReservasConfirmadas().stream().filter(r -> r.getEstadoReserva().equals("Finalizada")).toList().size();
	}
	
	public int cantVecesQueAlquilo(){
		return inmuebles.stream().mapToInt(i -> this.cantVecesQueFueAlquiladoElInmueble(i)).sum();
	}
	
	public List<Inmueble> todosLosInmueblesQueYaFueronAlquilados() {
		return inmuebles.stream().filter(i -> this.cantVecesQueFueAlquiladoElInmueble(i) >= 1).toList();
	}
	
	public void modificarPrecioPeriodo(Double precioNuevo, Inmueble inmueble, PeriodoConPrecio periodo) {
		inmueble.modificarPrecioPeriodo(periodo, precioNuevo);
	}
	
	public void modificarPrecioBase(Double precioNuevo, Inmueble inmueble) {
		inmueble.modificarPrecioBase(precioNuevo);
	}
	
	public void hacerCheckOut(Reserva reserva) {
		sitio.registrarCheckOut(reserva, LocalDate.now());
	}
}
