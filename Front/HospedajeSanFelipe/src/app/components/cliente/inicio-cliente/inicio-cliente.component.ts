import { Component, OnInit, inject, signal } from '@angular/core';
import { PeticionesService } from '../../../services/peticiones/peticiones.service';
import { AlertsService } from '../../../services/alerts.service';
import { Habitaciones, Imagenes } from '../../../model/constantes';
import { environment } from '../../../../environments/environment.development';
import { HabitacionClienteResponse, HabitacionResponse } from '../../../model/habitacion.model';
import { ImagenesService } from '../../../services/imagenes.service';
import { DomSanitizer } from '@angular/platform-browser';
import { UtilsService } from '../../../services/utils.service';

@Component({
  selector: 'app-inicio-cliente',
  standalone: true,
  imports: [],
  templateUrl: './inicio-cliente.component.html',
  styleUrl: './inicio-cliente.component.css'
})
export class InicioClienteComponent implements OnInit {

  private readonly URL_HABITACIONES = `${environment.apiHost}${Habitaciones.HABITACIONES}`;
  private readonly URL_IMAGENES = `${environment.apiHost}${Imagenes.IMAGENES}${Imagenes.GET_BY_NAME}`

  public isLoadedHabitaciones = signal(false);
  public habitaciones: HabitacionClienteResponse[];

  private _peticiones = inject(PeticionesService);
  private _imagenes = inject(ImagenesService);
  private sanitizer = inject(DomSanitizer);
  private _alerta = inject(AlertsService);

  constructor() {
    sessionStorage.clear();
  }

  private getAllHabitacion(): void {

    this._peticiones.getPeticion(`${this.URL_HABITACIONES}${Habitaciones.CLIENTES}` ).subscribe({
      next: (response: HabitacionClienteResponse[]) => {
        this.habitaciones = response;
        this.isLoadedHabitaciones.set(true);
        this.getFotoByName();
        this._alerta.cierraLoading();
      },
      error: (err: any) => {
        this._alerta.error(err);
      },
    });
  }

  private getFotoByName(): void {
    this.habitaciones.forEach((habitacion: HabitacionClienteResponse) => {
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
