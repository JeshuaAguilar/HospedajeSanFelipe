package com.hospedajesanfelipe.entity;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "cat_estados_habitaciones")
public class CatEstadoHabitacionEntity {
	@Id
	@Column(name = "id_estado")
	private Long idEstado;
	@Column(name = "descripcion")
	private String descripcion;
	
	public Long getIdEstado() {
		return idEstado;
	}
	public void setIdEstado(Long idEstado) {
		this.idEstado = idEstado;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
}
