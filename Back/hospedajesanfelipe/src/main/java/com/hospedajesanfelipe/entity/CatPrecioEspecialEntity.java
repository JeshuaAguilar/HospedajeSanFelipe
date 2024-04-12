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
	private Date fecha_inicio;
	@Column(name = "fecha_fin")
	private Date fecha_fin;
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
	public Date getFecha_inicio() {
		return fecha_inicio;
	}
	public void setFecha_inicio(Date fecha_inicio) {
		this.fecha_inicio = fecha_inicio;
	}
	public Date getFecha_fin() {
		return fecha_fin;
	}
	public void setFecha_fin(Date fecha_fin) {
		this.fecha_fin = fecha_fin;
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
