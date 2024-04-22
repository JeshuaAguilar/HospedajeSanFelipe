package com.hospedajesanfelipe.vo;

import java.io.Serializable;

public class ReservacionHabitacionIdVO implements Serializable {
    
	private static final long serialVersionUID = 1L;
	
	private Long reservacion;
    private Long habitacion;

    public Long getReservacion() {
		return reservacion;
	}
	public void setReservacion(Long reservacion) {
		this.reservacion = reservacion;
	}
	public Long getHabitacion() {
		return habitacion;
	}
	public void setHabitacion(Long habitacion) {
		this.habitacion = habitacion;
	}
}
