package com.hospedajesanfelipe.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "cat_servicios")
public class CatServicioEntity {

	@Id
	@Column(name = "idServicio")
	private Long idServicio;
	@Column(name = "descripcion")
	private String descripcion;
	@ManyToMany(mappedBy = "servicios")
	@JsonIgnore
	private List<HabitacionEntity> habitaciones;
	
	public Long getIdServicio() {
		return idServicio;
	}
	public void setIdServicio(Long idServicio) {
		this.idServicio = idServicio;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public List<HabitacionEntity> getHabitaciones() {
		return habitaciones;
	}
	public void setHabitaciones(List<HabitacionEntity> habitaciones) {
		this.habitaciones = habitaciones;
	}
}
