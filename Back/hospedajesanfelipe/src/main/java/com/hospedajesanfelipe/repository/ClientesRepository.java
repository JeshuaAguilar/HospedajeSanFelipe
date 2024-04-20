package com.hospedajesanfelipe.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.hospedajesanfelipe.entity.ClienteEntity;

@Repository
public interface ClientesRepository extends JpaRepository<ClienteEntity, Long>{
	@Query(nativeQuery = true,
		   value = "SELECT * FROM clientes WHERE lower(nombre) = lower(:nombre) AND lower(primer_apellido) = lower(:primerApellido)"
	      )
	public List<ClienteEntity> findByNombreAndPrimerApellido(@Param("nombre") String nombre, @Param("primerApellido") String primerApellido);
}
