package com.hospedajesanfelipe.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hospedajesanfelipe.dao.ReservacionesDao;
import com.hospedajesanfelipe.entity.ReservacionEntity;
import com.hospedajesanfelipe.service.ReservacionesService;

@Service
public class ReservacionesServiceImpl implements ReservacionesService{
	
	@Autowired
	ReservacionesDao reservacionesDao;
	
	@Override
	public List<ReservacionEntity> getAllReservaciones() {
		return reservacionesDao.getAllReservaciones();
	}
}
