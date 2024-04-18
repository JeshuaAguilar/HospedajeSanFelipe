import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

import { ComentariosComponent } from './components/administrador/comentarios/comentarios.component';
import { HabitacionesComponent } from './components/administrador/habitaciones/habitaciones.component';
import { InicioComponent } from './components/administrador/inicio/inicio.component';
import { ReservacionesComponent } from './components/administrador/reservaciones/reservaciones.component';
import { EmpleadosComponent } from './components/administrador/empleados/empleados.component';
import { LoginComponent } from './components/administrador/login/login.component';
import { ClientesComponent } from './components/administrador/clientes/clientes.component';
import { InicioClienteComponent } from './components/cliente/inicio-cliente/inicio-cliente.component';

export const routes: Routes = [
  { path: 'inicio', component: InicioComponent },
  { path: 'login', component: LoginComponent },
  { path: 'empleados', component: EmpleadosComponent },
  { path: 'habitaciones', component: HabitacionesComponent },
  { path: 'comentarios', component: ComentariosComponent },
  { path: 'reservaciones', component: ReservacionesComponent },
  { path: 'clientes', component: ClientesComponent },

  { path: 'inicioCliente', component: InicioClienteComponent },

  { path: '', redirectTo: 'Login', pathMatch: 'full' }, // redirect to `first-component`
  { path: '**', component: LoginComponent }, // Redirecciona a una página cuando no encuentra el path
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule],
})
export class AppRoutingModule {}
