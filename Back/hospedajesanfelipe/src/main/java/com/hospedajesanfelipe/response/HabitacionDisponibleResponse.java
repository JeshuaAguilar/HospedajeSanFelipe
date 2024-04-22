package com.hospedajesanfelipe.response;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

import com.hospedajesanfelipe.entity.CatServicioEntity;

public class HabitacionDisponibleResponse implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Long idHabitacion;
	private String noHabitacion;
	private int noOcupante;
	private int noMaxOcupante;
	private int noMaxExtras;
	private int noCamasIndividuales;
	private int noCamasMatrimoniales;
	private BigDecimal costo;
	private String piso;
	private List<CatServicioEntity> servicios;
	
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
	public int getNoMaxExtras() {
		return noMaxExtras;
	}
	public void setNoMaxExtras(int noMaxExtras) {
		this.noMaxExtras = noMaxExtras;
	}
	public int getNoCamasIndividuales() {
		return noCamasIndividuales;
	}
	public void setNoCamasIndividuales(int noCamasIndividuales) {
		this.noCamasIndividuales = noCamasIndividuales;
	}
	public int getNoCamasMatrimoniales() {
		return noCamasMatrimoniales;
	}
	public void setNoCamasMatrimoniales(int noCamasMatrimoniales) {
		this.noCamasMatrimoniales = noCamasMatrimoniales;
	}
	public BigDecimal getCosto() {
		return costo;
	}
	public void setCosto(BigDecimal costo) {
		this.costo = costo;
	}
	public String getPiso() {
		return piso;
	}
	public void setPiso(String piso) {
		this.piso = piso;
	}
	public List<CatServicioEntity> getServicios() {
		return servicios;
	}
	public void setServicios(List<CatServicioEntity> servicios) {
		this.servicios = servicios;
	}
}
