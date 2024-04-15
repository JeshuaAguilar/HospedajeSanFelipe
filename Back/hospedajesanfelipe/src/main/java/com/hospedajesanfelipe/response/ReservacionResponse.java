package com.hospedajesanfelipe.response;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import com.hospedajesanfelipe.entity.CatPrecioEspecialEntity;
import com.hospedajesanfelipe.entity.ClienteEntity;
import com.hospedajesanfelipe.entity.ComentarioEntity;
import com.hospedajesanfelipe.request.EmpleadoRequest;

public class ReservacionResponse implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long idReservacion;
	private Date fechaEntrada;
	private Date fechaSalida;
	private int noPersonas;
	private int noPersonaExtra;
	private BigDecimal total;
	private String estado;
	private ClienteEntity cliente;
	private EmpleadoRequest empleado;
	private ComentarioEntity comentario;
	private CatPrecioEspecialEntity precioEspecial;
	
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
	public String getEstado() {
		return estado;
	}
	public void setEstado(String estado) {
		this.estado = estado;
	}
	public ClienteEntity getCliente() {
		return cliente;
	}
	public void setCliente(ClienteEntity cliente) {
		this.cliente = cliente;
	}
	public EmpleadoRequest getEmpleado() {
		return empleado;
	}
	public void setEmpleado(EmpleadoRequest empleado) {
		this.empleado = empleado;
	}
	public ComentarioEntity getComentario() {
		return comentario;
	}
	public void setComentario(ComentarioEntity comentario) {
		this.comentario = comentario;
	}
	public CatPrecioEspecialEntity getPrecioEspecial() {
		return precioEspecial;
	}
	public void setPrecioEspecial(CatPrecioEspecialEntity precioEspecial) {
		this.precioEspecial = precioEspecial;
	}
}
