package com.hospedajesanfelipe.dao;

import java.util.List;

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
}
