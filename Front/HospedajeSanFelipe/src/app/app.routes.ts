import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

// import { ComentariosComponent } from './components/administrador/comentarios/comentarios.component';
import { HabitacionesComponent } from './components/administrador/habitaciones/habitaciones.component';
import { InicioComponent } from './components/administrador/inicio/inicio.component';
import { ReservacionesComponent } from './components/administrador/reservaciones/reservaciones.component';
import { EmpleadosComponent } from './components/administrador/empleados/empleados.component';
import { LoginComponent } from './components/administrador/login/login.component';
import { ClientesComponent } from './components/administrador/clientes/clientes.component';
import { InicioClienteComponent } from './components/cliente/inicio-cliente/inicio-cliente.component';
import { ImagenesComponent } from './components/administrador/shared/imagenes/imagenes.component';
import { authGuard } from './guards/auth.guard';

export const routes: Routes = [
  { path: 'login',         component: LoginComponent },
  { path: 'inicio',        component: InicioComponent,        canActivate: [authGuard]},
  { path: 'empleados',     component: EmpleadosComponent,     canActivate: [authGuard]},
  { path: 'habitaciones',  component: HabitacionesComponent,  canActivate: [authGuard]},
  // { path: 'comentarios',   component: ComentariosComponent,   canActivate: [authGuard]},
  { path: 'reservaciones', component: ReservacionesComponent, canActivate: [authGuard]},
  { path: 'clientes',      component: ClientesComponent,      canActivate: [authGuard]},

  { path: 'imagenes', component: ImagenesComponent },

  { path: 'inicioCliente', component: InicioClienteComponent },

  { path: '', redirectTo: 'login', pathMatch: 'full' }, // redirect to `first-component`
  { path: '**', component: LoginComponent }, // Redirecciona a una página cuando no encuentra el path
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule],
})
export class AppRoutingModule {}
