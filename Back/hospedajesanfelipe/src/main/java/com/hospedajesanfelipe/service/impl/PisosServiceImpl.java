package com.hospedajesanfelipe.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hospedajesanfelipe.dao.PisosDao;

import com.hospedajesanfelipe.entity.CatPisoEntity;
import com.hospedajesanfelipe.service.PisosService;

@Service
public class PisosServiceImpl implements PisosService {
	
	@Autowired
	PisosDao pisoDao;
	
	@Override
	public List<CatPisoEntity> getAllPiso() {
		return pisoDao.getAllPiso();
	}
}
