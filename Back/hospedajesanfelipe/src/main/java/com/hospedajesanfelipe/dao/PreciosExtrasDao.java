package com.hospedajesanfelipe.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.hospedajesanfelipe.entity.CatPrecioExtraEntity;
import com.hospedajesanfelipe.repository.PreciosExtrasRepository;

@Component
public class PreciosExtrasDao {
	@Autowired
	PreciosExtrasRepository preciosExtrasRepository;
	
	public List<CatPrecioExtraEntity> getAllPreciosExtras() {
		return preciosExtrasRepository.findAll();
	}
}

