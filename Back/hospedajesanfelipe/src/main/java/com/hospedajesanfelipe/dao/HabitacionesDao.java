package com.hospedajesanfelipe.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.hospedajesanfelipe.entity.HabitacionEntity;
import com.hospedajesanfelipe.repository.HabitacionesRepository;

@Component
public class HabitacionesDao {
	
	@Autowired
	HabitacionesRepository habitacionesRepository;

	public List<HabitacionEntity> getAllHabitaciones() {
		return habitacionesRepository.findAll();
	}
}
