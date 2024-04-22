package com.hospedajesanfelipe.service;

import java.util.List;

import com.hospedajesanfelipe.entity.ReservacionEntity;
import com.hospedajesanfelipe.request.ReservacionRequest;
import com.hospedajesanfelipe.response.ReservacionResponse;

public interface ReservacionesService {
	public List<ReservacionResponse> getAllReservaciones();
	public ReservacionEntity getReservacion(Long idReservacion);
	public ReservacionResponse saveReservaciones(ReservacionRequest reservacion);
	public ReservacionResponse updateReservaciones(ReservacionRequest reservacionRequest);
	public void deleteReservaciones(Long idReservacion);
}
