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
import com.hospedajesanfelipe.entity.CatPrecioExtraEntity;
import com.hospedajesanfelipe.service.PreciosExtrasService;

@RestController
@RequestMapping("/hospedaje/api/precioextra")
public class PreciosExtrasController {
	
	@Autowired
	PreciosExtrasService preciosExtrasService;
	
	@GetMapping()
	public List<CatPrecioExtraEntity> getAllPreciosExtras() {
		return preciosExtrasService.getAllPreciosExtras();
	}
	
	@PostMapping()
	public CatPrecioExtraEntity createPreciosExtras(@RequestBody CatPrecioExtraEntity preciosextras) {
//		return repository.save(comentario);
		return null;
	}
	
	@PutMapping("/{idPrecio}")
	public CatPrecioExtraEntity updatePreciosExtras(@PathVariable int idPrecio ,@RequestBody CatPrecioExtraEntity preciosextras) {
//		return repository.save(person);
		return null;
	}
	
	@DeleteMapping("/{idPrecio}")
	public void deletePreciosExtras(@PathVariable("idPrecio") Long idPreciosExtras) {
//		repository.deleteById(id);
	}
}
