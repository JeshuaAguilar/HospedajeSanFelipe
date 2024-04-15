package com.hospedajesanfelipe.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.hospedajesanfelipe.entity.ReservacionEntity;
import com.hospedajesanfelipe.repository.ReservacionesRepository;


@Component
public class ReservacionesDao {
	
	@Autowired
	ReservacionesRepository reservacionesRepository;
	
	public List<ReservacionEntity> getAllReservaciones() {
		return reservacionesRepository.findAll();
	}

	public Optional<ReservacionEntity> getReservacion(Long idReservacion) {
		return reservacionesRepository.findById(idReservacion);
	}
	
	public ReservacionEntity saveReservaciones(ReservacionEntity reservacion) {
		return reservacionesRepository.save(reservacion);
	}
	
	public ReservacionEntity updateReservaciones(ReservacionEntity reservacion) {
		return reservacionesRepository.save(reservacion);
	}
	
	public void deleteReservaciones(Long idReservacion) {
		reservacionesRepository.deleteById(null);
	}
}
