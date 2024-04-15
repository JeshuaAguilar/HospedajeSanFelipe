
export interface Habitacion {
  idHabitacion : number;
  noHabitacion : string;
  noOcupantes  : number;
  noMaxOcupante: number;
  piso         : CatPiso;
  estado       : CatEstadoHabitacion;
  urlFoto      : string;
  servicios    ?: CatServicio[];
}

export interface CatServicio {
  idServicio : number;
	descripcion: string;
}

export interface CatEstadoHabitacion {
  idEstado   : number;
	descripcion: string;
}

export interface CatPiso {
  idPiso     : number;
  descripcion: string;
}
