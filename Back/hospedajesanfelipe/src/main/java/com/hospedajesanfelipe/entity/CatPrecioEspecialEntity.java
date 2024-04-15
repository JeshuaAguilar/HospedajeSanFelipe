package com.hospedajesanfelipe.entity;

import java.math.BigDecimal;
import java.util.Date;

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
	private Date fechaInicio;
	@Column(name = "fecha_fin")
	private Date fechaFin;
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
	public Date getFechaInicio() {
		return fechaInicio;
	}
	public void setFechaInicio(Date fechaInicio) {
		this.fechaInicio = fechaInicio;
	}
	public Date getFechaFin() {
		return fechaFin;
	}
	public void setFechaFin(Date fechaFin) {
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
