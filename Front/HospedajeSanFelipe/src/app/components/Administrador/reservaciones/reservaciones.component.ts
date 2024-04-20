import { Component, EventEmitter, Input, Output, ViewChild, ViewEncapsulation, inject, signal } from '@angular/core';
import { FormBuilder, FormGroup, FormsModule, ReactiveFormsModule, Validators } from '@angular/forms';
import { PeticionesService } from '../../../services/peticiones/peticiones.service';
import { Reservacion } from '../../../model/reservaciones.model';
import { environment } from '../../../../environments/environment.development';
import { Clientes, Habitaciones, Reservaciones } from '../../../model/constantes';
import { AlertsService } from '../../../services/alerts.service';
import { CommonModule, DatePipe } from '@angular/common';

import { DateRange, MatCalendar, MatDatepickerModule } from '@angular/material/datepicker';
import { RangeSelectionStrategyService } from '../../../services/range-selection-strategy.service';
import { MAT_DATE_RANGE_SELECTION_STRATEGY } from '@angular/material/datepicker';
import { MatFormFieldModule } from '@angular/material/form-field';
import { provideNativeDateAdapter } from '@angular/material/core';
import { MatStepperModule } from '@angular/material/stepper';
import { MatCardModule } from '@angular/material/card';
import { HabitacionDisponibleResponse } from '../../../model/habitacion.model';
import { AltaClienteComponent } from '../alta-cliente/alta-cliente.component';
import { StepperSelectionEvent } from '@angular/cdk/stepper';
import { ClienteResponse } from '../../../model/cliente.model';


@Component({
  selector: 'app-reservaciones',
  encapsulation: ViewEncapsulation.None,
  standalone: true,
  providers: [
    provideNativeDateAdapter(),
    {provide: MAT_DATE_RANGE_SELECTION_STRATEGY, useClass: RangeSelectionStrategyService}
  ],
  imports: [CommonModule, FormsModule, ReactiveFormsModule, MatCardModule, MatDatepickerModule, MatStepperModule, MatFormFieldModule, AltaClienteComponent],
  templateUrl: './reservaciones.component.html',
  styleUrl: './reservaciones.component.css'
})
export class ReservacionesComponent {
  private readonly URL_CLIENTES = `${environment.apiHost}${Clientes.CLIENTES}`;
  // private readonly URL_HABITACIONES = `${environment.apiHost}${Habitaciones.HABITACIONES}`;
  private readonly URL_RESERVACIONES = `${environment.apiHost}${Reservaciones.RESERVACIONES}`;

  private _peticiones = inject(PeticionesService);
  private _alertas = inject(AlertsService);
  private fomrBuilder = inject(FormBuilder);
  private datePipe = inject(DatePipe);

  public reservacionForm: FormGroup;
  public busquedaForm: FormGroup;
  public reservaciones: Reservacion[];

  public habitaciones = signal<HabitacionDisponibleResponse[]>([]);
  public clientes = signal<ClienteResponse[]>([]);
  public isLoadedHabitaciones = signal(false);
  public isClienteNuevo = signal(true);
  public currentStep = signal(0);

  constructor() {
    this.ayer = new Date();
  }

  public defineFormBusquedaCliente(): void {
    this.busquedaForm = this.fomrBuilder.group({
      nombre   : ['', [Validators.required, Validators.minLength(3)]],
      primerAp : ['', [Validators.required, Validators.minLength(3)]],
    })
  }

  private defineForm(): void {
    this.reservacionForm = this.fomrBuilder.group({
      fechaEntrada  : ['', [Validators.required, Validators.minLength(3)]],
      fechaSalida   : ['', [Validators.required, Validators.minLength(3)]],
      noPersonas    : ['', [Validators.required, Validators.minLength(3)]],
      noPersonaExtra: ['', [Validators.required, Validators.minLength(3)]],
      subtotal      : ['', [Validators.required, Validators.minLength(3)]],
      total         : ['', [Validators.required, Validators.minLength(3)]],
      estado        : ['', [Validators.required, Validators.minLength(3)]],
      cliente       : ['', [Validators.required, Validators.minLength(3)]],
      empleado      : ['', [Validators.required, Validators.minLength(3)]],
      comentario    : ['', [Validators.required, Validators.minLength(3)]],
    })
  }

  public ngOnInit(): void {
    this.defineForm();
    this.defineFormBusquedaCliente();
    this.getReservaciones(true);
  }

  public getReservaciones(isCloseLoading?: boolean): void {
    this._peticiones.getPeticion(this.URL_RESERVACIONES).subscribe({
      next: (response: Reservacion[]) => {
        this.reservaciones = response;
        if (isCloseLoading) {
          this._alertas.cierraLoading();
        }
      },
      error: (err: any) => {
        this._alertas.error(err);
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
    this._peticiones.getPeticion(request).subscribe({
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

  /*Métodos de Datepicker*/
  @ViewChild(MatCalendar, {static: false}) calendar!: MatCalendar<Date>;
  @Input() selectedRangeValue: DateRange<Date> | undefined;
  @Output() selectedRangeValueChange = new EventEmitter<DateRange<Date>>();

  ayer: Date;

  // private isDateInRangeDisabled(startDate: Date, endDate: Date): boolean {
  //   let day = new Date(startDate);
  //   while (day <= endDate) {
  //     if (!this.myFilter(day)) { // Utiliza el filtro existente para verificar si la fecha está deshabilitada
  //       return true;
  //     }
  //     day.setDate(day.getDate() + 1); // Incrementa el día
  //   }
  //   return false;
  // }

  selectedChange(event: Date): void {
    if (!this.selectedRangeValue || this.selectedRangeValue.end) {
      this.selectedRangeValue = new DateRange(event, null);
    } else {
      if (event >= this.selectedRangeValue.start) {
        // Verifica si el rango es válido antes de establecerlo
        // if (!this.isDateInRangeDisabled(this.selectedRangeValue.start, event)) {
          this.selectedRangeValue = new DateRange(this.selectedRangeValue.start, event);
        // } else {
        //   // Manejar el error cuando hay fechas deshabilitadas en el rango
        //   this._alerta.toastAdvertencia("No puede seleccionar un rango que incluya fechas reservadas.");
        //   // Opcionalmente, puedes restablecer la selección o manejar de otra manera
        // }
      } else {
        this._alertas.toastAdvertencia("La fecha final debe ser después de la fecha inicial.");
      }
    }
    this.selectedRangeValueChange.emit(this.selectedRangeValue);
  }

  readonly fechasReservadas = [
    new Date("04/24/2024"),
    new Date("04/27/2024"),
    new Date("05/15/2024"),
    new Date("05/16/2024"),
    new Date("05/17/2024"),
    new Date("05/27/2024"),
    new Date("05/28/2024"),
  ];

  // dateClass: MatCalendarCellClassFunction<Date> = (cellDate, view) => {
  //   let clase = '';
  //   for(const fecha of this.fechasReservadas){
  //     if(fecha.getTime() == cellDate.getTime()) {
  //       clase = 'example-custom-date-class';
  //     }
  //   }
  //   return clase;
  // };

  // myFilter = (d: Date | null): boolean => {
  //   let esReservada = true;
  //   for(const fecha of this.fechasReservadas){
  //     if(fecha.getTime() == d.getTime()) {
  //       esReservada = false;
  //       break;
  //     }
  //   }

  //   return esReservada;
  // };

  /*Métodos de Datepicker*/

  public getAllHabitacionesDisponibles(): void {

    // const fechaEntrada = this.datePipe.transform(this.selectedRangeValue.start, 'yyyy-MM-dd');
    // const fechaSalida = this.datePipe.transform(this.selectedRangeValue.end, 'yyyy-MM-dd');

    // this._peticiones.getPeticion(`${this.URL_HABITACIONES}/${fechaEntrada}/${fechaSalida}`).subscribe({
      // next: (response: HabitacionDisponibleResponse[]) => {
        // this.habitaciones = response;
        this.habitaciones.set(JSON.parse(`[{"idHabitacion":1,"noHabitacion":"01","noOcupante":4,"noMaxOcupante":6,"piso":"Primer Piso","servicios":[{"idServicio":1,"descripcion":"Cama matrimonial"},{"idServicio":2,"descripcion":"Cama individual"},{"idServicio":3,"descripcion":"Agua caliente"},{"idServicio":4,"descripcion":"Ventilador"}]},{"idHabitacion":2,"noHabitacion":"02","noOcupante":2,"noMaxOcupante":3,"piso":"Segundo Piso","servicios":[{"idServicio":1,"descripcion":"Cama matrimonial"},{"idServicio":2,"descripcion":"Cama individual"},{"idServicio":3,"descripcion":"Agua caliente"},{"idServicio":4,"descripcion":"Ventilador"}]},{"idHabitacion":3,"noHabitacion":"03","noOcupante":6,"noMaxOcupante":8,"piso":"Tercer Piso","servicios":[{"idServicio":1,"descripcion":"Cama matrimonial"},{"idServicio":2,"descripcion":"Cama individual"},{"idServicio":4,"descripcion":"Ventilador"}]},{"idHabitacion":4,"noHabitacion":"04","noOcupante":6,"noMaxOcupante":8,"piso":"Tercer Piso","servicios":[{"idServicio":1,"descripcion":"Cama matrimonial"},{"idServicio":2,"descripcion":"Cama individual"},{"idServicio":3,"descripcion":"Agua caliente"}]}]`));
    //     this.isLoadedHabitaciones.set(true);
    //     this._alertas.cierraLoading();
    //   },
    //   error: (err: any) => {
    //     this._alertas.error(err);
    //   },
    // });
  }

  public validaFormBusqueda(): void {
    if (this.busquedaForm.invalid) {
      this.busquedaForm.markAllAsTouched();
    } else {
      this._alertas.iniciaLoading();
      const clienteRequest: any = {
        nombre: this.busquedaForm.get('nombre').value,
        apellido: this.busquedaForm.get('primerAp').value
      }

      this.getClienteByNombreApellido(clienteRequest);
    }
  }

  private getClienteByNombreApellido(clienteRequest: any): void {
    this._peticiones.getPeticion(`${this.URL_CLIENTES}/${clienteRequest.nombre}/${clienteRequest.apellido}`).subscribe({
      next: (response: ClienteResponse[]) => {
        this.clientes.set(response);
        this._alertas.cierraLoading();
      },
      error: (err: any) => {
        this._alertas.error(err);
      },
    });
  }

  public isSelected(idHabitacion: number, event: MouseEvent): void {
      const isChecked = (event.target as HTMLInputElement).checked;

    this.habitaciones.update((habitaciones: HabitacionDisponibleResponse[]) => {
      const indexToUpdate = habitaciones.findIndex((habitacion: HabitacionDisponibleResponse) => habitacion.idHabitacion === idHabitacion);
      if (indexToUpdate !== -1) {
        habitaciones[indexToUpdate].isSelected = isChecked;
      }
      return habitaciones;
    });
  }

  public isValid(control: string): boolean {
    return this.busquedaForm.get(control).valid;
  }

  public isFieldInvalid(control: string): boolean {
    const formControl = this.busquedaForm.get(control);
    return formControl.invalid && (formControl.dirty || formControl.touched);
  }

  public onStepChange(event: StepperSelectionEvent): void {
    this.currentStep.set(event.selectedIndex);
  }
}
