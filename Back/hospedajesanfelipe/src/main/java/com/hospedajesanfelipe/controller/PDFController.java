package com.hospedajesanfelipe.controller;

import java.io.ByteArrayInputStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hospedajesanfelipe.service.ClientesService;
import com.hospedajesanfelipe.service.EmpleadosService;
import com.hospedajesanfelipe.service.ReservacionesService;

@RestController
@RequestMapping("/hospedaje/api/pdf")
public class PDFController {

	@Autowired
	ClientesService clientesService;
	
	@Autowired
	EmpleadosService empleadosService;
	
	@Autowired
	ReservacionesService reservacionesService;
	
	@GetMapping(value = "/clientes", produces = MediaType.APPLICATION_PDF_VALUE)
    public ResponseEntity<InputStreamResource> getClientesPdf() {
        ByteArrayInputStream bis = empleadosService.getEmpleadosPdf();

        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=reporte_clientes.pdf");

        return ResponseEntity
                .ok()
                .headers(headers)
                .contentType(MediaType.APPLICATION_PDF)
                .body(new InputStreamResource(bis));
    }

	@GetMapping(value = "/empleados", produces = MediaType.APPLICATION_PDF_VALUE)
	public ResponseEntity<InputStreamResource> getEmpleadosPdf() {
		ByteArrayInputStream bis = empleadosService.getEmpleadosPdf();
		
		HttpHeaders headers = new HttpHeaders();
		headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=reporte_empleados.pdf");
		
		return ResponseEntity
				.ok()
				.headers(headers)
				.contentType(MediaType.APPLICATION_PDF)
				.body(new InputStreamResource(bis));
	}
	
	@GetMapping(value = "/reservaciones", produces = MediaType.APPLICATION_PDF_VALUE)
	public ResponseEntity<InputStreamResource> getReservacionesPdf() {
		ByteArrayInputStream bis = reservacionesService.getReservacionesPdf();
		
		HttpHeaders headers = new HttpHeaders();
		headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=reporte_empleados.pdf");
		
		return ResponseEntity
				.ok()
				.headers(headers)
				.contentType(MediaType.APPLICATION_PDF)
				.body(new InputStreamResource(bis));
	}
}
