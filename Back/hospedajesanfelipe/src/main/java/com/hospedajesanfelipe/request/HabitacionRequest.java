package com.hospedajesanfelipe.request;

import java.io.Serializable;
import java.math.BigDecimal;

public class HabitacionRequest implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long idHabitacion;
	private String noHabitacion;
	private int noOcupante;
	private int noMaxOcupante;
	private int noCamasIndividuales;
	private int noCamasMatrimoniales;
	private BigDecimal costo;
	private String urlFoto;
    private Long piso;
	private Long estado;
	
	public Long getIdHabitacion() {
		return idHabitacion;
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
	
    
	
}
