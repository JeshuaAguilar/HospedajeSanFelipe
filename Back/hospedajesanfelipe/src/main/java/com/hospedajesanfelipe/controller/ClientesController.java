package com.hospedajesanfelipe.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hospedajesanfelipe.entity.ClienteEntity;
import com.hospedajesanfelipe.service.ClientesService;

@RestController
@RequestMapping("/hospedaje/api/clientes")
public class ClientesController {
	
	@Autowired
	ClientesService clientesService;
	
	@GetMapping()
	public List<ClienteEntity> getAllClientes() {
		return clientesService.getAllClientes();
	}
	
	@PostMapping()
	public ClienteEntity createClientes(@RequestBody ClienteEntity roles) {
//		return repository.save(comentario);
		return null;
	}
	
	@PutMapping("/{idCliente}")
	public ClienteEntity updateCliente(@PathVariable int idCliente ,@RequestBody ClienteEntity clientes) {
//		return repository.save(person);
		return null;
	}
	
	@DeleteMapping("/{idCliente}")
	public void deleteCliente(@PathVariable("idCliente") Long idCliente) {
//		repository.deleteById(id);
	}
}
