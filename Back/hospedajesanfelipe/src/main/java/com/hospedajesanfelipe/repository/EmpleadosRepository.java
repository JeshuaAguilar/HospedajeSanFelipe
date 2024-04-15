package com.hospedajesanfelipe.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.hospedajesanfelipe.entity.EmpleadoEntity;

/*
 * Esta clase es el repositorio, que extiende de JpaRepository, y es de tipo empleado Entity.
 * Al extender de la clase JpaRepository, le agrega los métodos de JPA, como el findAll, FindById, save, deleteById, entre otros
 * Aunque no los veas aquí, ya están, pero ten en cuenta que todos estos métodos únicamnete van aplicar
 * A la tabla definida en el Entity que tienes aquí configurado, en este caso es EmpleadoEntity, y todos los métodos que
 * uses, se van aplicar a la tabla de empleados.
 * Tiene un Long, también, eso es únicamente el tipo de dato del idEmpleado, porque recuerda que los ids, los manejamos como Long
 * Y los manejamos como Long, porque pueden ser nulos también
 * 
 * Quí también se podrían crear queries natuvos, como un select * from, pero eso únicamente si es requerido.
 */
@Repository
public interface EmpleadosRepository  extends JpaRepository<EmpleadoEntity, Long> {
	
	@Query(nativeQuery = true, value = "select * from empleados where user_name = :userName and contrasenia = :contrasenia")
	public Optional<EmpleadoEntity> login(@Param("userName") String userName, @Param("contrasenia") String contrasenia);
	
	public Optional<EmpleadoEntity> findByUserName(@Param("userName") String userName);
}
