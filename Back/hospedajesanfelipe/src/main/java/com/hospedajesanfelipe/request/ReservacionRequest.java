package com.hospedajesanfelipe.request;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import com.hospedajesanfelipe.entity.HabitacionEntity;

public class ReservacionRequest implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long idReservacion;
	private LocalDate fechaEntrada;
	private LocalDate fechaSalida;
	private int noPersonas;
	private Integer noPersonaExtra;
	private BigDecimal total;
	private Long estado;
	private Long idCliente;
	private Long idEmpleado;
    private Long comentario;
	private Long precioEspecial;
	private List<HabitacionEntity> habitaciones;
	
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
	public Integer getNoPersonaExtra() {
		return noPersonaExtra;
	}
	public void setNoPersonaExtra(Integer noPersonaExtra) {
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
	public Long getIdCliente() {
		return idCliente;
	}
	public void setIdCliente(Long idCliente) {
		this.idCliente = idCliente;
	}
	public Long getIdEmpleado() {
		return idEmpleado;
	}
	public void setIdEmpleado(Long idEmpleado) {
		this.idEmpleado = idEmpleado;
	}
	public Long getComentario() {
		return comentario;
	}
	public void setComentario(Long comentario) {
		this.comentario = comentario;
	}
	public Long getPrecioEspecial() {
		return precioEspecial;
	}
	public void setPrecioEspecial(Long precioEspecial) {
		this.precioEspecial = precioEspecial;
	}
	public List<HabitacionEntity> getHabitaciones() {
		return habitaciones;
	}
	public void setHabitaciones(List<HabitacionEntity> habitaciones) {
		this.habitaciones = habitaciones;
	}
}
