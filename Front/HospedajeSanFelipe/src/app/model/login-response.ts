export interface LoginResponse {
  idEmpleado: number;
  username  : string;
  nombre    : string;
  rol       : Rol;
  token     : string;
}

export interface Rol {
	idRol      : number;
	tipo       : string;
	descripcion: string;
}
