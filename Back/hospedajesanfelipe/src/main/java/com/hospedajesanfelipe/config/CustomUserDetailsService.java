package com.hospedajesanfelipe.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.hospedajesanfelipe.entity.EmpleadoEntity;
import com.hospedajesanfelipe.repository.EmpleadosRepository;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private EmpleadosRepository empleadosRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        EmpleadoEntity empleado = empleadosRepository.findByUserName(username)
            .orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado: " + username));

        return org.springframework.security.core.userdetails.User.builder()
            .username(empleado.getUserName())
            .password(empleado.getContrasenia())
            .roles(empleado.getRol().getTipo())
            .build();
    }
}