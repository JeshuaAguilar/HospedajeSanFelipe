package com.hospedajesanfelipe.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hospedajesanfelipe.dao.HabitacionesDao;
import com.hospedajesanfelipe.entity.HabitacionEntity;
import com.hospedajesanfelipe.service.HabitacionesService;

@Service
public class HabitacionesServiceImpl implements HabitacionesService {
	
	@Autowired
	HabitacionesDao habitacionesDao;

	@Override
	public List<HabitacionEntity> getAllHabitaciones() {
		return habitacionesDao.getAllHabitaciones();
	}

}