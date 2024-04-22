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

import com.hospedajesanfelipe.entity.ReservacionEntity;
import com.hospedajesanfelipe.request.ReservacionRequest;
import com.hospedajesanfelipe.response.ReservacionResponse;
import com.hospedajesanfelipe.service.ReservacionesService;

@RestController
@RequestMapping("/hospedaje/api/reservaciones")
public class ReservacionesController {
	
	@Autowired
	ReservacionesService reservacionesService;
	
	@GetMapping()
	public List<ReservacionResponse> allReservaciones() {
		return reservacionesService.getAllReservaciones();
	}
	
	@GetMapping("/{idReservacion}")
	public ReservacionEntity getReservacion(Long idReservacion) {
		return reservacionesService.getReservacion(idReservacion);
	}
	
	@PostMapping()
	public ReservacionResponse createReservacion(@RequestBody ReservacionRequest reservacion) {
		return reservacionesService.saveReservaciones(reservacion);
	}
	
	@PutMapping()
	public ReservacionEntity updateReservacion(@RequestBody ReservacionRequest reservacion) {
		return reservacionesService.updateReservaciones(reservacion);
	}
	
	@DeleteMapping("/{idReservacion}")
	public void deleteReservacion(@PathVariable("idReservacion") Long idReservacion) {
		reservacionesService.deleteReservaciones(idReservacion);
	}
}
