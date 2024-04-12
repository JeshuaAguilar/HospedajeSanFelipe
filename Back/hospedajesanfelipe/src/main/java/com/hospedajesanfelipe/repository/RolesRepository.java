package com.hospedajesanfelipe.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hospedajesanfelipe.entity.CatRolEntity;

@Repository
public interface RolesRepository extends JpaRepository<CatRolEntity, Long>{

}
