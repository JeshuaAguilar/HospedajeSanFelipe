package com.hospedajesanfelipe.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hospedajesanfelipe.entity.ClienteEntity;

@Repository
public interface ClientesRepository extends JpaRepository<ClienteEntity, Long>{

}
