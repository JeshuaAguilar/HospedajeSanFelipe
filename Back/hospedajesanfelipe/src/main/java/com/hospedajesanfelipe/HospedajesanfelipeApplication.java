package com.hospedajesanfelipe;

import javax.annotation.Resource;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.hospedajesanfelipe.service.ImagenesService;

@SpringBootApplication
public class HospedajesanfelipeApplication implements CommandLineRunner {

	@Resource
	ImagenesService imagenesService;
	
	public static void main(String[] args) {
		SpringApplication.run(HospedajesanfelipeApplication.class, args);
	}
	
	@Override
	public void run(String... arg) throws Exception {
		imagenesService.init();
	}
}
