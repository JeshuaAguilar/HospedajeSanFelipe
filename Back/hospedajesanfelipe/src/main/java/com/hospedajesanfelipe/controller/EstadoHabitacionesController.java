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

import com.hospedajesanfelipe.entity.CatEstadoHabitacionEntity;
import com.hospedajesanfelipe.service.EstadoHabitacionesService;

@RestController
@RequestMapping("/hospedaje/api/estadoHabitaciones")
public class EstadoHabitacionesController {
	
	@Autowired
	EstadoHabitacionesService estadoHabitacionesService;
	
	@GetMapping()
	public List<CatEstadoHabitacionEntity> getAllEstadoHabitaciones() {
		return estadoHabitacionesService.getAllEstadoHabitaciones();
	}
	
	@PostMapping()
	public CatEstadoHabitacionEntity createEstadoHabitacion(@RequestBody CatEstadoHabitacionEntity estadohabitacion) {
//		return repository.save(comentario);
		return null;
	}
	
	@PutMapping("/{idEstado}")
	public CatEstadoHabitacionEntity updateEstadoHabitacion(@PathVariable int idEstado ,@RequestBody CatEstadoHabitacionEntity estadohabitacion) {
//		return repository.save(person);
		return null;
	}
	
	@DeleteMapping("/{idEstado}")
	public void deleteEstadoHabitacion(@PathVariable("idEstado") Long idEstado) {
//		repository.deleteById(id);
	}
}
