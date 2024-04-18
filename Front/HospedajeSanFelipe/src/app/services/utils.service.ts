import { Injectable } from '@angular/core';
import { LoginResponse } from '../model/login-response';
import { CatRol } from '../model/empleados';

@Injectable({
  providedIn: 'root'
})
export class UtilsService {

  constructor() { }

  public get isLogged() : boolean {
    if (sessionStorage.getItem("token")) {
      return true;
    } else {
      return false;
    }
  }

  public getRolUser(): string {
    const user:LoginResponse = JSON.parse(sessionStorage.getItem("user"));
    const rol: CatRol = JSON.parse(`${user.rol}`);
    return rol.tipo;
  }
}
