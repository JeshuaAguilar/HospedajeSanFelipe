package com.hospedajesanfelipe.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Component;

import com.hospedajesanfelipe.entity.EmpleadoEntity;
import com.hospedajesanfelipe.repository.EmpleadosRepository;
import com.hospedajesanfelipe.request.LoginRequest;
import com.hospedajesanfelipe.response.LoginResponse;

/*
 * Esta clase es el dao, y se encarga de llamar los método del repository de JPA
 * Aquí casi no se implementa lógica.
 */

//Agregamos @Component, para que spring lo detecte y pueda ser inyectado en otras clases
@Component
public class EmpleadosDao {
	
	//Esto es para inyectar el repositorio y podamos usar sus método aquí
	@Autowired
	EmpleadosRepository empleadosRepository;
	
	public Optional<EmpleadoEntity> getEmpleadoByUserName(String userName) {
		
		Optional<EmpleadoEntity> response = null;
		
		try {
			response = empleadosRepository.findByUserName(userName);
		} catch (DataAccessException ex) {
			/*
			 * Con esto imprimimos en el log, el detalle del error
			 */
			System.out.println(ex.getMessage() + ex);
		}
		
		return response;
	}
	
	public List<EmpleadoEntity> getAllEmpleados() {
		//El método de findAll(), es de JPA y trae todos los registros
		/*
		 * Creamos una variable de tipo List empleado, como nula, si la consulta falla o truena por algún motivo, retornaríamos
		 * un null, el try y catch es para evitar que el programa se detenga y podamos manejar el error y mostrarlo en el log
		 * para que podamos saber con detalle sobre qué fue lo que falló
		 */
		List<EmpleadoEntity> response = null;
		try {
			response = empleadosRepository.findAll();
		} catch (DataAccessException ex) {
			/*
			 * Con esto imprimimos en el log, el detalle del error
			 */
			System.out.println(ex.getMessage() + ex);
		}
		
		return response;
	}
	
	public Optional<EmpleadoEntity> getEmpleadoById(Long idEmpleado) {
		/*
		 * El método de findById(), es de JPA y trae el registro que coincida con el id enviado a buscar
		 * Recibe el id a buscar y retorna un opctional con el obketo del empleado
		 * el Optional es para saber si encontró o no encontró el empleado con ese id 
		 */
		Optional<EmpleadoEntity> response = null;
		try {
			response = empleadosRepository.findById(idEmpleado);
		} catch (DataAccessException ex) {
			System.out.println(ex.getMessage() + ex);
		}
		
		return response;
	}

	public EmpleadoEntity createEmpleado(EmpleadoEntity empleado) {
		/*
		 * El método save es de JPA y recibe como parámetro para este caso un objeto tipo empleado y si el objeto
		 * no tiene id, crea un nuevo registro con el insert
		 */
		EmpleadoEntity response = null;
		try {
			response = empleadosRepository.save(empleado);
		} catch (DataAccessException ex) {
			System.out.println(ex.getMessage() + ex);
		}
		
		return response;
	}

	public EmpleadoEntity updateEmpleado(EmpleadoEntity empleado) {
		/*
		 * Aquí se repite el mismo save como el de arriba, pero en este caso, como el objeto tipo empleado, si va a llevar un id
		 * hara un update y no un insert
		 */
		EmpleadoEntity response = null;
		try {
			response = empleadosRepository.save(empleado);
		} catch (DataAccessException ex) {
			System.out.println(ex.getMessage() + ex);
		}
		
		return response;
	}

	public void deleteEmpleado(Long idEmpleado) {
		/*
		 * El método deleteById es de JPA y elimina el registro con el id enviado
		 */
		try {
			empleadosRepository.deleteById(idEmpleado);
		} catch (DataAccessException ex) {
			System.out.println(ex.getMessage() + ex);
		}
	}
}
