package com.hospedajesanfelipe.response;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

import com.hospedajesanfelipe.entity.CatEstadoHabitacionEntity;
import com.hospedajesanfelipe.entity.CatPisoEntity;
import com.hospedajesanfelipe.entity.CatServicioEntity;
import com.hospedajesanfelipe.entity.ReservacionEntity;

public class HabitacionEmpleadoResponse implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Long idHabitacion;
	private String noHabitacion;
	private int noOcupante;
	private int noMaxOcupante;
//	private int noCamasIndividuales;
//	private int noCamasMatrimoniales;
//	private BigDecimal costo;
	private CatPisoEntity piso;
	private CatEstadoHabitacionEntity estado;
	private String urlFoto;
    private List<CatServicioEntity> servicios;
    private List<ReservacionEntity> reservaciones;
    
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
	public int getNoOcupante() {
		return noOcupante;
	}
	public void setNoOcupante(int noOcupante) {
		this.noOcupante = noOcupante;
	}
	public int getNoMaxOcupante() {
		return noMaxOcupante;
	}
	public void setNoMaxOcupante(int noMaxOcupante) {
		this.noMaxOcupante = noMaxOcupante;
	}
	public CatPisoEntity getPiso() {
		return piso;
	}
	public void setPiso(CatPisoEntity piso) {
		this.piso = piso;
	}
	public CatEstadoHabitacionEntity getEstado() {
		return estado;
	}
	public void setEstado(CatEstadoHabitacionEntity estado) {
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
	public List<ReservacionEntity> getReservaciones() {
		return reservaciones;
	}
	public void setReservaciones(List<ReservacionEntity> reservaciones) {
		this.reservaciones = reservaciones;
	}
}
