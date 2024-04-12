package com.hospedajesanfelipe.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.hospedajesanfelipe.dao.ComentariosDao;
import com.hospedajesanfelipe.entity.ComentarioEntity;
import com.hospedajesanfelipe.service.ComentariosService;

@Service
public class ComentariosServiceImpl implements ComentariosService{
	
	@Autowired
	ComentariosDao comentariosDao;
	
	@Override
	public List<ComentarioEntity> getAllComentarios() {
		return comentariosDao.getAllComentarios();
	}
}
