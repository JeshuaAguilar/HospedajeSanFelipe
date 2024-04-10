package com.hospedajesanfelipe.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "comentarios")
public class ComentarioEntity {
		@Id
		@GeneratedValue(strategy = GenerationType.IDENTITY)
		@Column(name = "id_comentario")
		private Long idComentario;
		@Column(name = "comentario")
		private String comentario;
		@Column(name = "fk_cliente")
		private Long fkCliente;
		@Column(name = "fk_empleado")
		private Long fkEmpleado;
		
		public Long getIdComentario() {
			return idComentario;
		}
		public void setIdComentario(Long idComentario) {
			this.idComentario = idComentario;
		}
		public String getComentario() {
			return comentario;
		}
		public void setComentario(String comentario) {
			this.comentario = comentario;
		}
		public Long getFkCliente() {
			return fkCliente;
		}
		public void setFkCliente(Long fkCliente) {
			this.fkCliente = fkCliente;
		}
		public Long getFkEmpleado() {
			return fkEmpleado;
		}
		public void setFkEmpleado(Long fkEmpleado) {
			this.fkEmpleado = fkEmpleado;
		}
}
