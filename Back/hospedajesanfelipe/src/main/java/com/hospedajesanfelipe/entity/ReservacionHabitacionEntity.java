package com.hospedajesanfelipe.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.hospedajesanfelipe.vo.ReservacionHabitacionIdVO;

@Entity
@Table(name = "reservaciones_habitaciones")
@IdClass(ReservacionHabitacionIdVO.class)
public class ReservacionHabitacionEntity {

    @Id
    @ManyToOne
    @JoinColumn(name = "id_reservacion")
    private ReservacionEntity reservacion;
    @Id
    @ManyToOne
    @JoinColumn(name = "id_habitacion")
    private HabitacionEntity habitacion;

    @Column(name = "no_personas")
    private int noPersonas;

    @Column(name = "no_personas_extra")
    private int noPersonasExtra;

	public ReservacionEntity getReservacion() {
		return reservacion;
	}

	public void setReservacion(ReservacionEntity reservacion) {
		this.reservacion = reservacion;
	}

	public HabitacionEntity getHabitacion() {
		return habitacion;
	}

	public void setHabitacion(HabitacionEntity habitacion) {
		this.habitacion = habitacion;
	}

	public int getNoPersonas() {
		return noPersonas;
	}

	public void setNoPersonas(int noPersonas) {
		this.noPersonas = noPersonas;
	}

	public int getNoPersonasExtra() {
		return noPersonasExtra;
	}

	public void setNoPersonasExtra(int noPersonasExtra) {
		this.noPersonasExtra = noPersonasExtra;
	}
}

