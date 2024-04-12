package com.hospedajesanfelipe.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hospedajesanfelipe.entity.CatEstadoHabitacionEntity;

@Repository
public interface EstadoHabitacionesRepository extends JpaRepository<CatEstadoHabitacionEntity, Long>{

}
