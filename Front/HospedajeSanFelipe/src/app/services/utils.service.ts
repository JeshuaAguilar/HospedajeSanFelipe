import { Injectable, inject } from '@angular/core';
import { LoginResponse } from '../model/login-response';
import { CatRol } from '../model/empleados';
import { Router } from '@angular/router';

@Injectable({
  providedIn: 'root'
})
export class UtilsService {

  router = inject(Router);

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

  public cerrarSesion(): void {
    sessionStorage.clear();
    this.router.navigateByUrl('/login');
  }
}
