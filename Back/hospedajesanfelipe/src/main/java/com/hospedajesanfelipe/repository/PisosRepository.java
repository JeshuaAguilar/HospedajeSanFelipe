package com.hospedajesanfelipe.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hospedajesanfelipe.entity.CatPisoEntity;

@Repository
public interface PisosRepository extends JpaRepository<CatPisoEntity, Long> {

}
