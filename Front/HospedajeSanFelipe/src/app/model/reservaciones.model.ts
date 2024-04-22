import { Cliente } from "./cliente.model";
import { EmpleadoRequest } from "./empleados";
import { Habitacion } from './habitacion.model';

export class Reservacion {
  idReservacion : number;
  fechaEntrada  : Date;
  fechaSalida   : Date;
  noPersonas    : number;
  noPersonaExtra: number;
  total         : number;
  estado        : string;
  cliente       : Cliente;
  empleado      : EmpleadoRequest;
  // comentario    : Comentario;
  precioEspecial: CatPrecioEspecial;
  habitaciones  : Habitacion[];
}

export class ReservacionRequest {
  // idReservacion : number;
  fechaEntrada    : Date;
  fechaSalida     : Date;
  noPersonas      : number;
  noPersonaExtra  : number;
  total           : number;
  estado          : number;
  idCliente       : number;
  idEmpleado      : number;
  idComentario    : number;
  idPrecioEspecial: number;
  habitaciones    : Habitacion[];
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
