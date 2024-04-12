package com.hospedajesanfelipe.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "empleados")
public class EmpleadoEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_empleado")
	private Long idEmpleado;
	@Column(name = "user_name")
	private String userName;
	@Column(name = "contrasenia")
	private String contrasenia;
	@Column(name = "nombre")
	private String nombre;
	@Column(name = "primer_apellido")
	private String primerApellido;
	@Column(name = "segundo_apellido")
	private String segundoApellido;
	@Column(name = "no_telefono")
	private long noTelefono;
	@ManyToOne
    @JoinColumn(name = "fk_rol")
	private CatRolEntity rol;
	@OneToMany(mappedBy = "empleado")
	private List<ComentarioEntity> comentarios;
	@OneToMany(mappedBy = "empleado")
	private List<ReservacionEntity> reservaciones;
	@Column(name = "url_foto")
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
	public long getNoTelefono() {
		return noTelefono;
	}
	public void setNoTelefono(long noTelefono) {
		this.noTelefono = noTelefono;
	}
	public CatRolEntity getRol() {
		return rol;
	}
	public void setRol(CatRolEntity rol) {
		this.rol = rol;
	}
	public List<ComentarioEntity> getComentarios() {
		return comentarios;
	}
	public void setComentarios(List<ComentarioEntity> comentarios) {
		this.comentarios = comentarios;
	}
	public List<ReservacionEntity> getReservaciones() {
		return reservaciones;
	}
	public void setReservaciones(List<ReservacionEntity> reservaciones) {
		this.reservaciones = reservaciones;
	}
	public String getUrlFoto() {
		return urlFoto;
	}
	public void setUrlFoto(String urlFoto) {
		this.urlFoto = urlFoto;
	}
}
