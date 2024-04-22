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

  public get isLogged(): boolean {
    return !!sessionStorage.getItem("token");
  }

  public getRolUser(): string {
    const user: LoginResponse = JSON.parse(sessionStorage.getItem("user"));
    const rol: CatRol = JSON.parse(`${user.rol}`);
    return rol.tipo;
  }

  public getUserName(): string {
    const user: LoginResponse = JSON.parse(sessionStorage.getItem("user"));
    const firstName: string = this.getFirstName(user.nombre);
    const rol: string = this.getRolUser();
    return `Bienvenido, ${rol} ${firstName}`;
  }

  private getFirstName(fullName: string): string {
    // Extraer el primer nombre de la cadena del nombre completo
    return fullName.split(' ')[0];
  }

  public cerrarSesion(): void {
    sessionStorage.clear();
    this.router.navigateByUrl('/login');
  }
}
