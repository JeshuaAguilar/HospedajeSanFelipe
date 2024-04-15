package com.hospedajesanfelipe.utils;

import java.security.Key;
import java.util.Base64;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

public class EncriptaPass {

	public static void main(String[] args) {
		PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		String encodedPassword = passwordEncoder.encode("emmanuel");
		System.out.println(encodedPassword);
		
		 Key secretKey = Keys.secretKeyFor(SignatureAlgorithm.HS512);
        String secretKeyString = Base64.getEncoder().encodeToString(secretKey.getEncoded());
        System.out.println("Clave secreta generada: " + secretKeyString);
	}
}
