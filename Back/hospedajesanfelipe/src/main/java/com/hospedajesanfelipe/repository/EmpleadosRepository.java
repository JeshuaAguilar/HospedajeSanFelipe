package com.hospedajesanfelipe.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hospedajesanfelipe.entity.EmpleadoEntity;

@Repository
public interface EmpleadosRepository  extends JpaRepository<EmpleadoEntity, Long> {

}
