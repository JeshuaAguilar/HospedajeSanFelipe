package com.hospedajesanfelipe.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.hospedajesanfelipe.dao.PreciosExtrasDao;
import com.hospedajesanfelipe.entity.CatPrecioExtraEntity;
import com.hospedajesanfelipe.service.PreciosExtrasService;

@Service
public class PreciosExtrasServiceImpl implements PreciosExtrasService {
	@Autowired
	PreciosExtrasDao preciosExtrasDao;
	
	@Override
	public List<CatPrecioExtraEntity> getAllPreciosExtras() {
		return preciosExtrasDao.getAllPreciosExtras();
	}
}
