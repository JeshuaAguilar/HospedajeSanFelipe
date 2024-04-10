package com.hospedajesanfelipe.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "habitaciones")
public class HabitacionEntity {
	
		@Id
		@GeneratedValue(strategy = GenerationType.IDENTITY)
		@Column(name = "id_habitacion")
		private Long idHabitacion;
		@Column(name = "no_habitacion")
		private String noHabitacion;
		@Column(name = "no_ocupantes")
		private int noOcupantes;
		@Column(name = "no_max_ocupantes")
		private int noMaxOcupante;
		@Column(name = "fk_piso")
		private Long fkPiso;
		@Column(name = "fk_estado")
		private Long fkEstado;
		@Column(name = "url_foto")
		private String urlFoto;
		
		
		public Long getIdHabitacion() {
			return idHabitacion;
		}
		public void setIdHabitacion(Long idHabitacion) {
			this.idHabitacion = idHabitacion;
		}
		public String getNoHabitacion() {
			return noHabitacion;
		}
		public void setNoHabitacion(String noHabitacion) {
			this.noHabitacion = noHabitacion;
		}
		public int getNoOcupantes() {
			return noOcupantes;
		}
		public void setNoOcupantes(int noOcupantes) {
			this.noOcupantes = noOcupantes;
		}
		public int getNoMaxOcupante() {
			return noMaxOcupante;
		}
		public void setNoMaxOcupante(int noMaxOcupante) {
			this.noMaxOcupante = noMaxOcupante;
		}
		public Long getFkPiso() {
			return fkPiso;
		}
		public void setFkPiso(Long fkPiso) {
			this.fkPiso = fkPiso;
		}
		public Long getFkEstado() {
			return fkEstado;
		}
		public void setFkEstado(Long fkEstado) {
			this.fkEstado = fkEstado;
		}
		public String getUrlFoto() {
			return urlFoto;
		}
		public void setUrlFoto(String urlFoto) {
			this.urlFoto = urlFoto;
		}
}
