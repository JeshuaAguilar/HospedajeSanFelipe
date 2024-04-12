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

import com.hospedajesanfelipe.entity.CatPrecioEspecialEntity;
import com.hospedajesanfelipe.service.PreciosEspecialesService;

@RestController
@RequestMapping("/hospedaje/api/precioespecial")
public class PreciosEspecialesController {
	
	@Autowired
	PreciosEspecialesService preciosEspecialesService;
	
	@GetMapping()
	public List<CatPrecioEspecialEntity> getAllPreciosEspeciales() {
		return preciosEspecialesService.getAllPreciosEspeciales();
	}
	
	@PostMapping()
	public CatPrecioEspecialEntity createPrecioEspecial(@RequestBody CatPrecioEspecialEntity precioespecial) {
//		return repository.save(comentario);
		return null;
	}
	
	@PutMapping("/{idPrecioEspecial}")
	public CatPrecioEspecialEntity updateEstadoHabitacion(@PathVariable int idPrecioEspecial ,@RequestBody CatPrecioEspecialEntity precioespecial) {
//		return repository.save(person);
		return null;
	}
	
	@DeleteMapping("/{idPrecioEspecial}")
	public void deletePrecioEspecial(@PathVariable("idPrecioEspecial") Long idPrecioEspecial) {
//		repository.deleteById(id);
	}
}
