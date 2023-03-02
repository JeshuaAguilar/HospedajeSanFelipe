import { DateRange, MatCalendarCellClassFunction, MatDateRangeSelectionStrategy, MAT_DATE_RANGE_SELECTION_STRATEGY } from '@angular/material/datepicker';
import { Component, OnInit, Injectable, ViewEncapsulation } from '@angular/core';
import { FormBuilder, FormGroup } from '@angular/forms';
import { DateAdapter } from '@angular/material/core';
import {map, startWith} from 'rxjs/operators';
import { EstadosMunicipios } from '../body.model';
import { DateTime } from "luxon";

import EstadosMunicipiosJson from '../../../../assets/EstadosMunicipios.json';
import { Observable } from 'rxjs';

let numeroDias = 1;

@Injectable()
export class FiveDayRangeSelectionStrategy<D> implements MatDateRangeSelectionStrategy<D> {
  constructor(private _dateAdapter: DateAdapter<D>) {}

  selectionFinished(date: D | null): DateRange<D> {
    return this._createFiveDayRange(date);
  }

  createPreview(activeDate: D | null): DateRange<D> {
    return this._createFiveDayRange(activeDate);
  }

  private _createFiveDayRange(date: D | null): DateRange<D> {
    if (date) {
      const start = this._dateAdapter.addCalendarDays(date, 0);
      const end = this._dateAdapter.addCalendarDays(date, numeroDias);
      return new DateRange<D>(start, end);
    }

    return new DateRange<D>(null, null);
  }
}

@Component({
  selector: 'app-crea-reservacion',
  templateUrl: './crea-reservacion.component.html',
  styleUrls: ['./crea-reservacion.component.css'],
  encapsulation: ViewEncapsulation.None,
  providers: [
    {
      provide: MAT_DATE_RANGE_SELECTION_STRATEGY,
      useClass: FiveDayRangeSelectionStrategy,
    }
  ],
})
export class CreaReservacionComponent implements OnInit {
  readonly ESTADOS_MUNICIPIOS: EstadosMunicipios[] = EstadosMunicipiosJson;
  filteredEstados: Observable<string[]>;
  filteredMunicipios: Observable<string[]>;

  reservacionForm: FormGroup;

  estadoSeleccionado: EstadosMunicipios;
  ayer: Date;

  /*Validadores*/
  get isNombreValid() {
    return this.reservacionForm.get('nombre').invalid && this.reservacionForm.get('nombre').touched;
  }
  get isApellidosValid() {
    return this.reservacionForm.get('apellidos').invalid && this.reservacionForm.get('apellidos').touched;
  }
  get isEstadoValid() {
    return this.reservacionForm.get('estado').invalid && this.reservacionForm.get('estado').touched;
  }
  get isMunicipioValid() {
    return this.reservacionForm.get('municipio').invalid && this.reservacionForm.get('municipio').touched;
  }
  get isNoCelularValid() {
    return this.reservacionForm.get('noCelular').invalid && this.reservacionForm.get('noCelular').touched;
  }
  get isEmailValid() {
    return this.reservacionForm.get('email').invalid && this.reservacionForm.get('email').touched;
  }
  get isNoDiasValid() {
    return this.reservacionForm.get('noDias').invalid && this.reservacionForm.get('noDias').touched;
  }
  get isEntradaValid() {
    return this.reservacionForm.get('noDias').invalid && this.reservacionForm.get('noDias').touched;
  }

  fechasReservadas = (d: Date): boolean => {
    const time=d.getTime();
    return !this.myHolidayDates.find(x=>x.getTime()==time);
  }

  dateClass: MatCalendarCellClassFunction<Date> = (cellDate, view) => {
    let clase = '';
    for(const fecha of this.myHolidayDates){
      if(fecha.getTime() == cellDate.getTime()) {
        clase = 'reservado';
      }
    }
    return clase;
  };

  constructor(private fmb: FormBuilder, private dateAdapter: DateAdapter<any>) {
    this.dateAdapter.setLocale('es-MX');
  }

  private creaFormulario(): void {
    this.reservacionForm = this.fmb.group({
      nombre   : '',
      apellidos: '',
      estado   : '',
      municipio: '',
      noCelular: '',
      email    : '',
      noDias   : 1,
      fechaEntrada   : '',
      fechaSalida   : ''
    });
  }

  ngOnInit(): void {
    this.creaFormulario();
    // for (const iterator of object) {
    // }
    var dt = DateTime.now();
    // this.ayer = new Date(dt.plus({ days: -1 }).toFormat('yyyy/LL/dd'));
    this.ayer = new Date(dt.toFormat('yyyy/LL/dd'));

    this.filteredEstados = this.reservacionForm.get('estado').valueChanges.pipe(
      startWith(''),
      map(value => this._filterEstados(value || '')),
    );
  }

  public setDias(dias: string): void {
    numeroDias = Number(dias)-1;
  }

  public evitaSolapacion(event: any): void {

    const selectDate: DateTime = DateTime.fromISO(event.value);

    let arrayMilis: number[] = this.myHolidayDates.map((fecha: Date) => fecha.getDate());

    const minimo = Math.min(...arrayMilis);

    const diferencia = selectDate.diff(minimo, ["days"])
    console.log(diferencia)
  }

  private _filterEstados(value: string): string[] {
    const filterValue = value.toLowerCase();
    return this.ESTADOS_MUNICIPIOS.reduce((estados, { estado }) => estado.toLocaleLowerCase().includes(filterValue) ? estados.push(estado) && estados : estados, []);
  }

  public setEstado(): void {
    this.estadoSeleccionado = this.ESTADOS_MUNICIPIOS.find((estado: EstadosMunicipios) => estado.estado.toLocaleLowerCase() === this.reservacionForm.get('estado').value.toLocaleLowerCase());

    this.reservacionForm.get('municipio').reset();

    this.filteredMunicipios = this.reservacionForm.get('municipio').valueChanges.pipe(
      startWith(''),
      map(value => this._filterMunicipios(value || '')),
    );
  }

  private _filterMunicipios(value: string): string[] {
    const filterValue = value.toLowerCase();
    let response: string[];
    if(this.estadoSeleccionado) {
      response = this.estadoSeleccionado.municipios.filter(option => option.toLowerCase().includes(filterValue));
    }
    return response;
  }

  myHolidayDates = [
    // new Date("1/02/2023"),
    // new Date("12/02/2023"),
    new Date("02/15/2023"),
    new Date("02/16/2023"),
    new Date("02/17/2023"),
    new Date("02/27/2023"),
    new Date("02/28/2023"),
];
}
