package com.hospedajesanfelipe.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hospedajesanfelipe.dao.ClientesDao;
import com.hospedajesanfelipe.entity.ClienteEntity;
import com.hospedajesanfelipe.service.ClientesService;

@Service
public class ClientesServiceImpl implements ClientesService {
	
	@Autowired
	ClientesDao clientesDao;
	
	@Override
	public List<ClienteEntity> getAllClientes() {
		return clientesDao.getAllClientes();
	}
}
