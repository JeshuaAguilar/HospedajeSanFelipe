package com.hospedajesanfelipe.request;

import java.io.Serializable;

//Creo una clase empleado, únicamente con los valores que la tabla necesita insertar
//Tengo que implementrar el Serializable
//Se crea como request, porque esta información viene del front
public class EmpleadoRequest implements Serializable {

	//Este es el  mis para todos
	private static final long serialVersionUID = 1L;

	private Long idEmpleado;
	private String userName;
	private String contrasenia;
	private String nombre;
	private String primerApellido;
	private String segundoApellido;
	private String noTelefono;
	private Long rol;
	private String urlFoto;
	
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
	public String getContrasenia() {
		return contrasenia;
	}
	public void setContrasenia(String contrasenia) {
		this.contrasenia = contrasenia;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getPrimerApellido() {
		return primerApellido;
	}
	public void setPrimerApellido(String primerApellido) {
		this.primerApellido = primerApellido;
	}
	public String getSegundoApellido() {
		return segundoApellido;
	}
	public void setSegundoApellido(String segundoApellido) {
		this.segundoApellido = segundoApellido;
	}
	public String getNoTelefono() {
		return noTelefono;
	}
	public void setNoTelefono(String noTelefono) {
		this.noTelefono = noTelefono;
	}
	public Long getRol() {
		return rol;
	}
	public void setRol(Long rol) {
		this.rol = rol;
	}
	public String getUrlFoto() {
		return urlFoto;
	}
	public void setUrlFoto(String urlFoto) {
		this.urlFoto = urlFoto;
	}
}
