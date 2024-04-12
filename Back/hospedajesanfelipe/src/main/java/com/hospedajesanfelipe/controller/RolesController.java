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

import com.hospedajesanfelipe.entity.CatRolEntity;
import com.hospedajesanfelipe.service.RolesService;

@RestController
@RequestMapping("/hospedaje/api/rol")
public class RolesController {

	@Autowired
	RolesService rolesService;
	
	@GetMapping()
	public List<CatRolEntity> getAllRoles() {
		return rolesService.getAllRoles();
	}
	
	@PostMapping()
	public CatRolEntity createRoles(@RequestBody CatRolEntity roles) {
//		return repository.save(comentario);
		return null;
	}
	
	@PutMapping("/{idRol}")
	public CatRolEntity updateRol(@PathVariable int idRol ,@RequestBody CatRolEntity roles) {
//		return repository.save(person);
		return null;
	}
	
	@DeleteMapping("/{idRol}")
	public void deleteRol(@PathVariable("idRol") Long idRol) {
//		repository.deleteById(id);
	}
}
