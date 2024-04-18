
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

export interface HabitacionRequest{
	noHabitacion  :  number     ;
	noOcupante    :  number     ;
	noMaxOcupante :  number     ;
	urlFoto       :  string     ;
  piso          :  number     ;
	estado        :  number     ;
  idHabitacion  ?:  number    ;
}

export interface HabitacionResponse{
  idHabitacion  :  number              ;
  noHabitacion  :  number              ;
  noOcupante    :  number              ;
  noMaxOcupante :  number              ;
  urlFoto       :  string              ;
  piso          :  CatPiso             ;
  estado        :  CatEstadoHabitacion ;
}

