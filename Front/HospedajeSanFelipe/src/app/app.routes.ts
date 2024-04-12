import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { UsuariosComponent } from './components/Administrador/usuarios/usuarios.component';
import { ComentariosComponent } from './components/Administrador/comentarios/comentarios.component';
import { HabitacionesComponent } from './components/Administrador/habitaciones/habitaciones.component';
import { InicioComponent } from './components/Administrador/inicio/inicio.component';
import { ReservacionesComponent } from './components/Administrador/reservaciones/reservaciones.component';
import { EmpleadosComponent } from './components/Administrador/empleados/empleados.component';

export const routes: Routes = [
  { path: 'inicio', component: InicioComponent },
  { path: 'empleados', component: EmpleadosComponent },
  { path: 'habitaciones', component: HabitacionesComponent },
  { path: 'usuarios', component: UsuariosComponent },
  { path: 'comentarios', component: ComentariosComponent },
  { path: 'reservaciones', component: ReservacionesComponent },

  { path: '', redirectTo: 'inicio', pathMatch: 'full' }, // redirect to `first-component`
  { path: '**', component: InicioComponent }, // Redirecciona a una página cuando no encuentra el path
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule],
})
export class AppRoutingModule {}
