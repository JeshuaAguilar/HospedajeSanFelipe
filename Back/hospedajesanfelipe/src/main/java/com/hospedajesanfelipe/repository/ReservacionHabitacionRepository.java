package com.hospedajesanfelipe.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hospedajesanfelipe.entity.ReservacionHabitacionEntity;
import com.hospedajesanfelipe.vo.ReservacionHabitacionIdVO;

@Repository
public interface ReservacionHabitacionRepository extends JpaRepository<ReservacionHabitacionEntity, ReservacionHabitacionIdVO> {
	List<ReservacionHabitacionEntity> findByReservacion_IdReservacion(Long idReservacion);
	List<ReservacionHabitacionEntity> findByHabitacion_IdHabitacion(Long idHabitacion);
}
