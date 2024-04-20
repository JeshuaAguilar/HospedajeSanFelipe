import { ApplicationConfig, importProvidersFrom } from '@angular/core';
import { provideRouter } from '@angular/router';

import { routes } from './app.routes';
import { HttpClientModule } from '@angular/common/http';
import { provideAnimationsAsync } from '@angular/platform-browser/animations/async';

import localeEsMx from '@angular/common/locales/es-MX';
import { LOCALE_ID, DEFAULT_CURRENCY_CODE } from '@angular/core';
import { DatePipe, registerLocaleData } from '@angular/common';

registerLocaleData(localeEsMx, 'es-Mx');

export const appConfig: ApplicationConfig = {
  providers: [
    DatePipe,
    provideRouter(routes),
    { provide: LOCALE_ID, useValue: 'es-Mx' },
    { provide: DEFAULT_CURRENCY_CODE, useValue: 'MXN' },
    importProvidersFrom(HttpClientModule), provideAnimationsAsync(),
  ],
};
