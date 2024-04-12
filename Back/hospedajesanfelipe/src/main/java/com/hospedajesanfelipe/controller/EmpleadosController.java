package com.hospedajesanfelipe.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.hospedajesanfelipe.entity.EmpleadoEntity;
import com.hospedajesanfelipe.service.EmpleadosService;

@RestController
@RequestMapping("/hospedaje/api/empleados")
public class EmpleadosController {
	
	@Autowired
	EmpleadosService empleadosService;
	
	@GetMapping()
	public List<EmpleadosController> allAdministradores(){
//		return repository.findAll();
		return null;
	}
	
	@PostMapping()
	public EmpleadoEntity createEmpleado(@RequestBody EmpleadoEntity Empleado) {
//		return repository.save(administrador);
		return null;
	}
	
	@PutMapping("/{idEmpleado}")
	public EmpleadoEntity updateEmpleado(@PathVariable int idEmpleado ,@RequestBody EmpleadoEntity Empleado) {
//		return repository.save(person);
		return null;
	}
	
	@DeleteMapping("/{idEmpleado}")
	public void deleteEmpleado(@PathVariable("idEmpleado") Long idEmpleado) {
//		repository.deleteById(id);
	}
}
