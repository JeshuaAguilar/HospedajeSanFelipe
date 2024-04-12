package com.hospedajesanfelipe.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.hospedajesanfelipe.entity.ComentarioEntity;
import com.hospedajesanfelipe.repository.ComentariosRepository;

@Component
public class ComentariosDao {
	
	@Autowired
	ComentariosRepository comentariosRepository;
	
	public List<ComentarioEntity> getAllComentarios() {
		return comentariosRepository.findAll();
	}
}
