package com.hospedajesanfelipe.entity;

import java.math.BigDecimal;
import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "reservaciones")
public class ReservacionEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_reservacion")
	private Long idReservacion;
	@Column(name = "fecha_entrada")
	private LocalDate fechaEntrada;
	@Column(name = "fecha_salida")
	private LocalDate fechaSalida;
	@Column(name = "no_personas")
	private int noPersonas;
	@Column(name = "no_personas_extra")
	private Integer noPersonaExtra;
	@Column(name = "total")
	private BigDecimal total;
	@ManyToOne
    @JoinColumn(name = "fk_id_estado")
	private CatEstadoHabitacionEntity estado;
	@ManyToOne
    @JoinColumn(name = "fk_id_cliente")
	private ClienteEntity cliente;
	@ManyToOne
    @JoinColumn(name = "fk_id_empleado")
	private EmpleadoEntity empleado;
	@OneToOne
	@JoinColumn(name = "fk_id_comentario", nullable = true)
    private ComentarioEntity comentario;
	@ManyToOne
    @JoinColumn(name = "fk_id_precio_especial")
	private CatPrecioEspecialEntity precioEspecial;
	
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
	public CatEstadoHabitacionEntity getEstado() {
		return estado;
	}
	public void setEstado(CatEstadoHabitacionEntity estado) {
		this.estado = estado;
	}
	public ClienteEntity getCliente() {
		return cliente;
	}
	public void setCliente(ClienteEntity cliente) {
		this.cliente = cliente;
	}
	public EmpleadoEntity getEmpleado() {
		return empleado;
	}
	public void setEmpleado(EmpleadoEntity empleado) {
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
