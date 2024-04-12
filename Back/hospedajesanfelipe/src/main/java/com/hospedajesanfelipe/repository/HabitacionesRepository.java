package com.hospedajesanfelipe.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hospedajesanfelipe.entity.HabitacionEntity;

@Repository
public interface HabitacionesRepository extends JpaRepository<HabitacionEntity, Long> {

}
