package com.hospedajesanfelipe.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Component;


import com.hospedajesanfelipe.entity.HabitacionEntity;
import com.hospedajesanfelipe.repository.HabitacionesRepository;

@Component
public class HabitacionesDao {
	
	@Autowired
	HabitacionesRepository habitacionesRepository;

	public Optional<HabitacionEntity> getHabitacionByNoHabitacion(String noHabitacion) {
		
		Optional<HabitacionEntity> response = null;
		
		try {
			response = habitacionesRepository.findByNoHabitacion(noHabitacion);
		} catch (DataAccessException ex) {
			System.out.println(ex.getMessage() + ex);
		}
		
		return response;
	}
	
	public List<HabitacionEntity> getAllHabitaciones() {
		//El método de findAll(), es de JPA y trae todos los registros
		/*
		 * Creamos una variable de tipo List empleado, como nula, si la consulta falla o truena por algún motivo, retornaríamos
		 * un null, el try y catch es para evitar que el programa se detenga y podamos manejar el error y mostrarlo en el log
		 * para que podamos saber con detalle sobre qué fue lo que falló
		 */
		List<HabitacionEntity> response = null;
		try {
			response = habitacionesRepository.findAll();
		} catch (DataAccessException ex) {
			/*
			 * Con esto imprimimos en el log, el detalle del error
			 */
			System.out.println(ex.getMessage() + ex);
		}
		
		return response;
	}
	
	public List<HabitacionEntity> getAllHabitacionesDisponibles() {
		//El método de findAll(), es de JPA y trae todos los registros
		/*
		 * Creamos una variable de tipo List empleado, como nula, si la consulta falla o truena por algún motivo, retornaríamos
		 * un null, el try y catch es para evitar que el programa se detenga y podamos manejar el error y mostrarlo en el log
		 * para que podamos saber con detalle sobre qué fue lo que falló
		 */
		List<HabitacionEntity> response = null;
		try {
			response = habitacionesRepository.findAll();
		} catch (DataAccessException ex) {
			/*
			 * Con esto imprimimos en el log, el detalle del error
			 */
			System.out.println(ex.getMessage() + ex);
		}
		
		return response;
	}
	
	public Optional<HabitacionEntity> getHabitacionById(Long idHabitacion) {
		/*
		 * El método de findById(), es de JPA y trae el registro que coincida con el id enviado a buscar
		 * Recibe el id a buscar y retorna un opctional con el obketo del empleado
		 * el Optional es para saber si encontró o no encontró el empleado con ese id 
		 */
		Optional<HabitacionEntity> response = null;
		try {
			response = habitacionesRepository.findById(idHabitacion);
		} catch (DataAccessException ex) {
			System.out.println(ex.getMessage() + ex);
		}
		
		return response;
	}
	
	public HabitacionEntity createHabitacion(HabitacionEntity habitacion) {
		/*
		 * El método save es de JPA y recibe como parámetro para este caso un objeto tipo empleado y si el objeto
		 * no tiene id, crea un nuevo registro con el insert
		 */
		HabitacionEntity response = null;
		try {
			response = habitacionesRepository.save(habitacion);
		} catch (DataAccessException ex) {
			System.out.println(ex.getMessage() + ex);
		}
		
		return response;
	}
	
	
	public HabitacionEntity updateHabitacion(HabitacionEntity habitacion) {
		/*
		 * Aquí se repite el mismo save como el de arriba, pero en este caso, como el objeto tipo empleado, si va a llevar un id
		 * hara un update y no un insert
		 */
		HabitacionEntity response = null;
		try {
			response = habitacionesRepository.save(habitacion);
		} catch (DataAccessException ex) {
			System.out.println(ex.getMessage() + ex);
		}
		
		return response;
	}

	public void deleteHabitacion(Long idHabitacion) {
		/*
		 * El método deleteById es de JPA y elimina el registro con el id enviado
		 */
		try {
			habitacionesRepository.deleteById(idHabitacion);
		} catch (DataAccessException ex) {
			System.out.println(ex.getMessage() + ex);
		}
	}
	
}
