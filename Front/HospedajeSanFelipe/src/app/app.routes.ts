import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AdministradoresComponent } from './components/administradores/administradores.component';
import { AppComponent } from './app.component';
import { PersonalComponent } from './components/personal/personal.component';
import { HabitacionesComponent } from './components/habitaciones/habitaciones.component';

export const routes: Routes = [
  { path: 'administradores', component: AdministradoresComponent },
  { path: 'personal', component: PersonalComponent },
  { path: 'habitaciones', component: HabitacionesComponent },

  // { path: '', redirectTo: '/administradores', pathMatch: 'full' }, // redirect to `first-component`
  // { path: '**', component: AdministradoresComponent }, // Redirecciona a una página cuando no encuentra el path
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule],
})
export class AppRoutingModule {}
