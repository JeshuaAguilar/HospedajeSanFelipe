import { Component, OnInit, inject } from '@angular/core';
import { FormBuilder, FormGroup, FormsModule, ReactiveFormsModule, Validators } from '@angular/forms';
import { PeticionesService } from '../../../services/peticiones/peticiones.service';
import { environment } from '../../../../environments/environment.development';
import { AlertsService } from '../../../services/alerts.service';
import { CatEstadoHabitacion, CatPiso, HabitacionRequest, HabitacionResponse } from '../../../model/habitacion.model';
import { Estados, Habitaciones, Pisos } from '../../../model/constantes';
import { LoadingComponent } from '../shared/loading/loading.component';

declare const bootstrap: any;

@Component({
  selector: 'app-habitaciones',
  standalone: true,
  imports: [FormsModule, ReactiveFormsModule, LoadingComponent],
  templateUrl: './habitaciones.component.html',
  styleUrl: './habitaciones.component.css'
})
export class HabitacionesComponent implements OnInit {

  private readonly URL_HABITACIONES = `${environment.apiHost}${Habitaciones.HABITACIONES}`;
  private readonly URL_PISOS = `${environment.apiHost}${Pisos.PISOS}`;
  private readonly URL_ESTADOS = `${environment.apiHost}${Estados.ESTADOS}`;

  private fomrBuilder = inject(FormBuilder);
  private _peticiones = inject(PeticionesService);
  private _alerta = inject(AlertsService);

  public isLoadedHabitaciones = false;
  public habitaciones?: HabitacionResponse[];
  public habitacionesForm!: FormGroup;
  public pisos?: CatPiso[];
  public estados?: CatEstadoHabitacion[];
  public isEditing = false;

  public defineForm(): void {

    this.isEditing = false;
    this.habitacionesForm = this .fomrBuilder.group({

      idHabitacion:  [''],
      noHabitacion:  ['' , [Validators.required, Validators.minLength(1)]],
      noOcupante:    ['' , [Validators.required, Validators.minLength(1)]],
      noMaxOcupante: ['' , [Validators.required, Validators.minLength(1)]],
      piso:          ['' , [Validators.required, Validators.minLength(1)]],
      estado:        ['' , [Validators.required]],
      urlFoto:          ['' , [Validators.required, Validators.minLength(3)]]
    })
  }

  private defineEditForm(habitacion: HabitacionRequest): void {
    this.isEditing = true;

    const { idHabitacion, noHabitacion, noOcupante, noMaxOcupante, piso, estado, urlFoto } = habitacion;

    this.habitacionesForm = this .fomrBuilder.group({

      idHabitacion: idHabitacion,
      noHabitacion:   [noHabitacion,  [Validators.required, Validators.minLength(1)]],
      noOcupante:     [noOcupante,    [Validators.required, Validators.minLength(1)]],
      noMaxOcupante:  [noMaxOcupante, [Validators.required, Validators.minLength(1)]],
      piso:           [piso,          [Validators.required, Validators.minLength(1)]],
      estado:         [estado,        [Validators.required]],
      urlFoto:        [urlFoto,       [Validators.minLength(3)]]
    })
  }

  public resetForm(): void {
    this.habitacionesForm.reset({
      idHabitacion:   '',
      noHabitacion:   '',
      noOcupante:    '',
      noMaxOcupante:  '',
      piso:           '',
      estado:         '',
      urlFoto:        '',
    })
  }

  public ngOnInit(): void {
    this.getPisoCatalog();
  }

  private getAllHabitacion(isCloseLoading?: boolean): void {
    this.isLoadedHabitaciones = false;

    this._peticiones.getPeticion(this.URL_HABITACIONES).subscribe({
      next: (response: HabitacionResponse[]) => {
        this.habitaciones = response;
        this.isLoadedHabitaciones = true;
        if (isCloseLoading) {
          this._alerta.cierraLoading();
        }
      },
      error: (err: any) => {
        this._alerta.error(err);
      },
    });
  }


  public validaFormHabitacion(): void {
    if (this.habitacionesForm.invalid) {
      this.habitacionesForm.markAllAsTouched();
    } else {
      this._alerta.iniciaLoading();

      const habitacionRequest: HabitacionRequest = {

        idHabitacion  : this.isEditing ? this.habitacionesForm.get('idHabitacion').value : 0,
        noHabitacion  : this.habitacionesForm.get('noHabitacion').value,
        noOcupante    : this.habitacionesForm.get('noOcupante').value,
        noMaxOcupante : this.habitacionesForm.get('noMaxOcupante').value,
        piso          : this.habitacionesForm.get('piso').value,
        estado        : this.habitacionesForm.get('estado').value,
        urlFoto        : this.habitacionesForm.get('urlFoto').value,
      };

      if (!this.isEditing) {
        this.agregaHabitacion(habitacionRequest);
      } else {
        this.editaHabitacion(habitacionRequest);
      }
    }
  }

  private agregaHabitacion(request: HabitacionRequest): void {

    this._peticiones.postPeticion(this.URL_HABITACIONES, request, false).subscribe({
      next: (response: string) => {

      this.resetForm();
      const miModal = bootstrap.Modal.getInstance(document.getElementById('staticBackdrop'));

     if (miModal) {
      miModal.hide();
      }
    this.getAllHabitacion();
    this._alerta.toastExito(response);
      },
      error: (err: any) => {
        console.error(err);
        this._alerta.error(err);
      },
    });
  }

  private editaHabitacion(request: HabitacionRequest): void {
    this._peticiones.putPeticion(this.URL_HABITACIONES, request).subscribe({
      next: (response: string) => {
        this.resetForm();

        const miModal = bootstrap.Modal.getInstance(document.getElementById('staticBackdrop'));

        if (miModal) {
          miModal.hide();
        }

        this.getAllHabitacion();

        this._alerta.toastExito(response);
      },
      error: (err: any) => {
        console.error(err);
        this._alerta.error(err);
      },
    });
  }

  public modificarHabitacion(idHabitacion: number): void {
    const habitacionFinded = this.habitaciones.find((habitacion: HabitacionResponse) => idHabitacion === habitacion.idHabitacion);
    const habitacionRequest: HabitacionRequest = {
      idHabitacion   : habitacionFinded.idHabitacion,
      noHabitacion   : habitacionFinded.noHabitacion,
      noOcupante     : habitacionFinded.noOcupante,
      noMaxOcupante  : habitacionFinded.noMaxOcupante,
      piso           : habitacionFinded.piso.idPiso,
      estado         : habitacionFinded.estado.idEstado,
      urlFoto        : '',
    }

    this.defineEditForm(habitacionRequest);
  }

  public eliminarHabitacion(idHabitacion: number): void {
    this._alerta.iniciaLoading();

    const url = `${this.URL_HABITACIONES}/${idHabitacion}`
    this._peticiones.deletePeticion(url).subscribe({
      next: (response: string) => {
        this.getAllHabitacion();
        this._alerta.toastExito(response);
      },
      error: (err: any) => {
        console.error(err);
        this._alerta.error(err);
      },
    });
  }

  public getPisoCatalog(): void {
    this._peticiones.getPeticion(this.URL_PISOS).subscribe({
      next: (response: CatPiso[]) => {
        this.pisos = response;
        this.getEstadosCatalog();
      },
      error: (err: any) => {
        console.error(err);
        this._alerta.error(err);
      },
    });
  }

  public getEstadosCatalog(): void {
    this._peticiones.getPeticion(this.URL_ESTADOS).subscribe({
      next: (response: CatEstadoHabitacion[]) => {
        this.estados = response;
        this.defineForm();
        this.getAllHabitacion(true);
      },
      error: (err: any) => {
        console.error(err);
        this._alerta.error(err);
      },
    });
  }

  public isValid(control: string): boolean {
    return this.habitacionesForm.get(control).valid;
  }

  public isFieldInvalid(control: string): boolean {
    const formControl = this.habitacionesForm.get(control);
    return formControl.invalid && (formControl.dirty || formControl.touched);
  }

  public validaNumero(valor: Event): void {
    const target = valor.currentTarget as HTMLElement;

    const noOcupante = this.habitacionesForm.get('noOcupante').value;
    if(noOcupante > Number(target.innerText)) {
      this.habitacionesForm.get('noMaxOcupante').reset();
      this._alerta.toastAdvertencia('El número de ocupantes no puede ser mayor al número de ocupantes máximo')
    }
  }
}
