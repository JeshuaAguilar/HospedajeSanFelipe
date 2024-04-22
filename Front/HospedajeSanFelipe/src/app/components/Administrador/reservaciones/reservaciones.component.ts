import { AfterViewInit, Component, EventEmitter, Input, OnInit, Output, ViewChild, ViewEncapsulation, inject, signal } from '@angular/core';
import { FormBuilder, FormGroup, FormsModule, ReactiveFormsModule, Validators } from '@angular/forms';
import { PeticionesService } from '../../../services/peticiones/peticiones.service';
import { Reservacion, ReservacionRequest } from '../../../model/reservaciones.model';
import { environment } from '../../../../environments/environment.development';
import { Clientes, Empleados, Habitaciones, Reservaciones } from '../../../model/constantes';
import { AlertsService } from '../../../services/alerts.service';
import { CommonModule, DatePipe } from '@angular/common';

import { DateRange, MatCalendar, MatDatepickerModule } from '@angular/material/datepicker';
import { RangeSelectionStrategyService } from '../../../services/range-selection-strategy.service';
import { MAT_DATE_RANGE_SELECTION_STRATEGY } from '@angular/material/datepicker';
import { MatFormFieldModule } from '@angular/material/form-field';
import { provideNativeDateAdapter } from '@angular/material/core';
import { MatStepperModule } from '@angular/material/stepper';
import { MatCardModule } from '@angular/material/card';
import { HabitacionDisponibleResponse, Habitacion } from '../../../model/habitacion.model';
import { AltaClienteComponent } from '../alta-cliente/alta-cliente.component';
import { StepperSelectionEvent } from '@angular/cdk/stepper';
import { Cliente } from '../../../model/cliente.model';

import Swal from 'sweetalert2'
import { LoginResponse } from '../../../model/login-response';
import { EmpleadoRequest } from '../../../model/empleados';

declare var bootstrap: any;

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
export class ReservacionesComponent implements OnInit {
  private readonly URL_CLIENTES = `${environment.apiHost}${Clientes.CLIENTES}`;
  private readonly URL_HABITACIONES = `${environment.apiHost}${Habitaciones.HABITACIONES}`;
  private readonly URL_RESERVACIONES = `${environment.apiHost}${Reservaciones.RESERVACIONES}`;

  private _peticiones = inject(PeticionesService);
  private _alertas = inject(AlertsService);
  private fomrBuilder = inject(FormBuilder);
  private datePipe = inject(DatePipe);

  public reservacionForm: FormGroup;
  public busquedaForm: FormGroup;
  public reservaciones: Reservacion[];

  public habitaciones = signal<HabitacionDisponibleResponse[]>([]);
  public clientes = signal<Cliente[]>([]);

  public isLoadedHabitaciones = signal(false);
  public isClienteNuevo = signal(true);
  public currentStep = signal(0);

  public habitacionSelected = signal<HabitacionDisponibleResponse>(new HabitacionDisponibleResponse());
  public reservacion: Reservacion = new Reservacion();
  public clienteNuevo: Cliente;

  constructor() {
    this.ayer = new Date();
  }

  public resetReservacion(): void {
    this.selectedRangeValue = undefined;
    this.habitaciones = signal<HabitacionDisponibleResponse[]>([]);
    this.clientes = signal<Cliente[]>([]);

    this.isLoadedHabitaciones = signal(false);
    this.isClienteNuevo = signal(true);
    this.currentStep = signal(0);

    this.habitacionSelected = signal<HabitacionDisponibleResponse>(new HabitacionDisponibleResponse());

    this.reservacion = new Reservacion();
    this.reservacion.habitaciones = [];
    this.reservacion.noPersonas = 0;
    this.reservacion.noPersonaExtra = 0;

    const empleado: LoginResponse = JSON.parse(sessionStorage.getItem('user'));

    this.reservacion.empleado = new EmpleadoRequest();
    this.reservacion.empleado.idEmpleado = empleado.idEmpleado;
    this.reservacion.empleado.nombre = empleado.nombre;
    this.clienteNuevo = undefined;
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
    var tooltipTriggerList = [].slice.call(document.querySelectorAll('[data-bs-toggle="tooltip"]'))
    var tooltipList = tooltipTriggerList.map(function (tooltipTriggerEl) {
      return new bootstrap.Tooltip(tooltipTriggerEl)
    })

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
      // this.creaReservacion(reservacionRequest);
    }
  }

  public creaReservacion(): void {
    const reservacionRequest = new ReservacionRequest();

	  reservacionRequest.fechaEntrada = this.reservacion.fechaEntrada;
	  reservacionRequest.fechaSalida = this.reservacion.fechaSalida;
	  reservacionRequest.total = this.reservacion.total; //Falta setearlo
	  reservacionRequest.estado = 1;// revisar el mapeo del estado pero de las reservaciones
	  reservacionRequest.idCliente = this.reservacion.cliente.idCliente;
	  reservacionRequest.idEmpleado = this.reservacion.empleado.idEmpleado;
    // reservacionRequest.idComentario;
	  // reservacionRequest.idPrecioEspecial;
    reservacionRequest.habitaciones = [];

    for (const habitacion of this.reservacion.habitaciones) {
      const habitacionEntity = new Habitacion();
      habitacionEntity.idHabitacion = habitacion.idHabitacion;
      habitacionEntity.noOcupante = habitacion.noOcupante;
      habitacionEntity.noMaxExtras = habitacion.noMaxExtras;
      reservacionRequest.habitaciones.push(habitacionEntity);
    }

    const noPersonas = this.reservacion.habitaciones.reduce((total: number, habitacion: Habitacion) => Number(total) + Number(habitacion.noOcupante), 0);
    reservacionRequest.noPersonas = noPersonas;
	  reservacionRequest.noPersonaExtra = this.getPersonasExtras(this.reservacion.habitaciones);

    reservacionRequest.total = this.getTotal(this.reservacion.habitaciones);

    this._peticiones.postPeticion(this.URL_RESERVACIONES, reservacionRequest, false).subscribe({
      next: (response: any) => {
        // console.log(response);
        const miModal = bootstrap.Modal.getInstance(document.getElementById('exampleModalToggle'));
        if (miModal) {
          miModal.hide();
        }

        this.getReservaciones(false);
        this._alertas.toastExito('Se ha creado la reservación exitosamente');
      },
      error: (err: any) => {
        console.error(err);
        this._alertas.error(err);
      },
    });
  }

  public editarReservacion(reservacion: Reservacion): void {
    this.selectedRangeValue = new DateRange(new Date(reservacion.fechaEntrada), new Date(reservacion.fechaSalida));

    // this.habitaciones = signal<HabitacionDisponibleResponse[]>([]);
    this.getAllHabitacionesDisponibles();

    const clientes: Cliente[] = [];
    clientes.push(reservacion.cliente);

    this.clientes = signal<Cliente[]>(clientes);

    this.isLoadedHabitaciones = signal(false);
    this.isClienteNuevo = signal(false);
    this.currentStep = signal(0);

    this.habitacionSelected = signal<HabitacionDisponibleResponse>(new HabitacionDisponibleResponse());

    this.reservacion = new Reservacion();
    this.reservacion.habitaciones = reservacion.habitaciones;
    this.reservacion.noPersonas = reservacion.noPersonas;
    this.reservacion.noPersonaExtra = reservacion.noPersonaExtra;

    const empleado: LoginResponse = JSON.parse(sessionStorage.getItem('user'));

    this.reservacion.empleado = new EmpleadoRequest();
    this.reservacion.empleado.idEmpleado = empleado.idEmpleado;
    this.reservacion.empleado.nombre = empleado.nombre;
    this.clienteNuevo = undefined;
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
          this.reservacion.fechaEntrada = this.selectedRangeValue.start;
          this.reservacion.fechaSalida = this.selectedRangeValue.end;
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

    const fechaEntrada = this.datePipe.transform(this.selectedRangeValue.start, 'yyyy-MM-dd');
    const fechaSalida = this.datePipe.transform(this.selectedRangeValue.end, 'yyyy-MM-dd');

    this._peticiones.getPeticion(`${this.URL_HABITACIONES}/${fechaEntrada}/${fechaSalida}`).subscribe({
      next: (response: HabitacionDisponibleResponse[]) => {
        this.habitaciones.set(response);
        console.log(JSON.stringify(response));


        // this.habitaciones.set(JSON.parse(`[{"idHabitacion":1,"noHabitacion":"01","noOcupante":2,"noMaxOcupante":2,"noMaxExtras":2,"noCamasIndividuales":1,"noCamasMatrimoniales":0,"costo":100,"piso":"Primer Piso","servicios":[{"idServicio":1,"descripcion":"Cama matrimonial"},{"idServicio":2,"descripcion":"Cama individual"},{"idServicio":3,"descripcion":"Agua caliente"},{"idServicio":4,"descripcion":"Ventilador"}]},{"idHabitacion":2,"noHabitacion":"02","noOcupante":4,"noMaxOcupante":4,"noMaxExtras":3,"noCamasIndividuales":2,"noCamasMatrimoniales":1,"costo":150,"piso":"Segundo Piso","servicios":[{"idServicio":1,"descripcion":"Cama matrimonial"},{"idServicio":2,"descripcion":"Cama individual"},{"idServicio":3,"descripcion":"Agua caliente"},{"idServicio":4,"descripcion":"Ventilador"}]},{"idHabitacion":3,"noHabitacion":"03","noOcupante":4,"noMaxOcupante":5,"noMaxExtras":2,"noCamasIndividuales":1,"noCamasMatrimoniales":1,"costo":120,"piso":"Tercer Piso","servicios":[{"idServicio":1,"descripcion":"Cama matrimonial"},{"idServicio":2,"descripcion":"Cama individual"},{"idServicio":4,"descripcion":"Ventilador"}]},{"idHabitacion":4,"noHabitacion":"04","noOcupante":3,"noMaxOcupante":4,"noMaxExtras":1,"noCamasIndividuales":1,"noCamasMatrimoniales":1,"costo":300,"piso":"Tercer Piso","servicios":[{"idServicio":1,"descripcion":"Cama matrimonial"},{"idServicio":2,"descripcion":"Cama individual"},{"idServicio":3,"descripcion":"Agua caliente"}]}]`));

        this.isLoadedHabitaciones.set(true);
        this._alertas.cierraLoading();
      },
      error: (err: any) => {
        this._alertas.error(err);
      },
    });
  }

  // public getAllHabitacionesDisponiblesEdit(): void {

  //   const fechaEntrada = this.datePipe.transform(this.selectedRangeValue.start, 'yyyy-MM-dd');
  //   const fechaSalida = this.datePipe.transform(this.selectedRangeValue.end, 'yyyy-MM-dd');

  //   this._peticiones.getPeticion(`${this.URL_HABITACIONES}/${fechaEntrada}/${fechaSalida}`).to({
  //     next: (response: HabitacionDisponibleResponse[]) => {
  //       this.habitaciones.set(response);
  //       console.log(JSON.stringify(response));

  //       this.habitaciones.set(JSON.parse(`[{"idHabitacion":1,"noHabitacion":"01","noOcupante":2,"noMaxOcupante":2,"noMaxExtras":2,"noCamasIndividuales":1,"noCamasMatrimoniales":0,"costo":100,"piso":"Primer Piso","servicios":[{"idServicio":1,"descripcion":"Cama matrimonial"},{"idServicio":2,"descripcion":"Cama individual"},{"idServicio":3,"descripcion":"Agua caliente"},{"idServicio":4,"descripcion":"Ventilador"}]},{"idHabitacion":2,"noHabitacion":"02","noOcupante":4,"noMaxOcupante":4,"noMaxExtras":3,"noCamasIndividuales":2,"noCamasMatrimoniales":1,"costo":150,"piso":"Segundo Piso","servicios":[{"idServicio":1,"descripcion":"Cama matrimonial"},{"idServicio":2,"descripcion":"Cama individual"},{"idServicio":3,"descripcion":"Agua caliente"},{"idServicio":4,"descripcion":"Ventilador"}]},{"idHabitacion":3,"noHabitacion":"03","noOcupante":4,"noMaxOcupante":5,"noMaxExtras":2,"noCamasIndividuales":1,"noCamasMatrimoniales":1,"costo":120,"piso":"Tercer Piso","servicios":[{"idServicio":1,"descripcion":"Cama matrimonial"},{"idServicio":2,"descripcion":"Cama individual"},{"idServicio":4,"descripcion":"Ventilador"}]},{"idHabitacion":4,"noHabitacion":"04","noOcupante":3,"noMaxOcupante":4,"noMaxExtras":1,"noCamasIndividuales":1,"noCamasMatrimoniales":1,"costo":300,"piso":"Tercer Piso","servicios":[{"idServicio":1,"descripcion":"Cama matrimonial"},{"idServicio":2,"descripcion":"Cama individual"},{"idServicio":3,"descripcion":"Agua caliente"}]}]`));

  //       this.isLoadedHabitaciones.set(true);
  //       this._alertas.cierraLoading();
  //     },
  //     error: (err: any) => {
  //       this._alertas.error(err);
  //     },
  //   });
  // }

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
      next: (response: Cliente[]) => {
        this.clientes.set(response);
        this._alertas.cierraLoading();
      },
      error: (err: any) => {
        this._alertas.error(err);
      },
    });
  }

  public isSelected(idHabitacion: number, isSelected: boolean): void {
    this.habitaciones.update((habitaciones: HabitacionDisponibleResponse[]) => {
      const indexToUpdate = habitaciones.findIndex((habitacion: HabitacionDisponibleResponse) => habitacion.idHabitacion === idHabitacion);
      if (indexToUpdate !== -1) {
        habitaciones[indexToUpdate].isSelected = isSelected;
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

  public ocupantes(e): void {
    this.reservacion.noPersonas = e.target.value;
  }

  public ocupantesExtra(e): void {
    this.reservacion.noPersonaExtra = e.target.value;
  }

  public quitaHabitacion(): void {
    const index = this.reservacion.habitaciones.findIndex((habitacion: Habitacion) => habitacion.idHabitacion === this.habitacionSelected().idHabitacion);
    if (index !== -1) {
        this.reservacion.habitaciones.splice(index, 1);
        this.isSelected(this.habitacionSelected().idHabitacion, false);
    }
  }

  public agregaHabitacion(): void {
    const habitacion = new Habitacion();
    habitacion.idHabitacion = this.habitacionSelected().idHabitacion;
    habitacion.noHabitacion = this.habitacionSelected().noHabitacion;
    habitacion.costo = this.habitacionSelected().costo;
    habitacion.noOcupante = this.reservacion.noPersonas;
    habitacion.noMaxExtras = this.reservacion.noPersonaExtra;
    this.reservacion.habitaciones.push(habitacion);
    this.isSelected(this.habitacionSelected().idHabitacion, true);
  }

  public creaNuevoCliente(cliente: Cliente): void {
    this.clienteNuevo = cliente;
    this.reservacion.cliente = cliente;
  }

  public agregaCliente(cliente: Cliente): void {
    Swal.fire({
      title: "¿Desea agregar a este cliente?",
      showCancelButton: true,
      cancelButtonText: `Cancelar`,
      confirmButtonText: "¡Sí, agregar!",
    }).then((result) => {
      if (result.isConfirmed) {
        this.reservacion.cliente = cliente;
        this.clienteNuevo = new Cliente();
      }
    });
  }

  public getSubtotal(habitaciones: Habitacion[]): number {
    if(habitaciones) {
      return habitaciones.reduce((total: number, habitacion: Habitacion) => total + habitacion.costo, 0);
    } else {
      return 0;
    }
  }

  public getPersonasExtras(habitaciones: Habitacion[]) {
    if(habitaciones) {
      return habitaciones.reduce((total: number, habitacion: Habitacion) => {
        return total + Number(habitacion.noMaxExtras);
      }, 0);
    } else {
      return 0;
    }
  }

  public getPersonasExtrasTotal(habitaciones: Habitacion[]) {
    if(habitaciones) {
      return habitaciones.reduce((total: number, habitacion: Habitacion) => {
        return total + (habitacion.noMaxExtras * 50);
      }, 0);
    } else {
      return 0;
    }
  }

  public getTotal(habitaciones: Habitacion[]): number {
    if(habitaciones) {
      return habitaciones.reduce((total: number, habitacion: Habitacion) => {
        return Number(total) + Number(habitacion.costo) + (Number(habitacion.noMaxExtras) * 50);
      }, 0);
    } else {
      return 0;
    }
  }
}
