package com.hospedajesanfelipe.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hospedajesanfelipe.dao.HabitacionesDao;
import com.hospedajesanfelipe.entity.CatEstadoHabitacionEntity;
import com.hospedajesanfelipe.entity.CatPisoEntity;

import com.hospedajesanfelipe.entity.HabitacionEntity;
import com.hospedajesanfelipe.request.HabitacionRequest;
import com.hospedajesanfelipe.response.HabitacionResponse;
import com.hospedajesanfelipe.service.HabitacionesService;

@Service
public class HabitacionesServiceImpl implements HabitacionesService {
	
	@Autowired
	HabitacionesDao habitacionesDao;

	@Override
	public HabitacionEntity getHabitacionByNoHabitacion(String noHabitacion) {
		Optional<HabitacionEntity> entity = habitacionesDao.getHabitacionByNoHabitacion(noHabitacion);
		
		if (entity.isPresent()) {
			return entity.get();
		} else {
			return null;
		}
	}
	
	@Override
	public List<HabitacionResponse> getAllHabitaciones() {
		List<HabitacionResponse> response = null;
		List<HabitacionEntity> habitaciones = habitacionesDao.getAllHabitaciones();
		
		if (habitaciones != null && !habitaciones.isEmpty()) {
			response = new ArrayList<HabitacionResponse>();
			for (HabitacionEntity habitacionEntity : habitaciones) {
				HabitacionResponse habitacion = new HabitacionResponse();
				habitacion = mapperRhabitacionResponse(habitacionEntity);
				response.add(habitacion);
			}
			
		}
		return response;
	}
	
	@Override
	public HabitacionEntity getHabitacionById(Long idHabitacion) {
		Optional<HabitacionEntity> habitacionEntity = habitacionesDao.getHabitacionById(idHabitacion); 
		
		if (habitacionEntity.isPresent()) {
			return habitacionEntity.get();
		} else {
			return null;
		}
	}
	
	@Override
	public HabitacionEntity createHabitacion(HabitacionRequest habitacion) {
		/*
		 * Se crea un método mapper, lo que hace este método es convertir el objeto EmpleadoRequest a empleado Entity
		 * El empleado request es la información que manda el front al back y el back pasa esos datos para que el entity 
		 * pueda insertar el nuevo empleado
		 */
		HabitacionEntity habitacionEntity = mapperHabitacion(habitacion);
		/*
		 * Una vez mapeado el empleado Request con el empleado Entity, podemos mandar a insertar el entity en la base de datos
		 */
		return habitacionesDao.createHabitacion(habitacionEntity);
	}
	
	@Override
	public HabitacionEntity updateHabitacion(HabitacionRequest habitacion) {
		/*
		 * También hacemos el mismo mapper, la única diferencia con el método anterior, es que aquí
		 * El EmpleadoRequest sí va a tener un id, todo los demás es lo mismo
		 */
		HabitacionEntity habitacionEntity = mapperHabitacion(habitacion);
		return habitacionesDao.createHabitacion(habitacionEntity);
	}

	/*
	 * Este es el método para eliminar un empleado, recibe como parámetro un id de empleado para que sepa cuál liminar
	 */
	@Override
	public void deleteHabitacion(Long idHabitacion) {
		habitacionesDao.deleteHabitacion(idHabitacion);
	}
	
	private HabitacionEntity mapperHabitacion(HabitacionRequest habitacion) {
		HabitacionEntity habitacionEntity = null;

		/*
		 * Validamos que el objeto EmpleadoRequest traida un id, si trae un id, quiere decir que el empleado ya existe y hay que actualizarlo
		 */
		if (habitacion.getIdHabitacion() != null && habitacion.getIdHabitacion() > 0) {
			/*
			 * Si cuenta con un id buscamos primero el empleado en la base de datos con el método de getEmpleadoById()
			 */
			habitacionEntity = getHabitacionById(habitacion.getIdHabitacion());
		} else {
			/*
			 * Si el empleado no trae un id, quiere decir que es un nuevo empleado, y creamos una nueva instancia vacía de Empleado entity
			 * Para poder guardarlo en la base de datos
			 */
			habitacionEntity = new HabitacionEntity();
		}
		
		habitacionEntity.setIdHabitacion(habitacion.getIdHabitacion());
		habitacionEntity.setNoHabitacion(validaNull(habitacion.getNoHabitacion(), habitacionEntity.getNoHabitacion()));
		habitacionEntity.setNoOcupante(validaNull(habitacion.getNoOcupante(), habitacionEntity.getNoOcupante()));
		habitacionEntity.setNoMaxOcupante(validaNull(habitacion.getNoMaxOcupante(), habitacionEntity.getNoMaxOcupante()));
		/*
		 * Rol, es un objeto, y cuando hacemos la instancia de un new EmpleadoEntity(), este objeto es nulo
		 * Por eso tenermos que crearle una nueva instancia de CatRolEntity()
		 */
		if (habitacion.getPiso() != null && habitacion.getPiso() > 0) {
			habitacionEntity.setPiso(new CatPisoEntity());
			/*
			 * Una vez creada la instancia de Rol ya ya no es nula, podemos asignarle el rol correspondiente
			 */
			habitacionEntity.getPiso().setIdPiso(habitacion.getPiso());
			
			
		}
		if (habitacion.getEstado() != null && habitacion.getEstado() > 0) {
			habitacionEntity.setEstado(new CatEstadoHabitacionEntity());
			/*
			 * Una vez creada la instancia de Rol ya ya no es nula, podemos asignarle el rol correspondiente
			 */
			habitacionEntity.getEstado().setIdEstado(habitacion.getEstado());
			
			
		}
		
		
		/*
		 * Retornamos el empleado entity que es lo que se va a guardar en la base de datos.
		 */
		habitacionEntity.setUrlFoto(habitacion.getUrlFoto());
		
		return habitacionEntity;
	}
	
	

	private HabitacionResponse mapperRhabitacionResponse(HabitacionEntity habitacion) {
		HabitacionResponse response = new HabitacionResponse();
		
		response.setIdHabitacion(habitacion.getIdHabitacion());
		response.setNoHabitacion(habitacion.getNoHabitacion());
		response.setNoOcupante(habitacion.getNoOcupante());
		response.setNoMaxOcupante(habitacion.getNoMaxOcupante());
		response.setPiso(habitacion.getPiso());
		response.setEstado(habitacion.getEstado());
		
		return response;
	}
	
	private int validaNull(int noOcupante, int noOcupante2) {
	    if (noOcupante > 0) {
	        return noOcupante;
	    } else {
	        return noOcupante2;
	    }
	}
	
	private String validaNull(String parametro, String actual) {
		if (parametro != null && !parametro.isBlank()) {
			return parametro;
		} else {
			return actual;
		}
	}
}
		
