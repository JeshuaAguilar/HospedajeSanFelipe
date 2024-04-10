package com.hospedajesanfelipe.controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/hospedaje/api/administradores")
public class EmpleadoController {
	@GetMapping()
	public List<EmpleadoController> allAdministradores(){
//		return repository.findAll();
		return null;
	}
	
	
	@PostMapping()
	public EmpleadoController createAdministrador(@RequestBody EmpleadoController administrador) {
//		return repository.save(administrador);
		return null;
	}
	
	@PutMapping()
	public EmpleadoController updateAdministrador(@PathVariable int id ,@RequestBody EmpleadoController administrador) {
//		return repository.save(person);
		return null;
	}
	
	@DeleteMapping()
	public void deleteAdministrador(@PathVariable("id") Long id) {
//		repository.deleteById(id);
	}
}
