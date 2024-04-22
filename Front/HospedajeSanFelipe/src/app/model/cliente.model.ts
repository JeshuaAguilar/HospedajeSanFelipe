export class Cliente {
  nombre         : string;
  primerApellido : string;
  segundoApellido: string;
  telefono       : string;
  estado         : string;
  municipio      : string;
  idCliente      ?: number;
}

export interface ClienteResponse{
  idCliente      : number;
  nombre         : string;
  primerApellido : string;
  segundoApellido: string;
  telefono       : string;
  estado         : string;
  municipio      : string;
}
