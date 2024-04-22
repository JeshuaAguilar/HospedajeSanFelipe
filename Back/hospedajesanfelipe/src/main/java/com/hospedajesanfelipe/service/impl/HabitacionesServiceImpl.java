package com.hospedajesanfelipe.service.impl;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hospedajesanfelipe.dao.HabitacionesDao;
import com.hospedajesanfelipe.dao.ReservacionesDao;
import com.hospedajesanfelipe.entity.CatEstadoHabitacionEntity;
import com.hospedajesanfelipe.entity.CatPisoEntity;
import com.hospedajesanfelipe.entity.HabitacionEntity;
import com.hospedajesanfelipe.entity.ReservacionEntity;
import com.hospedajesanfelipe.request.HabitacionRequest;
import com.hospedajesanfelipe.response.HabitacionClienteResponse;
import com.hospedajesanfelipe.response.HabitacionDisponibleResponse;
import com.hospedajesanfelipe.response.HabitacionEmpleadoResponse;
import com.hospedajesanfelipe.response.HabitacionResponse;
import com.hospedajesanfelipe.service.HabitacionesService;

@Service
public class HabitacionesServiceImpl implements HabitacionesService {
	
	@Autowired
	HabitacionesDao habitacionesDao;
	@Autowired
	ReservacionesDao reservacionesDao;

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
	public List<HabitacionClienteResponse> getAllHabitacionesCliente() {
		List<HabitacionClienteResponse> response = null;
		List<HabitacionEntity> habitaciones = habitacionesDao.getAllHabitaciones();
		
		if (habitaciones != null && !habitaciones.isEmpty()) {
			response = new ArrayList<HabitacionClienteResponse>();
			for (HabitacionEntity habitacionEntity : habitaciones) {
				HabitacionClienteResponse habitacion = new HabitacionClienteResponse();
				habitacion = mapperHabitacionClienteResponse(habitacionEntity);
				response.add(habitacion);
			}
			
		}
		return response;
	}

	@Override
	public List<HabitacionEmpleadoResponse> getAllHabitacionesEmpleado() {
		List<HabitacionEmpleadoResponse> response = null;
		List<HabitacionEntity> habitaciones = habitacionesDao.getAllHabitaciones();
		
		if (habitaciones != null && !habitaciones.isEmpty()) {
			response = new ArrayList<HabitacionEmpleadoResponse>();
			for (HabitacionEntity habitacionEntity : habitaciones) {
				HabitacionEmpleadoResponse habitacion = new HabitacionEmpleadoResponse();
				habitacion = mapperHabitacionEmpleadoResponse(habitacionEntity);
				response.add(habitacion);
			}
			
		}
		return response;
	}
	
	@Override
	public List<HabitacionDisponibleResponse> getAllHabitacionesDisponibles(LocalDate fechaEntrada, LocalDate fechaSalida) {
	    List<HabitacionDisponibleResponse> response = new ArrayList<>();  // Inicializar siempre para evitar NullPointerException.
	    List<HabitacionEntity> habitaciones = habitacionesDao.getAllHabitaciones();
	    
	    if (habitaciones != null && !habitaciones.isEmpty()) {
	        for (HabitacionEntity habitacionEntity : habitaciones) {
	            List<ReservacionEntity> reservaciones = reservacionesDao.findReservacionesByHabitacion(habitacionEntity.getIdHabitacion());
	            
	            boolean isAvailable = true;
	            for (ReservacionEntity reservacion : reservaciones) {
	                if (!(fechaSalida.isBefore(reservacion.getFechaEntrada()) || fechaEntrada.isAfter(reservacion.getFechaSalida()))) {
	                    isAvailable = false;
	                    break;  // No need to check further, habitacion is not available
	                }
	            }

	            if (isAvailable) {
	                HabitacionDisponibleResponse habitacion = new HabitacionDisponibleResponse();
	                habitacion.setIdHabitacion(habitacionEntity.getIdHabitacion());
	                habitacion.setNoHabitacion(habitacionEntity.getNoHabitacion());
	                habitacion.setNoOcupante(habitacionEntity.getNoOcupantes());
	                habitacion.setNoMaxOcupante(habitacionEntity.getNoMaxOcupante());
	                habitacion.setNoMaxExtras(habitacionEntity.getNoMaxExtras());
	                habitacion.setNoCamasIndividuales(habitacionEntity.getNoCamasIndividuales());
	                habitacion.setNoCamasMatrimoniales(habitacionEntity.getNoCamasMatrimoniales());
	                habitacion.setCosto(habitacionEntity.getCosto());
	                habitacion.setPiso(habitacionEntity.getPiso().getDescripcion());
	                habitacion.setServicios(habitacionEntity.getServicios());
	                response.add(habitacion);
	            }
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
		habitacionEntity.setNoOcupantes(validaNull(habitacion.getNoOcupante(), habitacionEntity.getNoOcupantes()));
		habitacionEntity.setNoMaxOcupante(validaNull(habitacion.getNoMaxOcupante(), habitacionEntity.getNoMaxOcupante()));
		habitacionEntity.setNoCamasIndividuales(validaNull(habitacion.getNoCamasIndividuales(), habitacionEntity.getNoCamasIndividuales()));
		habitacionEntity.setNoCamasMatrimoniales(validaNull(habitacion.getNoCamasMatrimoniales(), habitacionEntity.getNoCamasMatrimoniales()));
		habitacionEntity.setCosto(validaNull(habitacion.getCosto(), habitacionEntity.getCosto()));
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
		response.setNoOcupante(habitacion.getNoOcupantes());
		response.setNoMaxOcupante(habitacion.getNoMaxOcupante());
		response.setNoCamasIndividuales(habitacion.getNoCamasIndividuales());
		response.setNoCamasMatrimoniales(habitacion.getNoCamasMatrimoniales());
		response.setNoMaxExtras(habitacion.getNoMaxExtras());
		response.setCosto(habitacion.getCosto());
		response.setPiso(habitacion.getPiso());
		response.setEstado(habitacion.getEstado());
		response.setUrlFoto(habitacion.getUrlFoto());
		
		return response;
	}

	private HabitacionClienteResponse mapperHabitacionClienteResponse(HabitacionEntity habitacion) {
		HabitacionClienteResponse response = new HabitacionClienteResponse();
		
		response.setIdHabitacion(habitacion.getIdHabitacion());
		response.setNoHabitacion(habitacion.getNoHabitacion());
		response.setNoOcupante(habitacion.getNoOcupantes());
		response.setUrlFoto(habitacion.getUrlFoto());
		response.setServicios(habitacion.getServicios());
		
		return response;
	}
	
	private HabitacionEmpleadoResponse mapperHabitacionEmpleadoResponse(HabitacionEntity habitacion) {
		HabitacionEmpleadoResponse response = new HabitacionEmpleadoResponse();
		
		response.setIdHabitacion(habitacion.getIdHabitacion());
		response.setNoHabitacion(habitacion.getNoHabitacion());
		response.setNoOcupante(habitacion.getNoOcupantes());
		response.setNoMaxOcupante(habitacion.getNoMaxOcupante());
		response.setNoCamasIndividuales(habitacion.getNoCamasIndividuales());
		response.setNoCamasMatrimoniales(habitacion.getNoCamasMatrimoniales());
		response.setCosto(habitacion.getCosto());
		response.setPiso(habitacion.getPiso());
		response.setEstado(habitacion.getEstado());
		response.setUrlFoto(habitacion.getUrlFoto());
		response.setServicios(habitacion.getServicios());
		List<ReservacionEntity> reservaciones = reservacionesDao.findReservacionesByHabitacion(habitacion.getIdHabitacion());
		response.setReservaciones(reservaciones);
		
		return response;
	}
	
	private int validaNull(int parametro, int parametro2) {
	    if (parametro > 0) {
	        return parametro;
	    } else {
	        return parametro2;
	    }
	}
	
	public BigDecimal validaNull(BigDecimal parametro, BigDecimal parametro2) {
	    if (parametro.compareTo(BigDecimal.ZERO) > 0) {
	        return parametro;
	    } else {
	        return parametro2;
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
		
