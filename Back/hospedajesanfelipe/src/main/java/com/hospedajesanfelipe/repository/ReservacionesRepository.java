package com.hospedajesanfelipe.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hospedajesanfelipe.entity.ReservacionEntity;

@Repository
public interface ReservacionesRepository extends JpaRepository<ReservacionEntity, Long> {

}
