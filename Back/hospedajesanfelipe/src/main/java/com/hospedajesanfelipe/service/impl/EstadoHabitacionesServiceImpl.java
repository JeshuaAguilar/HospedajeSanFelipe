package com.hospedajesanfelipe.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hospedajesanfelipe.dao.EstadoHabitacionesDao;
import com.hospedajesanfelipe.entity.CatEstadoHabitacionEntity;
import com.hospedajesanfelipe.service.EstadoHabitacionesService;

@Service
public class EstadoHabitacionesServiceImpl implements EstadoHabitacionesService {
	
	@Autowired
	EstadoHabitacionesDao estadoHabitacionesDao;
	
	@Override
	public List<CatEstadoHabitacionEntity> getAllEstadoHabitaciones() {
		return estadoHabitacionesDao.getAllEstadoHabitaciones();
	}
}
