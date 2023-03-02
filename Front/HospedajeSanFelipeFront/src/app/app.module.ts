import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';

import { PrimeNgModule } from './prime-ng/prime-ng.module';
import { AngularMaterialModule } from './angular-material/angular-material.module';
import { FullCalendarModule } from '@fullcalendar/angular';
import { DatosClienteComponent } from './pages/body/datos-cliente/datos-cliente.component';
import { CardHabitacionComponent } from './pages/shared/card-habitacion/card-habitacion.component';
import { HabitacionesComponent } from './pages/body/habitaciones/habitaciones.component';
import { CreaReservacionComponent } from './pages/body/crea-reservacion/crea-reservacion.component';
import { CardCuartoComponent } from './pages/shared/card-cuarto/card-cuarto.component';
import { CuartosComponent } from './pages/body/cuartos/cuartos.component';

@NgModule({
  declarations: [
    AppComponent,
    DatosClienteComponent,
    CardHabitacionComponent,
    HabitacionesComponent,
    CreaReservacionComponent,
    CardCuartoComponent,
    CuartosComponent,
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule,
    ReactiveFormsModule,
    PrimeNgModule,
    AngularMaterialModule,
    FullCalendarModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
