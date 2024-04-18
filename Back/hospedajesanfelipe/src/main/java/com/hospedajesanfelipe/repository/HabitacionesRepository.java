package com.hospedajesanfelipe.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.hospedajesanfelipe.entity.HabitacionEntity;

@Repository
public interface HabitacionesRepository extends JpaRepository<HabitacionEntity, Long> {
	public Optional<HabitacionEntity> findByNoHabitacion(@Param("noHabitacion") String noHabitacion);
}
