package com.hospedajesanfelipe.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.hospedajesanfelipe.entity.CatEstadoHabitacionEntity;
import com.hospedajesanfelipe.repository.EstadoHabitacionesRepository;

@Component
public class EstadoHabitacionesDao {
	
	@Autowired
	EstadoHabitacionesRepository estadoHabitacionesRepository;
	
	public List<CatEstadoHabitacionEntity> getAllEstadoHabitaciones() {
		return estadoHabitacionesRepository.findAll();
	}
}
