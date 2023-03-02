import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { DatosClienteComponent } from './pages/body/datos-cliente/datos-cliente.component';
import { AppComponent } from './app.component';
import { HabitacionesComponent } from './pages/body/habitaciones/habitaciones.component';
import { CreaReservacionComponent } from './pages/body/crea-reservacion/crea-reservacion.component';
import { CuartosComponent } from './pages/body/cuartos/cuartos.component';

const routes: Routes = [
  { path: 'datosCliente', component: DatosClienteComponent},
  { path: 'habitaciones', component: HabitacionesComponent},
  { path: 'reservacion', component: CreaReservacionComponent},
  { path: 'cuarto', component: CuartosComponent},

  { path: '', component: AppComponent},
  { path: '**', pathMatch: 'full', redirectTo: 'datosCliente'}
];

@NgModule({
  imports: [RouterModule.forRoot(routes, { useHash: true })],
  exports: [RouterModule]
})
export class AppRoutingModule { }
