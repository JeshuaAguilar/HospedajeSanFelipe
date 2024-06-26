package com.hospedajesanfelipe.service;

import java.io.ByteArrayInputStream;
import java.util.List;

import com.hospedajesanfelipe.entity.EmpleadoEntity;
import com.hospedajesanfelipe.request.EmpleadoRequest;
import com.hospedajesanfelipe.response.EmpleadoResponse;

/*
 * Esta interfaz únicamente contiene las definiciones de los métodos que vamos a utilizar, tanto sus request como sus response
 * Esta interfaz tiene que estar implementada en un servicio implement
 */

public interface EmpleadosService {
	public EmpleadoEntity getEmpleadoByUserName(String userName);
	
	/*Este método trae todos los empleados de la BD, por eso se regresa una lista
	 No rebine parámetros porque tiene que traer todos y no 1 en específico
	 Retorna una lista con todos los empleados de tipo empleados entity, porque aquí sí me puede trar sus reservaciones y comentarios 
	 */
	public List<EmpleadoResponse> getAllEmpleados();
	/*Este método busca a un empleado por su id, y únicamente regresa 1 empleado, que conincida con el id de búsqueda
	  Y recibe el idEmpledo por parámetro para poder buscarlo
	  Y retorna un objeto tipo empleado con la información del empleado encontrada de tipo empleados entity, porque aquí sí me puede trar sus reservaciones y comentarios
	 */
	public EmpleadoEntity getEmpleadoById(Long idEmpleado);
	/*
	 * Crea un nuevo empleado
	 * recibe por parámetro un objeto de tipo empleado con los datos del empleado, este obnjeto no lleva id, porque es nuevo empleado, y la BD al insertar le asigna su id
	 * retorna un objeto tipo empleado con la información del empleado creado + su id, pero de tipo empleado request 
	 */
	public EmpleadoEntity createEmpleado(EmpleadoRequest empleado);
	/*
	 * Este método actualiza un empleado
	 * Recibe por parámetro un objeto con los nuevos datos del empleado de empleado request, este objeto tiene que contener el id del empleado para saber qué empleado va a modificar
	 * Retorna de igual manera 1 objeto de tipo empleado, tiene que regresar el mismo id que se le fue enviado, pero de tipo request
	 */
	public EmpleadoEntity updateEmpleado(EmpleadoRequest empleado);
	/*
	 * Este método elimina un empelado de la BD, únicamente se le tiene que mandar el id del empleado que queremos eliminar
	 * No retorna nada, por eso se le pone void
	 */
	public void deleteEmpleado(Long idEmpleado);
	public ByteArrayInputStream getEmpleadosPdf();
}
