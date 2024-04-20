import { SafeUrl } from "@angular/platform-browser";
import { CatServicios } from "./servicios.model";

export interface Habitacion {
  idHabitacion : number;
  noHabitacion : string;
  noOcupantes  : number;
  noMaxOcupante: number;
  noCamasIndividuales  : number;
  noCamasMatrimoniales: number;
  costo         : number;
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
	noHabitacion  :  number;
	noOcupante    :  number;
	noMaxOcupante :  number;
  noCamasIndividuales  : number;
  noCamasMatrimoniales: number;
  costo         : number;
  piso          :  number;
	estado        :  number;
	urlFoto       ?: string;
  idHabitacion  ?: number;
}

export interface HabitacionResponse{
  idHabitacion  : number;
  noHabitacion  : number;
  noOcupante    : number;
  noMaxOcupante : number;
  noCamasIndividuales  : number;
  noCamasMatrimoniales: number;
  costo         : number;
  urlFoto       : string;
  piso          : CatPiso;
  estado        : CatEstadoHabitacion
}

export interface HabitacionClienteResponse {
  idHabitacion: number;
	noHabitacion: string;
	noOcupante  : number;
	urlFoto     : string;
  servicios   : CatServicios[];
  imagen      ?: SafeUrl;
}

export interface HabitacionEmpleadoResponse {
  idHabitacion  : number;
  noHabitacion  : number;
  noOcupante    : number;
  noMaxOcupante : number;
  urlFoto       : string;
  piso          : CatPiso;
  estado        : CatEstadoHabitacion
  servicios     : CatServicios[];
  imagen        ?: SafeUrl;
}
