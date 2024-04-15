package com.hospedajesanfelipe.request;

import java.io.Serializable;
import java.util.List;

import com.hospedajesanfelipe.entity.CatServicioEntity;

public class HabitacionRequest implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long idHabitacion;
	private String noHabitacion;
	private int noOcupantes;
	private int noMaxOcupante;
	private Long piso;
	private Long estado;
	private String urlFoto;
    private List<CatServicioEntity> servicios;
    private List<ReservacionRequest> reservaciones;
    
	public Long getIdHabitacion() {
		return idHabitacion;
	}
	public void setIdHabitacion(Long idHabitacion) {
		this.idHabitacion = idHabitacion;
	}
	public String getNoHabitacion() {
		return noHabitacion;
	}
	public void setNoHabitacion(String noHabitacion) {
		this.noHabitacion = noHabitacion;
	}
	public int getNoOcupantes() {
		return noOcupantes;
	}
	public void setNoOcupantes(int noOcupantes) {
		this.noOcupantes = noOcupantes;
	}
	public int getNoMaxOcupante() {
		return noMaxOcupante;
	}
	public void setNoMaxOcupante(int noMaxOcupante) {
		this.noMaxOcupante = noMaxOcupante;
	}
	public Long getPiso() {
		return piso;
	}
	public void setPiso(Long piso) {
		this.piso = piso;
	}
	public Long getEstado() {
		return estado;
	}
	public void setEstado(Long estado) {
		this.estado = estado;
	}
	public String getUrlFoto() {
		return urlFoto;
	}
	public void setUrlFoto(String urlFoto) {
		this.urlFoto = urlFoto;
	}
	public List<CatServicioEntity> getServicios() {
		return servicios;
	}
	public void setServicios(List<CatServicioEntity> servicios) {
		this.servicios = servicios;
	}
	public List<ReservacionRequest> getReservaciones() {
		return reservaciones;
	}
	public void setReservaciones(List<ReservacionRequest> reservaciones) {
		this.reservaciones = reservaciones;
	}
}
