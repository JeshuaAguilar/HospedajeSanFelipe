package com.hospedajesanfelipe.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hospedajesanfelipe.entity.ComentarioEntity;

@Repository
public interface ComentariosRepository extends JpaRepository<ComentarioEntity, Long>{

}
