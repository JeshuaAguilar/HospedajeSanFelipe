package com.hospedajesanfelipe.service;

import java.util.List;

import com.hospedajesanfelipe.entity.ClienteEntity;
import com.hospedajesanfelipe.request.ClienteRequest;
import com.hospedajesanfelipe.response.ClienteResponse;



public interface ClientesService {
	
	public List<ClienteResponse> getClienteByNombreApellido(String nombre, String apellido);
	
	public List<ClienteResponse> getAllClientes();
	
	public ClienteEntity getClienteById(Long idCliente);
	
	public ClienteEntity createCliente(ClienteRequest cliente);
	
	public ClienteEntity updateCliente(ClienteRequest cliente);
	
	public void deleteCliente(Long idCliente);

	
}
