package com.hospedajesanfelipe.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hospedajesanfelipe.dao.RolesDao;
import com.hospedajesanfelipe.entity.CatRolEntity;
import com.hospedajesanfelipe.service.RolesService;

@Service
public class RolesServiceImpl implements RolesService {
	
	@Autowired
	RolesDao rolesDao;
	
	@Override
	public List<CatRolEntity> getAllRoles() {
		return rolesDao.getAllRoles();
	}
}

