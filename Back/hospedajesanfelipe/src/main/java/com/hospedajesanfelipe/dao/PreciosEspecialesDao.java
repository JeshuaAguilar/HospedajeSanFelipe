package com.hospedajesanfelipe.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.hospedajesanfelipe.entity.CatPrecioEspecialEntity;
import com.hospedajesanfelipe.repository.PreciosEspecialesRepository;

@Component
public class PreciosEspecialesDao {
	@Autowired
	PreciosEspecialesRepository preciosespecialesRepository;
	
	public List<CatPrecioEspecialEntity> getAllPreciosEspeciales() {
		return preciosespecialesRepository.findAll();
	}
}
