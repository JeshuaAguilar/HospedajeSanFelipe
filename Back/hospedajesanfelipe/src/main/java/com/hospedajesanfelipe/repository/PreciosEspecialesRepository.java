package com.hospedajesanfelipe.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hospedajesanfelipe.entity.CatPrecioEspecialEntity;

@Repository
public interface PreciosEspecialesRepository  extends JpaRepository<CatPrecioEspecialEntity, Long>{

}
