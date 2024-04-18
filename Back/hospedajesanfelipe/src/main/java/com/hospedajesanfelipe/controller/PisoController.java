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


import com.hospedajesanfelipe.entity.CatPisoEntity;
import com.hospedajesanfelipe.service.PisosService;

@RestController
@RequestMapping("/hospedaje/api/pisos")
public class PisoController {

	@Autowired
	PisosService pisosService;

    PisoController(PisosService pisosService) {
        this.pisosService = pisosService;
    }
	
	@GetMapping()
	public List<CatPisoEntity> getAllPiso() {
		return pisosService.getAllPiso();
	}
	
	@PostMapping()
	public CatPisoEntity createPiso(@RequestBody CatPisoEntity piso) {
//		return repository.save(comentario);
		return null;
	}
	
	@PutMapping("/{idPiso}")
	public CatPisoEntity updatePiso(@PathVariable int idPiso ,@RequestBody CatPisoEntity piso) {
//		return repository.save(person);
		return null;
	}
	
	@DeleteMapping("/{idPiso}")
	public void deletePiso(@PathVariable("idPiso") Long idPiso) {
//		repository.deleteById(id);
	}
}
