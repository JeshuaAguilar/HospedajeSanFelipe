package com.hospedajesanfelipe.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hospedajesanfelipe.dao.ReservacionesDao;
import com.hospedajesanfelipe.entity.CatEstadoHabitacionEntity;
import com.hospedajesanfelipe.entity.CatPrecioEspecialEntity;
import com.hospedajesanfelipe.entity.ClienteEntity;
import com.hospedajesanfelipe.entity.ComentarioEntity;
import com.hospedajesanfelipe.entity.EmpleadoEntity;
import com.hospedajesanfelipe.entity.HabitacionEntity;
import com.hospedajesanfelipe.entity.ReservacionEntity;
import com.hospedajesanfelipe.entity.ReservacionHabitacionEntity;
import com.hospedajesanfelipe.request.EmpleadoRequest;
import com.hospedajesanfelipe.request.ReservacionRequest;
import com.hospedajesanfelipe.response.ReservacionResponse;
import com.hospedajesanfelipe.service.ReservacionesService;

@Service
public class ReservacionesServiceImpl implements ReservacionesService{
	
	@Autowired
	ReservacionesDao reservacionesDao;
	
	@Override
	public List<ReservacionResponse> getAllReservaciones() {
		List<ReservacionResponse> response = null;
		List<ReservacionEntity> reservaciones = reservacionesDao.getAllReservaciones();
		
		if (reservaciones != null && !reservaciones.isEmpty()) {
			response = new ArrayList<ReservacionResponse>();
			for (ReservacionEntity reservacionEntity : reservaciones) {
				ReservacionResponse reservacion = reservacionResponseMap(reservacionEntity);
				response.add(reservacion);
			}
		}
		
		return response;
	}

	@Override
	public ReservacionEntity getReservacion(Long idReservacion) {
		Optional<ReservacionEntity> reservacionEntity = reservacionesDao.getReservacion(idReservacion); 
		if (reservacionEntity.isPresent()) {
			ReservacionResponse reservacion = new ReservacionResponse();
			
			reservacion.setIdReservacion(idReservacion);
			
			return reservacionEntity.get();
		} else {
			return null;
		}
	}
	
	@Override
	public ReservacionResponse saveReservaciones(ReservacionRequest reservacionRequest) {
		ReservacionEntity reservacionEntity = mapRequestReservacion(reservacionRequest);
		ReservacionEntity reservacionSaved = reservacionesDao.saveReservaciones(reservacionEntity); 

		List<ReservacionHabitacionEntity> rhs = reservacionHabitacionMapper(reservacionSaved.getIdReservacion(), reservacionRequest.getHabitaciones());
		List<ReservacionHabitacionEntity> rhsSaved = reservacionesDao.saveReservacionHabitacion(rhs);
		
		ReservacionResponse response = reservacionResponseMap(reservacionSaved);
		
		response.setRhs(rhsSaved);
		
		return response;
	}
	
	@Override
	public ReservacionEntity updateReservaciones(ReservacionRequest reservacion) {
		ReservacionEntity reservacionEntity = mapRequestReservacion(reservacion);
		return reservacionesDao.updateReservaciones(reservacionEntity);
	}
	
	@Override
	public void deleteReservaciones(Long idReservacion) {
		reservacionesDao.deleteReservaciones(idReservacion);
	}
	
	private ReservacionEntity mapRequestReservacion(ReservacionRequest reservacion) {
		ReservacionEntity entity = null;
		if (reservacion.getIdReservacion() != null && reservacion.getIdReservacion() > 0) {
			entity = getReservacion(reservacion.getIdReservacion());
		} else {
			entity = new ReservacionEntity();
		}
		
		entity.setFechaEntrada(reservacion.getFechaEntrada());
		entity.setFechaSalida(reservacion.getFechaSalida());
		entity.setNoPersonas(reservacion.getNoPersonas());
		entity.setNoPersonaExtra(reservacion.getNoPersonaExtra());
		entity.setTotal(reservacion.getTotal());
		entity.setEstado(new CatEstadoHabitacionEntity());
		entity.getEstado().setIdEstado(reservacion.getEstado());
		entity.setCliente(new ClienteEntity());
		entity.getCliente().setIdCliente(reservacion.getIdCliente());
		entity.setEmpleado(new EmpleadoEntity());
		entity.getEmpleado().setIdEmpleado(reservacion.getIdEmpleado());

		if (reservacion.getComentario() != null) {
			entity.setComentario(new ComentarioEntity());
			entity.getComentario().setIdComentario(reservacion.getComentario());
		}
		
		if (reservacion.getPrecioEspecial() != null) {
			entity.setPrecioEspecial(new CatPrecioEspecialEntity());
			entity.getPrecioEspecial().setIdPrecioEspecial(reservacion.getPrecioEspecial());
		}
		
		return entity;
	}
	
	private List<ReservacionHabitacionEntity> reservacionHabitacionMapper(Long idReservacion, List<HabitacionEntity> habitaciones) {
		List<ReservacionHabitacionEntity> rhs = new ArrayList<ReservacionHabitacionEntity>();
		
		for (HabitacionEntity habitacion : habitaciones) {
			ReservacionHabitacionEntity rh = new ReservacionHabitacionEntity();
			
			ReservacionEntity reservacion =  new ReservacionEntity();
			reservacion.setIdReservacion(idReservacion);
			
			rh.setReservacion(reservacion);
			rh.setHabitacion(habitacion);
			rh.setNoPersonas(habitacion.getNoOcupantes());
			rh.setNoPersonasExtra(habitacion.getNoMaxExtras());
			
			rhs.add(rh);
		}
		
		return rhs;
	}
	
	private ReservacionResponse reservacionResponseMap(ReservacionEntity reservacionEntity) {
		ReservacionResponse reservacion = new ReservacionResponse();
		
		reservacion.setIdReservacion(reservacionEntity.getIdReservacion());
		reservacion.setFechaEntrada(reservacionEntity.getFechaEntrada());
		reservacion.setFechaSalida(reservacionEntity.getFechaSalida());
		reservacion.setNoPersonas(reservacionEntity.getNoPersonas());
		reservacion.setNoPersonaExtra(reservacionEntity.getNoPersonaExtra());
		reservacion.setTotal(reservacionEntity.getTotal());
		reservacion.setEstado(reservacionEntity.getEstado().getDescripcion());
		reservacion.setCliente(reservacionEntity.getCliente());
		EmpleadoRequest empleado = new EmpleadoRequest();
		empleado.setIdEmpleado(reservacionEntity.getEmpleado().getIdEmpleado());
		empleado.setNombre(reservacionEntity.getEmpleado().getNombre());
		empleado.setPrimerApellido(reservacionEntity.getEmpleado().getPrimerApellido());
		reservacion.setEmpleado(empleado);
		reservacion.setComentario(reservacionEntity.getComentario());
		reservacion.setPrecioEspecial(reservacionEntity.getPrecioEspecial());
		reservacion.setRhs(reservacionesDao.getAllReservacionHabitacion(reservacionEntity.getIdReservacion()));
		
		return reservacion;
	}
}
