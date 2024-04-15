package com.hospedajesanfelipe.service;

import java.util.List;

import com.hospedajesanfelipe.entity.ReservacionEntity;
import com.hospedajesanfelipe.request.ReservacionRequest;

public interface ReservacionesService {
	public List<ReservacionEntity> getAllReservaciones();
	public ReservacionEntity getReservacion(Long idReservacion);
	public ReservacionEntity saveReservaciones(ReservacionRequest reservacion);
	public ReservacionEntity updateReservaciones(ReservacionRequest reservacion);
	public void deleteReservaciones(Long idReservacion);
}
