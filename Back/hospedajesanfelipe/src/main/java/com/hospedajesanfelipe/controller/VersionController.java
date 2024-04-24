package com.hospedajesanfelipe.controller;

import org.json.JSONObject;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/hospedaje/api/version")
public class VersionController {
	
	@GetMapping()
	public String getVersion() {
		JSONObject json = new JSONObject();
		json.put("Versión: ", "v1.0");
		json.put("Fecha: ", "23/04/2024");
		json.put("Modifica: ", "Jeshua");
		
		return json.toString();
	}
}
