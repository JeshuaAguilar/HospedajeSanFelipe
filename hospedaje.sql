-- Eliminar la base de datos
DROP DATABASE IF EXISTS HospedajeSanFelipe;

-- Crear la base de datos de HospedajeSanFelipe
CREATE DATABASE IF NOT EXISTS HospedajeSanFelipe;

USE HospedajeSanFelipe;

-- -----------------------------------------------------
-- Table hotel_san_felipe.estado_habitacion
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS cat_estados_habitaciones (
  id_estado INT UNSIGNED NOT NULL AUTO_INCREMENT,
  descripcion VARCHAR(15) NOT NULL,
  PRIMARY KEY (id_estado)
) ENGINE = InnoDB;

insert into cat_estados_habitaciones(id_estado, descripcion) values (1, "Disponible");
insert into cat_estados_habitaciones(id_estado, descripcion) values (2, "Reservado");
insert into cat_estados_habitaciones(id_estado, descripcion) values (3, "Ocupado");

-- -----------------------------------------------------
-- Table hotel_san_felipe.piso
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS cat_pisos (
  id_piso INT UNSIGNED NOT NULL AUTO_INCREMENT,
  descripcion VARCHAR(15) NOT NULL,
  PRIMARY KEY (id_piso)
) ENGINE = InnoDB;

insert into cat_pisos(id_piso, descripcion) values (1, "Primer Piso");
insert into cat_pisos(id_piso, descripcion) values (2, "Segundo Piso");
insert into cat_pisos(id_piso, descripcion) values (3, "Tercer Piso");

-- -----------------------------------------------------
-- Table hotel_san_felipe.capacidad_precio
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS cat_precios_extra (
  id_precio INT UNSIGNED NOT NULL AUTO_INCREMENT,
  precio DECIMAL(10,2) UNSIGNED NOT NULL,
  descripcion VARCHAR(30) NOT NULL,
  PRIMARY KEY (id_precio)
) ENGINE = InnoDB;

insert into cat_precios_extra(id_precio, precio, descripcion) values (1, 50, "Precio por persona extra");

-- -----------------------------------------------------
-- Table hotel_san_felipe.precios_especiales
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS cat_precios_especiales (
  id_precio_especial INT UNSIGNED NOT NULL AUTO_INCREMENT,
  fecha_inicio DATE NOT NULL,
  fecha_fin DATE NOT NULL,
  precio DECIMAL(10, 2),
  descripcion VARCHAR(50),
  PRIMARY KEY (id_precio_especial)
) ENGINE = InnoDB;

insert into cat_precios_especiales(id_precio_especial, fecha_inicio, fecha_fin, precio, descripcion) values (1, NOW(), NOW(), 550, "Feria Semana Santa");

-- -----------------------------------------------------
-- Table hotel_san_felipe.servicios
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS cat_servicios (
  id_servicio INT UNSIGNED NOT NULL AUTO_INCREMENT,
  descripcion VARCHAR(45) NOT NULL,
  PRIMARY KEY (id_servicio)
) ENGINE = InnoDB;

insert into cat_servicios(id_servicio, descripcion) values (1, "Ventildor");
insert into cat_servicios(id_servicio, descripcion) values (2, "Agua Caliente");

-- -----------------------------------------------------
-- Table hotel_san_felipe.rol
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS cat_roles (
  id_rol INT UNSIGNED NOT NULL AUTO_INCREMENT,
  tipo VARCHAR(45) NOT NULL,
  descripcion VARCHAR(45) NOT NULL,
  PRIMARY KEY (id_rol)
) ENGINE = InnoDB;

insert into cat_roles(id_rol, tipo, descripcion) values (1, "ADMIN", "Administrador de sistema");
insert into cat_roles(id_rol, tipo, descripcion) values (2, "VENDEDOR", "Vendedor general");

-- -----------------------------------------------------
-- Table hotel_san_felipe.clientes
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS clientes (
  id_cliente INT UNSIGNED NOT NULL AUTO_INCREMENT,
  nombre VARCHAR(45) NULL,
  primer_apellido VARCHAR(45) NULL,
  segundo_apellido VARCHAR(45) NULL,
  telefono VARCHAR(10) NULL,
  estado VARCHAR(45) NULL,
  municipio VARCHAR(45) null,
  PRIMARY KEY (id_cliente)
) ENGINE = InnoDB;

insert into clientes(id_cliente, nombre, primer_apellido, segundo_apellido, telefono, estado, municipio) 
values (1, "Daniel Alejandro", "Trujillo", "López", "9191234568", "Chiapas", "Tila");

-- -----------------------------------------------------
-- Table hotel_san_felipe.habitacion
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS habitaciones (
  id_habitacion INT UNSIGNED NOT NULL AUTO_INCREMENT,
  no_habitacion VARCHAR(4) NOT NULL,
  no_ocupantes INT UNSIGNED NOT NULL,
  no_max_ocupantes INT UNSIGNED NOT NULL,
  no_camas_individuales INT UNSIGNED NOT NULL,
  no_camas_matrimoniales INT UNSIGNED NOT NULL,
  costo DECIMAL(10,2) UNSIGNED NOT NULL,
  fk_piso INT UNSIGNED NOT NULL,
  fk_estado INT UNSIGNED NOT NULL,
  url_foto VARCHAR(255) NOT NULL,
  PRIMARY KEY (id_habitacion),
  FOREIGN KEY (fk_piso) REFERENCES cat_pisos(id_piso)
  ON DELETE NO ACTION
  ON UPDATE NO ACTION,
  FOREIGN KEY (fk_estado) REFERENCES cat_estados_habitaciones(id_estado)
  ON DELETE NO ACTION
  ON UPDATE NO ACTION
) ENGINE = InnoDB;

INSERT INTO habitaciones (no_habitacion, no_ocupantes, no_max_ocupantes, no_camas_individuales, no_camas_matrimoniales, costo, fk_piso, fk_estado, url_foto)
VALUES 
('01', 2, 2, 1, 0, 100.00, 1, 2, 'habitacion_1.jpg'),
('02', 4, 4, 2, 1, 150.00, 2, 3, 'habitacion_2.jpg'),
('03', 4, 5, 1, 1, 120.00, 3, 2, 'habitacion_3.jpg'),
('04', 3, 4, 1, 1, 300.00, 3, 1, 'habitacion_4.jpg');


-- -----------------------------------------------------
-- Table hotel_san_felipe.trabajo_empleado
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS empleados (
  id_empleado INT UNSIGNED NOT NULL AUTO_INCREMENT,
  user_name VARCHAR(20) NULL,
  contrasenia VARCHAR(200) NULL,
  nombre VARCHAR(45) NULL,
  primer_apellido VARCHAR(45) NULL,
  segundo_apellido VARCHAR(45) NULL,
  telefono VARCHAR(10) NULL,
  fk_rol INT UNSIGNED NULL,
  url_foto varchar(255) null,
  PRIMARY KEY (id_empleado),
  FOREIGN KEY (fk_rol) REFERENCES cat_roles(id_rol)
  ON DELETE NO ACTION
  ON UPDATE NO action
) ENGINE = InnoDB;

insert into empleados(id_empleado, user_name, contrasenia, nombre, primer_apellido, segundo_apellido, telefono, fk_rol, url_foto) 
values (1, "angel123", "$2a$10$11lyeP0u/t.Nyk0XnQeo0.v1srm7hI8sLgkEOmXpR/W9SQEQe5vAO", "Ángel Emmanuel", "Trujillo", "López", "9198524678", 1, "");

-- -----------------------------------------------------
-- Table hotel_san_felipe.comentarios
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS comentarios (
  id_comentario INT UNSIGNED NOT NULL AUTO_INCREMENT,
  comentario VARCHAR(45) NOT null,
  fk_cliente INT UNSIGNED NOT NULL,
  fk_empleado INT UNSIGNED NOT NULL,
  PRIMARY KEY (id_comentario),
  FOREIGN KEY (fk_cliente) REFERENCES clientes(id_cliente)
  ON DELETE NO ACTION
  ON UPDATE NO action,
  FOREIGN KEY (fk_empleado) REFERENCES empleados(id_empleado)
  ON DELETE NO ACTION
  ON UPDATE NO action
) ENGINE = InnoDB;
-- -----------------------------------------------------
-- Table hotel_san_felipe.reservaciones
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS reservaciones (
  id_reservacion INT UNSIGNED NOT NULL AUTO_INCREMENT,
  fecha_entrada DATE NOT NULL,
  fecha_salida DATE NOT NULL,
  no_personas INT UNSIGNED NOT NULL,
  no_personas_extra INT UNSIGNED NULL,
  total DECIMAL(10,2) UNSIGNED NOT NULL,
  fk_id_estado INT UNSIGNED NOT NULL,
  fk_id_cliente INT UNSIGNED NOT NULL,
  fk_id_empleado INT UNSIGNED NOT NULL,
  fk_id_comentario INT UNSIGNED NULL,
  fk_id_precio_especial INT UNSIGNED NULL,
  PRIMARY KEY (id_reservacion),
  FOREIGN KEY (fk_id_estado) REFERENCES cat_estados_habitaciones(id_estado)
  ON DELETE NO ACTION
  ON UPDATE NO action,
  FOREIGN KEY (fk_id_cliente) REFERENCES clientes(id_cliente)
  ON DELETE NO ACTION
  ON UPDATE NO action,
  FOREIGN KEY (fk_id_empleado) REFERENCES empleados(id_empleado)
  ON DELETE NO ACTION
  ON UPDATE NO action,
  FOREIGN KEY (fk_id_comentario) REFERENCES comentarios(id_comentario)
  ON DELETE NO ACTION
  ON UPDATE NO action,
  FOREIGN KEY (fk_id_precio_especial) REFERENCES cat_precios_especiales(id_precio_especial)
  ON DELETE NO ACTION
  ON UPDATE NO ACTION
) ENGINE = InnoDB;

CREATE TABLE IF NOT EXISTS reservaciones_habitaciones (
  id_reservacion INT UNSIGNED NOT NULL,
  id_habitacion INT UNSIGNED NOT NULL,
  PRIMARY KEY (id_reservacion, id_habitacion),
  FOREIGN KEY (id_reservacion) REFERENCES reservaciones(id_reservacion)
  ON DELETE CASCADE
  ON UPDATE CASCADE,
  FOREIGN KEY (id_habitacion) REFERENCES habitaciones(id_habitacion)
  ON DELETE CASCADE
  ON UPDATE CASCADE
) ENGINE = InnoDB;

CREATE TABLE IF NOT EXISTS habitaciones_servicios (
  id_habitacion_servicio INT UNSIGNED NOT NULL,  
  id_servicio INT UNSIGNED NOT NULL,
  PRIMARY KEY (id_habitacion_servicio, id_servicio),
  FOREIGN KEY (id_habitacion_servicio) REFERENCES habitaciones(id_habitacion)
  ON DELETE CASCADE
  ON UPDATE cascade,
  FOREIGN KEY (id_servicio) REFERENCES cat_servicios(id_servicio)
  ON DELETE CASCADE
  ON UPDATE CASCADE  
) ENGINE = InnoDB;

insert into habitaciones_servicios(id_habitacion_servicio, id_servicio) values (1, 1);
insert into habitaciones_servicios(id_habitacion_servicio, id_servicio) values (1, 2);
