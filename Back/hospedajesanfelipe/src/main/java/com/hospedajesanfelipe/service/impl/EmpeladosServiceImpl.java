package com.hospedajesanfelipe.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.hospedajesanfelipe.dao.EmpleadosDao;
import com.hospedajesanfelipe.entity.EmpleadoEntity;
import com.hospedajesanfelipe.service.EmpleadosService;

@Service
public class EmpeladosServiceImpl implements EmpleadosService {

	@Autowired
	EmpleadosDao empleadosDao;
	
	@Override
	public List<EmpleadoEntity> getAllEmpleados() {
		return empleadosDao.getAllEmpleados();
	}
}
