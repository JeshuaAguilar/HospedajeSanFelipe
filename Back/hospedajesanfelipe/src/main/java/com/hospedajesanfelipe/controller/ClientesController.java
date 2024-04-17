package com.hospedajesanfelipe.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hospedajesanfelipe.entity.ClienteEntity;
import com.hospedajesanfelipe.entity.EmpleadoEntity;
import com.hospedajesanfelipe.request.ClienteRequest;
import com.hospedajesanfelipe.request.EmpleadoRequest;
import com.hospedajesanfelipe.response.ClienteResponse;
import com.hospedajesanfelipe.service.ClientesService;

@RestController
@RequestMapping("/hospedaje/api/clientes")
public class ClientesController {
	
	@Autowired
	ClientesService clientesService;
	
	@GetMapping()
	public List<ClienteResponse> getAllClientes() {
		return clientesService.getAllClientes();
	}
	
	@PostMapping()
	public ResponseEntity<String> createClientes(@RequestBody ClienteRequest cliente) {
		ClienteEntity response = clientesService.createCliente(cliente); 
		
		if (response != null) {
			return ResponseEntity.status(HttpStatus.CREATED).body("El cliente se ha creado exitosamente");
		} else {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Ocurrió un error al crear el cliente");
		}
	}
	
	
	@PutMapping()
	public ResponseEntity<String> updateCliente(@RequestBody ClienteRequest cliente) {
		
		ClienteEntity response = clientesService.updateCliente(cliente); 
		
		if (response != null) {
			return ResponseEntity.status(HttpStatus.CREATED).body("El cliente se ha modificado exitosamente");
		} else {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Ocurrió un error al modificar el cliente");
		}
	}
	
	
	
	
	@DeleteMapping("/{idCliente}")
	public ResponseEntity<String> deleteCliente(@PathVariable("idCliente") Long idCliente) {
		clientesService.deleteCliente(idCliente);
		return ResponseEntity.status(HttpStatus.OK).body("El cliente se ha eliminado exitosamente");
	}
}
