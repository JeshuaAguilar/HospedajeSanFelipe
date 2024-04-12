package com.hospedajesanfelipe.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


import com.hospedajesanfelipe.entity.CatPisoEntity;

import com.hospedajesanfelipe.repository.PisosRepository;

@Component
public class PisosDao {
	
	@Autowired
	PisosRepository pisoRepository;
	
	public List<CatPisoEntity> getAllPiso() {
		return pisoRepository.findAll();
	}
}
