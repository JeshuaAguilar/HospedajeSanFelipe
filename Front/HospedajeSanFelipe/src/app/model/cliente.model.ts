export interface Cliente {
  nombre         : string;
  primerApellido : string;
  segundoApellido: string;
  telefono       : string;
  estado         : string;
  municipio      : string;
  idCliente      ?: number;
}

export interface ClienteRequest{
  nombre         : String;
  primerApellido : String;
  segundoApellido: String;
  telefono       : String;
  estado         : String;
  municipio      : String;
  idCliente     ?: number;
}


export interface ClienteResponse{
  idCliente      : number;
  nombre         : String;
  primerApellido : String;
  segundoApellido: String;
  telefono       : String;
  estado         : String;
  municipio      : String;
}
