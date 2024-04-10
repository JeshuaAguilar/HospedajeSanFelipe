package com.hospedajesanfelipe.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "cat_precios_extra")
public class CatPrecioExtraEntity {
		@Id
		@GeneratedValue(strategy = GenerationType.IDENTITY)
		@Column(name = "id_precio")
		private	Long idPrecio;
		@Column(name = "precio")
		private int precio;
		
		
		public Long getIdPrecio() {
			return idPrecio;
		}
		public void setIdPrecio(Long idPrecio) {
			this.idPrecio = idPrecio;
		}
		public int getPrecio() {
			return precio;
		}
		public void setPrecio(int precio) {
			this.precio = precio;
		}
}
