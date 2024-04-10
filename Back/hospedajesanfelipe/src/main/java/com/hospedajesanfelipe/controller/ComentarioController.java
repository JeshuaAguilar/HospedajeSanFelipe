package com.hospedajesanfelipe.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/hospedaje/api/comentarios")
public class ComentarioController {
	@GetMapping("/persons")
	public List<Person> allPersons(){
		return repository.findAll();
	}
	
	@PostMapping("/person")
	public Person createPerson(@RequestBody Person person) {
		return repository.save(person);
	}
	
	@PutMapping("/person/{id}")
	public Person updatePerson(@PathVariable int id ,@RequestBody Person person) {
		return repository.save(person);
	}
	
	@DeleteMapping("/person/{id}")
	public void deletePerson(@PathVariable("id") Long id) {
		repository.deleteById(id);
	}
}
