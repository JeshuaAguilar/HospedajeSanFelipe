import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

import { ComentariosComponent } from './components/Administrador/comentarios/comentarios.component';
import { HabitacionesComponent } from './components/Administrador/habitaciones/habitaciones.component';
import { InicioComponent } from './components/Administrador/inicio/inicio.component';
import { ReservacionesComponent } from './components/Administrador/reservaciones/reservaciones.component';
import { EmpleadosComponent } from './components/Administrador/empleados/empleados.component';
import { LoginComponent } from './components/Administrador/login/login.component';
import { ClientesComponent } from './components/Administrador/clientes/clientes.component';

export const routes: Routes = [
  { path: 'inicio', component: InicioComponent },
  { path: 'login', component: LoginComponent },
  { path: 'empleados', component: EmpleadosComponent },
  { path: 'habitaciones', component: HabitacionesComponent },
  { path: 'comentarios', component: ComentariosComponent },
  { path: 'reservaciones', component: ReservacionesComponent },
  { path: 'clientes', component: ClientesComponent },

  { path: '', redirectTo: 'Login', pathMatch: 'full' }, // redirect to `first-component`
  { path: '**', component: LoginComponent }, // Redirecciona a una página cuando no encuentra el path
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule],
})
export class AppRoutingModule {}
