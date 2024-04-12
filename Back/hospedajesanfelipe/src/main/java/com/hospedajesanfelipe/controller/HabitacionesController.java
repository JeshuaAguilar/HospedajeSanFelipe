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

import com.hospedajesanfelipe.entity.HabitacionEntity;
import com.hospedajesanfelipe.service.HabitacionesService;

@RestController
@RequestMapping("/hospedaje/api/habitaciones")
public class HabitacionesController {
	
	@Autowired
	HabitacionesService habitacionesService;
	
	@GetMapping()
	public List<HabitacionEntity> getAllHabitaciones() {
		return habitacionesService.getAllHabitaciones();
	}
	
	@PostMapping()
	public HabitacionEntity createHabitacion(@RequestBody HabitacionEntity habitacion) {
//		return repository.save(comentario);
		return null;
	}
	
	@PutMapping("/{idHabitacion}")
	public HabitacionEntity updateHabitacion(@PathVariable int idHabitacion ,@RequestBody HabitacionEntity habitacion) {
//		return repository.save(person);
		return null;
	}
	
	@DeleteMapping("/{idHabitacion}")
	public void deleteHabitacion(@PathVariable("idHabitacion") Long idHabitacion) {
//		repository.deleteById(id);
	}
}
