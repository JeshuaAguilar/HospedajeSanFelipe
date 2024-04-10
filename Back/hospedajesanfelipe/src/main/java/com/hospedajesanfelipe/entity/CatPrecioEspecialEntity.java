package com.hospedajesanfelipe.entity;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "cat_precios_especiales")
public class CatPrecioEspecialEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_especiales")
	private Long idEspecial;
	@Column(name = "fecha_inicio")
	private Date fecha_inicio;
	@Column(name = "fecha_fin")
	private Date fecha_fin;
	@Column(name = "descripcion")
	private String descripcion;
	
	public Long getIdEspecial() {
		return idEspecial;
	}
	public void setIdEspecial(Long idEspecial) {
		this.idEspecial = idEspecial;
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
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
}
