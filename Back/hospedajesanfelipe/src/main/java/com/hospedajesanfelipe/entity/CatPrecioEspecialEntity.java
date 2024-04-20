package com.hospedajesanfelipe.entity;

import java.math.BigDecimal;
import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "cat_precios_especiales")
public class CatPrecioEspecialEntity {
	
	@Id
	@Column(name = "id_precio_especial")
	private Long idPrecioEspecial;
	@Column(name = "fecha_inicio")
	private LocalDate fechaInicio;
	@Column(name = "fecha_fin")
	private LocalDate fechaFin;
	@Column(name = "precio")
	private BigDecimal precio;
	@Column(name = "descripcion")
	private String descripcion;
	
	public Long getIdPrecioEspecial() {
		return idPrecioEspecial;
	}
	public void setIdPrecioEspecial(Long idPrecioEspecial) {
		this.idPrecioEspecial = idPrecioEspecial;
	}
	public LocalDate getFechaInicio() {
		return fechaInicio;
	}
	public void setFechaInicio(LocalDate fechaInicio) {
		this.fechaInicio = fechaInicio;
	}
	public LocalDate getFechaFin() {
		return fechaFin;
	}
	public void setFechaFin(LocalDate fechaFin) {
		this.fechaFin = fechaFin;
	}
	public BigDecimal getPrecio() {
		return precio;
	}
	public void setPrecio(BigDecimal precio) {
		this.precio = precio;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
}
