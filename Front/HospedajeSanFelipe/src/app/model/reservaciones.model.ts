import { Cliente } from "./cliente.model";
import { EmpleadoRequest } from "./empleados";
import { Habitacion } from "./habitacion.model";

export interface Reservacion {
  idReservacion : number;
  fechaEntrada  : Date;
  fechaSalida   : Date;
  noPersonas    : number;
  noPersonaExtra: number;
  total         : number;
  estado        : string;
  cliente       : Cliente;
  empleado      : EmpleadoRequest;
  comentario    : Comentario;
  precioEspecial: CatPrecioEspecial;
  habitaciones  : Habitacion[];
}


export interface Comentario {
  idComentario: number;
	comentario  : string;
}

export interface CatPrecioEspecial {
  idPrecioEspecial: number;
	fecha_inicio    : Date;
	fecha_fin       : Date;
	precio          : number;
	descripcion     : string;
}
