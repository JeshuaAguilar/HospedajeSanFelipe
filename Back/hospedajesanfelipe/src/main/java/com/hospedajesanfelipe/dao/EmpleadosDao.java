package com.hospedajesanfelipe.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.hospedajesanfelipe.entity.EmpleadoEntity;
import com.hospedajesanfelipe.repository.EmpleadosRepository;

@Component
public class EmpleadosDao {
	
	@Autowired
	EmpleadosRepository empleadosRepository;
	
	public List<EmpleadoEntity> getAllEmpleados() {
		return empleadosRepository.findAll();
	}
}
