package com.hospedajesanfelipe.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hospedajesanfelipe.entity.EmpleadoEntity;
import com.hospedajesanfelipe.request.EmpleadoRequest;
import com.hospedajesanfelipe.request.LoginRequest;
import com.hospedajesanfelipe.response.LoginResponse;
import com.hospedajesanfelipe.service.EmpleadosService;

//Esto hace que sea un controlador y pueda invocarse sus métodos
@RestController
//Aquí definimos la url con la que queremos invocar este servicio de empleados
@RequestMapping("/hospedaje/api/empleados")
public class EmpleadosController {
	
	//Con esto injectamos la interfaz para poder usar sus métodos aquí
	@Autowired
	EmpleadosService empleadosService;
	
//	@PostMapping("/login")
//	public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest request) {
//		
//		LoginResponse response = empleadosService.login(request);
//		
//		if (response != null) {
//			return new ResponseEntity<>(response, HttpStatus.OK);
//		} else {
//			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//		}
//	}
	
	/*Get es el verbo para obntener cosas
	 * En este caso sólo tiene @GetMapping() y no @GetMapping("obtenerEmpleados"), porque al invoca la url /hospedaje/api/empleados,
	 * ya sea en postman o en el front cuando le enviemos la petición de tipo get, ya sabe que tiene que buscar este verbo get 
	 */
	@GetMapping()
	public List<EmpleadoEntity> getAllEmpleados() {
		return empleadosService.getAllEmpleados();
	}

	/* Este también es un verbo get
	 * Pero cuando consultemos esta petición get, cómo es que sabe cuál get de los 2 tomar?
	 * A este GET le estamos agregando un valor extra a la url, en este caso quedaría así "/hospedaje/api/empleados/{idEmpleado}"
	 * Básicamnte se combina el requestMapping + el get Mapping y por eso arma esa url, y para invocarla tienes que enviar 1 id
	 * la url final sería "/hospedaje/api/empleados/3", se le pone el id del empleado, y ese id se va a recuperar en l la variable idEmpleado de tipo Long 
	 */
	@GetMapping("/{idEmpleado}")
	public EmpleadoEntity getEmpleadoById(@PathVariable Long idEmpleado) {
		return empleadosService.getEmpleadoById(idEmpleado);
	}
	
	/* Verbo Post, para este verbo no se le envia nungín parámetro en la url, los parámetros van en el cuerpo de la petición
	 * Por eso el objeto de tipo EmpleadoRequest, lo obtiene con @RequestBody y automáticamente el json que se le envía del front, lo convierte a ese objeto
	 * Por eso es importante, que el json que se le mande, tiene que coincidir con los atributos y tipos de datos del objeto Empleado Entity  
	 * 
	 */
	@PostMapping()
	public EmpleadoEntity createEmpleado(@RequestBody EmpleadoRequest empleado) {
		return empleadosService.createEmpleado(empleado);
	}
	
	/* El verbo Put, es muy similar al Post, básicamente la difencia es el nombre del vebo pero es para identificar únicamente lo que hace el método
	 * Y esa para saber que este método es para update y el otro para insert
	 */
	@PutMapping()
	public EmpleadoEntity updateEmpleado(@RequestBody EmpleadoRequest empleado) {
		return empleadosService.updateEmpleado(empleado);
	}
	
	/* El verbo Delete, es para eliminar un registro, es similar al verbo Get, porque recibe el parámetro por medio de la url
	 * Puedes usar la misma explicación que el del getById
	 */
	@DeleteMapping("/{idEmpleado}")
	public void deleteEmpleado(@PathVariable("idEmpleado") Long idEmpleado) {
		empleadosService.deleteEmpleado(idEmpleado);
	}
}
