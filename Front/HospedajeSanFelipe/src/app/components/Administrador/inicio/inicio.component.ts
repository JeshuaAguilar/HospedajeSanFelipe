import { Component } from '@angular/core';
import { HabitacionEmpleadoResponse } from '../../../model/habitacion.model';

@Component({
  selector: 'app-inicio',
  standalone: true,
  imports: [],
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
  private _alerta = inject(AlertsService);

  private getAllHabitacion(): void {

    this._peticiones.getPeticion(`${this.URL_HABITACIONES}${Habitaciones.CLIENTES}` ).subscribe({
      next: (response: HabitacionEmpleadoResponse[]) => {
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

