import { Component, OnInit, inject, signal } from '@angular/core';
import { HabitacionEmpleadoResponse } from '../../../model/habitacion.model';
import { environment } from '../../../../environments/environment.development';
import { Habitaciones, Imagenes } from '../../../model/constantes';
import { PeticionesService } from '../../../services/peticiones/peticiones.service';
import { ImagenesService } from '../../../services/imagenes.service';
import { DomSanitizer } from '@angular/platform-browser';
import { AlertsService } from '../../../services/alerts.service';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-inicio',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './inicio.component.html',
  styleUrl: './inicio.component.css'
})
export class InicioComponent implements OnInit {

  private readonly URL_HABITACIONES = `${environment.apiHost}${Habitaciones.HABITACIONES}`;
  private readonly URL_IMAGENES = `${environment.apiHost}${Imagenes.IMAGENES}${Imagenes.GET_BY_NAME}`

  public isLoadedHabitaciones = signal(false);
  public habitaciones: HabitacionEmpleadoResponse[];

  private _peticiones = inject(PeticionesService);
  private _imagenes = inject(ImagenesService);
  private sanitizer = inject(DomSanitizer);
  private _alertas = inject(AlertsService);

  private getAllHabitacion(): void {

    this._peticiones.getPeticion(`${this.URL_HABITACIONES}${Habitaciones.EMPLEADOS}` ).subscribe({
      next: (response: HabitacionEmpleadoResponse[]) => {
        this.habitaciones = response;
        this.isLoadedHabitaciones.set(true);
        this.getFotoByName();
        this._alertas.cierraLoading();
      },
      error: (err: any) => {
        this._alertas.error(err);
      },
    });
  }

  private getFotoByName(): void {
    this.habitaciones.forEach((habitacion: HabitacionEmpleadoResponse) => {
      this._imagenes.getImagenByName(`${this.URL_IMAGENES}/${habitacion.urlFoto}`).subscribe(data => {
        const objectURL = URL.createObjectURL(data);
        habitacion.imagen = this.sanitizer.bypassSecurityTrustUrl(objectURL);
      });
    });
  }

  ngOnInit(): void {
    this.getAllHabitacion();
  }
}

