package ar.edu.unq.po2.tpFinal;

import java.time.LocalDate;

public class Reserva {
	private final LocalDate fechaEntrada;
	private final LocalDate fechaSalida;
	private final Inmueble inmueble;
	private final Usuario inquilino;
	private String estadoReserva; 
	
	public Reserva(LocalDate fechaEntrada, LocalDate fechaSalida, Inmueble inmueble, Usuario inquilino) {
		this.fechaEntrada = fechaEntrada;
		this.fechaSalida = fechaSalida;
		this.inmueble = inmueble;
		this.inquilino = inquilino;
		this.setEstadoReserva("Pendiente");
	}

	public LocalDate getFechaEntrada() {
		return fechaEntrada;
	}
	
	public LocalDate getFechaSalida() {
		return fechaSalida;
	}

	public Inmueble getInmueble() {
		return inmueble;
	}

	public Usuario getInquilino() {
		return inquilino;
	}

	public Usuario getPropietario() {
		return this.inmueble.getPropietario();
	}

	public void cambiarEstadoAAceptada() {
        if ("Pendiente".equals(this.estadoReserva)) {
            this.estadoReserva = "Aceptada";
        } else {
            throw new IllegalStateException("No se puede cambiar el estado a Aceptada si no está en Pendiente.");
        }
    }

	public void cambiarEstadoAFinalizada() {
        if ("Aceptada".equals(this.estadoReserva)) {
            this.estadoReserva = "Finalizada";
        } else {
            throw new IllegalStateException("No se puede cambiar el estado a Finalizada si no está en Aceptada.");
        }
    }

	public String getEstadoReserva() {
		return estadoReserva;
	}

	public void setEstadoReserva(String estadoReserva) {
		this.estadoReserva = estadoReserva;
	}
	
	public Double calcularPrecioEstadia() {
		return getInmueble().calcularPrecioEstadia(getFechaEntrada(), getFechaSalida());
	}
	
	public String toString() {
	        return "Reserva{" +
	               "Inmueble=" + inmueble +
	               ", Fecha de entrada=" + fechaEntrada +
	               ", Fecha de salida=" + fechaSalida +
	               ", Política de cancelación= " + getInmueble().getPoliticaDeCancelacion() +
	               ", Precio total= $" + calcularPrecioEstadia() +
	               '}';
	}
	 
}
