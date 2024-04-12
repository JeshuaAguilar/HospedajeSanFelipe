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


import com.hospedajesanfelipe.entity.ReservacionEntity;
import com.hospedajesanfelipe.service.ReservacionesService;

@RestController
@RequestMapping("/hospedaje/api/reservaciones")
public class ReservacionesController {
	
	ReservacionesService reservacionesService;
	
	@GetMapping()
	public List<ReservacionesController> allReservaciones(){
//		return repository.findAll();
		return null;
	}
	
	@PostMapping()
	public ReservacionEntity createReservacion(@RequestBody ReservacionEntity reservacion) {
//		return repository.save();
		return null;
	}
	
	@PutMapping("/{idReservacion}")
	public ReservacionEntity updateReservacion(@PathVariable int idReservacion ,@RequestBody ReservacionEntity reservacion) {
//		return repository.save(person);
		return null;
	}
	
	@DeleteMapping("/{idReservacion}")
	public void deleteReservacion(@PathVariable("idReservacion") Long idReservacion) {
//		repository.deleteById(id);
	}
}
