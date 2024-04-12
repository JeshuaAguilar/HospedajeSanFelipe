package com.hospedajesanfelipe.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.hospedajesanfelipe.dao.PreciosEspecialesDao;
import com.hospedajesanfelipe.entity.CatPrecioEspecialEntity;
import com.hospedajesanfelipe.service.PreciosEspecialesService;

@Service
public class PreciosEspecialesServiceImpl implements PreciosEspecialesService {

	@Autowired
	PreciosEspecialesDao preciosespecialesDao;
	
	@Override
	public List<CatPrecioEspecialEntity> getAllPreciosEspeciales() {
		return preciosespecialesDao.getAllPreciosEspeciales();
	}
}
