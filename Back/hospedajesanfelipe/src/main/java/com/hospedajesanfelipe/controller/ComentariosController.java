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

import com.hospedajesanfelipe.entity.ComentarioEntity;
import com.hospedajesanfelipe.service.ComentariosService;

@RestController
@RequestMapping("/hospedaje/api/comentarios")
public class ComentariosController {
	
	ComentariosService comentariosService;
	
	@GetMapping()
	public List<ComentariosController> allComentarios(){
//		return repository.findAll();
		return null;
	}
	
	@PostMapping()
	public ComentarioEntity createComentario(@RequestBody ComentarioEntity comentario) {
//		return repository.save();
		return null;
	}
	
	@PutMapping("/{idComentario}")
	public ComentarioEntity updateComentario(@PathVariable int idComentario ,@RequestBody ComentarioEntity comentario) {
//		return repository.save(person);
		return null;
	}
	
	@DeleteMapping("/{idComentario}")
	public void deleteComentario(@PathVariable("idComentario") Long idComentario) {
//		repository.deleteById(id);
	}
}
