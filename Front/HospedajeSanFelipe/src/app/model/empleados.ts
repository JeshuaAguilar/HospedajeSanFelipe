export interface EmpleadoRequest {
  userName       : string;
  contrasenia    : string;
  nombre         : string;
  primerApellido : string;
  segundoApellido: string;
  noTelefono     : string;
  rol            : number;
  urlFoto        : string;
  idEmpleado     ?: number;
}

export interface EmpleadoResponse {
  idEmpleado     : number;
	userName       : string;
	nombre         : string;
	primerApellido : string;
	segundoApellido: string;
	noTelefono     : string;
	rol            : CatRol;
}

export interface CatRol {
  idRol      : number;
	tipo       : string;
	descripcion: string;
}


