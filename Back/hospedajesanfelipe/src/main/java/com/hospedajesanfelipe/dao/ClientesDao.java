package com.hospedajesanfelipe.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.hospedajesanfelipe.entity.ClienteEntity;
import com.hospedajesanfelipe.repository.ClientesRepository;

@Component
public class ClientesDao {
	
	@Autowired
	ClientesRepository clientesRepository;
	
	public List<ClienteEntity> getAllClientes() {
		return clientesRepository.findAll();
	}
}
