package com.hospedajesanfelipe.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hospedajesanfelipe.entity.CatServicioEntity;

@Repository
public interface ServiciosRepository extends JpaRepository<CatServicioEntity, Long> {

}
