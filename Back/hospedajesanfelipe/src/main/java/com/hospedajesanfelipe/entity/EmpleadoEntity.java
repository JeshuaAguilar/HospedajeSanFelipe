package com.hospedajesanfelipe.entity;

import java.util.Collection;
import java.util.Collections;
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

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "empleados")
public class EmpleadoEntity implements UserDetails {
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_empleado", updatable = false)
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
	@Column(name = "telefono")
	private String noTelefono;
	@ManyToOne
    @JoinColumn(name = "fk_rol")
	private CatRolEntity rol;
	@OneToMany(mappedBy = "empleado")
	private List<ComentarioEntity> comentarios;
	@OneToMany(mappedBy = "empleado")
	@JsonIgnore
	private List<ReservacionEntity> reservaciones;
	@Column(name = "url_foto")
	private String urlFoto;
	
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        if (rol == null) {
            return Collections.emptyList();
        }
        return Collections.singletonList(new SimpleGrantedAuthority(rol.getTipo()));
    }
    
    @Override
    public String getUsername() {
        return userName;
    }
    
    @Override
	public String getPassword() {
		return contrasenia;
	}

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
	
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
