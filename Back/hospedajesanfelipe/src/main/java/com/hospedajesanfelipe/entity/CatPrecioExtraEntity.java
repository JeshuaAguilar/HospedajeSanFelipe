package com.hospedajesanfelipe.entity;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "cat_precios_extra")
public class CatPrecioExtraEntity {
	
	@Id
	@Column(name = "id_precio")
	private	Long idPrecio;
	@Column(name = "precio")
	private BigDecimal precio;
	@Column(name = "descripcion")
	private String descripcion;
	
	public Long getIdPrecio() {
		return idPrecio;
	}
	public void setIdPrecio(Long idPrecio) {
		this.idPrecio = idPrecio;
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
