package com.hospedajesanfelipe.request;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public class ReservacionRequest implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long idReservacion;
	private LocalDate fechaEntrada;
	private LocalDate fechaSalida;
	private int noPersonas;
	private int noPersonaExtra;
	private BigDecimal total;
	private Long estado;
	private Long cliente;
	private Long empleado;
    private String comentario;
	private Long precioEspecial;
	private List<HabitacionRequest> habitaciones;
	
	public Long getIdReservacion() {
		return idReservacion;
	}
	public void setIdReservacion(Long idReservacion) {
		this.idReservacion = idReservacion;
	}
	public LocalDate getFechaEntrada() {
		return fechaEntrada;
	}
	public void setFechaEntrada(LocalDate fechaEntrada) {
		this.fechaEntrada = fechaEntrada;
	}
	public LocalDate getFechaSalida() {
		return fechaSalida;
	}
	public void setFechaSalida(LocalDate fechaSalida) {
		this.fechaSalida = fechaSalida;
	}
	public int getNoPersonas() {
		return noPersonas;
	}
	public void setNoPersonas(int noPersonas) {
		this.noPersonas = noPersonas;
	}
	public int getNoPersonaExtra() {
		return noPersonaExtra;
	}
	public void setNoPersonaExtra(int noPersonaExtra) {
		this.noPersonaExtra = noPersonaExtra;
	}
	public BigDecimal getTotal() {
		return total;
	}
	public void setTotal(BigDecimal total) {
		this.total = total;
	}
	public Long getEstado() {
		return estado;
	}
	public void setEstado(Long estado) {
		this.estado = estado;
	}
	public Long getCliente() {
		return cliente;
	}
	public void setCliente(Long cliente) {
		this.cliente = cliente;
	}
	public Long getEmpleado() {
		return empleado;
	}
	public void setEmpleado(Long empleado) {
		this.empleado = empleado;
	}
	public String getComentario() {
		return comentario;
	}
	public void setComentario(String comentario) {
		this.comentario = comentario;
	}
	public Long getPrecioEspecial() {
		return precioEspecial;
	}
	public void setPrecioEspecial(Long precioEspecial) {
		this.precioEspecial = precioEspecial;
	}
	public List<HabitacionRequest> getHabitaciones() {
		return habitaciones;
	}
	public void setHabitaciones(List<HabitacionRequest> habitaciones) {
		this.habitaciones = habitaciones;
	}
}
