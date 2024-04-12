package com.hospedajesanfelipe.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.hospedajesanfelipe.entity.CatRolEntity;
import com.hospedajesanfelipe.repository.RolesRepository;

@Component
public class RolesDao {
	@Autowired
	RolesRepository rolesRepository;
	
	public List<CatRolEntity> getAllRoles() {
		return rolesRepository.findAll();
	}
}
