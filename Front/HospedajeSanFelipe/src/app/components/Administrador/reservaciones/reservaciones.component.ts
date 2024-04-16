import { Component, EventEmitter, Injectable, Input, Output, ViewChild, ViewEncapsulation, inject } from '@angular/core';
import { FormBuilder, FormGroup, FormsModule, ReactiveFormsModule, Validators } from '@angular/forms';
import { PeticionesService } from '../../../services/peticiones/peticiones.service';
import { Reservacion } from '../../../model/reservaciones.model';
import { environment } from '../../../../environments/environment.development';
import { Reservaciones } from '../../../model/constantes';
import { AlertsService } from '../../../services/alerts.service';
import { CommonModule } from '@angular/common';

import { DateRange, MatCalendarCellClassFunction, MatDateRangePicker, MatDateRangeSelectionStrategy, MatDatepicker, MatDatepickerModule } from '@angular/material/datepicker';
import { MatFormFieldModule } from '@angular/material/form-field';
import { NativeDateAdapter, provideNativeDateAdapter } from '@angular/material/core';
import { MatStepperModule } from '@angular/material/stepper';
import { MatCardModule } from '@angular/material/card';
import { MatCalendar } from '@angular/material/datepicker';
import { DateAdapter, MAT_DATE_FORMATS, MAT_DATE_LOCALE } from '@angular/material/core';

import { MAT_DATE_RANGE_SELECTION_STRATEGY } from '@angular/material/datepicker';

@Injectable()
export class FiveDayRangeSelectionStrategy<D> implements MatDateRangeSelectionStrategy<D> {
  constructor(private _dateAdapter: DateAdapter<D>) {}

  selectionFinished(date: D | null): DateRange<D> {
    console.log(date);
    return this._createFiveDayRange(date, null);
  }

  createPreview(activeDate: D | null, currentRange: DateRange<D>): DateRange<D> {
    return this._createFiveDayRange(activeDate, currentRange);
  }

  private _createFiveDayRange(activeDate: D | null, currentRange: DateRange<D>): DateRange<D> {
    if (activeDate) {

      const start = this._dateAdapter.addCalendarDays(currentRange.start, 0);
      const diferencia = Math.round((new Date(activeDate as Date).getTime() - new Date(currentRange.start as Date).getTime()) / (1000 * 60 * 60 * 24));
      const end = this._dateAdapter.addCalendarDays(currentRange.start, diferencia);

      return new DateRange<D>(start as D, end as D);
    }

    return new DateRange<D>(null, null);
  }
}

@Component({
  selector: 'app-reservaciones',
  encapsulation: ViewEncapsulation.None,
  standalone: true,
  providers: [
    provideNativeDateAdapter(),
    {provide: MAT_DATE_RANGE_SELECTION_STRATEGY, useClass: FiveDayRangeSelectionStrategy},
  ],
  imports: [CommonModule, FormsModule, ReactiveFormsModule, MatCardModule, MatDatepickerModule, MatStepperModule, MatFormFieldModule],
  templateUrl: './reservaciones.component.html',
  styleUrl: './reservaciones.component.css'
})
export class ReservacionesComponent {

  private readonly URL_RESERVACIONES = `${environment.apiHost}${Reservaciones.RESERVACIONES}`;

  private fomrBuilder = inject(FormBuilder)
  private _peticiones = inject(PeticionesService)
  private _alerta = inject(AlertsService)
  private dateAdapter = inject(DateAdapter<Date>);

  public reservacionForm: FormGroup;
  public campaignOne: FormGroup;
  public reservaciones: Reservacion[];


  constructor() {
    this.ayer = new Date();
  }

  private defineForm(): void {
    this.reservacionForm = this.fomrBuilder.group({
      fechaEntrada  : ['', [Validators.required, Validators.minLength(3)]],
      fechaSalida   : ['', [Validators.required, Validators.minLength(3)]],
      noPersonas    : ['', [Validators.required, Validators.minLength(3)]],
      noPersonaExtra: ['', [Validators.required, Validators.minLength(3)]],
      total         : ['', [Validators.required, Validators.minLength(3)]],
      estado        : ['', [Validators.required, Validators.minLength(3)]],
      cliente       : ['', [Validators.required, Validators.minLength(3)]],
      empleado      : ['', [Validators.required, Validators.minLength(3)]],
      comentario    : ['', [Validators.required, Validators.minLength(3)]],
      precioEspecial: ['', [Validators.required, Validators.minLength(3)]],
    })
  }

  public ngOnInit(): void {
    this.defineForm();
    this.getReservaciones(true);
  }

  public getReservaciones(isCloseLoading?: boolean): void {
    this._peticiones.getPeticion(this.URL_RESERVACIONES).subscribe({
      next: (response: Reservacion[]) => {
        this.reservaciones = response;
        if (isCloseLoading) {
          this._alerta.cierraLoading();
        }
      },
      error: (err: any) => {
        this._alerta.error(err);
      },
    });
  }

  public agregarreservacion(): void {
    if (this.reservacionForm.invalid) {
      this.reservacionForm.markAllAsTouched();
    } else {
      // this._alerts.loading();
      const reservacionRequest: any = {
        fechaEntrada  : this.reservacionForm.get('fechaE')?.value,
        fechaSalida   : this.reservacionForm.get('fechaS')?.value,
        noPersonas    : this.reservacionForm.get('noPe')?.value,
        noPersonaExtra: this.reservacionForm.get('noPeEx')?.value,
        total         : this.reservacionForm.get('total')?.value,
        estado        : this.reservacionForm.get('estado')?.value,
        cliente       : this.reservacionForm.get('cliente')?.value,
        empleado      : this.reservacionForm.get('empleado')?.value,
        comentario    : this.reservacionForm.get('comentario')?.value,
        precioEspecial: this.reservacionForm.get('precioEs')?.value,

      };
      this.reservacion(reservacionRequest);
    }
  }
  private reservacion(request: any): void {
    this._peticiones.reservacion(request).subscribe({
      next: (response: any) => {
        console.log(response);
        // sessionStorage.setItem('token', response.token);
        // sessionStorage.setItem('rol', response.rol);
        // this._alerts.toastLogin('¡Ha iniciado sesión correctamente!');
        // this.redirectHome();
      },
      error: (err: any) => {
        console.error(err);
        // this._alerts.error(err);
      },
    });
  }

  public isFieldInvalid(control: string): boolean {
    const formControl = this.reservacionForm.get(control);
    return formControl!.invalid && (formControl!.dirty || formControl!.touched);
  }

  /*Métodos de Datepicker*/
  @Input() selectedRangeValue: DateRange<Date> | undefined;
  @Output() selectedRangeValueChange = new EventEmitter<DateRange<Date>>();

  ayer: Date;

  selectedChange(event: Date): void {
    if (!this.selectedRangeValue || this.selectedRangeValue.end) {
        this.selectedRangeValue = new DateRange(event, null);
    } else {
        this.selectedRangeValue = new DateRange(this.selectedRangeValue.start, event);
    }
    this.selectedRangeValueChange.emit(this.selectedRangeValue);
  }

  predefinedRanges = [
    new DateRange(new Date(new Date().getFullYear(), new Date().getMonth(), 10), new Date(new Date().getFullYear(), new Date().getMonth(), 15)),
    new DateRange(new Date(new Date().getFullYear(), 4, 1), new Date(new Date().getFullYear(), 4, 10))
  ];

  readonly fechasReservadas = [
    new Date("04/16/2024"),
    new Date("04/27/2024"),
    new Date("05/15/2024"),
    new Date("05/16/2024"),
    new Date("05/17/2024"),
    new Date("05/27/2024"),
    new Date("05/28/2024"),
  ];

  dateClass: MatCalendarCellClassFunction<Date> = (cellDate, view) => {
    let clase = '';
    for(const fecha of this.fechasReservadas){
      if(fecha.getTime() == cellDate.getTime()) {
        clase = 'example-custom-date-class';
      }
    }
    return clase;
  };

  myFilter = (d: Date | null): boolean => {
    let esReservada = true;
    for(const fecha of this.fechasReservadas){
      if(fecha.getTime() == d.getTime()) {
        esReservada = false;
      }
    }
    return esReservada;
};
  /*Métodos de Datepicker*/

}
