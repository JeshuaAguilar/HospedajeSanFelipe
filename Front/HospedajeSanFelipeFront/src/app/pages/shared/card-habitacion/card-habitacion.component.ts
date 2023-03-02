import { Component, OnInit } from '@angular/core';
import { DateTime } from "luxon";

import { CalendarOptions, EventApi, EventInput } from '@fullcalendar/core';

import interactionPlugin from '@fullcalendar/interaction';
import esLocale from '@fullcalendar/core/locales/es';
import timeGridPlugin from '@fullcalendar/timegrid';
import dayGridPlugin from '@fullcalendar/daygrid';
import listPlugin from '@fullcalendar/list';

let eventGuid = 0;
const TODAY_STR = new Date().toISOString().replace(/T.*$/, ''); // YYYY-MM-DD of today

@Component({
  selector: 'app-card-habitacion',
  templateUrl: './card-habitacion.component.html',
  styleUrls: ['./card-habitacion.component.css']
})
export class CardHabitacionComponent implements OnInit{

  muestra = false;
  formModal: any;

  readonly MEXICO = "es-MX";
  readonly MEXICO_FORMAT = "dd/MMMM/yyyy hh:mm:ss";

  calendarOptions: CalendarOptions = {
    timeZone: 'America/Mexico_City',
    locale: esLocale,
    plugins: [
      interactionPlugin,
      dayGridPlugin,
      timeGridPlugin,
      listPlugin,
    ],
    headerToolbar: {
      left: 'prev,next today',
      center: 'title',
      right: 'dayGridMonth listWeek'
    },
    initialView: 'dayGridMonth',
    initialEvents: INITIAL_EVENTS, // alternatively, use the `events` setting to fetch from a feed
    weekends: true,
    // editable: true,
    // selectable: true,
    // selectMirror: true,
    dayMaxEvents: true,
    height: '100%',
    // rerenderDelay: 5000,
    // select: this.handleDateSelect.bind(this),
    // eventClick: this.handleEventClick.bind(this),
    // eventsSet: this.handleEvents.bind(this)
    /* you can update a remote database when these fire:
    eventAdd:
    eventChange:
    eventRemove:
    */
  };

  currentEvents: EventApi[] = [];

  ngOnInit(): void {
    console.log(`Hoy ${TODAY_STR}`);
  }


  public fecha(): string {
    // var dt = DateTime.now();
    // return dt.setLocale(this.MEXICO).toFormat(this.MEXICO_FORMAT);
    return '12/febrero/2023 12:59:37';
  }

  public change(): void {
    setTimeout(() => {
      this.muestra = true;
    }, 400)
  }
}

export const INITIAL_EVENTS: EventInput[] = [
  {
    id: createEventId(),
    title: 'All-day event',
    start: TODAY_STR
  },
  {
    id: createEventId(),
    title: 'Timed event',
    start: TODAY_STR + 'T00:00:00',
    end: TODAY_STR + 'T03:00:00'
  },
  {
    id: createEventId(),
    title: 'Timed event',
    start: TODAY_STR + 'T12:00:00',
    end: TODAY_STR + 'T15:00:00'
  }
];

export function createEventId() {
  return String(eventGuid++);
}
