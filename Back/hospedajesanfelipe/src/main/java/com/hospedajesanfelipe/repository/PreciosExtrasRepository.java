package com.hospedajesanfelipe.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hospedajesanfelipe.entity.CatPrecioExtraEntity;

@Repository
public interface PreciosExtrasRepository extends JpaRepository<CatPrecioExtraEntity, Long>{

}
