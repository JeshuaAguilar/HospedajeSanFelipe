package com.hospedajesanfelipe.response;

import java.io.Serializable;

import com.hospedajesanfelipe.entity.CatRolEntity;

public class LoginResponse implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long idEmpleado;
	private String userName;
	private String nombre;
	private CatRolEntity rol;
	
	public Long getIdEmpleado() {
		return idEmpleado;
	}
	public void setIdEmpleado(Long idEmpleado) {
		this.idEmpleado = idEmpleado;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public CatRolEntity getRol() {
		return rol;
	}
	public void setRol(CatRolEntity rol) {
		this.rol = rol;
	}
}
