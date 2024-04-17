package com.hospedajesanfelipe.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.hospedajesanfelipe.entity.ClienteEntity;

@Repository
public interface ClientesRepository extends JpaRepository<ClienteEntity, Long>{

	public Optional<ClienteEntity> findByNombre(@Param("nombre") String nombre);
}
