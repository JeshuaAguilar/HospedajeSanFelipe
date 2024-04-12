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

import com.hospedajesanfelipe.entity.CatServicioEntity;
import com.hospedajesanfelipe.service.ServiciosService;

@RestController
@RequestMapping("/hospedaje/api/servicio")
public class ServiciosController {
	
	@Autowired
	ServiciosService serviciosService;
	
	@GetMapping()
	public List<CatServicioEntity> getAllServicios() {
		return serviciosService.getAllServicios();
	}
	
	@PostMapping()
	public CatServicioEntity createServicios(@RequestBody CatServicioEntity servicios) {
//		return repository.save(comentario);
		return null;
	}
	
	@PutMapping("/{idServicio}")
	public CatServicioEntity updateServicio(@PathVariable int idServicio ,@RequestBody CatServicioEntity servicios) {
//		return repository.save(person);
		return null;
	}
	
	@DeleteMapping("/{idServicio}")
	public void deleteServicio(@PathVariable("idServicio") Long idServicio) {
//		repository.deleteById(id);
	}
}
