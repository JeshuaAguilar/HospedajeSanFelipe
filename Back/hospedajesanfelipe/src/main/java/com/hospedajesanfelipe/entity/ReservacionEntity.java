package com.hospedajesanfelipe.entity;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "reservaciones")
public class ReservacionEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_reservacion")
	private Long idReservacion;
	@Column(name = "fecha_entrada")
	private Date fechaEntrada;
	@Column(name = "fecha_salida")
	private Date fechaSalida;
	@Column(name = "no_personas")
	private int noPersonas;
	@Column(name = "no_personas_extra")
	private int noPersonaExtra;
	@Column(name = "total")
	private BigDecimal total;
	@Column(name = "fk_id_estado")
	private Long fkIdEstado;
	@Column(name = "fk_id_habitacion")
	private Long fkIdHabitacion;
	@Column(name = "fk_id_cliente")
	private Long fkIdCliente;
	@Column(name = "fk_id_empleado")
	private Long fkIdEmpleado;
	@Column(name = "fk_id_comentario")
	private Long fkIdComentario;
	
	
	public Long getIdReservacion() {
		return idReservacion;
	}
	public void setIdReservacion(Long idReservacion) {
		this.idReservacion = idReservacion;
	}
	public Date getFechaEntrada() {
		return fechaEntrada;
	}
	public void setFechaEntrada(Date fechaEntrada) {
		this.fechaEntrada = fechaEntrada;
	}
	public Date getFechaSalida() {
		return fechaSalida;
	}
	public void setFechaSalida(Date fechaSalida) {
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
	public Long getFkIdEstado() {
		return fkIdEstado;
	}
	public void setFkIdEstado(Long fkIdEstado) {
		this.fkIdEstado = fkIdEstado;
	}
	public Long getFkIdHabitacion() {
		return fkIdHabitacion;
	}
	public void setFkIdHabitacion(Long fkIdHabitacion) {
		this.fkIdHabitacion = fkIdHabitacion;
	}
	public Long getFkIdCliente() {
		return fkIdCliente;
	}
	public void setFkIdCliente(Long fkIdCliente) {
		this.fkIdCliente = fkIdCliente;
	}
	public Long getFkIdEmpleado() {
		return fkIdEmpleado;
	}
	public void setFkIdEmpleado(Long fkIdEmpleado) {
		this.fkIdEmpleado = fkIdEmpleado;
	}
	public Long getFkIdComentario() {
		return fkIdComentario;
	}
	public void setFkIdComentario(Long fkIdComentario) {
		this.fkIdComentario = fkIdComentario;
	}
}
