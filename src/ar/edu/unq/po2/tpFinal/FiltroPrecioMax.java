package ar.edu.unq.po2.tpFinal;

import java.time.LocalDate;

public class FiltroPrecioMax implements Filtro{

		private LocalDate fechaInicio;
		private LocalDate fechaFin;
		private double precio;
		
		public FiltroPrecioMax(double precio) {
			this.setPrecio(precio);
		}
		@Override
		public boolean cumple(Inmueble inmueble) {
			// TODO Auto-generated method stub
			return inmueble.calcularPrecioEstadia(getFechaInicio(), getFechaFin()) < getPrecio();
		}
		
		public LocalDate getFechaFin() {
			return fechaFin;
		}
		public void setFechaFin(LocalDate fechaFin) {
			this.fechaFin = fechaFin;
		}
		public LocalDate getFechaInicio() {
			return fechaInicio;
		}
		public void setFechaInicio(LocalDate fechaInicio) {
			this.fechaInicio = fechaInicio;
		}
		public double getPrecio() {
			return precio;
		}
		public void setPrecio(double precio) {
			this.precio = precio;
		}
		@Override
		public boolean esFiltroValido() {
			// TODO Auto-generated method stub
			return (!(getFechaInicio()== null)&&
					!(getFechaFin() == null) &&
					(getFechaInicio().isBefore(getFechaFin()))&&
					(getPrecio()>= 0d)
					);
		}

	}

