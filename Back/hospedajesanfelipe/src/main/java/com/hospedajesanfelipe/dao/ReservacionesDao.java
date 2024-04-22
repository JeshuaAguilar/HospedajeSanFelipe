package com.hospedajesanfelipe.dao;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.hospedajesanfelipe.entity.ReservacionEntity;
import com.hospedajesanfelipe.entity.ReservacionHabitacionEntity;
import com.hospedajesanfelipe.repository.ReservacionHabitacionRepository;
import com.hospedajesanfelipe.repository.ReservacionesRepository;
import com.hospedajesanfelipe.vo.ReservacionHabitacionIdVO;


@Component
public class ReservacionesDao {
	
	@Autowired
	ReservacionesRepository reservacionesRepository;
	@Autowired
	ReservacionHabitacionRepository rhRepository;
	
	public List<ReservacionEntity> getAllReservaciones() {
		List<ReservacionEntity> response = null;
		
		try {
			response = reservacionesRepository.findAll();
		} catch (DataAccessException ex) {
			System.out.println(ex.getMessage() + ex);
		}
		
		return response;
	}

	public Optional<ReservacionEntity> getReservacion(Long idReservacion) {
		Optional<ReservacionEntity> response = null;
		
		try {
			response = reservacionesRepository.findById(idReservacion);
		} catch (DataAccessException ex) {
			System.out.println(ex.getMessage() + ex);
		}
		
		return response;
	}
	
	public ReservacionEntity saveReservaciones(ReservacionEntity reservacion) {
		ReservacionEntity response = null;

		try {
			response = reservacionesRepository.save(reservacion);
		} catch (DataAccessException ex) {
			System.out.println(ex.getMessage() + ex);
		}
		
		return response;
	}
	
	public List<ReservacionEntity> findReservacionesByHabitacion(Long idHabitacion) {
        List<ReservacionHabitacionEntity> rhList = rhRepository.findByHabitacion_IdHabitacion(idHabitacion);
        return rhList.stream()
                     .map(ReservacionHabitacionEntity::getReservacion)
                     .collect(Collectors.toList());
    }
	
	public List<ReservacionHabitacionEntity> getAllReservacionHabitacion(Long idReservacion) {
		List<ReservacionHabitacionEntity> response = null;
		try {
			response = rhRepository.findByReservacion_IdReservacion(idReservacion);
		} catch (DataAccessException ex) {
			System.out.println(ex.getMessage() + ex);
		}
		
		return response;
	}

	@Transactional
	public List<ReservacionHabitacionEntity> saveReservacionHabitacion(List<ReservacionHabitacionEntity> rhs) {
		List<ReservacionHabitacionEntity> response = null;
		
		try {
			response = rhRepository.saveAll(rhs);
		} catch (DataAccessException ex) {
			System.out.println(ex.getMessage() + ex);
		}
		
		return response;
	}
	
	@Transactional
	public List<ReservacionHabitacionEntity> updateReservacionHabitacion(List<ReservacionHabitacionEntity> rhs) {
		List<ReservacionHabitacionEntity> response = null;
		
		try {
			response = rhRepository.saveAll(rhs);
		} catch (DataAccessException ex) {
			System.out.println(ex.getMessage() + ex);
		}
		
		return response;
	}
	
	public void deleteReservaciones(Long idReservacion) {
		try {
			reservacionesRepository.deleteById(idReservacion);
		} catch (DataAccessException ex) {
			System.out.println(ex.getMessage() + ex);
		}
	}
	
	public void deleteReservacionHabitacionEntity(ReservacionHabitacionIdVO reservacion) {
		try {
			rhRepository.deleteById(reservacion);
		} catch (DataAccessException ex) {
			System.out.println(ex.getMessage() + ex);
		}
	}
}
