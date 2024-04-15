package com.hospedajesanfelipe.controller;

import java.util.HashMap;
import java.util.Map;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.hospedajesanfelipe.config.JwtUtil;
import com.hospedajesanfelipe.entity.EmpleadoEntity;
import com.hospedajesanfelipe.request.LoginRequest;
import com.hospedajesanfelipe.service.EmpleadosService;

@RestController
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtUtil jwtUtil;

    @SuppressWarnings("unused")
	@Autowired
    private UserDetailsService userDetailsService;
    
    @Autowired
    EmpleadosService empleadosService;

    @PostMapping("/hospedaje/api/empleados/login")
    public ResponseEntity<?> authenticate(@RequestBody LoginRequest loginRequest) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getUserName(), loginRequest.getPassword())
        );

        User principal = (User) authentication.getPrincipal();
        String jwt = jwtUtil.generateToken(principal.getUsername());
        
        EmpleadoEntity empleado = empleadosService.getEmpleadoByUserName(principal.getUsername());

        Map<String, String> response = new HashMap<>();
        
        response.put("idEmpleado", empleado.getIdEmpleado().toString());
        response.put("username", empleado.getUserName());
        response.put("nombre", empleado.getNombre().concat(" ".concat(empleado.getPrimerApellido().concat(" ".concat(empleado.getSegundoApellido())))));
        response.put("rol", new JSONObject(empleado.getRol()).toString());
        response.put("token", jwt);

        return ResponseEntity.ok(response);
    }
}
