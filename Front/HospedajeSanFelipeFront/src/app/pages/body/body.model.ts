export class Select {
  idx : number;
  desc: string;
}

export class EstadosMunicipios {
  estado    : string;
  municipios: string[];
}

export class Piso {
  idPiso  : number;
  descPiso: string;
  habitaciones: Habitacion[];
}

export class Habitacion {
  idHabitacion : number;
  noHabitacion : string;
  piso         : number;
  noMatrimonial: number;
  noIndividual : number;
  maxPersonas  : number;
  aguacaliente : 1;
  ventilador   : 1;
  banio        : 1;
  internet     : 1;
  television   : 1;
  aireC        : 0;
  costo        : number;
  costoFeriado : number;
  costoExtraPp : number;
}

export class Cliente {
  idCliente     : number;
  nombre        : string;
  apellido      : string;
  estado        : string;
  municipio     : string;
  comentarios   : ComentarioCliente[];
}

export class Reservacion {
  idReservacion: number;
  cliente      : Cliente;
  habitacion   : Habitacion;
  empleado     : Empleado;
  pago         : Pago;
  fechaRegistro: Date;
  noPersonas   : number;
  extraPersonas: number;
  noToallas    : number;
  fechaEntrada : Date;
  fechaSalida  : Date;
  estatus      : string;
}

export class ComentarioCliente {
  idComentario: number;
  cliente     : Cliente;
  comentario  : string;
}

export class Empleado {
  idEmpleado: number;
  nombre    : string;
  apellido  : string;
  perfil    : string;
}

export class Pago {
  idPago    : number;
  tipoPago  : string;
  total     : number;
  totalExtra: number;
}

export class Parametro {
  idParametro: number;
  valor      : string;
  descripcion: string;
}
