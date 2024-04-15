package com.hospedajesanfelipe.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "habitaciones")
public class HabitacionEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_habitacion")
	private Long idHabitacion;
	@Column(name = "no_habitacion")
	private String noHabitacion;
	@Column(name = "no_ocupantes")
	private int noOcupantes;
	@Column(name = "no_max_ocupantes")
	private int noMaxOcupante;
	@ManyToOne
	@JoinColumn(name = "fk_piso", insertable = false, updatable = false)
	private CatPisoEntity piso;
	@ManyToOne
	@JoinColumn(name = "fk_estado")
	private CatEstadoHabitacionEntity estado;
	@Column(name = "url_foto")
	private String urlFoto;
	@ManyToMany()
    @JoinTable(
            name = "habitaciones_servicios",
            joinColumns = @JoinColumn(name = "id_habitacion_servicio"),
            inverseJoinColumns = @JoinColumn(name = "id_servicio")
    )
    private List<CatServicioEntity> servicios;
	@ManyToMany(mappedBy = "habitaciones")
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
