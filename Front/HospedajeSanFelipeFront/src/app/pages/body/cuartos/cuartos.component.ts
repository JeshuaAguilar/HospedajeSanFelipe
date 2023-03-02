import { Component, Output, EventEmitter, OnInit, Input, Injectable } from '@angular/core';
import { DateAdapter } from '@angular/material/core';
import { MatCalendarCellClassFunction, DateRange, MatDateRangeSelectionStrategy, MAT_DATE_RANGE_SELECTION_STRATEGY } from '@angular/material/datepicker';
import { Habitacion, Piso, Select } from '../body.model';
import { DateTime } from "luxon";

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

      const inicio = this._dateAdapter.getDate(currentRange.start);
      const final = this._dateAdapter.getDate(activeDate);
      const diferencia = final - inicio;

      const end = this._dateAdapter.addCalendarDays(currentRange.start, diferencia);

      return new DateRange<D>(start, end);
    }

    return new DateRange<D>(null, null);
  }
}

@Component({
  selector: 'app-cuartos',
  templateUrl: './cuartos.component.html',
  styleUrls: ['./cuartos.component.css'],
  providers: [
    {
      provide: MAT_DATE_RANGE_SELECTION_STRATEGY,
      useClass: FiveDayRangeSelectionStrategy,
    }
  ],
})
export class CuartosComponent implements OnInit {
  readonly PISOS_DATA = '[{"idPiso":0,"descPiso":"Piso 1","habitaciones":[{"noHabitacion":"101","piso":0,"noMatrimonial":1,"noIndividual":1,"costo":250},{"noHabitacion":"102","piso":0,"noMatrimonial":1,"noIndividual":1,"costo":250},{"noHabitacion":"103","piso":0,"noMatrimonial":1,"noIndividual":1,"costo":250},{"noHabitacion":"104","piso":0,"noMatrimonial":1,"noIndividual":1,"costo":250}]},{"idPiso":1,"descPiso":"Piso 2","habitaciones":[{"noHabitacion":"201","piso":1,"noMatrimonial":1,"noIndividual":1,"costo":250},{"noHabitacion":"202","piso":1,"noMatrimonial":1,"noIndividual":1,"costo":250},{"noHabitacion":"203","piso":1,"noMatrimonial":1,"noIndividual":1,"costo":250},{"noHabitacion":"204","piso":1,"noMatrimonial":1,"noIndividual":1,"costo":250},{"noHabitacion":"205","piso":1,"noMatrimonial":1,"noIndividual":1,"costo":250},{"noHabitacion":"206","piso":1,"noMatrimonial":1,"noIndividual":1,"costo":250},{"noHabitacion":"207","piso":1,"noMatrimonial":1,"noIndividual":1,"costo":250},{"noHabitacion":"208","piso":1,"noMatrimonial":1,"noIndividual":1,"costo":250}]},{"idPiso":2,"descPiso":"Piso 3","habitaciones":[{"noHabitacion":"301","piso":2,"noMatrimonial":1,"noIndividual":1,"costo":250},{"noHabitacion":"302","piso":2,"noMatrimonial":1,"noIndividual":1,"costo":250},{"noHabitacion":"303","piso":2,"noMatrimonial":1,"noIndividual":1,"costo":250},{"noHabitacion":"304","piso":2,"noMatrimonial":1,"noIndividual":1,"costo":250},{"noHabitacion":"305","piso":2,"noMatrimonial":1,"noIndividual":1,"costo":250},{"noHabitacion":"306","piso":2,"noMatrimonial":1,"noIndividual":1,"costo":250},{"noHabitacion":"307","piso":2,"noMatrimonial":1,"noIndividual":1,"costo":250},{"noHabitacion":"308","piso":2,"noMatrimonial":1,"noIndividual":1,"costo":250},{"noHabitacion":"309","piso":2,"noMatrimonial":1,"noIndividual":1,"costo":250}]}]';


  readonly ICONS: Select[] = [
    {'idx': 1, 'desc': '../../../../assets/icons/svg/doubleBed.svg'},
    {'idx': 2, 'desc': '../../../../assets/icons/svg/singleBed.svg'},
    {'idx': 3, 'desc': '../../../../assets/icons/svg/hotwater.svg'},
    {'idx': 4, 'desc': '../../../../assets/icons/svg/fan.svg'},
    {'idx': 5, 'desc': '../../../../assets/icons/svg/wc.svg'},
    {'idx': 6, 'desc': '../../../../assets/icons/svg/wifi.svg'},
    {'idx': 7, 'desc': '../../../../assets/icons/svg/tv.svg'}
  ];

  readonly fechasReservadas = [
    new Date("02/16/2023"),
    new Date("02/27/2023"),
    new Date("03/15/2023"),
    new Date("03/16/2023"),
    new Date("03/17/2023"),
    new Date("03/27/2023"),
    new Date("03/28/2023"),
  ];


  @Output() habitacionSelect = new EventEmitter<string>();

  selected: Date | null;
  @Input() selectedRangeValue: DateRange<Date> | undefined;
  @Output() selectedRangeValueChange = new EventEmitter<DateRange<Date>>();
  ayer: Date;

  pisos: Piso[];
  habitacionSelected: Habitacion;

  isVistaCompleta = false;

  dateClass: MatCalendarCellClassFunction<Date> = (cellDate, view) => {
    let clase = '';
    for(const fecha of this.fechasReservadas){
      if(fecha.getTime() == cellDate.getTime()) {
        clase = 'reservado';
      }
    }
    return clase;
  };

  constructor(private dateAdapter: DateAdapter<any>) {
    this.dateAdapter.setLocale('es-MX');

    const piso = JSON.parse(this.PISOS_DATA);
    this.pisos = piso;

    this.habitacionSelected = new Habitacion();

    console.log(JSON.stringify(this.pisos));
}

  public setDatosHabitacion(habitacion: Habitacion): void {
    this.habitacionSelected = null;
    this.habitacionSelected = habitacion;
  }

  public ngOnInit(): void {
    const ahora = DateTime.now();
    this.ayer = new Date(ahora.toFormat('yyyy/LL/dd'));
  }

  selectedChange(date: any) {
    if (!this.selectedRangeValue?.start || this.selectedRangeValue?.end) {
      this.selectedRangeValue = new DateRange<Date>(date, null);
    } else {
      const start = this.selectedRangeValue.start;
      const end = date;
      if (end < start) {
        // this.selectedRangeValue = new DateRange<Date>(end, start);
      } else {
        this.selectedRangeValue = new DateRange<Date>(start, end);
      }
    }
    this.selectedRangeValueChange.emit(this.selectedRangeValue);
    console.log(this.selectedRangeValue);
  }

  public cambiarVista(): void {
    this.isVistaCompleta = !this.isVistaCompleta;
  }
}
