package com.hospedajesanfelipe.entity;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "cat_pisos")
public class CatPisoEntity {
	
		@Id
		@Column(name = "id_piso")
		private Long idPiso;
		@Column(name = "descripcion")
		private String descripcion;
		
		public Long getIdPiso() {
			return idPiso;
		}
		public void setIdPiso(Long idPiso) {
			this.idPiso = idPiso;
		}
		public String getDescripcion() {
			return descripcion;
		}
		public void setDescripcion(String descripcion) {
			this.descripcion = descripcion;
		}
}
