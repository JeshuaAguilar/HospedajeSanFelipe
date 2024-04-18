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


import com.hospedajesanfelipe.entity.HabitacionEntity;
import com.hospedajesanfelipe.request.HabitacionRequest;
import com.hospedajesanfelipe.response.HabitacionResponse;
import com.hospedajesanfelipe.service.HabitacionesService;

@RestController
@RequestMapping("/hospedaje/api/habitaciones")
public class HabitacionesController {
	
	@Autowired
	HabitacionesService habitacionesService;
	
	@GetMapping()
	public List<HabitacionResponse> getAllHabitaciones() {
		return habitacionesService.getAllHabitaciones();
	}
	
	@GetMapping("/{idHabitacion}")
	public HabitacionEntity getHabitacionById(@PathVariable Long idHabitacion) {
		return habitacionesService.getHabitacionById(idHabitacion);
	}
	
	@PostMapping()
	public ResponseEntity<String> createHabitacion(@RequestBody HabitacionRequest habitacion) {
		HabitacionEntity response = habitacionesService.createHabitacion(habitacion); 
		
		if (response != null) {
			return ResponseEntity.status(HttpStatus.CREATED).body("La habitacion se ha creado exitosamente");
		} else {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Ocurrió un error al crear la habitacion");
		}
	}
	
	@PutMapping()
	public ResponseEntity<String> updateHabitacion(@RequestBody HabitacionRequest habitacion) {
		HabitacionEntity response = habitacionesService.updateHabitacion(habitacion); 
		
		if (response != null) {
			return ResponseEntity.status(HttpStatus.CREATED).body("La habitacion se ha modificado exitosamente");
		} else {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Ocurrió un error al modificar la habitacion");
		}
	}
	
	@DeleteMapping("/{idHabitacion}")
	public ResponseEntity<String> deleteHabitacion(@PathVariable("idHabitacion") Long idHabitacion) {
		habitacionesService.deleteHabitacion(idHabitacion);
		return ResponseEntity.status(HttpStatus.OK).body("La habitacion se ha eliminado exitosamente");
	}
}
