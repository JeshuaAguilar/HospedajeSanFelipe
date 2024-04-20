package com.hospedajesanfelipe.response;

import java.io.Serializable;
import java.math.BigDecimal;

import com.hospedajesanfelipe.entity.CatEstadoHabitacionEntity;
import com.hospedajesanfelipe.entity.CatPisoEntity;

public class HabitacionResponse implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private Long idHabitacion;
	private String noHabitacion;
	private int noOcupante;
	private int noMaxOcupante;
	private int noCamasIndividuales;
	private int noCamasMatrimoniales;
	private BigDecimal costo;
	private String urlFoto;
    private CatPisoEntity piso;
	private CatEstadoHabitacionEntity estado;
	
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
	public String getUrlFoto() {
		return urlFoto;
	}
	public void setUrlFoto(String urlFoto) {
		this.urlFoto = urlFoto;
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
	
	
}
