package com.hospedajesanfelipe.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.hospedajesanfelipe.entity.CatServicioEntity;
import com.hospedajesanfelipe.repository.ServiciosRepository;

@Component
public class ServiciosDao {
	
	@Autowired
	ServiciosRepository serviciosRepository;
	
	public List<CatServicioEntity> getAllServicios() {
		return serviciosRepository.findAll();
	}
	public List<CatServicioEntity> saveServicios() {
		return serviciosRepository.findAll();
	}
	public List<CatServicioEntity> updateServicios() {
		return serviciosRepository.findAll();
	}
	public List<CatServicioEntity> deleteServicios() {
		return serviciosRepository.findAll();
	}
}
