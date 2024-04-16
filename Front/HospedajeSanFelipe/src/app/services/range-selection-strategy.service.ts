import { Injectable, inject, } from '@angular/core';
import { DateAdapter } from '@angular/material/core';
import { DateRange, MatDateRangeSelectionStrategy } from '@angular/material/datepicker';

@Injectable({
  providedIn: 'root'
})
export class RangeSelectionStrategyService<D> implements MatDateRangeSelectionStrategy<D> {

  private _dateAdapter = inject(DateAdapter<D>);

  // readonly fechasReservadas = [
  //   new Date("04/24/2024"),
  //   new Date("04/27/2024"),
  //   new Date("05/15/2024"),
  //   new Date("05/16/2024"),
  //   new Date("05/17/2024"),
  //   new Date("05/27/2024"),
  //   new Date("05/28/2024"),
  // ];

  constructor() {}

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

    // if (activeDate && currentRange.start) {
    //   const start = this._dateAdapter.addCalendarDays(currentRange.start, 0);
    //   const diferencia = Math.round((new Date(activeDate as Date).getTime() - new Date(currentRange.start as Date).getTime()) / (1000 * 60 * 60 * 24));

    //   // Se establece el rango potencial desde la fecha de inicio hasta la fecha activa calculada
    //   let potentialEnd = this._dateAdapter.addCalendarDays(currentRange.start, diferencia);
    //   let end = potentialEnd;

    //   // Verificar que no haya fechas deshabilitadas en el rango desde 'start' hasta 'potentialEnd'
    //   if (this.isDateInRangeDisabled(new Date(currentRange.start as Date), new Date(potentialEnd as Date))) {
    //     // Si hay una fecha deshabilitada, ajustar 'end' para terminar antes de la fecha deshabilitada
    //     // Encuentra el primer día deshabilitado y ajusta 'end' para que termine el día antes
    //     let day = new Date(currentRange.start as Date);
    //     while (day <= potentialEnd) {
    //       if (!this.myFilter(new Date(day))) {
    //         end = this._dateAdapter.addCalendarDays(day, -1);
    //         break;
    //       }
    //       day.setDate(day.getDate() + 1);
    //     }
    //   }

    //   return new DateRange<D>(start as D, end as D);
    // }

    return new DateRange<D>(null, null);
  }


  // public isDateInRangeDisabled(startDate: Date, endDate: Date): boolean {
  //   let day = new Date(startDate);
  //   while (day <= endDate) {
  //     if (!this.myFilter(day)) { // Utiliza el filtro existente para verificar si la fecha está deshabilitada
  //       return true;
  //     }
  //     day.setDate(day.getDate() + 1); // Incrementa el día
  //   }
  //   return false;
  // }

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

}

