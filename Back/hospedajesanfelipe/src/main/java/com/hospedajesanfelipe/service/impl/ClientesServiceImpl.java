package com.hospedajesanfelipe.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import com.hospedajesanfelipe.dao.ClientesDao;
import com.hospedajesanfelipe.entity.ClienteEntity;
import com.hospedajesanfelipe.request.ClienteRequest;
import com.hospedajesanfelipe.response.ClienteResponse;
import com.hospedajesanfelipe.service.ClientesService;

@Service
public class ClientesServiceImpl implements ClientesService {
	
	@Autowired
	ClientesDao clientesDao;
	
	@Override
	public ClienteEntity getClienteByNombre(String nombre) {
		Optional<ClienteEntity> entity = clientesDao.getClienteByNombre(nombre);
		
		if (entity.isPresent()) {
			return entity.get();
		} else {
			return null;
		}
	}
	
	@Override
	public List<ClienteResponse> getAllClientes() {
		List<ClienteResponse> response = null;
		List<ClienteEntity> clientes = clientesDao.getAllClientes();
		
		if (clientes != null && !clientes.isEmpty()) {
			response = new ArrayList<ClienteResponse>();
			for (ClienteEntity clienteEntity : clientes) {
				ClienteResponse cliente = new ClienteResponse();
				cliente = mapperRclienteResponse(clienteEntity);
				response.add(cliente);
			}
			
		}
		return response;
	}
	
	@Override
	public ClienteEntity getClienteById(Long idCliente) {
		Optional<ClienteEntity> clienteEntity = clientesDao.getClienteById(idCliente); 
		
		if (clienteEntity.isPresent()) {
			return clienteEntity.get();
		} else {
			return null;
		}
	}
	
	@Override
	public ClienteEntity createCliente(ClienteRequest cliente) {
		/*
		 * Se crea un método mapper, lo que hace este método es convertir el objeto EmpleadoRequest a empleado Entity
		 * El empleado request es la información que manda el front al back y el back pasa esos datos para que el entity 
		 * pueda insertar el nuevo empleado
		 */
		ClienteEntity clienteEntity = mapperCliente(cliente);
		/*
		 * Una vez mapeado el empleado Request con el empleado Entity, podemos mandar a insertar el entity en la base de datos
		 */
		return clientesDao.createCliente(clienteEntity);
	}
	
	@Override
	public ClienteEntity updateCliente(ClienteRequest cliente) {
		/*
		 * También hacemos el mismo mapper, la única diferencia con el método anterior, es que aquí
		 * El EmpleadoRequest sí va a tener un id, todo los demás es lo mismo
		 */
		ClienteEntity clienteEntity = mapperCliente(cliente);
		return clientesDao.createCliente(clienteEntity);
	}
	
	@Override
	public void deleteCliente(Long idCliente) {
		clientesDao.deleteCliente(idCliente);
	}
	
	private ClienteEntity mapperCliente(ClienteRequest cliente) {
		ClienteEntity clienteEntity = null;

		/*
		 * Validamos que el objeto EmpleadoRequest traida un id, si trae un id, quiere decir que el empleado ya existe y hay que actualizarlo
		 */
		if (cliente.getIdCliente() != null && cliente.getIdCliente() > 0) {
			/*
			 * Si cuenta con un id buscamos primero el empleado en la base de datos con el método de getEmpleadoById()
			 */
			clienteEntity = getClienteById(cliente.getIdCliente());
		} else {
			/*
			 * Si el empleado no trae un id, quiere decir que es un nuevo empleado, y creamos una nueva instancia vacía de Empleado entity
			 * Para poder guardarlo en la base de datos
			 */
			clienteEntity = new ClienteEntity();
		}
		
		clienteEntity.setIdCliente(cliente.getIdCliente());
		clienteEntity.setNombre(validaNull(cliente.getNombre(), clienteEntity.getNombre()));
		clienteEntity.setPrimerApellido(validaNull(cliente.getPrimerApellido(), clienteEntity.getPrimerApellido()));
		clienteEntity.setSegundoApellido(validaNull(cliente.getSegundoApellido(), clienteEntity.getSegundoApellido()));
		clienteEntity.setTelefono(validaNull(cliente.getTelefono(), clienteEntity.getTelefono()));
		clienteEntity.setEstado(validaNull(cliente.getEstado(), clienteEntity.getEstado()));
		clienteEntity.setMunicipio(validaNull(cliente.getMunicipio(), clienteEntity.getMunicipio()));
		
		return clienteEntity;
	}
	
	private ClienteResponse mapperRclienteResponse(ClienteEntity cliente) {
		ClienteResponse response = new ClienteResponse();
		
		response.setIdCliente(cliente.getIdCliente());
		response.setNombre(cliente.getNombre());
		response.setPrimerApellido(cliente.getPrimerApellido());
		response.setSegundoApellido(cliente.getSegundoApellido());
		response.setTelefono(cliente.getTelefono());
		response.setEstado(cliente.getEstado());
		response.setMunicipio(cliente.getMunicipio());
		
		return response;
	}
	
	private String validaNull(String parametro, String actual) {
		if (parametro != null && !parametro.isBlank()) {
			return parametro;
		} else {
			return actual;
		}
	}
		
}
