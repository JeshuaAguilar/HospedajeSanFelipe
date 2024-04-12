package com.hospedajesanfelipe.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hospedajesanfelipe.dao.ServiciosDao;
import com.hospedajesanfelipe.entity.CatServicioEntity;
import com.hospedajesanfelipe.service.ServiciosService;

@Service
public class ServiciosServiceImpl implements ServiciosService{
	
	@Autowired
	ServiciosDao serviciosDao;
	
	@Override
	public List<CatServicioEntity> getAllServicios() {
		return serviciosDao.getAllServicios();
	}
}
